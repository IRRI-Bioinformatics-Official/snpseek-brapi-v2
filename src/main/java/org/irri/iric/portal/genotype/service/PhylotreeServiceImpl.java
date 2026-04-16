package org.irri.iric.portal.genotype.service;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.biojava3.phylo.TreeConstructor;
import org.forester.evoinference.matrix.distance.BasicSymmetricalDistanceMatrix;
import org.forester.io.parsers.PhylogenyParser;
import org.forester.io.parsers.util.ParserUtils;
import org.forester.io.writers.PhylogenyWriter;
import org.forester.phylogeny.Phylogeny;
import org.forester.phylogeny.PhylogenyMethods;
import org.forester.phylogeny.PhylogenyNode;
import org.forester.phylogeny.iterators.PhylogenyNodeIterator;
import org.irri.iric.ds.chado.dao.SnpsAllvarsPosDAO;
import org.irri.iric.ds.chado.domain.Position;
import org.irri.iric.ds.chado.domain.Snps2VarsCountmismatch;
import org.irri.iric.ds.chado.domain.SnpsStringAllvars;
import org.irri.iric.ds.chado.domain.Variety;
import org.irri.iric.ds.chado.domain.VarietyDistance;
import org.irri.iric.ds.chado.domain.impl.Snps2VarsCountmismatchImpl;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.Snps2VarsCountMismatchDAO;
import org.irri.iric.portal.genotype.PhylotreeQueryParams;
import org.irri.iric.portal.genotype.PhylotreeService;
import org.irri.iric.portal.genotype.VariantStringData;
import org.irri.iric.portal.variety.VarietyFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("PhylotreeService")
public class PhylotreeServiceImpl implements PhylotreeService {

	Snps2VarsCountMismatchDAO snpcount2linesService;

	@Autowired
	@Qualifier("VSnpRefposindexDAO")
	SnpsAllvarsPosDAO snpstringallvarsposService;

	@Autowired
	@Qualifier("VarietyFacade")
	VarietyFacade varietyfacade;

	// *************************************
	// Methods for Phylogenetic tree
	// construction
	// *************************************

	@Override
	public Object[] constructPhylotree(PhylotreeQueryParams params, String requestid) {

		AppContext.debug("executing: constructPhylotree(PhylotreeQueryParams params, String requestid)");
		if (params.getMethod().equals(PHYLOTREE_METHOD_TOPN))
			return constructPhylotreeTopN(Double.toString(params.getScale()), params.getGenotype().getsChr(),
					params.getGenotype().getlStart().intValue(), params.getGenotype().getlEnd().intValue(),
					params.getTopN(), requestid, (Set) params.getGenotype().getColVarIds(),
					params.getGenotype().getSnpSet().contains("3kcore"), params.getDataset());

		return null;
	}

	@Override
	public Object[] constructPhylotree(String scale, String chr, int start, int end, String requestid, Set dataset) {
		AppContext.debug(
				"executing: constructPhylotree(String scale, String chr, int start, int end, String requestid,String dataset");

		return constructPhylotreeTopN(scale, chr, start, end, -1, requestid, null, false, dataset);
	}

	@Override
	public Object[] constructPhylotree(VariantStringData data, PhylotreeQueryParams params) {

		// AppContext.debug("executing: constructPhylotree(VariantStringData data,
		// PhylotreeQueryParams params)");
		//
		// AppContext.debug("data=" + data);
		// if(data!=null) AppContext.debug("getListVariantsString()=" +
		// data.getListVariantsString().size());
		//
		// List<Snps2VarsCountmismatch> listmis=new ArrayList();
		// Iterator<SnpsStringAllvars>
		// itallvars1=data.getListVariantsString().iterator();
		// while(itallvars1.hasNext()) {
		// SnpsStringAllvars varsnps1 = itallvars1.next();
		// Map<Position,Character> var1allele2str=varsnps1.getMapPos2Allele2();
		// Iterator<SnpsStringAllvars>
		// itallvars2=data.getListVariantsString().iterator();
		// while(itallvars2.hasNext()) {
		// SnpsStringAllvars varsnps2 = itallvars2.next();
		// if(varsnps1.getVar().intValue()>varsnps2.getVar().intValue()) continue;
		//
		//
		// double mismatch[] =
		// SnpsStringMultiHDF5nRDBMSHybridService.countVarpairMismatch(data.getListPos(),
		// varsnps1.getVarnuc() , varsnps2.getVarnuc(), false, var1allele2str,
		// varsnps2.getMapPos2Allele2() ,
		// data.getMapPos2NonsynAlleles(), data.getSetSnpInExonPos(), new HashSet(),
		// params.getGenotype().isbNonsynPlusSpliceSnps(),
		// params.getGenotype().isbCountMissingAs05());
		// listmis.add( new Snps2VarsCountmismatchImpl(varsnps1.getVar(),
		// varsnps2.getVar(), BigDecimal.valueOf(mismatch[0])));
		// }
		// }

		List listmis = this.calculateDistancePair(data, params);
		// return new Object[] { calculateTree(listmis, data.getListPos().size(),
		// params.getDataset()),listmis};

		return calculateTree(listmis, data.getListPos().size(), params.getDataset());

	}

	@Override
	public Object[] constructPhylotree(List<Snps2VarsCountmismatch> listmis, int n, PhylotreeQueryParams params) {
		return calculateTree(listmis, n, params.getDataset());
	}

	/*
	 * @Override public Object[] constructMDS(String scale, String chr, int start,
	 * int end, String requestid,String dataset) { AppContext.
	 * debug("executing: constructPhylotree(String scale, String chr, int start, int end, String requestid,String dataset"
	 * );
	 * 
	 * return null; //constructMDS(scale, chr, start, end, -1, requestid, null,
	 * false, dataset); }
	 */

	@Override
	public List<Snps2VarsCountmismatch> calculateDistancePair(VariantStringData data, PhylotreeQueryParams params) {
		List<Snps2VarsCountmismatch> listmis = new ArrayList();
		Iterator<SnpsStringAllvars> itallvars1 = data.getListVariantsString().iterator();
		while (itallvars1.hasNext()) {
			SnpsStringAllvars varsnps1 = itallvars1.next();
			Map<Position, Character> var1allele2str = varsnps1.getMapPos2Allele2();
			Iterator<SnpsStringAllvars> itallvars2 = data.getListVariantsString().iterator();
			while (itallvars2.hasNext()) {
				SnpsStringAllvars varsnps2 = itallvars2.next();
				if (varsnps1.getVar().intValue() > varsnps2.getVar().intValue())
					continue;

				double mismatch[] = SnpsStringMultiHDF5nRDBMSHybridService.countVarpairMismatch(data.getListPos(),
						varsnps1.getVarnuc(), varsnps2.getVarnuc(), false, var1allele2str, varsnps2.getMapPos2Allele2(),
						data.getMapPos2NonsynAlleles(), new HashSet(), params.getGenotype().isbNonsynPlusSpliceSnps(),
						params.getGenotype().isbCountMissingAs05());
				listmis.add(new Snps2VarsCountmismatchImpl(varsnps1.getVar(), varsnps2.getVar(),
						BigDecimal.valueOf(mismatch[0])));
			}
		}
		return listmis;
	}

	@Override
	public double[][] constructMDS(Map<BigDecimal, Integer> mapVarid2Row, VariantStringData data,
			PhylotreeQueryParams params) {

		AppContext.debug("executing: constructPhylotree(VariantStringData data, PhylotreeQueryParams params)");

		AppContext.debug("data=" + data);
		if (data != null)
			AppContext.debug("getListVariantsString()=" + data.getListVariantsString().size());

		List listmis = calculateDistancePair(data, params);

		return constructMDS(mapVarid2Row, listmis, "1");
	}

	@Override
	public double[][] constructMDS(Map<BigDecimal, Integer> mapVarid2Row, List<VarietyDistance> listdist,
			String scale) {

		int i = mapVarid2Row.size();
		java.util.Iterator<VarietyDistance> itdist = listdist.iterator();
		double input[][] = new double[i][i];
		int distscale = Integer.parseInt(scale);
		// construct the distance matrix

		AppContext.debug("constructing mds from snpregion for " + i + " vars, " + listdist.size() + " distances");

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

	private Object[] constructPhylotreeTopN(String scale, String chr, int start, int end, int topN, String requestid,
			Set limitVarIds, boolean isCore, Set dataset) {

		// int varids=0;
		// if(limitVarIds!=null) varids=limitVarIds.size();
		//
		// AppContext.debug("constructPhylotreeTopN: scale=" + scale + ", chr=" + chr +
		// ", start="+ start +", end=" + end + ", topN=" + topN + ", limitVarIds=" +
		// varids + ", core=" + isCore);
		// AppContext.startTimer();
		// //return new String[]{};
		//
		// //snpcount2linesService =
		// (Snps2VarsCountMismatchDAO)AppContext.checkBean(snpcount2linesService,
		// "Snps2VarsCountMismatchDAO");
		// snpcount2linesService =
		// (Snps2VarsCountMismatchDAO)AppContext.checkBean(snpcount2linesService,
		// "SnpsString2VarsCountMismatchDAO");
		//
		// //mapVariety2PhyloOrder = null;
		//
		// List<Snps2VarsCountmismatch> mismatches = null;
		//
		// AppContext.startTimer();
		//
		// if(topN>0) {
		//
		// if(limitVarIds!=null && !limitVarIds.isEmpty())
		// mismatches = snpcount2linesService.countMismatch(Integer.valueOf(chr),
		// BigDecimal.valueOf(start), BigDecimal.valueOf(end), topN, limitVarIds);
		// else
		// mismatches = snpcount2linesService.countMismatch(Integer.valueOf(chr),
		// BigDecimal.valueOf(start), BigDecimal.valueOf(end), topN);
		//
		// AppContext.debug(mismatches.size() + " mismatch pairs");
		//
		// // get varieties in topN
		// java.util.Iterator<Snps2VarsCountmismatch> itdist = mismatches.iterator();
		// Set topVars =new java.util.HashSet();
		// while(itdist.hasNext())
		// {
		// Snps2VarsCountmismatch dist3k = itdist.next();
		// topVars.add( dist3k.getVar1());
		// topVars.add( dist3k.getVar2());
		// }
		// mismatches = snpcount2linesService.countMismatch(Integer.valueOf(chr),
		// BigDecimal.valueOf(start), BigDecimal.valueOf(end), topVars);
		// AppContext.resetTimer(" topN distance calc");
		//
		// }
		// else {
		// if(limitVarIds!=null && !limitVarIds.isEmpty())
		// mismatches = snpcount2linesService.countMismatch(Integer.valueOf(chr),
		// BigDecimal.valueOf(start), BigDecimal.valueOf(end), limitVarIds);
		// else
		// mismatches = snpcount2linesService.countMismatch(Integer.valueOf(chr),
		// BigDecimal.valueOf(start), BigDecimal.valueOf(end));
		//
		//
		//
		// AppContext.resetTimer(" all distance calc");
		// }
		//
		//
		//
		// int snps = -1;
		// //List snps = null;
		// snpstringallvarsposService =
		// (SnpsAllvarsPosDAO)AppContext.checkBean(snpstringallvarsposService,
		// "VSnpRefposindexDAO") ;
		// //if(isCore) {
		// if(false) {
		// //snpcoreallvarsposService =
		// (SnpsAllvarsPosDAO)AppContext.checkBean(snpcoreallvarsposService,
		// "MvCoreSnpsDAO") ;
		// //snps = snpcoreallvarsposService.getSNPs(chr, start, end, null ).size();
		//
		//
		// snps = snpstringallvarsposService.getSNPs(chr, start, end,
		// SnpsAllvarsPosDAO.TYPE_3KCORESNP ).size();
		//
		//
		// }
		// else {
		// snps = snpstringallvarsposService.getSNPs(chr, start, end,
		// SnpsAllvarsPosDAO.TYPE_3KALLSNP_HDF5_V2 ).size();
		// //snpallvarsposService =
		// (SnpsAllvarsPosDAO)AppContext.checkBean(snpallvarsposService,
		// "SnpsAllvarsPosDAO") ;
		// // snps = snpallvarsposService.getSNPs(chr, start, end ,null ).size();
		// }
		//
		//
		// AppContext.debug(mismatches.size() + " mismatch pairs, " + snps + " snp
		// pos");
		//
		// if(snps==0) return new Object[] {"", 0,0,null};

		// germplasms
		return calculateTree(new ArrayList(), 0, dataset);

	}

	// private Object[] calculateTree(List<Snps2VarsCountmismatch> mismatches,
	// Integer snps, String dataset) {
	private Object[] calculateTree(List<Snps2VarsCountmismatch> mismatches, Integer snps, Set dataset) {

		java.util.Map<BigDecimal, Integer> mapName2Row = new java.util.HashMap<BigDecimal, Integer>();

		Set<BigDecimal> setWithMismatch = new java.util.TreeSet();

		// java.util.Map<String, BigDecimal> mapPairMismatch = new
		// java.util.HashMap<String, BigDecimal>();
		java.util.Iterator<Snps2VarsCountmismatch> itdist = mismatches.iterator();
		while (itdist.hasNext()) {

			Snps2VarsCountmismatch dist3k = itdist.next();
			// mapPairMismatch.put( dist3k.getVar1() + ":" + dist3k.getVar2(),
			// dist3k.getMismatch());
			setWithMismatch.add(dist3k.getVar1());
			setWithMismatch.add(dist3k.getVar2());
		}

		BasicSymmetricalDistanceMatrix symdistmatrix = new BasicSymmetricalDistanceMatrix(setWithMismatch.size());

		AppContext.debug(setWithMismatch.size() + " unique names with mismatch");

		varietyfacade = (VarietyFacade) AppContext.checkBean(varietyfacade, "VarietyFacade");
		Map<BigDecimal, Variety> mapVarid2Variety = varietyfacade.getMapId2Variety(dataset);

		int i = 0;
		Iterator<BigDecimal> itgerm = setWithMismatch.iterator();
		while (itgerm.hasNext()) {
			BigDecimal varid = itgerm.next();
			mapName2Row.put(varid, i);
			symdistmatrix.setIdentifier(i, "varid_" + varid);
			i++;
		}

		AppContext.debug("symdistmatrix done");

		double distscale = 1.0;

		java.util.Iterator<Snps2VarsCountmismatch> itdist2 = mismatches.iterator();
		while (itdist2.hasNext()) {
			Snps2VarsCountmismatch dist3k = itdist2.next();
			double dist = 0;
			if (snps > dist3k.getMismatch().intValue())
				dist = dist3k.getMismatch().intValue() * distscale / (snps - dist3k.getMismatch().intValue());
			else
				dist = AppContext.getMaxPhylodistance();

			try {

				if (mapName2Row.get(dist3k.getVar1()).equals(mapName2Row.get(dist3k.getVar2()))) {
					// AppContext.debug( mapName2Row.get(dist3k.getVar1()) + ": " +
					// mapVarid2Variety.get(dist3k.getVar1()) + "\t" +
					// mapName2Row.get(dist3k.getVar2()) + ": " +
					// mapVarid2Variety.get(dist3k.getVar2()) + " -- " + dist);
					dist = 0.0;
				}

				symdistmatrix.setValue(mapName2Row.get(dist3k.getVar1()), mapName2Row.get(dist3k.getVar2()), dist);
				symdistmatrix.setValue(mapName2Row.get(dist3k.getVar2()), mapName2Row.get(dist3k.getVar1()), dist);

			} catch (Exception ex) {

				AppContext.debug(
						"NULL EXCEPTION:\t" + dist + "\t" + dist3k.getVar1() + "\t" + mapName2Row.get(dist3k.getVar1())
								+ "\t" + dist3k.getVar2() + "\t" + mapName2Row.get(dist3k.getVar2()) + "\t" + dist);

			}
		}

		AppContext.resetTimer("distmatrix construction");
		AppContext.debug(symdistmatrix.getSize() + " symdistmatrix ready");

		try {

			TreeConstructor tree = new TreeConstructor(symdistmatrix, org.biojava3.phylo.TreeType.NJ,
					org.biojava3.phylo.TreeConstructionAlgorithm.PID,
					// null);
					new org.biojava3.phylo.ProgessListenerStub());

			tree.process();

			AppContext.debug("process done");
			String newick = tree.getNewickString(false, true);
			Phylogeny phy = tree.getP();
			PhylogenyWriter w = new PhylogenyWriter();
			String phyloxml = w.toPhyloXML(phy, 1).toString();

			// String phyloxml="";

			Map<BigDecimal, Variety> mapId2Variety = varietyfacade.getMapId2Variety(dataset);

			// AppContext.debug(newick);
			Iterator<BigDecimal> itgerm2 = setWithMismatch.iterator();
			while (itgerm2.hasNext()) {
				BigDecimal c = itgerm2.next();

				Variety var = mapId2Variety.get(c);
				String subpop = "";
				if (var.getSubpopulation() != null)
					subpop = var.getSubpopulation(); // .replace("/","_").replace(", ","_");
				String irisid = "";
				if (var.getIrisId() != null)
					irisid = var.getIrisId();
				// newick = newick.replace("varid_" + c + ":",(var.getName().split("::")[0] +
				// "/" + irisid + "/" + subpop).replace(" ", "_").replace("'","").replace("(",
				// "").replace(")", "").replace("\"", "") + ":" );
				newick = newick.replace("varid_" + c + ":",
						(var.getName().split("::")[0].replace(", ", "_") + "|" + irisid + "|" + subpop)
								.replace(" ", "_").replace("'", "").replace("(", "").replace(")", "").replace("\"", "")
								+ ":");
				phyloxml = phyloxml.replace(">varid_" + c + "<",
						">" + (var.getName().split("::")[0].replace(", ", "_") + "|" + irisid + "|" + subpop)
								.replace(" ", "_").replace("'", "").replace("(", "").replace(")", "").replace("\"", "")
								+ "<");

			}
			// AppContext.debug(newick);

			AppContext.resetTimer("phylotree construction");

			Map mapVariety2Order = sortByPhylogeny(tree.getP(), dataset);
			// Map mapVariety2Order = this.sortByPhylogeny( phy, dataset );

			// return new String[] {newick, Integer.toString(symdistmatrix.getSize()),
			// Integer.toString( mismatches.size()) };
			return new Object[] { newick, symdistmatrix.getSize(), mismatches.size(), mapVariety2Order, phyloxml };

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;

	}

	static boolean isLessThan(BigDecimal n1, BigDecimal n2) {
		return n1.compareTo(n2) < 0;
	}

	static boolean isGreaterThan(BigDecimal n1, BigDecimal n2) {
		return n1.compareTo(n2) > 0;
	}

	/**
	 * Move the group origkey to newkey, chaneg group assignments, remove orig key
	 * group
	 * 
	 * @param origkey
	 * @param newkey
	 * @param mapGroup2Set
	 * @param mapVar2Group
	 */
	private Map[] changeGroupKey(BigDecimal origkey, BigDecimal newkey, Map<BigDecimal, Set<BigDecimal>> mapGroup2Set,
			Map<BigDecimal, BigDecimal> mapVar2Group) {

		Set origset = mapGroup2Set.get(origkey);
		Iterator<BigDecimal> itSet = origset.iterator();
		while (itSet.hasNext()) {
			BigDecimal var = itSet.next();
			mapVar2Group.put(var, newkey);
		}
		mapVar2Group.put(newkey, newkey);
		origset.add(newkey);
		mapGroup2Set.put(newkey, origset);
		mapGroup2Set.remove(origkey);
		return new Map[] { mapVar2Group, mapGroup2Set };
	}

	/**
	 * merge 2 groups, from srcVarGroup add to destVarGroup, and remove srcVarGroup
	 * 
	 * @param destVarGroup
	 * @param srcVarGroup
	 * @param mapGroup2Set
	 * @param mapVar2Group
	 */
	private Map[] mergeGroups(BigDecimal destVarGroup, BigDecimal srcVarGroup,
			Map<BigDecimal, Set<BigDecimal>> mapGroup2Set, Map<BigDecimal, BigDecimal> mapVar2Group) {

		Set origset = mapGroup2Set.get(srcVarGroup);
		Iterator<BigDecimal> itSet = origset.iterator();
		while (itSet.hasNext()) {
			BigDecimal var = itSet.next();
			mapVar2Group.put(var, destVarGroup);
		}

		Set minvarset = mapGroup2Set.get(destVarGroup);
		minvarset.addAll(origset);
		mapGroup2Set.remove(srcVarGroup);
		mapGroup2Set.put(destVarGroup, minvarset);
		return new Map[] { mapVar2Group, mapGroup2Set };

	}

	/**
	 * 
	 * @param var1
	 * @param var2
	 * @param mapVar2Group
	 * @param mapGroup2Varset
	 */
	private double linkUngroupedToGroup(BigDecimal var1, BigDecimal var2, Map<BigDecimal, BigDecimal> mapVar2Group,
			Map<BigDecimal, Set<BigDecimal>> mapGroup2Varset, Map<BigDecimal, Map<BigDecimal, Double>> mapdistances) {

		Set groupset = mapGroup2Varset.get(mapVar2Group.get(var2));
		Iterator<BigDecimal> itGroupmems = groupset.iterator();
		double total = 0;
		while (itGroupmems.hasNext()) {
			BigDecimal varmax = itGroupmems.next();
			BigDecimal varmin = var1;
			if (isGreaterThan(var1, varmax)) {
				varmin = varmax;
				varmax = var1;
			}
			// total += mapdistances.get(varmin).[varmax.intValue()-1];
			total += mapdistances.get(varmin).get(varmax);
		}
		return total / groupset.size();
	}

	private double linkgroupedTogroup(BigDecimal var1, BigDecimal var2, Map<BigDecimal, BigDecimal> mapVar2Group,
			Map<BigDecimal, Set<BigDecimal>> mapGroup2Varset, Map<BigDecimal, Map<BigDecimal, Double>> mapdistances) {

		Set groupset1 = mapGroup2Varset.get(mapVar2Group.get(var1));
		Set groupset2 = mapGroup2Varset.get(mapVar2Group.get(var2));
		Iterator<BigDecimal> itGroup1mems = groupset1.iterator();
		double total = 0;
		int ncount = 0;
		while (itGroup1mems.hasNext()) {
			BigDecimal vargroup1 = itGroup1mems.next();

			Iterator<BigDecimal> itGroup2mems = groupset2.iterator();
			while (itGroup2mems.hasNext()) {
				BigDecimal vargroup2 = itGroup2mems.next();
				if (isGreaterThan(vargroup1, vargroup2))
					continue;
				// total += mapdistances.get(vargroup1)[vargroup2.intValue()-1];
				total += mapdistances.get(vargroup1).get(vargroup2);
				ncount++;
			}
		}
		return total / ncount;
	}

	//
	//
	//
	// //public Object[] constructPhylotreeTopNNew(String scale, String chr, int
	// start, int end, int topN, String requestid) {
	// private Object[] constructPhylotreeMindist(String scale, String chr, int
	// start, int end, String mindist) {
	//
	//
	// SNPsStringData snpstrdata = getSNPsStringData(Integer.valueOf(chr),
	// BigDecimal.valueOf(start), BigDecimal.valueOf(end), null );
	// if(snpstrdata==null) return new String[] {"", "0","0"};
	//
	// double minCount = 0;
	// if(mindist!=null) {
	// try {
	// minCount = Double.valueOf(mindist);
	// AppContext.debug("grouping pairs with distance below " + minCount + " pairs
	// (" + mindist +")");
	// } catch(Exception ex) {
	// ex.printStackTrace();
	// }
	// }
	// minCount=1;
	//
	// // use minVar in Varset as group key
	// Map<BigDecimal,Set<BigDecimal>> mapGroup2Varset = new HashMap();
	//
	// // groups refer to group key
	// Map<BigDecimal,BigDecimal> mapVar2Group = new HashMap();
	//
	// Map mapVarid2Snpsstr = snpstrdata.getMapVarid2Snpsstr();
	//
	// Set setNonsynIdx=new HashSet();
	// //Set sortedVarieties = new TreeSet(new SnpsStringAllvarsImplSorter());
	//
	// //Set sortedVarietiesPairsDesc = new TreeSet(new
	// SnpsString2VarsImplSorter());
	// List sortedVarietiesPairsDesc = new ArrayList();
	//
	// Map<BigDecimal,Map<BigDecimal,Double>> mapVar1ToVar2Dist = new HashMap();
	//
	// Iterator<BigDecimal> itVar1 = mapVarid2Snpsstr.keySet().iterator();
	// while(itVar1.hasNext()) {
	// BigDecimal var1 = itVar1.next();
	// String snpstr1 = (String)mapVarid2Snpsstr.get(var1);
	// Map getMapVar12SnpsAllele2str =
	// (Map)snpstrdata.getMapVarid2SnpsAllele2str().get(var1);
	//
	// //double var2distarray[] = new double[var1.intValue()];
	// Map<BigDecimal,Double> var2distmap = new HashMap();
	//
	// Iterator<BigDecimal> itVar2 = mapVarid2Snpsstr.keySet().iterator();
	// while(itVar2.hasNext()) {
	// BigDecimal var2 = itVar2.next();
	// if( isGreaterThan(var1, var2)) continue;
	//
	//
	// String snpstr2 = (String)mapVarid2Snpsstr.get(var2);
	// Set varNonsynIdx = new HashSet();
	//
	// double misCount = countVarpairMismatch(snpstr1, snpstr2, false,
	// getMapVar12SnpsAllele2str,
	// (Map)snpstrdata.getMapVarid2SnpsAllele2str().get(var2),
	// (Map)snpstrdata.getMapIdx2NonsynAlleles(),
	// snpstrdata.getSetSnpInExonTableIdx(), varNonsynIdx, isNonsynOnly );
	//
	// sortedVarietiesPairsDesc.add( new Snps2VarsCountmismatchImpl( var1, var2,
	// BigDecimal.valueOf(misCount)) );
	// //var2distarray[var2.intValue()-1] = misCount;
	// var2distmap.put(var2, misCount);
	//
	// }
	// mapVar1ToVar2Dist.put(var1, var2distmap);
	// }
	//
	// Collections.sort(sortedVarietiesPairsDesc, new SnpsString2VarsImplSorter());
	//
	// // filter sorted pairs
	//
	// Iterator<Snps2VarsCountmismatch> itPairs =
	// sortedVarietiesPairsDesc.iterator();
	// //Set setVarietyDontGroup = new HashSet();
	// Set setVarietyNotGrouped = new HashSet();
	// Set setVarietyGrouped = new HashSet();
	//
	// while(itPairs.hasNext()) {
	// Snps2VarsCountmismatch pair = itPairs.next();
	// //AppContext.debug(pair.getMismatch().toString());
	//
	// if(pair.getMismatch().doubleValue()>=minCount) {
	// //mismatches.add( pair );
	// //setVarietyDontGroup.add( pair.getVar1() );
	// //setVarietyDontGroup.add( pair.getVar2() );
	// setVarietyNotGrouped.add( pair.getVar1().intValue() );
	// setVarietyNotGrouped.add( pair.getVar2().intValue() );
	//
	// //} else if( setVarietyDontGroup.contains(pair.getVar1()) ||
	// setVarietyDontGroup.contains(pair.getVar2()) ) {}
	// }
	// else {
	// // both nodes not in paired, sould be grouped
	// //setVarietyNotGrouped.remove( pair.getVar1() );
	// //setVarietyNotGrouped.remove( pair.getVar2() );
	// setVarietyGrouped.add( pair.getVar1().intValue());
	// setVarietyGrouped.add( pair.getVar2().intValue());
	//
	// // create groups for mincount
	// BigDecimal minVar = pair.getVar1();
	// BigDecimal maxVar = pair.getVar2();
	// if( isGreaterThan(minVar,maxVar)) {
	// minVar = pair.getVar2();
	// maxVar = pair.getVar1();
	// }
	//
	// // check if var1 or var2 in the groups
	// BigDecimal minVarGroup = mapVar2Group.get(minVar);
	// if(minVarGroup==null) {
	// // minvar has no group
	// BigDecimal maxVarGroup = mapVar2Group.get(maxVar);
	// if(maxVarGroup==null) {
	// //maxvar has no group
	//
	// // make new groups
	// Set newgroup = new HashSet();
	// newgroup.add(minVar);
	// newgroup.add(maxVar);
	// mapVar2Group.put(minVar ,minVar);
	// mapVar2Group.put(maxVar ,minVar);
	// mapGroup2Varset.put(minVar, newgroup);
	//
	// } else {
	// // add minvar to max's group
	// // check nodename
	// if( isLessThan(maxVarGroup, minVar) ) {
	// mapGroup2Varset.get(maxVarGroup).add(minVar);
	// } else {
	// Map[] groupmaps = changeGroupKey(maxVarGroup, minVar, mapGroup2Varset,
	// mapVar2Group);
	// mapVar2Group = groupmaps[0];
	// mapGroup2Varset = groupmaps[1];
	// }
	// }
	// } else {
	// BigDecimal maxVarGroup = mapVar2Group.get(maxVar);
	// if(maxVarGroup==null) {
	// //maxvar has no group
	// // add maxvar to minvars group
	// mapGroup2Varset.get( minVarGroup ).add( maxVar );
	// mapVar2Group.put(maxVar, minVarGroup );
	// } else {
	// // merge min's and max's groups
	// if(isLessThan(minVarGroup,maxVarGroup)) {
	// // merge to minvar's group
	// Map[] groupmaps = mergeGroups( minVarGroup, maxVarGroup, mapGroup2Varset,
	// mapVar2Group);
	// mapVar2Group = groupmaps[0];
	// mapGroup2Varset = groupmaps[1];
	// } else if(isGreaterThan(minVarGroup, maxVarGroup) )
	// {
	// // merge to maxvar's group
	// Map[] groupmaps = mergeGroups( maxVarGroup, minVarGroup, mapGroup2Varset,
	// mapVar2Group);
	// mapVar2Group = groupmaps[0];
	// mapGroup2Varset = groupmaps[1];
	//
	// } // else they are from the same group, do nothing
	// }
	// }
	//
	// }
	// }
	//
	// AppContext.debug( mapGroup2Varset.size() + " groups, " +
	// setVarietyGrouped.size() + " varieties grouped, " +
	// setVarietyNotGrouped.size() + " not grouped");
	//
	// Set grouponlySet = new HashSet();
	// grouponlySet.addAll( setVarietyGrouped);
	// grouponlySet.removeAll( setVarietyNotGrouped );
	// Set notgrouponlySet = new HashSet();
	// notgrouponlySet.addAll( setVarietyNotGrouped);
	// notgrouponlySet.removeAll( setVarietyGrouped );
	// Set bothSet = new HashSet();
	// bothSet.addAll( setVarietyNotGrouped);
	// bothSet.retainAll( setVarietyGrouped );
	// AppContext.debug(grouponlySet.size() + " group only, " +
	// notgrouponlySet.size() + " ungroup only, " + bothSet.size() + " both" );
	//
	// // linked groups to ungroupd pairs
	//
	// AppContext.debug(sortedVarietiesPairsDesc.size() + " distance pairs");
	//
	// Set setUGToUGVarietyPairs = new HashSet();
	// Set setGroupToGroupPairs = new HashSet();
	// Set setVarietyToGroupPairs = new HashSet();
	// itPairs = sortedVarietiesPairsDesc.iterator();
	//
	// long paircnt = 0;
	// int paircnt2 = 0;
	// while(itPairs.hasNext()) {
	// Snps2VarsCountmismatch pair = itPairs.next();
	// BigDecimal var1 = pair.getVar1();
	// BigDecimal var2 = pair.getVar2();
	// int ivar1 = var1.intValue();
	// int ivar2 = var2.intValue();
	//
	//
	// //if(pair.getMismatch().doubleValue()>=minCount) continue;
	//
	// paircnt++;
	// if(paircnt> 1000) {
	// paircnt =0;
	// AppContext.debug("pair " + paircnt2 + "k pairs");
	// paircnt2 ++;
	// }
	//
	// if(notgrouponlySet.contains(ivar1) && notgrouponlySet.contains(ivar2)) {
	// setUGToUGVarietyPairs.add( new Snps2VarsCountmismatchImpl( var1,
	// mapVar2Group.get(var2) , pair.getMismatch()) );
	// }
	// else if(grouponlySet.contains(ivar1) && notgrouponlySet.contains(ivar2)) {
	// // link var2 to group of var1, using average distance between var2 and all
	// group of var1
	// double avgdist = linkUngroupedToGroup(var2, var1, mapVar2Group,
	// mapGroup2Varset , mapVar1ToVar2Dist);
	// setVarietyToGroupPairs.add( new Snps2VarsCountmismatchImpl( var2,
	// mapVar2Group.get(var1) , BigDecimal.valueOf(avgdist)) );
	// }
	// else if(grouponlySet.contains(ivar2) && notgrouponlySet.contains(ivar1)) {
	// // link var1 to group of var2, using average distance between var1 and all
	// group of var2
	// double avgdist = linkUngroupedToGroup(var1, var2, mapVar2Group,
	// mapGroup2Varset , mapVar1ToVar2Dist);
	// setVarietyToGroupPairs.add( new Snps2VarsCountmismatchImpl( var1,
	// mapVar2Group.get(var2) , BigDecimal.valueOf(avgdist)) );
	// }
	// else if(bothSet.contains(ivar1) && notgrouponlySet.contains(ivar2)) {
	// // link var2 to group of var1, using average distance between var2 and all
	// group of var1
	// double avgdist = linkUngroupedToGroup(var2, var1, mapVar2Group,
	// mapGroup2Varset , mapVar1ToVar2Dist);
	// setVarietyToGroupPairs.add( new Snps2VarsCountmismatchImpl( var2,
	// mapVar2Group.get(var1) , BigDecimal.valueOf(
	// (avgdist+pair.getMismatch().doubleValue())/2 )) );
	// }
	// else if(bothSet.contains(ivar2) && notgrouponlySet.contains(ivar1)) {
	// // link var1 to group of var2, using average distance between var1 and all
	// group of var2
	// double avgdist = linkUngroupedToGroup(var1, var2, mapVar2Group,
	// mapGroup2Varset , mapVar1ToVar2Dist);
	// setVarietyToGroupPairs.add( new Snps2VarsCountmismatchImpl( var1,
	// mapVar2Group.get(var2) , BigDecimal.valueOf(
	// (avgdist+pair.getMismatch().doubleValue())/2 )) );
	// }
	// else if( grouponlySet.contains(ivar1) && grouponlySet.contains(ivar2) ) {
	// throw new RuntimeException("cannot be both members of grouponly because their
	// groups should have been merged earlier");
	// //double avgdist = linkgroupedTogroup(var1, var2, mapVar2Group,
	// mapGroup2Varset , mapVar1ToVar2Dist);
	// //setGroupToGroupPairs.add( new Snps2VarsCountmismatchImpl(
	// mapVar2Group.get(var1), mapVar2Group.get(var2) , BigDecimal.valueOf(avgdist))
	// );
	// }
	// else if( (grouponlySet.contains(ivar1) || bothSet.contains(ivar1)) &&
	// (grouponlySet.contains(ivar2) || bothSet.contains(ivar2)) ) {
	// //throw new RuntimeException("cannot be both members of grouponly because
	// their groups should have been merged earlier");
	// double avgdist = linkgroupedTogroup(var1, var2, mapVar2Group, mapGroup2Varset
	// , mapVar1ToVar2Dist);
	// setGroupToGroupPairs.add( new Snps2VarsCountmismatchImpl(
	// mapVar2Group.get(var1), mapVar2Group.get(var2) , BigDecimal.valueOf(avgdist))
	// );
	// }
	// else throw new RuntimeException("cannot be both members of bothSet because
	// their groups should have been merged earlier");
	// }
	//
	//
	// AppContext.debug(mapGroup2Varset.size() + " groups created for tree" );
	//
	// varietyfacade = (VarietyFacade)AppContext.checkBean( varietyfacade,
	// "VarietyFacade" );
	//
	// // name groups (using minvar and subpop count)
	// Map<BigDecimal,Variety> mapVarid2Var = varietyfacade.getMapId2Variety();
	// Map<BigDecimal,String> mapGroup2Name = new HashMap();
	// Iterator<BigDecimal> itGroup = mapGroup2Varset.keySet().iterator();
	// while(itGroup.hasNext()) {
	// BigDecimal group = itGroup.next();
	// Iterator<BigDecimal> itVarset = mapGroup2Varset.get(group).iterator();
	// Map<String, Integer> mapGroupSubpopCount = new HashMap();
	// while(itVarset.hasNext()) {
	// BigDecimal varsetmember = itVarset.next();
	// String subpop = mapVarid2Var.get(varsetmember).getSubpopulation();
	// Integer subcount = mapGroupSubpopCount.get(subpop);
	// if(subcount==null) {
	// Integer count = 1;
	// mapGroupSubpopCount.put(subpop, count);
	// } else {
	// mapGroupSubpopCount.remove(subpop);
	// mapGroupSubpopCount.put(subpop, subcount+1) ;
	// }
	// }
	//
	//
	//
	// // create name
	// Iterator<String> itSub = new
	// TreeSet(mapGroupSubpopCount.keySet()).iterator();
	// StringBuffer buff = new StringBuffer();
	// buff.append("GROUP " + mapVarid2Var.get(group).getName() + " :" );
	// while(itSub.hasNext()) {
	// String subpop = itSub.next();
	// buff.append(subpop +" " + mapGroupSubpopCount.get(subpop));
	// if(itSub.hasNext()) buff.append(":");
	// }
	// mapGroup2Name.put(group,buff.toString());
	//
	// AppContext.debug("group " + group + ": " + mapGroup2Name.get(group) + " has
	// size " + mapGroup2Varset.get(group).size() );
	// }
	//
	// AppContext.startTimer();
	//
	// int snps = snpstrdata.getStrRef().length();
	// if(snps==0) return new String[] {"", "0","0"};
	//
	// //germplasms
	//
	//
	//
	// AppContext.debug(setUGToUGVarietyPairs.size() + " mismatch var-var pairs");
	// AppContext.debug(setVarietyToGroupPairs.size() + " mismatch var-group
	// pairs");
	// AppContext.debug(setGroupToGroupPairs.size() + " mismatch group-group
	// pairs");
	//
	// List<Snps2VarsCountmismatch> mismatches = new ArrayList();
	// mismatches.addAll(setUGToUGVarietyPairs);
	// mismatches.addAll(setVarietyToGroupPairs );
	// mismatches.addAll(setGroupToGroupPairs );
	//
	//
	// Set setNodes = new TreeSet();
	//
	// //AppContext.debug(grouponlySet.size() + " group only, " +
	// notgrouponlySet.size() + " ungroup only, " + bothSet.size() + " both" );
	// //setNodes.addAll( setVarietyDontGroup );
	// setNodes.addAll( notgrouponlySet );
	// setNodes.addAll( mapGroup2Varset.keySet() );
	//
	//
	// BasicSymmetricalDistanceMatrix symdistmatrix = new
	// BasicSymmetricalDistanceMatrix( setNodes.size() );
	//
	// varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade,
	// "VarietyFacade");
	// Map<BigDecimal,Variety> mapVarid2Variety = varietyfacade.getMapId2Variety();
	//
	// Map<BigDecimal,Integer> mapVarid2Row = new HashMap();
	//
	// int i=0;
	// Iterator<BigDecimal> itgerm = setNodes.iterator();
	// while(itgerm.hasNext()) {
	// BigDecimal varid = itgerm.next();
	// mapVarid2Row.put(varid , i);
	// symdistmatrix.setIdentifier( i, "varid_" + varid );
	// i++;
	// }
	//
	// AppContext.debug("symdistmatrix done");
	//
	// double distscale = 1.0;
	//
	// java.util.Iterator<Snps2VarsCountmismatch> itdist2 = mismatches.iterator();
	// while(itdist2.hasNext())
	// {
	// Snps2VarsCountmismatch dist3k = itdist2.next();
	// double dist =0 ;
	// if(snps > dist3k.getMismatch().intValue())
	// dist = dist3k.getMismatch().intValue()*distscale /( snps -
	// dist3k.getMismatch().intValue() );
	// else
	// dist = AppContext.getMaxPhylodistance();
	//
	// try {
	//
	// if( mapVarid2Row.get(dist3k.getVar1()).equals(
	// mapVarid2Row.get(dist3k.getVar2()) ) ) {
	// //AppContext.debug( mapName2Row.get(dist3k.getVar1()) + ": " +
	// mapVarid2Variety.get(dist3k.getVar1()) + "\t" +
	// mapName2Row.get(dist3k.getVar2()) + ": " +
	// mapVarid2Variety.get(dist3k.getVar2()) + " -- " + dist);
	// dist = 0.0;
	// }
	//
	// symdistmatrix.setValue( mapVarid2Row.get(dist3k.getVar1()) ,
	// mapVarid2Row.get(dist3k.getVar2()) , dist );
	// symdistmatrix.setValue( mapVarid2Row.get(dist3k.getVar2()) ,
	// mapVarid2Row.get(dist3k.getVar1()), dist );
	//
	// } catch(Exception ex) {
	//
	// AppContext.debug( "NULL EXCEPTION:\t" + dist + "\t" + dist3k.getVar1() + "\t"
	// + mapVarid2Row.get(dist3k.getVar1()) + "\t" + dist3k.getVar2() + "\t" +
	// mapVarid2Row.get(dist3k.getVar2()) + "\t" + dist );
	//
	// }
	// }
	//
	// AppContext.debug(symdistmatrix.getSize() + " symdistmatrix ready");
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
	// AppContext.debug("process done");
	// String newick = tree.getNewickString(false, true);
	//
	//
	// Map<BigDecimal,Variety> mapId2Variety = varietyfacade.getMapId2Variety();
	//
	// //AppContext.debug(newick);
	// Iterator<BigDecimal> itgerm2 = setNodes.iterator();
	// while(itgerm2.hasNext()) {
	// BigDecimal c = itgerm2.next();
	//
	// Variety var = mapId2Variety.get(c);
	//
	// String nodename = mapGroup2Name.get(var.getVarietyId());
	// if(nodename==null) {
	// String subpop = "";
	// if( var.getSubpopulation()!=null) subpop = var.getSubpopulation();
	// //.replace("/","_").replace(", ","_");
	// String irisid = "";
	// if( var.getIrisId()!=null) irisid=var.getIrisId();
	// //newick = newick.replace("varid_" + c + ":",(var.getName().split("::")[0] +
	// "/" + irisid + "/" + subpop).replace(" ", "_").replace("'","").replace("(",
	// "").replace(")", "").replace("\"", "") + ":" );
	// newick = newick.replace("varid_" + c +
	// ":",(var.getName().split("::")[0].replace(", ","_") + "|" + irisid + "|" +
	// subpop).replace(" ", "_").replace("'","").replace("(", "").replace(")",
	// "").replace("\"", "") + ":" );
	// } else {
	// newick = newick.replace("varid_" + c + ":", nodename.replace("::",
	// "_").replace(":","_") + ":");
	// }
	//
	// }
	// //AppContext.debug(newick);
	//
	// return new Object[] {newick, Integer.toString(symdistmatrix.getSize()),
	// Integer.toString( mismatches.size()) , mapGroup2Varset, mapGroup2Name};
	//
	//
	// } catch(Exception ex)
	// {
	// ex.printStackTrace();
	// }
	//
	// return null;
	//
	// }
	//
	//
	// public Object[] constructPhylotreeMindist1(String scale, String chr, int
	// start, int end, String mindist) {
	//
	// List<Snps2VarsCountmismatch> mismatches = new ArrayList();
	// SNPsStringData snpstrdata = getSNPsStringData(Integer.valueOf(chr),
	// BigDecimal.valueOf(start), BigDecimal.valueOf(end), null );
	// if(snpstrdata==null) return new String[] {"", "0","0"};
	//
	// double minCount = 0;
	// if(mindist!=null) {
	// try {
	// minCount = Double.valueOf(mindist);
	// AppContext.debug("grouping pairs with distance below " + minCount + " pairs
	// (" + mindist +")");
	// } catch(Exception ex) {
	// ex.printStackTrace();
	// }
	// }
	// minCount=1;
	//
	// // use minVar in Varset as group key
	// Map<BigDecimal,Set<BigDecimal>> mapGroup2Varset = new HashMap();
	//
	// // groups refer to group key
	// Map<BigDecimal,BigDecimal> mapVar2Group = new HashMap();
	//
	// Map mapVarid2Snpsstr = snpstrdata.getMapVarid2Snpsstr();
	//
	// Set setNonsynIdx=new HashSet();
	// //Set sortedVarieties = new TreeSet(new SnpsStringAllvarsImplSorter());
	//
	// //Set sortedVarietiesPairsDesc = new TreeSet(new
	// SnpsString2VarsImplSorter());
	// List sortedVarietiesPairsDesc = new ArrayList();
	//
	// Iterator<BigDecimal> itVar1 = mapVarid2Snpsstr.keySet().iterator();
	// while(itVar1.hasNext()) {
	// BigDecimal var1 = itVar1.next();
	// String snpstr1 = (String)mapVarid2Snpsstr.get(var1);
	// Map getMapVar12SnpsAllele2str =
	// (Map)snpstrdata.getMapVarid2SnpsAllele2str().get(var1);
	//
	// Iterator<BigDecimal> itVar2 = mapVarid2Snpsstr.keySet().iterator();
	// while(itVar2.hasNext()) {
	// BigDecimal var2 = itVar2.next();
	// if( isGreaterThan(var1, var2)) continue;
	//
	// String snpstr2 = (String)mapVarid2Snpsstr.get(var2);
	// Set varNonsynIdx = new HashSet();
	//
	// double misCount = countVarpairMismatch(snpstr1, snpstr2, false,
	// getMapVar12SnpsAllele2str,
	// (Map)snpstrdata.getMapVarid2SnpsAllele2str().get(var2),
	// (Map)snpstrdata.getMapIdx2NonsynAlleles(),
	// snpstrdata.getSetSnpInExonTableIdx(), varNonsynIdx, isNonsynOnly );
	//
	// sortedVarietiesPairsDesc.add( new Snps2VarsCountmismatchImpl( var1, var2,
	// BigDecimal.valueOf(misCount)) );
	//
	// }
	// }
	//
	// Collections.sort(sortedVarietiesPairsDesc, new SnpsString2VarsImplSorter());
	//
	// // filter sorted pairs
	//
	// Iterator<Snps2VarsCountmismatch> itPairs =
	// sortedVarietiesPairsDesc.iterator();
	// //Set setVarietyDontGroup = new HashSet();
	// Set setVarietyNotGrouped = new HashSet();
	// Set setVarietyGrouped = new HashSet();
	//
	// while(itPairs.hasNext()) {
	// Snps2VarsCountmismatch pair = itPairs.next();
	// //AppContext.debug(pair.getMismatch().toString());
	//
	// if(pair.getMismatch().intValue()>=minCount) {
	// mismatches.add( pair );
	// //setVarietyDontGroup.add( pair.getVar1() );
	// //setVarietyDontGroup.add( pair.getVar2() );
	// setVarietyNotGrouped.add( pair.getVar1() );
	// setVarietyNotGrouped.add( pair.getVar2() );
	//
	// //} else if( setVarietyDontGroup.contains(pair.getVar1()) ||
	// setVarietyDontGroup.contains(pair.getVar2()) ) {}
	// }
	// else {
	// // both nodes not in paired, sould be grouped
	// //setVarietyNotGrouped.remove( pair.getVar1() );
	// //setVarietyNotGrouped.remove( pair.getVar2() );
	// setVarietyGrouped.add( pair.getVar1());
	// setVarietyGrouped.add( pair.getVar2());
	//
	// // create groups for mincount
	// BigDecimal minVar = pair.getVar1();
	// BigDecimal maxVar = pair.getVar2();
	// if( isGreaterThan(minVar,maxVar)) {
	// minVar = pair.getVar2();
	// maxVar = pair.getVar1();
	// }
	//
	// // check if var1 or var2 in the groups
	// BigDecimal minVarGroup = mapVar2Group.get(minVar);
	// if(minVarGroup==null) {
	// // minvar has no group
	// BigDecimal maxVarGroup = mapVar2Group.get(maxVar);
	// if(maxVarGroup==null) {
	// //maxvar has no group
	//
	// // make new groups
	// Set newgroup = new HashSet();
	// newgroup.add(minVar);
	// newgroup.add(maxVar);
	// mapVar2Group.put(minVar ,minVar);
	// mapVar2Group.put(maxVar ,minVar);
	// mapGroup2Varset.put(minVar, newgroup);
	//
	// } else {
	// // add minvar to max's group
	// // check nodename
	// if( isLessThan(maxVarGroup, minVar) ) {
	// mapGroup2Varset.get(maxVarGroup).add(minVar);
	// } else {
	// changeGroupKey(maxVarGroup, minVar, mapGroup2Varset, mapVar2Group);
	// }
	// }
	// } else {
	// BigDecimal maxVarGroup = mapVar2Group.get(maxVar);
	// if(maxVarGroup==null) {
	// //maxvar has no group
	// // add maxvar to minvars group
	// mapGroup2Varset.get( minVarGroup ).add( maxVar );
	// mapVar2Group.put(maxVar, minVarGroup );
	// } else {
	// // merge min's and max's groups
	// if(isLessThan(minVarGroup,maxVarGroup)) {
	// // merge to minvar's group
	// mergeGroups( minVarGroup, maxVarGroup, mapGroup2Varset, mapVar2Group);
	// } else if(isGreaterThan(minVarGroup, maxVarGroup) )
	// {
	// // merge to maxvar's group
	// mergeGroups( maxVarGroup, minVarGroup, mapGroup2Varset, mapVar2Group);
	// } // else they are from the same group, do nothing
	// }
	// }
	//
	// }
	// }
	//
	// AppContext.debug(mapGroup2Varset.keySet().size() + " groups, " +
	// setVarietyGrouped.size() + " varieties grouped, " +
	// setVarietyNotGrouped.size() + " not grouped");
	//
	// Set grouponlySet = new HashSet();
	// grouponlySet.addAll( setVarietyGrouped);
	// grouponlySet.removeAll( setVarietyNotGrouped );
	// Set notgrouponlySet = new HashSet();
	// notgrouponlySet.addAll( setVarietyNotGrouped);
	// notgrouponlySet.removeAll( setVarietyGrouped );
	// Set bothSet = new HashSet();
	// bothSet.addAll( setVarietyNotGrouped);
	// bothSet.retainAll( setVarietyGrouped );
	// AppContext.debug(grouponlySet.size() + " group only, " +
	// notgrouponlySet.size() + " ungroup only, " + bothSet.size() + " both" );
	// // linked groups to ungroupd pairs
	//
	// Set setVarietyToGroupPairs = new HashSet();
	// itPairs = sortedVarietiesPairsDesc.iterator();
	// while(itPairs.hasNext()) {
	// Snps2VarsCountmismatch pair = itPairs.next();
	// BigDecimal var1 = pair.getVar1();
	// BigDecimal var2 = pair.getVar2();
	//
	// if(pair.getMismatch().intValue()>=minCount) {
	//
	// // if var1 in group, link node to group
	// if(mapVar2Group.containsKey(var1) && !mapVar2Group.containsKey(var2)) {
	// // link var2 to group of var1
	// setVarietyToGroupPairs.add( new Snps2VarsCountmismatchImpl( var2,
	// mapVar2Group.get(var1) , pair.getMismatch() ) );
	// }
	// else if(!mapVar2Group.containsKey(var1) && mapVar2Group.containsKey(var2)) {
	// // link var1 to group of var2
	// setVarietyToGroupPairs.add( new Snps2VarsCountmismatchImpl( var1,
	// mapVar2Group.get(var2) , pair.getMismatch() ) );
	//
	// } else if(mapVar2Group.containsKey(var1) && mapVar2Group.containsKey(var2)) {
	// // link the groups of var1 and var2
	// setVarietyToGroupPairs.add( new Snps2VarsCountmismatchImpl(
	// mapVar2Group.get(var2), mapVar2Group.get(var2) , pair.getMismatch() ) );
	// }
	//
	//
	// /*
	// if(!setVarietyDontGroup.contains(pair.getVar1()) &&
	// setVarietyDontGroup.contains(var2))
	// {
	// // var1 in groups
	// // fnd in group, and join in pair
	// BigDecimal var1group = mapVar2Group.get( var1 );
	// if(var1group==null) throw new RuntimeException("var1 supposed to be in
	// groups");
	// setVarietyToGroupPairs.add( new Snps2VarsCountmismatchImpl( var2, var1group ,
	// pair.getMismatch() ) );
	// }
	// else if(!setVarietyDontGroup.contains( var2 ) &&
	// setVarietyDontGroup.contains(var1) )
	// {
	// // var1 in groups
	// // fnd in group, and join in pair
	// BigDecimal var2group = mapVar2Group.get( var2 );
	// if(var2group==null) throw new RuntimeException("var2 supposed to be in
	// groups");
	// setVarietyToGroupPairs.add( new Snps2VarsCountmismatchImpl( var1, var2group ,
	// pair.getMismatch() ) );
	// }
	// //else throw new RuntimeException("Cannot be, 1 var should be grouped, and 1
	// not grouped in pair");
	// * */
	//
	// }
	// }
	//
	//
	//
	//
	//// while(itVar1.hasNext()) {
	//// BigDecimal var1 = itVar1.next();
	//// String snpstr1 = (String)mapVarid2Snpsstr.get(var1);
	//// Map getMapVar12SnpsAllele2str =
	// (Map)snpstrdata.getMapVarid2SnpsAllele2str().get(var1);
	////
	//// Iterator<BigDecimal> itVar2 = mapVarid2Snpsstr.keySet().iterator();
	//// while(itVar2.hasNext()) {
	//// BigDecimal var2 = itVar2.next();
	//// if( isGreaterThan(var1, var2) || var1.equals(var2) ) continue;
	////
	//// String snpstr2 = (String)mapVarid2Snpsstr.get(var2);
	// //
	//// Set varNonsynIdx = new HashSet();
	////
	//// double misCount = countVarpairMismatch(snpstr1, snpstr2, false,
	// getMapVar12SnpsAllele2str,
	// (Map)snpstrdata.getMapVarid2SnpsAllele2str().get(var2),
	//// (Map)snpstrdata.getMapIdx2NonsynAlleles(),
	// snpstrdata.getSetSnpInExonTableIdx(), varNonsynIdx, isNonsynOnly );
	// //
	//// if(misCount>=minCount) {
	//// mismatches.add( new Snps2VarsCountmismatchImpl( var1, var2,
	// BigDecimal.valueOf(misCount)) );
	//// } else {
	////
	////
	//// // create groups for mincount
	//// BigDecimal minVar = var1;
	//// BigDecimal maxVar = var1;
	//// if( isGreaterThan(var1,var2)) {
	//// minVar = var2;
	//// maxVar = var1;
	//// }
	////
	//// // check if var1 or var2 in the groups
	//// BigDecimal minVarGroup = mapVar2Group.get(minVar);
	//// if(minVarGroup==null) {
	//// // minvar has no group
	//// BigDecimal maxVarGroup = mapVar2Group.get(maxVar);
	//// if(maxVarGroup==null) {
	//// //maxvar has no group
	////
	//// // make new groups
	//// Set newgroup = new HashSet();
	//// newgroup.add(minVar);
	//// newgroup.add(maxVar);
	//// mapVar2Group.put(minVar ,minVar);
	//// mapVar2Group.put(maxVar ,minVar);
	//// mapGroup2Varset.put(minVar, newgroup);
	////
	//// } else {
	//// // add minvar to max's group
	//// // check nodename
	//// if( isLessThan(maxVarGroup, minVar) ) {
	//// mapGroup2Varset.get(maxVarGroup).add(minVar);
	//// } else {
	//// changeGroupKey(maxVarGroup, minVar, mapGroup2Varset, mapVar2Group);
	//// }
	//// }
	//// } else {
	//// BigDecimal maxVarGroup = mapVar2Group.get(maxVar);
	//// if(maxVarGroup==null) {
	//// //maxvar has no group
	//// // add maxvar to minvars group
	//// mapGroup2Varset.get( minVarGroup ).add( maxVar );
	//// mapVar2Group.put(maxVar, minVarGroup );
	//// } else {
	//// // merge min's and max's groups
	//// if(isLessThan(minVarGroup,maxVarGroup)) {
	//// // merge to minvar's group
	//// mergeGroups( minVarGroup, maxVarGroup, mapGroup2Varset, mapVar2Group);
	//// } else if(isGreaterThan(minVarGroup, maxVarGroup) )
	//// {
	//// // merge to maxvar's group
	//// mergeGroups( maxVarGroup, minVarGroup, mapGroup2Varset, mapVar2Group);
	//// } // else they are from the same group, do nothing
	//// }
	//// }
	////
	//// }
	////
	//// }
	//// }
	//
	// AppContext.debug(mapGroup2Varset.size() + " groups created for tree" );
	//
	// varietyfacade = (VarietyFacade)AppContext.checkBean( varietyfacade,
	// "VarietyFacade" );
	//
	// // name groups (using minvar and subpop count)
	// Map<BigDecimal,Variety> mapVarid2Var = varietyfacade.getMapId2Variety();
	// Map<BigDecimal,String> mapGroup2Name = new HashMap();
	// Iterator<BigDecimal> itGroup = mapGroup2Varset.keySet().iterator();
	// while(itGroup.hasNext()) {
	// BigDecimal group = itGroup.next();
	// Iterator<BigDecimal> itVarset = mapGroup2Varset.get(group).iterator();
	// Map<String, Integer> mapGroupSubpopCount = new HashMap();
	// while(itVarset.hasNext()) {
	// BigDecimal varsetmember = itVarset.next();
	// String subpop = mapVarid2Var.get(varsetmember).getSubpopulation();
	// Integer subcount = mapGroupSubpopCount.get(subpop);
	// if(subcount==null) {
	// Integer count = 1;
	// mapGroupSubpopCount.put(subpop, count);
	// } else {
	// mapGroupSubpopCount.remove(subpop);
	// mapGroupSubpopCount.put(subpop, subcount+1) ;
	// }
	// }
	//
	//
	//
	// // create name
	// Iterator<String> itSub = new
	// TreeSet(mapGroupSubpopCount.keySet()).iterator();
	// StringBuffer buff = new StringBuffer();
	// buff.append("GROUP " + mapVarid2Var.get(group).getName() + " :" );
	// while(itSub.hasNext()) {
	// String subpop = itSub.next();
	// buff.append(subpop +" " + mapGroupSubpopCount.get(subpop));
	// if(itSub.hasNext()) buff.append(":");
	// }
	// mapGroup2Name.put(group,buff.toString());
	//
	// AppContext.debug("group " + group + ": " + mapGroup2Name.get(group) + " has
	// size " + mapGroup2Varset.get(group).size() );
	// }
	//
	//
	//
	// AppContext.startTimer();
	//
	// //
	//// if(topN>0) {
	////
	//// if(limitVarIds!=null && !limitVarIds.isEmpty())
	//// mismatches = snpcount2linesService.countMismatch(Integer.valueOf(chr),
	// BigDecimal.valueOf(start), BigDecimal.valueOf(end), topN, limitVarIds);
	//// else
	//// mismatches = snpcount2linesService.countMismatch(Integer.valueOf(chr),
	// BigDecimal.valueOf(start), BigDecimal.valueOf(end), topN);
	////
	//// AppContext.debug(mismatches.size() + " mismatch pairs");
	////
	//// // get varieties in topN
	//// java.util.Iterator<Snps2VarsCountmismatch> itdist = mismatches.iterator();
	//// Set topVars =new java.util.HashSet();
	//// while(itdist.hasNext())
	//// {
	//// Snps2VarsCountmismatch dist3k = itdist.next();
	//// topVars.add( dist3k.getVar1());
	//// topVars.add( dist3k.getVar2());
	//// }
	//// mismatches = snpcount2linesService.countMismatch(Integer.valueOf(chr),
	// BigDecimal.valueOf(start), BigDecimal.valueOf(end), topVars);
	//// AppContext.resetTimer(" topN distance calc");
	////
	//// }
	//// else {
	//// if(limitVarIds!=null && !limitVarIds.isEmpty())
	//// mismatches = snpcount2linesService.countMismatch(Integer.valueOf(chr),
	// BigDecimal.valueOf(start), BigDecimal.valueOf(end), limitVarIds);
	//// else
	//// mismatches = snpcount2linesService.countMismatch(Integer.valueOf(chr),
	// BigDecimal.valueOf(start), BigDecimal.valueOf(end));
	////
	////
	////
	//// AppContext.resetTimer(" all distance calc");
	//// }
	//
	//// int snps = -1;
	//// //List snps = null;
	//// snpstringallvarsposService =
	// (SnpsAllvarsPosDAO)AppContext.checkBean(snpstringallvarsposService,
	// "SnpcoreRefposindexDAO") ;
	//// if(isCore) {
	//// //snpcoreallvarsposService =
	// (SnpsAllvarsPosDAO)AppContext.checkBean(snpcoreallvarsposService,
	// "MvCoreSnpsDAO") ;
	//// //snps = snpcoreallvarsposService.getSNPs(chr, start, end, null ).size();
	////
	//// snps = snpstringallvarsposService.getSNPs(chr, start, end,
	// SnpcoreRefposindexDAO.TYPE_3KCORESNP ).size();
	////
	////
	//// }
	//// else {
	//// snps = snpstringallvarsposService.getSNPs(chr, start, end,
	// SnpcoreRefposindexDAO.TYPE_3KALLSNP ).size();
	//// //snpallvarsposService =
	// (SnpsAllvarsPosDAO)AppContext.checkBean(snpallvarsposService,
	// "SnpsAllvarsPosDAO") ;
	//// // snps = snpallvarsposService.getSNPs(chr, start, end ,null ).size();
	//// }
	//
	// int snps = snpstrdata.getStrRef().length();
	// if(snps==0) return new String[] {"", "0","0"};
	//
	// //germplasms
	//
	//
	// AppContext.debug(mismatches.size() + " mismatch var pairs");
	// AppContext.debug(setVarietyToGroupPairs.size() + " mismatch var-group
	// pairs");
	// mismatches.addAll(setVarietyToGroupPairs );
	//
	//
	//// java.util.Map<BigDecimal, Integer> mapName2Row = new
	// java.util.HashMap<BigDecimal, Integer>();
	// //
	//// Set<BigDecimal> setWithMismatch = new java.util.TreeSet();
	// //
	//// List listMismatchGrouped = new ArrayList();
	// //
	//// //java.util.Map<String, BigDecimal> mapPairMismatch = new
	// java.util.HashMap<String, BigDecimal>();
	//// java.util.Iterator<Snps2VarsCountmismatch> itdist = mismatches.iterator();
	//// while(itdist.hasNext())
	//// {
	////
	//// Snps2VarsCountmismatch dist3k = itdist.next();
	//// //mapPairMismatch.put( dist3k.getVar1() + ":" + dist3k.getVar2(),
	// dist3k.getMismatch());
	////
	//// BigDecimal node1 = dist3k.getVar1();
	//// BigDecimal node2 = dist3k.getVar2();
	//// BigDecimal var1group = mapVar2Group.get(node1);
	//// BigDecimal var2group = mapVar2Group.get(node2);
	//// if(var1group!=null) node1 = var1group;
	//// if(var2group!=null) node2 = var2group;
	////
	//// if(var1group!=null || var2group!=null) {
	//// listMismatchGrouped.add( new Snps2VarsCountmismatchImpl(node1,node2,
	// dist3k.getMismatch()) );
	//// } else
	//// listMismatchGrouped.add(dist3k );
	////
	//// setWithMismatch.add( node1 );
	//// setWithMismatch.add( node2 );
	////
	//// //setWithMismatch.add( dist3k.getVar1());
	//// //setWithMismatch.add( dist3k.getVar2());
	//// }
	// //
	//// mismatches = listMismatchGrouped;
	// //
	//
	// Set setNodes = new TreeSet();
	//
	// //setNodes.addAll( setVarietyDontGroup );
	// setNodes.addAll( setVarietyNotGrouped );
	// setNodes.addAll( mapGroup2Varset.keySet() );
	//
	// AppContext.debug(setVarietyNotGrouped.size() + " varieties not grouped, " +
	// setVarietyGrouped.size() + " grouped," + mapGroup2Varset.keySet().size() + "
	// groups, " + setNodes.size() + " treenodes");
	//
	// BasicSymmetricalDistanceMatrix symdistmatrix = new
	// BasicSymmetricalDistanceMatrix( setNodes.size() );
	//
	// varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade,
	// "VarietyFacade");
	// Map<BigDecimal,Variety> mapVarid2Variety = varietyfacade.getMapId2Variety();
	//
	// Map<BigDecimal,Integer> mapVarid2Row = new HashMap();
	//
	// int i=0;
	// Iterator<BigDecimal> itgerm = setNodes.iterator();
	// while(itgerm.hasNext()) {
	// BigDecimal varid = itgerm.next();
	// mapVarid2Row.put(varid , i);
	// symdistmatrix.setIdentifier( i, "varid_" + varid );
	// i++;
	// }
	//
	// AppContext.debug("symdistmatrix done");
	//
	// double distscale = 1.0;
	//
	// java.util.Iterator<Snps2VarsCountmismatch> itdist2 = mismatches.iterator();
	// while(itdist2.hasNext())
	// {
	// Snps2VarsCountmismatch dist3k = itdist2.next();
	// double dist =0 ;
	// if(snps > dist3k.getMismatch().intValue())
	// dist = dist3k.getMismatch().intValue()*distscale /( snps -
	// dist3k.getMismatch().intValue() );
	// else
	// dist = AppContext.getMaxPhylodistance();
	//
	// try {
	//
	// if( mapVarid2Row.get(dist3k.getVar1()).equals(
	// mapVarid2Row.get(dist3k.getVar2()) ) ) {
	// //AppContext.debug( mapName2Row.get(dist3k.getVar1()) + ": " +
	// mapVarid2Variety.get(dist3k.getVar1()) + "\t" +
	// mapName2Row.get(dist3k.getVar2()) + ": " +
	// mapVarid2Variety.get(dist3k.getVar2()) + " -- " + dist);
	// dist = 0.0;
	// }
	//
	// symdistmatrix.setValue( mapVarid2Row.get(dist3k.getVar1()) ,
	// mapVarid2Row.get(dist3k.getVar2()) , dist );
	// symdistmatrix.setValue( mapVarid2Row.get(dist3k.getVar2()) ,
	// mapVarid2Row.get(dist3k.getVar1()), dist );
	//
	// } catch(Exception ex) {
	//
	// AppContext.debug( "NULL EXCEPTION:\t" + dist + "\t" + dist3k.getVar1() + "\t"
	// + mapVarid2Row.get(dist3k.getVar1()) + "\t" + dist3k.getVar2() + "\t" +
	// mapVarid2Row.get(dist3k.getVar2()) + "\t" + dist );
	//
	// }
	// }
	//
	// AppContext.debug(symdistmatrix.getSize() + " symdistmatrix ready");
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
	// AppContext.debug("process done");
	// String newick = tree.getNewickString(false, true);
	//
	//
	// Map<BigDecimal,Variety> mapId2Variety = varietyfacade.getMapId2Variety();
	//
	// //AppContext.debug(newick);
	// Iterator<BigDecimal> itgerm2 = setNodes.iterator();
	// while(itgerm2.hasNext()) {
	// BigDecimal c = itgerm2.next();
	//
	// Variety var = mapId2Variety.get(c);
	//
	// String nodename = mapGroup2Name.get(var.getVarietyId());
	// if(nodename==null) {
	// String subpop = "";
	// if( var.getSubpopulation()!=null) subpop = var.getSubpopulation();
	// //.replace("/","_").replace(", ","_");
	// String irisid = "";
	// if( var.getIrisId()!=null) irisid=var.getIrisId();
	// //newick = newick.replace("varid_" + c + ":",(var.getName().split("::")[0] +
	// "/" + irisid + "/" + subpop).replace(" ", "_").replace("'","").replace("(",
	// "").replace(")", "").replace("\"", "") + ":" );
	// newick = newick.replace("varid_" + c +
	// ":",(var.getName().split("::")[0].replace(", ","_") + "|" + irisid + "|" +
	// subpop).replace(" ", "_").replace("'","").replace("(", "").replace(")",
	// "").replace("\"", "") + ":" );
	// } else {
	// newick = newick.replace("varid_" + c + ":", nodename.replace("::",
	// "_").replace(":","_") + ":");
	// }
	//
	// }
	// //AppContext.debug(newick);
	//
	// return new Object[] {newick, Integer.toString(symdistmatrix.getSize()),
	// Integer.toString( mismatches.size()) , mapGroup2Varset, mapGroup2Name};
	//
	//
	// } catch(Exception ex)
	// {
	// ex.printStackTrace();
	// }
	//
	// return null;
	//
	// }
	//

	private Map<BigDecimal, Integer> sortByPhylogeny(Phylogeny phy, Set dataset) {
		varietyfacade = (VarietyFacade) AppContext.checkBean(varietyfacade, "VarietyFacade");
		Map<String, Variety> varname2var = varietyfacade.getMapVarname2Variety(dataset);
		Map<String, Variety> irisid2var = varietyfacade.getIrisId2Variety();

		Map mapVariety2PhyloOrder = new HashMap<BigDecimal, Integer>();

		int leafcount = 0;
		for (PhylogenyNodeIterator it = phy.iteratorPostorder(); it.hasNext();) {
			PhylogenyNode node = it.next();
			if (node.isExternal()) {

				mapVariety2PhyloOrder.put(BigDecimal.valueOf(Long.valueOf(node.getName().replace("varid_", ""))),
						leafcount);
				leafcount++;
				/*
				 * String[] nodenames = node.getName().split("\\|"); if(nodenames.length!=3)
				 * AppContext.debug("Invalid nodename " + node.getName());
				 * 
				 * if(nodenames.length>1 && !nodenames[1].isEmpty() ){ Variety varNode =
				 * irisid2var.get(nodenames[1].replace("_"," ").toUpperCase() );
				 * //varietyfacade.getGermplasmByIrisId(nodenames[1].replace("_"," "));
				 * if(varNode!=null) { mapVariety2PhyloOrder.put( varNode.getVarietyId() ,
				 * leafcount); leafcount++; } else { AppContext.debug("cant resolve irisid " +
				 * nodenames[1]); } } else if(!nodenames[0].isEmpty()) { Variety varNode =
				 * varname2var.get(nodenames[0].replace("_"," ").replace("//",""));
				 * if(varNode!=null) { mapVariety2PhyloOrder.put( varNode.getVarietyId() ,
				 * leafcount); leafcount++; } else {
				 * AppContext.debug("cant resolve variety name " + nodenames[0] ); } }
				 */
			}
		}

		return mapVariety2PhyloOrder;
	}

	@Override
	public Map<BigDecimal, Integer> orderVarietiesFromPhylotree(String tmpfile, Set dataset) {
		return orderVarietiesFromPhylotree(tmpfile, null, dataset);
	}

	@Override
	public Map<BigDecimal, Integer> orderVarietiesFromPhylotree(String tmpfile, String newick, Set dataset) {

		// if(mapVariety2PhyloOrder!=null) return mapVariety2PhyloOrder;

		Map<BigDecimal, Integer> mapVariety2PhyloOrder = new HashMap<BigDecimal, Integer>();

		String treefilename = AppContext.getTempDir() + tmpfile + ".newick";
		AppContext.debug("Openning file:" + treefilename);
		File treefile = new File(treefilename);
		PhylogenyParser parser = null;
		try {
			Phylogeny[] phys = null;
			if (newick == null) {
				parser = ParserUtils.createParserDependingOnFileType(treefile, true);
				// parser = ParserUtils.createParserDependingOnSuffix( treefilename, false );
				phys = PhylogenyMethods.readPhylogenies(parser, treefile);
			} else {
				// parser = ParserUtils.

				// PhylogenyMethods.

			}

			AppContext.debug("Newick postorder listing:");
			Map<String, Variety> varname2var = varietyfacade.getMapVarname2Variety(dataset);
			Map<String, Variety> irisid2var = varietyfacade.getIrisId2Variety();

			int leafcount = 0;
			for (int iphy = 0; iphy < phys.length; iphy++) {

				mapVariety2PhyloOrder.putAll(sortByPhylogeny(phys[iphy], dataset));

				// for(PhylogenyNodeIterator it = phys[iphy].iteratorPostorder(); it.hasNext();
				// ) {
				// PhylogenyNode node = it.next();
				// if(node.isExternal()) {
				//
				// String[] nodenames = node.getName().split("\\|");
				// if(nodenames.length!=3) AppContext.debug("Invalid nodename " +
				// node.getName());
				//
				// if(nodenames.length>1 && !nodenames[1].isEmpty() ){
				// Variety varNode = irisid2var.get(nodenames[1].replace("_"," ").toUpperCase()
				// ); //varietyfacade.getGermplasmByIrisId(nodenames[1].replace("_"," "));
				// if(varNode!=null) {
				// mapVariety2PhyloOrder.put( varNode.getVarietyId() , leafcount);
				// leafcount++;
				// } else
				// {
				// AppContext.debug("cant resolve irisid " + nodenames[1]);
				// }
				// }
				// else if(!nodenames[0].isEmpty())
				// {
				// Variety varNode = varname2var.get(nodenames[0].replace("_","
				// ").replace("//",""));
				// if(varNode!=null) {
				// mapVariety2PhyloOrder.put( varNode.getVarietyId() , leafcount);
				// leafcount++;
				// } else
				// {
				// AppContext.debug("cant resolve variety name " + nodenames[0] );
				// }
				// }
				// }
				// }
			}

			// Display of the tree(s) with Archaeopteryx.
			// Archaeopteryx.createApplication( phys );

		}

		catch (final IOException e) {
			e.printStackTrace();
			AppContext.debug(e.getMessage());
			throw new RuntimeException("newick parse error");
		}

		AppContext.debug("mapVariety2PhyloOrder.size()=" + mapVariety2PhyloOrder.size());

		return mapVariety2PhyloOrder;

	}

}
