package org.irri.iric.ds.chado.domain.object;

public class SnpEffectAnn {

	private String anns[];
	private String ann;

	// ##INFO=<ID=ANN,Number=.,Type=String,Description="Functional annotations:
	// 'Allele | Annotation | Annotation_Impact | Gene_Name | Gene_ID | Feature_Type
	// | Feature_ID | Transcript_BioType |
	// Rank | HGVS.c | HGVS.p | cDNA.pos / cDNA.length | CDS.pos / CDS.length |
	// AA.pos / AA.length | Distance | ERRORS / WARNINGS / INFO' ">

	public SnpEffectAnn(String ann) {
		super();
		this.ann = ann;
	}

	private void parse() {
		if (anns == null) {
			anns = ann.split("\\|");
			ann = null;
		}
	}

	private String getANN() {
		return ann;
	}

	public String getAlt() {
		parse();
		return anns[0];
	}

	public String getAnnotation() {
		parse();
		return anns[1];
	}

	public String getAnnotationImpact() {
		parse();
		return anns[2];
	}

	public String getGenename() {
		parse();
		return anns[3];
	}

	public String getFeatureType() {
		parse();
		return anns[5];
	}

	String getFeatureId() {
		parse();
		return anns[6];
	}

	String getTranscriptBiotype() {
		parse();
		return anns[7];
	}

	String getHGVSc() {
		parse();
		return anns[9];
	}

	String getHGVSp() {
		parse();
		return anns[10];
	}

	String getDistance() {
		parse();
		return anns[14];
	}

	String getMsg() {
		parse();
		return anns[15];
	}
}
