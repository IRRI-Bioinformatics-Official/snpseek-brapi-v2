package org.irri.iric.ds.chado.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.irri.iric.ds.chado.domain.Locus;



/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVLocusIntxnRicenetv1s", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByAtcxDenserank", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.atcxDenserank = ?1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByAtcxRank", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.atcxRank = ?1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByAtcxScore", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.atcxScore = ?1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByAtdcDenserank", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.atdcDenserank = ?1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByAtdcRank", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.atdcRank = ?1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByAtdcScore", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.atdcScore = ?1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByCommonName", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.commonName = ?1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByCommonNameContaining", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.commonName like ?1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByContigId", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.contigId = ?1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByContigName", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.contigName = ?1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByContigNameContaining", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.contigName like ?1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByFeatureId", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.featureId = ?1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByFmax", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.fmax = ?1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByFmin", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.fmin = ?1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByIntnetDenserank", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.intnetDenserank = ?1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByIntnetRank", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.intnetRank = ?1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByIntnetScore", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.intnetScore = ?1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByName", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.name = ?1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByNameContaining", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.name like ?1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByNotes", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.notes = ?1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByNotesContaining", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.notes like ?1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByOrganismId", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.organismId = ?1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByOscxDenserank", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.oscxDenserank = ?1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByOscxRank", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.oscxRank = ?1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByOscxScore", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.oscxScore = ?1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByOsgnDenserank", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.osgnDenserank = ?1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByOsgnRank", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.osgnRank = ?1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByOsgnScore", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.osgnScore = ?1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByOspgDenserank", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.ospgDenserank = ?1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByOspgRank", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.ospgRank = ?1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByOspgScore", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.ospgScore = ?1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByPrimaryKey", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.featureId = ?1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByQfeatureId", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.qfeatureId = ?1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByQfeatureName", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.qfeatureName = ?1"),

		@NamedQuery(name = "findVLocusIntxnRicenetv1ByQfeatureIdIn", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.qfeatureId in (?1) order by  myVLocusIntxnRicenetv1.oscxScore desc"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByQfeatureNameIn", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.qfeatureName in (?1) order by  myVLocusIntxnRicenetv1.oscxScore desc"),

		@NamedQuery(name = "findVLocusIntxnRicenetv1ByQfeatureNameContaining", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.qfeatureName like ?1"),
		@NamedQuery(name = "findVLocusIntxnRicenetv1ByStrand", query = "select myVLocusIntxnRicenetv1 from VLocusIntxnRicenetv1 myVLocusIntxnRicenetv1 where myVLocusIntxnRicenetv1.strand = ?1") })
@Table(name = "V_LOCUS_INTXN_RICENETV1")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/oracle/domain", name = "VLocusIntxnRicenetv1")
public class VLocusIntxnRicenetv1 implements Serializable, Locus {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "FEATURE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal featureId;
	/**
	 */

	@Column(name = "COMMON_NAME", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String commonName;
	/**
	 */

	@Column(name = "CONTIG_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal contigId;
	/**
	 */

	@Column(name = "CONTIG_NAME", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String contigName;
	/**
	 */

	@Column(name = "NAME", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 */

	@Column(name = "FMIN", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer fmin;
	/**
	 */

	@Column(name = "FMAX", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer fmax;
	/**
	 */

	@Column(name = "STRAND", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer strand;
	/**
	 */

	@Column(name = "ORGANISM_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal organismId;
	/**
	 */

	@Column(name = "NOTES", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String notes;
	/**
	 */

	@Column(name = "QFEATURE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal qfeatureId;
	/**
	 */

	@Column(name = "QFEATURE_NAME", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String qfeatureName;
	/**
	 */

	@Column(name = "ATCX_SCORE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal atcxScore;
	/**
	 */

	@Column(name = "ATCX_RANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer atcxRank;
	/**
	 */

	@Column(name = "ATCX_DENSERANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer atcxDenserank;
	/**
	 */

	@Column(name = "ATDC_SCORE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal atdcScore;
	/**
	 */

	@Column(name = "ATDC_RANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer atdcRank;
	/**
	 */

	@Column(name = "ATDC_DENSERANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer atdcDenserank;
	/**
	 */

	@Column(name = "OSCX_SCORE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal oscxScore;
	/**
	 */

	@Column(name = "OSCX_RANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer oscxRank;
	/**
	 */

	@Column(name = "OSCX_DENSERANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer oscxDenserank;
	/**
	 */

	@Column(name = "OSGN_SCORE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal osgnScore;
	/**
	 */

	@Column(name = "OSGN_RANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer osgnRank;
	/**
	 */

	@Column(name = "OSGN_DENSERANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer osgnDenserank;
	/**
	 */

	@Column(name = "OSPG_SCORE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal ospgScore;
	/**
	 */

	@Column(name = "OSPG_RANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer ospgRank;
	/**
	 */

	@Column(name = "OSPG_DENSERANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer ospgDenserank;
	/**
	 */

	@Column(name = "INTNET_SCORE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal intnetScore;
	/**
	 */

	@Column(name = "INTNET_RANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer intnetRank;
	/**
	 */

	@Column(name = "INTNET_DENSERANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer intnetDenserank;

	/**
	 */
	public void setFeatureId(BigDecimal featureId) {
		this.featureId = featureId;
	}

	/**
	 */
	public BigDecimal getFeatureId() {
		return this.featureId;
	}

	/**
	 */
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	/**
	 */
	public String getCommonName() {
		return this.commonName;
	}

	/**
	 */
	public void setContigId(BigDecimal contigId) {
		this.contigId = contigId;
	}

	/**
	 */
	public BigDecimal getContigId() {
		return this.contigId;
	}

	/**
	 */
	public void setContigName(String contigName) {
		this.contigName = contigName;
	}

	/**
	 */
	public String getContigName() {
		return this.contigName;
	}

	/**
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 */
	public String getName() {
		return this.name;
	}

	/**
	 */
	public void setFmin(Integer fmin) {
		this.fmin = fmin;
	}

	/**
	 */
	public Integer getFmin() {
		return this.fmin;
	}

	/**
	 */
	public void setFmax(Integer fmax) {
		this.fmax = fmax;
	}

	/**
	 */
	public Integer getFmax() {
		return this.fmax;
	}

	/**
	 */
	public void setStrand(Integer strand) {
		this.strand = strand;
	}

	/**
	 */
	public Integer getStrand() {
		return this.strand;
	}

	/**
	 */
	public void setOrganismId(BigDecimal organismId) {
		this.organismId = organismId;
	}

	/**
	 */
	public BigDecimal getOrganismId() {
		return this.organismId;
	}

	/**
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 */
	public String getNotes() {
		return this.notes;
	}

	/**
	 */
	public void setQfeatureId(BigDecimal qfeatureId) {
		this.qfeatureId = qfeatureId;
	}

	/**
	 */
	public BigDecimal getQfeatureId() {
		return this.qfeatureId;
	}

	/**
	 */
	public void setQfeatureName(String qfeatureName) {
		this.qfeatureName = qfeatureName;
	}

	/**
	 */
	public String getQfeatureName() {
		return this.qfeatureName;
	}

	/**
	 */
	public void setAtcxScore(BigDecimal atcxScore) {
		this.atcxScore = atcxScore;
	}

	/**
	 */
	public BigDecimal getAtcxScore() {
		return this.atcxScore;
	}

	/**
	 */
	public void setAtcxRank(Integer atcxRank) {
		this.atcxRank = atcxRank;
	}

	/**
	 */
	public Integer getAtcxRank() {
		return this.atcxRank;
	}

	/**
	 */
	public void setAtcxDenserank(Integer atcxDenserank) {
		this.atcxDenserank = atcxDenserank;
	}

	/**
	 */
	public Integer getAtcxDenserank() {
		return this.atcxDenserank;
	}

	/**
	 */
	public void setAtdcScore(BigDecimal atdcScore) {
		this.atdcScore = atdcScore;
	}

	/**
	 */
	public BigDecimal getAtdcScore() {
		return this.atdcScore;
	}

	/**
	 */
	public void setAtdcRank(Integer atdcRank) {
		this.atdcRank = atdcRank;
	}

	/**
	 */
	public Integer getAtdcRank() {
		return this.atdcRank;
	}

	/**
	 */
	public void setAtdcDenserank(Integer atdcDenserank) {
		this.atdcDenserank = atdcDenserank;
	}

	/**
	 */
	public Integer getAtdcDenserank() {
		return this.atdcDenserank;
	}

	/**
	 */
	public void setOscxScore(BigDecimal oscxScore) {
		this.oscxScore = oscxScore;
	}

	/**
	 */
	public BigDecimal getOscxScore() {
		return this.oscxScore;
	}

	/**
	 */
	public void setOscxRank(Integer oscxRank) {
		this.oscxRank = oscxRank;
	}

	/**
	 */
	public Integer getOscxRank() {
		return this.oscxRank;
	}

	/**
	 */
	public void setOscxDenserank(Integer oscxDenserank) {
		this.oscxDenserank = oscxDenserank;
	}

	/**
	 */
	public Integer getOscxDenserank() {
		return this.oscxDenserank;
	}

	/**
	 */
	public void setOsgnScore(BigDecimal osgnScore) {
		this.osgnScore = osgnScore;
	}

	/**
	 */
	public BigDecimal getOsgnScore() {
		return this.osgnScore;
	}

	/**
	 */
	public void setOsgnRank(Integer osgnRank) {
		this.osgnRank = osgnRank;
	}

	/**
	 */
	public Integer getOsgnRank() {
		return this.osgnRank;
	}

	/**
	 */
	public void setOsgnDenserank(Integer osgnDenserank) {
		this.osgnDenserank = osgnDenserank;
	}

	/**
	 */
	public Integer getOsgnDenserank() {
		return this.osgnDenserank;
	}

	/**
	 */
	public void setOspgScore(BigDecimal ospgScore) {
		this.ospgScore = ospgScore;
	}

	/**
	 */
	public BigDecimal getOspgScore() {
		return this.ospgScore;
	}

	/**
	 */
	public void setOspgRank(Integer ospgRank) {
		this.ospgRank = ospgRank;
	}

	/**
	 */
	public Integer getOspgRank() {
		return this.ospgRank;
	}

	/**
	 */
	public void setOspgDenserank(Integer ospgDenserank) {
		this.ospgDenserank = ospgDenserank;
	}

	/**
	 */
	public Integer getOspgDenserank() {
		return this.ospgDenserank;
	}

	/**
	 */
	public void setIntnetScore(BigDecimal intnetScore) {
		this.intnetScore = intnetScore;
	}

	/**
	 */
	public BigDecimal getIntnetScore() {
		return this.intnetScore;
	}

	/**
	 */
	public void setIntnetRank(Integer intnetRank) {
		this.intnetRank = intnetRank;
	}

	/**
	 */
	public Integer getIntnetRank() {
		return this.intnetRank;
	}

	/**
	 */
	public void setIntnetDenserank(Integer intnetDenserank) {
		this.intnetDenserank = intnetDenserank;
	}

	/**
	 */
	public Integer getIntnetDenserank() {
		return this.intnetDenserank;
	}

	/**
	 */
	public VLocusIntxnRicenetv1() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VLocusIntxnRicenetv1 that) {
		setFeatureId(that.getFeatureId());
		setCommonName(that.getCommonName());
		setContigId(that.getContigId());
		setContigName(that.getContigName());
		setName(that.getName());
		setFmin(that.getFmin());
		setFmax(that.getFmax());
		setStrand(that.getStrand());
		setOrganismId(that.getOrganismId());
		setNotes(that.getNotes());
		setQfeatureId(that.getQfeatureId());
		setQfeatureName(that.getQfeatureName());
		setAtcxScore(that.getAtcxScore());
		setAtcxRank(that.getAtcxRank());
		setAtcxDenserank(that.getAtcxDenserank());
		setAtdcScore(that.getAtdcScore());
		setAtdcRank(that.getAtdcRank());
		setAtdcDenserank(that.getAtdcDenserank());
		setOscxScore(that.getOscxScore());
		setOscxRank(that.getOscxRank());
		setOscxDenserank(that.getOscxDenserank());
		setOsgnScore(that.getOsgnScore());
		setOsgnRank(that.getOsgnRank());
		setOsgnDenserank(that.getOsgnDenserank());
		setOspgScore(that.getOspgScore());
		setOspgRank(that.getOspgRank());
		setOspgDenserank(that.getOspgDenserank());
		setIntnetScore(that.getIntnetScore());
		setIntnetRank(that.getIntnetRank());
		setIntnetDenserank(that.getIntnetDenserank());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("featureId=[").append(featureId).append("] ");
		buffer.append("commonName=[").append(commonName).append("] ");
		buffer.append("contigId=[").append(contigId).append("] ");
		buffer.append("contigName=[").append(contigName).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("fmin=[").append(fmin).append("] ");
		buffer.append("fmax=[").append(fmax).append("] ");
		buffer.append("strand=[").append(strand).append("] ");
		buffer.append("organismId=[").append(organismId).append("] ");
		buffer.append("notes=[").append(notes).append("] ");
		buffer.append("qfeatureId=[").append(qfeatureId).append("] ");
		buffer.append("qfeatureName=[").append(qfeatureName).append("] ");
		buffer.append("atcxScore=[").append(atcxScore).append("] ");
		buffer.append("atcxRank=[").append(atcxRank).append("] ");
		buffer.append("atcxDenserank=[").append(atcxDenserank).append("] ");
		buffer.append("atdcScore=[").append(atdcScore).append("] ");
		buffer.append("atdcRank=[").append(atdcRank).append("] ");
		buffer.append("atdcDenserank=[").append(atdcDenserank).append("] ");
		buffer.append("oscxScore=[").append(oscxScore).append("] ");
		buffer.append("oscxRank=[").append(oscxRank).append("] ");
		buffer.append("oscxDenserank=[").append(oscxDenserank).append("] ");
		buffer.append("osgnScore=[").append(osgnScore).append("] ");
		buffer.append("osgnRank=[").append(osgnRank).append("] ");
		buffer.append("osgnDenserank=[").append(osgnDenserank).append("] ");
		buffer.append("ospgScore=[").append(ospgScore).append("] ");
		buffer.append("ospgRank=[").append(ospgRank).append("] ");
		buffer.append("ospgDenserank=[").append(ospgDenserank).append("] ");
		buffer.append("intnetScore=[").append(intnetScore).append("] ");
		buffer.append("intnetRank=[").append(intnetRank).append("] ");
		buffer.append("intnetDenserank=[").append(intnetDenserank).append("] ");

		return buffer.toString();
	}

	// /**
	// */
	// @Override
	// public int hashCode() {
	// final int prime = 31;
	// int result = 1;
	// result = (int) (prime * result + ((featureId == null) ? 0 :
	// featureId.hashCode()));
	// return result;
	// }
	//
	// /**
	// */
	// public boolean equals(Object obj) {
	// if (obj == this)
	// return true;
	// if (!(obj instanceof VLocusIntxnRicenetv1))
	// return false;
	// VLocusIntxnRicenetv1 equalCheck = (VLocusIntxnRicenetv1) obj;
	// if ((featureId == null && equalCheck.featureId != null) || (featureId != null
	// && equalCheck.featureId == null))
	// return false;
	// if (featureId != null && !featureId.equals(equalCheck.featureId))
	// return false;
	// return true;
	// }

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		// result = (int) (prime * result + ((featureId == null) ? 0 :
		// featureId.hashCode()));
		result = (int) (prime * result + ((organismId == null) ? 0 : organismId.hashCode()));
		result = (int) (prime * result + ((contigId == null) ? 0 : contigId.hashCode()));
		result = (int) (prime * result + ((fmin == null) ? 0 : fmin.hashCode()));
		result = (int) (prime * result + ((fmax == null) ? 0 : fmax.hashCode()));
		return result;
	}

	@Override
	public int compareTo(Object o) {
		
		Locus l1 = (Locus) this;
		Locus l2 = (Locus) o;
		int ret = l1.getContig().compareTo(l2.getContig());
		if (ret != 0)
			return ret;
		ret = l1.getFmin().compareTo(l2.getFmin());
		if (ret != 0)
			return ret;
		ret = l1.getFmax().compareTo(l2.getFmax());
		return ret;

	}

	@Override
	public boolean equals(Object obj) {
		
		return compareTo(obj) == 0;
	}

	@Override
	public String getUniquename() {
		
		return name;
	}

	@Override
	public Long getChr() {
		
		return Long.valueOf(getContig());
	}

	@Override
	public String getContig() {
		
		return contigName;
	}

	@Override
	public String getDescription() {
		
		StringBuffer buff = new StringBuffer();
		if (intnetScore != null)
			buff.append(" Score:" + String.format("%.2f", intnetScore) + "/" + intnetRank);
		if (oscxScore != null)
			buff.append(" OS-CX:" + String.format("%.2f", oscxScore) + "/" + oscxRank);
		if (osgnScore != null)
			buff.append(" OS-GN:" + String.format("%.2f", osgnScore) + "/" + osgnRank);
		if (ospgScore != null)
			buff.append(" OS-PG:" + String.format("%.2f", ospgScore) + "/" + ospgRank);

		return buff + " " + notes;
	}

	@Override
	public String getFeatureType() {
		return null;
	}

}
