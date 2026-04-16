package org.irri.iric.portal.admin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.io.FilenameUtils;
import org.irri.iric.ds.chado.domain.Locus;
import org.irri.iric.ds.chado.domain.MultiReferencePosition;
import org.irri.iric.ds.chado.domain.Variety;
import org.irri.iric.ds.chado.domain.VarietyPlusPlus;
import org.irri.iric.ds.chado.domain.impl.MultiReferencePositionImpl;
import org.irri.iric.ds.chado.domain.impl.MultiReferencePositionImplAllelePvalue;
import org.irri.iric.ds.chado.domain.model.VGene;
import org.irri.iric.ds.chado.domain.model.VLocusNotes;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.genotype.GenotypeFacade;
import org.irri.portal.properties.WebVariableConstants;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.Notification;
import org.zkoss.zul.Messagebox;

public class WorkspaceLoadLocal {

	private static WorkspaceFacade workspace;

	public static void initUserVarietyList(String email, WorkspaceFacade workspace) {
		workspace = (WorkspaceFacade) AppContext.checkBean(workspace, "WorkspaceFacade");

		File directory = new File(AppContext.getFlatfilesDir() + File.separator + WebVariableConstants.USER_DIR + File.separator
				+ email + File.separator + WebVariableConstants.VARIETY_DIR);
		
		System.out.println("Directory: " + AppContext.getFlatfilesDir() + File.separator + WebVariableConstants.USER_DIR + File.separator
				+ email + File.separator + WebVariableConstants.VARIETY_DIR);

		File[] files = directory.listFiles();

		Set<String> dsSet = new LinkedHashSet();

		if (files != null) {
			for (File file : files) {

				if (file.isDirectory()) {

					String dataset = file.getName();

					dsSet.add(dataset);

					File varDir = new File(directory + File.separator + dataset);

					File[] varFiles = varDir.listFiles();

					if (varFiles != null) {
						for (File var : varFiles) {
							try {
								FileInputStream fi = new FileInputStream(var);
								ObjectInputStream oi = new ObjectInputStream(fi);

								Set<Variety> setVar = new LinkedHashSet();

								while (true) {
									try {
										
										Variety obj = (Variety) oi.readObject();
										setVar.add(obj);

									} catch (EOFException e) {
										break;
									} catch (ClassNotFoundException e) {
										e.printStackTrace();
									}
								}

								oi.close();
								fi.close();

								workspace.addVarietyList(var.getName(), setVar, dsSet);

							} catch (FileNotFoundException e) {
								System.out.println("File not found");
							} catch (IOException e) {
								System.out.println("Error initializing stream");
							}
						}
					}

				} else {
					System.out.println("File: " + file.getName());
				}
			}
		} else {
			System.out.println("This directory is empty.");
		}
	}

	public static void writeListToUserList(String listname, String type, Set list, String email) {

		File directory = new File(AppContext.getFlatfilesDir() + File.separator + WebVariableConstants.USER_DIR + File.separator
				+ email + File.separator + type);

		// Check if the directory exists; if not, create it
		if (!directory.exists()) {
			boolean isCreated = directory.mkdirs();
			if (isCreated) {
				System.out.println("Directory created: ");
			} else {
				System.out.println("Failed to create directory.");
				return;
			}
		}

		// Create the full file path
		File file = new File(directory + File.separator + listname);

		try {
			FileOutputStream f = new FileOutputStream(file);
			ObjectOutputStream o = new ObjectOutputStream(f);

			Iterator varIter = list.iterator();

			while (varIter.hasNext()) {
				Variety var;
				Locus loc;

				Object obj = varIter.next();

				if (obj instanceof VarietyPlusPlus) {
					o.writeObject((VarietyPlusPlus) obj);
					continue;
				}if (obj instanceof Locus)
					o.writeObject((Locus) obj);
				if (obj instanceof Variety)
					o.writeObject((Variety) obj);
				if (obj instanceof String)
					o.writeObject((String) obj);
			}

			o.close();
			f.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}

	}

	public static void loadSNPLocalFile(String folder, WorkspaceFacade workspace, GenotypeFacade genotype) {
		genotype = (GenotypeFacade) AppContext.checkBean(genotype, "GenotypeFacade");

		String organism = AppContext.getDefaultOrganism();

		boolean hasAllele = false;
		boolean hasPvalue = false;

		File directoryPath = new File(folder);
		// List of all files and directories
		File filesList[] = directoryPath.listFiles();

		Map<String, Map> mapChr2Pos2Pvalue;
		Map<String, Map> mapChr2Pos2Allele;

		Map<String, Set> mapChr2Set;

		BufferedReader reader;

		if (filesList != null) {
			for (File file : filesList) {

				mapChr2Set = new TreeMap();

				mapChr2Pos2Pvalue = new HashMap();
				mapChr2Pos2Allele = new HashMap();

				try {
					reader = new BufferedReader(new FileReader(file));
					String line = reader.readLine();

					while ((line = reader.readLine()) != null) {

						try {
							String chrposline = line.trim();

							if (chrposline.isEmpty()) {
								System.out.println("EMPTY >>> check");
								continue;
							}
							String chrpos[] = chrposline.split("\\s+");
							String chr = "";
							try {
								int intchr = Integer.valueOf(chrpos[0]);
								if (intchr > 9)
									chr = "chr" + intchr;
								else
									chr = "chr0" + intchr;
							} catch (Exception ex) {
								chr = chrpos[0].toLowerCase();
							}

							BigDecimal pos = null;
							try {
								pos = BigDecimal.valueOf(Long.valueOf(chrpos[1]));
							} catch (Exception ex) {
								AppContext.debug("Invalid position chrome position");
								continue;
							}

							Map<BigDecimal, String> mapPos2Allele = mapChr2Pos2Allele.get(chr);
							if (mapPos2Allele == null) {
								mapPos2Allele = new HashMap();
								mapChr2Pos2Allele.put(chr, mapPos2Allele);
							}
							Map<BigDecimal, Double> mapPos2Pvalue = mapChr2Pos2Pvalue.get(chr);
							if (mapPos2Pvalue == null) {
								mapPos2Pvalue = new HashMap();
								mapChr2Pos2Pvalue.put(chr, mapPos2Pvalue);
							}

							if (hasAllele) {
								mapPos2Allele.put(pos, chrpos[2]);
								if (hasPvalue) {
									try {
										mapPos2Pvalue.put(pos, Double.valueOf(chrpos[3]));
									} catch (Exception ex) {
										AppContext.debug("Invalid p-value " + chrpos[3]);
									}
								}
							} else if (hasPvalue) {
								try {
									mapPos2Pvalue.put(pos, Double.valueOf(chrpos[2]));
								} catch (Exception ex) {
									AppContext.debug("Invalid number " + chrpos[2]);
								}
							}

							Set setPos = mapChr2Set.get(chr);
							if (setPos == null) {
								setPos = new HashSet();
								mapChr2Set.put(chr, setPos);
							}
							setPos.add(pos);

						} catch (Exception ex) {
							AppContext.debug("onbuttonSaveSNP exception: ");
							ex.printStackTrace();
						}

					}

					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

				Set<MultiReferencePosition> setSNPDBPos = new HashSet();
				// Set<MultiReferencePosition> setCoreSNPDBPos = new HashSet();

				Set setSNP = null;
				Set setChrSNP = new HashSet();
				Iterator<String> itChr = mapChr2Set.keySet().iterator();
				while (itChr.hasNext()) {
					String chr = itChr.next();
					setSNP = mapChr2Set.get(chr);

					if (hasAllele || hasPvalue) {
						Iterator<BigDecimal> itPos = setSNP.iterator();
						while (itPos.hasNext()) {
							BigDecimal ipos = itPos.next();
							setChrSNP.add(new MultiReferencePositionImplAllelePvalue(organism, chr, ipos,
									(String) mapChr2Pos2Allele.get(chr).get(ipos),
									(Double) mapChr2Pos2Pvalue.get(chr).get(ipos)));
						}

					} else {

						Iterator<BigDecimal> itPos = setSNP.iterator();
						while (itPos.hasNext()) {
							setChrSNP.add(new MultiReferencePositionImpl(organism, chr, itPos.next()));
						}
					}

				}

				onbuttonSaveSNPInChr(workspace, file.getName(), setChrSNP, null, null, hasAllele, hasPvalue);

			}
		}
	}

	private static void onbuttonSaveSNPInChr(final WorkspaceFacade workspace, String filename, Set setSNP,
			Set setSNPDBPos, Set setCoreSNPDBPos, final boolean hasAllele, final boolean hasPvalue) {

		if (setCoreSNPDBPos == null && setSNPDBPos != null) {
			setCoreSNPDBPos = new HashSet(setSNPDBPos);
		}

		String newlistname = filename.replaceAll(":", "").trim();
		newlistname = FilenameUtils.removeExtension(newlistname);

		if (setSNPDBPos == null && setCoreSNPDBPos == null) {

			workspace.addSnpPositionList("ANY", newlistname, setSNP, hasAllele, hasPvalue);

			return;
		}

	}

	public static void initUserList(String type, String email, WorkspaceFacade workspace) {
		workspace = (WorkspaceFacade) AppContext.checkBean(workspace, "WorkspaceFacade");

		File directory = new File(AppContext.getFlatfilesDir() + File.separator + WebVariableConstants.USER_DIR + File.separator
				+ email + File.separator + type);

		File[] files = directory.listFiles();

		if (files != null) {
			for (File file : files) {
				try {
					FileInputStream fi = new FileInputStream(file);
					ObjectInputStream oi = new ObjectInputStream(fi);

					Set setVar = new LinkedHashSet();

					while (true) {
						try {
							if (oi.readObject() instanceof VLocusNotes) {
								VLocusNotes obj = (VLocusNotes) oi.readObject();
								setVar.add(obj);
							}
							
							if (oi.readObject() instanceof VGene) {
								VGene obj = (VGene) oi.readObject();
								setVar.add(obj);
							}
							
							

						} catch (EOFException e) {
							break;
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					}

					oi.close();
					fi.close();

					if (type.equals(WebVariableConstants.LOCUS_DIR))
						workspace.addLocusList(file.getName(), setVar);

				} catch (FileNotFoundException e) {
					System.out.println("File not found");
				} catch (IOException e) {
					System.out.println("Error initializing stream");
				}

			}
		}

	}

	public static void writeListToUserList(String listname, String type, String list, String email) {
		File directory = new File(AppContext.getFlatfilesDir() + File.separator + "users" + File.separator + email
				+ File.separator + type);

		// Check if the directory exists; if not, create it
		if (!directory.exists()) {
			boolean isCreated = directory.mkdirs();
			if (isCreated) {
				System.out.println("Directory created: ");
			} else {
				System.out.println("Failed to create directory.");
				return;
			}
		}

		// Create the full file path
		File file = new File(directory + File.separator + listname);

		// Use BufferedWriter to write content to the file
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.write(list); // Write the content to the file
			System.out.println("Content written to file: " + file.getAbsolutePath());
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}

	}

}
