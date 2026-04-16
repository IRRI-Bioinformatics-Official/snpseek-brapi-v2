package org.irri.iric.ds.chado.dao;

import java.util.List;

import org.irri.iric.ds.chado.domain.model.Subscription;

public interface SubscriptionDAO {

	public Subscription save(Subscription subscription);
	
	public List<Subscription> getByShortName(String shortName);

	
	
	
}
