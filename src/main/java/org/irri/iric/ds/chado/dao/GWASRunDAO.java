package org.irri.iric.ds.chado.dao;

import java.util.List;

import org.irri.iric.ds.chado.domain.GWASRun;

public interface GWASRunDAO {

	List<GWASRun> getGWASRuns();

	GWASRun getGWASRunByTrait(String trait);

	GWASRun getGWASRunByCoterm(String coterm);

	GWASRun getGWASRunByCodefinition(String codefinition);
}
