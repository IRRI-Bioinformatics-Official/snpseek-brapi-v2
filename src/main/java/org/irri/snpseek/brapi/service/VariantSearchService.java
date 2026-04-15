package org.irri.snpseek.brapi.service;

import jakarta.persistence.criteria.Predicate;
import org.irri.snpseek.brapi.domain.SnpMetadata;
import org.irri.snpseek.brapi.dto.VariantSearchRequest;
import org.irri.snpseek.brapi.repository.SnpMetadataRepository;
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
 * Handles BrAPI variant searches against the {@code v_snp_refposindex_v2} view.
 *
 * <h3>Async search pattern</h3>
 * <p>{@link #executeAsync} is annotated {@link Async} so Spring runs the
 * database query on the shared task executor thread pool, returning a
 * {@link CompletableFuture} immediately.  The controller caches the future
 * under a UUID ({@link #cache}) and checks {@code isDone()} to decide whether
 * to return HTTP 200 (results ready) or 202 (still processing).
 *
 * <p>Note: the cache has no eviction policy — for production use, replace the
 * plain {@link ConcurrentHashMap} with a size- or time-bounded cache such as
 * Caffeine.
 */
@Service
public class VariantSearchService {

    private final SnpMetadataRepository repository;

    /** In-memory store: searchResultsDbId → async search result. */
    final ConcurrentHashMap<String, CompletableFuture<Page<SnpMetadata>>> cache =
            new ConcurrentHashMap<>();

    public VariantSearchService(SnpMetadataRepository repository) {
        this.repository = repository;
    }

    // -------------------------------------------------------------------------
    // Async execution — called from controller (Spring AOP proxy ensures @Async works)
    // -------------------------------------------------------------------------

    /**
     * Execute the search on the async executor thread pool.
     * Spring wraps the return value in a new {@link CompletableFuture} that
     * completes when the method body finishes.
     */
    @Async
    public CompletableFuture<Page<SnpMetadata>> executeAsync(VariantSearchRequest req) {
        Specification<SnpMetadata> spec    = buildSpec(req);
        PageRequest                pageable = PageRequest.of(req.effectivePage(), req.effectivePageSize());
        return CompletableFuture.completedFuture(repository.findAll(spec, pageable));
    }

    // -------------------------------------------------------------------------
    // Cache access
    // -------------------------------------------------------------------------

    public void cacheSearch(String searchId, CompletableFuture<Page<SnpMetadata>> future) {
        cache.put(searchId, future);
    }

    public Optional<CompletableFuture<Page<SnpMetadata>>> getResult(String searchId) {
        return Optional.ofNullable(cache.get(searchId));
    }

    // -------------------------------------------------------------------------
    // Specification builder
    // -------------------------------------------------------------------------

    /**
     * Build a JPA {@link Specification} from the search request.
     * Only non-null / non-empty fields are added as predicates.
     */
    public Specification<SnpMetadata> buildSpec(VariantSearchRequest req) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (notEmpty(req.variantDbIds())) {
                List<Integer> ids = req.variantDbIds().stream()
                        .map(Integer::parseInt)
                        .toList();
                predicates.add(root.get("snpFeatureId").in(ids));
            }

            if (notEmpty(req.variantSetDbIds())) {
                predicates.add(root.get("variantset").in(req.variantSetDbIds()));
            }

            if (notEmpty(req.referenceNames())) {
                List<Integer> chroms = req.referenceNames().stream()
                        .map(Integer::parseInt)
                        .toList();
                predicates.add(root.get("chromosome").in(chroms));
            }

            if (req.start() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("position"), req.start().intValue()));
            }

            if (req.end() != null) {
                predicates.add(cb.lessThan(root.get("position"), req.end().intValue()));
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
