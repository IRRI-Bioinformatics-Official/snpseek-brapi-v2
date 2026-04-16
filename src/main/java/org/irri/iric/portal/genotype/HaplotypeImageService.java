package org.irri.iric.portal.genotype;

import java.util.List;

import org.irri.iric.ds.chado.domain.Locus;

//import org.irri.iric.portal.genotype.service.Locus;

public interface HaplotypeImageService {

	void HaplotypeImageService(String distdir);

	/*
	 * boolean createImage(String pedfile, String mapfile, String summaryfile,
	 * String format);
	 * 
	 * //boolean createImage(String pedfile, String mapfile, String summaryfile,
	 * String format, List<Locus> listLocus);
	 * 
	 * boolean createImage(String pedfile, String mapfile, String summaryfile,
	 * String format, List<Locus> listLocus);
	 * 
	 * boolean createImage(String pedfile, String mapfile, String summaryfile,
	 * String format, List<Locus> listLocus, boolean genomic_coords, double
	 * resolution, double local_weight);
	 */
	boolean createImage(String pedfile, String mapfile, String summaryfile, String format, List<Locus> listLocus,
			boolean genomic_coords, double resolution, double local_weight, int kgroup, double kheight,
			String autogroup, String imagesize);

	boolean displayHapotypeTreeImage(String pedfile, String format, double kheight, int imagesize);

	double getMaxLog2treeheight(String haplofilename);

	double[] getMinMaxLog2treeheight(String haplofilename);

}
