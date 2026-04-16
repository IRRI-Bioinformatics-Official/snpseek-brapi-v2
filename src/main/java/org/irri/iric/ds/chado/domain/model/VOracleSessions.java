package org.irri.iric.ds.chado.domain.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 */
@IdClass(org.irri.iric.ds.chado.domain.object.VOracleSessionsPK.class)
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVOracleSessionss", query = "select myVOracleSessions from VOracleSessions myVOracleSessions"),
		@NamedQuery(name = "findVOracleSessionsByAction", query = "select myVOracleSessions from VOracleSessions myVOracleSessions where myVOracleSessions.action = ?1"),
		@NamedQuery(name = "findVOracleSessionsByActionContaining", query = "select myVOracleSessions from VOracleSessions myVOracleSessions where myVOracleSessions.action like ?1"),
		@NamedQuery(name = "findVOracleSessionsByInstId", query = "select myVOracleSessions from VOracleSessions myVOracleSessions where myVOracleSessions.instId = ?1"),
		@NamedQuery(name = "findVOracleSessionsByPrimaryKey", query = "select myVOracleSessions from VOracleSessions myVOracleSessions where myVOracleSessions.sid = ?1 and myVOracleSessions.serial_ = ?2"),
		@NamedQuery(name = "findVOracleSessionsByProgram", query = "select myVOracleSessions from VOracleSessions myVOracleSessions where myVOracleSessions.program = ?1"),
		@NamedQuery(name = "findVOracleSessionsByProgramContaining", query = "select myVOracleSessions from VOracleSessions myVOracleSessions where myVOracleSessions.program like ?1"),
		@NamedQuery(name = "findVOracleSessionsBySerial_", query = "select myVOracleSessions from VOracleSessions myVOracleSessions where myVOracleSessions.serial_ = ?1"),
		@NamedQuery(name = "findVOracleSessionsBySid", query = "select myVOracleSessions from VOracleSessions myVOracleSessions where myVOracleSessions.sid = ?1"),
		@NamedQuery(name = "findVOracleSessionsBySpid", query = "select myVOracleSessions from VOracleSessions myVOracleSessions where myVOracleSessions.spid = ?1"),
		@NamedQuery(name = "findVOracleSessionsBySpidContaining", query = "select myVOracleSessions from VOracleSessions myVOracleSessions where myVOracleSessions.spid like ?1"),
		@NamedQuery(name = "findVOracleSessionsByUsername", query = "select myVOracleSessions from VOracleSessions myVOracleSessions where myVOracleSessions.username = ?1"),
		@NamedQuery(name = "findVOracleSessionsByUsernameContaining", query = "select myVOracleSessions from VOracleSessions myVOracleSessions where myVOracleSessions.username like ?1") })
@Table(schema = "LMANSUETO", name = "VL_ORACLE_SESSIONS")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/admin/domain", name = "VOracleSessions")
public class VOracleSessions implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "INST_ID")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer instId;
	/**
	 */

	@Column(name = "SID")
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer sid;
	/**
	 */

	@Column(name = "SERIAL#")
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer serial_;
	/**
	 */

	@Column(name = "SPID", length = 24)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String spid;
	/**
	 */

	@Column(name = "USERNAME", length = 30)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String username;
	/**
	 */

	@Column(name = "PROGRAM", length = 48)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String program;
	/**
	 */

	@Column(name = "ACTION", length = 32)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String action;

	/**
	 */
	public void setInstId(Integer instId) {
		this.instId = instId;
	}

	/**
	 */
	public Integer getInstId() {
		return this.instId;
	}

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
	public void setSpid(String spid) {
		this.spid = spid;
	}

	/**
	 */
	public String getSpid() {
		return this.spid;
	}

	/**
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 */
	public void setProgram(String program) {
		this.program = program;
	}

	/**
	 */
	public String getProgram() {
		return this.program;
	}

	/**
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 */
	public String getAction() {
		return this.action;
	}

	/**
	 */
	public VOracleSessions() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VOracleSessions that) {
		setInstId(that.getInstId());
		setSid(that.getSid());
		setSerial_(that.getSerial_());
		setSpid(that.getSpid());
		setUsername(that.getUsername());
		setProgram(that.getProgram());
		setAction(that.getAction());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("instId=[").append(instId).append("] ");
		buffer.append("sid=[").append(sid).append("] ");
		buffer.append("serial_=[").append(serial_).append("] ");
		buffer.append("spid=[").append(spid).append("] ");
		buffer.append("username=[").append(username).append("] ");
		buffer.append("program=[").append(program).append("] ");
		buffer.append("action=[").append(action).append("] ");

		return buffer.toString();
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
		if (!(obj instanceof VOracleSessions))
			return false;
		VOracleSessions equalCheck = (VOracleSessions) obj;
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
}
