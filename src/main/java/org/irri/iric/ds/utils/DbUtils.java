package org.irri.iric.ds.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.irri.iric.ds.chado.domain.Position;
import org.springframework.util.ResourceUtils;

public class DbUtils {

	public static DecimalFormat decf = new DecimalFormat("#.##");
	private static Map<String, Integer> mapVariant2Order;
	private static Properties webProp;

	private static Map<Integer, Integer> offsetMap;

	static {
		offsetMap = new HashMap<>();
		offsetMap.put(9, 2);
		offsetMap.put(23, 7651231);

	}

	/**
	 * Slice a large set into groups of 1000, (for oracle IN has limit 1000)
	 * 
	 * @param vars
	 * @return
	 */
	public static Set[] setSlicer(Set vars, int size) {
		int nSlices = vars.size() / size + 1;
		Set slicedsets[] = new LinkedHashSet[nSlices];
		for (int iset = 0; iset < nSlices; iset++) {
			slicedsets[iset] = new LinkedHashSet();
		}
		Iterator it = vars.iterator();

		int icount = 0;
		while (it.hasNext()) {
			slicedsets[icount / size].add(it.next());
			icount++;
		}
		return slicedsets;
	}

	/**
	 * returns all objects in the collection to UPPER CASE
	 * 
	 * @param dataset
	 * @return returns all objects in the collection to UPPER CASE
	 */

	public static Set toUpperCase(Collection dataset) {
		Set s = new LinkedHashSet();
		for (Object ds : dataset) {
			s.add(((String) ds).toUpperCase());
		}
		return s;
	}

	/**
	 * Guess the chromosome number from contig name
	 * 
	 * @param chr
	 * @return
	 */
	public static String guessChrFromString(String chr) {
		return chr.toUpperCase().replace("CHR0", "").replace("CHR", "");
	}

	/**
	 * Convert SQL CLOB field to string
	 * 
	 * @param clb
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static String clobStringConversion(Clob clb) throws IOException, SQLException {
		if (clb == null)
			return "";

		StringBuffer str = new StringBuffer();
		String strng;
		BufferedReader bufferRead = new BufferedReader(clb.getCharacterStream());
		while ((strng = bufferRead.readLine()) != null)
			str.append(strng);
		return str.toString();
	}

	public static List setStringSlicer(Set varIds, boolean isQuoted, boolean toUpper) {
		List<String> listVaridSets = new ArrayList();
		if (varIds != null && !varIds.isEmpty()) {
			// create varids list
			Set[] sets = setSlicer(varIds);

			for (int iset = 0; iset < sets.length; iset++) {

				StringBuffer buff = new StringBuffer();
				Iterator<String> itSet = sets[iset].iterator();
				while (itSet.hasNext()) {
					String s = itSet.next();
					if (toUpper)
						s = s.toUpperCase();
					if (isQuoted)
						buff.append("'" + s + "'");
					else
						buff.append(s);
					if (itSet.hasNext())
						buff.append(",");
				}
				listVaridSets.add(buff.toString());
			}

		}
		return listVaridSets;
	}

	public static Set[] setSlicer(Set vars) {
		if (vars.size() > 3000) {
			int nSlices = vars.size() / 1000 + 1;
			Set slicedsets[] = new LinkedHashSet[nSlices];
			for (int iset = 0; iset < nSlices; iset++) {
				slicedsets[iset] = new LinkedHashSet();
			}
			Iterator it = vars.iterator();

			int icount = 0;
			while (it.hasNext()) {
				slicedsets[icount / 1000].add(it.next());
				icount++;
			}
			return slicedsets;
		}

		if (vars.size() > 2000) {
			java.util.Set set1 = new HashSet();
			Iterator it = vars.iterator();
			for (int i = 0; i < 1000; i++)
				set1.add(it.next());
			java.util.Set set2 = new HashSet();
			for (int i = 0; i < 1000; i++)
				set2.add(it.next());
			java.util.Set set3 = new HashSet();
			while (it.hasNext())
				set3.add(it.next());

			return new Set[] { set1, set2, set3 };

		} else if (vars.size() > 1000) {
			java.util.Set set1 = new HashSet();
			Iterator it = vars.iterator();
			for (int i = 0; i < 1000; i++)
				set1.add(it.next());
			java.util.Set set2 = new HashSet();
			while (it.hasNext())
				set2.add(it.next());

			return new Set[] { set1, set2 };

		} else
			return new Set[] { vars };
	}

	public static String toCSVquoted(Collection col, String quote) {
		StringBuffer buff = new StringBuffer();
		Iterator itSet = col.iterator();
		while (itSet.hasNext()) {

			buff.append(quote).append(itSet.next()).append(quote);
			if (itSet.hasNext())
				buff.append(",");
		}
		return buff.toString();

	}

	public static Integer getMapVariantRank(String annot) {

		if (mapVariant2Order == null) {
			mapVariant2Order = new HashMap();
			mapVariant2Order.put("chromosome_number_variation", 1);
			mapVariant2Order.put("exon_loss_variant", 2);
			mapVariant2Order.put("frameshift_variant", 3);
			mapVariant2Order.put("stop_gained", 4);
			mapVariant2Order.put("stop_lost", 5);
			mapVariant2Order.put("start_lost", 6);
			mapVariant2Order.put("splice_acceptor_variant", 7);
			mapVariant2Order.put("splice_donor_variant", 8);
			mapVariant2Order.put("rare_amino_acid_variant", 9);
			mapVariant2Order.put("missense_variant", 10);
			mapVariant2Order.put("inframe_insertion", 11);
			mapVariant2Order.put("disruptive_inframe_insertion", 12);
			mapVariant2Order.put("inframe_deletion", 13);
			mapVariant2Order.put("disruptive_inframe_deletion", 14);
			mapVariant2Order.put("5_prime_UTR_truncation+exon_loss_variant", 15);
			mapVariant2Order.put("3_prime_UTR_truncation+exon_loss", 16);
			mapVariant2Order.put("splice_branch_variant", 17);
			mapVariant2Order.put("splice_region_variant", 18);
			mapVariant2Order.put("splice_branch_variant", 19);
			mapVariant2Order.put("stop_retained_variant", 20);
			mapVariant2Order.put("initiator_codon_variant", 21);
			mapVariant2Order.put("synonymous_variant", 22);
			mapVariant2Order.put("initiator_codon_variant+non_canonical_start_codon", 23);
			mapVariant2Order.put("stop_retained_variant", 24);
			mapVariant2Order.put("coding_sequence_variant", 25);
			mapVariant2Order.put("5_prime_UTR_variant", 26);
			mapVariant2Order.put("3_prime_UTR_variant", 27);
			mapVariant2Order.put("5_prime_UTR_premature_start_codon_gain_variant", 28);
			mapVariant2Order.put("upstream_gene_variant", 29);
			mapVariant2Order.put("downstream_gene_variant", 30);
			mapVariant2Order.put("TF_binding_site_variant", 31);
			mapVariant2Order.put("regulatory_region_variant", 32);
			mapVariant2Order.put("miRNA", 33);
			mapVariant2Order.put("custom", 34);
			mapVariant2Order.put("sequence_feature", 35);
			mapVariant2Order.put("conserved_intron_variant", 36);
			mapVariant2Order.put("intron_variant", 37);
			mapVariant2Order.put("intragenic_variant", 38);
			mapVariant2Order.put("conserved_intergenic_variant", 39);
			mapVariant2Order.put("intergenic_region", 40);
			mapVariant2Order.put("coding_sequence_variant", 41);
			mapVariant2Order.put("non_coding_exon_variant", 42);
			mapVariant2Order.put("nc_transcript_variant", 43);
			mapVariant2Order.put("gene_variant", 44);
			mapVariant2Order.put("chromosome", 45);
		}

		return (Integer) mapVariant2Order.get(annot);

	}

	public static String guessSrcfeataureidFromString(String chr, Integer organismId) {
		return Integer.toString(
				chr2srcfeatureidOffset(organismId) + Integer.valueOf(chr.toUpperCase().replace("CHR0", "").replace("CHR", "")));
	}

	
	public static void setWebProp(Properties config) {
		webProp = config;
	}

	public static Integer getDefaultOrganismId() {

		if (webProp.get(WebserverPropertyConstants.DEFAULT_ORGANISM_ID).toString().equals("."))
			return Integer.parseInt(webProp.get(WebserverPropertyConstants.DEFAULT_ORGANISM_ID).toString());
		else
			return 9;

	}

	public static Collection convertPos2Position(Collection possetobj) {

		if (possetobj == null || possetobj.isEmpty())
			return possetobj;

		String prevcont = null;
		List posset = new ArrayList();
		Iterator itpos = possetobj.iterator();
		while (itpos.hasNext()) {
			Object pos = itpos.next();
			if (pos instanceof Position) {
				posset.add(((Position) pos).getPosition());
				if (prevcont != null && !prevcont.equals(((Position) pos).getContig())) {
					throw new RuntimeException("Different contigs for " + prevcont + " and " + pos);
				}
				prevcont = ((Position) pos).getContig();
			} else
				posset.add((Number) pos);
		}
		return posset;
	}

	/**
	 * Convert collection to csv
	 * 
	 * @param col
	 * @return
	 */
	public static String toCSV(Collection col) {
		StringBuffer buff = new StringBuffer();
		Iterator itSet = col.iterator();
		while (itSet.hasNext()) {
			buff.append(itSet.next());
			if (itSet.hasNext())
				buff.append(",");
		}
		return buff.toString();

	}

	public static BigDecimal convertRegion2Snpfeatureid(Integer chr, Long pos) {

		throw new RuntimeException("Dont use convertRegion2Snpfeatureid! Integer chr, Long pos");

	}

	public static BigDecimal convertRegion2Snpfeatureid(String chr, Integer pos) {
		chr = guessChrFromString(chr);
		return convertRegion2Snpfeatureid(Integer.valueOf(chr), pos);
	}

	public static BigDecimal convertRegion2Snpfeatureid(Integer chr, Integer pos) {
		throw new RuntimeException("Dont use convertRegion2Snpfeatureid! Integer chr, Integer pos");

	}

	public static BigDecimal convertRegion2Snpfeatureid(Integer chr, BigDecimal pos) {
		throw new RuntimeException("Dont use convertRegion2Snpfeatureid! Integer chr, BigDecimal pos");
	}

	public static Collection convertRegion2Snpfeatureid(String chr, Collection poslist) {

		chr = guessChrFromString(chr);
		return convertRegion2Snpfeatureid(Integer.valueOf(chr), poslist);

	}

	public static Collection convertRegion2Snpfeatureid(Integer chr, Collection poslist) {
		Set snpfeatureidSet = new TreeSet();
		Iterator<BigDecimal> it = poslist.iterator();
		while (it.hasNext()) {
			snpfeatureidSet.add(convertRegion2Snpfeatureid(chr, it.next()));
		}
		return snpfeatureidSet;
	}

	public static BigDecimal convertRegion2Snpfeatureid(String chr, Long pos) {
		chr = guessChrFromString(chr);
		return convertRegion2Snpfeatureid(Integer.valueOf(chr), pos);
	}

	public static List executeSQL(EntityManager entityManager, Class retclass, String sql) {

		try {
			Session ses = entityManager.unwrap(Session.class);
			List l = ses.createSQLQuery(sql).addEntity(retclass).list();
			return l;
		} catch (Exception ex) {

			ex.printStackTrace();
			throw ex;
		}
	}

	public static String getSystemStatus() {
		Runtime runtime = Runtime.getRuntime();

		NumberFormat format = NumberFormat.getInstance();

		StringBuilder sb = new StringBuilder();
		long maxMemory = runtime.maxMemory();
		long allocatedMemory = runtime.totalMemory();
		long freeMemory = runtime.freeMemory();

		OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

		sb.append("SystemStatus: free memory kb: " + format.format(freeMemory / 1024) + ";");
		sb.append("allocated memory kb: " + format.format(allocatedMemory / 1024) + ";");
		sb.append("max memory kb: " + format.format(maxMemory / 1024) + ";");
		sb.append("total free memory kb: " + format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024) + ";");
		sb.append("system load average: " + osBean.getSystemLoadAverage() + ";");
		sb.append("avail processors: " + osBean.getAvailableProcessors() + ";");
		sb.append("active threads: " + Thread.activeCount() + ";");

		return (sb.toString());
	}

	public static boolean reloadFromDB(String string) {
		return false;
	}

	/**
	 * get nucleotide values for IUPAC symbol
	 * 
	 * @param alleles
	 * @return
	 */
	public static String getNucsFromIUPAC(String alleles) {
		if (alleles.equals("R"))
			return "AG";
		else if (alleles.equals("W"))
			return "AT";
		else if (alleles.equals("M"))
			return "AC";
		else if (alleles.equals("S"))
			return "CG";
		else if (alleles.equals("Y"))
			return "CT";
		else if (alleles.equals("K"))
			return "GT";
		else if (alleles.equals("N"))
			return "N";
		else
			return alleles;
	}

	public static String getNucsFromIUPAC(char alleles) {
		return getNucsFromIUPAC(String.valueOf(alleles));
	}

	

	public static Integer chr2srcfeatureidOffset(int organismid) {
		return offsetMap.get(organismid);

	}

	

	public static Integer chr2srcfeatureidOffset(Integer organismId) {
		return offsetMap.get(organismId);

	}

}
