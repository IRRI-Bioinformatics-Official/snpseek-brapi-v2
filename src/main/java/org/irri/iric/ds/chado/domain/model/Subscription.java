package org.irri.iric.ds.chado.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the subscriptions database table.
 * 
 */
@Entity
@Table(name="subscriptions")
@NamedQueries({
	@NamedQuery(name="subscription.findAll", query="SELECT s FROM Subscription s"),
	@NamedQuery(name="subscription.findByShortName", query="SELECT s FROM Subscription s where s.shortname = ?1")
})

public class Subscription implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="subscription_id")
	private Integer subscriptionId;

	@Column(name="created_at")
	private Timestamp createdAt;

	private String description;

	@Column(name="duration_days")
	private Integer durationDays;

	private String name;

	private BigDecimal price;

	private String shortname;

	//bi-directional many-to-one association to UserSubscription
	@OneToMany(mappedBy="subscription")
	private List<UserSubscription> userSubscriptions;

	public Subscription() {
	}

	public Integer getSubscriptionId() {
		return this.subscriptionId;
	}

	public void setSubscriptionId(Integer subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDurationDays() {
		return this.durationDays;
	}

	public void setDurationDays(Integer durationDays) {
		this.durationDays = durationDays;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getShortname() {
		return this.shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public List<UserSubscription> getUserSubscriptions() {
		return this.userSubscriptions;
	}

	public void setUserSubscriptions(List<UserSubscription> userSubscriptions) {
		this.userSubscriptions = userSubscriptions;
	}

	public UserSubscription addUserSubscription(UserSubscription userSubscription) {
		getUserSubscriptions().add(userSubscription);
		userSubscription.setSubscription(this);

		return userSubscription;
	}

	public UserSubscription removeUserSubscription(UserSubscription userSubscription) {
		getUserSubscriptions().remove(userSubscription);
		userSubscription.setSubscription(null);

		return userSubscription;
	}

}