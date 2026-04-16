package org.irri.iric.ds.chado.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

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

import org.irri.iric.ds.chado.domain.Scaffold;



/**
 */

@Entity(name = "VScaffoldsOrganism")
@NamedQueries({
		@NamedQuery(name = "findAllVScaffoldsOrganisms", query = "select myVScaffoldsOrganism from VScaffoldsOrganism myVScaffoldsOrganism"),
		@NamedQuery(name = "findVScaffoldsOrganismByCommonName", query = "select myVScaffoldsOrganism from VScaffoldsOrganism myVScaffoldsOrganism where myVScaffoldsOrganism.commonName = ?1"),
		@NamedQuery(name = "findVScaffoldsOrganismByCommonNameContaining", query = "select myVScaffoldsOrganism from VScaffoldsOrganism myVScaffoldsOrganism where myVScaffoldsOrganism.commonName like ?1"),
		@NamedQuery(name = "findVScaffoldsOrganismByFeatureId", query = "select myVScaffoldsOrganism from VScaffoldsOrganism myVScaffoldsOrganism where myVScaffoldsOrganism.featureId = ?1"),
		@NamedQuery(name = "findVScaffoldsOrganismByName", query = "select myVScaffoldsOrganism from VScaffoldsOrganism myVScaffoldsOrganism where myVScaffoldsOrganism.name = ?1"),
		@NamedQuery(name = "findVScaffoldsOrganismByNameContaining", query = "select myVScaffoldsOrganism from VScaffoldsOrganism myVScaffoldsOrganism where myVScaffoldsOrganism.name like ?1"),
		@NamedQuery(name = "findVScaffoldsOrganismByNameCommonName", query = "select myVScaffoldsOrganism from VScaffoldsOrganism myVScaffoldsOrganism where upper(myVScaffoldsOrganism.name) = upper(?1)  and myVScaffoldsOrganism.commonName = ?2"),
		@NamedQuery(name = "findVScaffoldsOrganismByNameOrganismId", query = "select myVScaffoldsOrganism from VScaffoldsOrganism myVScaffoldsOrganism where upper(myVScaffoldsOrganism.name) = upper(?1)  and myVScaffoldsOrganism.organismId = ?2"),
		@NamedQuery(name = "findVScaffoldsOrganismByOrganismId", query = "select myVScaffoldsOrganism from VScaffoldsOrganism myVScaffoldsOrganism where myVScaffoldsOrganism.organismId = ?1 order by myVScaffoldsOrganism.seqlen desc"),
		@NamedQuery(name = "findVScaffoldsOrganismByPrimaryKey", query = "select myVScaffoldsOrganism from VScaffoldsOrganism myVScaffoldsOrganism where myVScaffoldsOrganism.featureId = ?1"),
		@NamedQuery(name = "findVScaffoldsOrganismBySeqlen", query = "select myVScaffoldsOrganism from VScaffoldsOrganism myVScaffoldsOrganism where myVScaffoldsOrganism.seqlen = ?1"),
		@NamedQuery(name = "findVScaffoldsOrganismByType", query = "select myVScaffoldsOrganism from VScaffoldsOrganism myVScaffoldsOrganism where myVScaffoldsOrganism.type = ?1"),
		@NamedQuery(name = "findVScaffoldsOrganismByTypeContaining", query = "select myVScaffoldsOrganism from VScaffoldsOrganism myVScaffoldsOrganism where myVScaffoldsOrganism.type like ?1"),
		@NamedQuery(name = "findVScaffoldsOrganismByTypeId", query = "select myVScaffoldsOrganism from VScaffoldsOrganism myVScaffoldsOrganism where myVScaffoldsOrganism.typeId = ?1"),
		@NamedQuery(name = "findVScaffoldsOrganismByUniquename", query = "select myVScaffoldsOrganism from VScaffoldsOrganism myVScaffoldsOrganism where upper(myVScaffoldsOrganism.uniquename) = upper(?1)"),
		@NamedQuery(name = "findVScaffoldsOrganismByUniquenameContaining", query = "select myVScaffoldsOrganism from VScaffoldsOrganism myVScaffoldsOrganism where upper(myVScaffoldsOrganism.uniquename) like upper(?1)") })
@Table(name = "V_SCAFFOLDS_ORGANISM")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VScaffoldsOrganism")
public class VScaffoldsOrganism implements Serializable, Scaffold {
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

	@Column(name = "NAME")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 */

	@Column(name = "UNIQUENAME", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String uniquename;
	/**
	 */

	@Column(name = "SEQLEN", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal seqlen;
	/**
	 */

	@Column(name = "ORGANISM_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal organismId;
	/**
	 */

	@Column(name = "COMMON_NAME")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String commonName;
	/**
	 */

	@Column(name = "TYPE_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal typeId;
	/**
	 */

	@Column(name = "TYPE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String type;

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
	public void setUniquename(String uniquename) {
		this.uniquename = uniquename;
	}

	/**
	 */
	public String getUniquename() {
		return this.uniquename;
	}

	/**
	 */
	public void setSeqlen(BigDecimal seqlen) {
		this.seqlen = seqlen;
	}

	/**
	 */
	public BigDecimal getSeqlen() {
		return this.seqlen;
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
	public void setType(String type) {
		this.type = type;
	}

	/**
	 */
	public String getType() {
		return this.type;
	}

	/**
	 */
	public VScaffoldsOrganism() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VScaffoldsOrganism that) {
		setFeatureId(that.getFeatureId());
		setName(that.getName());
		setUniquename(that.getUniquename());
		setSeqlen(that.getSeqlen());
		setOrganismId(that.getOrganismId());
		setCommonName(that.getCommonName());
		setTypeId(that.getTypeId());
		setType(that.getType());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("featureId=[").append(featureId).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("uniquename=[").append(uniquename).append("] ");
		buffer.append("seqlen=[").append(seqlen).append("] ");
		buffer.append("organismId=[").append(organismId).append("] ");
		buffer.append("commonName=[").append(commonName).append("] ");
		buffer.append("typeId=[").append(typeId).append("] ");
		buffer.append("type=[").append(type).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((featureId == null) ? 0 : featureId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VScaffoldsOrganism))
			return false;
		VScaffoldsOrganism equalCheck = (VScaffoldsOrganism) obj;
		if ((featureId == null && equalCheck.featureId != null) || (featureId != null && equalCheck.featureId == null))
			return false;
		if (featureId != null && !featureId.equals(equalCheck.featureId))
			return false;
		return true;
	}

	@Override
	public long getLength() {
		
		return Objects.isNull(seqlen) ? 0 : seqlen.longValue();
		//return seqlen.longValue();
	}

	

}
