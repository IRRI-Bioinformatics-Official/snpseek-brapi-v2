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

import org.irri.iric.ds.AppContext;
import org.irri.iric.ds.chado.domain.SnpsAllvarsMultirefsPos;
import org.irri.iric.ds.chado.domain.SnpsAllvarsPos;
import org.irri.iric.ds.utils.DbUtils;




/**
 */

@Entity
@NamedQueries({

		@NamedQuery(name = "findMVConvertposAny2allrefsByFromOrgIdContigIdPosBetween", query = "select myVConvertposAny2allrefs from VConvertposAny2allrefsMV myVConvertposAny2allrefs where myVConvertposAny2allrefs.fromOrganismId = ?1 and myVConvertposAny2allrefs.fromContigId = ?2 and myVConvertposAny2allrefs.fromPosition between ?3 and ?4 order by  myVConvertposAny2allrefs.fromContigId, myVConvertposAny2allrefs.fromPosition"),

})
@Table(name = "MV_CONVERTPOS_ANY2ALLREFS")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/oracle/domain", name = "VConvertposAny2allrefsMV")
public class VConvertposAny2allrefsMV implements Serializable, SnpsAllvarsMultirefsPos {
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
	BigDecimal rice9311ContigId;
	/**
	 */

	@Column(name = "RICE9311_POSITION")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal rice9311Position;
	/**
	 */

	@Column(name = "RICE9311_REFCALL", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String rice9311Refcall;
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

	/*
	 * @Column(name = "ORGANISM_ID")
	 * 
	 * @Basic(fetch = FetchType.EAGER)
	 * 
	 * @XmlElement BigDecimal organismId;
	 */
	/**
	 */

	@Column(name = "TYPE_ID")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal typeId;
	/**
	 */

	@Column(name = "ALLELE_INDEX")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal alleleIndex;

	@Column(name = "NB_CONTIG_NAME")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String nbContigName;

	@Column(name = "RICE9311_CONTIG_NAME")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String _311ContigName;

	@Column(name = "IR64_CONTIG_NAME")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String ir64ContigName;

	@Column(name = "DJ123_CONTIG_NAME")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String dj123ContigName;

	@Column(name = "KASALATH_CONTIG_NAME")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String kasalathContigName;

	@Column(name = "NB_ALIGN_COUNT")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer nbAlignCount;

	@Column(name = "RICE9311_ALIGN_COUNT")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer _311AlignCount;

	@Column(name = "IR64_ALIGN_COUNT")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer ir64AlignCount;

	@Column(name = "DJ123_ALIGN_COUNT")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer dj123AlignCount;

	@Column(name = "KASALATH_ALIGN_COUNT")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer kasalathAlignCount;

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
	public void setRice9311ContigId(BigDecimal rice9311ContigId) {
		this.rice9311ContigId = rice9311ContigId;
	}

	/**
	 */
	public BigDecimal getRice9311ContigId() {
		return this.rice9311ContigId;
	}

	/**
	 */
	public void setRice9311Position(BigDecimal rice9311Position) {
		this.rice9311Position = rice9311Position;
	}

	/**
	 */
	public BigDecimal getRice9311Position() {
		return this.rice9311Position;
	}

	/**
	 */
	public void setRice9311Refcall(String rice9311Refcall) {
		this.rice9311Refcall = rice9311Refcall;
	}

	/**
	 */
	public String getRice9311Refcall() {
		return this.rice9311Refcall;
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

	// /**
	// */
	// public void setOrganismId(BigDecimal organismId) {
	// this.organismId = organismId;
	// }
	//
	// /**
	// */
	// public BigDecimal getOrganismId() {
	// return this.organismId;
	// }

	/**
	 */
	public void setTypeId(BigDecimal typeId) {
		this.typeId = typeId;
	}

	/**
	 */
	public BigDecimal getTypeId() {
		return this.typeId;
	}

	/**
	 */
	public void setAlleleIndex(BigDecimal alleleIndex) {
		this.alleleIndex = alleleIndex;
	}

	/**
	 */
	public BigDecimal getAlleleIndex() {
		return this.alleleIndex;
	}

	/**
	 */
	public VConvertposAny2allrefsMV() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VConvertposAny2allrefsMV that) {
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
		setRice9311ContigId(that.getRice9311ContigId());
		setRice9311Position(that.getRice9311Position());
		setRice9311Refcall(that.getRice9311Refcall());
		setDj123ContigId(that.getDj123ContigId());
		setDj123Position(that.getDj123Position());
		setDj123Refcall(that.getDj123Refcall());
		setKasalathContigId(that.getKasalathContigId());
		setKasalathPosition(that.getKasalathPosition());
		setKasalathRefcall(that.getKasalathRefcall());
		// setOrganismId(that.getOrganismId());
		setTypeId(that.getTypeId());
		setAlleleIndex(that.getAlleleIndex());
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
		buffer.append("rice9311ContigId=[").append(rice9311ContigId).append("] ");
		buffer.append("rice9311Position=[").append(rice9311Position).append("] ");
		buffer.append("rice9311Refcall=[").append(rice9311Refcall).append("] ");
		buffer.append("dj123ContigId=[").append(dj123ContigId).append("] ");
		buffer.append("dj123Position=[").append(dj123Position).append("] ");
		buffer.append("dj123Refcall=[").append(dj123Refcall).append("] ");
		buffer.append("kasalathContigId=[").append(kasalathContigId).append("] ");
		buffer.append("kasalathPosition=[").append(kasalathPosition).append("] ");
		buffer.append("kasalathRefcall=[").append(kasalathRefcall).append("] ");
		// buffer.append("organismId=[").append(organismId).append("] ");
		buffer.append("typeId=[").append(typeId).append("] ");
		buffer.append("alleleIndex=[").append(alleleIndex).append("] ");

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

		return compareTo(obj) == 0;
		/*
		 * if (obj == this) return true; if (!(obj instanceof VConvertposAny2allrefs))
		 * return false; VConvertposAny2allrefs equalCheck = (VConvertposAny2allrefs)
		 * obj; if ((snpFeatureId == null && equalCheck.snpFeatureId != null) ||
		 * (snpFeatureId != null && equalCheck.snpFeatureId == null)) return false; if
		 * (snpFeatureId != null && !snpFeatureId.equals(equalCheck.snpFeatureId))
		 * return false; return true;
		 */
	}

	public String getNBContigName() {
		/*
		 * if(this.nbContigName!=null) { if(nbContigName.length()==4) return
		 * this.nbContigName.replace("Chr","chr0"); else return
		 * this.nbContigName.replace("Chr","chr"); }
		 */
		return this.nbContigName;
	}

	public String get9311ContigName() {
		return this._311ContigName;
	}

	public String getIR64ContigName() {
		return this.ir64ContigName;
	}

	public String getDJ123ContigName() {
		return this.dj123ContigName;
	}

	public String getKasalathContigName() {
		return this.kasalathContigName;
	}

	@Override
	public String getRefnuc() {

		return this.fromRefcall;
	}

	@Override
	public void setRefnuc(String refnuc) {

		this.fromRefcall = refnuc;
	}

	@Override
	public String getContig() {

		String ctgname = "";
		if (fromOrganismId.equals(AppContext.REFERENCE_NIPPONBARE_ID()))
			ctgname = this.nbContigName;
		else if (fromOrganismId.equals(Organism.REFERENCE_9311_ID))
			ctgname = this._311ContigName;
		else if (fromOrganismId.equals(Organism.REFERENCE_IR64_ID))
			ctgname = this.ir64ContigName;
		else if (fromOrganismId.equals(Organism.REFERENCE_DJ123_ID))
			ctgname = this.dj123ContigName;
		else if (fromOrganismId.equals(Organism.REFERENCE_KASALATH_ID))
			ctgname = this.kasalathContigName;
		// if(ctgname==null) ctgname="";
		return ctgname;
	}

	@Override
	public String getContigName(String organism) {

		String ctgname = "";
		if (organism.equals(AppContext.REFERENCE_NIPPONBARE()))
			ctgname = getNBContigName();
		else if (organism.equals(Organism.REFERENCE_9311))
			ctgname = get9311ContigName();
		else if (organism.equals(Organism.REFERENCE_IR64))
			ctgname = getIR64ContigName();
		else if (organism.equals(Organism.REFERENCE_DJ123))
			ctgname = getDJ123ContigName();
		else if (organism.equals(Organism.REFERENCE_KASALATH))
			ctgname = getKasalathContigName();
		if (ctgname == null)
			ctgname = "";
		return ctgname;

	}

	@Override
	public BigDecimal getPosition() {

		return this.fromPosition;
	}

	@Override
	public BigDecimal getPosition(String organism) {

		if (organism.equals(AppContext.REFERENCE_NIPPONBARE()))
			return this.nbPosition;
		else if (organism.equals(Organism.REFERENCE_9311))
			return this.rice9311Position;
		else if (organism.equals(Organism.REFERENCE_IR64))
			return this.ir64Position;
		else if (organism.equals(Organism.REFERENCE_DJ123))
			return this.dj123Position;
		else if (organism.equals(Organism.REFERENCE_KASALATH))
			return this.kasalathPosition;
		return null;
	}

	@Override
	public Integer getAlignCount(String organism) {

		if (organism.equals(AppContext.REFERENCE_NIPPONBARE()))
			return this.nbAlignCount;
		else if (organism.equals(Organism.REFERENCE_9311))
			return this._311AlignCount;
		else if (organism.equals(Organism.REFERENCE_IR64))
			return this.ir64AlignCount;
		else if (organism.equals(Organism.REFERENCE_DJ123))
			return this.dj123AlignCount;
		else if (organism.equals(Organism.REFERENCE_KASALATH))
			return this.kasalathAlignCount;
		return null;
	}

	@Override
	public String getAllele(String organism) {

		if (organism.equals(AppContext.REFERENCE_NIPPONBARE()))
			return this.nbRefcall;
		else if (organism.equals(Organism.REFERENCE_9311))
			return this.rice9311Refcall;
		else if (organism.equals(Organism.REFERENCE_IR64))
			return this.ir64Refcall;
		else if (organism.equals(Organism.REFERENCE_DJ123))
			return this.dj123Refcall;
		else if (organism.equals(Organism.REFERENCE_KASALATH))
			return this.kasalathRefcall;
		return null;
	}

	@Override
	public Long getChr() {

		if (fromOrganismId.equals(AppContext.REFERENCE_NIPPONBARE_ID()))
			return Long.valueOf(DbUtils.guessChrFromString(this.nbContigName));
		else if (fromOrganismId.equals(Organism.REFERENCE_KASALATH_ID))
			return Long.valueOf(DbUtils.guessChrFromString(this.kasalathContigName));
		else if (fromOrganismId.equals(Organism.REFERENCE_KASALATH_ID)) {
			try {
				return Long.valueOf(DbUtils.guessChrFromString(this.kasalathContigName.replace("9311_", "")));
			} catch (Exception ex) {

			}
		}
		return null;
	}

	@Override
	public int compareTo(Object o) {

		// VConvertposAny2allrefs obj=(VConvertposAny2allrefs)o;
		int ret = 0;
		if (o instanceof VConvertposAny2allrefsMV) {
			VConvertposAny2allrefsMV obj = (VConvertposAny2allrefsMV) o;
			ret = this.fromContigId.compareTo(obj.fromContigId);
			if (ret == 0)
				ret = this.fromPosition.compareTo(obj.fromPosition);
		} else {
			SnpsAllvarsPos obj = (SnpsAllvarsPos) o;
			ret = this.getContig().compareTo(obj.getContig());
			if (ret == 0)
				ret = this.getPosition().compareTo(obj.getPosition());
		}
		return ret;
	}

	@Override
	public BigDecimal getOrganism() {

		return this.fromOrganismId;
	}

	@Override
	public BigDecimal getFileId() {

		return this.typeId;
	}

	@Override
	public void setAltnuc(String altnuc) {

	}

	public String getNbContigName() {
		return nbContigName;
	}

	public void setNbContigName(String nbContigName) {
		this.nbContigName = nbContigName;
	}

	public String get_311ContigName() {
		return _311ContigName;
	}

	public void set_311ContigName(String _311ContigName) {
		this._311ContigName = _311ContigName;
	}

	public String getIr64ContigName() {
		return ir64ContigName;
	}

	public void setIr64ContigName(String ir64ContigName) {
		this.ir64ContigName = ir64ContigName;
	}

	public String getDj123ContigName() {
		return dj123ContigName;
	}

	public void setDj123ContigName(String dj123ContigName) {
		this.dj123ContigName = dj123ContigName;
	}

	public void setKasalathContigName(String kasalathContigName) {
		this.kasalathContigName = kasalathContigName;
	}

	@Override
	public String getAltnuc() {
		return null;
	}

}
