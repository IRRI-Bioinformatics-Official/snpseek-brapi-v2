package org.irri.iric.ds.chado.dao;

import java.util.List;

import org.irri.iric.ds.chado.domain.GWASRun;
import org.irri.iric.ds.chado.domain.ManhattanPlot;

public interface ManhattanPlotDAO {

	public List<ManhattanPlot> getMinusPValues(GWASRun run);

	public List<ManhattanPlot> getMinusPValues(GWASRun run, Double minlogP, String region);

}
