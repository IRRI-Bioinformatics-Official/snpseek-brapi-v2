package org.irri.iric.ds.chado.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the user_subscriptions database table.
 * 
 */
@Entity
@Table(name="user_subscriptions")
@NamedQuery(name="UserSubscription.findAll", query="SELECT u FROM UserSubscription u")
public class UserSubscription implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_subscription_id")
	private Integer userSubscriptionId;

	@Column(name="end_date")
	private Timestamp endDate;

	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="start_date")
	private Timestamp startDate;

	//bi-directional many-to-one association to Subscription
	@ManyToOne
	@JoinColumn(name="subscription_id")
	private Subscription subscription;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public UserSubscription() {
	}

	public Integer getUserSubscriptionId() {
		return this.userSubscriptionId;
	}

	public void setUserSubscriptionId(Integer userSubscriptionId) {
		this.userSubscriptionId = userSubscriptionId;
	}

	public Timestamp getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Timestamp getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Subscription getSubscription() {
		return this.subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}