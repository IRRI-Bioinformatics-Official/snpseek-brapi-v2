package org.irri.snpseek.brapi.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JPA entity for the {@code platform} table.
 *
 * <p>Schema source: {@code docs/schema/db_spec.md §2}.
 *
 * <pre>
 * platform
 * ├── platform_id          integer  NOT NULL  PK
 * ├── variantset_id        integer  NULL      FK → variantset.variantset_id
 * ├── db_id                integer  NULL      FK → db.db_id
 * └── genotyping_method_id integer  NULL      FK → cvterm.cvterm_id
 * </pre>
 *
 * {@code db} and {@code cvterm} are read-only supporting tables not managed by
 * this service; their keys are stored as plain {@code Integer} columns.
 */
@Entity
@Table(name = "platform", schema = "public")
public class Platform {

    @Id
    @Column(name = "platform_id", nullable = false)
    private Integer platformId;

    /** Owning side of the VariantSet → Platform association. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variantset_id")
    private VariantSet variantSet;

    /**
     * FK to {@code db.db_id} (external database reference).
     * Nullable per spec.
     */
    @Column(name = "db_id")
    private Integer dbId;

    /**
     * FK to {@code cvterm.cvterm_id} for the genotyping method.
     * Nullable per spec.
     */
    @Column(name = "genotyping_method_id")
    private Integer genotypingMethodId;

    /**
     * Genotype runs that used this platform.
     * Mapped by {@code genotype_run.platform_id} (spec §3).
     */
    @OneToMany(mappedBy = "platform", fetch = FetchType.LAZY)
    private List<GenotypeRun> genotypeRuns = new ArrayList<>();

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    protected Platform() {}

    // -------------------------------------------------------------------------
    // Accessors
    // -------------------------------------------------------------------------

    public Integer getPlatformId()                              { return platformId; }

    public VariantSet getVariantSet()                           { return variantSet; }
    public void setVariantSet(VariantSet variantSet)            { this.variantSet = variantSet; }

    public Integer getDbId()                                    { return dbId; }
    public void setDbId(Integer dbId)                           { this.dbId = dbId; }

    public Integer getGenotypingMethodId()                      { return genotypingMethodId; }
    public void setGenotypingMethodId(Integer id)               { this.genotypingMethodId = id; }

    public List<GenotypeRun> getGenotypeRuns()                  { return genotypeRuns; }
}
