package org.irri.snpseek.brapi.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * JPA entity for the {@code genotype_run} table.
 *
 * <p>Schema source: {@code docs/schema/db_spec.md §3}.
 *
 * <pre>
 * genotype_run
 * ├── genotype_run_id  integer           NOT NULL  PK
 * ├── platform_id      integer           NULL      FK → platform.platform_id
 * ├── date_performed   date              NULL
 * ├── data_location    character varying NULL      path / filename of the HDF5 file
 * └── visible          boolean           NULL
 * </pre>
 *
 * The {@code data_location} column is the entry point for
 * {@link org.irri.snpseek.brapi.service.GenotypeStorageService}: it holds the
 * file name or relative path of the HDF5 genotype matrix
 * (e.g. {@code SNPuni_geno_NB_3k.h5}).  The service prepends the configured
 * base directory ({@code brapi.hdf5.data-dir}) to obtain an absolute path.
 */
@Entity
@Table(name = "genotype_run", schema = "public")
public class GenotypeRun {

    @Id
    @Column(name = "genotype_run_id", nullable = false)
    private Integer genotypeRunId;

    /** Owning side of the Platform → GenotypeRun association. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platform_id")
    private Platform platform;

    /** Date the genotyping experiment was performed. Nullable per spec. */
    @Column(name = "date_performed")
    private LocalDate datePerformed;

    /**
     * File name or relative path of the HDF5 genotype data file.
     * Examples from spec: {@code SNPuni_geno_NB_3k.h5}, {@code filtered_3kv1.h5}.
     * Nullable per spec; a run without a data_location cannot serve genotype calls.
     */
    @Column(name = "data_location")
    private String dataLocation;

    /**
     * Whether this run is visible to users.
     * Only visible runs are opened by {@link org.irri.snpseek.brapi.service.GenotypeStorageService}.
     * Nullable per spec; treated as {@code false} when null.
     */
    @Column(name = "visible")
    private Boolean visible;

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    protected GenotypeRun() {}

    // -------------------------------------------------------------------------
    // Accessors
    // -------------------------------------------------------------------------

    public Integer getGenotypeRunId()                           { return genotypeRunId; }

    public Platform getPlatform()                               { return platform; }
    public void setPlatform(Platform platform)                  { this.platform = platform; }

    public LocalDate getDatePerformed()                         { return datePerformed; }
    public void setDatePerformed(LocalDate datePerformed)       { this.datePerformed = datePerformed; }

    public String getDataLocation()                             { return dataLocation; }
    public void setDataLocation(String dataLocation)            { this.dataLocation = dataLocation; }

    /** Returns {@code true} only when the {@code visible} column is explicitly {@code true}. */
    public boolean isVisible()                                  { return Boolean.TRUE.equals(visible); }
    public void setVisible(Boolean visible)                     { this.visible = visible; }
}
