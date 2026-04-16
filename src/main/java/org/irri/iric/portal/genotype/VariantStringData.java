package org.irri.iric.portal.genotype;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.irri.iric.ds.chado.domain.IndelsStringAllvars;
import org.irri.iric.ds.chado.domain.MultiReferencePosition;
import org.irri.iric.ds.chado.domain.Position;
import org.irri.iric.ds.chado.domain.SnpsAllvarsPos;
import org.irri.iric.ds.chado.domain.SnpsStringAllvars;
import org.irri.iric.ds.chado.domain.Variety;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.irri.iric.portal.genotype.service.SnpsStringAllvarsImplSorter;
import org.irri.iric.portal.genotype.service.SnpsStringMultiHDF5nRDBMSHybridService;
//import org.irri.iric.portal.genotype.service.IndelStringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

//import scala.actors.threadpool.Arrays;

/**
 * Container for genotype data from query
 * 
 * @author LMansueto
 *
 */
public class VariantStringData {

	@Autowired
	@Qualifier("ListItems")
	private ListItemsDAO listitemsdao;

	// variety id to reference mismatch count
	protected Map<BigDecimal, Double> mapVariety2Mismatch = new HashMap();

	// variety id to sort order
	protected Map<BigDecimal, Integer> mapVariety2Order = new HashMap();

	// list of SNP/Indel positions
	protected List<SnpsAllvarsPos> listPos = new ArrayList();

	// list of Variant string, length is number of returned varieties
	protected List<SnpsStringAllvars> listVariantsString = new ArrayList();

	// reference nucleotide string
	protected String refnuc;

	// message
	protected String msgbox = "";

	// snp/indel merged table column index to SNP string index
	protected Map<Integer, Integer> mapMergedIdx2SnpIdx;

	// snp/indel merged table column index to position
	protected Map<Integer, Position> mapMergedIndex2Pos;

	// private String strRef;

	// variety id to position to allele2 map for heterozygous SNPs
	private Map<BigDecimal, Map<Position, Character>> mapVarid2SnpsAllele2str;

	// nonsynonymous alleles for position
	private Map<Position, Set<Character>> mapPos2NonsynAlleles;
	private Map<Position, Set<Character>> mapPos2SynAlleles;

	// exon positions
	// private Set<Position> setSnpInExonPos;

	// splice donow positions
	private Set<Position> setSnpSpliceDonorPos;

	// splice acceptor positions
	private Set<Position> setSnpSpliceAcceptorPos;

	// convert nipponbare position to othere reference genomes
	private Map<BigDecimal, MultiReferencePosition> mapMSU7Pos2ConvertedPos;

	// convert nipponbare position to other reference genomes for a given reference
	// private Map<String, Map<BigDecimal, MultiReferencePosition>>
	// mapOrg2MSU7Pos2ConvertedPos;

	// convert query position to all other reference genomes for a given reference
	private Map<String, Map<BigDecimal, MultiReferencePosition>> mapOrg2RefPos2ConvertedPos;

	// nipponbare contig name
	private String npbContig;

	protected Map<String, Double> mapReference2Mismatch;

	// holds SNP data
	private VariantSnpsStringData snpstringdata;
	// holds indel data
	private VariantIndelStringData indelstringdata;

	public void setMapMSU7Pos2ConvertedPos(Map<BigDecimal, MultiReferencePosition> mapMSU7Pos2ConvertedPos,
			String npbContig) {
		this.mapMSU7Pos2ConvertedPos = mapMSU7Pos2ConvertedPos;
		this.npbContig = npbContig;
	}

	public Map<BigDecimal, MultiReferencePosition> getMapMSU7Pos2ConvertedPos() {
		if (mapMSU7Pos2ConvertedPos != null)
			return mapMSU7Pos2ConvertedPos;
		return null;
	}

	public Map<String, Map<BigDecimal, MultiReferencePosition>> getMapOrg2MSU7Pos2ConvertedPos() {
		// if(mapOrg2MSU7Pos2ConvertedPos!=null)
		// return mapOrg2MSU7Pos2ConvertedPos;
		return getMapOrg2RefPos2ConvertedPos();
	}

	public Map<String, Map<BigDecimal, MultiReferencePosition>> getMapOrg2RefPos2ConvertedPos() {
		return mapOrg2RefPos2ConvertedPos;
	}

	/*
	 * public void addMapOrg2MSU7PosConverterPos(String orgname, Map
	 * mapMSU7Pos2ConvertedPos, String npbcontig) {
	 * 
	 * if(this.npbContig==null) this.npbContig = npbcontig; else
	 * if(!npbcontig.equals(this.npbContig)) throw new
	 * RuntimeException("NPB contig " + npbcontig +
	 * " did not match from previous conversion " + this.npbContig);
	 * 
	 * if(mapOrg2MSU7Pos2ConvertedPos==null) mapOrg2MSU7Pos2ConvertedPos=new
	 * HashMap(); mapOrg2MSU7Pos2ConvertedPos.put(orgname, mapMSU7Pos2ConvertedPos);
	 * }
	 */

	public void addMapOrg2RefPosConverterPos(String orgname, Map mapRefPos2ConvertedPos) {
		addMapOrg2RefPosConverterPos(orgname, mapRefPos2ConvertedPos, null);
	}

	public void addMapOrg2RefPosConverterPos(String orgname, Map mapRefPos2ConvertedPos, String refcontig) {

		if (refcontig != null) {
			if (this.npbContig == null)
				this.npbContig = refcontig;
			else if (!refcontig.equals(this.npbContig))
				throw new RuntimeException(
						"Refcontig " + refcontig + " did not match from previous conversion " + this.npbContig);
		}

		if (mapOrg2RefPos2ConvertedPos == null)
			mapOrg2RefPos2ConvertedPos = new HashMap();
		mapOrg2RefPos2ConvertedPos.put(orgname, mapRefPos2ConvertedPos);
	}

	public String getNpbContig() {
		return npbContig;
	}

	public boolean isNipponbareReference() {
		return (mapMSU7Pos2ConvertedPos == null);
	}

	public boolean hasIndel() {
		return indelstringdata != null;
	}

	public boolean hasSnp() {
		return snpstringdata != null;
	}

	public void clearVarietyData() {

		mapVariety2Mismatch = new HashMap();
		mapVariety2Order = new HashMap();
		;
		listVariantsString = new ArrayList();
		if (snpstringdata != null)
			snpstringdata.clearVarietyData();
		if (indelstringdata != null)
			indelstringdata.clearVarietyData();
	}

	/**
	 * convert merged column index to SNP string index
	 * 
	 * @param merged
	 * @return
	 */

	public Integer convertMergedIdx2SnpIdx(int merged) {

		try {
			if (mapMergedIdx2SnpIdx == null)
				return merged;
			if (mapMergedIdx2SnpIdx.containsKey(merged))
				return mapMergedIdx2SnpIdx.get(merged);
			else
				return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			AppContext.debug(mapMergedIdx2SnpIdx.toString());
			AppContext.debug("merged=" + merged);
			throw new RuntimeException(ex);
		}
	}

	/**
	 * get position for column
	 * 
	 * @param merged
	 * @return
	 */

	public Position convertMergedIdx2Pos(int merged) {
		return this.getMapIdx2Pos().get(merged);
	}

	public void addSnpstringdata(VariantSnpsStringData snpstringdata, boolean isUnion, boolean unionref,
			boolean sortByVarid, boolean nonsynonly, boolean missing05, String dataset1, String dataset2) {
		if (this.snpstringdata == null) {
			setSnpstringdata(snpstringdata);
			return;
		}

		VariantSnpsStringData data1 = this.snpstringdata;
		VariantSnpsStringData data2 = snpstringdata;

		// Map<Integer,Position> mapIdx2PosMerged=new HashMap();
		// Map<Position,Integer> mapPos2IdxMerged=new HashMap();
		Map<Position, Integer> mapPos2Idx1 = new HashMap();
		Map<Position, Integer> mapPos2Idx2 = new HashMap();
		Map<Integer, Position> mapIdx2Pos1 = new HashMap();
		Map<Integer, Position> mapIdx2Pos2 = new HashMap();

		Map<Integer, Integer> mapIdxmerged2Idx1 = null;
		Map<Integer, Integer> mapIdxmerged2Idx2 = null;

		Iterator itPos = data1.getListPos().iterator();
		int idx = 0;
		while (itPos.hasNext()) {
			Position pos = (Position) itPos.next();
			mapPos2Idx1.put(pos, idx);
			mapIdx2Pos1.put(idx, pos);
			idx++;
		}
		itPos = data2.getListPos().iterator();
		idx = 0;
		while (itPos.hasNext()) {
			Position pos = (Position) itPos.next();
			mapPos2Idx2.put(pos, idx);
			mapIdx2Pos2.put(idx, pos);
			idx++;
		}

		// map mergedIds to orig idx

		StringBuffer mergedRefBuff = new StringBuffer();
		// merge snps data, get id mapping
		Set setMergedAllvarsPos = new TreeSet(new SnpsAllvarsPosComparator());
		setMergedAllvarsPos.addAll(data1.getListPos());
		if (isUnion) {
			// union
			setMergedAllvarsPos.addAll(data2.getListPos());
		} else {
			// intersect
			setMergedAllvarsPos.retainAll(data2.getListPos());
		}

		mapIdxmerged2Idx1 = new HashMap();
		mapIdxmerged2Idx2 = new HashMap();
		itPos = setMergedAllvarsPos.iterator();
		idx = 0;
		while (itPos.hasNext()) {
			// Position pos= (Position)itPos.next();
			SnpsAllvarsPos pos = (SnpsAllvarsPos) itPos.next();
			mergedRefBuff.append(pos.getRefnuc());
			if (mapPos2Idx1.containsKey(pos))
				mapIdxmerged2Idx1.put(idx, mapPos2Idx1.get(pos));
			if (mapPos2Idx2.containsKey(pos))
				mapIdxmerged2Idx2.put(idx, mapPos2Idx2.get(pos));
			idx++;
		}

		String mergedRef = mergedRefBuff.toString();
		List<SnpsAllvarsPos> mergedListPos = new ArrayList();
		mergedListPos.addAll(setMergedAllvarsPos);

		AppContext.debug("data1 pos=" + data1.getListPos().size() + ", data2 pos=" + data2.getListPos().size()
				+ ", mergedpos=" + mergedListPos.size());

		String msginit = (dataset1 != null && !dataset1.isEmpty() ? data1.getListPos().size() + " " + dataset1 : "")
				+ (isUnion ? " UNION " : " INTERSECT ") + data2.getListPos().size() + " " + dataset2 + " ... "
				+ mergedListPos.size() + " SNP positions";

		// merge listVariants
		List<SnpsStringAllvars> listMergedVariants = new ArrayList();

		Map<BigDecimal, SnpsStringAllvars> mapVarid2Varnuc2 = new HashMap();

		// use int[] instead of map (for speed?)
		Set<Integer> setMergedIdx = new TreeSet(mapIdxmerged2Idx1.keySet());
		setMergedIdx.addAll(mapIdxmerged2Idx2.keySet());
		int mergedidx1[] = new int[setMergedIdx.size()];
		int mergedidx2[] = new int[setMergedIdx.size()];
		Iterator<Integer> itMergedIdx = setMergedIdx.iterator();
		int imergedidx = 0;
		while (itMergedIdx.hasNext()) {
			int idxmerged = itMergedIdx.next();
			mergedidx1[imergedidx] = -1;
			mergedidx2[imergedidx] = -1;
			if (mapIdxmerged2Idx1.containsKey(idxmerged))
				mergedidx1[imergedidx] = mapIdxmerged2Idx1.get(idxmerged);
			if (mapIdxmerged2Idx2.containsKey(idxmerged))
				mergedidx2[imergedidx] = mapIdxmerged2Idx2.get(idxmerged);
			imergedidx++;
		}

		if (isUnion) {

			Iterator<SnpsStringAllvars> itSnpString1 = data1.getListVariantsString().iterator();
			data1.getMapPos2NonsynAlleles().putAll(data2.getMapPos2NonsynAlleles());
			// data1.getMapPos2SynAlleles().putAll(data2.getMapPos2SynAlleles());
			// data1.getSetSnpInExonPos().addAll(data2.getSetSnpInExonPos());
			data1.getSetSnpSpliceAcceptorPos().addAll(data2.getSetSnpSpliceAcceptorPos());
			data1.getSetSnpSpliceDonorPos().addAll(data2.getSetSnpSpliceDonorPos());

			while (itSnpString1.hasNext()) {
				SnpsStringAllvars snpstring1 = itSnpString1.next();
				SnpsStringAllvars snpstringmerged = snpstring1.copy();

				String varnuc1 = snpstring1.getVarnuc();
				StringBuffer buffMergeVarnuc = new StringBuffer();
				for (imergedidx = 0; imergedidx < mergedidx1.length; imergedidx++) {
					if (mergedidx1[imergedidx] > -1)
						buffMergeVarnuc.append(varnuc1.charAt(mergedidx1[imergedidx]));
					else {
						if (unionref)
							buffMergeVarnuc.append(mergedRef.charAt(imergedidx));
						else
							buffMergeVarnuc.append('?');
					}
				}
				String mergedVarnuc = buffMergeVarnuc.toString();
				snpstringmerged.setVarnuc(mergedVarnuc);

				// SnpsStringMultiHDF5nRDBMSHybridService.countVarpairMismatch(listpos, var1,
				// var2, var1isref, var1allele2str, var2allele2str, mapPos2NonsynAlleles,
				// setInExon, setNonsynPos, isNonsynOnly, countMissing05)

				/*
				 * mismatchCount =
				 * SnpsStringMultiHDF5nRDBMSHybridService.countVarpairMismatch(listNonsynPos,
				 * nonsynRef, varNuc, true, null, varstr.getMapPos2Allele2() ,
				 * //(Map)snpstrdata.getMapIdx2NonsynAlleles(),
				 * snpstrdata.getSetSnpInExonTableIdx(), varNonsynIdx,
				 * params.isbExcludeSynonymous() ); this.getMapPos2NonsynAlleles() ,
				 * this.getSetSnpInExonPos() , varstr.getNonsynPosset(), true , false); //
				 * .isbExcludeSynonymous() );
				 */
				double mismatchCount[] = SnpsStringMultiHDF5nRDBMSHybridService.countVarpairMismatch(mergedListPos,
						mergedRef, mergedVarnuc, true, null, snpstringmerged.getMapPos2Allele2(),
						data1.getMapPos2NonsynAlleles(), snpstringmerged.getNonsynPosset(), nonsynonly, missing05);
				snpstringmerged.setMismatch(BigDecimal.valueOf(mismatchCount[0]));
				listMergedVariants.add(snpstringmerged);
			}

			Iterator<SnpsStringAllvars> itSnpString2 = data2.getListVariantsString().iterator();

			while (itSnpString2.hasNext()) {
				SnpsStringAllvars snpstring2 = itSnpString2.next();
				SnpsStringAllvars snpstringmerged = snpstring2.copy();

				String varnuc2 = snpstring2.getVarnuc();
				StringBuffer buffMergeVarnuc = new StringBuffer();
				for (imergedidx = 0; imergedidx < mergedidx2.length; imergedidx++) {
					if (mergedidx2[imergedidx] > -1)
						buffMergeVarnuc.append(varnuc2.charAt(mergedidx2[imergedidx]));
					else {
						if (unionref)
							buffMergeVarnuc.append(mergedRef.charAt(imergedidx));
						else
							buffMergeVarnuc.append('?');
					}
				}
				String mergedVarnuc = buffMergeVarnuc.toString();
				snpstringmerged.setVarnuc(mergedVarnuc);

				double mismatchCount[] = SnpsStringMultiHDF5nRDBMSHybridService.countVarpairMismatch(mergedListPos,
						mergedRef, mergedVarnuc, true, null, snpstringmerged.getMapPos2Allele2(),
						data1.getMapPos2NonsynAlleles(), snpstringmerged.getNonsynPosset(), nonsynonly, missing05);
				snpstringmerged.setMismatch(BigDecimal.valueOf(mismatchCount[0]));
				listMergedVariants.add(snpstringmerged);
			}

			AppContext.debug("union position " + mergedListPos.size());

		} else {
			// intersect positions

			Iterator<SnpsStringAllvars> itSnpString1 = data1.getListVariantsString().iterator();
			// retain all common pos
			Iterator itposns = data1.getMapPos2NonsynAlleles().keySet().iterator();
			Map mapNewPosns = new HashMap();
			while (itposns.hasNext()) {
				Object posns = itposns.next();
				if (data2.getMapPos2NonsynAlleles().containsKey(posns)) {
					mapNewPosns.put(posns, data1.getMapPos2NonsynAlleles().get(posns));
				}
			}
			itposns = data1.getMapPos2NonsynAlleles().keySet().iterator();

			/*
			 * Map mapSynPosns=new HashMap(); while(itposns.hasNext()) { Object
			 * posns=itposns.next(); if(data2.getMapPos2SynAlleles().containsKey(posns)) {
			 * mapSynPosns.put( posns, data1.getMapPos2SynAlleles().get(posns)); } }
			 * data1.getMapPos2SynAlleles().clear();
			 * data1.getMapPos2SynAlleles().putAll(mapSynPosns);
			 */

			data1.getMapPos2NonsynAlleles().clear();
			data1.getMapPos2NonsynAlleles().putAll(mapNewPosns);

			// data1.getSetSnpInExonPos().retainAll(data2.getSetSnpInExonPos());
			data1.getSetSnpSpliceAcceptorPos().retainAll(data2.getSetSnpSpliceAcceptorPos());
			data1.getSetSnpSpliceDonorPos().retainAll(data2.getSetSnpSpliceDonorPos());

			while (itSnpString1.hasNext()) {
				SnpsStringAllvars snpstring1 = itSnpString1.next();
				SnpsStringAllvars snpstringmerged = snpstring1.copy();

				String varnuc1 = snpstring1.getVarnuc();

				StringBuffer buffMergeVarnuc = new StringBuffer();
				for (imergedidx = 0; imergedidx < mergedidx1.length; imergedidx++) {
					if (mergedidx1[imergedidx] > -1)
						buffMergeVarnuc.append(varnuc1.charAt(mergedidx1[imergedidx]));
					else
						throw new RuntimeException(
								"imergedidx not in mergedidx1:" + imergedidx + ", " + Arrays.toString(mergedidx1));
				}

				String mergedVarnuc = buffMergeVarnuc.toString();
				snpstringmerged.setVarnuc(mergedVarnuc);
				double mismatchCount[] = SnpsStringMultiHDF5nRDBMSHybridService.countVarpairMismatch(mergedListPos,
						mergedRef, mergedVarnuc, true, null, snpstringmerged.getMapPos2Allele2(),
						data1.getMapPos2NonsynAlleles(), snpstringmerged.getNonsynPosset(), nonsynonly, missing05);
				snpstringmerged.setMismatch(BigDecimal.valueOf(mismatchCount[0]));
				listMergedVariants.add(snpstringmerged);
			}

			Iterator<SnpsStringAllvars> itSnpString2 = data2.getListVariantsString().iterator();

			while (itSnpString2.hasNext()) {
				SnpsStringAllvars snpstring2 = itSnpString2.next();
				SnpsStringAllvars snpstringmerged = snpstring2.copy();

				String varnuc2 = snpstring2.getVarnuc();

				StringBuffer buffMergeVarnuc = new StringBuffer();
				for (imergedidx = 0; imergedidx < mergedidx2.length; imergedidx++) {
					if (mergedidx2[imergedidx] > -1)
						buffMergeVarnuc.append(varnuc2.charAt(mergedidx2[imergedidx]));
					else
						throw new RuntimeException(
								"imergedidx not in mergedidx2:" + imergedidx + ", " + Arrays.toString(mergedidx2));

				}
				String mergedVarnuc = buffMergeVarnuc.toString();
				snpstringmerged.setVarnuc(mergedVarnuc);
				double mismatchCount[] = SnpsStringMultiHDF5nRDBMSHybridService.countVarpairMismatch(mergedListPos,
						mergedRef, mergedVarnuc, true, null, snpstringmerged.getMapPos2Allele2(),
						data2.getMapPos2NonsynAlleles(), snpstringmerged.getNonsynPosset(), nonsynonly, missing05);
				snpstringmerged.setMismatch(BigDecimal.valueOf(mismatchCount[0]));
				listMergedVariants.add(snpstringmerged);
			}
			AppContext.debug("intersect position " + mergedListPos.size());
		}

		// Collections.sort(listMergedVariants, new
		// SnpsStringAllvarsImplSorter(sortByVarid, VarietyFacade.DATASET_SNP_ALL));
		Collections.sort(listMergedVariants, new SnpsStringAllvarsImplSorter(sortByVarid));

		AppContext.debug("sorted " + listMergedVariants.size() + " varieties");

		/*
		 * AppContext.debug("merging " + snpstringdata.getListPos().size() + " snps, " +
		 * indelstringdata.getListPos().size() + " indels, total=" +
		 * (snpstringdata.getListPos().size() + indelstringdata.getListPos().size()) +
		 * ", actal merged=" + setMergedAllvarsPos.size());
		 */

		// update mismatches and order
		Map<BigDecimal, Double> mapMergedVar2Mismatch = new HashMap();
		Map<BigDecimal, Integer> mapMergedVar2Order = new HashMap();
		Iterator<SnpsStringAllvars> itSnpstring = listMergedVariants.iterator();
		int ordercnt = 0;
		while (itSnpstring.hasNext()) {
			SnpsStringAllvars snpstring = itSnpstring.next();
			mapMergedVar2Mismatch.put(snpstring.getVar(), snpstring.getMismatch().doubleValue());
			mapMergedVar2Order.put(snpstring.getVar(), ordercnt);
			ordercnt++;
		}

		// update mapMergedIdx2SnpIdx
		// Map<BigDecimal, Integer> mapSNPPos2SnpIdx = new HashMap();
		Map<Position, Integer> mapSNPPos2SnpIdx = new HashMap();
		Iterator<SnpsAllvarsPos> itSNPosList = mergedListPos.iterator();
		int idxcnt = 0;
		while (itSNPosList.hasNext()) {
			SnpsAllvarsPos snppos = itSNPosList.next();
			mapSNPPos2SnpIdx.put(snppos, idxcnt);
			idxcnt++;
		}

		data1.mapMergedIdx2SnpIdx = new HashMap();
		// merge column indexing, update gapped set
		// Map<Integer, Position> mapMergedIndex2Pos = new HashMap();
		data1.mapMergedIndex2Pos = new HashMap();
		Iterator<SnpsAllvarsPos> itSnppos = setMergedAllvarsPos.iterator();
		idxcnt = 0;
		while (itSnppos.hasNext()) {
			SnpsAllvarsPos posallvars = itSnppos.next();
			data1.mapMergedIndex2Pos.put(idxcnt, posallvars);
			if (mapSNPPos2SnpIdx.get(posallvars) == null) {
				// throw new RuntimeException("Can't find " + posallvars + " IN " +
				// mapSNPPos2SnpIdx.keySet());
			} else
				data1.mapMergedIdx2SnpIdx.put(idxcnt, mapSNPPos2SnpIdx.get(posallvars));
			// if(posallvars.getRefnuc().equals(IndelStringService.INDEL_GAP))
			// setNewGappedIdx.add(idxcnt);
			idxcnt++;
		}

		data1.mapVariety2Mismatch = mapMergedVar2Mismatch;
		data1.mapVariety2Order = mapMergedVar2Order;
		// this.mapIdx2Pos=mapMergedIndex2Pos;
		data1.listPos = new ArrayList();
		data1.listPos.addAll(setMergedAllvarsPos);
		data1.listVariantsString = listMergedVariants;

		/*
		 * if(!snpstringdata.isNipponbareReference() ||
		 * !indelstringdata.isNipponbareReference()) { this.mapMSU7Pos2ConvertedPos=new
		 * HashMap(); if(!snpstringdata.isNipponbareReference())
		 * mapMSU7Pos2ConvertedPos.putAll(snpstringdata.getMapMSU7Pos2ConvertedPos());
		 * if(!indelstringdata.isNipponbareReference())
		 * mapMSU7Pos2ConvertedPos.putAll(indelstringdata.getMapMSU7Pos2ConvertedPos());
		 * }
		 * 
		 * if(snpstringdata.getMapOrg2MSU7Pos2ConvertedPos()!=null ||
		 * indelstringdata.getMapOrg2MSU7Pos2ConvertedPos()!=null ) {
		 * this.mapOrg2RefPos2ConvertedPos= new HashMap();
		 * if(snpstringdata.getMapOrg2MSU7Pos2ConvertedPos()!=null &&
		 * indelstringdata.getMapOrg2MSU7Pos2ConvertedPos()!=null) {
		 * this.mapOrg2RefPos2ConvertedPos.putAll(
		 * snpstringdata.getMapOrg2MSU7Pos2ConvertedPos() ); Map<String,
		 * Map<BigDecimal,MultiReferencePosition>> mapOrg2posMapIndel =
		 * indelstringdata.getMapOrg2MSU7Pos2ConvertedPos();
		 * 
		 * Iterator<String> itOrg = mapOrg2posMapIndel.keySet().iterator(); Map
		 * mapNewOrgs = new HashMap(); while(itOrg.hasNext()) { String
		 * orgname=itOrg.next(); Map mapPos = mapOrg2RefPos2ConvertedPos.get(orgname);
		 * if(mapPos==null) { mapOrg2RefPos2ConvertedPos.put( orgname,
		 * mapOrg2posMapIndel.get(orgname)); } else { mapPos.putAll(
		 * mapOrg2posMapIndel.get(orgname) ); } }
		 * 
		 * } else if(snpstringdata.getMapOrg2MSU7Pos2ConvertedPos()!=null)
		 * this.mapOrg2RefPos2ConvertedPos.putAll(
		 * snpstringdata.getMapOrg2MSU7Pos2ConvertedPos() ); else if(
		 * indelstringdata.getMapOrg2MSU7Pos2ConvertedPos()!=null)
		 * this.mapOrg2RefPos2ConvertedPos.putAll(
		 * indelstringdata.getMapOrg2MSU7Pos2ConvertedPos() ); }
		 * 
		 * this.msgbox += " ... RESULT: " + listMergedVariants.size() + " varieties x "
		 * + snpstringdata.getListPos().size() + " SNP, " +
		 * indelstringdata.getListPos().size() + " INDEL positions\n";
		 * 
		 * if(!snpstringdata.msgbox.isEmpty()) this.msgbox +=
		 * snpstringdata.msgbox.trim() + "\n";
		 * 
		 * if(!indelstringdata.msgbox.isEmpty()) this.msgbox +=
		 * indelstringdata.msgbox.trim() + "\n" ;
		 */

		data1.msgbox += msginit + "\n";

	}

	public void setSnpstringdata(VariantSnpsStringData snpstringdata) {
		this.snpstringdata = snpstringdata;

		if (snpstringdata != null && snpstringdata.getListPos() == null)
			throw new RuntimeException("snpstringdata.getListPos()==null");
		// listVariantsString=null;
		// merge();
	}

	public void setIndelstringdata(VariantIndelStringData indelstringdata) {
		this.indelstringdata = indelstringdata;

		if (indelstringdata != null && indelstringdata.getListPos() == null)
			throw new RuntimeException("indelstringdata.getListPos()==null");
		// listVariantsString=null;
		// merge();
	}

	public List<SnpsStringAllvars> getListVariantsString() {
		return listVariantsString;
	}

	public String getRefnuc() {
		if (refnuc == null) {
			StringBuffer buff = new StringBuffer();
			Iterator<SnpsAllvarsPos> itlistPos = listPos.iterator();
			while (itlistPos.hasNext()) {
				String refnuc = itlistPos.next().getRefnuc();
				if (refnuc.length() != 1)
					throw new RuntimeException("refnuc.length()!=1");
				buff.append(refnuc);
			}
			refnuc = buff.toString();
		}
		return refnuc;
	}

	public Map<String, Double> getMapReference2Mismatch() {
		return mapReference2Mismatch;
	}

	public VariantSnpsStringData getSnpstringdata() {
		return snpstringdata;
	}

	public VariantIndelStringData getIndelstringdata() {
		return indelstringdata;
	}

	public Map getMapVariety2Mismatch() {
		return mapVariety2Mismatch;
	}

	public Map<BigDecimal, Integer> getMapVariety2Order() {
		return mapVariety2Order;
	}

	public List<SnpsAllvarsPos> getListPos() {
		return listPos;
	}

	public Map<Integer, Position> getMapIdx2Pos() {

		if (mapMergedIndex2Pos != null)
			return mapMergedIndex2Pos;

		Map idx2Pos = new TreeMap();
		Iterator<SnpsAllvarsPos> itPos = listPos.iterator();
		int ipos = 0;
		while (itPos.hasNext()) {
			idx2Pos.put(ipos, (Position) itPos.next());
			ipos++;
		}
		mapMergedIndex2Pos = idx2Pos;
		return mapMergedIndex2Pos;
	}

	public void setMessage(String message) {
		
		this.msgbox = message;
	}

	public String getMessage() {
		return msgbox;
	}

	public void merge(Set dataset) {
		merge(false, dataset);
	}

	/**
	 * merge SNPs and Indels sortByVarId, else by score (mismatch/match)
	 */
	public void merge(boolean sortByVarId, Set dataset) {
		// merge

		listitemsdao = (ListItemsDAO) AppContext.checkBean(listitemsdao, "ListItems");

		// if SNP and Indels are queried, merge their positions
		// Update the mismatch count (add SNP and Indel mismatches)
		// Map the merged column index to SNP String index
		// if SNPs only, use SNP positions
		// if Indels only, use Indel position

		if (snpstringdata != null && indelstringdata != null) {

			// merge listVariants
			List<SnpsStringAllvars> listMergedVariants = new ArrayList();

			// varwithsnps
			Set hasSnps = new HashSet();
			Iterator<SnpsStringAllvars> itSnpString = snpstringdata.getListVariantsString().iterator();
			Map<BigDecimal, SnpsStringAllvars> mapVar2SnpStringAllvars = new HashMap();
			while (itSnpString.hasNext()) {
				SnpsStringAllvars snpstring = itSnpString.next();
				mapVar2SnpStringAllvars.put(snpstring.getVar(), snpstring);
				hasSnps.add(snpstring.getVar());
			}

			// varswithindels
			Set hasIndels = new HashSet();
			itSnpString = indelstringdata.getListVariantsString().iterator();
			while (itSnpString.hasNext()) {
				IndelsStringAllvars indelstring = (IndelsStringAllvars) itSnpString.next();
				BigDecimal var = indelstring.getVar();
				indelstring.delegateSnpString(mapVar2SnpStringAllvars.get(var));

				hasIndels.add(var);
				listMergedVariants.add(indelstring);
			}

			// has snps only
			Set hasSnpsOnly = new HashSet(hasSnps);
			hasSnpsOnly.removeAll(hasIndels);
			itSnpString = hasSnpsOnly.iterator();
			while (itSnpString.hasNext()) {
				listMergedVariants.add(mapVar2SnpStringAllvars.get(itSnpString.next()));
			}

			System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");

			if (this.hasIndel())
				Collections.sort(listMergedVariants, new IndelStringAllvarsImplSorter(sortByVarId, dataset));
			else
				Collections.sort(listMergedVariants, new SnpsStringAllvarsImplSorter(sortByVarId, dataset));

			// merge pos
			Set setMergedAllvarsPos = new TreeSet(new SnpsAllvarsPosComparator());
			setMergedAllvarsPos.addAll(snpstringdata.getListPos());
			setMergedAllvarsPos.addAll(indelstringdata.getListPos());

			AppContext.debug("merging " + snpstringdata.getMapVariety2Mismatch().size() + " vars "
					+ snpstringdata.getListPos().size() + " snps, " + indelstringdata.getMapVariety2Mismatch().size()
					+ " vars " + indelstringdata.getListPos().size() + " indels, total="
					+ (snpstringdata.getListPos().size() + indelstringdata.getListPos().size())
					+ ", actual merged snps=" + setMergedAllvarsPos.size() + " vars=" + listMergedVariants.size());

			// update mismatches and order
			Map<BigDecimal, Double> mapMergedVar2Mismatch = new HashMap();
			Map<BigDecimal, Integer> mapMergedVar2Order = new HashMap();
			Iterator<SnpsStringAllvars> itSnpstring = listMergedVariants.iterator();
			int ordercnt = 0;
			while (itSnpstring.hasNext()) {
				SnpsStringAllvars snpstring = itSnpstring.next();
				mapMergedVar2Mismatch.put(snpstring.getVar(), snpstring.getMismatch().doubleValue());
				mapMergedVar2Order.put(snpstring.getVar(), ordercnt);
				ordercnt++;
			}

			// update mapMergedIdx2SnpIdx
			// Map<BigDecimal, Integer> mapSNPPos2SnpIdx = new HashMap();
			Map<Position, Integer> mapSNPPos2SnpIdx = new HashMap();
			Iterator<SnpsAllvarsPos> itSNPosList = snpstringdata.getListPos().iterator();
			int idxcnt = 0;
			while (itSNPosList.hasNext()) {
				SnpsAllvarsPos snppos = itSNPosList.next();
				mapSNPPos2SnpIdx.put(snppos, idxcnt);
				idxcnt++;
			}

			mapMergedIdx2SnpIdx = new HashMap();
			// merge column indexing, update gapped set
			// Map<Integer, Position> mapMergedIndex2Pos = new HashMap();
			mapMergedIndex2Pos = new HashMap();
			Iterator<SnpsAllvarsPos> itSnppos = setMergedAllvarsPos.iterator();
			idxcnt = 0;
			while (itSnppos.hasNext()) {
				SnpsAllvarsPos posallvars = itSnppos.next();
				mapMergedIndex2Pos.put(idxcnt, posallvars);

				if (mapSNPPos2SnpIdx.get(posallvars) == null) {

					// throw new RuntimeException("Can't find " + posallvars + " IN " +
					// mapSNPPos2SnpIdx.keySet());

				} else
					mapMergedIdx2SnpIdx.put(idxcnt, mapSNPPos2SnpIdx.get(posallvars));

				// if(posallvars.getRefnuc().equals(IndelStringService.INDEL_GAP))
				// setNewGappedIdx.add(idxcnt);
				idxcnt++;
			}

			this.mapVariety2Mismatch = mapMergedVar2Mismatch;
			this.mapVariety2Order = mapMergedVar2Order;
			// this.mapIdx2Pos=mapMergedIndex2Pos;
			this.listPos = new ArrayList();
			this.listPos.addAll(setMergedAllvarsPos);
			this.listVariantsString = listMergedVariants;

			mapPos2NonsynAlleles = snpstringdata.getMapPos2NonsynAlleles();
			mapPos2SynAlleles = snpstringdata.getMapPos2SynAlleles();
			setSnpSpliceDonorPos = snpstringdata.getSetSnpSpliceDonorPos();
			setSnpSpliceAcceptorPos = snpstringdata.getSetSnpSpliceAcceptorPos();

			//
			// strRef= snpstringdata.getStrRef();
			// mapVarid2SnpsAllele2str=snpstringdata.getMapVarid2SnpsAllele2str();
			// mapPos2NonsynAlleles=snpstringdata.getMapPos2NonsynAlleles();
			// setSnpInExonPos=snpstringdata.getSetSnpInExonPos();
			// setSnpSpliceDonorPos=snpstringdata.getSetSnpSpliceDonorPos();
			// setSnpSpliceAcceptorPos=snpstringdata.getSetSnpSpliceAcceptorPos();

			// this.snpstringdata.setMapMergedIdx2SnpIdx( mapMergedIdx2SnpIdx );
			// this.indelstringdata.setSetIdxGap( setNewGappedIdx );

			// this.mapPos2Alleleset = new HashMap();
			// this.mapPos2Alleleset.putAll( this.snpstringdata.getMapPos2Alleleset() );
			// this.mapPos2Alleleset.putAll( this.indelstringdata.mapPos2Alleleset );

			// variantsmerged = new VariantStringData(mapMergedVar2Mismatch,
			// mapMergedVar2Order,
			// listPos, mapMergedIndex2Pos, listMergedVariants);
			// variantsmerged.setMapMergedIdx2SnpIdx(mapMergedIdx2SnpIdx);

			if (!snpstringdata.isNipponbareReference() || !indelstringdata.isNipponbareReference()) {
				this.mapMSU7Pos2ConvertedPos = new HashMap();
				if (!snpstringdata.isNipponbareReference())
					mapMSU7Pos2ConvertedPos.putAll(snpstringdata.getMapMSU7Pos2ConvertedPos());
				if (!indelstringdata.isNipponbareReference())
					mapMSU7Pos2ConvertedPos.putAll(indelstringdata.getMapMSU7Pos2ConvertedPos());
			}

			if (snpstringdata.getMapOrg2MSU7Pos2ConvertedPos() != null
					|| indelstringdata.getMapOrg2MSU7Pos2ConvertedPos() != null) {
				this.mapOrg2RefPos2ConvertedPos = new HashMap();
				if (snpstringdata.getMapOrg2MSU7Pos2ConvertedPos() != null
						&& indelstringdata.getMapOrg2MSU7Pos2ConvertedPos() != null) {
					this.mapOrg2RefPos2ConvertedPos.putAll(snpstringdata.getMapOrg2MSU7Pos2ConvertedPos());
					Map<String, Map<BigDecimal, MultiReferencePosition>> mapOrg2posMapIndel = indelstringdata
							.getMapOrg2MSU7Pos2ConvertedPos();

					Iterator<String> itOrg = mapOrg2posMapIndel.keySet().iterator();
					Map mapNewOrgs = new HashMap();
					while (itOrg.hasNext()) {
						String orgname = itOrg.next();
						Map mapPos = mapOrg2RefPos2ConvertedPos.get(orgname);
						if (mapPos == null) {
							mapOrg2RefPos2ConvertedPos.put(orgname, mapOrg2posMapIndel.get(orgname));
						} else {
							mapPos.putAll(mapOrg2posMapIndel.get(orgname));
						}
					}

				} else if (snpstringdata.getMapOrg2MSU7Pos2ConvertedPos() != null)
					this.mapOrg2RefPos2ConvertedPos.putAll(snpstringdata.getMapOrg2MSU7Pos2ConvertedPos());
				else if (indelstringdata.getMapOrg2MSU7Pos2ConvertedPos() != null)
					this.mapOrg2RefPos2ConvertedPos.putAll(indelstringdata.getMapOrg2MSU7Pos2ConvertedPos());
			}

			this.msgbox += " ... RESULT: " + listMergedVariants.size() + " varieties x "
					+ snpstringdata.getListPos().size() + " SNP, " + indelstringdata.getListPos().size()
					+ " INDEL positions\n";

			if (!snpstringdata.msgbox.isEmpty())
				this.msgbox += snpstringdata.msgbox.trim() + "\n";

			if (!indelstringdata.msgbox.isEmpty())
				this.msgbox += indelstringdata.msgbox.trim() + "\n";

		} else if (indelstringdata != null) {
			msgbox += " ... RESULT: " + indelstringdata.getListVariantsString().size() + " varieties x "
					+ indelstringdata.getListPos().size() + " INDEL anchor positions\n";

			this.mapVariety2Mismatch = indelstringdata.mapVariety2Mismatch;
			this.mapVariety2Order = indelstringdata.mapVariety2Order;
			// this.mapIdx2Pos= indelstringdata.mapIdx2Pos;
			this.listPos = indelstringdata.listPos;
			this.listVariantsString = indelstringdata.listVariantsString;
			this.msgbox += indelstringdata.msgbox;
			this.refnuc = indelstringdata.refnuc;
			// this.mapPos2Alleleset = indelstringdata.mapPos2Alleleset;
			this.mapMSU7Pos2ConvertedPos = indelstringdata.getMapMSU7Pos2ConvertedPos(); // .mapMSU7Pos2ConvertedPos;
			this.npbContig = indelstringdata.getNpbContig();
			this.mapOrg2RefPos2ConvertedPos = indelstringdata.getMapOrg2MSU7Pos2ConvertedPos();

			mapMergedIndex2Pos = indelstringdata.getMapIdx2Pos();

		} else if (snpstringdata != null) {

			if (snpstringdata.getListVariantsString() == null)
				AppContext.debug("snpstringdata.getListVariantsString()==null");
			if (snpstringdata.getListPos() == null)
				AppContext.debug("snpstringdata.getListPos()==null");
			msgbox += " ... RESULT: " + snpstringdata.getListVariantsString().size() + " varieties x "
					+ snpstringdata.getListPos().size() + " SNPS positions\n";
			this.mapVariety2Mismatch = snpstringdata.mapVariety2Mismatch;
			this.mapVariety2Order = snpstringdata.mapVariety2Order;
			// this.mapIdx2Pos= snpstringdata.mapIdx2Pos;
			this.listPos = snpstringdata.listPos;
			this.listVariantsString = snpstringdata.listVariantsString;
			// this.mapPos2Alleleset = snpstringdata.getMapPos2Alleleset();
			this.msgbox += snpstringdata.msgbox;
			this.refnuc = snpstringdata.refnuc;
			this.mapMSU7Pos2ConvertedPos = snpstringdata.getMapMSU7Pos2ConvertedPos(); // .mapMSU7Pos2ConvertedPos;
			this.npbContig = snpstringdata.getNpbContig();
			this.mapOrg2RefPos2ConvertedPos = snpstringdata.getMapOrg2MSU7Pos2ConvertedPos();

			this.mapReference2Mismatch = snpstringdata.getMapReference2Mismatch();

			/*
			 * Map mapMergedIdx2SnpIdx=new HashMap(); for(int i=0; i<listPos.size(); i++)
			 * mapMergedIdx2SnpIdx.put(i, i);
			 * this.snpstringdata.setMapMergedIdx2SnpIdx(mapMergedIdx2SnpIdx);
			 */
			mapMergedIndex2Pos = snpstringdata.getMapIdx2Pos();

			// strRef= snpstringdata.getStrRef();
			refnuc = snpstringdata.getStrRef();
			mapVarid2SnpsAllele2str = snpstringdata.getMapVarid2SnpsAllele2str();
			mapPos2NonsynAlleles = snpstringdata.getMapPos2NonsynAlleles();
			// setSnpInExonPos=snpstringdata.getSetSnpInExonPos();
			mapPos2SynAlleles = snpstringdata.getMapPos2SynAlleles();
			setSnpSpliceDonorPos = snpstringdata.getSetSnpSpliceDonorPos();
			setSnpSpliceAcceptorPos = snpstringdata.getSetSnpSpliceAcceptorPos();

		}

		AppContext.debug(msgbox);
	}

	public Map<Position, Set<Character>> getMapPos2NonsynAlleles() {
		return mapPos2NonsynAlleles;
	}

	public Map<Position, Set<Character>> getMapPos2SynAlleles() {
		return mapPos2SynAlleles;
	}

	/*
	 * public Set<Position> getSetSnpInExonPos() { return setSnpInExonPos; }
	 */

	public Set<Position> getSetSnpSpliceDonorPos() {
		return setSnpSpliceDonorPos;
	}

	public Set<Position> getSetSnpSpliceAcceptorPos() {
		return setSnpSpliceAcceptorPos;
	}

	class SnpsAllvarsPosComparator implements Comparator {
		@Override
		public int compare(Object o1, Object o2) {
			
			/*
			 * SnpsAllvarsPos pos1 = (SnpsAllvarsPos)o1; SnpsAllvarsPos pos2 =
			 * (SnpsAllvarsPos)o2; //return pos1.getPos().compareTo(pos2.getPos()); return
			 * pos1.compareTo(pos2);
			 */

			Position pos1 = (Position) o1;
			Position pos2 = (Position) o2;
			return pos1.compareTo(pos2);

		}
	}

	class IndelStringAllvarsImplSorter implements Comparator {
		private boolean sortByVarId = false;
		private Set dataset;

		public IndelStringAllvarsImplSorter(boolean sortByVarId, Set dataset) {
			super();
			this.sortByVarId = sortByVarId;
			this.dataset = dataset;
		}

		@Override
		public int compare(Object o1, Object o2) {
			

			SnpsStringAllvars s1 = (SnpsStringAllvars) o1;
			SnpsStringAllvars s2 = (SnpsStringAllvars) o2;

			if (sortByVarId) {
				return s1.getVar().compareTo(s2.getVar());
			}

			int ret = -s1.getMismatch().compareTo(s2.getMismatch());

			if (ret != 0)
				return ret;

			Variety v1 = (Variety) listitemsdao.getMapId2Variety(dataset).get(s1.getVar());
			Variety v2 = (Variety) listitemsdao.getMapId2Variety(dataset).get(s2.getVar());
			return v1.getVarietyId().compareTo(v2.getVarietyId());

		}
	}

}
