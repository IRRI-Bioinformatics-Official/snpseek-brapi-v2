package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.irri.iric.ds.LocalDatasource;

public interface SnpsAllvarsPosDAO {

	// public static BigDecimal TYPE_3KCORESNP = new BigDecimal(4);
	// public static BigDecimal TYPE_3KALLSNP = new BigDecimal(3);

	// public static BigDecimal TYPE_3KCORESNP = new BigDecimal(6);
	public static BigDecimal TYPE_3KCORESNP = new BigDecimal(15);
	public static BigDecimal TYPE_3KALLSNP = new BigDecimal(5);

	// public static BigDecimal TYPE_3KCORESNP_HDF5 = new BigDecimal(4);
	// public static BigDecimal TYPE_3KALLSNP_HDF5 = new BigDecimal(3);

	// public static BigDecimal TYPE_3KCORESNP_HDF5_V2 = new BigDecimal(6);

	public static BigDecimal TYPE_3KCORESNP_HDF5_V2 = new BigDecimal(15);
	public static BigDecimal TYPE_3KALLSNP_HDF5_V2 = new BigDecimal(5);
	public static BigDecimal TYPE_3KBIALLSNP_HDF5_V2 = new BigDecimal(16);

	public static BigDecimal TYPE_3KALLINDEL_V2 = new BigDecimal(7);

	/*
	 * public static String DATASET_SNPINDELV1="SNP v1"; public static String
	 * DATASET_SNPINDELV2="SNP v2"; public static String
	 * DATASET_SNPINDELV2_IUPAC="SNP v2 IUPAC"; public static String
	 * DATASET_SNP_HDRA="SNP HDRA";
	 */

	public static BigDecimal TYPE_3K9311SNP_HDF5_V2 = new BigDecimal(8);
	public static BigDecimal TYPE_3KIR64SNP_HDF5_V2 = new BigDecimal(9);
	public static BigDecimal TYPE_3KKASALATHSNP_HDF5_V2 = new BigDecimal(10);
	public static BigDecimal TYPE_3KDJ123SNP_HDF5_V2 = new BigDecimal(11);

	public static BigDecimal TYPE_HDRASNP = new BigDecimal(12);
	public static BigDecimal TYPE_WISSUWASNP = new BigDecimal(17);

	public static BigDecimal TYPE_3KBASESNP_HDF5_V2 = new BigDecimal(13);
	public static BigDecimal TYPE_3KFILTEREDSNP_HDF5_V2 = new BigDecimal(14);
	// public static BigDecimal TYPE_3KCORE2SNP_HDF5_V2 = new BigDecimal(15);

	/*
	 * public static BigDecimal TYPE_GQ92BASESNP = new BigDecimal(16); public static
	 * BigDecimal TYPE_GQ92FILTEREDSNP = new BigDecimal(17); public static
	 * BigDecimal TYPE_GQ92CORESNP = new BigDecimal(18);
	 */

	public static Map<BigDecimal, String> MAP_TYPE2HDF5FILE = Collections
			.unmodifiableMap(new HashMap<BigDecimal, String>() {
				{
					// Unnamed init block.
					put(TYPE_3KCORESNP_HDF5_V2, LocalDatasource.getFlatfilesDir() + "SNPuni_geno_core_NB");
					put(TYPE_3KALLSNP_HDF5_V2, LocalDatasource.getFlatfilesDir() + "SNPuni_geno_NB");
					put(TYPE_3K9311SNP_HDF5_V2, LocalDatasource.getFlatfilesDir() + "SNPuni_geno_9311");
					put(TYPE_3KIR64SNP_HDF5_V2, LocalDatasource.getFlatfilesDir() + "SNPuni_geno_IR64");
					put(TYPE_3KKASALATHSNP_HDF5_V2, LocalDatasource.getFlatfilesDir() + "SNPuni_geno_Kas");
					put(TYPE_3KDJ123SNP_HDF5_V2, LocalDatasource.getFlatfilesDir() + "SNPuni_geno_DJ123");
					/*
					 * put(TYPE_GQ92BASESNP, AppContext.getFlatfilesDir() + "SNPuni_geno_NB");
					 * put(TYPE_GQ92FILTEREDSNP, AppContext.getFlatfilesDir() + "SNPuni_geno_NB");
					 * put(TYPE_GQ92CORESNP, AppContext.getFlatfilesDir() + "SNPuni_geno_NB");
					 */
				}
			});

	/**
	 * Get SNP positions for all varieties in the region
	 * 
	 * @param chromosome
	 * @param startPos
	 * @param endPos
	 * @return
	 */
	/*
	 * public List getSNPs(String chromosome, Integer startPos, Integer endPos ,
	 * BigDecimal type);
	 * 
	 * public List getSNPs(String chromosome, Integer startPos, Integer endPos,
	 * BigDecimal type, int firstRow, int maxRows);
	 * 
	 * public List getSNPsInChromosome(String chr, Collection posset, BigDecimal
	 * type);
	 * 
	 * long countSNPsInChromosome(String chr, Collection posset, BigDecimal type);
	 * 
	 * long countSNPs(String chr, Integer startPos, Integer endPos, BigDecimal
	 * type);
	 */

	public List getSNPs(Integer organismId, String chromosome, Integer startPos, Integer endPos, Set variantset);
	// List getSNPs(String chromosome, Integer startPos, Integer endPos, BigDecimal
	// type);

	public List getSNPs(Integer organismId, String chromosome, Integer startPos, Integer endPos, Set variantset, int firstRow, int maxRows);
	// public List getSNPs(String chromosome, Integer startPos, Integer endPos,
	// BigDecimal variantset, int firstRow, int maxRows);

	//public List getSNPsInChromosome(String chr, Collection posset, Set variantset);
	
	public List getSNPsInChromosome(Integer organismId, String chr, Collection posset, Set variantset);

	long countSNPsInChromosome(String chr, Collection posset, Set variantset);

	long countSNPs(String chr, Integer startPos, Integer endPos, Set variantset);

}
