package org.irri.iric.ds.chado.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.ds.chado.dao.UserDAO;
import org.irri.iric.ds.chado.domain.model.User;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("UserDAO")
@Transactional
public class UserImpl extends AbstractJpaDao<User> implements UserDAO {

	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public boolean canBeMerged(User o) {
		return true;
	}

	@Override
	public Set<Class<?>> getTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public User save(User user) {
		if (user == null) {
			throw new IllegalArgumentException("Entity cannot be null");
		}

		user = store(user); // The store method is defined in AbstractJpaDao

		return user; // Return the saved entity
	}

	@SuppressWarnings("unchecked")
	@Transactional
	private Collection<User> findUserByUserName(String username) {
		Query query = createNamedQuery("user.findByUsername", -1, -1, username);

		return new LinkedHashSet<User>(query.getResultList());
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	private Collection<User> findUserByValidationToken(String token) {
		Query query = createNamedQuery("user.findByValidationToken", -1, -1, token);

		return new LinkedHashSet<User>(query.getResultList());
	}
	
	private Collection<? extends User> findByUserEmail(String email) {
		Query query = createNamedQuery("user.findByUserEmail", -1, -1, email);

		return new LinkedHashSet<User>(query.getResultList());
	}

	@Override
	public List<User> getByUserName(String username) {
		List<User> list = new ArrayList();

		list.addAll(findUserByUserName(username));

		return list;
	}

	@Override
	public List<User> getByValidationToken(String token) {
		List<User> list = new ArrayList();

		list.addAll(findUserByValidationToken(token));

		return list;
	}

	@Override
	public List<User> getByUserEmail(String email) {
		List<User> list = new ArrayList();

		list.addAll(findByUserEmail(email));
		
		return list;
	}

	

	

}
