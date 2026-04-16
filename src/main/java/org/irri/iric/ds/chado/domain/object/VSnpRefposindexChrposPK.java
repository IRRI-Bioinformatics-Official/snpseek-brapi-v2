package org.irri.iric.ds.chado.domain.object;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;

/**
 */
public class VSnpRefposindexChrposPK implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */
	public VSnpRefposindexChrposPK() {
	}

	/**
	 */

	@Column(name = "CHROMOSOME", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal chromosome;
	/**
	 */

	@Column(name = "POSITION", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal position;

	/**
	 */
	public void setChromosome(BigDecimal chromosome) {
		this.chromosome = chromosome;
	}

	/**
	 */
	public BigDecimal getChromosome() {
		return this.chromosome;
	}

	/**
	 */
	public void setPosition(BigDecimal position) {
		this.position = position;
	}

	/**
	 */
	public BigDecimal getPosition() {
		return this.position;
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((chromosome == null) ? 0 : chromosome.hashCode()));
		result = (int) (prime * result + ((position == null) ? 0 : position.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {

		if (obj == this)
			return true;
		if (!(obj instanceof VSnpRefposindexChrposPK))
			return false;
		VSnpRefposindexChrposPK equalCheck = (VSnpRefposindexChrposPK) obj;
		if ((chromosome == null && equalCheck.chromosome != null)
				|| (chromosome != null && equalCheck.chromosome == null))
			return false;
		if (chromosome != null && !chromosome.equals(equalCheck.chromosome))
			return false;
		if ((position == null && equalCheck.position != null) || (position != null && equalCheck.position == null))
			return false;
		if (position != null && !position.equals(equalCheck.position))
			return false;
		return true;

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("VSnpRefposindexPK");
		sb.append(" chromosome: ").append(getChromosome());
		sb.append(" position: ").append(getPosition());
		return sb.toString();
	}
}
