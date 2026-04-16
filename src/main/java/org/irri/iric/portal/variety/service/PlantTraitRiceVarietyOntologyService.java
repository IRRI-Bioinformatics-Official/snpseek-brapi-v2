package org.irri.iric.portal.variety.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.irri.iric.ds.chado.dao.CvTermPathDAO;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.genomics.OntologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("PlantTraitRiceVarietyOntologyService")
public class PlantTraitRiceVarietyOntologyService implements OntologyService {

	@Autowired
	// @Qualifier("VCvPhenotypeByPtocoDAO")
	@Qualifier("VCvPhenotypeByPtocoPathDAO")
	private CvTermPathDAO cvtermpathDAO;

	@Override
	public String queryAccession(String q) throws Exception {
		return null;
	}

	@Override
	public List getCVtermAncestors(String cv, String term) {
		
		cvtermpathDAO = (CvTermPathDAO) AppContext.checkBean(cvtermpathDAO, "VCvPhenotypeByPtocoPathDAO");

		return cvtermpathDAO.getAncestors(cv, term);
	}

	@Override
	public List getCVtermDescendants(String cv, String term) {
		return null;
	}

	@Override
	public List getCVtermDescendants(String cv, String term, Set dataset) {
		
		cvtermpathDAO = (CvTermPathDAO) AppContext.checkBean(cvtermpathDAO, "VCvPhenotypeByPtocoPathDAO");
		return cvtermpathDAO.getDescendants(cv, term, dataset);
	}

	@Override
	public List countLociInTerms(String organism, Collection genelist, String cv) throws Exception {
		return null;
	}

	@Override
	public String overRepresentationTest(String organism, Collection genelist, String enrichmentType) throws Exception {
		return null;
	}

}
