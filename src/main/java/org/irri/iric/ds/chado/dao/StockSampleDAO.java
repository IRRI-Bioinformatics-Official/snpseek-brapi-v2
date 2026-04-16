package org.irri.iric.ds.chado.dao;

import java.util.Set;

import org.irri.iric.ds.chado.domain.StockSample;

public interface StockSampleDAO extends VarietyDAO {

	Set<StockSample> getSamples(Set dataset);

	Set<StockSample> getSamplesById(Set sampleid);
	
	Set<StockSample> getSamplesBySampleIdInDataset(Set dataset, Set sampleid);
	
	Set<StockSample> getSamplesByStock(Set stock, Set dataset);

	Set<StockSample> getSamplesByStock(Set stock);

	Set<StockSample> getSamplesByAccession(Set accessions, Set dataset);

	Set<StockSample> getSamplesByAccession(Set accessions);

	Set<StockSample> getSamplesByVarnames(Set names, Set dataset);

	Set<StockSample> getSamplesByVarnames(Set names);

}
