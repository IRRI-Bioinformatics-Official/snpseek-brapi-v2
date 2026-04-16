package org.irri.snpseek.brapi.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

/**
 * Read-only JPA mapping for the {@code v_allstock_basicprop} view.
 * {@link Immutable} prevents Hibernate from ever issuing INSERT/UPDATE/DELETE.
 *
 * <pre>
 * v_allstock_basicprop
 * ├── stock_sample_id  int4       PK  (HDF5 column index = callSetDbId)
 * ├── stock_id         bigint         (germplasmDbId)
 * ├── name             varchar
 * ├── assay            varchar        (irisId)
 * ├── ori_country      varchar
 * ├── subpopulation    varchar
 * ├── box_code         varchar
 * ├── gs_accession     varchar
 * └── dataset          varchar        (matches VariantSet.name)
 * </pre>
 */
@Entity
@Immutable
@Table(name = "v_allstock_basicprop", schema = "public")
public class Germplasm {

    @Id
    @Column(name = "stock_sample_id", nullable = false)
    private Integer stockSampleId;

    /** germplasmDbId in BrAPI terms. */
    @Column(name = "stock_id")
    private Long stockId;

    @Column(name = "name")
    private String name;

    /** IRIS germplasm identifier (stored in the {@code assay} column). */
    @Column(name = "assay")
    private String irisId;

    @Column(name = "ori_country")
    private String country;

    @Column(name = "subpopulation")
    private String subpopulation;

    @Column(name = "box_code")
    private String boxCode;

    @Column(name = "gs_accession")
    private String accessionNumber;

    /** Matches {@link VariantSet#getName()} and {@link SnpMetadata#getVariantset()}. */
    @Column(name = "dataset")
    private String dataset;

    // -------------------------------------------------------------------------
    // Accessors (no setters — immutable view)
    // -------------------------------------------------------------------------

    public Integer getStockSampleId()   { return stockSampleId; }
    public Long   getStockId()         { return stockId; }
    public String getName()            { return name; }
    public String getIrisId()          { return irisId; }
    public String getCountry()         { return country; }
    public String getSubpopulation()   { return subpopulation; }
    public String getBoxCode()         { return boxCode; }
    public String getAccessionNumber() { return accessionNumber; }
    public String getDataset()         { return dataset; }
}
