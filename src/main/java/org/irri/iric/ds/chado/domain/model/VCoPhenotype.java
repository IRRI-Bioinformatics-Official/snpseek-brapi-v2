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



/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVCoPhenotype", query = "select myVCoPhenotype from VCoPhenotype myVCoPhenotype order by trait"),
		@NamedQuery(name = "findMethodsVCoPhenotypeByMethodId", query = "select myVCoPhenotype from VCoPhenotype myVCoPhenotype where myVCoPhenotype.method = ?1")

})
@Table(name = "V_CO_VARIABLE_TMS")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VCoPhenotype")
public class VCoPhenotype implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 */
	@Column(name = "VARIABLE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal variableId;
	/**
	 */

	@Column(name = "VARIABLE_NAME", length = 1024)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String variable_name;

	/**
	 */
	@Column(name = "TRAIT_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal trait_id;
	/**
	 */

	@Column(name = "VARIABLE_NAME", length = 1024)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String trait;

	/**
	 */
	@Column(name = "METHOD_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal method_id;
	/**
	 */

	@Column(name = "METHOD", length = 1024)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String method;

	/**
	 */
	@Column(name = "SCALE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal scale_id;
	/**
	 */

	@Column(name = "VARIABLE_NAME", length = 1024)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String scale;

	public VCoPhenotype() {
	}

	public BigDecimal getVariableId() {
		return variableId;
	}

	public void setVariableId(BigDecimal variableId) {
		this.variableId = variableId;
	}

	public String getVariable_name() {
		return variable_name;
	}

	public void setVariable_name(String variable_name) {
		this.variable_name = variable_name;
	}

	public BigDecimal getTrait_id() {
		return trait_id;
	}

	public void setTrait_id(BigDecimal trait_id) {
		this.trait_id = trait_id;
	}

	public String getTrait() {
		return trait;
	}

	public void setTrait(String trait) {
		this.trait = trait;
	}

	public BigDecimal getMethod_id() {
		return method_id;
	}

	public void setMethod_id(BigDecimal method_id) {
		this.method_id = method_id;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public BigDecimal getScale_id() {
		return scale_id;
	}

	public void setScale_id(BigDecimal scale_id) {
		this.scale_id = scale_id;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	

}

