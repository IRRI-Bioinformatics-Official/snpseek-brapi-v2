package org.irri.snpseek.brapi.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JPA entity for the {@code variantset} table.
 *
 * <p>Schema source: {@code docs/schema/db_spec.md §1}.
 *
 * <pre>
 * variantset
 * ├── variantset_id  integer       NOT NULL  PK
 * ├── name           varchar       NULL
 * ├── description    text          NULL
 * ├── variant_type_id integer      NOT NULL  FK → cvterm.cvterm_id
 * └── organism_id    integer       NULL      FK → organism.organism_id
 * </pre>
 *
 * {@code cvterm} and {@code organism} are read-only supporting tables not
 * managed by this service, so their foreign keys are mapped as plain
 * {@code Integer} columns rather than full {@code @ManyToOne} associations.
 */
@Entity
@Table(name = "variantset", schema = "public")
public class VariantSet {

    @Id
    @Column(name = "variantset_id", nullable = false)
    private Integer variantSetId;

    /** Human-readable name of the variant set (nullable per spec). */
    @Column(name = "name")
    private String name;

    /** Detailed description (nullable per spec). */
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    /**
     * FK to {@code cvterm.cvterm_id} identifying the variant type (e.g. SNP, InDel).
     * NOT NULL per spec.
     */
    @Column(name = "variant_type_id", nullable = false)
    private Integer variantTypeId;

    /**
     * FK to {@code organism.organism_id}.
     * Nullable per spec.
     */
    @Column(name = "organism_id")
    private Integer organismId;

    /**
     * Genotyping platforms associated with this variant set.
     * Mapped by {@code platform.variantset_id} (spec §2).
     *
     * <p>Fetched lazily — use
     * {@link org.irri.snpseek.brapi.repository.VariantSetRepository#findByIdWithGenotypeRuns}
     * when the full run graph is needed.
     */
    @OneToMany(mappedBy = "variantSet", fetch = FetchType.LAZY)
    private List<Platform> platforms = new ArrayList<>();

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    protected VariantSet() {}

    // -------------------------------------------------------------------------
    // Accessors
    // -------------------------------------------------------------------------

    public Integer getVariantSetId()                          { return variantSetId; }

    public String getName()                                   { return name; }
    public void setName(String name)                          { this.name = name; }

    public String getDescription()                            { return description; }
    public void setDescription(String description)            { this.description = description; }

    public Integer getVariantTypeId()                         { return variantTypeId; }
    public void setVariantTypeId(Integer variantTypeId)       { this.variantTypeId = variantTypeId; }

    public Integer getOrganismId()                            { return organismId; }
    public void setOrganismId(Integer organismId)             { this.organismId = organismId; }

    public List<Platform> getPlatforms()                      { return platforms; }
}
