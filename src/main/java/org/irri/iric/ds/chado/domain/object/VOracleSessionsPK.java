package org.irri.iric.ds.chado.domain.object;

import java.io.Serializable;

import javax.persistence.Id;

import javax.persistence.*;

/**
 */
public class VOracleSessionsPK implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */
	public VOracleSessionsPK() {
	}

	/**
	 */

	@Column(name = "SID")
	@Basic(fetch = FetchType.EAGER)
	@Id
	public Integer sid;
	/**
	 */

	@Column(name = "SERIAL#")
	@Basic(fetch = FetchType.EAGER)
	@Id
	public Integer serial_;

	/**
	 */
	public void setSid(Integer sid) {
		this.sid = sid;
	}

	/**
	 */
	public Integer getSid() {
		return this.sid;
	}

	/**
	 */
	public void setSerial_(Integer serial_) {
		this.serial_ = serial_;
	}

	/**
	 */
	public Integer getSerial_() {
		return this.serial_;
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((sid == null) ? 0 : sid.hashCode()));
		result = (int) (prime * result + ((serial_ == null) ? 0 : serial_.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VOracleSessionsPK))
			return false;
		VOracleSessionsPK equalCheck = (VOracleSessionsPK) obj;
		if ((sid == null && equalCheck.sid != null) || (sid != null && equalCheck.sid == null))
			return false;
		if (sid != null && !sid.equals(equalCheck.sid))
			return false;
		if ((serial_ == null && equalCheck.serial_ != null) || (serial_ != null && equalCheck.serial_ == null))
			return false;
		if (serial_ != null && !serial_.equals(equalCheck.serial_))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("VOracleSessionsPK");
		sb.append(" sid: ").append(getSid());
		sb.append(" serial_: ").append(getSerial_());
		return sb.toString();
	}
}
