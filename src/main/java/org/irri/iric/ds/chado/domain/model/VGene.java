package org.irri.iric.ds.chado.domain.model;

import java.io.Serializable;

import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.ds.chado.domain.Gene;
import org.irri.iric.ds.chado.domain.Locus;
import org.irri.iric.ds.utils.DbUtils;

/**
 */

@Entity
@NamedQueries({ @NamedQuery(name = "findAllVGenes", query = "select myVGene from VGene myVGene"),
		@NamedQuery(name = "findVGeneByChr", query = "select myVGene from VGene myVGene where myVGene.chr = ?1"),
		@NamedQuery(name = "findVGeneByFmax", query = "select myVGene from VGene myVGene where myVGene.fmax = ?1"),
		@NamedQuery(name = "findVGeneByFmin", query = "select myVGene from VGene myVGene where myVGene.fmin = ?1"),
		@NamedQuery(name = "findVGeneByGeneId", query = "select myVGene from VGene myVGene where myVGene.geneId = ?1"),
		@NamedQuery(name = "findVGeneByName", query = "select myVGene from VGene myVGene where upper(myVGene.name) = upper(?1)"),
		@NamedQuery(name = "findVGeneByNameContaining", query = "select myVGene from VGene myVGene where upper(myVGene.name) like upper(?1)"),

		@NamedQuery(name = "findVGeneByNameOrg", query = "select myVGene from VGene myVGene where upper(myVGene.name) = upper(?1) and myVGene.organismId=?2"),
		@NamedQuery(name = "findVGeneByOrg", query = "select myVGene from VGene myVGene where myVGene.organismId= ?1"),
		@NamedQuery(name = "findVGeneByNamesOrg", query = "select myVGene from VGene myVGene where myVGene.name in (?1) and myVGene.organismId=?2"),

		@NamedQuery(name = "findVGeneByPhase", query = "select myVGene from VGene myVGene where myVGene.phase = ?1"),
		@NamedQuery(name = "findVGeneByPrimaryKey", query = "select myVGene from VGene myVGene where myVGene.geneId = ?1"),
		@NamedQuery(name = "findVGeneByStrand", query = "select myVGene from VGene myVGene where myVGene.strand = ?1") })
@Table(name = "V_GENE")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VGene")
public class VGene implements Serializable, Gene, Comparable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "GENE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer geneId;
	/**
	 */

	@Column(name = "NAME", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 */

	@Column(name = "CHR", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String chr;
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

	@Column(name = "PHASE", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer phase;

	@Column(name = "ORGANISM_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer organismId;

	/**
	 */
	public void setGeneId(Integer geneId) {
		this.geneId = geneId;
	}

	/**
	 */
	public Integer getGeneId() {
		return this.geneId;
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
	public void setChr(String chr) {
		this.chr = chr;
	}

	/**
	 */
	public Long getChr() {
		return Long.valueOf(DbUtils.guessChrFromString(this.chr));
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
	public void setPhase(Integer phase) {
		this.phase = phase;
	}

	/**
	 */
	public Integer getPhase() {
		return this.phase;
	}

	public Integer getOrganismId() {
		return organismId;
	}

	public void setOrganismId(Integer organismId) {
		this.organismId = organismId;
	}

	/**
	 */
	public VGene() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VGene that) {
		setGeneId(that.getGeneId());
		setName(that.getName());
		setChr(that.getChr().toString());
		setFmin(that.getFmin());
		setFmax(that.getFmax());
		setStrand(that.getStrand());
		setPhase(that.getPhase());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("geneId=[").append(geneId).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("chr=[").append(chr).append("] ");
		buffer.append("fmin=[").append(fmin).append("] ");
		buffer.append("fmax=[").append(fmax).append("] ");
		buffer.append("strand=[").append(strand).append("] ");
		buffer.append("phase=[").append(phase).append("] ");

		return buffer.toString();
	}

	// /**
	// */
	// @Override
	// public int hashCode() {
	// final int prime = 31;
	// int result = 1;
	// result = (int) (prime * result + ((geneId == null) ? 0 : geneId.hashCode()));
	// return result;
	// }
	//
	// /**
	// */
	// public boolean equals(Object obj) {
	// if (obj == this)
	// return true;
	// if (!(obj instanceof VGene))
	// return false;
	// VGene equalCheck = (VGene) obj;
	// if ((geneId == null && equalCheck.geneId != null) || (geneId != null &&
	// equalCheck.geneId == null))
	// return false;
	// if (geneId != null && !geneId.equals(equalCheck.geneId))
	// return false;
	// return true;
	// }
	//
	//

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		// result = (int) (prime * result + ((featureId == null) ? 0 :
		// featureId.hashCode()));
		result = (int) (prime * result + ((organismId == null) ? 0 : organismId.hashCode()));
		result = (int) (prime * result + ((chr == null) ? 0 : chr.hashCode()));
		result = (int) (prime * result + ((fmin == null) ? 0 : fmin.hashCode()));
		result = (int) (prime * result + ((fmax == null) ? 0 : fmax.hashCode()));
		return result;
	}

	@Override
	public int compareTo(Object o) {

		Locus l1 = (Locus) this;
		Locus l2 = (Locus) o;
		// int ret = organismId compareTo(l2.getOrganismId());
		// if(ret!=0) return ret;
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

		return this.getName();
	}

	@Override
	public String getContig() {

		try {
			Integer chrnum = Integer.valueOf(chr);
			if (chrnum > 9)
				return "chr0" + chrnum;
			else
				return "chr" + chrnum;
		} catch (Exception ex) {
			// ex.printStackTrace();
		}
		return chr;
	}

	@Override
	public String getDescription() {
		return null;
	}

	@Override
	public String getOrganism() {

		return this.organismId.toString();
	}

	@Override
	public BigDecimal getFeatureId() {

		return BigDecimal.valueOf(this.geneId);
	}

	@Override
	public String getFeatureType() {

		return "gene";
	}

}
