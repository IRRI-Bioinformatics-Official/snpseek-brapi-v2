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

import org.irri.iric.ds.chado.domain.SnpsAllvarsPos;



/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVConvertposNb2allrefss", query = "select myVConvertposNb2allrefs from VConvertposNb2allrefs myVConvertposNb2allrefs"),
		@NamedQuery(name = "findVConvertposNb2allrefsByFromContigId", query = "select myVConvertposNb2allrefs from VConvertposNb2allrefs myVConvertposNb2allrefs where myVConvertposNb2allrefs.fromContigId = ?1"),
		@NamedQuery(name = "findVConvertposNb2allrefsByFromOrganismId", query = "select myVConvertposNb2allrefs from VConvertposNb2allrefs myVConvertposNb2allrefs where myVConvertposNb2allrefs.fromOrganismId = ?1"),
		@NamedQuery(name = "findVConvertposNb2allrefsByFromPosition", query = "select myVConvertposNb2allrefs from VConvertposNb2allrefs myVConvertposNb2allrefs where myVConvertposNb2allrefs.fromPosition = ?1"),
		
		@NamedQuery(name = "findVConvertposNb2allrefsByFromOrgIdContigIdPosBetween", query = "select myVConvertposNb2allrefs from VConvertposNb2allrefs myVConvertposNb2allrefs where myVConvertposNb2allrefs.fromOrganismId = ?1 and myVConvertposNb2allrefs.fromContigId = ?2 and myVConvertposNb2allrefs.fromPosition between ?3 and ?4 order by myVConvertposNb2allrefs.snpFeatureId"),
		
		
		@NamedQuery(name = "findVConvertposNb2allrefsByFromRefcall", query = "select myVConvertposNb2allrefs from VConvertposNb2allrefs myVConvertposNb2allrefs where myVConvertposNb2allrefs.fromRefcall = ?1"),
		@NamedQuery(name = "findVConvertposNb2allrefsByFromRefcallContaining", query = "select myVConvertposNb2allrefs from VConvertposNb2allrefs myVConvertposNb2allrefs where myVConvertposNb2allrefs.fromRefcall like ?1"),
		@NamedQuery(name = "findVConvertposNb2allrefsByPrimaryKey", query = "select myVConvertposNb2allrefs from VConvertposNb2allrefs myVConvertposNb2allrefs where myVConvertposNb2allrefs.snpFeatureId = ?1"),
		@NamedQuery(name = "findVConvertposNb2allrefsBySnpFeatureId", query = "select myVConvertposNb2allrefs from VConvertposNb2allrefs myVConvertposNb2allrefs where myVConvertposNb2allrefs.snpFeatureId = ?1") })
@Table( name = "V_CONVERTPOS_NB2ALLREFS")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/oracle/domain", name = "VConvertposNb2allrefs")
public class VConvertposNb2allrefs implements Serializable, SnpsAllvarsPos {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "SNP_FEATURE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal snpFeatureId;
	/**
	 */

	@Column(name = "FROM_ORGANISM_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal fromOrganismId;
	/**
	 */

	@Column(name = "FROM_CONTIG_ID")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal fromContigId;
	/**
	 */

	@Column(name = "FROM_POSITION")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal fromPosition;
	/**
	 */

	@Column(name = "FROM_REFCALL", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String fromRefcall;
	/**
	 */

	@Column(name = "NB_CONTIG_ID")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal nbContigId;
	/**
	 */

	@Column(name = "NB_POSITION")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal nbPosition;
	/**
	 */

	@Column(name = "NB_REFCALL", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String nbRefcall;
	/**
	 */

	@Column(name = "IR64_CONTIG_ID")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal ir64ContigId;
	/**
	 */

	@Column(name = "IR64_POSITION")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal ir64Position;
	/**
	 */

	@Column(name = "IR64_REFCALL", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String ir64Refcall;
	/**
	 */

	@Column(name = "RICE9311_CONTIG_ID")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal _311ContigId;
	/**
	 */

	@Column(name = "RICE9311_POSITION")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal _311Position;
	/**
	 */

	@Column(name = "RICE9311_REFCALL", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String _311Refcall;
	/**
	 */

	@Column(name = "DJ123_CONTIG_ID")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal dj123ContigId;
	/**
	 */

	@Column(name = "DJ123_POSITION")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal dj123Position;
	/**
	 */

	@Column(name = "DJ123_REFCALL", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String dj123Refcall;
	/**
	 */

	@Column(name = "KASALATH_CONTIG_ID")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal kasalathContigId;
	/**
	 */

	@Column(name = "KASALATH_POSITION")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal kasalathPosition;
	/**
	 */

	@Column(name = "KASALATH_REFCALL", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String kasalathRefcall;

	
	/**
	 */
	public void setSnpFeatureId(BigDecimal snpFeatureId) {
		this.snpFeatureId = snpFeatureId;
	}

	/**
	 */
	public BigDecimal getSnpFeatureId() {
		return this.snpFeatureId;
	}

	/**
	 */
	public void setFromOrganismId(BigDecimal fromOrganismId) {
		this.fromOrganismId = fromOrganismId;
	}

	/**
	 */
	public BigDecimal getFromOrganismId() {
		return this.fromOrganismId;
	}

	/**
	 */
	public void setFromContigId(BigDecimal fromContigId) {
		this.fromContigId = fromContigId;
	}

	/**
	 */
	public BigDecimal getFromContigId() {
		return this.fromContigId;
	}

	/**
	 */
	public void setFromPosition(BigDecimal fromPosition) {
		this.fromPosition = fromPosition;
	}

	/**
	 */
	public BigDecimal getFromPosition() {
		return this.fromPosition;
	}

	/**
	 */
	public void setFromRefcall(String fromRefcall) {
		this.fromRefcall = fromRefcall;
	}

	/**
	 */
	public String getFromRefcall() {
		return this.fromRefcall;
	}

	/**
	 */
	public void setNbContigId(BigDecimal nbContigId) {
		this.nbContigId = nbContigId;
	}

	/**
	 */
	public BigDecimal getNbContigId() {
		return this.nbContigId;
	}

	/**
	 */
	public void setNbPosition(BigDecimal nbPosition) {
		this.nbPosition = nbPosition;
	}

	/**
	 */
	public BigDecimal getNbPosition() {
		return this.nbPosition;
	}

	/**
	 */
	public void setNbRefcall(String nbRefcall) {
		this.nbRefcall = nbRefcall;
	}

	/**
	 */
	public String getNbRefcall() {
		return this.nbRefcall;
	}

	/**
	 */
	public void setIr64ContigId(BigDecimal ir64ContigId) {
		this.ir64ContigId = ir64ContigId;
	}

	/**
	 */
	public BigDecimal getIr64ContigId() {
		return this.ir64ContigId;
	}

	/**
	 */
	public void setIr64Position(BigDecimal ir64Position) {
		this.ir64Position = ir64Position;
	}

	/**
	 */
	public BigDecimal getIr64Position() {
		return this.ir64Position;
	}

	/**
	 */
	public void setIr64Refcall(String ir64Refcall) {
		this.ir64Refcall = ir64Refcall;
	}

	/**
	 */
	public String getIr64Refcall() {
		return this.ir64Refcall;
	}

	/**
	 */
	public void set_311ContigId(BigDecimal _311ContigId) {
		this._311ContigId = _311ContigId;
	}

	/**
	 */
	public BigDecimal get_311ContigId() {
		return this._311ContigId;
	}

	/**
	 */
	public void set_311Position(BigDecimal _311Position) {
		this._311Position = _311Position;
	}

	/**
	 */
	public BigDecimal get_311Position() {
		return this._311Position;
	}

	/**
	 */
	public void set_311Refcall(String _311Refcall) {
		this._311Refcall = _311Refcall;
	}

	/**
	 */
	public String get_311Refcall() {
		return this._311Refcall;
	}

	/**
	 */
	public void setDj123ContigId(BigDecimal dj123ContigId) {
		this.dj123ContigId = dj123ContigId;
	}

	/**
	 */
	public BigDecimal getDj123ContigId() {
		return this.dj123ContigId;
	}

	/**
	 */
	public void setDj123Position(BigDecimal dj123Position) {
		this.dj123Position = dj123Position;
	}

	/**
	 */
	public BigDecimal getDj123Position() {
		return this.dj123Position;
	}

	/**
	 */
	public void setDj123Refcall(String dj123Refcall) {
		this.dj123Refcall = dj123Refcall;
	}

	/**
	 */
	public String getDj123Refcall() {
		return this.dj123Refcall;
	}

	/**
	 */
	public void setKasalathContigId(BigDecimal kasalathContigId) {
		this.kasalathContigId = kasalathContigId;
	}

	/**
	 */
	public BigDecimal getKasalathContigId() {
		return this.kasalathContigId;
	}

	/**
	 */
	public void setKasalathPosition(BigDecimal kasalathPosition) {
		this.kasalathPosition = kasalathPosition;
	}

	/**
	 */
	public BigDecimal getKasalathPosition() {
		return this.kasalathPosition;
	}

	/**
	 */
	public void setKasalathRefcall(String kasalathRefcall) {
		this.kasalathRefcall = kasalathRefcall;
	}

	/**
	 */
	public String getKasalathRefcall() {
		return this.kasalathRefcall;
	}

	/**
	 */
	public VConvertposNb2allrefs() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VConvertposNb2allrefs that) {
		setSnpFeatureId(that.getSnpFeatureId());
		setFromOrganismId(that.getFromOrganismId());
		setFromContigId(that.getFromContigId());
		setFromPosition(that.getFromPosition());
		setFromRefcall(that.getFromRefcall());
		setNbContigId(that.getNbContigId());
		setNbPosition(that.getNbPosition());
		setNbRefcall(that.getNbRefcall());
		setIr64ContigId(that.getIr64ContigId());
		setIr64Position(that.getIr64Position());
		setIr64Refcall(that.getIr64Refcall());
		set_311ContigId(that.get_311ContigId());
		set_311Position(that.get_311Position());
		set_311Refcall(that.get_311Refcall());
		setDj123ContigId(that.getDj123ContigId());
		setDj123Position(that.getDj123Position());
		setDj123Refcall(that.getDj123Refcall());
		setKasalathContigId(that.getKasalathContigId());
		setKasalathPosition(that.getKasalathPosition());
		setKasalathRefcall(that.getKasalathRefcall());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("snpFeatureId=[").append(snpFeatureId).append("] ");
		buffer.append("fromOrganismId=[").append(fromOrganismId).append("] ");
		buffer.append("fromContigId=[").append(fromContigId).append("] ");
		buffer.append("fromPosition=[").append(fromPosition).append("] ");
		buffer.append("fromRefcall=[").append(fromRefcall).append("] ");
		buffer.append("nbContigId=[").append(nbContigId).append("] ");
		buffer.append("nbPosition=[").append(nbPosition).append("] ");
		buffer.append("nbRefcall=[").append(nbRefcall).append("] ");
		buffer.append("ir64ContigId=[").append(ir64ContigId).append("] ");
		buffer.append("ir64Position=[").append(ir64Position).append("] ");
		buffer.append("ir64Refcall=[").append(ir64Refcall).append("] ");
		buffer.append("_311ContigId=[").append(_311ContigId).append("] ");
		buffer.append("_311Position=[").append(_311Position).append("] ");
		buffer.append("_311Refcall=[").append(_311Refcall).append("] ");
		buffer.append("dj123ContigId=[").append(dj123ContigId).append("] ");
		buffer.append("dj123Position=[").append(dj123Position).append("] ");
		buffer.append("dj123Refcall=[").append(dj123Refcall).append("] ");
		buffer.append("kasalathContigId=[").append(kasalathContigId).append("] ");
		buffer.append("kasalathPosition=[").append(kasalathPosition).append("] ");
		buffer.append("kasalathRefcall=[").append(kasalathRefcall).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((snpFeatureId == null) ? 0 : snpFeatureId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VConvertposNb2allrefs))
			return false;
		VConvertposNb2allrefs equalCheck = (VConvertposNb2allrefs) obj;
		if ((snpFeatureId == null && equalCheck.snpFeatureId != null) || (snpFeatureId != null && equalCheck.snpFeatureId == null))
			return false;
		if (snpFeatureId != null && !snpFeatureId.equals(equalCheck.snpFeatureId))
			return false;
		return true;
	}

	@Override
	public BigDecimal getPosition() {
		return null;
	}


	@Override
	public Long getChr() {
		return null;
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}



	@Override
	public String getRefnuc() {
		return null;
	}

	@Override
	public void setRefnuc(String refnuc) {
		
	}

	@Override
	public String getContig() {
		return null;
	}

	@Override
	public BigDecimal getAlleleIndex() {
		return null;
	}

	@Override
	public void setAltnuc(String altnuc) {
		
	}

	@Override
	public String getAltnuc() {
		return null;
	}
	
	
}
