package org.irri.iric.ds.chado.domain.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name = "users")
@NamedQueries({ 
	@NamedQuery(name = "user.findAll", query = "SELECT u FROM User u"),
	@NamedQuery(name = "user.findByUsername", query = "SELECT u FROM User u where u.username = ?1"),
	@NamedQuery(name = "user.findByValidationToken", query = "SELECT u FROM User u where u.validationtoken = ?1"),
	@NamedQuery(name = "user.findByUserEmail", query = "SELECT u FROM User u where u.email = ?1")
})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "created_at")
	private Timestamp createdAt;

	private String email;

	private String firstname;

	private String lastname;

	@Column(name = "password_hash")
	private String passwordHash;

	@Column(name = "updated_at")
	private Timestamp updatedAt;
	
	private Timestamp tokenvalidity;

	private String username;

	private Boolean validated;

	private String validationtoken;

	// bi-directional many-to-one association to UserSubscription
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SELECT)
	private List<UserSubscription> userSubscriptions;

	public User() {
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPasswordHash() {
		return this.passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getValidated() {
		return this.validated;
	}

	public void setValidated(Boolean validated) {
		this.validated = validated;
	}
	
	

	public Timestamp getTokenvalidity() {
		return tokenvalidity;
	}

	public void setTokenvalidity(Timestamp tokenvalidity) {
		this.tokenvalidity = tokenvalidity;
	}

	public String getValidationtoken() {
		return this.validationtoken;
	}

	public void setValidationtoken(String validationtoken) {
		this.validationtoken = validationtoken;
	}

	public List<UserSubscription> getUserSubscriptions() {
		return this.userSubscriptions;
	}

	public void setUserSubscriptions(List<UserSubscription> userSubscriptions) {
		this.userSubscriptions = userSubscriptions;
	}

	public UserSubscription addUserSubscription(UserSubscription userSubscription) {
		if (getUserSubscriptions() == null)
			setUserSubscriptions(new ArrayList<>());
			
		getUserSubscriptions().add(userSubscription);
		userSubscription.setUser(this);

		return userSubscription;
	}

	public UserSubscription removeUserSubscription(UserSubscription userSubscription) {
		getUserSubscriptions().remove(userSubscription);
		userSubscription.setUser(null);

		return userSubscription;
	}

}