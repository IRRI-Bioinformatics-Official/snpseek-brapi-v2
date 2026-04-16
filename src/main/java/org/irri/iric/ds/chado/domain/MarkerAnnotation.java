package org.irri.iric.ds.chado.domain;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface MarkerAnnotation extends Position {

	public static String GENE_MODEL_GENE = "gene";
	public static String GENE_MODEL_CDS = "CDS";
	public static String GENE_MODEL_3UTR = "three_prime_UTR";
	public static String GENE_MODEL_5UTR = "five_prime_UTR";
	public static String GENE_MODEL_EXON = "exon";
	public static String GENE_MODEL_PROMOTER = "promoter";
	public static String GENE_MODEL_INTRON = "intron";
	public static String GENE_MODEL_ACCEPTOR = "splice_acceptor";
	public static String GENE_MODEL_DONOR = "splice_donor";

	public static String MARKER_POSITION = "MARKER POSITION";
	public static String MARKER_SNPEFF = "SNPEFF";
	public static String MARKER_FEATURE_TYPE = "FEATURE_TYPE";
	public static String GENE_MODEL = "GENE MODELS";
	public static String GENE_QTARO = "QTARO TRAIT GENES";
	public static String GENE_TO = "TRAIT ONTOLOGY GENES";
	public static String GENE_PO = "PLANT ANATOMY GENES";
	public static String GENE_PO_DEVT = "PLANT DEVELOPMENT GENES";
	public static String GENE_GO_BP = "GO BIOLOGICAL PROCESS";
	public static String GENE_GO_MF = "GO MOLECULAR FUNCTION";
	public static String GENE_GO_CC = "GO CELLULAR COMPONENT";

	public static String QTL_QTARO = "QTARO QTL";
	public static String QTL_GRAMENE = "GRAMENE QTL (<100KB)";

	public static String GENE_INT_RICENETV2 = "RICENETV2 INTERACTIONS";
	public static String GENE_INT_RICENETV1 = "RICENETV1 INTERACTIONS";
	public static String GENE_INT_PRINEXPT = "PRIN EXPERIMENTAL";
	public static String GENE_INT_PRINPRED = "PRIN PREDICTED";

	public static String PROM_PLANTPROMDB = "PLANTPROMDB PRED (-200) PROMOTER";
	public static String PROM_FGENESH1K = "FGENESH (-1KB) PROMOTER";

	public static Set<String> ONTOLOGY_GENES = new HashSet<String>(Arrays.asList(GENE_TO, GENE_PO, GENE_PO_DEVT,
			GENE_QTARO, QTL_QTARO, QTL_GRAMENE, GENE_GO_BP, GENE_GO_MF, GENE_GO_CC));
	public static Set<String> PROMOTERS = new HashSet<String>(Arrays.asList(PROM_PLANTPROMDB, PROM_FGENESH1K));
	public static Set<String> INT_GENES = new HashSet<String>(
			Arrays.asList(GENE_INT_RICENETV2, GENE_INT_RICENETV1, GENE_INT_PRINEXPT, GENE_INT_PRINPRED));
	public static Set<String> MARKERS = new HashSet<String>(Arrays.asList(MARKER_POSITION, MARKER_SNPEFF));

	// List<Locus> getQTL1();
	// List<Locus> getQTL2();
	// Set<Locus> getGenes();
	// void addGene(Locus loc);
	// void addQTL1(Locus loc);
	// void addQTL2(Locus loc);

	void addMarker(String name, Locus pos);

	void addGene(String name, Locus loc);

	void addQTL(String name, Locus loc);

	void addTraitGene(String name, Locus loc);

	void addNetworkGene(String name, Locus loc);

	void addOntologyGene(String name, Locus loc);

	void addPromoterGene(String name, Locus loc);

	Collection getAnnotation(String name);

	Collection getAnnotation(int index);

	Set<String> getAnnotations();

	Set<Locus> getGene();

	Set<Locus> getQTL();

	Set<Locus> getTraitGene();

	Set<Locus> getNetworkGene();

	Set<Locus> getOntologyGene();

	Set<Locus> getPromoterGene();

	Position getContigPosition();

	void addNetworkGene(String name, Collection loc);

	void addOntologyGene(String name, Collection loc);

	void addPromoterGene(String name, Collection loc);

	void addQTL(String name, Collection loc);

	void addTraitGene(String name, Collection loc);

	void addGene(String name, Collection loc);

	void addMarker(String name, Collection loc);

	void setMinusLogPvalue(Double logp);

	Double getMinusLogPvalue();

}
