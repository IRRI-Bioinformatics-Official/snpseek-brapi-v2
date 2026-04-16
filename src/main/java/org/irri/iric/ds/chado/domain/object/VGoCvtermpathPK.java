package org.irri.iric.ds.chado.domain.object;

import java.io.Serializable;

import javax.persistence.Id;

import javax.persistence.*;

/**
 */
public class VGoCvtermpathPK implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */
	public VGoCvtermpathPK() {
	}

	/**
	 */

	@Column(name = "SUBJ_ACC", length = 1020, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public String subjAcc;
	/**
	 */

	@Column(name = "OBJ_ACC", length = 1020, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public String objAcc;

	/**
	 */
	public void setSubjAcc(String subjAcc) {
		this.subjAcc = subjAcc;
	}

	/**
	 */
	public String getSubjAcc() {
		return this.subjAcc;
	}

	/**
	 */
	public void setObjAcc(String objAcc) {
		this.objAcc = objAcc;
	}

	/**
	 */
	public String getObjAcc() {
		return this.objAcc;
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((subjAcc == null) ? 0 : subjAcc.hashCode()));
		result = (int) (prime * result + ((objAcc == null) ? 0 : objAcc.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VGoCvtermpathPK))
			return false;
		VGoCvtermpathPK equalCheck = (VGoCvtermpathPK) obj;
		if ((subjAcc == null && equalCheck.subjAcc != null) || (subjAcc != null && equalCheck.subjAcc == null))
			return false;
		if (subjAcc != null && !subjAcc.equals(equalCheck.subjAcc))
			return false;
		if ((objAcc == null && equalCheck.objAcc != null) || (objAcc != null && equalCheck.objAcc == null))
			return false;
		if (objAcc != null && !objAcc.equals(equalCheck.objAcc))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("VGoCvtermpathPK");
		sb.append(" subjAcc: ").append(getSubjAcc());
		sb.append(" objAcc: ").append(getObjAcc());
		return sb.toString();
	}
}
