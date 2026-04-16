package org.irri.iric.ds.chado.domain.model;

import java.io.Serializable;

import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

/**
 */

@Entity(name = "Organism")
@NamedQueries({ @NamedQuery(name = "findAllOrganisms", query = "select myOrganism from Organism myOrganism"),
		@NamedQuery(name = "findOrganismByAbbreviation", query = "select myOrganism from Organism myOrganism where myOrganism.abbreviation = ?1"),
		@NamedQuery(name = "findOrganismByAbbreviationContaining", query = "select myOrganism from Organism myOrganism where myOrganism.abbreviation like ?1"),
		@NamedQuery(name = "findOrganismByCommonName", query = "select myOrganism from Organism myOrganism where myOrganism.commonName = ?1"),
		@NamedQuery(name = "findOrganismByCommonNameContaining", query = "select myOrganism from Organism myOrganism where myOrganism.commonName like ?1"),
		@NamedQuery(name = "findOrganismByGenus", query = "select myOrganism from Organism myOrganism where myOrganism.genus = ?1"),
		@NamedQuery(name = "findOrganismByGenusContaining", query = "select myOrganism from Organism myOrganism where myOrganism.genus like ?1"),
		@NamedQuery(name = "findOrganismByOrganismId", query = "select myOrganism from Organism myOrganism where myOrganism.organismId = ?1"),
		@NamedQuery(name = "findOrganismByPrimaryKey", query = "select myOrganism from Organism myOrganism where myOrganism.organismId = ?1"),
		@NamedQuery(name = "findOrganismBySpecies", query = "select myOrganism from Organism myOrganism where myOrganism.species = ?1"),
		@NamedQuery(name = "findOrganismBySpeciesContaining", query = "select myOrganism from Organism myOrganism where myOrganism.species like ?1") })
@Table(name = "V_ORGANISM")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "Organism")
public class Organism implements Serializable, org.irri.iric.ds.chado.domain.Organism {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "ORGANISM_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal organismId;
	/**
	 */

	@Column(name = "ABBREVIATION")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String abbreviation;
	/**
	 */

	@Column(name = "GENUS")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String genus;
	/**
	 */

	@Column(name = "SPECIES")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String species;
	/**
	 */

	@Column(name = "COMMON_NAME")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String commonName;
	/**
	 */

	@Column(name = "COMMENT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	byte[] comment;

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
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	/**
	 */
	public String getAbbreviation() {
		return this.abbreviation;
	}

	/**
	 */
	public void setGenus(String genus) {
		this.genus = genus;
	}

	/**
	 */
	public String getGenus() {
		return this.genus;
	}

	/**
	 */
	public void setSpecies(String species) {
		this.species = species;
	}

	/**
	 */
	public String getSpecies() {
		return this.species;
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
	public void setComment(byte[] comment) {
		this.comment = comment;
	}

	/**
	 */
	public byte[] getComment() {
		return this.comment;
	}

	/**
	 */
	public Organism() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(Organism that) {
		setOrganismId(that.getOrganismId());
		setAbbreviation(that.getAbbreviation());
		setGenus(that.getGenus());
		setSpecies(that.getSpecies());
		setCommonName(that.getCommonName());
		setComment(that.getComment());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("organismId=[").append(organismId).append("] ");
		buffer.append("abbreviation=[").append(abbreviation).append("] ");
		buffer.append("genus=[").append(genus).append("] ");
		buffer.append("species=[").append(species).append("] ");
		buffer.append("commonName=[").append(commonName).append("] ");
		buffer.append("comment=[").append(comment).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((organismId == null) ? 0 : organismId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Organism))
			return false;
		Organism equalCheck = (Organism) obj;
		if ((organismId == null && equalCheck.organismId != null)
				|| (organismId != null && equalCheck.organismId == null))
			return false;
		if (organismId != null && !organismId.equals(equalCheck.organismId))
			return false;
		return true;
	}

	@Override
	public String getName() {
		
		return this.getCommonName();
		// return this.getAbbreviation();
	}

}
