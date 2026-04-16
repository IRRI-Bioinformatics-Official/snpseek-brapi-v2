package org.irri.iric.portal.genotype.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.irri.iric.ds.chado.domain.Locus;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.genotype.HaplotypeImageService;

public class HaplotypeImageRHeatmapServiceImpl implements HaplotypeImageService {

	String destdir;

	public HaplotypeImageRHeatmapServiceImpl(String tempDir) {

		this.destdir = tempDir;
	}

	@Override
	public void HaplotypeImageService(String distdir) {

		this.destdir = destdir;
	}

	@Override
	public boolean createImage(String pedfile, String mapfile, String summaryfile, String format, List<Locus> listLocus,
			boolean genomic_coords, double resolution, double local_weight, int kgroup, double kheight,
			String autogroup, String imagesize) {

		//

		destdir = AppContext.getFlatfilesDir() + "temp/";

		boolean success = false;
		try {

			BufferedWriter bwlog = new BufferedWriter(new FileWriter(destdir + pedfile + ".cmds.log"));

			String system = "linux";
			if (AppContext.isWindows())
				system = "win";
			
			// File fileScript=new File(AppContext.getFlatfilesDir() + "geno_heatmap.R");
			File fileScript = new File(AppContext.getHaploscriptsDir() + "geno_heatmap.R");
			File fileDest = new File(destdir);
			File fileR = new File(AppContext.getPathToR());

			String strDisplayGene = "F";
			String genefilename = mapfile + ".gene";
			if (listLocus != null) {
				strDisplayGene = "T"; 	
				BufferedWriter bwgene = new BufferedWriter(
						new FileWriter(fileDest.getAbsolutePath() + "/" + genefilename));
				bwgene.append("name\tstart\tend\tstrand\n");
				Iterator<Locus> itLoc = listLocus.iterator();
				while (itLoc.hasNext()) {
					Locus loc = itLoc.next();
					if (loc.getUniquename().contains(".")) {
						// cds or exon
						bwgene.append("\t" + loc.getFmin() + "\t" + loc.getFmax() + "\t" + loc.getStrand() + "\n");
					} else {
						bwgene.append(loc.getUniquename() + "\t" + loc.getFmin() + "\t" + loc.getFmax() + "\t"
								+ loc.getStrand() + "\n");
					}
				}
				bwgene.close();
			}
			String strGenomicCoord = "F";
			if (genomic_coords)
				strGenomicCoord = "T";

			// DOCKER R WINDOWS
//			String rDocker = "C:\\Program Files\\Docker\\Docker\\resources\\bin\\docker.exe";
//			String[] argarr = (String[]) ArrayUtils.addAll(
//					new String[] { rDocker, "run", "--rm", "-v", "/D/apache-tomcat-8.5.50/webapps/temp:/01_data", "-v",
//							"/D/docker/02_code:/02_code", "-v", "/D/docker/03_output:/03_output", "rsnpseeknotmin",
//							"Rscript", "--verbose", "/02_code/geno_heatmap.R", "linux", format, "/01_data/", pedfile,
//							mapfile, summaryfile, strGenomicCoord, strDisplayGene, genefilename,
//							Double.toString(resolution), Double.toString(local_weight), Integer.toString(kgroup),
//							Double.toString(kheight), autogroup, imagesize + imagesize });
//
//			String cmdline = rDocker + " run" + " --rm " + " -v \"/D/apache-tomcat-8.5.50/webapps/temp\":/01_data "
//					+ " -v \"/D/docker/02_code\":/02_code" + " -v \"/D/docker/03_output\":/03_output"
//					+ " rsnpseeknotmin" + " Rscript" + " --verbose " + "/02_code/geno_heatmap.R" + " " + "linux" + " "
//					+ format + " " + "/01_data/" + " " + pedfile + " " + mapfile + " " + summaryfile + " "
//					+ strGenomicCoord + " " + strDisplayGene + " " + genefilename + " " + resolution + " "
//					+ local_weight + " " + kgroup + " " + kheight + " " + autogroup + " " + imagesize + imagesize;
			// END DOCKER WINDOWS R

			// DOCKER LINUX
//			String rDocker = "/usr/bin/docker";
//			String[] argarr = (String[]) ArrayUtils
//					.addAll(new String[] { rDocker, "run", "--rm", "-v", "/IRCstorage/temp:/01_data", "-v",
//							"/IRCstorage/haplo:/02_code", "-v", "/IRCstorage/plink:/03_output", "snpseek/rsnpseeknotmin:1.0",
//							"Rscript", "--verbose", "/02_code/geno_heatmap.R", "linux", format, "/01_data/", pedfile,
//							mapfile, summaryfile, strGenomicCoord, strDisplayGene, genefilename,
//							Double.toString(resolution), Double.toString(local_weight), Integer.toString(kgroup),
//							Double.toString(kheight), autogroup, imagesize + imagesize });
//
//			String cmdline = rDocker + " run" + " --rm " + " -v /IRCstorage/temp:/01_data "
//					+ " -v /IRCstorage/haplo:/02_code" + " -v /IRCstorage/plink:/03_output" + " rsnpseeknotmin:1.0"
//					+ " Rscript" + " --verbose " + "/02_code/geno_heatmap.R" + " " + "linux" + " " + format + " "
//					+ "/01_data/" + " " + pedfile + " " + mapfile + " " + summaryfile + " " + strGenomicCoord + " "
//					+ strDisplayGene + " " + genefilename + " " + resolution + " " + local_weight + " " + kgroup + " "
//					+ kheight + " " + autogroup + " " + imagesize + imagesize;

			// END DOCKER LINUX

			// DOCKER AWS
//			String[] argarr = (String[]) ArrayUtils
//					.addAll(new String[] { "docker", "exec", "rsnpseek", "Rscript",  "--verbose", "/usr/local/src/geno_heatmap.R", system,
//							format, fileDest.getAbsolutePath(), pedfile, mapfile, summaryfile, strGenomicCoord,
//							strDisplayGene, genefilename, Double.toString(resolution), Double.toString(local_weight),
//							Integer.toString(kgroup), Double.toString(kheight), autogroup, imagesize + imagesize });
//			
//			String cmdline = fileR.getAbsolutePath() + " --verbose " + fileScript.getAbsolutePath() + " " + system + " "
//					+ format + " " + fileDest.getAbsolutePath() + " " + pedfile + " " + mapfile + " " + summaryfile
//					+ " " + strGenomicCoord + " " + strDisplayGene + " " + genefilename + " " + resolution + " "
//					+ local_weight + " " + kgroup + " " + kheight + " " + autogroup + " " + imagesize + imagesize;
			// END DOCKER AWS

			List<String> command = new ArrayList<>();

			if (AppContext.isDockerize()) {
				command.add("docker");
				command.add("exec");
				command.add("rsnpseek");
			}
			
			command.add(fileR.getPath());
			command.add("--verbose");
			command.add(fileScript.getAbsolutePath());
			command.add(system);
			command.add(format);
			command.add(fileDest.getAbsolutePath());
			command.add(pedfile);
			command.add(mapfile);
			command.add(summaryfile);
			command.add(strGenomicCoord);
			command.add(strDisplayGene);
			command.add(genefilename);
			command.add(Double.toString(resolution));
			command.add(Double.toString(local_weight));
			command.add(Integer.toString(kgroup));
			command.add(Double.toString(kheight));
			command.add(autogroup);
			command.add(imagesize + imagesize);

			command.forEach(element -> System.out.println(element));
			// NOT DOCKER VERSION
//			String[] argarr = (String[]) ArrayUtils
//					.addAll(new String[] { fileR.getAbsolutePath(), "--verbose", fileScript.getAbsolutePath(), system,
//							format, fileDest.getAbsolutePath(), pedfile, mapfile, summaryfile, strGenomicCoord,
//							strDisplayGene, genefilename, Double.toString(resolution), Double.toString(local_weight),
//							Integer.toString(kgroup), Double.toString(kheight), autogroup, imagesize + imagesize });

			// tempFolder = fileDest.getAbsolutePath().replace("\\\\", "/").replace("\\", "/");
			String cmdline = fileR.getPath() + " --verbose " + fileScript.getAbsolutePath() + " " + system + " "
					+ format + " " + fileDest.getAbsolutePath() + " " + pedfile + " " + mapfile + " " + summaryfile
					+ " " + strGenomicCoord + " " + strDisplayGene + " " + genefilename + " " + resolution + " "
					+ local_weight + " " + kgroup + " " + kheight + " " + autogroup + " " + imagesize + imagesize;
//			
			// NOT DOCKER VERSION

			bwlog.append("executing: " + cmdline);
			AppContext.debug("executing: " + cmdline);

//			AppContext.debug("executing ArgArr: " + argarr);
//
//			ProcessBuilder pbvcf = new ProcessBuilder(argarr);

			AppContext.debug("executing ArgArr: " + command);

			ProcessBuilder pbvcf = new ProcessBuilder(command);
			// ProcessBuilder pbvcf = new ProcessBuilder(cmdline);
			pbvcf.directory(new File(destdir));
			pbvcf.redirectOutput(Redirect.to(new File(destdir + pedfile + ".stdout.log")));
			pbvcf.redirectErrorStream(true);
			try {
				Process pvcf = pbvcf.start();
				// if(pvcf.waitFor()==0) success=true;
				pvcf.waitFor();
				pvcf.getInputStream().close();
				pvcf.getOutputStream().close();
				pvcf.getErrorStream().close();

				success = new File(destdir + pedfile + "." + format).exists();

				if (success) {
					// brhtml.write("<HTML><BODY><IMG src=\"" + AppContext.getHostname() + "/" +
					// AppContext.getTempFolder() + pedfile + "." + format + "\"></BODY></HTML>" );
					AppContext.debug("haptype image success.");

					// String minmaxxy="var minx=125,miny=23,maxx=2080,maxy=3509;";

					/*
					 * String minmaxxy="var minx=455,miny=426,maxx=5316,maxy=9970;";
					 * if(genomic_coords) minmaxxy="var minx=450,miny=933,maxx=5344,maxy=8856;";
					 */
					String genomiccoord = "var genomic_coord=false;\n";
					if (genomic_coords)
						genomiccoord = "var genomic_coord=true;\n";

					if (strDisplayGene.equals("F"))
						genomiccoord += "var show_gene=false\n";
					else
						genomiccoord += "var show_gene=true\n";

					AppContext.debug(" haplotrype images generated");

					writeHaploJS(destdir + pedfile, format, genomiccoord, autogroup, imagesize);

				} else {
					BufferedWriter brhtml = new BufferedWriter(new FileWriter(destdir + pedfile + ".html"));
					brhtml.write("<HTML><BODY>Haplotype image generation failed<BR><BR>");

					BufferedReader br = new BufferedReader(new FileReader(destdir + pedfile + ".stdout.log"));
					String line = null;
					while ((line = br.readLine()) != null) {
						if (line.startsWith("Error:")) {
							brhtml.write(line + "<BR>\n");
							if (line.contains("Insufficient values in manual scale")) {
								brhtml.write("Try increasing CutTree height<BR>\n");
							}
						}
					}
					br.close();
					brhtml.write("\n</BODY></HTML>");
					brhtml.flush();
					brhtml.close();
					brhtml = new BufferedWriter(new FileWriter(destdir + pedfile + ".hctree.html"));
					brhtml.write("<HTML><BODY>Phylogenetic tree generation failed</BODY></HTML>");
					brhtml.flush();
					brhtml.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				bwlog.append(ex.getMessage());
				AppContext.debug("haptype image failed.");
			}
			bwlog.close();

		} catch (Exception ex) {
			ex.printStackTrace();
			AppContext.debug("haptype image failed.");
		}

		return success;

	}

	@Override
	public boolean displayHapotypeTreeImage(String pedfile, String format, double kheight, int imagesize) {
		/*
		 * #args[1] filename #args[2] height #args[3] phylosize #args[4] format #args[4]
		 * dataroot
		 */

		BufferedWriter bwlog = null;
		boolean success = false;
		try {
			bwlog = new BufferedWriter(new FileWriter(destdir + pedfile + ".cmds.log", true));

			String system = "linux";
			if (AppContext.isLocalhost())
				system = "win";
			

			// File fileScript=new File(AppContext.getFlatfilesDir() + "geno_heatmap.R");
			File fileScript = new File(AppContext.getHaploscriptsDir() + "draw_tree.R");
			File fileDest = new File(destdir);
			File fileR = new File(AppContext.getPathToR());

			String[] argarr = (String[]) ArrayUtils.addAll(new String[] { fileR.getAbsolutePath(), "--verbose",
					fileScript.getAbsolutePath(), pedfile + ".ped.newick", Double.toString(kheight),
					Integer.toString(imagesize), format, fileDest.getAbsolutePath() });
			String cmdline = fileR.getAbsolutePath() + " --verbose " + fileScript.getAbsolutePath() + " " + pedfile
					+ ".ped.newick " + Double.toString(kheight) + " " + Integer.toString(imagesize) + " " + format + " "
					+ fileDest.getAbsolutePath();

			bwlog.append("\nexecuting: " + cmdline);
			AppContext.debug("executing: " + cmdline);

			bwlog.close();

			ProcessBuilder pbvcf = new ProcessBuilder(argarr);
			// ProcessBuilder pbvcf = new ProcessBuilder(cmdline);
			pbvcf.directory(new File(destdir));
			pbvcf.redirectOutput(Redirect.to(new File(destdir + pedfile + ".stdout2.log")));
			pbvcf.redirectErrorStream(true);

			Process pvcf = pbvcf.start();
			// if(pvcf.waitFor()==0) success=true;
			pvcf.waitFor();
			pvcf.getInputStream().close();
			pvcf.getOutputStream().close();
			pvcf.getErrorStream().close();
			success = new File(destdir + pedfile + ".ped.hctree." + format).exists();

			BufferedWriter bw = new BufferedWriter(new FileWriter(destdir + pedfile + ".ped.hctree.html"));
			if (success) {
				BufferedReader br = new BufferedReader(
						new FileReader(new File(AppContext.getHaploscriptsDir() + "pretreehtml.txt")));
				// String pedfilename=new File(pedfile).getName();
				String line = null;
				while ((line = br.readLine()) != null) {
					bw.append(line).append("\n");
				}
				bw.append("\n\n\n");

				/*
				 * if(imagesize.equals("0")) bw.append(
				 * "<img src=\"/haplo/notgenerated.png\">\n</div></div>"); else bw.append(
				 * "<img src=\"/temp/" + pedfilename + ".hctree." + format +
				 * "\"  style=\"width:100%\">\n</div></div>");
				 */
				bw.append("<img src=\"/temp/" + pedfile + ".ped.hctree." + format
						+ "\"  style=\"width:100%\">\n</div></div>");
			} else {
				bw.write("<h4>Failed to generate tree image</h4>");
			}
			bw.append("\n</body></html>");
			bw.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return success;

	}

	public double getMaxLog2treeheight(String haplofilename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(destdir + haplofilename + ".log2h.txt"));
			String line = br.readLine();
			String lastline = line;
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (line.isEmpty())
					continue;
				lastline = line;
			}
			return Double.valueOf(lastline);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	public double[] getMinMaxLog2treeheight(String haplofilename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(destdir + haplofilename + ".log2h.txt"));
			String line = br.readLine();
			String lastline = line;
			Double min = null;
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (line.isEmpty())
					continue;
				if (min == null) {
					if (!line.contains("Inf")) {
						try {
							min = Double.valueOf(line);
						} catch (Exception ex) {

						}
					}
				}
				lastline = line;
			}
			return new double[] { min, Double.valueOf(lastline) };
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		
		return null;
	}

	public static void writeHaploJS(String pedfile, String format, String etc, String autogroup, String imagesize) {
		try {

			BufferedWriter bw = new BufferedWriter(new FileWriter(pedfile + ".autogroup.html"));
			String pedfilename = new File(pedfile).getName();
			BufferedReader br = null;

			/*
			 * br = new BufferedReader(new FileReader(new
			 * File(AppContext.getHaploscriptsDir()+"pretreehtml.txt"))); BufferedWriter
			 * bw=new BufferedWriter(new FileWriter(pedfile + ".hctree.html"));
			 * 
			 * String line=null; while( (line=br.readLine())!=null) {
			 * bw.append(line).append("\n"); } bw.append("\n\n\n");
			 */

			/*
			 * if(imagesize.equals("0")) bw.append(
			 * "<img src=\"/haplo/notgenerated.png\">\n</div></div>"); else bw.append(
			 * "<img src=\"/temp/" + pedfilename + ".hctree." + format +
			 * "\"  style=\"width:100%\">\n</div></div>");
			 */
			// bw.append( "<img src=\"/temp/" + pedfilename + ".hctree." + format + "\"
			// style=\"width:100%\">\n</div></div>");

			bw.append("<html><body>\n");
			bw.append("<BR><h4>Hierarchical tree height vs. K-groups</h4><BR>");
			bw.append("<img src=\"/temp/" + pedfilename + ".hvsk.png\" style=\"width:4in;height:4in;\">\n");
			bw.append("<img src=\"/temp/" + pedfilename + ".log2hvsk.png\" style=\"width:4in;height:4in;\">\n");
			bw.append("<img src=\"/temp/" + pedfilename + ".normlog2hvsk.png\" style=\"width:4in;height:4in;\">\n");

			// br.close();

			if (autogroup.equals("pamk") || autogroup.equals("calinski")) {
				bw.append(
						"<BR><BR><h3><a href=\"https://www.rdocumentation.org/packages/fpc/versions/2.1-10/topics/pamk\" target=\"_blank\" >PAMK</a></h3>\n");
				// bw.append("<img src=\"/temp/" + pedfilename + ".pamk.jpeg" + format +
				// "\">\n");
				bw.append("<embed src=\"/temp/" + pedfilename + ".pamk.pdf\"  style=\"width:5in;height:5in\">\n");
				bw.append("<BR><BR><h3>K-means SSE</h3>\n");
				bw.append("<img src=\"/temp/" + pedfilename + ".kmeansSSE.png\">\n");
				bw.append(
						"<BR><BR><h3>Calinski criterion using <a href=\"http://search.r-project.org/library/vegan/html/cascadeKM.html\" target=\"_blank\">cascadeKM</a></h3>\n");
				if (new File(AppContext.getTempDir() + pedfilename + ".calinski.png").exists())
					bw.append("<img src=\"/temp/" + pedfilename + ".calinski.png\">\n");
				else
					bw.append("<h4>cascadeKM failed, using PAMK to calculate number of groups</h4>");
			}

			bw.append("\n</body></html>");
			bw.flush();
			bw.close();

			bw = new BufferedWriter(new FileWriter(pedfile + ".html"));

			// URL url = new URL( AppContext.getHostname() + "/" +
			// AppContext.getHostDirectory() + "/haplo/prehtml.txt");
			// open the url stream, wrap it an a few "readers"
			// br = new BufferedReader(new InputStreamReader(url.openStream()));

			br = new BufferedReader(new FileReader(new File(AppContext.getHaploscriptsDir() + "prehtml.txt")));
			String line = null;
			while ((line = br.readLine()) != null) {
				bw.append(line).append("\n");
			}
			bw.append("\n\n\n");
			br.close();

			if (pedfile.contains(".flapjack."))
				br = new BufferedReader(new FileReader(pedfile.replace(".ped", ".mappl")));
			else
				br = new BufferedReader(new FileReader(pedfile.replace(".ped", ".map")));

			// bw.append("var minx=110,miny=23,maxx=2080,maxy=3509;\n");
			// bw.append(minmaxxy+"\n");
			bw.append("var mapX={};\n");

			long xcount = 0;
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (line.isEmpty())
					continue;
				// chr01 10100044599 0 44599
				String cols[] = line.split("\t");
				xcount++;
				bw.append("mapX[" + xcount + "]=\"" + cols[1] + ";" + cols[0] + "-" + cols[3] + "\";\n");
			}
			bw.append("var nX=" + xcount + ";\n");
			bw.flush();
			br.close();
			br = new BufferedReader(new FileReader(pedfile.replace(".ped", ".summary.txt.clustered.txt")));
			long ycount = 0;
			bw.append("var mapY={};\n");
			line = br.readLine().replace("\"", "");
			if (!line.startsWith("clust_order"))
				throw new RuntimeException("!line.startsWith(clust_order)");
			while ((line = br.readLine()) != null) {
				line = line.replace("\"", "").trim();
				if (line.isEmpty())
					continue;

				// "clust_order" "orig_order" "variety" "iris_id" "pop" "mismatch" "snps"
				// "0001" "2301" "KADA CHOPA::IRGC 34954-1" "IRIS 313-11168" "aus" 29.5 393
				// "0002" "2727" "SANDOCA::GERVEX 1262-C1" "IRIS 313-8139" "japx" 1 393

				String cols[] = line.split("\t");
				ycount++;
				bw.append("mapY[" + Integer.valueOf(cols[0]) + "]=\"" + cols[2] + ";" + cols[3] + ";" + cols[4]
						+ "\";\n");
			}
			bw.append("var nY=" + ycount + ";\n");
			bw.append("\n\n\n");
			bw.append(etc).append("\n");

			bw.flush();

			// url = new URL(AppContext.getHostname() + "/" + AppContext.getHostDirectory()
			// + "/haplo/aphtml.txt");
			// open the url stream, wrap it an a few "readers"
			// br = new BufferedReader(new InputStreamReader(url.openStream()));

			br = new BufferedReader(new FileReader(new File(AppContext.getHaploscriptsDir() + "aphtml.txt")));
			line = null;
			while ((line = br.readLine()) != null) {
				bw.append(line).append("\n");
			}
			bw.append("\n\n\n");
			br.close();

			/*
			 * 
			 * <div align="center"
			 * style="position:static; height: 100%; width: 100%; top:0;left 0;"> <img
			 * src="myImg.png" style="width: 80%; max-width: 300px;"> </div>
			 * 
			 */

			// bw.append("<body><div align=\"center\" style=\"position:static; height: 100%;
			// width: 100%; top:0;left 0;\">\n<img class=\"coords\" src=\"/temp/" +
			// pedfilename + "." + format + "\" style=\"width: 80%; max-width:
			// 3000px;\"></body>\n</html>\n");
			/*
			 * if(imagesize.equals("0")) bw.
			 * append("<body><img class=\"coords\" src=\"/haplo/notgenerated.png\"></body>\n</html>\n"
			 * ); else bw.append("<body><img class=\"coords\" src=\"/temp/" + pedfilename +
			 * "." + format + "\" style=\"width: 100%\"></body>\n</html>\n");
			 */
			bw.append("<body><img class=\"coords\" src=\"/temp/" + pedfilename + "." + format
					+ "\" style=\"width: 100%\"></body>\n</html>\n");

			// pedfilename + "." + format + "\" style=\"width: 80%;\"></body>\n</html>\n");

			// bw.append("<body>\n<img class=\"coords\" src=\"/temp/" + pedfilename + "." +
			// format + "\">\n</body>\n</html>\n");
			bw.close();

			/*
			 * <body> <img class="coords"
			 * src="/temp/snp3kvars-LOC_OS01G01090-30441718616849419.ped.jpeg"> </body>
			 * </html>
			 */

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
