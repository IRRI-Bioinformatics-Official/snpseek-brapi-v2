package org.irri.iric.ds.chado.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.ds.chado.dao.SubscriptionDAO;
import org.irri.iric.ds.chado.dao.UserDAO;
import org.irri.iric.ds.chado.dao.UserSubscriptionDAO;
import org.irri.iric.ds.chado.domain.model.Subscription;
import org.irri.iric.ds.chado.domain.model.User;
import org.irri.iric.ds.chado.domain.model.UserSubscription;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("UserSubscriptionDAO")
@Transactional
public class UserSubscriptionImpl extends AbstractJpaDao<UserSubscription> implements UserSubscriptionDAO {

	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public boolean canBeMerged(UserSubscription o) {
		return false;
	}

	@Override
	public Set<Class<?>> getTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public UserSubscription save(UserSubscription subscription) {
		if (subscription == null) {
			throw new IllegalArgumentException("Entity cannot be null");
		}

		subscription = store(subscription); // The store method is defined in AbstractJpaDao

		return subscription; // Return the saved entity
	}

}
