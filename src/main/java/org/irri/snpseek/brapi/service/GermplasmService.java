package org.irri.snpseek.brapi.service;

import jakarta.persistence.criteria.Predicate;
import org.irri.snpseek.brapi.domain.Germplasm;
import org.irri.snpseek.brapi.dto.GermplasmSearchRequest;
import org.irri.snpseek.brapi.repository.GermplasmRepository;
import org.irri.snpseek.brapi.repository.VariantSetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Handles BrAPI germplasm searches against the {@code v_allstock_basicprop} view.
 *
 * <h3>Async search pattern</h3>
 * <p>{@link #search} is annotated {@link Async} so Spring runs the database
 * query on the shared task executor thread pool.  The controller caches the
 * future under a UUID and checks {@code isDone()} to decide between HTTP 200
 * and 202.
 *
 * <p>Note: the in-memory cache has no eviction policy — for production use,
 * replace the {@link ConcurrentHashMap} with a size- or time-bounded cache.
 */
@Service
public class GermplasmService {

    private final GermplasmRepository  germplasmRepository;
    private final VariantSetRepository variantSetRepository;

    /** In-memory store: searchResultsDbId → async search result. */
    private final ConcurrentHashMap<String, CompletableFuture<Page<Germplasm>>> cache =
            new ConcurrentHashMap<>();

    public GermplasmService(GermplasmRepository germplasmRepository,
                            VariantSetRepository variantSetRepository) {
        this.germplasmRepository  = germplasmRepository;
        this.variantSetRepository = variantSetRepository;
    }

    // -------------------------------------------------------------------------
    // Async execution
    // -------------------------------------------------------------------------

    /**
     * Execute the germplasm search on the async executor thread pool.
     * Spring wraps the return value in a new {@link CompletableFuture} that
     * completes when the method body finishes.
     */
    @Async
    public CompletableFuture<Page<Germplasm>> search(GermplasmSearchRequest req) {
        Specification<Germplasm> spec    = buildSpec(req);
        PageRequest              pageable = PageRequest.of(req.effectivePage(), req.effectivePageSize());
        return CompletableFuture.completedFuture(germplasmRepository.findAll(spec, pageable));
    }

    // -------------------------------------------------------------------------
    // Single-item lookup
    // -------------------------------------------------------------------------

    /**
     * Find any germplasm row whose {@code stock_id} matches the given value.
     * Multiple rows may share the same {@code stock_id} (one per variant set);
     * this returns the first one found.
     *
     * @param stockId the germplasmDbId (numeric stock_id from the view)
     * @return first matching row, or empty
     */
    public Optional<Germplasm> findByGermplasmDbId(Long stockId) {
        Specification<Germplasm> spec = (root, query, cb) ->
                cb.equal(root.get("stockId"), stockId);
        return germplasmRepository.findAll(spec, PageRequest.of(0, 1))
                .getContent()
                .stream()
                .findFirst();
    }

    // -------------------------------------------------------------------------
    // Cache access
    // -------------------------------------------------------------------------

    public void cacheSearch(String searchId, CompletableFuture<Page<Germplasm>> future) {
        cache.put(searchId, future);
    }

    public Optional<CompletableFuture<Page<Germplasm>>> getResult(String searchId) {
        return Optional.ofNullable(cache.get(searchId));
    }

    // -------------------------------------------------------------------------
    // Specification builder
    // -------------------------------------------------------------------------

    /**
     * Build a JPA {@link Specification} from the search request.
     * Only non-null / non-empty fields are added as predicates.
     */
    public Specification<Germplasm> buildSpec(GermplasmSearchRequest req) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // germplasmDbIds → stock_id IN (...)
            if (notEmpty(req.germplasmDbIds())) {
                List<Long> ids = req.germplasmDbIds().stream()
                        .map(Long::parseLong)
                        .toList();
                predicates.add(root.get("stockId").in(ids));
            }

            // germplasmNames → name IN (...)
            if (notEmpty(req.germplasmNames())) {
                predicates.add(root.get("name").in(req.germplasmNames()));
            }

            // accessionNumbers → gs_accession IN (...)
            if (notEmpty(req.accessionNumbers())) {
                predicates.add(root.get("accessionNumber").in(req.accessionNumbers()));
            }

            // countries → ori_country IN (...)
            if (notEmpty(req.countries())) {
                predicates.add(root.get("country").in(req.countries()));
            }

            // subpopulations → subpopulation IN (...)
            if (notEmpty(req.subpopulations())) {
                predicates.add(root.get("subpopulation").in(req.subpopulations()));
            }

            // variantSetDbIds → resolve VariantSet.name and filter by dataset
            if (notEmpty(req.variantSetDbIds())) {
                List<String> datasetNames = req.variantSetDbIds().stream()
                        .map(id -> {
                            try {
                                return variantSetRepository.findById(Integer.parseInt(id))
                                        .map(vs -> vs.getName())
                                        .orElse(null);
                            } catch (NumberFormatException e) {
                                return null;
                            }
                        })
                        .filter(name -> name != null)
                        .toList();
                if (!datasetNames.isEmpty()) {
                    predicates.add(root.get("dataset").in(datasetNames));
                } else {
                    // No valid variantSets resolved — return nothing
                    predicates.add(cb.disjunction());
                }
            }

            return predicates.isEmpty()
                    ? cb.conjunction()
                    : cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static boolean notEmpty(List<?> list) {
        return list != null && !list.isEmpty();
    }
}
