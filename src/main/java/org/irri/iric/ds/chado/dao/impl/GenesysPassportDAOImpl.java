package org.irri.iric.ds.chado.dao.impl;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.dao.IricstockPassportDAO;
import org.irri.iric.ds.chado.domain.Passport;
import org.irri.iric.ds.utils.GenesysAPI;
import org.springframework.stereotype.Repository;

@Repository("GenesysPassportDAO")
public class GenesysPassportDAOImpl implements IricstockPassportDAO {

	@Override
	public Set getPassportByStockId(BigDecimal id) throws Exception {
		return null;
	}

	@Override
	public Set getPassportByAccession(String acc) throws Exception {
		return GenesysAPI.getInstance(Passport.class, true).getPassportByAccession(acc);
	}

}
