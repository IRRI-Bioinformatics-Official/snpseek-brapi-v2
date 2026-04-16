
package org.irri.iric.ds.chado.domain.model;

import java.io.Serializable;

import java.lang.StringBuilder;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVSnpseekSettingss", query = "select myVSnpseekSettings from VSnpseekSettings myVSnpseekSettings"),
		@NamedQuery(name = "findVSnpseekSettingsByName", query = "select myVSnpseekSettings from VSnpseekSettings myVSnpseekSettings where myVSnpseekSettings.name = ?1"),
		@NamedQuery(name = "findVSnpseekSettingsByNameContaining", query = "select myVSnpseekSettings from VSnpseekSettings myVSnpseekSettings where myVSnpseekSettings.name like ?1"),
		@NamedQuery(name = "findVSnpseekSettingsByPrimaryKey", query = "select myVSnpseekSettings from VSnpseekSettings myVSnpseekSettings where myVSnpseekSettings.name = ?1"),
		@NamedQuery(name = "findVSnpseekSettingsByValue", query = "select myVSnpseekSettings from VSnpseekSettings myVSnpseekSettings where myVSnpseekSettings.value = ?1"),
		@NamedQuery(name = "findVSnpseekSettingsByValueContaining", query = "select myVSnpseekSettings from VSnpseekSettings myVSnpseekSettings where myVSnpseekSettings.value like ?1") })

@Table(schema = "public", name = "v_snpseek_settings")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/oracle/domain", name = "VSnpseekSettings")

public class VSnpseekSettings implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "name")
	@Basic(fetch = FetchType.EAGER)

	@Id
	@XmlElement
	String name;
	/**
	 */

	@Column(name = "value")
	@Basic(fetch = FetchType.EAGER)

	@XmlElement
	String value;

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
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 */
	public VSnpseekSettings() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VSnpseekSettings that) {
		setName(that.getName());
		setValue(that.getValue());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("name=[").append(name).append("] ");
		buffer.append("value=[").append(value).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((name == null) ? 0 : name.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VSnpseekSettings))
			return false;
		VSnpseekSettings equalCheck = (VSnpseekSettings) obj;
		if ((name == null && equalCheck.name != null) || (name != null && equalCheck.name == null))
			return false;
		if (name != null && !name.equals(equalCheck.name))
			return false;
		return true;
	}
}
