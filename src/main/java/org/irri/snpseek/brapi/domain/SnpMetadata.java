package org.irri.snpseek.brapi.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

/**
 * Read-only JPA mapping for the {@code v_snp_refposindex_v2} view (aliased
 * "snp_metadata" in project docs).  {@link Immutable} prevents Hibernate from
 * ever issuing INSERT/UPDATE/DELETE against the view.
 *
 * <pre>
 * v_snp_refposindex_v2
 * ├── snp_feature_id  integer    PK
 * ├── chromosome      integer
 * ├── position        integer
 * ├── variantset      varchar
 * ├── refcall         char(1)
 * ├── altcall         varchar
 * └── allele_index    integer
 * </pre>
 */
@Entity
@Immutable
@Table(name = "v_snp_refposindex_v2", schema = "public")
public class SnpMetadata {

    @Id
    @Column(name = "snp_feature_id", nullable = false)
    private Integer snpFeatureId;

    @Column(name = "chromosome")
    private Integer chromosome;

    @Column(name = "position")
    private Integer position;

    /** Corresponds to {@code variantset.variantset_id} cast to text in the view. */
    @Column(name = "variantset")
    private String variantset;

    /** Reference allele call (single character). */
    @Column(name = "refcall")
    @org.hibernate.annotations.JdbcTypeCode(java.sql.Types.CHAR)
    private String refcall;

    /** Alternate allele call(s); may contain multiple alleles separated by "/". */
    @Column(name = "altcall")
    private String altcall;

    /** Chado feature type (FK → cvterm.cvterm_id). Used to distinguish SNP from indel. */
    @Column(name = "type_id")
    private Integer typeId;

    /**
     * 0-based row index into the HDF5 genotype matrix for this SNP position.
     * Used by {@link org.irri.snpseek.brapi.service.AlleleMatrixService} to
     * address the correct row when slicing a sub-matrix.
     */
    @Column(name = "allele_index")
    private Integer alleleIndex;

    // -------------------------------------------------------------------------
    // Accessors (no setters — immutable view)
    // -------------------------------------------------------------------------

    public Integer getSnpFeatureId() { return snpFeatureId; }
    public Integer getChromosome()   { return chromosome; }
    public Integer getPosition()     { return position; }
    public String  getVariantset()   { return variantset; }
    public String  getRefcall()      { return refcall; }
    public String  getAltcall()      { return altcall; }
    public Integer getTypeId()       { return typeId; }
    public Integer getAlleleIndex()   { return alleleIndex; }
}
