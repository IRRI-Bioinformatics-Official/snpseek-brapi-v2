package org.irri.iric.portal.genotype.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.irri.iric.ds.chado.dao.SnpsEffectDAO;
import org.irri.iric.ds.chado.domain.Locus;
import org.irri.iric.ds.chado.domain.MultiReferencePosition;
import org.irri.iric.ds.chado.domain.Position;
import org.irri.iric.ds.chado.domain.SnpsAllvarsPos;
import org.irri.iric.ds.chado.domain.SnpsStringAllvars;
import org.irri.iric.ds.chado.domain.StockSample;
import org.irri.iric.ds.chado.domain.impl.MultiReferencePositionImplAllelePvalue;
import org.irri.iric.ds.chado.domain.model.VIndelRefposindex;
import org.irri.iric.ds.chado.domain.model.VSnpRefposindex;
import org.irri.iric.ds.chado.domain.model.VSnpeff;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.irri.iric.portal.genomics.GenomicsFacade;
import org.irri.iric.portal.genotype.GenotypeQueryParams;
import org.irri.iric.portal.genotype.VariantSnpsStringData;
import org.irri.iric.portal.genotype.VariantStringData;
import org.irri.iric.portal.genotype.VariantTableArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * display variants as variant table (variety x position)
 * 
 * @author LMansueto
 *
 */
public class VariantAlignmentTableArraysImpl implements VariantTableArray {

	@Autowired
	@Qualifier("ListItems")
	private ListItemsDAO lisitemdao;
	@Autowired
	private GenomicsFacade genomics;
	@Autowired
	private SnpsEffectDAO snpseffDAO;

	// message
	private String message;
	// variety names
	private String varnames[];
	// allele table
	private String allelestring[][];
	// mismatches
	private Double varmismatch[];
	// variety IDs
	private Long varids[];

	private String dataset[];

	// all reference genome allelles
	private String allrefalleles[][];
	// all reference genome names
	private String allrefallelesnames[];
	// position array
	private Position posarr[];
	// reference allele (NP)
	private String refnuc[];
	// contig array
	private String contigarr[];

	private String queryallele[];
	private Double allrefallelesmatch[];

	private VariantStringData data;
	private String chr;

	private String geneAnnots[];

	// @Autowired
	// private ListItemsDAO listitemsdao;

	public VariantAlignmentTableArraysImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VariantAlignmentTableArraysImpl(VariantAlignmentTableArraysImpl copyfrom) {
		super();
		this.lisitemdao = copyfrom.lisitemdao;
		this.message = copyfrom.message;
		this.varnames = copyfrom.varnames;
		this.allelestring = copyfrom.allelestring;
		this.varmismatch = copyfrom.varmismatch;
		this.varids = copyfrom.varids;
		this.posarr = copyfrom.posarr;
		this.refnuc = copyfrom.refnuc;
		this.data = copyfrom.data;
		this.contigarr = copyfrom.contigarr;
		this.queryallele = copyfrom.queryallele;
		this.allrefallelesmatch = copyfrom.allrefallelesmatch;
		this.dataset = copyfrom.dataset;
	}
	//
	// public VariantAlignmentTableArraysImpl(ListItemsDAO lisitemdao,
	// String message, String[] varnames, String[][] allelestring,
	// Double[] varmismatch, Long[] varids, Position[] posarr,
	// String[] refnuc, VariantStringData data) {
	// super();
	// this.lisitemdao = lisitemdao;
	// this.message = message;
	// this.varnames = varnames;
	// this.allelestring = allelestring;
	// this.varmismatch = varmismatch;
	// this.varids = varids;
	// this.posarr = posarr;
	// this.refnuc = refnuc;
	// this.data = data;
	// }

	@Override
	public void setVariantStringData(VariantStringData data, GenotypeQueryParams params) {
		setVariantStringData(data, params, null);
	}

	@Override
	public void setVariantStringData(VariantStringData data, GenotypeQueryParams params, List listCDS) {

		this.data = data;

		boolean cdsonly = listCDS != null && (params.isbNonsynSnps() || params.isbNonsynPlusSpliceSnps())
				&& data.isNipponbareReference();

		List listPosarr = new ArrayList();
		List listRefnuc = new ArrayList();
		List listContigarr = new ArrayList();
		Set<Integer> setRemoveColumns = new HashSet();

		List<SnpsAllvarsPos> snpsposlist = data.getListPos();
		Iterator<SnpsAllvarsPos> itPos = snpsposlist.iterator();
		int origposcount = 0;
		int poscount = 0;
		Set<SnpsAllvarsPos> setInclude = new TreeSet();
		while (itPos.hasNext()) {
			SnpsAllvarsPos posnuc = itPos.next();
			// posarr[poscount] = posnuc.getPos().doubleValue(); //.longValue();

			boolean includepos = true;
			if (cdsonly) {
				Iterator<Locus> itLoc = listCDS.iterator();
				// includepos = false;
				while (itLoc.hasNext()) {
					Locus loc = itLoc.next();
					if ((loc.getChr() != null && posnuc.getChr() != null && posnuc.getChr() == loc.getChr())
							|| posnuc.getContig().equals(loc.getContig())) {
						if (loc.getFmin() <= posnuc.getPosition().intValue()
								&& loc.getFmax() >= posnuc.getPosition().intValue()) {
							includepos = true;
							break;
						}
					}
				}
			}

			if (includepos) {
				setInclude.add(posnuc);
				listPosarr.add(posnuc);
				listRefnuc.add(posnuc.getRefnuc());
				listContigarr.add(posnuc.getContig());
			} else
				setRemoveColumns.add(origposcount);

			/*
			 * posarr[poscount] = posnuc; //posnuc.getPos(); //.doubleValue();
			 * //.longValue(); refnuc[poscount] = posnuc.getRefnuc();
			 * 
			 * if(contigarr!=null) { contigarr[poscount]= posnuc.getContig(); }
			 * 
			 */
			origposcount++;
		}

		posarr = (Position[]) listPosarr.toArray(new Position[listPosarr.size()]);
		refnuc = (String[]) listRefnuc.toArray(new String[listRefnuc.size()]);
		if (params.isLocusList() || params.isSNPList()) {
			contigarr = (String[]) listContigarr.toArray(new String[listContigarr.size()]);
		}
		poscount = posarr.length;

		/*
		 * posarr = new Position[snpsposlist.size()]; refnuc = new
		 * String[snpsposlist.size()]; if(params.isLocusList() || params.isSNPList()) {
		 * contigarr = new String[snpsposlist.size()]; }
		 */

		// data.getIndelstringdata().getMapPos2NonsynAlleles()
		Map<Position, Set<Character>> mapPos2Nonsysn = data.getMapPos2NonsynAlleles();
		Map mapPos2NonsysnSNPOnly = new TreeMap(mapPos2Nonsysn);

		lisitemdao = (ListItemsDAO) AppContext.checkBean(lisitemdao, "ListItems");

		// Map<BigDecimal, Variety> mapVarid2Variety =
		// lisitemdao.getMapId2Variety(params.getDataset());
		Map<String, Map<BigDecimal, StockSample>> mapDs = lisitemdao.getMapId2Sample(params.getDataset());
		// AppContext.debug("DISPLAY:" + params.getDataset() + ":" +
		// mapVarid2Variety.size());

		List listTable = data.getListVariantsString();
		varnames = new String[listTable.size()];
		varids = new Long[listTable.size()];
		dataset = new String[listTable.size()];
		varmismatch = new Double[listTable.size()];
		allelestring = new String[listTable.size()][poscount]; // [data.getListPos().size()];

		Iterator<SnpsStringAllvars> itSnpstring = listTable.iterator();
		AppContext.debug("List Table: " + listTable.size());

		int varcount = 0;

		BigDecimal newIdx;
		// TODO: NULL MULTIREFERENCE
		while (itSnpstring.hasNext()) {
			SnpsStringAllvars snpstr = itSnpstring.next();

			Map<BigDecimal, StockSample> mapVarid2Variety = mapDs.get(snpstr.getDataset());

			if (mapVarid2Variety != null) {
				varmismatch[varcount] = snpstr.getMismatch().doubleValue();

				newIdx = snpstr.getVar();
				// TODO: 2nd reference Genome

//				if (snpstr.getDataset().equals("3k-MH63v15")) {
//					Map<Integer, BigDecimal> idxSample = data.getSnpstringdata().getIdxSample();
//					newIdx = idxSample.get(snpstr.getVar().intValue());
//				}

				StockSample ss = mapVarid2Variety.get(newIdx);
				if (ss != null) {
					// AppContext.debug(snpstr.getDataset() + " DS: " + snpstr.getVar());
					varnames[varcount] = ss.getName() + "::[" + ss.getAssay() + "]";
					varids[varcount] = snpstr.getVar().longValue();
					dataset[varcount] = snpstr.getDataset();

					// AppContext.debug( snpstr.getClass() + " " + snpstr.toString() );

					if (params.isbIndel()) {
						// IndelStringHDF5nRDBMSHybridService.isfirstrow=0;
						if (params.isbAlignIndels())
							allelestring[varcount] = IndelStringHDF5nRDBMSHybridService
									.createVarietyStringAligned(snpstr, data, varnames[varcount]);
						else
							allelestring[varcount] = IndelStringHDF5nRDBMSHybridService.createVarietyString(snpstr,
									data);
					} else if (params.isbSNP()) {
						allelestring[varcount] = IndelStringHDF5nRDBMSHybridService.createVarietyString(snpstr, data);
					}

					if (cdsonly && !setRemoveColumns.isEmpty()) {
						List<String> listAllstr = new ArrayList();
						for (int idx = 0; idx < allelestring[varcount].length; idx++) {
							if (!setRemoveColumns.contains(idx)) {
								listAllstr.add(allelestring[varcount][idx]);
								SnpsAllvarsPos idxpos = snpsposlist.get(idx);
								if (!allelestring[varcount][idx].isEmpty()
										&& !idxpos.getRefnuc().equals(allelestring[varcount][idx])) {
									Set setnonsysns = mapPos2Nonsysn.get(idxpos);
									if (setnonsysns == null)
										setnonsysns = new HashSet();
									setnonsysns.add(allelestring[varcount][idx].charAt(0));
								}
							}
						}
						allelestring[varcount] = (String[]) listAllstr.toArray(new String[listAllstr.size()]);
					}
				}
			}

			varcount++;
		}

		AppContext.debug("VAR COUNT: " + varcount);
		AppContext.debug("CDS: " + listCDS);
		AppContext.debug("removed columns: " + setRemoveColumns);

		AppContext.debug(varcount + " varieties aligned into table");

		message = data.getMessage();

		AppContext.debug(message);
		AppContext.debug("creating table: isNPB=" + data.isNipponbareReference());

		if (!data.isNipponbareReference()) {
			// change coordinates, remove columns with no conversion

			Map mapPos2Newpos = data.getMapMSU7Pos2ConvertedPos();

			// int convertedTableSize = mapPos2Newpos.size();
			int convertedTableSize = data.getListPos().size();

			// contigarr = new String[convertedTableSize];
			itPos = snpsposlist.iterator();

			int newref_poscount = 0;
			Position newref_posarr[] = new Position[convertedTableSize];
			String newref_refnuc[] = new String[convertedTableSize];

			Set setRemoveIdx = new HashSet();

			int idxPos = 0;
			while (itPos.hasNext()) {
				SnpsAllvarsPos posnuc = itPos.next();

				MultiReferencePosition newpos = (MultiReferencePosition) mapPos2Newpos.get(posnuc.getPosition());

				if (newpos != null) {
					// posarr[poscount] = posnuc.getPos().doubleValue(); //.longValue();
					// newref_posarr[newref_poscount] = newpos.getPosition() ;
					newref_posarr[newref_poscount] = newpos; // .getPosition() ;
					newref_refnuc[newref_poscount] = newpos.getRefnuc(); // posnuc.getRefnuc();
					// contigarr[newref_poscount] = newpos.getToContig();
					newref_poscount++;
				} else {
					setRemoveIdx.add(idxPos);
					newref_posarr[newref_poscount] = null;
					newref_refnuc[newref_poscount] = null;
					// contigarr[newref_poscount] = null;
					newref_poscount++;
				}

				idxPos++;
			}

			Double newref_varmismatch[] = new Double[listTable.size()];
			String newref_allelestring[][] = new String[listTable.size()][convertedTableSize];

			itSnpstring = listTable.iterator();
			varcount = 0;
			while (itSnpstring.hasNext()) {
				SnpsStringAllvars snpstr = itSnpstring.next();
				newref_varmismatch[varcount] = varmismatch[varcount];

				newref_poscount = 0;
				for (int idx = 0; idx < snpsposlist.size(); idx++) {

					if (setRemoveIdx.contains(idx)) {
						if (!refnuc.equals(allelestring[varcount][idx])) {
							newref_varmismatch[varcount] = newref_varmismatch[varcount] - 1;
						}
						newref_allelestring[varcount][newref_poscount] = "";
					} else
						newref_allelestring[varcount][newref_poscount] = allelestring[varcount][idx];

					newref_poscount++;
				}
				varcount++;
			}

			varmismatch = newref_varmismatch;
			allelestring = newref_allelestring;
			posarr = newref_posarr;
			refnuc = newref_refnuc;
		}

		Map<String, Map<BigDecimal, MultiReferencePosition>> mapOrg2Posmap = data.getMapOrg2MSU7Pos2ConvertedPos();
		if (mapOrg2Posmap != null && !mapOrg2Posmap.isEmpty()) {
			List listOrgsAllrefs = new ArrayList();
			listOrgsAllrefs.addAll(mapOrg2Posmap.keySet());
			allrefalleles = new String[listOrgsAllrefs.size()][data.getListPos().size()];
			allrefallelesnames = new String[listOrgsAllrefs.size()];

			// if(data.getMapReference2Mismatch()!=null) {
			if (params.isbAlleleFilter()) {
				allrefallelesmatch = new Double[listOrgsAllrefs.size()];
				queryallele = new String[params.getPoslist().size()];
				Iterator<SnpsAllvarsPos> itPosA = params.getPoslist().iterator();
				int ipos = 0;
				while (itPosA.hasNext()) {
					MultiReferencePositionImplAllelePvalue posa = (MultiReferencePositionImplAllelePvalue) itPosA
							.next();
					queryallele[ipos] = posa.getAllele();
					ipos++;
				}

			}
			;

			for (int iref = 0; iref < listOrgsAllrefs.size(); iref++) {
				Map<BigDecimal, MultiReferencePosition> mapPos = mapOrg2Posmap.get(listOrgsAllrefs.get(iref));
				allrefallelesnames[iref] = (String) listOrgsAllrefs.get(iref);
				itPos = snpsposlist.iterator();
				int newref_poscount = 0;
				while (itPos.hasNext()) {
					SnpsAllvarsPos posnuc = itPos.next();
					MultiReferencePosition newpos = (MultiReferencePosition) mapPos.get(posnuc.getPosition());
					if (newpos != null) {
						allrefalleles[iref][newref_poscount] = newpos.getRefnuc(); // posnuc.getRefnuc();
					} else {
						allrefalleles[iref][newref_poscount] = null;
					}
					newref_poscount++;
				}

				if (cdsonly && !setRemoveColumns.isEmpty()) {
					List<String> listAllstr = new ArrayList();
					for (int idx = 0; idx < allrefalleles[iref].length; idx++) {
						if (!setRemoveColumns.contains(idx))
							listAllstr.add(allrefalleles[iref][idx]);
					}
					allrefalleles[iref] = (String[]) listAllstr.toArray(new String[listAllstr.size()]);
					newref_poscount = allrefalleles.length;
				}

				if (data.getMapReference2Mismatch() != null) {
					allrefallelesmatch[iref] = data.getMapReference2Mismatch().get(listOrgsAllrefs.get(iref));
				}
			}

		} else {
			if (params.isbAlleleFilter()) {
				queryallele = new String[params.getPoslist().size()];
				Iterator<SnpsAllvarsPos> itPosA = params.getPoslist().iterator();
				int ipos = 0;
				while (itPosA.hasNext()) {
					MultiReferencePositionImplAllelePvalue posa = (MultiReferencePositionImplAllelePvalue) itPosA
							.next();
					queryallele[ipos] = posa.getAllele();
					ipos++;
				}
				allrefallelesmatch = new Double[1];
				allrefallelesmatch[0] = data.getMapReference2Mismatch().get(params.getOrganism().getName());
			}
			;
		}
	}

	public String[] getAllrefallelesnames() {
		return allrefallelesnames;
	}

	public String[][] getAllrefalleles() {
		return allrefalleles;
	}

	public void setAllrefalleles(String[][] allrefalleles) {
		this.allrefalleles = allrefalleles;
	}

	public String getMessage() {

		return message;
	}

	public void setMessage(String message) {

		this.message = message;
	}

	private ListItemsDAO getLisitemdao() {
		return lisitemdao;
	}

	public void setLisitemdao(ListItemsDAO lisitemdao) {
		this.lisitemdao = lisitemdao;
	}

	private String[] getVarnames() {
		return varnames;
	}

	public void setVarnames(String[] varnames) {
		this.varnames = varnames;
	}

	private String[][] getAllelestring() {
		return allelestring;
	}

	public void setAllelestring(String[][] allelestring) {
		this.allelestring = allelestring;
	}

	private Long[] getVarids() {
		return varids;
	}

	public void setVarids(Long[] varids) {
		this.varids = varids;
	}

	/*
	 * private BigDecimal[] getPosarr() { return posarr; }
	 * 
	 * public void setPosarr(BigDecimal[] posarr) { this.posarr = posarr; }
	 */

	private Position[] getPosarr() {
		return posarr;
	}

	public void setPosarr(Position[] posarr) {
		this.posarr = posarr;
	}

	private String[] getRefnuc() {
		return refnuc;
	}

	public void setRefnuc(String[] refnuc) {
		this.refnuc = refnuc;
	}

	public void setVarmismatch(Double[] varmismatch) {
		this.varmismatch = varmismatch;
	}

	public String[] getQueryallele() {
		return queryallele;
	}

	public Double[] getAllrefallelesmatch() {
		return allrefallelesmatch;
	}

	public Position[] getPositionNPB() {

		List listpos = data.getListPos();
		Position[] posNPB = new Position[listpos.size()];
		Iterator<SnpsAllvarsPos> itPos = listpos.iterator();
		int icount = 0;
		while (itPos.hasNext()) {
			posNPB[icount] = itPos.next(); // .getPos();
			icount++;
		}
		return posNPB;
	}

	public String[] getReferenceNPB() {
		List listpos = data.getListPos();
		String[] refNPB = new String[listpos.size()];
		Iterator<SnpsAllvarsPos> itPos = listpos.iterator();
		int icount = 0;
		while (itPos.hasNext()) {
			refNPB[icount] = itPos.next().getRefnuc();
			icount++;
		}
		return refNPB;
	}

	@Override
	public String[] getContigs() {
		return this.contigarr;
	}

	@Override
	public Position[] getPosition() {
		return this.getPosarr();
	}

	@Override
	public String[] getReference() {
		return this.getRefnuc();
	}

	@Override
	public Object[][] getVaralleles() {
		return this.getAllelestring();
	}

	@Override
	public Long[] getVarid() {
		return this.getVarids();
	}

	@Override
	public String[] getVarname() {
		return this.getVarnames();
	}

	@Override
	public Double[] getVarmismatch() {
		return this.varmismatch;
	}

	/*
	 * @Override public String[] getSNPGenomicAnnotation(GenotypeQueryParams
	 * genotypeQueryParams) { return null; }
	 */
	@Override
	public String[] getSNPGenomicAnnotation(GenotypeQueryParams genotypeQueryParams) {

//		SNP annot to fill on missing DEBUG
		if (geneAnnots == null) {
			genomics = (GenomicsFacade) AppContext.checkBean(genomics, "GenomicsFacade");

			// Map<Position,Locus> setAnnots[] =null;
			Map<Position, List<Locus>> setAnnots[] = null;
			chr = "any";
			if (genotypeQueryParams.isRegion()) {
				chr = genotypeQueryParams.getsChr();
				setAnnots = genomics.getMarkerGenomicsAnnotsByRegion(chr, data.getListPos(),
						genotypeQueryParams.getlStart(), genotypeQueryParams.getlEnd(),
						genotypeQueryParams.getOrganismName(), GenomicsFacade.GENEMODEL_MSU7_ONLY);
			} else if (genotypeQueryParams.isSNPList() || genotypeQueryParams.isLocusList()) {
				chr = "any";
				setAnnots = genomics.getMarkerGenomicsAnnotsByContigPositions(chr, data.getListPos(),
						genotypeQueryParams.getOrganismName(), GenomicsFacade.GENEMODEL_MSU7_ONLY);
			}
			// Map<Position,Locus> setAnnots[] =
			// genomics.getMarkerGenomicsAnnotsByContigPositions(chr,
			// data.getListPos(),AppContext.getDefaultOrganism(),
			// GenomicsFacade.GENEMODEL_MSU7);

			AppContext.debug("setAnnots[].size()=" + setAnnots[0].size() + "," + setAnnots[1].size() + ","
					+ setAnnots[2].size() + "," + setAnnots[3].size());
			AppContext.debug("setAnnots=" + setAnnots);
			
			// TODO temporary solution
			snpseffDAO = (SnpsEffectDAO) AppContext.checkBean(snpseffDAO, "SnpsEffectDAO");
			List listEffs = new ArrayList();
			listEffs.addAll(snpseffDAO.getAnnotChrPos(chr, data.getListPos()));

			Map<BigDecimal, String> annot2; 
			Map<Integer, Map<BigDecimal,String>> chrPosMap = new HashMap<Integer, Map<BigDecimal, String>>();
			Iterator<VSnpeff> snpeffIter2 = listEffs.iterator();

			while (snpeffIter2.hasNext()) {
				VSnpeff snpeff2 = snpeffIter2.next();
				annot2 = null;
				
				if (!chrPosMap.containsKey(snpeff2.getChromosome())) {
					chrPosMap.put(snpeff2.getChromosome(), new HashMap<BigDecimal, String>());
				}
				
				annot2 = chrPosMap.get(snpeff2.getChromosome());
				
				annot2.put(snpeff2.getPosition(), snpeff2.getAnnotation().toString());
			}
			// END temporary Solution

			if (setAnnots == null)
				return null;

			Set setCDS = new HashSet();
			Set set3p = new HashSet();
			Set set5p = new HashSet();

			String annots[] = new String[data.getListPos().size()];
			Iterator<SnpsAllvarsPos> itPos = data.getListPos().iterator();
			int ipos = 0;
			while (itPos.hasNext()) {
				Position pos = itPos.next();
				VSnpRefposindex posSnp;
				VIndelRefposindex posIndel;
				
				String variantset = null;
				
				if (pos instanceof VSnpRefposindex) {
					 posSnp = (VSnpRefposindex) pos;
					 variantset = posSnp.getVariantset();
				}
				if (pos instanceof VIndelRefposindex) {
					 posIndel = (VIndelRefposindex) pos;
					 variantset = posIndel.getVariantset();
				}
				
				String annot = "";

				Set setConsideredGene = new HashSet();
				if (setAnnots[0].containsKey(pos)) {
					Iterator<Locus> itloc = setAnnots[0].get(pos).iterator();
					while (itloc.hasNext()) {
						Locus loc = itloc.next();
						if (loc.getFeatureType().equals(GenomicsFacade.FEATURETYPE_CDS)) {
							if (variantset.contains("3k")) {
								annot2 = chrPosMap.get(pos.getChr().intValue());
								annot += annot2.get(pos.getPosition());
							} else {
							if (data.getMapPos2NonsynAlleles().containsKey(pos))
								annot += GenomicsFacade.SNPANNOT_NONSYNONYMOUS;
							else
								annot += GenomicsFacade.SNPANNOT_SYNONYMOUS;
							
							}/*
							 * else if(data.getMapPos2SynAlleles().containsKey(pos))
							 * annot=GenomicsFacade.SNPANNOT_SYNONYMOUS; else
							 * annot=GenomicsFacade.SNPANNOT_CDS;
							 */
						} else if (loc.getFeatureType().equals(GenomicsFacade.FEATURETYPE_3PUTR)) {
							annot += GenomicsFacade.SNPANNOT_3PUTR;
						} else if (loc.getFeatureType().equals(GenomicsFacade.FEATURETYPE_5PUTR)) {
							annot += GenomicsFacade.SNPANNOT_5PUTR;
						}
						setConsideredGene.add(loc.getUniquename().split("\\.")[0]);
					}
				}

				if (setAnnots[1].containsKey(pos)) {
					Iterator<Locus> itloc = setAnnots[1].get(pos).iterator();
					while (itloc.hasNext()) {
						Locus loc = itloc.next();
						if (!setConsideredGene.contains(loc.getUniquename().split("\\.")[0])) {
							annot += GenomicsFacade.SNPANNOT_EXON;
							setConsideredGene.add(loc.getUniquename().split("\\.")[0]);
						}
					}
				}

				if (setAnnots[2].containsKey(pos)) {
					Iterator<Locus> itloc = setAnnots[2].get(pos).iterator();
					while (itloc.hasNext()) {
						Locus loc = itloc.next();
						if (!setConsideredGene.contains(loc.getUniquename().split("\\.")[0])) {
							annot += GenomicsFacade.SNPANNOT_GENE;
							setConsideredGene.add(loc.getUniquename().split("\\.")[0]);
						}
					}
				}
				if (setAnnots[3].containsKey(pos)) {
					Iterator<Locus> itloc = setAnnots[3].get(pos).iterator();
					while (itloc.hasNext()) {
						Locus loc = itloc.next();
						if (!setConsideredGene.contains(loc.getUniquename().split("\\.")[0])) {
							annot += GenomicsFacade.SNPANNOT_PROMOTER;
							setConsideredGene.add(loc.getUniquename().split("\\.")[0]);
						}
					}
				}

				if (data.getSetSnpSpliceAcceptorPos().contains(pos)) {
					annot += GenomicsFacade.SNPANNOT_SPLICEACCEPTOR;
				}

				if (data.getSetSnpSpliceDonorPos().contains(pos)) {
					annot += GenomicsFacade.SNPANNOT_SPLICEDONOR;
				}

				annots[ipos] = annot;
				ipos++;

				/*
				 * for pos->locus if(setAnnots[0].containsKey(pos)) { Locus loc =
				 * setAnnots[0].get(pos);
				 * if(loc.getFeatureType().equals(GenomicsFacade.FEATURETYPE_CDS)) {
				 * if(data.getMapPos2NonsynAlleles().containsKey(pos))
				 * annot+=GenomicsFacade.SNPANNOT_NONSYNONYMOUS; else
				 * annot+=GenomicsFacade.SNPANNOT_SYNONYMOUS; } else
				 * if(loc.getFeatureType().equals(GenomicsFacade.FEATURETYPE_3PUTR)) {
				 * annot+=GenomicsFacade.SNPANNOT_3PUTR; } else
				 * if(loc.getFeatureType().equals(GenomicsFacade.FEATURETYPE_5PUTR)) {
				 * annot+=GenomicsFacade.SNPANNOT_5PUTR; } }
				 * 
				 * if(setAnnots[1].containsKey(pos)) { annot=GenomicsFacade.SNPANNOT_EXON; else
				 * if(setAnnots[1].containsKey(pos)) { annot=GenomicsFacade.SNPANNOT_EXON; }
				 * else if(setAnnots[2].containsKey(pos)) { annot=GenomicsFacade.SNPANNOT_GENE;
				 * 
				 * } else if(setAnnots[3].containsKey(pos)) {
				 * annot=GenomicsFacade.SNPANNOT_PROMOTER; }
				 * if(data.getSetSnpSpliceAcceptorPos().contains(pos)) {
				 * annot=GenomicsFacade.SNPANNOT_SPLICEACCEPTOR; } else
				 * if(data.getSetSnpSpliceDonorPos().contains(pos)) {
				 * annot=GenomicsFacade.SNPANNOT_SPLICEDONOR; }
				 */

			}

			// cds,utr

			// exon

			// gene

			// prom

			geneAnnots = annots;
		}

		return geneAnnots;
	}

	@Override
	public VariantStringData getVariantStringData() {

		return data;
	}

	public List getCompare2VarsList(String chromosome, GenotypeQueryParams params) {
		List list = new ArrayList();

		AppContext.debug("getCompare2VarsList allelestring.length=" + allelestring.length);

		if (allelestring.length != 2) {

			String[] newvarnames = new String[2];
			Long[] newvarids = new Long[2];
			Double[] newvarmismatch = new Double[2];
			String[][] newallelestring = new String[2][data.getListPos().size()];

			int varcount = 0;
			for (int ivar = 0; ivar < varids.length; ivar++) {
				if (!params.getColVarIds().contains(BigDecimal.valueOf(varids[ivar])))
					continue;

				newvarnames[varcount] = varnames[ivar];
				newvarids[varcount] = varids[ivar];
				newvarmismatch[varcount] = varmismatch[ivar];
				newallelestring[varcount] = allelestring[ivar];
				varcount++;

				if (varcount == 2)
					break;
			}
			varnames = newvarnames;
			varids = newvarids;
			varmismatch = newvarmismatch;
			allelestring = newallelestring;
			AppContext.debug("getCompare2VarsList allelestring.length=" + allelestring.length);
		}

		if (true) {

			String npbContig = "";
			if (params.isbShowNPBPositions()) {
				npbContig = getVariantStringData().getNpbContig();
			}

			VariantSnpsStringData snpdata = data.getSnpstringdata();

			if (params.isbNonsynSnps() || params.isbNonsynPlusSpliceSnps()) {

				if (params.isbMismatchonly()) {
					for (int ipos = 0; ipos < this.posarr.length; ipos++) {
						// Set setNonsynAlleles =
						// data.getSnpstringdata().getMapIdx2NonsynAlleles().get(ipos);

						Set setNonsynAlleles = snpdata.getMapPos2NonsynAlleles().get(snpdata.getListPos().get(ipos));

						if (setNonsynAlleles == null)
							continue;
						if ((!allelestring[0][ipos].isEmpty()
								&& setNonsynAlleles.contains(allelestring[0][ipos].charAt(0)))
								|| (!allelestring[1][ipos].isEmpty()
										&& setNonsynAlleles.contains(allelestring[1][ipos].charAt(0)))) {
							if (!allelestring[0][ipos].equals(allelestring[1][ipos])) {
								if (params.isbShowNPBPositions()) {
									SnpsAllvarsPos snppos = data.getListPos().get(ipos);
									list.add(new Object[] { chromosome, posarr[ipos], this.refnuc[ipos], npbContig,
											snppos.getPosition(), snppos.getRefnuc(), this.allelestring[0][ipos],
											this.allelestring[1][ipos] });
								} else
									list.add(new Object[] { chromosome, posarr[ipos], this.refnuc[ipos],
											this.allelestring[0][ipos], this.allelestring[1][ipos] });
							}
						}
					}
				} else {
					for (int ipos = 0; ipos < this.posarr.length; ipos++) {
						// Set setNonsynAlleles =
						// data.getSnpstringdata().getMapIdx2NonsynAlleles().get(ipos);
						Set setNonsynAlleles = snpdata.getMapPos2NonsynAlleles().get(snpdata.getListPos().get(ipos));

						if (setNonsynAlleles == null)
							continue;
						if ((!allelestring[0][ipos].isEmpty()
								&& setNonsynAlleles.contains(allelestring[0][ipos].charAt(0)))
								|| (!allelestring[1][ipos].isEmpty()
										&& setNonsynAlleles.contains(allelestring[1][ipos].charAt(0)))) {
							if (params.isbShowNPBPositions()) {
								SnpsAllvarsPos snppos = data.getListPos().get(ipos);
								list.add(new Object[] { chromosome, posarr[ipos], this.refnuc[ipos], npbContig,
										snppos.getPosition(), snppos.getRefnuc(), this.allelestring[0][ipos],
										this.allelestring[1][ipos] });
							} else
								list.add(new Object[] { chromosome, posarr[ipos], this.refnuc[ipos],
										this.allelestring[0][ipos], this.allelestring[1][ipos] });
						}
					}
				}

			} else {
				if (params.isbMismatchonly()) {
					for (int ipos = 0; ipos < this.posarr.length; ipos++) {
						if (!allelestring[0][ipos].equals(allelestring[1][ipos])) {
							if (params.isbShowNPBPositions()) {
								SnpsAllvarsPos snppos = data.getListPos().get(ipos);
								list.add(new Object[] { chromosome, posarr[ipos], this.refnuc[ipos], npbContig,
										snppos.getPosition(), snppos.getRefnuc(), this.allelestring[0][ipos],
										this.allelestring[1][ipos] });
							} else
								list.add(new Object[] { chromosome, posarr[ipos], this.refnuc[ipos],
										this.allelestring[0][ipos], this.allelestring[1][ipos] });
						}
					}
				} else {
					for (int ipos = 0; ipos < this.posarr.length; ipos++) {
						if (params.isbShowNPBPositions()) {
							SnpsAllvarsPos snppos = data.getListPos().get(ipos);
							list.add(new Object[] { chromosome, posarr[ipos], this.refnuc[ipos], npbContig,
									snppos.getPosition(), snppos.getRefnuc(), this.allelestring[0][ipos],
									this.allelestring[1][ipos] });
						} else
							list.add(new Object[] { chromosome, posarr[ipos], this.refnuc[ipos],
									this.allelestring[0][ipos], this.allelestring[1][ipos] });
					}
				}
			}

		}
		return list;
	}

	public String[] getDataset() {
		return dataset;
	}
}
