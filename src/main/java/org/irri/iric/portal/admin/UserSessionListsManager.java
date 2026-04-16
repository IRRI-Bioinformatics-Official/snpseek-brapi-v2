package org.irri.iric.portal.admin;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.irri.iric.ds.chado.dao.VarietyDAO;
import org.irri.iric.ds.chado.domain.Locus;
import org.irri.iric.ds.chado.domain.Variety;
import org.irri.iric.ds.chado.domain.impl.MultiReferencePositionImpl;
import org.irri.iric.ds.chado.domain.impl.MultiReferencePositionImplAllelePvalue;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.irri.iric.portal.genomics.GenomicsFacade;
import org.irri.iric.portal.variety.VarietyFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

//@Component("UserSessionListsManager")
//@Scope("prototype")
public class UserSessionListsManager implements Serializable {

	/**
	 * Stores a map of name 2 variety list
	 */
	private Map<String, Set> mapVarietyLists;
	private Map<String, Map<String, Set>> mapSnpposLists;
	private Map<String, Set> mapLocusLists;

	private Set setPoslistWithAllele = new LinkedHashSet();
	private Set setPoslistWithPvalue = new LinkedHashSet();

	private Map<String, DatasetPhenotypeParams> mapDataset2Varietylist2PhenParams = new HashMap();

	@Autowired
	// VIricstockBasicprop2DAO varietyprop2DAO;
	@Qualifier("VarietyDAO")
	private VarietyDAO varietyprop2DAO;

	@Autowired
	// VLocusNotesDAO locusnotesDAO;
	// private LocusService locusService;
	private GenomicsFacade genomics;

	@Autowired
	@Qualifier("ListItems")
	private ListItemsDAO listdao;

	public UserSessionListsManager() {
		super();
		AppContext.debug("created UserSessionManager:" + this);
	}

	class DatasetPhenotypeParams {
		String dataset;
		Set setVarietylistWithQuantPhenotype = new TreeSet();
		Set setVarietylistWithCatPhenotype = new TreeSet();
		Map<String, List> mapVarietylist2Phenotypenames = new HashMap();
		Map<String, Map<BigDecimal, Object[]>> mapVarietylist2Mapvarid2Phenvalues = new HashMap();

		public DatasetPhenotypeParams(String dataset) {
			super();
			this.dataset = dataset;
		}
	}

	public Set getVarietylistNames() {
		if (mapVarietyLists == null)
			return new HashSet();
		return mapVarietyLists.keySet();
	}

	public Set getVarietyQuantPhenotypelistNames() {
		if (mapDataset2Varietylist2PhenParams == null)
			return new HashSet();
		Set s = new LinkedHashSet();
		for (String ds : mapDataset2Varietylist2PhenParams.keySet()) {
			s.addAll(getVarietyQuantPhenotypelistNames(ds));
		}
		return s;
	}

	public String getDataset(String listname) {
		if (mapDataset2Varietylist2PhenParams == null)
			return null;
		for (String ds : mapDataset2Varietylist2PhenParams.keySet()) {
			if (mapDataset2Varietylist2PhenParams.get(ds).mapVarietylist2Phenotypenames.keySet().contains(listname)) {
				AppContext.debug("dataset of " + listname + " = " + ds);
				return ds;
			}
		}
		return "";
	}

	public boolean hasQuantPhenotype(String listname) {
		if (mapDataset2Varietylist2PhenParams == null)
			return false;
		for (String ds : mapDataset2Varietylist2PhenParams.keySet()) {
			for (String listphen : (Set<String>) getVarietyQuantPhenotypelistNames(ds)) {
				if (listphen.split("::")[0].equals(listname))
					return true;
			}
		}
		return false;
	}

	public boolean hasCatPhenotype(String listname) {
		if (mapDataset2Varietylist2PhenParams == null)
			return false;
		for (String ds : mapDataset2Varietylist2PhenParams.keySet()) {
			for (String listphen : (Set<String>) getVarietyCatPhenotypelistNames(ds)) {
				if (listphen.split("::")[0].equals(listname))
					return true;
			}
		}
		return false;
	}

	public Set getVarietyCatPhenotypelistNames() {
		if (mapDataset2Varietylist2PhenParams == null)
			return new HashSet();
		Set s = new LinkedHashSet();
		for (String ds : mapDataset2Varietylist2PhenParams.keySet()) {
			s.addAll(getVarietyCatPhenotypelistNames(ds));
		}
		return s;
	}

	public Set<String> getVarietyQuantPhenotypelistNames(String dataset) {
		DatasetPhenotypeParams p = mapDataset2Varietylist2PhenParams.get(dataset);
		if (p == null || p.setVarietylistWithQuantPhenotype == null)
			return new HashSet<String>();
		Set<String> npset = new TreeSet<String>();
		Iterator<String> itL = p.setVarietylistWithQuantPhenotype.iterator();
		while (itL.hasNext()) {
			String lname = itL.next();
			List sP = p.mapVarietylist2Phenotypenames.get(lname);
			Iterator<String> itP = sP.iterator();
			while (itP.hasNext()) {
				npset.add(lname + "::" + itP.next());
			}
		}
		return npset;
	}

	public Set getVarietyCatPhenotypelistNames(String dataset) {
		DatasetPhenotypeParams p = mapDataset2Varietylist2PhenParams.get(dataset);
		if (p == null || p.setVarietylistWithCatPhenotype == null)
			return new HashSet();

		Set npset = new TreeSet();
		Iterator<String> itL = p.setVarietylistWithCatPhenotype.iterator();
		while (itL.hasNext()) {
			String lname = itL.next();
			List sP = p.mapVarietylist2Phenotypenames.get(lname);
			Iterator<String> itP = sP.iterator();
			while (itP.hasNext()) {
				npset.add(lname + "::" + itP.next());
			}
		}
		return npset;
	}

	public Set getVarieties(String listname) {
		if (mapVarietyLists == null)
			return new HashSet();

		Set valist = mapVarietyLists.get(listname);
		// valist AppContext.debug(listname + " retrieved with " + valist.size() + "
		// varieties");
		return valist;
	}

	public void deleteVarietyList(String listname) {
		if (mapVarietyLists == null)
			return;
		mapVarietyLists.remove(listname);
	}

	/**
	 * 
	 * @param name
	 * @param varietylist
	 * @return
	 */

	public boolean addLocusList(String name, Set locuslist) {
		if (mapLocusLists == null)
			mapLocusLists = new LinkedHashMap();
		if (mapLocusLists.containsKey(name))
			return false;

		mapLocusLists.put(name, locuslist);
		
		AppContext.debug(name + " added with " + locuslist.size() + " loci");
		return true;
	}

	public Set getLocuslistNames() {
		if (mapLocusLists == null) {
			AppContext.debug("0 mapLocusLists");
			return new HashSet();
		}
		AppContext.debug(mapLocusLists.keySet().toString());
		return mapLocusLists.keySet();
	}

	public Set getLoci(String listname) {
		if (mapLocusLists == null)
			return new HashSet();

		Set loclist = mapLocusLists.get(listname);
		// valist AppContext.debug(listname + " retrieved with " + valist.size() + "
		// varieties");
		return loclist;
	}

	public void deleteLocusList(String listname) {
		if (mapLocusLists == null)
			return;
		mapLocusLists.remove(listname);
	}

	/**
	 * 
	 * @param chromosome
	 * @param name
	 * @param poslist
	 * @param hasPvalue
	 * @param hasAllele
	 * @return
	 */

	// public boolean addSNPList(String contig, String name, Set poslist) {
	// return addSNPList(contig, name, poslist, false, false);
	// }

	public boolean addSNPList(String contig, String name, Set poslist, boolean hasAllele, boolean hasPvalue) {

		if (mapSnpposLists == null)
			mapSnpposLists = new TreeMap();

		Map mapName2List = mapSnpposLists.get(contig);
		if (mapName2List == null) {
			mapName2List = new LinkedHashMap();
			mapSnpposLists.put(contig, mapName2List);
		}
		if (mapName2List.containsKey(name))
			return false;
		mapName2List.put(name, new TreeSet(poslist));
		AppContext.debug(name + " added to chromosome " + contig + " with " + poslist.size() + " snp positions");
		if (hasAllele)
			setPoslistWithAllele.add(contig + ":" + name);
		if (hasPvalue)
			setPoslistWithPvalue.add(contig + ":" + name);
		return true;
	}

	/**
	 * 
	 * @param name
	 * @param varietylist
	 * @param has
	 *            phenotype (0:none, 1:quantitative, 2:categorical
	 * @param phenotype
	 *            names
	 * @return false if name already exists
	 */
	// public boolean addVarietyList(String name, Set varietylist, boolean
	// hasPhenotype) {

	public boolean addVarietyList(String name, Set varietylist, String dataset) {
		return addVarietyList(name, varietylist, dataset, 0, null, null);
	}

	public boolean addVarietyList(String name, Set setVarieties, Set dataset) {
		boolean success = true;
		Iterator it = dataset.iterator();
		while (it.hasNext()) {
			String ds = (String) it.next();
			success = success && addVarietyList(name, setVarieties, ds);
		}

		return success;
	}

	public boolean addVarietyList(String name, Set varietylist, String dataset, int intPhenotype, List phenotypeNames,
			Map<BigDecimal, Object[]> mapVarid2Phen) {
		if (mapVarietyLists == null)
			mapVarietyLists = new LinkedHashMap();
		if (mapVarietyLists.containsKey(name))
			return true;

		Set setQueryVarobj = new HashSet();
		Set setVarobj = new HashSet();
		Iterator itVars = varietylist.iterator();
		while (itVars.hasNext()) {
			Object varobj = itVars.next();
			if (varobj instanceof Number)
				setQueryVarobj.add(varobj);
			else
				setVarobj.add(varobj);
		}
		if (!setQueryVarobj.isEmpty()) {
			setVarobj.addAll(varietyprop2DAO.findVarietyByIds(setQueryVarobj));
		}
		varietylist = setVarobj;

		mapVarietyLists.put(name, varietylist);
		AppContext.debug(name + " added with " + varietylist.size() + " varieties");

		if (intPhenotype == 0) {
		} else {
			if (varietylist.size() != mapVarid2Phen.size()) {
				throw new RuntimeException(
						"varietylist=" + varietylist.size() + " != mapVarid2Phen " + mapVarid2Phen.size());
			}

			DatasetPhenotypeParams p = mapDataset2Varietylist2PhenParams.get(dataset);
			if (p == null) {
				p = new DatasetPhenotypeParams(dataset);
				mapDataset2Varietylist2PhenParams.put(dataset, p);
			}
			p.mapVarietylist2Phenotypenames.put(name, phenotypeNames);
			p.mapVarietylist2Mapvarid2Phenvalues.put(name, mapVarid2Phen);
			if (intPhenotype == 1)
				p.setVarietylistWithQuantPhenotype.add(name);
			else if (intPhenotype == 2)
				p.setVarietylistWithCatPhenotype.add(name);

			AppContext.debug("addVarietyList  p.mapVarietylist2Phenotypenames=" + p.mapVarietylist2Phenotypenames + "\n"
					+ "setVarietylistWithQuantPhenotype=" + p.setVarietylistWithQuantPhenotype + "\n"
					+ "setVarietylistWithCatPhenotype=" + p.setVarietylistWithCatPhenotype + "\n"
					+ "mapVarietylist2Mapvarid2Phenvalues.keyset=" + p.mapVarietylist2Mapvarid2Phenvalues.keySet()
					+ "\n");
		}
		return true;
	}

	public List getPhenotypeNames(String listname, String dataset) {
		DatasetPhenotypeParams p = mapDataset2Varietylist2PhenParams.get(dataset);
		if (p == null || p.mapVarietylist2Phenotypenames == null)
			return new ArrayList();
		return p.mapVarietylist2Phenotypenames.get(listname);
	}

	public List getPhenotypeNames(String listname) {
		List listnames = new ArrayList();
		for (String ds : mapDataset2Varietylist2PhenParams.keySet()) {
			listnames.addAll(getPhenotypeNames(listname, ds));
		}
		return listnames;
	}

	private Map getMapvarid2Phenvalues(String name) {
		Map m = new LinkedHashMap();
		for (String ds : mapDataset2Varietylist2PhenParams.keySet()) {
			m.putAll(getMapvarid2Phenvalues(name, ds));
		}
		return m;
	}

	private Map getMapvarid2Phenvalues(String name, String dataset) {
		DatasetPhenotypeParams p = mapDataset2Varietylist2PhenParams.get(dataset);
		Map m = null;
		if (p == null || p.mapVarietylist2Mapvarid2Phenvalues == null)
			m = new HashMap();
		else {
			m = p.mapVarietylist2Mapvarid2Phenvalues.get(name);
			if (m == null)
				m = new HashMap();
		}

		AppContext.debug(" getMapvarid2Phenvalues " + name + ", " + dataset + " size=" + m.size() + ", p=" + p);
		return m;
	}

	public Integer getPhenotypeColidx(String listname, String name, String dataset) {
		DatasetPhenotypeParams p = mapDataset2Varietylist2PhenParams.get(dataset);
		if (p == null || p.mapVarietylist2Phenotypenames == null)
			return null;
		List l = p.mapVarietylist2Phenotypenames.get(listname);
		if (l == null)
			return null;
		name = name.toUpperCase();
		int i = 0;
		Iterator<String> itl = l.iterator();
		while (itl.hasNext()) {
			String n = itl.next();
			if (n.toUpperCase().equals(name))
				return i;
			i++;
		}
		return null;
	}

	public Map<BigDecimal, Object[]> getPhenotypeValues(String listname, String dataset) {
		DatasetPhenotypeParams p = mapDataset2Varietylist2PhenParams.get(dataset);
		if (p == null || p.mapVarietylist2Mapvarid2Phenvalues == null)
			return null;
		return p.mapVarietylist2Mapvarid2Phenvalues.get(listname);
	}

	public Double getVarietyQuantPhenotypeValues(String listname, String dataset, BigDecimal varid, int colidx) {
		DatasetPhenotypeParams p = mapDataset2Varietylist2PhenParams.get(dataset);
		if (p == null || p.mapVarietylist2Mapvarid2Phenvalues == null)
			return null;
		return (Double) p.mapVarietylist2Mapvarid2Phenvalues.get(listname).get(varid)[colidx];
	}

	private Map<BigDecimal, Double> getVarietyQuantPhenotypeValues(String listname, String dataset, String phenname) {
		DatasetPhenotypeParams p = mapDataset2Varietylist2PhenParams.get(dataset);
		if (p == null || p.mapVarietylist2Mapvarid2Phenvalues == null)
			return null;
		AppContext.debug("getVarietyQuantPhenotypeValues: p=" + p);
		Map m = new HashMap();
		int colidx = getPhenotypeColidx(listname, phenname, dataset);
		Map<BigDecimal, Object[]> mvar = p.mapVarietylist2Mapvarid2Phenvalues.get(listname);
		AppContext.debug("getVarietyQuantPhenotypeValues: colidx=" + colidx + " p=" + p + " mvar=" + mvar);
		for (BigDecimal varid : mvar.keySet()) {
			m.put(varid, mvar.get(varid)[colidx]);
		}
		return m;
	}

	public String getVarietyCatPhenotypeValues(String listname, String dataset, BigDecimal varid, int colidx) {
		DatasetPhenotypeParams p = mapDataset2Varietylist2PhenParams.get(dataset);
		if (p == null || p.mapVarietylist2Mapvarid2Phenvalues == null)
			return null;
		return (String) p.mapVarietylist2Mapvarid2Phenvalues.get(listname).get(varid)[colidx];
	}

	private Map<BigDecimal, String> getVarietyCatPhenotypeValues(String listname, String dataset, String phenname) {
		DatasetPhenotypeParams p = mapDataset2Varietylist2PhenParams.get(dataset);
		if (p == null || p.mapVarietylist2Mapvarid2Phenvalues == null)
			return null;
		Map m = new HashMap();
		int colidx = getPhenotypeColidx(listname, phenname, dataset);
		Map<BigDecimal, Object[]> mvar = p.mapVarietylist2Mapvarid2Phenvalues.get(listname);
		for (BigDecimal varid : mvar.keySet()) {
			m.put(varid, mvar.get(varid)[colidx]);
		}
		return m;
	}

	public Map getVarietyPhenotypeValues(String listname, String dataset, String phenname) {
		AppContext.debug("getVarietyPhenotypeValues: " + listname + "::" + phenname);
		AppContext.debug("getVarietyCatPhenotypelistNames()=" + getVarietyCatPhenotypelistNames());
		AppContext.debug("getVarietyQuantPhenotypelistNames()=" + getVarietyQuantPhenotypelistNames());

		if (getVarietyCatPhenotypelistNames().contains(listname + "::" + phenname))
			return getVarietyCatPhenotypeValues(listname, dataset, phenname);
		else if (getVarietyQuantPhenotypelistNames().contains(listname + "::" + phenname))
			return getVarietyQuantPhenotypeValues(listname, dataset, phenname);
		return new HashMap();
	}

	public Set getSNPlistNames() {

		Set returnset = new LinkedHashSet();
		if (mapSnpposLists == null)
			return returnset;
		Iterator itChr = mapSnpposLists.keySet().iterator();
		while (itChr.hasNext()) {
			Object chr = itChr.next();
			Iterator itNames = ((Map) mapSnpposLists.get(chr)).keySet().iterator();
			while (itNames.hasNext()) {
				// returnset.add("CHR " + chr + ": " + itNames.next());
				returnset.add(chr + ":" + itNames.next());
			}
		}
		return returnset;
	}

	public Set getSNPlistAlleleNames() {
		return this.setPoslistWithAllele;
	}

	public Set getSNPlistPvalueNames() {
		return this.setPoslistWithPvalue;
	}

	public Set getSNPs(String chromosome, String listname) {
		Set returnset = new LinkedHashSet();
		if (mapSnpposLists == null)
			return returnset;
		Map name2List = mapSnpposLists.get(chromosome);
		if (name2List == null)
			return returnset;
		Set namelist = (Set) name2List.get(listname);
		if (namelist == null)
			return returnset;
		return namelist;
	}

	public boolean SNPhasAllele(String name) {
		return this.setPoslistWithAllele.contains(name);
	}

	public boolean SNPhasPvalue(String name) {
		return this.setPoslistWithPvalue.contains(name);
	}

	public void deleteSNPList(String chromosome, String listname) {

		this.setPoslistWithAllele.remove(listname);
		this.setPoslistWithPvalue.remove(listname);

		if (mapSnpposLists == null)
			return;
		Map name2List = mapSnpposLists.get(chromosome);
		if (name2List == null)
			return;
		name2List.remove(listname);
	}

	/**
	 * Recreate list from list file
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public boolean uploadList(String list) throws Exception {
		varietyprop2DAO = (VarietyDAO) AppContext.checkBean(varietyprop2DAO, "VarietyDAO");
		// locusService=(LocusService)AppContext.checkBean(locusService,"LocusService");
		genomics = (GenomicsFacade) AppContext.checkBean(genomics, "GenomicsFacade");

		String lines[] = list.split("\n");
		int state = 0;
		String listname = "";
		String chr = "";
		boolean hasAllele = false;
		boolean hasPvalue = false;
		// boolean hasPhenotype=false;
		int intPhenotype = 0;
		List setphennames = null;
		Map mapvarid2phen = null;
		String dataset = "";
		Set setListMembers = null;
		String version = "";
		try {

			for (int i = 0; i < lines.length; i++) {
				String l = lines[i];
				// AppContext.debug("processing: " + l);
				if (l.isEmpty())
					continue;
				String lsplit[] = lines[i].split("\t");
				switch (state) {
				case 0:
					if (l.startsWith("VARIETY LISTS:"))
						state = 1;
					else if (l.startsWith("SNP LISTS:"))
						state = 2;
					else if (l.startsWith("LOCUS LISTS:"))
						state = 3;
					else if (l.startsWith("VERSION:"))
						version = l.replace("VERSION:", "").trim();
					break;
				case 1:
					if (l.startsWith("#VARLIST")) {
						if (setListMembers != null && !listname.isEmpty()) {
							addVarietyList(listname, setListMembers, dataset, intPhenotype, setphennames,
									mapvarid2phen);
						}
						listname = lsplit[1];
						dataset = lsplit[2];
						intPhenotype = 0;
						setphennames = new ArrayList();
						if (lsplit.length > 3) {
							intPhenotype = Integer.valueOf(lsplit[3]);
							String phennames[] = lsplit[4].trim().split(",");
							for (int ip = 0; ip < phennames.length; ip++)
								setphennames.add(phennames[ip].trim());
						}
						setListMembers = new LinkedHashSet();
						mapvarid2phen = new LinkedHashMap();
					} else if (l.startsWith("\t\t")) {
						// buff.append("\t\t" + var.getVarietyId() + "\t" + var.getName() + "\t" +
						// irisid + "\t" + boxcode + "\n");
						// \t\t1981 ARC 7281::IRGC 24252-2 IRIS 313-10933 BC40 aro India 2.7,1.2,62.0
						BigDecimal varid = BigDecimal.valueOf(Long.valueOf(lsplit[2]));
						// setListMembers.add( varietyprop2DAO.findVarietyById(varid) );
						setListMembers.add(varid);
						// AppContext.debug("l.split().length=" + lsplit.length);
						if (lsplit.length > 8) {
							String phenvals[] = lsplit[8].split(",");
							if (intPhenotype == 1) {
								Double dvals[] = new Double[phenvals.length];
								for (int ival = 0; ival < phenvals.length; ival++) {
									if (phenvals[ival].isEmpty())
										continue;
									dvals[ival] = Double.valueOf(phenvals[ival]);
								}
								mapvarid2phen.put(varid, dvals);
							} else if (intPhenotype == 2) {
								String svals[] = new String[phenvals.length];
								for (int ival = 0; ival < phenvals.length; ival++) {
									if (phenvals[ival].isEmpty())
										continue;
									svals[ival] = phenvals[ival];
								}
								mapvarid2phen.put(varid, svals);
							}
						}

					} else if (l.startsWith("SNP LISTS:")) {
						if (setListMembers != null && !listname.isEmpty())
							addVarietyList(listname, setListMembers, dataset, intPhenotype, setphennames,
									mapvarid2phen);
						listname = "";
						setListMembers = null;
						state = 2;
					} else if (l.startsWith("LOCUS LISTS:")) {
						if (setListMembers != null && !listname.isEmpty())
							addVarietyList(listname, setListMembers, dataset, intPhenotype, setphennames,
									mapvarid2phen);
						listname = "";
						setListMembers = null;
						state = 3;
					}
					break;
				case 2:
					if (l.startsWith("#SNPLIST")) {
						if (setListMembers != null && !listname.isEmpty())
							addSNPList(chr, listname, setListMembers, hasAllele, hasPvalue);
						listname = lsplit[2];
						chr = lsplit[1];
						hasAllele = Boolean.valueOf(lsplit[3]);
						hasPvalue = Boolean.valueOf(lsplit[4]);
						setListMembers = new LinkedHashSet();

						// AppContext.debug("parsing snp list " + listname);

					} else if (l.startsWith("LOCUS LISTS:")) {
						if (setListMembers != null && !listname.isEmpty())
							addSNPList(chr, listname, setListMembers, hasAllele, hasPvalue);
						listname = "";
						setListMembers = null;
						state = 3;
					} else if (l.startsWith("\t\t")) {
						// buff.append("\t\t\t" + itPos.next() + "\n");

						String pos = lsplit[3];
						try {
							setListMembers.add(BigDecimal.valueOf(Long.valueOf(pos)));
						} catch (Exception ex) {
							// AppContext.debug("parsing " + pos);
							if (pos.startsWith("(")) {
								if (hasAllele || hasPvalue) {
									setListMembers.add(new MultiReferencePositionImplAllelePvalue(pos));
								} else
									setListMembers.add(new MultiReferencePositionImpl(pos));
							}
						}

						// setListMembers.add( lsplit[3] );
					}
					break;
				case 3:
					if (l.startsWith("#LOCUSLIST")) {
						if (setListMembers != null && !listname.isEmpty())
							addLocusList(listname, setListMembers);
						listname = lsplit[1];
						setListMembers = new LinkedHashSet();
					} else if (l.startsWith("\t\t")) {
						// buff.append("\t\t" + loc.getUniquename() + "\t" + loc.getContig() + "\t" +
						// loc.getFmin() + "\t" + loc.getFmax() + "\t" + loc.getStrand() + "\n");
						// setListMembers.add( locusService.getLocusByName( lsplit[2]));
						setListMembers.add(genomics.getLocusByName(lsplit[2]));
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

		switch (state) {
		case 0:
			break;
		case 1:
			if (setListMembers != null && !listname.isEmpty())
				addVarietyList(listname, setListMembers, dataset, intPhenotype, setphennames, mapvarid2phen);
			break;
		case 2:
			if (setListMembers != null && !listname.isEmpty())
				addSNPList(chr, listname, setListMembers, hasAllele, hasPvalue);
			break;
		case 3:
			if (setListMembers != null && !listname.isEmpty())
				addLocusList(listname, setListMembers);
			break;
		}

		return true;
	}

	public boolean uploadListCookie(String list) {
		/*
		 * String lines[] = list.split("\n"); for(int i=0; i<lines.length; i++ ) {
		 * String l=lines[i];
		 * 
		 * if(l.startsWith("#VARLIST\t")) { String[] cols = l.split("\t"); Set
		 * setListMembers = new LinkedHashSet(); String varids[] = cols[2].split(",");
		 * for(int iVar=0; iVar<varids.length; iVar++) { setListMembers.add(
		 * varietyprop2DAO.findVarietyById( BigDecimal.valueOf(Long.valueOf(
		 * varids[iVar] ))) ); } addVarietyList(cols[1].trim(), setListMembers); } else
		 * if(l.startsWith("#SNPLIST")) {
		 * 
		 * String[] cols = l.split("\t"); Set setListMembers = new LinkedHashSet();
		 * String pos[] = cols[3].split(","); for(int ipos=0; ipos<pos.length; ipos++) {
		 * setListMembers.add( pos[ipos] ); } addSNPList(cols[1] ,cols[2].trim(),
		 * setListMembers, hasAllele, hasPvalue); } }
		 */

		return true;
	}

	/**
	 * Convert list to ASCII text
	 * 
	 * @return
	 */
	public String downloadLists() {
		
		StringBuffer buff = new StringBuffer();
		// Map<BigDecimal, Variety> mapVarid2Var2 = null;

		listdao = (ListItemsDAO) AppContext.checkBean(listdao, "ListItems");
		Map<BigDecimal, Variety> mapVarid2Var2 = listdao.getMapId2Variety(VarietyFacade.DATASET_SNP_ALL);

		buff.append("VERSION: 1.0.42\n");
		Iterator<String> itNames = getVarietylistNames().iterator();

		if (itNames.hasNext()) {
			/*
			 * varietyprop2DAO = (VarietyDAO)AppContext.checkBean( varietyprop2DAO,
			 * "VarietyDAO"); Iterator<Variety> itVars =
			 * varietyprop2DAO.findAllVariety().iterator(); mapVarid2Var2 = new HashMap();
			 * while(itVars.hasNext()) { Variety var = itVars.next();
			 * mapVarid2Var2.put(var.getVarietyId(), var); }
			 */

			buff.append(
					"VARIETY LISTS:\tLIST NAMES:\tVARIETIES (ID, NAME:ACCESSION, IRIS ID, BOX CODE, SUBPOPULATION, COUNTRY)\n");
		}

		while (itNames.hasNext()) {
			String name = itNames.next();
			// buff.append("\t" + name + "\n");
			buff.append("#VARLIST\t" + name).append("\t").append(getDataset(name));

			// boolean hasQuant=getVarietyQuantPhenotypelistNames().contains(name);
			// boolean hasCat=getVarietyCatPhenotypelistNames().contains(name);

			boolean hasQuant = hasQuantPhenotype(name);
			boolean hasCat = hasCatPhenotype(name);

			Map varid2Phenvalues = null;
			if (hasCat || hasQuant) {
				buff.append("\t");
				if (hasQuant)
					buff.append("1");
				else if (hasCat)
					buff.append("2");
				buff.append("\t");
				// List lnames=(List)mapVarietylist2Phenotypenames.get(name);
				List lnames = getPhenotypeNames(name);
				Iterator<String> itpname = lnames.iterator();
				while (itpname.hasNext()) {
					buff.append(((String) itpname.next()));
					if (itpname.hasNext())
						buff.append(",");
				}
				// varid2Phenvalues=(Map)mapVarietylist2Mapvarid2Phenvalues.get(name);
				varid2Phenvalues = getMapvarid2Phenvalues(name);
			} else
				buff.append("0");

			buff.append("\n");

			Iterator<Variety> itVar = getVarieties(name).iterator();
			while (itVar.hasNext()) {
				Variety var = itVar.next();
				if (var == null)
					continue;
				String irisid = "";
				String boxcode = "";
				String country = "";
				String subpop = "";
				if (var.getIrisId() != null)
					irisid = var.getIrisId();
				if (var.getCountry() != null)
					country = var.getCountry();
				if (var.getSubpopulation() != null)
					subpop = var.getSubpopulation();

				Variety var2 = mapVarid2Var2.get(var.getVarietyId());
				if (var2 != null && var2.getBoxCode() != null)
					boxcode = var2.getBoxCode();

				buff.append("\t\t" + var.getVarietyId() + "\t" + var.getName() + "\t" + irisid + "\t" + boxcode + "\t"
						+ subpop + "\t" + country);

				if (hasQuant || hasCat) {
					buff.append("\t");
					Object vals[] = (Object[]) varid2Phenvalues.get(var.getVarietyId());
					if (vals == null) {
						AppContext.debug("No phenvalues for " + var.getVarietyId() + "  " + var.getName());
					} else {
						for (int ival = 0; ival < vals.length; ival++) {
							buff.append(vals[ival]);
							if (ival + 1 < vals.length)
								buff.append(",");
						}
					}
				}
				buff.append("\n");
			}
			buff.append("\n");
		}
		buff.append("\n");

		itNames = getSNPlistNames().iterator();

		if (itNames.hasNext())
			buff.append("SNP LISTS:\tCHROMOSOME:\tLIST NAMES:\tPOSITIONS\n");

		while (itNames.hasNext()) {
			String names[] = itNames.next().split(":");
			// Integer chr = Integer.valueOf(names[0].replace("CHR","").trim());
			String chr = names[0].trim();
			String listname = names[1].trim();
			Iterator<BigDecimal> itPos = getSNPs(chr, listname).iterator();
			// buff.append("\t" + chr + "\t" + listname + "\n");
			// buff.append("#SNPLIST\t" + chr + "\t" + listname + "\n");
			buff.append("#SNPLIST\t" + chr + "\t" + listname + "\t" + SNPhasAllele(listname) + "\t"
					+ SNPhasPvalue(listname) + "\n");
			while (itPos.hasNext()) {
				buff.append("\t\t\t" + itPos.next() + "\n");
			}
			buff.append("\n");
		}

		itNames = getLocuslistNames().iterator();
		if (itNames.hasNext())
			buff.append("LOCUS LISTS:\tLIST NAMES:\tLOCI (ACCESSION, CONTIG, START, END, STRAND)\n");

		while (itNames.hasNext()) {
			String name = itNames.next();
			// buff.append("\t" + name + "\n");
			buff.append("#LOCUSLIST\t" + name + "\n");
			Iterator<Locus> itLoc = getLoci(name).iterator();
			while (itLoc.hasNext()) {
				Locus loc = itLoc.next();
				if (loc == null)
					continue;
				buff.append("\t\t" + loc.getUniquename() + "\t" + loc.getContig() + "\t" + loc.getFmin() + "\t"
						+ loc.getFmax() + "\t" + loc.getStrand() + "\n");
			}
			buff.append("\n");
		}

		return buff.toString();

	}

	/**
	 * Convert list to ASCII text (compact version)
	 * 
	 * @return
	 */
	public String downloadListsCookie() {
		
		StringBuffer buff = new StringBuffer();

		varietyprop2DAO = (VarietyDAO) AppContext.checkBean(varietyprop2DAO, "VarietyDAO");
		Iterator<Variety> itVars = varietyprop2DAO.findAllVariety().iterator();
		Map<BigDecimal, Variety> mapVarid2Var2 = new HashMap();
		while (itVars.hasNext()) {
			Variety var = itVars.next();
			mapVarid2Var2.put(var.getVarietyId(), var);
		}

		Iterator<String> itNames = getVarietylistNames().iterator();

		while (itNames.hasNext()) {
			String name = itNames.next();
			// buff.append("\t" + name + "\n");
			buff.append("#VARLIST\t" + name + "\t");
			Iterator<Variety> itVar = getVarieties(name).iterator();
			while (itVar.hasNext()) {
				Variety var = itVar.next();
				if (var == null)
					continue;
				buff.append(var.getVarietyId());
				if (itVar.hasNext())
					buff.append(",");
			}
			buff.append("\n");
		}

		itNames = getSNPlistNames().iterator();

		while (itNames.hasNext()) {
			String names[] = itNames.next().split(":");
			// Integer chr = Integer.valueOf(names[0].replace("CHR","").trim());
			String chr = names[0].trim();
			String listname = names[1].trim();
			Iterator<BigDecimal> itPos = getSNPs(chr, listname).iterator();
			// buff.append("\t" + chr + "\t" + listname + "\n");
			buff.append("#SNPLIST\t" + chr + "\t" + listname + "\t");
			while (itPos.hasNext()) {
				buff.append(itPos.next());
				if (itPos.hasNext())
					buff.append(",");
			}
			buff.append("\n");
		}

		return buff.toString();

	}

}