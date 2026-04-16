package org.irri.iric.ds.chado.dao;

import java.util.List;

import org.irri.iric.ds.chado.domain.model.User;

public interface UserDAO {

	public User save(User user);
	
	public List<User> getByUserName(String username);

	public List<User> getByValidationToken(String token);
	
	public List<User> getByUserEmail(String email);
	
	
}
