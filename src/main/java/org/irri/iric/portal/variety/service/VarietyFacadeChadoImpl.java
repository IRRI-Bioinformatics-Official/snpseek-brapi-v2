package org.irri.iric.portal.variety.service;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.irri.iric.ds.chado.dao.CvTermDAO;
import org.irri.iric.ds.chado.dao.CvTermUniqueValuesDAO;
import org.irri.iric.ds.chado.dao.IricstockPassportDAO;
import org.irri.iric.ds.chado.dao.PhenotypeDAO;
import org.irri.iric.ds.chado.dao.VarietyByPassportDAO;
import org.irri.iric.ds.chado.dao.VarietyByPhenotypeDAO;
import org.irri.iric.ds.chado.dao.VarietyDAO;
import org.irri.iric.ds.chado.domain.Phenotype;
import org.irri.iric.ds.chado.domain.StockByPhenotype;
import org.irri.iric.ds.chado.domain.StockSample;
import org.irri.iric.ds.chado.domain.Variety;
import org.irri.iric.ds.chado.domain.VarietyDistance;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.admin.WorkspaceFacade;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.irri.iric.portal.variety.VarietyFacade;
import org.irri.iric.portal.variety.VarietyPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zkoss.zul.Messagebox;

@Service("VarietyFacade")
public class VarietyFacadeChadoImpl implements VarietyFacade {

	@Override
	public Set getGermplasm(String dataset) {
		Set s = new HashSet();
		s.add(dataset);
		return getGermplasm(s);
	}

	@Override
	public List getIRGCVarietyNames() {
		return listitemsDAO.getIRGCVarietyNames();
	}

	@Override
	public List getIRGCAccessions() {
		return listitemsDAO.getIRGCAccessions();
	}

	@Override
	public List<Variety> getGermplasmByName(String var1, Set dataset) {
		Set sn = new HashSet();
		sn.add(var1);
		return varietydao.findVarietyByNames(sn, dataset);
	}

	@Override
	public Variety getGermplasmsByAccession(String varname, Set dataset) {

		// Set s=new HashSet(); s.add(dataset);
		return varietydao.findVarietyByAccession(varname, dataset); // .getGermplasmsByAccession(varname, dataset) ;
	}

	@Override
	public Map<String, BigDecimal> getPassportDefinitions(Set dataset) {
		return listitemsDAO.getPassportDefinitions(dataset);
	}

	@Override
	public Map<String, BigDecimal> getPtocoDefinitions(Set dataset) {
		return listitemsDAO.getPtocoDefinitions(dataset);
	}

	@Override
	public List getVarietyAccessions(Set dataset) {
		return listitemsDAO.getAccessions(dataset);
	}

	@Override
	public List getIrisIds(Set dataset) {
		return listitemsDAO.getIrisIds(dataset);
	}

	@Override
	public List getCountries(Set dataset) {
		return listitemsDAO.getCountries(dataset);
	}

	@Override
	public Variety getGermplasmByIrisId(String name, Set dataset) {
		return varietydao.findVarietyByIrisId(name, dataset);
	}

	@Override
	public Collection getGermplasmsByNameAccession(String varname, String accession, Set dataset) {
		return varietydao.findVarietiesByNameAccession(varname, accession, dataset);
	}

	@Override
	public double[][] constructMDSPlot(List<BigDecimal> ids, String scale, boolean isAll, Set dataset) {

		double[][] xy = null;
		Iterator<String> dsIterator = dataset.iterator();

		while (dsIterator.hasNext()) {
			String ds = dsIterator.next();
			if (ds.equals("3k"))
				xy = constructMDSPlot(ids, scale, isAll, -1);
			else if (ds.equals("hdra"))
				xy = constructMDSPlotFromHDRA(ids);
		}

		return xy;
	}

	@Override
	public String[] constructPhylotree(String varids, String scale, Object topN, Object requestid, Set dataset) {
		return constructPhylotree(varids, scale, topN, (String) requestid, dataset);
	}

	@Override
	public Map<BigDecimal, Object> getPhenotypeValues(String sPhenotype, Set dataset) {
		Map m = new HashMap();
		Iterator it = dataset.iterator();
		while (it.hasNext()) {
			m.putAll(getPhenotypeValues(sPhenotype, (String) it.next()));
		}
		return m;
	}

	@Override
	public Variety getGermplasmByAccession(String accession, Set dataset) {
		return varietydao.findVarietyByAccession(accession, dataset);
	}

	@Override
	public Map<String, Variety> getMapVarname2Variety(Set dataset) {
		return listitemsDAO.getMapVarname2Variety(dataset);
	}

	@Override
	public Map<String, StockSample> getMapAssay2Sample(Set dataset) {
		return listitemsDAO.getMapAssay2Sample(dataset);
	}

	@Override
	public Collection getGermplasmsByName(String varname, Set dataset) {
		// Set s=new HashSet(); s.add(dataset);
		Set sn = new HashSet();
		sn.add(varname);
		return varietydao.findVarietyByNames(sn, dataset); // .getGermplasmsByName(varname, dataset) ;
	}

	@Override
	public Map getMapId2Variety(Set dataset) {
		return listitemsDAO.getMapId2Variety(dataset);
	}

	@Override
	public Map getMapId2Sample(Set dataset) {
		return listitemsDAO.getMapId2Sample(dataset);
	}

	@Override
	public List getSubpopulations(Set dataset) {
		return listitemsDAO.getSubpopulations(dataset);
	}

	@Override
	public List getAccessions(Set dataset) {
		return listitemsDAO.getAccessions(dataset);
	}

	@Override
	public Map getPhenotypeDefinitions(Set dataset) {
		return listitemsDAO.getPhenotypeDefinitions(dataset);
	}

	@Override
	public Map getUIforDataset(Set dataset) {
		return null;
	}

	@Override
	public Set getGermplasmBySubpopulation(String subpopulation, Set dataset) {
		Set s = new HashSet();
		s.add(dataset);
		return varietydao.findAllVarietyBySubpopulation(subpopulation, dataset); // .getGermplasmBySubpopulation(subpopulation,
																					// s);
	}

	private static final Log log = LogFactory.getLog(VarietyFacadeChadoImpl.class);

	// Variety query DAOs

	// @Autowired
	// @Qualifier("VarietyBasicprop2DAO")
	// @Qualifier("VarietyDAO")
	// private VarietyDAO germ2dao;

	@Autowired
	@Qualifier("WorkspaceFacade")
	private WorkspaceFacade workspace;

	@Autowired
	@Qualifier("VarietyDAO")
	private VarietyDAO varietydao;

	@Autowired
	// @Qualifier("VIricstocksByPassportDAO")
	private VarietyByPassportDAO varbypassportdao;

	@Autowired
	@Qualifier("VIricstocksByPhenotypeDAO")
	private VarietyByPhenotypeDAO varbyphenotypedao;

	@Autowired
	@Qualifier("VIricstocksByPtocoDAO")
	private VarietyByPhenotypeDAO varbyptocodao;

	@Autowired
	private PlantTraitRiceVarietyOntologyService traitvarietyservice;

	// Passport DAOs
	// @Autowired
	// @Qualifier("VCvPassportDAOPostges")
	// private CvTermDAO cvtermsPassportdao;
	// private VCvPassportDAO cvtermsPassportdao;

	@Autowired
	@Qualifier("IricstockPassportDAO")
	private IricstockPassportDAO passportdao;
	@Autowired
	@Qualifier("GenesysPassportDAO")
	private IricstockPassportDAO passportdaogenesys;

	// @Autowired
	// @Qualifier("VCvPassportValuesDAOPostges")
	// @Qualifier("VIricstockPassportValuesDAOPostges")
	private CvTermUniqueValuesDAO cvpassportValuesDao;

	// Phenotype DAOs
	@Autowired
	private PhenotypeDAO phendao;

	// @Autowired
	// @Qualifier("VCvPhenotypeDAO")
	// @Qualifier("VCvPhenotypeDAOPostges")
	private CvTermDAO cvtermsPhenotypedao;

	// @Autowired
	// @Qualifier("VCvPhenotypeQualValuesDAO")
	// @Qualifier("VIricstockPhenotypeQualvalDAOPostges")
	private CvTermUniqueValuesDAO cvphenotypeQualValuesDao;

	// @Autowired
	// @Qualifier("VCvPhenotypeQuanValuesDAO")
	// @Qualifier("VIricstockPhenotypeQuanvalDAOPostges")
	private CvTermUniqueValuesDAO cvphenotypeQuanValuesDao;

	// phylogenetic tree construction daos
	// @Autowired
	// private VarietyDistanceDAO dist3kdao;

	// User interface Listboxes values DAO
	@Autowired
	@Qualifier("ListItems")
	private ListItemsDAO listitemsDAO;

	@Autowired
	private VarietyPropertiesService varpropservice;

	protected Map<String, Set<String>> mapPop2Trait;

	protected Map<String, Set<String>> mapPop2Coterm;

	// Class methods
	// Methods with @Override annotation are implementations
	// defined and documented in the Interface

	public VarietyFacadeChadoImpl() {
		super();
		listitemsDAO = (ListItemsDAO) AppContext.checkBean(listitemsDAO, "ListItems");
		AppContext.debug("VarietyFacadeChadoImpl created");
	}

	// Variety instance query methods
	@Override
	public Map getIrisId2Variety() {
		return listitemsDAO.getIrisId2Variety();
	}

	@Override
	public Variety getGermplasmByIrisId(String name, String dataset) {
		Set s = new HashSet();
		s.add(dataset);
		return varietydao.findVarietyByIrisId(name, s); // .findVarietyByName(name)
														// listitemsDAO.getGermplasmByIrisId(name, s);
	}
	/*
	 * @Override public Variety getGermplasmByNameLike(String name) { return
	 * listitemsDAO.getGermplasmByNameLike(name);
	 * 
	 * }
	 * 
	 * 
	 * @Override public Variety getGermplasmByName(String name) { return
	 * listitemsDAO.getGermplasmByName(name);
	 * 
	 * }
	 */
	/*
	 * @Override public java.util.Set getGermplasmByCountry(String country, String
	 * dataset) { return listitemsDAO.getGermplasmByCountry(country, dataset); }
	 * 
	 */
	/*
	 * @Override public java.util.Set getGermplasmBySubpopulation(String
	 * subpopulation) { return
	 * listitemsDAO.getGermplasmBySubpopulation(subpopulation); }
	 * 
	 * @Override public Set getGermplasm() { 
	 * return listitemsDAO.getGermplasm(); }
	 * 
	 * @Override public Set getGermplasmByExample(Variety germplasm) { // TODO
	 * Auto-generated method stub return
	 * listitemsDAO.getGermplasmByExample(germplasm); }
	 */

	@Override
	public Collection<? extends Variety> getGermplasmByExample(Variety example, Set dataset) {
		return listitemsDAO.getGermplasmByExample(example, dataset);
	}

	@Override
	public java.util.Map<String, Variety> getMapVarname2Variety(String dataset) {
		return listitemsDAO.getMapVarname2Variety(dataset);
	}

	@Override
	public Map<BigDecimal, Variety> getMapId2Variety(String dataset) {
		listitemsDAO = (ListItemsDAO) AppContext.checkBean(listitemsDAO, "ListItems");
		return listitemsDAO.getMapId2Variety(dataset);
	}

	@Override
	public Variety getGermplasmById(BigDecimal id, String dataset) {
		// listitemsDAO = (ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItems");
		// return listitemsDAO.getMapId2Variety(dataset).get(id);
		Set s = new HashSet();
		s.add(dataset);
		return varietydao.findVarietyById(id, s);

	}

	@Override
	public List<Variety> getGermplasmByNamesWithSpace(Collection names, Set dataset) {
		// Set s=new HashSet(); s.add(dataset);
		return varietydao.findVarietyByNamesWithSpace(names, dataset);
	}
	
	@Override
	public List<Variety> getGermplasmByNames(Collection names, Set dataset) {
		// Set s=new HashSet(); s.add(dataset);
		return varietydao.findVarietyByNames(names, dataset);
	}

	@Override
	public List<Variety> getGermplasmByNamesLike(Collection names, String dataset) {
		Set s = new HashSet();
		s.add(dataset);
		return varietydao.findVarietyByNamesLike(names, s);
	}

	@Override
	public List<Variety> getGermplasmByIrisIds(Collection names, Set dataset) {
		// Set s=new HashSet(); s.add(dataset);
		return varietydao.findVarietyByIrisIds(names, dataset);
	}

	@Override
	public List getVarietyByPassport(String definition, Set dataset, String value) {
		varbypassportdao = (VarietyByPassportDAO) AppContext.checkBean(varbypassportdao, "VIricstocksByPassportDAO");
		listitemsDAO = (ListItemsDAO) AppContext.checkBean(listitemsDAO, "ListItems");
		return varbypassportdao.findVarietyByPassportEquals(
				(BigDecimal) listitemsDAO.getPassportDefinitions(dataset).get(definition), dataset, value);
	}

	@Override
	public List getVarietyByPhenotype(String definition, Set dataset, String comparator, String value,
			int phenotype_type) {

		varbyphenotypedao = (VarietyByPhenotypeDAO) AppContext.checkBean(varbyphenotypedao,
				"VIricstocksByPhenotypeDAO");
		listitemsDAO = (ListItemsDAO) AppContext.checkBean(listitemsDAO, "ListItems");

		if (phenotype_type == ListItemsDAO.PHENOTYPETYPE_QUAN) {

			if (comparator.equals(COMPARATOR_EQUALS)) {
				return varbyphenotypedao.findVarietyByQuanPhenotypeEquals(
						(BigDecimal) listitemsDAO.getPhenotypeDefinitions(dataset).get(definition), dataset,
						Double.valueOf(value));
			} else if (comparator.equals(COMPARATOR_LESSTHAN)) {
				return varbyphenotypedao.findVarietyByQuanPhenotypeLessThan(
						(BigDecimal) listitemsDAO.getPhenotypeDefinitions(dataset).get(definition), dataset,
						Double.valueOf(value));
			} else if (comparator.equals(COMPARATOR_GREATERTHAN)) {
				return varbyphenotypedao.findVarietyByQuanPhenotypeGreaterThan(
						(BigDecimal) listitemsDAO.getPhenotypeDefinitions(dataset).get(definition), dataset,
						Double.valueOf(value));
			}

		} else if (phenotype_type == ListItemsDAO.PHENOTYPETYPE_QUAL) {
			// return
			// varbyphenotypedao.findVarietyByQualPhenotypeEquals(listitemsDAO.getPhenotypeDefinitions(dataset).get(definition),
			// dataset, value);
			if (comparator.equals(COMPARATOR_EQUALS)) {
				return varbyphenotypedao.findVarietyByQualPhenotypeEquals(
						(BigDecimal) listitemsDAO.getPhenotypeDefinitions(dataset).get(definition), dataset, value);
			} else if (comparator.equals(COMPARATOR_LESSTHAN)) {
				return varbyphenotypedao.findVarietyByQualPhenotypeLessThan(
						(BigDecimal) listitemsDAO.getPhenotypeDefinitions(dataset).get(definition), dataset, value);
			} else if (comparator.equals(COMPARATOR_GREATERTHAN)) {
				return varbyphenotypedao.findVarietyByQualPhenotypeGreaterThan(
						(BigDecimal) listitemsDAO.getPhenotypeDefinitions(dataset).get(definition), dataset, value);
			}
		}

		return null;
	}

	@Override
	public List getPhenotypeByPtoco(String cv, String term, Set dataset) {
		traitvarietyservice = (PlantTraitRiceVarietyOntologyService) AppContext.checkBean(traitvarietyservice,
				"PlantTraitRiceVarietyOntologyService");
		return traitvarietyservice.getCVtermDescendants(cv, term, dataset);
	}

	@Override
	public List getVarietyByPhenotype(String phenId, String dataset) {
		Set s = new HashSet();
		s.add(dataset);
		return getVarietyByPhenotype(phenId, s);
	}

	@Override
	public List getVarietyByPhenotype(String phenId, Set dataset) {
		varbyphenotypedao = (VarietyByPhenotypeDAO) AppContext.checkBean(varbyphenotypedao,
				"VIricstocksByPhenotypeDAO");
		listitemsDAO = (ListItemsDAO) AppContext.checkBean(listitemsDAO, "ListItems");

		BigDecimal bdId = null;
		try {
			bdId = BigDecimal.valueOf(Long.valueOf(phenId));
		} catch (Exception ex) {
			bdId = (BigDecimal) listitemsDAO.getPhenotypeDefinitions(dataset).get(phenId);
		}

		return varbyphenotypedao.findVarietyByPhenotype(bdId, dataset);

	}

	
	@Override
	public java.util.List getCountries(String dataset) {
		return listitemsDAO.getCountries(dataset);
	}

	/*
	 * @Override public java.util.List getSubpopulations() { return
	 * listitemsDAO.getSubpopulations(); }
	 */

	// Passport query methods

	@Override
	public Set getPassportByVarietyid(BigDecimal id) {
		// return passportdao.findVIricstockPassportByIricStockId( );
		try {
			return passportdao.getPassportByStockId(id);
		} catch(Exception ex) {
			ex.printStackTrace();
			Messagebox.show(ex.getMessage());
		}
		return null;
	}

	@Override
	public Set getPassportByAccession(String acc) {
		// return passportdao.findVIricstockPassportByIricStockId( );
		try {
			passportdaogenesys=(IricstockPassportDAO)AppContext.checkBean(passportdaogenesys , "GenesysPassportDAO");
			return passportdaogenesys.getPassportByAccession(acc);
		} catch(Exception ex) {
		ex.printStackTrace();
		Messagebox.show(ex.getMessage());
		}
		return null;
	}

	@Override
	public Map<String, BigDecimal> getPassportDefinitions(String dataset) {
		try {
			listitemsDAO = (ListItemsDAO) AppContext.checkBean(listitemsDAO, "ListItems");
			return listitemsDAO.getPassportDefinitions(dataset);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new HashMap();

	}

	@Override
	public Set getPassportUniqueValues(String definition, Set dataset) {

		cvpassportValuesDao = (CvTermUniqueValuesDAO) AppContext.checkBean(cvpassportValuesDao, "VCvPassportValuesDAO");
		listitemsDAO = (ListItemsDAO) AppContext.checkBean(listitemsDAO, "ListItems");
		return cvpassportValuesDao
				.getUniqueValues((BigDecimal) listitemsDAO.getPassportDefinitions(dataset).get(definition), dataset);

	}

	// Phenotypes query methods

	@Override
	public List getPhenotypesByGermplasm(Variety var, Set dataset) {
		return phendao.findPhenotypesByVariety(var, dataset);
	}

	@Override
	public Phenotype getPhenotypesByGermplasm(Variety var, String dataset, String phenId) {
		return phendao.findPhenotypesByVariety(var, dataset, phenId);

	}

	@Override
	public List getPhenotypesByGermplasm(String phenId, String dataset) {
		return phendao.findPhenotypesByVariety(phenId, dataset);
	}

	@Override
	public List getPhenotypesByGermplasmName(String name) {
		return phendao.findPhenotypesByVarietyNameLike(name);
	}

	@Override
	public Map<String, BigDecimal> getPhenotypeDefinitions(String dataset) {
		// return new HashMap();
		try {
			listitemsDAO = (ListItemsDAO) AppContext.checkBean(listitemsDAO, "ListItems");
			return listitemsDAO.getPhenotypeDefinitions(dataset);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new HashMap();
	}

	@Override
	public Map<String, BigDecimal> getPtocoDefinitions(String dataset) {
		try {
			listitemsDAO = (ListItemsDAO) AppContext.checkBean(listitemsDAO, "ListItems");
			return listitemsDAO.getPtocoDefinitions(dataset);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new HashMap();

	}

	/*
	 * @Override public Object[] getPhenotypeUniqueValues(String definition, Set
	 * dataset) {  cvphenotypeQuanValuesDao =
	 * (CvTermUniqueValuesDAO)AppContext.checkBean(cvphenotypeQuanValuesDao,
	 * "VCvPhenotypeQuanValuesDAO"); listitemsDAO =
	 * (ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItems");
	 * Map<String,BigDecimal> phenotypeDefinitions =
	 * listitemsDAO.getPhenotypeDefinitions(dataset); Set values =
	 * cvphenotypeQuanValuesDao.getUniqueValues(phenotypeDefinitions.get(definition)
	 * , dataset); //System.out.println( definition + "  =>  " +
	 * phenotypeDefinitions.get(definition) + "   values=" + values.size() + " : " +
	 * values);
	 * 
	 * int phenotype_type=ListItemsDAO.PHENOTYPETYPE_QUAN;
	 * 
	 * if(values.size()==1 && values.iterator().next()==null ) {
	 * 
	 * cvphenotypeQualValuesDao =
	 * (CvTermUniqueValuesDAO)AppContext.checkBean(cvphenotypeQualValuesDao,
	 * "VCvPhenotypeQualValuesDAO"); values =
	 * cvphenotypeQualValuesDao.getUniqueValues(phenotypeDefinitions.get(definition)
	 * , dataset); //System.out.println( definition + "  =>  " +
	 * phenotypeDefinitions.get(definition) + "   values=" + values.size() + " : " +
	 * values); phenotype_type=ListItemsDAO.PHENOTYPETYPE_QUAL; }
	 * 
	 * return new Object[] {values, phenotype_type}; }
	 */
	@Override
	public Object[] getPhenotypeUniqueValues(String definition, Set dataset) {
		cvphenotypeQuanValuesDao = (CvTermUniqueValuesDAO) AppContext.checkBean(cvphenotypeQuanValuesDao,
				"VCvPhenotypeQuanValuesDAO");
		listitemsDAO = (ListItemsDAO) AppContext.checkBean(listitemsDAO, "ListItems");

		Map<String, BigDecimal> phenotypeDefinitions = listitemsDAO.getPhenotypeDefinitions(dataset);
		Set values = cvphenotypeQuanValuesDao.getUniqueValues(phenotypeDefinitions.get(definition), dataset);
		// System.out.println( definition + " => " +
		// phenotypeDefinitions.get(definition) + " values=" + values.size() + " : " +
		// values);

		int phenotype_type = ListItemsDAO.PHENOTYPETYPE_QUAN;

		if (values.size() == 1 && values.iterator().next() == null) {

			cvphenotypeQualValuesDao = (CvTermUniqueValuesDAO) AppContext.checkBean(cvphenotypeQualValuesDao,
					"VCvPhenotypeQualValuesDAO");
			values = cvphenotypeQualValuesDao.getUniqueValues(phenotypeDefinitions.get(definition), dataset);
			// System.out.println( definition + " => " +
			// phenotypeDefinitions.get(definition) + " values=" + values.size() + " : " +
			// values);
			phenotype_type = ListItemsDAO.PHENOTYPETYPE_QUAL;
		}

		return new Object[] { values, phenotype_type };
	}

	@Override
	public Map<BigDecimal, Object> getPhenotypeValues(String phenotype, String dataset) {
		Map mapVarid2Value = new HashMap();
		AppContext.debug("get phenotype values for " + phenotype + ", " + dataset);

		if (phenotype.contains("::") && phenotype.startsWith("CO_320")) {
			phenotype=phenotype.split("::")[1];
		}
		AppContext.debug("get phenotype values for final " + phenotype + ", " + dataset);
		
		if (phenotype.contains("::") && !phenotype.startsWith("CO_320")) {
			workspace = (WorkspaceFacade) AppContext.checkBean(workspace, "WorkspaceFacade");
			mapVarid2Value.putAll(workspace.getVarietylistPhenotypeValues(phenotype, dataset));
		} else {

			/*
			 * Iterator<VIricstocksByPhenotype> itVars = getVarietyByPhenotype(phenotype,
			 * dataset).iterator(); while(itVars.hasNext()) { VIricstocksByPhenotype
			 * varval=itVars.next(); mapVarid2Value.put( varval.getIricStockId() ,
			 * varval.getValue()); }
			 */
			Iterator<StockByPhenotype> itVars = getVarietyByPhenotype(phenotype, dataset).iterator();
			while (itVars.hasNext()) {
				StockByPhenotype varval = itVars.next();
				mapVarid2Value.put(varval.getStockId(), varval.getValue());
			}

		}
		AppContext.debug("getPhenotypeValues mapVarid2Value=" + mapVarid2Value.size());
		return mapVarid2Value;
	}

	// Phylogenetic tree construction methods
	@Override
	public String[] constructPhylotree(String varids, String scale, String requestid, String dataset) {
		return constructPhylotree(varids, scale, null, requestid, dataset);
	}

	@Override
	public String[] constructPhylotree(String varids, String scale, String topN, String requestid, String dataset) {

		Set setids = new java.util.TreeSet<BigDecimal>();
		if (varids.equals("all")) {
			setids.addAll(getMapId2Variety(dataset).keySet());
			if (topN != null)
				return constructPhylotree(setids, scale, true, Integer.parseInt(topN), requestid, dataset);
			else
				return constructPhylotree(setids, scale, true, requestid, dataset);
		} else {
			String[] ids = varids.split(",");
			for (int i = 0; i < ids.length; i++)
				setids.add(BigDecimal.valueOf(Long.parseLong(ids[i])));
			if (topN != null)
				return constructPhylotree(setids, scale, false, Integer.parseInt(topN), requestid, dataset);
			else
				return constructPhylotree(setids, scale, false, requestid, dataset);
		}
	}

	@Override
	public String[] constructPhylotree(Set<BigDecimal> germplasms, String scale, String requestid, String dataset) {
		return constructPhylotree(germplasms, scale, false, requestid, dataset);
	}

	private String[] constructPhylotree(Set<BigDecimal> germplasms, String scale, boolean isAll, String requestid,
			String dataset) {
		return constructPhylotree(germplasms, scale, isAll, -1, requestid, dataset);
	}

	/**
	 * Construct phylogenetic tree
	 * 
	 * @param germplasms
	 *            set of germplams (iric_stock_id) to include
	 * @param scale
	 *            adjust distances by this factor
	 * @param isAll
	 *            if true, all varieties are included, germplasms is ignored
	 * @param topN
	 *            consider only the topN distances to construct the tree, only
	 *            varieties in these pairs are included, -1 to include alll pairs
	 * @param requestid
	 *            requestid from the user interface, use to identify the database
	 *            query
	 * @return Newick format of tree
	 */
	private String[] constructPhylotree(Set<BigDecimal> germplasms, String scale, boolean isAll, int topN,
			String requestid, String dataset) {

		// TODO
		return null;
		// if(isAll)
		// // use a precomputed newick tree using all varieties on the core set
		// return new String[] {constructPhylotreeFromCoreNewick(), ""};
		//
		//
		// dist3kdao = (VarietyDistanceDAO)AppContext.checkBean(dist3kdao,
		// "VarietyDistanceDAO");
		// dist3kdao.setRequestId(requestid);
		//
		// // first clean up memory
		// MemoryMXBean bean = ManagementFactory.getMemoryMXBean();
		// AppContext.debug("heap space used MB:" +
		// bean.getHeapMemoryUsage().getUsed()*1.0/1000000 );
		// bean.gc();
		// AppContext.debug("GC successful: heap space used MB:" +
		// bean.getHeapMemoryUsage().getUsed()*1.0/1000000 );
		//
		//
		// AppContext.debug("constructPhylotree: " + germplasms.size() + ", " + scale +
		// " " + isAll + " " + topN );
		//
		//
		// // construct distance matrix
		// BasicSymmetricalDistanceMatrix symdistmatrix = new
		// BasicSymmetricalDistanceMatrix(germplasms.size());
		//
		// java.util.Iterator<BigDecimal> itgerm=germplasms.iterator();
		// int i=0;
		// java.util.Map<BigDecimal, Integer> mapVarid2Row = new
		// java.util.HashMap<BigDecimal, Integer>();
		//
		// // setup column/row identifiers
		// Map<BigDecimal,Variety> mapId2Variety = getMapId2Variety(dataset);
		// while(itgerm.hasNext()) {
		// BigDecimal c = itgerm.next();
		// symdistmatrix.setIdentifier( i, "varid_" +
		// mapId2Variety.get(c).getVarietyId() );
		// mapVarid2Row.put(c , i);
		// i++;
		// }
		//
		// // get list of distances
		// List<VarietyDistance> listdist;
		// if(isAll)
		// if(topN>0)
		// listdist = dist3kdao.findAllVarietiesTopN(topN);
		// else
		// listdist = dist3kdao.findAllVarieties();
		// else
		// listdist = dist3kdao.findVarieties(germplasms);
		//
		//
		// if(topN>0) {
		//
		// // if only the topN distance pairs are considered,
		// // get the new set of distances where only varieties in these top pairs are
		// included
		//
		// AppContext.debug("ORIG:" + listdist.size() + " distances; varieties " +
		// germplasms.size());
		// germplasms=new HashSet();
		// java.util.Iterator<VarietyDistance> itdist = listdist.iterator();
		// while(itdist.hasNext())
		// {
		// VarietyDistance dist3k = itdist.next();
		// germplasms.add(dist3k.getVar1());
		// germplasms.add(dist3k.getVar2());
		// }
		// listdist = dist3kdao.findVarieties(germplasms);
		//
		// // setup column/row identifiers in new distance matrix
		// symdistmatrix = new BasicSymmetricalDistanceMatrix(germplasms.size());
		// mapVarid2Row = new java.util.HashMap<BigDecimal, Integer>();
		// itgerm=germplasms.iterator();
		// i=0;
		// while(itgerm.hasNext()) {
		// BigDecimal c = itgerm.next();
		// symdistmatrix.setIdentifier( i, "varid_" +
		// mapId2Variety.get(c).getVarietyId() );
		// mapVarid2Row.put(c , i);
		// i++;
		// }
		//
		// AppContext.debug("After topN:" + listdist.size() + " distances; varieties " +
		// germplasms.size());
		// }
		//
		// dist3kdao.setRequestId(null);
		//
		// // construct the distance matrix
		// java.util.Iterator<VarietyDistance> itdist = listdist.iterator();
		// int distscale = Integer.parseInt(scale);
		// while(itdist.hasNext())
		// {
		//
		// VarietyDistance dist3k = itdist.next();
		// BigDecimal var1 = dist3k.getVar1();
		// BigDecimal var2 = dist3k.getVar2();
		//
		// if(!mapVarid2Row.containsKey(var1) ) continue ; //throw new
		// RuntimeException("No key " + dist3k.getVar1() + " in mapVarid2Row");
		// if(!mapVarid2Row.containsKey(var2) ) continue ; //throw new
		// RuntimeException("No key " + dist3k.getVar2() + " in mapVarid2Row");
		//
		// Double dist = dist3k.getDist().doubleValue()*distscale;
		//
		// symdistmatrix.setValue( mapVarid2Row.get(var1 ) , mapVarid2Row.get(var2) ,
		// dist );
		// symdistmatrix.setValue( mapVarid2Row.get(var2) , mapVarid2Row.get(var1), dist
		// );
		// }
		//
		// itdist = null;
		// listdist = null;
		//
		//
		// // clean memory again, for tree construction
		// bean.gc();
		//
		// try {
		//
		// TreeConstructor tree = new TreeConstructor(symdistmatrix,
		// org.biojava3.phylo.TreeType.NJ ,
		// org.biojava3.phylo.TreeConstructionAlgorithm.PID ,
		// // null);
		// new org.biojava3.phylo.ProgessListenerStub());
		// tree.process();
		//
		// AppContext.debug("Tree construction process done");
		//
		// String newick = tree.getNewickString(false, true);
		//
		// Phylogeny phy = tree.getP();
		// PhylogenyWriter w = new PhylogenyWriter();
		// String phyloxml = w.toPhyloXML(phy, 1).toString();
		//
		// Iterator<BigDecimal> itgerm2 = germplasms.iterator();
		// while(itgerm2.hasNext()) {
		// BigDecimal c = itgerm2.next();
		//
		// Variety var = mapId2Variety.get(c);
		//
		// String subpop = "";
		// if( var.getSubpopulation()!=null) subpop = var.getSubpopulation();
		// //.replace("/","_").replace(", ","_");
		//
		// String irisid = "";
		// if( var.getIrisId()!=null) irisid=var.getIrisId();
		//
		// // remove invalid characters for newick format
		// newick = newick.replace("varid_" + c + ":",(var.getName().split("::")[0] +
		// "|" + irisid + "|" + subpop).replace(" ", "_").replace("'","").replace("(",
		// "").replace(")", "").replace("\"", "") + ":" );
		//
		// phyloxml = phyloxml.replace(">varid_" + c + "<", ">" +
		// (var.getName().split("::")[0] + "|" + irisid + "|" + subpop).replace(" ",
		// "_").replace("'","").replace("(", "").replace(")", "").replace("\"", "") +
		// "<" );
		// }
		//
		// //AppContext.debug(newick);
		// return new String[]{ newick, phyloxml};
		//
		// } catch(Exception ex)
		// {
		// ex.printStackTrace();
		// }
		// return null;
	}

	// get newick for all varieties, using core snp distances
	private String constructPhylotreeFromCoreNewick() {

		// germ2dao = (VarietyDAO)AppContext.checkBean(germ2dao, "VarietyDAO");
		//
		// // get newick
		// String newick= Data.get3kCoreNewick();
		// Iterator<Variety> itVars = germ2dao.findAllVariety().iterator();
		//
		// // rename the varieties using NAME/IRISID/SUBPOPULATION
		// while(itVars.hasNext()) {
		// Variety var = itVars.next();
		//
		// String subpop = "";
		// if( var.getSubpopulation()!=null) subpop = var.getSubpopulation();
		// //.replace("/","_").replace(", ","_");
		// String irisid = "";
		// if( var.getIrisId()!=null) irisid=var.getIrisId();
		//
		// String boxcode = var.getIrisId();
		// if(boxcode==null) boxcode= var.getBoxCode();
		// else boxcode=boxcode.replace("IRIS ", "IRIS_");
		// newick = newick.replace(boxcode + ":"
		// ,(var.getName().split("::")[0].replace(", ","_") + "|" + irisid + "|" +
		// subpop).replace(" ", "_").replace("'","").replace("(", "").replace(")",
		// "").replace("\"", "") + ":" );
		// }
		// return newick;
		return null;
	}

	@Override
	public double[][] constructMDSPlot(String varids, String scale, boolean isAll, String dataset) {

		List setids = new java.util.ArrayList<BigDecimal>();
		String[] ids = varids.split(",");
		for (int i = 0; i < ids.length; i++)
			setids.add(BigDecimal.valueOf(Long.parseLong(ids[i])));
		return constructMDSPlot(setids, scale, isAll, dataset);
	}

	@Override
	public double[][] constructMDSPlot(List<BigDecimal> germplasms, String scale, boolean isAll, String dataset) {
		if (dataset.equals("3k"))
			return constructMDSPlot(germplasms, scale, isAll, -1);
		else if (dataset.equals("hdra"))
			return constructMDSPlotFromHDRA(germplasms);
		return null;

	}

	/**
	 * Calculate MDS plot xy coordinates
	 * 
	 * @param germplasms
	 *            List of germplasms, ordering is important because the caller will
	 *            use it to plot
	 * @param scale
	 *            adjust distances with this factor
	 * @param isAll
	 *            Include all varieties?
	 * @param topN
	 *            -- not yet implemented
	 * @return 2xN matrix, xy coordinates, the same order as in the List of
	 *         germplasms
	 */
	private double[][] constructMDSPlot(List<BigDecimal> germplasms, String scale, boolean isAll, int topN) {

		// if (isAll)
		return constructMDSPlotFromCore(germplasms);
		// return null;

		// dist3kdao = (VarietyDistanceDAO) AppContext.checkBean(dist3kdao,
		// "VarietyDistanceDAO");
		//
		// // /*
		// // MemoryMXBean bean = ManagementFactory.getMemoryMXBean();
		// // AppContext.debug("heap space used MB:" +
		// // bean.getHeapMemoryUsage().getUsed()*1.0/1000000 );
		// // bean.gc();
		// // AppContext.debug("GC successful: heap space used MB:" +
		// // bean.getHeapMemoryUsage().getUsed()*1.0/1000000 );
		// // */
		// //
		// java.util.Map<BigDecimal, Integer> mapVarid2Row = new
		// java.util.HashMap<BigDecimal, Integer>();
		//
		// int distscale = Integer.parseInt(scale);
		// List<VarietyDistance> listdist;
		// //
		// int i = 0;
		//
		// // setup ids, columns, rows
		// java.util.Iterator<BigDecimal> itgerm = germplasms.iterator();
		// while (itgerm.hasNext()) {
		// BigDecimal c = itgerm.next();
		// mapVarid2Row.put(c, i);
		// i++;
		// }
		//
		// listdist = dist3kdao.findVarieties(new HashSet(germplasms));
		//
		// return constructMDSPlot(mapVarid2Row, listdist, scale, topN);

		// java.util.Iterator<VarietyDistance> itdist = listdist.iterator();
		// double input[][] = new double[i][i];
		//
		// // construct the distance matrix
		// while(itdist.hasNext())
		// {
		//
		// VarietyDistance dist3k = itdist.next();
		//
		// if(!mapVarid2Row.containsKey(dist3k.getVar1()) ) continue ; //throw new
		// new RuntimeException("No key " + dist3k.getVar1() + " in mapVarid2Row");
		// if(!mapVarid2Row.containsKey(dist3k.getVar2()) ) continue ; //throw new
		// new RuntimeException("No key " + dist3k.getVar2() + " in mapVarid2Row");
		//
		// Double dist = dist3k.getDist().doubleValue()*distscale; //
		// Double.valueOf(dist3k.getDist().toString() )*distscale;;
		//
		// input[ mapVarid2Row.get(dist3k.getVar1()
		// )][mapVarid2Row.get(dist3k.getVar2())] = dist ;
		// input[ mapVarid2Row.get(dist3k.getVar2()
		// )][mapVarid2Row.get(dist3k.getVar1())] = dist ;
		// }
		//
		// itdist = null;
		// listdist = null;
		//
		// //bean.gc();
		// return mdsj.MDSJ.classicalScaling(input);
	}

	public static double[][] constructMDSPlot(Map<BigDecimal, Integer> mapVarid2Row, List<VarietyDistance> listdist,
			String scale, int topN) {

		// int i=listdist.size();
		int i = mapVarid2Row.size();
		AppContext.debug("constructing mds wholegenome for " + i + " vars, " + listdist.size() + " distances");
		java.util.Iterator<VarietyDistance> itdist = listdist.iterator();
		double input[][] = new double[i][i];
		int distscale = Integer.parseInt(scale);
		// construct the distance matrix
		while (itdist.hasNext()) {

			VarietyDistance dist3k = itdist.next();

			if (!mapVarid2Row.containsKey(dist3k.getVar1()))
				continue; // throw new RuntimeException("No key " + dist3k.getVar1() + " in
							// mapVarid2Row");
			if (!mapVarid2Row.containsKey(dist3k.getVar2()))
				continue; // throw new RuntimeException("No key " + dist3k.getVar2() + " in
							// mapVarid2Row");

			Double dist = dist3k.getDist().doubleValue() * distscale; // Double.valueOf( dist3k.getDist().toString()
																		// )*distscale;;

			input[mapVarid2Row.get(dist3k.getVar1())][mapVarid2Row.get(dist3k.getVar2())] = dist;
			input[mapVarid2Row.get(dist3k.getVar2())][mapVarid2Row.get(dist3k.getVar1())] = dist;
		}

		itdist = null;
		listdist = null;
		MemoryMXBean bean = ManagementFactory.getMemoryMXBean();
		AppContext.debug("heap space used MB:" + bean.getHeapMemoryUsage().getUsed() * 1.0 / 1000000);
		bean.gc();
		AppContext.debug("GC successful: heap space used MB:" + bean.getHeapMemoryUsage().getUsed() * 1.0 / 1000000);
		return mdsj.MDSJ.classicalScaling(input);
	}

	/**
	 * Construct mds for all varieties
	 * 
	 * @param germplasms
	 * @return 2xN matrix of xy coordinates
	 */
	private double[][] constructMDSPlotFromCore(List<BigDecimal> germplasms) {

		Map<String, double[]> mapCode2XY = Data.get3kCoreMDSXY();

		varietydao = (VarietyDAO) AppContext.checkBean(varietydao, "VarietyDAO");

		Iterator<Variety> itVars = varietydao.findAllVariety().iterator();
		Map<BigDecimal, Variety> mapId2Var = new HashMap();
		while (itVars.hasNext()) {
			Variety var = itVars.next();
			mapId2Var.put(var.getVarietyId(), var);
		}

		Iterator itId = germplasms.iterator();

		double xy[][] = new double[2][germplasms.size()];

		int i = 0;
		while (itId.hasNext()) {
			Variety var = mapId2Var.get(itId.next());
			if (var.getIrisId() != null && !var.getIrisId().isEmpty()) {
				double xyi[] = mapCode2XY.get(var.getIrisId().replace(" ", "_").toUpperCase());
				if (xyi == null) {
					i++;
					continue;
				}
				;
				xy[0][i] = xyi[0];
				xy[1][i] = xyi[1];
				i++;
			} else if (var.getBoxCode() != null && !var.getBoxCode().isEmpty()) {
				double xyi[] = mapCode2XY.get(var.getBoxCode().toUpperCase());
				if (xyi == null) {
					i++;
					continue;
				}
				;
				xy[0][i] = xyi[0];
				xy[1][i] = xyi[1];
				i++;
			}

		}
		// AppContext.debug(i + " varieties in MDS all");

		return xy;
		//
		// return null;

	}

	/**
	 * Construct mds for all varieties
	 * 
	 * @param germplasms
	 * @return 2xN matrix of xy coordinates
	 */
	private double[][] constructMDSPlot(List<BigDecimal> germplasms) {

		Map<String, double[]> mapCode2XY = Data.get3kCoreMDSXY();

		varietydao = (VarietyDAO) AppContext.checkBean(varietydao, "VarietyDAO");

		Iterator<Variety> itVars = varietydao.findAllVariety().iterator();
		Map<BigDecimal, Variety> mapId2Var = new HashMap();
		while (itVars.hasNext()) {
			Variety var = itVars.next();
			mapId2Var.put(var.getVarietyId(), var);
		}

		Iterator itId = germplasms.iterator();

		double xy[][] = new double[2][germplasms.size()];

		int i = 0;
		while (itId.hasNext()) {
			Variety var = mapId2Var.get(itId.next());
			if (var.getIrisId() != null && !var.getIrisId().isEmpty()) {
				double xyi[] = mapCode2XY.get(var.getIrisId().replace(" ", "_").toUpperCase());
				if (xyi == null) {
					i++;
					continue;
				}
				;
				xy[0][i] = xyi[0];
				xy[1][i] = xyi[1];
				i++;
			} else if (var.getBoxCode() != null && !var.getBoxCode().isEmpty()) {
				double xyi[] = mapCode2XY.get(var.getBoxCode().toUpperCase());
				if (xyi == null) {
					i++;
					continue;
				}
				;
				xy[0][i] = xyi[0];
				xy[1][i] = xyi[1];
				i++;
			}

		}
		// AppContext.debug(i + " varieties in MDS all");

		return xy;
		//
		// return null;

	}

	private double[][] constructMDSPlotFromHDRA(List<BigDecimal> germplasms) {

		Map<BigDecimal, double[]> mapCode2XY = Data.getHDRAMDSXY();

		Iterator<BigDecimal> itId = germplasms.iterator();
		double xy[][] = new double[2][germplasms.size()];

		int i = 0;
		while (itId.hasNext()) {
			BigDecimal varid = itId.next();
			if (mapCode2XY.containsKey(varid)) {
				double xyi[] = mapCode2XY.get(varid);
				xy[0][i] = xyi[0];
				xy[1][i] = xyi[1];
			} else
				AppContext.debug(varid + " not found in getHDRAMDSXY()");
			i++;
		}
		// AppContext.debug(i + " varieties in MDS all");

		return xy;

	}

	@Override
	public Map getVarietyExternalURL(String name) {
		

		varpropservice = (VarietyPropertiesService) AppContext.checkBean(varpropservice, "VarietyPropertiesService");

		return varpropservice.getProperties(name);
	}

	@Override
	public Set checkVariety(String varstrs, Set dataset) {
		Set varset = new LinkedHashSet();
		String varstrarr[] = varstrs.split(",");
		for (int ivar = 0; ivar < varstrarr.length; ivar++) {
			String varstr = varstrarr[ivar];
			try {
				varset.addAll(getGermplasmsByName(varstr, dataset));
				// varset.addAll(getGermplasmsByAccession("IRGC " +
				// varstr.toUpperCase().replace("IRGC","").trim() ,dataset));
				varset.add(getGermplasmsByAccession(varstr.toUpperCase().trim(), dataset));
				varset.add(
						getGermplasmsByAccession("IRGC " + varstr.toUpperCase().replace("IRGC", "").trim(), dataset));
				varset.add(getGermplasmByIrisId("IRIS " + varstr.replace("IRIS_", "").replace("IRIS", "").trim(),
						dataset));

				/*
				 * if(var==null) var = getGermplasmByIrisId(varstr, dataset); if(var==null) var
				 * = getGermplasmByIrisId("IRIS " + varstr.replace("IRIS_",""), dataset);
				 * if(var==null) var = getGermplasmByNameLike(varstr, dataset); if(var==null)
				 * var = getGermplasmByNameLike("%" + varstr, dataset); if(var==null) var =
				 * getGermplasmByNameLike("%:irgc " + varstr, dataset); if(var==null) var =
				 * getGermplasmByNameLike(varstr+"%", dataset); if(var!=null) varset.add(var);
				 */
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return varset;
	}

	@Override
	public Set checkVariety(String varstr, String dataset) {
		Set s = new HashSet();
		s.add(dataset);
		return checkVariety(varstr, s);
	}

	@Override
	public List getVarietyNames(Set dataset) {
		return listitemsDAO.getVarietyNames(dataset);
	}

	@Override
	public List<String> getVarietyAccessions(String dataset) {
		return listitemsDAO.getAccessions(dataset);
	}

	@Override
	public Set getGermplasmByCountry(String country, Set dataset) {
		return listitemsDAO.getGermplasmByCountry(country, dataset);
	}

	@Override
	public Set getGermplasm(Set dataset) {
		return listitemsDAO.getGermplasm(dataset);
	}

	@Override
	public Set getGermplasmBySubpopulation(String subpopulation, String dataset) {
		return listitemsDAO.getGermplasmBySubpopulation(subpopulation, dataset);
	}

	@Override
	public List getIrisIds(String dataset) {
		return listitemsDAO.getIrisIds(dataset);
	}

	@Override
	public List getSubpopulations(String dataset) {
		return listitemsDAO.getSubpopulations(dataset);
	}

	/*
	 * @Override public List getAccessions(String dataset) { // TODO Auto-generated
	 * method stub return listitemsDAO.getAccessions(); }
	 */

	@Override
	public List getAccessions(String dataset) {
		return listitemsDAO.getAccessions(dataset);
	}

	
	@Override
	public List getGermplasmByNameLike(String name, Set dataset) {
		Set s = new HashSet();
		s.add(name);
		return varietydao.findVarietyByNamesLike(s, dataset);
	}

	@Override
	public Variety getGermplasmByAccession(String value, String dataset) {
		return listitemsDAO.getGermplasmByAccession(value, dataset);
	}

	@Override
	public Set getGermplasmsByNameAccession(String varname, String accession, String dataset) {
		return listitemsDAO.getGermplasmsByNameAccession(varname, accession, dataset);
	}

	@Override
	public Set getQuantTraits(String dataset) {
		Set s = new HashSet(AppContext.getDBQuanTraits());
		workspace = (WorkspaceFacade) AppContext.checkBean(workspace, "WorkspaceFacade");
		s.addAll(workspace.getVarietyQuantPhenotypelistNames(dataset));
		return s;
	}

	@Override
	public Set getQuantTraits(Set dataset) {
		return listitemsDAO.getQuantTraits(dataset);
	}

	@Override
	public List getDatasets() {
		List l = new ArrayList();
		l.addAll(listitemsDAO.getDatasets("SNP"));
		return l;
	}
	
	@Override
	public List getDatasets(String type, String reference) {
		List l = new ArrayList();
		l.addAll(listitemsDAO.getDatasets(type, reference));
		return l;
	}

	@Override
	public Map getTraits(Set<String> dataset, boolean legacyPhenotype) {

		return listitemsDAO.getTraits(dataset, legacyPhenotype);

	}

	@Override
	public BigDecimal getPhenotypeId(String coTerm, String dataset) {

		return (BigDecimal) listitemsDAO.getCOTerms(dataset).get(coTerm);

	}

	@Override
	public Map<BigDecimal, String> getAllTraits(Set<String> dataset, boolean legacyPhenotype) {

		return listitemsDAO.getAllTraits(dataset, legacyPhenotype);
	}

	@Override
	public List<Variety> getGermplasmsByAccession(Collection names, Set dataset) {
		return varietydao.findVarietyByAccession(names, dataset);
	}
	
	@Override
	public List getGenotyperuns() {
		List l = new ArrayList();
		l.addAll(listitemsDAO.getRunsByType("SNP"));
		return l;
	}

}

// PAST CODES RETAINED

/*
 * @Override public List getVarietyByPassport(String sPassId) { // TODO
 * Auto-generated method stub varbypassportdao =
 * (VarietyByPassportDAO)AppContext.checkBean(varbypassportdao,
 * "VIricstocksByPassportDAO"); //listitemsDAO =
 * (ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItemsDAO"); return
 * varbypassportdao.findVarietyByPassport(sPassId); }
 */