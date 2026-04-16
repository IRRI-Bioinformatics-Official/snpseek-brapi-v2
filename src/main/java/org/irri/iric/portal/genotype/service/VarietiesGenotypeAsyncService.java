package org.irri.iric.portal.genotype.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.Future;

import org.irri.iric.ds.chado.dao.LocusDAO;
import org.irri.iric.ds.chado.domain.GenotypeRunPlatform;
import org.irri.iric.ds.chado.domain.Position;
import org.irri.iric.ds.chado.domain.SnpsAllvarsPos;
import org.irri.iric.ds.chado.domain.SnpsEffect;
import org.irri.iric.ds.chado.domain.StockSample;
import org.irri.iric.ds.chado.domain.Variety;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.CreateZipMultipleFiles;
import org.irri.iric.portal.admin.JobsFacade;
import org.irri.iric.portal.genomics.GenomicsFacade;
import org.irri.iric.portal.genotype.GenotypeQueryParams;
import org.irri.iric.portal.genotype.HaplotypeImageService;
import org.irri.iric.portal.genotype.VariantStringData;
import org.irri.iric.portal.genotype.VariantTable;
import org.irri.iric.portal.genotype.VarietiesGenotypeService;
import org.irri.iric.portal.variety.VarietyFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service("VarietiesGenotypeAsyncService")
@EnableAsync
public class VarietiesGenotypeAsyncService implements VarietiesGenotypeService {

	@Autowired
	VarietiesGenotypeService vargenservice = null;
	@Autowired
	VarietyFacade varietyfacade;
	@Autowired
	@Qualifier("JobsFacade")
	JobsFacade jobsfacade;
	@Autowired
	@Qualifier("LocusNotesDAO")
	LocusDAO locusDAO;

	@Override
	public List checkSNPsInChromosome(Integer organismId, String chr, Collection posset, Set type) {
		return null;
	}

	@Override
	public List<SnpsAllvarsPos> getSNPPoslist(GenotypeQueryParams params) {
		return null;
	}

	@Override
	public List<SnpsEffect> getSnpEffects(List poslist) {
		return null;
	}

	@Override
	public long countSNPPoslist(GenotypeQueryParams params) {

		return -1;
	}

	@Override
	public long countVariantStringData(GenotypeQueryParams params) {

		return -1;
	}

	// @Async("simpleAsyncTaskExecutor")
	@Async("threadPoolTaskExecutor")
	public Future<VariantStringData> queryVariantStringDataAsync(GenotypeQueryParams params) {
		jobsfacade = (JobsFacade) AppContext.checkBean(jobsfacade, "JobsFacade");
		AppContext.resetTimer(
				"queryVariantStringDataAsync started using configured executor: " + Thread.currentThread().getName());
		jobsfacade.startSubmitter(params.getSubmitter());
		try {

			VariantStringData data = _queryVariantStringData(params);

			if (data != null) {
				AppContext.resetTimer("_queryVariantStringDataAsync completed");
				jobsfacade.doneSubmitter(params.getSubmitter());
			} else {
				AppContext.resetTimer("_queryVariantStringDataAsync error");
				jobsfacade.errorSubmitter(params.getSubmitter(), params.getFilename(), "data==null");
			}
			return new AsyncResult<VariantStringData>(null);
		} catch (Exception ex) {
			ex.printStackTrace();
			AppContext.resetTimer("_queryVariantStringDataAsync exception");

			StringBuffer buff = new StringBuffer();
			buff.append(ex.toString() + "\n" + ex.getMessage() + "\n");
			StackTraceElement traces[] = ex.getStackTrace();
			for (int i = 0; i < traces.length; i++) {
				buff.append(traces[i].toString() + "\n");
			}
			jobsfacade.errorSubmitter(params.getSubmitter(), params.getFilename(), buff.toString());

			return new AsyncResult<VariantStringData>(null);
		}
	}

	@Override
	public VariantStringData queryVariantStringData(GenotypeQueryParams params) throws Exception {
		return _queryVariantStringData(params);
		/*
		 * VariantStringData data=new VariantStringData();
		 * if(params.getSubmitter()==null) {
		 * data.setMessage("Submitter ID required for long jobs.");
		 * AppContext.debug("Submitter ID required for long jobs."); return data; }
		 * 
		 * if(jobsfacade.checkSubmitter(params.getSubmitter())) { data.
		 * setMessage("You have a running long job. Please try again when that job is done."
		 * ); AppContext.
		 * debug("You have a running long job. Please try again when that job is done."
		 * ); return data; }
		 * 
		 * AsyncJob job = new AsyncJobImpl( new
		 * File(params.getFilename()).getName().replace(".tmp",""), params,
		 * params.getSubmitter() ); if(jobsfacade.addJob(job)) { Future future =
		 * queryVariantStringDataAsync(params); job.setFuture(future);
		 * AppContext.debug("job submitted..");
		 * data.setMessage(jobsfacade.JOBSTATUS_SUBMITTED); } else {
		 * AppContext.debug("job refused..");
		 * data.setMessage(jobsfacade.JOBSTATUS_REFUSED); }
		 * 
		 * return data;
		 */

		/*
		 * if(!params.isbWaitResult()) { queryVariantStringDataAsync(params);
		 * VariantStringData data=new VariantStringData(); //File f=new
		 * File(params.getFilename().replace(".tmp","")); //data.setMessage(
		 * AppContext.getHostname() + "/" + AppContext.getHostDirectory() +
		 * "/_jobs.zul?jobid=" + f.getName()); return data; } else return
		 * _queryVariantStringData(params);
		 */
	}

	// @Override

	private void writeStatus(String filename, String msg) {
		jobsfacade = (JobsFacade) AppContext.checkBean(jobsfacade, "JobsFacade");
		jobsfacade.setStatus(new File(filename).getName() + ".status", msg);

		/*
		 * while(true) { try { BufferedWriter bw=new BufferedWriter(new
		 * FileWriter(filename + ".status")); bw.append(msg); bw.close(); break; }
		 * catch(Exception ex) { ex.printStackTrace(); try { Thread.sleep(100); }
		 * catch(Exception ex2) { ex2.printStackTrace(); } } }
		 */
	}

	/*
	 * private void writeError(String filename, String msg) { while(true) { try {
	 * BufferedWriter bw=new BufferedWriter(new FileWriter(filename + ".error"));
	 * bw.append(msg); bw.close(); break; } catch(Exception ex) {
	 * ex.printStackTrace(); try { Thread.sleep(100); } catch(Exception ex2) {
	 * ex2.printStackTrace(); } } } }
	 */

	/**
	 * Slice varities into chunks
	 * 
	 * @param cols
	 *            expected number of SNPs
	 * @return
	 */
	/*
	 * private List<Integer[]> sliceVarids(int cols) { return sliceVarids(cols,
	 * null); }
	 */
	private List<Integer[]> sliceVarids4Variety(int cols, Collection varids, int varid_offset) {
		List<Integer[]> listVaridRange = null;

		int maxvars = Long.valueOf(AppContext.getMaxGenotypeElements() / cols).intValue();
		AppContext.debug("sliceVarids: maxvars=" + maxvars + "?" + AppContext.getMaxRows());

		if (varids == null) {
			if (maxvars > AppContext.getMaxRows()) {
				listVaridRange = new ArrayList();
				listVaridRange.add(new Integer[] { 1 + varid_offset, AppContext.getMaxRows() + varid_offset });
			} else {
				listVaridRange = new ArrayList();
				for (int i = 1 + varid_offset; i <= AppContext.getMaxRows() + varid_offset; i += maxvars) {
					listVaridRange.add(
							new Integer[] { i, Math.min(AppContext.getMaxRows() + varid_offset, i + maxvars - 1) });
				}
			}
		} else {
			Set sortedids = new TreeSet(varids);
			Iterator itVars = sortedids.iterator();
			listVaridRange = new ArrayList();
			int previd = -1;
			int laststart = -1;
			StringBuffer buffin = new StringBuffer();
			while (itVars.hasNext()) {
				Object vars = itVars.next();
				int varid = -1;
				if (vars instanceof Variety) {
					varid = ((Variety) vars).getVarietyId().intValue();
				} else {
					varid = ((Number) vars).intValue();
				}

				buffin.append(varid).append(",");
				if (previd > 0) {
					if (previd + 1 == varid) {
						// next varid
						if (laststart + maxvars - 1 == varid) {
							// max vars in listgroup, create range
							listVaridRange.add(new Integer[] { laststart, varid });
							laststart = -1;
							previd = -1;
						} else {
							previd = varid;
						}
					} else {
						// skip varid, create range
						if (laststart > -1) {
							listVaridRange.add(new Integer[] { laststart, previd });
						}
						laststart = varid;
						previd = varid;
					}
				} else {
					// new range
					previd = varid;
					laststart = varid;
				}

				if (laststart > -1 && !itVars.hasNext()) {
					listVaridRange.add(new Integer[] { laststart, varid });
				}

			}

			buffin.append("\n");
			Iterator<Integer[]> itRange = listVaridRange.iterator();
			while (itRange.hasNext()) {
				Integer[] r = itRange.next();
				buffin.append(r[0] + "-" + r[1]).append(", ");
			}
			AppContext.debug("slicing varids: " + buffin.toString());
		}

		/*
		 * 
		 * if(size<100) { } else if(size<500) { listVaridRange=new ArrayList();
		 * listVaridRange.add(new Integer[]{1,1000}); listVaridRange.add(new
		 * Integer[]{1001,2000}); listVaridRange.add(new Integer[]{2001,3024}); } else
		 * if(size<2000) { listVaridRange=new ArrayList(); listVaridRange.add(new
		 * Integer[]{1,500}); listVaridRange.add(new Integer[]{501,1000});
		 * listVaridRange.add(new Integer[]{1001,1500}); listVaridRange.add(new
		 * Integer[]{1501,2000}); listVaridRange.add(new Integer[]{2001,2500});
		 * listVaridRange.add(new Integer[]{2501,3024}); } else { listVaridRange=new
		 * ArrayList(); for(int i=1; i<3000; i+=100) { listVaridRange.add(new
		 * Integer[]{i,i+99}); } listVaridRange.add(new Integer[]{3001,3024}); }
		 */

		return listVaridRange;
	}

	private List<Integer[]> sliceVarids(int cols, Collection varids, int varid_offset) {
		List<Integer[]> listVaridRange = null;
		//int varid_offset = 0;
		int maxvars = Long.valueOf(AppContext.getMaxGenotypeElements() / cols).intValue();
		AppContext.debug("sliceVarids: maxvars=" + maxvars + "?" + AppContext.getMaxRows());
		int maxTemp;
		int minNum;
		if (varids == null) {
			if (maxvars > AppContext.getMaxRows()) {
				listVaridRange = new ArrayList();
				listVaridRange.add(new Integer[] { 1 + varid_offset, AppContext.getMaxRows() + varid_offset });
			} else {
				listVaridRange = new ArrayList();
				for (int i = 1 + varid_offset; i <= AppContext.getMaxRows() + varid_offset; i += maxvars) {
					listVaridRange.add(
							new Integer[] { i, Math.min(AppContext.getMaxRows() + varid_offset, i + maxvars - 1) });
					maxTemp = i + maxvars - 1;
					minNum = Math.min(AppContext.getMaxRows() + varid_offset, maxTemp);
					AppContext.debug("i=" + i + "?" + minNum + "? " + maxTemp);
				}
			}
		} else {
			Set sortedids = new TreeSet(varids);
			Iterator itVars = sortedids.iterator();
			listVaridRange = new ArrayList();
			int previd = -1;
			int laststart = -1;
			StringBuffer buffin = new StringBuffer();
			while (itVars.hasNext()) {
				Object vars = itVars.next();
				int varid = -1;
				if (vars instanceof StockSample) {
					varid = ((StockSample) vars).getStockSampleId().intValue();
				} else if (vars instanceof Variety) {
					varid = ((Variety) vars).getVarietyId().intValue();
				} else {
					varid = ((Number) vars).intValue();
				}

				buffin.append(varid).append(",");
				if (previd > 0) {
					if (previd + 1 == varid) {
						// next varid
						if (laststart + maxvars - 1 == varid) {
							// max vars in listgroup, create range
							listVaridRange.add(new Integer[] { laststart, varid });
							laststart = -1;
							previd = -1;
						} else {
							previd = varid;
						}
					} else {
						// skip varid, create range
						if (laststart > -1) {
							listVaridRange.add(new Integer[] { laststart, previd });
						}
						laststart = varid;
						previd = varid;
					}
				} else {
					// new range
					previd = varid;
					laststart = varid;
				}

				if (laststart > -1 && !itVars.hasNext()) {
					listVaridRange.add(new Integer[] { laststart, varid });
				}

			}

			buffin.append("\n");
			Iterator<Integer[]> itRange = listVaridRange.iterator();
			while (itRange.hasNext()) {
				Integer[] r = itRange.next();
				buffin.append(r[0] + "-" + r[1]).append(", ");
			}
			AppContext.debug("slicing sampleids: " + buffin.toString());
		}

		return listVaridRange;
	}

	private VariantStringData _queryVariantStringData(GenotypeQueryParams params) throws Exception {

		// TODO: DAGS 
		//if(true) {
		// writeStatus(params.getFilename(), JobsFacade.JOBSTATUS_ERROR);
		// //return null;
		// throw new RuntimeException("Asynch _queryVariantStringData disabled.. check
		// sliceVarids to use hdf5 index instead of varids");
		// }

		String chr = "any";
		if (params.isRegion())
			chr = params.getsChr();

		vargenservice = (VarietiesGenotypeService) AppContext.checkBean(vargenservice, "VarietiesGenotypeService");
		AppContext.debug("executing VarietiesGenotypeAsyncService queryVariantStringData");
		jobsfacade = (JobsFacade) AppContext.checkBean(jobsfacade, "JobsFacade");

		List<Integer[]> listVaridRange = null;
		int indelfactor = 1;

		int var_offset = 0;
		if (params.getSetRun().size() > 1)
			throw new RuntimeException(
					"VarietiesGenotypeAsyncService._queryVariantStringData params.getSetRun().size()>1");
		GenotypeRunPlatform r = (GenotypeRunPlatform) params.getSetRun().iterator().next();

		// var_offset=r.getVaridOffset();

		if (params.isbIndel())
			indelfactor = 5;

		listVaridRange = sliceVarids(indelfactor * Integer.valueOf(Long.toString((params.getlEnd() - params.getlStart()) / 10)),
				params.getColVarIds(), var_offset);

		if (params.getPoslist() != null)
			;// listVaridRange=sliceVarids(indelfactor*params.getPoslist().size(),
				// params.getColVarIds(),var_offset);
		else if (params.getlEnd() != null && params.getlStart() != null) {
			// listVaridRange=sliceVarids( indelfactor*Integer.valueOf(
			// Long.toString((params.getlEnd()-params.getlStart())/10)),
			// params.getColVarIds(),var_offset);
		} else if (params.getColLoci() != null) {
			// listVaridRange=sliceVarids( indelfactor*params.getColLoci().size()*10 ,
			// params.getColVarIds(),var_offset);
		}

		// if(!params.hasVarlist()) {
		// if (params.isbCoreonly()) {
		// if (params.getPoslist() != null)
		// listVaridRange = sliceVarids(params.getPoslist().size() * indelfactor,
		// params.getColVarIds(),
		// var_offset);
		// else if (params.getlEnd() != null && params.getlStart() != null) {
		// listVaridRange = sliceVarids(
		// indelfactor * Integer.valueOf(Long.toString((params.getlEnd() -
		// params.getlStart()) / 300)),
		// params.getColVarIds(), var_offset);
		// } else if (params.getColLoci() != null) {
		// listVaridRange = sliceVarids(indelfactor * params.getColLoci().size() * 10 /
		// 30, params.getColVarIds(),
		// var_offset);
		// }
		// } else if (params.getDataset().equals(VarietyFacade.DATASET_SNP_HDRA)) {
		// if (params.getPoslist() != null)
		// listVaridRange = sliceVarids(indelfactor * params.getPoslist().size(),
		// params.getColVarIds(),
		// var_offset);
		// else if (params.getlEnd() != null && params.getlStart() != null) {
		// listVaridRange = sliceVarids(
		// indelfactor * Integer.valueOf(Long.toString((params.getlEnd() -
		// params.getlStart()) / 150)),
		// params.getColVarIds(), var_offset);
		// } else if (params.getColLoci() != null) {
		// listVaridRange = sliceVarids(indelfactor * params.getColLoci().size() * 10 /
		// 15, params.getColVarIds(),
		// var_offset);
		// }
		// } else {
		// // allpos
		// if (params.getPoslist() != null)
		// listVaridRange = sliceVarids(indelfactor * params.getPoslist().size(),
		// params.getColVarIds(),
		// var_offset);
		// else if (params.getlEnd() != null && params.getlStart() != null) {
		// listVaridRange = sliceVarids(
		// indelfactor * Integer.valueOf(Long.toString((params.getlEnd() -
		// params.getlStart()) / 10)),
		// params.getColVarIds(), var_offset);
		// } else if (params.getColLoci() != null) {
		// listVaridRange = sliceVarids(indelfactor * params.getColLoci().size() * 10,
		// params.getColVarIds(),
		// var_offset);
		// }
		// }
		// }

		VariantStringData data = null;

		AppContext.debug(listVaridRange != null ? "listVaridRange=" + listVaridRange.size() : "listVaridRange=null");

		if (listVaridRange != null) {
			Iterator<Integer[]> itVars = listVaridRange.iterator();
			boolean first = true;
			VariantStringData prevvarstrdata = null;

			int rangecnt = 1;
			// Timer time = new Timer();
			// time.start();
			while (itVars.hasNext()) {
				Integer[] varids = itVars.next();

				Map<BigDecimal, Double> mapVarid2Score = new LinkedHashMap();
				Map<BigDecimal, Integer> mapVarid2Columns = new LinkedHashMap();

				AppContext.debug("querying varids  " + varids[0] + "-" + varids[1] + "  " + rangecnt + "/"
						+ listVaridRange.size());
				rangecnt++;

				int midvarid = (varids[1] + varids[0]) / 2;
				AppContext.debug("status=" + Integer.valueOf(midvarid * 100 / AppContext.getMaxRows()) + "%");
				writeStatus(params.getFilename(), Integer.valueOf(midvarid * 100 / AppContext.getMaxRows()) + "%");
				params.setVaridStartEnd(varids);
				params.setColVarIds(null);
				params.setVariantdata(prevvarstrdata);
				data = null;
				// TODO PUT TIMER HERE
				data = vargenservice.queryVariantStringData(params);
				mapVarid2Score.putAll(data.getMapVariety2Mismatch());

				VariantTable varianttable = new VariantAlignmentTableArraysImpl();
				varianttable = vargenservice.fillVariantTable(varianttable, data, params);

				AppContext.debug("async _queryVariantStringData writing partial result."
						+ varianttable.getVariantStringData().getMapVariety2Mismatch().size() + " vars");

				// if(params.getFilename().endsWith(".plink"))
				downloadBigListboxPlinkZip((VariantAlignmentTableArraysImpl) varianttable, params.getFilename(), true,
						first, params);

				if (params.getFilename().endsWith(".flapjack"))
					downloadBigListboxFlapjackZip((VariantAlignmentTableArraysImpl) varianttable, params.getFilename(),
							true, first, (String) params.getDataset().iterator().next());
				else if (!params.getFilename().endsWith(".plink"))
					downloadBigListbox(params, (VariantAlignmentTableArraysImpl) varianttable, params.getFilename(),
							params.getDelimiter(), true, first, (params.isbAlleleFilter() ? "MATCH" : "MISMATCH"),
							params.getOrganismName(), params.isbShowNPBPositions(), params.isbShowAllRefAlleles(),
							(String) params.getDataset().iterator().next());

				Iterator<BigDecimal> itVarid = varianttable.getVariantStringData().getMapVariety2Mismatch().keySet()
						.iterator();
				int columns = varianttable.getVariantStringData().getListPos().size();
				while (itVarid.hasNext()) {
					mapVarid2Columns.put(itVarid.next(), columns);
				}

				writeSummary(first, params, data.getMapVariety2Order(), mapVarid2Score, mapVarid2Columns);

				first = false;
				prevvarstrdata = data;
				prevvarstrdata.clearVarietyData();
			}
			//time.stopAndGetTime();
		} else if (params.hasVarlist()) {
			VariantStringData prevvarstrdata = null;
			Collection colAllvars = params.getColVarIds();

			long maxvars = AppContext.getMaxRows();
			if (params.isRegion()) {
				long region = params.getlEnd() - params.getlStart();
				long maxsnps = region / (10 * indelfactor);

				// if(params.isbCoreonly()) maxsnps=maxsnps/(30*indelfactor);

				maxvars = AppContext.getMaxGenotypeElementsDownload() / maxsnps;
			}

			Iterator<BigDecimal> itVarid = params.getColVarIds().iterator();
			int varcount = 0;
			boolean first = true;
			while (itVarid.hasNext()) {

				Map<BigDecimal, Double> mapVarid2Score = new LinkedHashMap();
				Map<BigDecimal, Integer> mapVarid2Columns = new LinkedHashMap();

				AppContext.debug("status=" + varcount * 100 / colAllvars.size() + "%");
				writeStatus(params.getFilename(), varcount * 100 / colAllvars.size() + "%");
				Set newvars = new HashSet();
				newvars.add(itVarid.next());
				long batchvar = maxvars - 1;
				while (batchvar > 0 && itVarid.hasNext()) {
					newvars.add(itVarid.next());
					varcount++;
					batchvar--;
				}
				params.setColVarIds(newvars);
				params.setVariantdata(prevvarstrdata);
				data = null;
				data = vargenservice.queryVariantStringData(params);
				mapVarid2Score.putAll(data.getMapVariety2Mismatch());

				VariantTable varianttable = new VariantAlignmentTableArraysImpl();
				varianttable = vargenservice.fillVariantTable(varianttable, data, params);

				AppContext.debug("async _queryVariantStringData writing partial result."
						+ varianttable.getVariantStringData().getMapVariety2Mismatch().size() + " vars");

				// if(params.getFilename().endsWith(".plink"))
				downloadBigListboxPlinkZip((VariantAlignmentTableArraysImpl) varianttable, params.getFilename(), true,
						first, params);
				if (params.getFilename().endsWith(".flapjack"))
					downloadBigListboxFlapjackZip((VariantAlignmentTableArraysImpl) varianttable, params.getFilename(),
							true, first, (String) params.getDataset().iterator().next());
				else if (!params.getFilename().endsWith(".plink"))
					downloadBigListbox(params, (VariantAlignmentTableArraysImpl) varianttable, params.getFilename(),
							params.getDelimiter(), true, first, (params.isbAlleleFilter() ? "MATCH" : "MISMATCH"),
							params.getOrganismName(), params.isbShowNPBPositions(), params.isbShowAllRefAlleles(),
							(String) params.getDataset().iterator().next());

				Iterator<BigDecimal> itVarsid = varianttable.getVariantStringData().getMapVariety2Mismatch().keySet()
						.iterator();
				int columns = varianttable.getVariantStringData().getListPos().size();
				while (itVarsid.hasNext()) {
					mapVarid2Columns.put(itVarsid.next(), columns);
				}

				writeSummary(first, params, data.getMapVariety2Order(), mapVarid2Score, mapVarid2Columns);

				first = false;
				prevvarstrdata = data;
				prevvarstrdata.clearVarietyData();
				varcount++;
			}

		} else {
			Map<BigDecimal, Double> mapVarid2Score = new LinkedHashMap();
			Map<BigDecimal, Integer> mapVarid2Columns = new LinkedHashMap();

			writeStatus(params.getFilename(), "0%");
			data = null;
			data = vargenservice.queryVariantStringData(params);
			VariantTable varianttable = new VariantAlignmentTableArraysImpl();
			mapVarid2Score.putAll(data.getMapVariety2Mismatch());
			writeStatus(params.getFilename(), "50%");
			varianttable = vargenservice.fillVariantTable(varianttable, data, params);
			AppContext.debug("async _queryVariantStringData writing all result.");

			// if(params.getFilename().endsWith(".plink"))
			downloadBigListboxPlinkZip((VariantAlignmentTableArraysImpl) varianttable, params.getFilename(), false,
					false, params);
			if (params.getFilename().endsWith(".flapjack"))
				downloadBigListboxFlapjackZip((VariantAlignmentTableArraysImpl) varianttable, params.getFilename(),
						false, false, (String) params.getDataset().iterator().next());
			else if (!params.getFilename().endsWith(".plink"))
				downloadBigListbox(params, (VariantAlignmentTableArraysImpl) varianttable, params.getFilename(),
						params.getDelimiter(), false, false, (params.isbAlleleFilter() ? "MATCH" : "MISMATCH"),
						params.getOrganismName(), params.isbShowNPBPositions(), params.isbShowAllRefAlleles(),
						(String) params.getDataset().iterator().next());

			Iterator<BigDecimal> itVarsid = varianttable.getVariantStringData().getMapVariety2Mismatch().keySet()
					.iterator();
			int columns = varianttable.getVariantStringData().getListPos().size();
			while (itVarsid.hasNext()) {
				mapVarid2Columns.put(itVarsid.next(), columns);
			}

			writeSummary(true, params, data.getMapVariety2Order(), mapVarid2Score, mapVarid2Columns);
		}

		String finalfilename = params.getFilename(); // .replace(".tmp","");
		File fsummary = new File(finalfilename + ".summary.txt");

		// String filenameonly = params.getJobid(); // new
		// File(finalfilename).getName();
		String filenameonly = new File(finalfilename).getName();

		// create haplotype image
		boolean imageSuccess = false;
		String imageformat = "jpeg";
		if (params.isbGenerateHapmap()) {
			HaplotypeImageService hi = new HaplotypeImageRHeatmapServiceImpl(AppContext.getTempDir());

			// String imageformat="tiff";
			// public boolean createImage(String pedfile, String mapfile, String
			// summaryfile, String format)
			String plmap = ".map";
			if (filenameonly.endsWith("flapjack"))
				plmap = ".mappl";
			// s, boolean genomic_coords, double resolution, double local_weight)

			// boolean displayGenes=params.isRegion();
			double resFactor = 10;
			double localWeight = 0.85;

			// if(displayGenes) {
			List listCDS = new ArrayList();
			locusDAO = (LocusDAO) AppContext.checkBean(locusDAO, "LocusNotesDAO");
			if (params.hasChrPosRange()) {
				listCDS = locusDAO.getLocusByRegion(params.getsChr(), params.getlStart(), params.getlEnd(),
						params.getOrganismName(), GenomicsFacade.GENEMODEL_MSU7_ONLY, GenomicsFacade.FEATURETYPE_GENE);
				listCDS.addAll(locusDAO.getLocusByRegion(params.getsChr(), params.getlStart(), params.getlEnd(),
						params.getOrganismName(), GenomicsFacade.GENEMODEL_MSU7_ONLY, GenomicsFacade.FEATURETYPE_CDS));
			} else if (params.hasSnpList()) {
				listCDS = null;
				// listCDS=locusDAO.getLocusByContigPositions(params.getsChr(),
				// params.getPoslist(), params.getOrganism(), 0,
				// GenomicsFacade.GENEMODEL_MSU7,GenomicsFacade.FEATURETYPE_GENE);
				// listCDS.addAll(locusDAO.getLocusByContigPositions(params.getsChr(),
				// params.getPoslist(), params.getOrganism(), 0,
				// GenomicsFacade.GENEMODEL_MSU7,GenomicsFacade.FEATURETYPE_CDS));
			}
			AppContext.debug("genes and exons:" + listCDS);
			imageSuccess = hi.createImage(filenameonly + ".ped", filenameonly + plmap, filenameonly + ".summary.txt",
					imageformat, listCDS, false, resFactor, localWeight, 0, 0, "pamk", "22");
		}

		// boolean imageSuccess=hi.createImage( filenameonly+".ped" , filenameonly+plmap
		// , filenameonly + ".summary.txt", imageformat, true, 10,0.85);

		AppContext.debug("async _queryVariantStringData done.. writing result to " + params.getFilename());
		if (params.getFilename().endsWith(".flapjack")) {
			String filename = params.getFilename();
			String allzipfilenames[] = null;
			if (params.isbGenerateHapmap()) {
				if (imageSuccess)
					allzipfilenames = new String[] { filename + ".map", filename + ".genotype",
							fsummary.getAbsolutePath(), finalfilename + ".ped" + "." + imageformat,
							finalfilename + ".ped.html", fsummary.getAbsolutePath() + ".clustered.txt" };
				else {
					allzipfilenames = new String[] { filename + ".map", filename + ".genotype",
							fsummary.getAbsolutePath() }; // , finalfilename+".ped.stdout.log"};
					jobsfacade.copyToS3Error(filenameonly + ".ped.stdout.log");
				}
			} else
				allzipfilenames = new String[] { filename + ".map", filename + ".genotype",
						fsummary.getAbsolutePath() };

			new CreateZipMultipleFiles(filename + ".zip", allzipfilenames).create(false);
			// Filedownload.save(new File(filename + ".zip") , "application/zip");
			writeStatus(params.getFilename(), JobsFacade.JOBSTATUS_DONE);
		} else if (params.getFilename().endsWith(".plink")) {
			String filename = params.getFilename();
			String allzipfilenames[] = null;
			if (params.isbGenerateHapmap()) {
				if (imageSuccess)
					allzipfilenames = new String[] { filename + ".map", filename + ".ped", fsummary.getAbsolutePath(),
							finalfilename + ".ped" + "." + imageformat, finalfilename + ".ped.html",
							fsummary.getAbsolutePath() + ".clustered.txt" };
				else {
					allzipfilenames = new String[] { filename + ".map", filename + ".ped", fsummary.getAbsolutePath() };
					jobsfacade.copyToS3Error(filenameonly + ".ped.stdout.log");
				}
			} else
				allzipfilenames = new String[] { filename + ".map", filename + ".ped", fsummary.getAbsolutePath() };

			new CreateZipMultipleFiles(filename + ".zip", allzipfilenames).create(false);
			// Filedownload.save(new File(filename + ".zip") , "application/zip");
			writeStatus(params.getFilename(), JobsFacade.JOBSTATUS_DONE);
		} else {

			File file2 = new File(finalfilename);

			/*
			 * BufferedWriter bw=new BufferedWriter(new FileWriter(fsummary));
			 * bw.append("VARIETY").append("\t");
			 * if(params.getDataset().equals(VarietyFacade.DATASET_SNPINDELV2_IUPAC))
			 * bw.append("IRIS ID"); else bw.append("ACCESSION");
			 * bw.append("\t").append("SUBPOPULATION").append("\t");
			 * if(params.isbAlleleFilter()) bw.append("MATCH\t"); else
			 * bw.append("MISMATCH\t"); bw.append("COLUMNS\n");
			 * 
			 * NumberFormat formatter = new DecimalFormat("#0.00");
			 * 
			 * varietyfacade =
			 * (VarietyFacade)AppContext.checkBean(varietyfacade,"VarietyFacade");
			 * Map<BigDecimal,Variety> mapId2Var=
			 * varietyfacade.getMapId2Variety(params.getDataset()); Iterator<BigDecimal>
			 * itVarid=mapVarid2Score.keySet().iterator(); while(itVarid.hasNext()) {
			 * BigDecimal varid=itVarid.next(); Variety var = mapId2Var.get(varid);
			 * bw.append("\"").append(var.getName()).append("\"").append("\t");
			 * if(params.getDataset().equals(VarietyFacade.DATASET_SNPINDELV2_IUPAC))
			 * bw.append(var.getIrisId()); else bw.append(var.getAccession());
			 * bw.append("\t").append(var.getSubpopulation()).append("\t").
			 * append(formatter.format(mapVarid2Score.get(varid))).append("\t").
			 * append(mapVarid2Columns.get(varid).toString()).append("\n");
			 * 
			 * } bw.flush(); bw.close();
			 */
			String allzipfilenames[] = null;
			if (params.isbGenerateHapmap()) {
				if (imageSuccess)
					allzipfilenames = new String[] { file2.getAbsolutePath(), fsummary.getAbsolutePath(),
							finalfilename + ".ped" + "." + imageformat, finalfilename + ".ped.html",
							fsummary.getAbsolutePath() + ".clustered.txt" };
				else {
					allzipfilenames = new String[] { file2.getAbsolutePath(), fsummary.getAbsolutePath() };
					jobsfacade.copyToS3Error(filenameonly + ".ped.stdout.log");
				}
			} else
				allzipfilenames = new String[] { file2.getAbsolutePath(), fsummary.getAbsolutePath() };
			new CreateZipMultipleFiles(finalfilename + ".zip", allzipfilenames).create(false);
			AppContext.debug(finalfilename + ".zip created");

			writeStatus(params.getFilename(), JobsFacade.JOBSTATUS_DONE);
		}

		AppContext.debug("async _queryVariantStringData write result DONE");
		return data;
	}

	private Map orderMap(Map orig) {
		Map map = new TreeMap();
		Iterator it = orig.keySet().iterator();
		while (it.hasNext()) {
			Object key = it.next();
			map.put(orig.get(key), key);
		}
		return map;
	}

	private void writeSummary(boolean first, GenotypeQueryParams params, Map mapVarid2Order, Map mapVarid2Score,
			Map mapVarid2Columns) {

		try {
			String finalfilename = params.getFilename();
			File fsummary = new File(finalfilename + ".summary.txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(fsummary, !first));

			if (first) {
				bw.append("VARIETY").append("\t");
				if (params.getDataset().equals(VarietyFacade.DATASET_SNPINDELV2_IUPAC))
					bw.append("IRIS ID");
				else
					bw.append("ACCESSION");
				bw.append("\t").append("SUBPOPULATION").append("\t");
				if (params.isbAlleleFilter())
					bw.append("MATCH\t");
				else
					bw.append("MISMATCH\t");
				bw.append("COLUMNS\n");
			}
			NumberFormat formatter = new DecimalFormat("#0.00");

			varietyfacade = (VarietyFacade) AppContext.checkBean(varietyfacade, "VarietyFacade");
			Map<BigDecimal, Variety> mapId2Var = varietyfacade.getMapId2Variety(params.getDataset());
			// Iterator<BigDecimal> itVarid=mapVarid2Score.keySet().iterator();

			Map<Integer, BigDecimal> mapord2var = orderMap(mapVarid2Order);
			Iterator<Integer> itOrder = mapord2var.keySet().iterator();
			while (itOrder.hasNext()) {
				Integer ord = itOrder.next();
				BigDecimal varid = mapord2var.get(ord);
				Variety var = mapId2Var.get(varid);
				bw.append("\"").append(var.getName()).append("\"").append("\t");
				if (params.getDataset().equals(VarietyFacade.DATASET_SNPINDELV2_IUPAC))
					bw.append(var.getIrisId().replace(" ", "_"));
				else
					bw.append(var.getAccession().replace(" ", "_"));
				bw.append("\t").append(var.getSubpopulation()).append("\t")
						.append(formatter.format(mapVarid2Score.get(varid))).append("\t")
						.append(mapVarid2Columns.get(varid).toString()).append("\n");
			}
			/*
			 * while(itVarid.hasNext()) { BigDecimal varid=itVarid.next(); Variety var =
			 * mapId2Var.get(varid);
			 * bw.append("\"").append(var.getName()).append("\"").append("\t");
			 * if(params.getDataset().equals(VarietyFacade.DATASET_SNPINDELV2_IUPAC))
			 * bw.append(var.getIrisId()); else bw.append(var.getAccession());
			 * bw.append("\t").append(var.getSubpopulation()).append("\t").
			 * append(formatter.format(mapVarid2Score.get(varid))).append("\t").
			 * append(mapVarid2Columns.get(varid).toString()).append("\n");
			 * 
			 * }
			 */

			bw.flush();
			bw.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// private VariantStringData _queryVariantStringDataPlink(GenotypeQueryParams
	// params) throws Exception
	// {
	//
	//
	// vargenservice= (VarietiesGenotypeService)AppContext.checkBean(vargenservice,
	// "VarietiesGenotypeService") ;
	// AppContext.debug("executing VarietiesGenotypeAsyncService
	// queryVariantStringData");
	//
	//
	// List<Integer[]> listVaridRange=null;
	// if(params.getColVarIds()==null) {
	// // allvars
	// if(params.getPoslist()!=null)
	// listVaridRange=sliceVarids(params.getPoslist().size());
	// else if(params.getlEnd()!=null && params.getlStart()!=null) {
	// listVaridRange=sliceVarids( Integer.valueOf(
	// Long.toString((params.getlEnd()-params.getlStart())/10)));
	// }
	// else if(params.getColLoci()!=null) {
	// listVaridRange=sliceVarids( params.getColLoci().size()*10 );
	// }
	// }
	//
	// Map<BigDecimal,Double> mapVarid2Score=new LinkedHashMap();
	//
	// if(listVaridRange!=null) {
	// Iterator<Integer[]> itVars=listVaridRange.iterator();
	// boolean first=true;
	// VariantStringData prevvarstrdata=null;
	//
	// while(itVars.hasNext()) {
	// Integer[] varids=itVars.next();
	//
	// int midvarid = (varids[1]-varids[0])/2;
	// writeStatus(params.getFilename(), Integer.valueOf(midvarid*100/3024) +"%");
	// params.setVaridStartEnd(varids);
	// params.setVariantdata(prevvarstrdata);
	// VariantStringData data = vargenservice.queryVariantStringData(params);
	// mapVarid2Score.putAll(data.getMapVariety2Mismatch());
	//
	// VariantTable varianttable = new VariantAlignmentTableArraysImpl();
	// varianttable = vargenservice.fillVariantTable(varianttable , data, params);
	// downloadBigListboxPlinkZip( (VariantAlignmentTableArraysImpl)varianttable,
	// params.getFilename(), true, first);
	// first=false;
	// prevvarstrdata=data;
	// prevvarstrdata.clearVarietyData();
	// }
	// }
	// else if (params.getColVarIds()!=null){
	// VariantStringData prevvarstrdata=null;
	// Collection colAllvars=params.getColVarIds();
	//
	// long maxvars=0;
	// if(params.isRegion()) {
	// long region=params.getlEnd()-params.getlStart();
	// long maxsnps=region/10;
	// maxvars = AppContext.getMaxGenotypeElementsDownload()/maxsnps;
	// }
	//
	// Iterator<BigDecimal> itVarid=params.getColVarIds().iterator();
	// int varcount=0;
	// boolean first=true;
	// while(itVarid.hasNext()) {
	// AppContext.debug( "status=" +varcount*100/colAllvars.size() +"%");
	// writeStatus(params.getFilename(), varcount*100/colAllvars.size() +"%");
	// Set newvars=new HashSet();
	// newvars.add( itVarid.next() );
	// long batchvar=maxvars-1;
	// while(batchvar>0 && itVarid.hasNext()) {
	// newvars.add( itVarid.next() );
	// varcount++;
	// batchvar--;
	// }
	// params.setColVarIds(newvars);
	// params.setVariantdata(prevvarstrdata);
	// VariantStringData data = vargenservice.queryVariantStringData(params);
	// mapVarid2Score.putAll(data.getMapVariety2Mismatch());
	// VariantTable varianttable = new VariantAlignmentTableArraysImpl();
	// varianttable = vargenservice.fillVariantTable(varianttable , data, params);
	// downloadBigListboxPlinkZip( (VariantAlignmentTableArraysImpl)varianttable,
	// params.getFilename(), true, first);
	// first=false;
	// prevvarstrdata=data;
	// prevvarstrdata.clearVarietyData();
	// varcount++;
	// }
	//
	// }
	// else {
	// writeStatus(params.getFilename(), "0%");
	// VariantStringData data = vargenservice.queryVariantStringData(params);
	// VariantTable varianttable = new VariantAlignmentTableArraysImpl();
	// mapVarid2Score.putAll(data.getMapVariety2Mismatch());
	// writeStatus(params.getFilename(), "50%");
	// varianttable = vargenservice.fillVariantTable(varianttable , data, params);
	// downloadBigListboxPlinkZip( (VariantAlignmentTableArraysImpl)varianttable,
	// params.getFilename(), false, false);
	// }
	//
	// /*
	// String finalfilename=params.getFilename().replace(".tmp","");
	//
	// File file2 = new File(finalfilename);
	// if(file2.exists()) file2.delete();
	//
	// File f1=new File(params.getFilename());
	// f1.renameTo(file2);
	//
	// File fsummary=new File(finalfilename+".summary.txt");
	// BufferedWriter bw=new BufferedWriter(new FileWriter(fsummary));
	// bw.append("VARIETY").append("\t").append("IRIS
	// ID").append("\t").append("SUBPOPULATION").append("\t");
	// if(params.isbAlleleFilter()) bw.append("MATCH");
	// else bw.append("MISMATCH\n");
	//
	// NumberFormat formatter = new DecimalFormat("#0.00");
	//
	// varietyfacade =
	// (VarietyFacade)AppContext.checkBean(varietyfacade,"VarietyFacade");
	// Map<BigDecimal,Variety> mapId2Var= varietyfacade.getMapId2Variety();
	// Iterator<BigDecimal> itVarid=mapVarid2Score.keySet().iterator();
	// while(itVarid.hasNext()) {
	// BigDecimal varid=itVarid.next();
	// Variety var = mapId2Var.get(varid);
	// bw.append("\"").append(var.getName()).append("\"").append("\t").append(var.getIrisId()).append("\t").append(var.getSubpopulation()).append("\t").
	// append(formatter.format(mapVarid2Score.get(varid))).append("\n");
	// }
	// bw.flush();
	// bw.close();
	//
	// new CreateZipMultipleFiles(finalfilename+".zip", new
	// String[]{file2.getAbsolutePath(), fsummary.getAbsolutePath()}).create(false);
	// AppContext.debug(finalfilename+".zip created");
	//
	// */
	//
	// String filename=params.getFilename();
	// String allzipfilenames[] = new String[]{ filename + ".map", filename +
	// ".ped"};
	// new CreateZipMultipleFiles(filename + ".zip", allzipfilenames ).create();
	// Filedownload.save(new File(filename + ".zip") , "application/zip");
	// writeStatus(params.getFilename(), "DONE");
	//
	// return null;
	// }
	//

	@Override
	public VariantStringData compare2VariantStrings(BigDecimal var1, BigDecimal var2, GenotypeQueryParams params)
			throws Exception {
		return null;
	}

	@Override
	public VariantTable fillVariantTable(VariantTable table, VariantStringData data, GenotypeQueryParams params)
			throws Exception {
		return null;
	}

	private void downloadBigListbox(GenotypeQueryParams params, VariantAlignmentTableArraysImpl table, String filename,
			String delimiter, boolean isAppend, boolean isAppendFirst, String strmismatch, String reference,
			boolean showNPBPosition, boolean showAllRefAlleles, String dataset) {

		try {

			// Object2StringMultirefsMatrixModel matrixmodel =
			// (Object2StringMultirefsMatrixModel)biglistboxArray.getModel();
			// VariantAlignmentTableArraysImpl table =
			// (VariantAlignmentTableArraysImpl)matrixmodel.getData();

			StringBuffer buff = new StringBuffer();
			String refs[] = table.getReference();

			if (!isAppend || isAppendFirst) {

				buff.append(reference.toUpperCase() + " POSITIONS").append(delimiter).append("IRIS ID")
						.append(delimiter).append("SUBPOPULATION").append(delimiter).append(strmismatch)
						.append(delimiter);

				/*
				 * if(this.checkboxShowNPBPosition.isChecked())
				 * buff.append(this.listboxReference.getSelectedItem().getLabel().toUpperCase()
				 * + " POSITIONS").append(delimiter).append("IRIS ID").append(delimiter).append(
				 * "SUBPOPULATION").append(delimiter).append("MISMATCH").append(delimiter); else
				 * buff.append("VARIETY").append(delimiter).append("IRIS ID").append(delimiter).
				 * append("SUBPOPULATION").append(delimiter).append("MISMATCH").append(delimiter
				 * );
				 */

				String[] contigs = table.getContigs();
				// BigDecimal[] positions = table.getPosition();
				Position[] positions = table.getPosition();
				StringBuffer buffPos = new StringBuffer();

				// check if multiple contig

				boolean isMulticontig = false;
				String contig0 = positions[0].getContig();
				for (int i = 2; i < positions.length; i++) {
					if (!contig0.equals(positions[i].getContig())) {
						isMulticontig = true;
						break;
					}
				}

				for (int i = 0; i < positions.length; i++) {
					if (isMulticontig)
						buff.append(positions[i].getContig()).append("-");
					buff.append(positions[i].getPosition());
					if (i < positions.length - 1)
						buff.append(delimiter);
				}

				buff.append("\n" + reference.toUpperCase() + " ALLELES").append(delimiter).append(delimiter)
						.append(delimiter).append(delimiter);

				/*
				 * if(this.checkboxShowNPBPosition.isChecked()) buff.append("\n" +
				 * listboxReference.getSelectedItem().getLabel().toUpperCase() +
				 * " ALLELES").append(delimiter).append(delimiter).append(delimiter).append(
				 * delimiter); else
				 * buff.append("\nREFERENCE").append(delimiter).append(delimiter).append(
				 * delimiter).append(delimiter);
				 */

				for (int i = 0; i < refs.length; i++) {

					String refnuc = refs[i];
					if (refnuc == null || refnuc.isEmpty()) {
						// tabledata.getIndelstringdata().getMapIndelIdx2Refnuc().get(colIndex-frozenCols);
						BigDecimal pos = table.getVariantStringData().getListPos().get(i).getPosition();
						if (table.getVariantStringData().getIndelstringdata() != null
								&& table.getVariantStringData().getIndelstringdata().getMapIndelpos2Refnuc() != null)
							refnuc = table.getVariantStringData().getIndelstringdata().getMapIndelpos2Refnuc().get(pos);
					}

					if (refnuc == null)
						buff.append("");
					else {
						refnuc = refnuc.substring(0, 1);
						buff.append(refnuc);
					}

					if (i < refs.length - 1)
						buff.append(delimiter);
				}
				buff.append("\n");

				Double refsmatch[] = table.getAllrefallelesmatch();

				if (showNPBPosition) {
					buff.append("NIPPONBARE POSITION").append(delimiter).append("").append(delimiter).append("")
							.append(delimiter).append(delimiter);

					positions = table.getPositionNPB();
					for (int i = 0; i < positions.length; i++) {
						buff.append(positions[i]);
						if (i < positions.length - 1)
							buff.append(delimiter);
					}

					// buff.append("\nNIPPONBARE
					// ALLELES").append(delimiter).append(delimiter).append(delimiter).append(delimiter);
					buff.append("\nNIPPONBARE ALLELES").append(delimiter).append(delimiter).append(delimiter);
					if (refsmatch != null) {
						buff.append(refsmatch[0]);
					}
					buff.append(delimiter);

					refs = table.getReferenceNPB();
					for (int i = 0; i < refs.length; i++) {

						String refnuc = refs[i];
						if (refnuc.isEmpty()) {
							// tabledata.getIndelstringdata().getMapIndelIdx2Refnuc().get(colIndex-frozenCols);
							BigDecimal pos = table.getVariantStringData().getListPos().get(i).getPosition();
							if (table.getVariantStringData().getIndelstringdata() != null && table
									.getVariantStringData().getIndelstringdata().getMapIndelpos2Refnuc() != null)
								refnuc = table.getVariantStringData().getIndelstringdata().getMapIndelpos2Refnuc()
										.get(pos);
						}

						if (refnuc == null)
							buff.append("");
						else {
							refnuc = refnuc.substring(0, 1);
							buff.append(refnuc);
						}

						if (i < refs.length - 1)
							buff.append(delimiter);
					}
					buff.append("\n");

				}

				if (showAllRefAlleles) {
					String allrefsalleles[][] = table.getAllrefalleles();
					String refnames[] = table.getAllrefallelesnames();
					for (int iref = 0; iref < refnames.length; iref++) {
						// buff.append("REF " +
						// refnames[iref]).append(delimiter).append(delimiter).append(delimiter).append(delimiter);

						buff.append("REF " + refnames[iref]).append(delimiter).append(delimiter).append(delimiter);
						if (refsmatch != null) {
							buff.append(refsmatch[iref]);
						}
						buff.append(delimiter);

						String irefs[] = allrefsalleles[iref];
						for (int i = 0; i < refs.length; i++) {

							String refnuc = irefs[i];
							/*
							 * if(refnuc.isEmpty()) {
							 * //tabledata.getIndelstringdata().getMapIndelIdx2Refnuc().get(colIndex-
							 * frozenCols); BigDecimal pos =
							 * table.getVariantStringData().getListPos().get(i).getPos();
							 * if(table.getVariantStringData().getIndelstringdata()!=null &&
							 * table.getVariantStringData().getIndelstringdata().getMapIndelpos2Refnuc()!=
							 * null) refnuc =
							 * table.getVariantStringData().getIndelstringdata().getMapIndelpos2Refnuc().get
							 * (pos); }
							 */
							if (refnuc == null)
								buff.append("-");
							else
								buff.append(refnuc);
							if (i < refs.length - 1)
								buff.append(delimiter);
						}
						buff.append("\n");
					}
				}

				String columnfillers = delimiter + delimiter + delimiter + delimiter + delimiter;
				/*
				 * if(params.getPhenotype()!=null && !params.getPhenotype().isEmpty()) {
				 * sPhenotype=params.getPhenotype; mapVarid2Phenotype =
				 * varietyfacade.getPhenotypeValues(sPhenotype, getDataset());
				 * phenlabel=sPhenotype+delimiter; columnfillers+=delimiter;
				 * phenfiller=delimiter; }
				 */

				String annots[] = table.getSNPGenomicAnnotation(params);
				if (annots != null) {
					buff.append(
							"MSU7 EFFECTS (cds-Non-synonymous/cds-Synonymous/Cds/3'UTR/5'UTR/Exon/splice Acceptor/splice Donor/Gene-intron/Promoter)")
							.append(columnfillers); // .append(delimiter).append(delimiter).append(delimiter).append(delimiter).append(delimiter);
					for (int i = 0; i < annots.length; i++) {
						buff.append(annots[i]);
						if (i < annots.length - 1)
							buff.append(delimiter);
					}
					buff.append("\n");
				}

				String queryallele[] = table.getQueryallele();
				if (queryallele != null) {
					buff.append("Query alleles").append(delimiter).append(delimiter).append(delimiter)
							.append(delimiter);
					for (int ia = 0; ia < queryallele.length; ia++) {
						buff.append(queryallele[ia]);
						if (ia + 1 < queryallele.length)
							buff.append(delimiter);
					}
					buff.append("\n");
				}

			}

			Object varalleles[][] = table.getVaralleles();
			AppContext.debug("mxn=" + varalleles.length + "x" + varalleles[0].length);
			AppContext.debug("positions = " + refs.length);
			AppContext.debug("varids = " + table.getVarname().length);

			varietyfacade = (VarietyFacade) AppContext.checkBean(varietyfacade, "VarietyFacade");

			// Map<BigDecimal,Variety> mapVarId2Var =
			// varietyfacade.getMapId2Variety(dataset);
			Set ds = new HashSet();
			ds.add(dataset);
			Map<String, Map<BigDecimal, StockSample>> mapDs = varietyfacade.getMapId2Sample(ds);

			if (!isAppend) {

				throw new RuntimeException("isAppend=" + isAppend + " in VarietiesGenotypeAsyncService");
				/*
				 * for(int i=0; i< table.getVarid().length; i++) { String
				 * varname=table.getVarname()[i];
				 * 
				 * //if(delimiter.equals(",") && varname.contains(",")) // varname = "\"" +
				 * varname + "\"";
				 * 
				 * Variety var = mapVarId2Var.get(BigDecimal.valueOf(table.getVarid()[i]));
				 * buff.append("\"").append(varname).append("\"").append(delimiter).append(
				 * var.getIrisId()
				 * ).append(delimiter).append(var.getSubpopulation()).append(delimiter).append(
				 * table.getVarmismatch()[i]).append(delimiter); for(int j=0; j<refs.length;
				 * j++) { Object allele = varalleles[i][j]; if(allele==null) buff.append( "");
				 * else buff.append( varalleles[i][j] ); if(j<refs.length-1)
				 * buff.append(delimiter); } buff.append("\n"); }
				 * 
				 * String filetype = "text/plain"; if(delimiter.equals(","))
				 * filetype="text/csv"; Filedownload.save( buff.toString(), filetype ,
				 * filename); //AppContext.debug("File download complete! Saved to: "+filename);
				 * org.zkoss.zk.ui.Session zksession = Sessions.getCurrent();
				 * AppContext.debug("snpallvars download complete!"+ filename +
				 * " Downloaded to:" + zksession.getRemoteHost() + "  " +
				 * zksession.getRemoteAddr() );
				 */

			} else {
				BufferedWriter bw = null;
				if (isAppendFirst) {
					bw = new BufferedWriter(new FileWriter(filename));
					bw.append(buff);
					bw.flush();
				} else
					bw = new BufferedWriter(new FileWriter(filename, true));

				buff = new StringBuffer();
				for (int i = 0; i < table.getVarid().length; i++) {
					String varname = table.getVarname()[i];

					// if(delimiter.equals(",") && varname.contains(","))
					// varname = "\"" + varname + "\"";

					// Variety var = mapVarId2Var.get(BigDecimal.valueOf(table.getVarid()[i]));
					Map<BigDecimal, StockSample> mapVarId2Var = mapDs.get(table.getDataset()[i]);
					StockSample var = mapVarId2Var.get(BigDecimal.valueOf(table.getVarid()[i]));

					buff.append("\"").append(varname).append("\"").append(delimiter).append(var.getAssay())
							.append(delimiter).append(var.getSubpopulation()).append(delimiter)
							.append(table.getVarmismatch()[i]).append(delimiter);
					for (int j = 0; j < refs.length; j++) {
						Object allele = varalleles[i][j];
						if (allele == null)
							buff.append("");
						else
							buff.append(varalleles[i][j]);
						if (j < refs.length - 1)
							buff.append(delimiter);
					}
					buff.append("\n");
					bw.append(buff);
					bw.flush();
					buff = new StringBuffer();
				}
				bw.close();

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void downloadBigListboxPlinkZip(VariantAlignmentTableArraysImpl table, String filename, boolean isAppend,
			boolean isAppendFirst, GenotypeQueryParams param) {

		// MAP files
		// The fields in a MAP file are:
		// Chromosome
		// Marker ID
		// Genetic distance
		// Physical position
		//
		// PED files
		// The fields in a PED file are
		// Family ID
		// Sample ID
		// Paternal ID
		// Maternal ID
		// Sex (1=male; 2=female; other=unknown)
		// Affection (0=unknown; 1=unaffected; 2=affected)
		// Genotypes (space or tab separated, 2 for each marker. 0=missing)
		//

		// -HapMap file format:
		// The current release consists of text-table files only, with the following
		// columns:
		//
		// Col1: refSNP rs# identifier at the time of release (NB: it might merge
		// with another rs# in the future)
		// Col2: SNP alleles according to dbSNP
		// Col3: chromosome that SNP maps to
		// Col4: chromosome position of SNP, in basepairs on reference sequence
		// Col5: strand of reference sequence that SNP maps to
		// Col6: version of reference sequence assembly (currently NCBI build36)
		// Col7: HapMap genotyping center that produced the genotypes
		// Col8: LSID for HapMap protocol used for genotyping
		// Col9: LSID for HapMap assay used for genotyping
		// Col10: LSID for panel of individuals genotyped
		// Col11: QC-code, currently 'QC+' for all entries (for future use)
		// Col12 and on: observed genotypes of samples, one per column, sample
		// identifiers in column headers (Coriell catalog numbers, example:
		// NA10847).

		if (param.getDataset().size() > 1)
			throw new RuntimeException(
					"VarietiesGenotypeAsyncService.downloadBigListboxPlinkZip params.getDataset().size()>1");
		String dataset = (String) param.getDataset().iterator().next();

		try {
			// String chr= this.selectChr.getValue();
			String chr = null;

			String refs[] = table.getReference();

			StringBuffer buff = new StringBuffer();
			if (!isAppend || (isAppend && isAppendFirst)) {

				String plmap = ".map";
				if (filename.endsWith("flapjack"))
					plmap = ".mappl";
				BufferedWriter bw = new BufferedWriter(new FileWriter(filename + plmap));
				BufferedWriter bwref = new BufferedWriter(new FileWriter(filename + plmap + ".ref")); // use for
																										// haploimage

				// buff.append("POSITION").append(delimiter).append("MISMATCH").append(delimiter);
				Position[] positions = table.getPosition();
				String contigs[] = table.getContigs();
				String snpannots[] = table.getSNPGenomicAnnotation(param);
				StringBuffer buffannot = new StringBuffer();

				for (int i = 0; i < positions.length; i++) {
					if (!refs[i].equals("-")) {
						int pos = positions[i].getPosition().intValue();
						String contig = positions[i].getContig();
						if (contigs != null)
							contig = contigs[i];
						String snpid = null;
						try {
							Integer.valueOf(AppContext.guessChrFromString(contig));
							snpid = "1" + String.format("%02d", Integer.valueOf(AppContext.guessChrFromString(contig)))
									+ String.format("%08d", pos);
							buff.append(contig).append("\t").append(snpid).append("\t0\t").append(pos).append("\n");

						} catch (Exception ex) {
							snpid = contig + "-" + String.format("%08d", pos);
							buff.append(contig).append("\t").append(snpid).append("\t0\t").append(pos).append("\n");
						}

						bwref.append(snpid).append("\t").append(refs[i].subSequence(0, 1)).append("\n");

						if (snpannots != null)
							buffannot.append(snpid).append("\t").append(snpannots[i]).append("\n");
						else
							buffannot.append(snpid).append("\t").append("U").append("\n");

						/*
						 * if(contigs!=null) buff.append(chr).append("\t").append( "1" +
						 * String.format("%02d",
						 * Integer.valueOf(AppContext.guessChrFromString(contigs[i]))) +
						 * String.format("%08d", pos) ) .append("\t0\t").append(pos).append("\n");
						 * //buff.append(chr).append("\t").append("snp" + chr + "-" + pos)
						 * .append("\t0\t").append(pos+1).append("\n"); else
						 * buff.append(chr).append("\t").append( "1" + String.format("%02d",
						 * Integer.valueOf(chr.toLowerCase().replace("chr", ""))) +
						 * String.format("%08d", pos) ) .append("\t0\t").append(pos).append("\n");
						 */
					} else {
						// for indels
						BigDecimal pos = positions[i].getPosition();
						String snpid = null;
						String contig = positions[i].getContig();
						if (contigs != null)
							contig = contigs[i];
						try {
							Integer.valueOf(AppContext.guessChrFromString(contig));
							snpid = "1" + String.format("%02d", Integer.valueOf(AppContext.guessChrFromString(contig)))
									+ String.format("%08d", pos.intValue()) + "."
									+ pos.subtract(BigDecimal.valueOf(pos.longValue()))
											.multiply(BigDecimal.valueOf(100)).intValue();

							buff.append(contig).append("\t").append(snpid).append("\t0\t").append(pos).append("\n");
						} catch (Exception ex) {
							// snpid= contig + "-" + String.format("%08d", pos);
							snpid = contig + "-" + String.format("%08d", pos.intValue()) + "."
									+ pos.subtract(BigDecimal.valueOf(pos.longValue()))
											.multiply(BigDecimal.valueOf(100)).intValue();
							buff.append(contig).append("\t").append(snpid).append("\t0\t").append(pos).append("\n");
						}

						bwref.append(snpid).append("\t").append(refs[i]).append("\n");

					}
					bw.append(buff);
					bw.flush();
					buff = new StringBuffer();
				}

				FileWriter writer = new FileWriter(filename + ".map.annot");
				writer.append(buffannot.toString());
				writer.flush();
				writer.close();

				bwref.flush();
				bwref.close();
				bw.close();
			}

			BufferedWriter bw = null;
			if (isAppend) {
				if (isAppendFirst)
					bw = new BufferedWriter(new FileWriter(filename + ".ped"));
				else
					bw = new BufferedWriter(new FileWriter(filename + ".ped", true));
			} else
				bw = new BufferedWriter(new FileWriter(filename + ".ped"));

			buff = new StringBuffer();

			Object[][] varalleles = table.getVaralleles();
			varietyfacade = (VarietyFacade) AppContext.checkBean(varietyfacade, "VarietyFacade");
			// Map<BigDecimal,Variety> mapVarId2Var =
			// varietyfacade.getMapId2Variety(dataset);
			Set ds = new HashSet();
			ds.add(dataset);
			Map<String, Map<BigDecimal, StockSample>> mapDs = varietyfacade.getMapId2Sample(ds);
			for (int i = 0; i < table.getVarid().length; i++) {

				/*
				 * //if(mapVarId2Var.get(table.getVarid()[i])==null) throw new
				 * RuntimeException("null variety with id=" + table.getVarid()[i]) String
				 * irisid= mapVarId2Var.get( BigDecimal.valueOf(table.getVarid()[i])
				 * ).getIrisId(); if(irisid==null || irisid.isEmpty() || irisid.equals("NA") )
				 * irisid=mapVarId2Var.get( BigDecimal.valueOf(table.getVarid()[i])
				 * ).getAccession(); if(irisid==null || irisid.isEmpty() || irisid.equals("NA")
				 * ) irisid=mapVarId2Var.get( BigDecimal.valueOf(table.getVarid()[i])
				 * ).getName(); String indvid=irisid.replaceAll(" ","_"); //String indvid=
				 * mapVarId2Var.get( BigDecimal.valueOf(table.getVarid()[i])
				 * ).getIrisId().replaceAll(" ","_");
				 */
				Map<BigDecimal, StockSample> mapVarId2Var = mapDs.get(table.getDataset()[i]);
				String indvid = AppContext
						.createSampleUniqueName(mapVarId2Var.get(BigDecimal.valueOf(table.getVarid()[i])), ds);

				buff.append(indvid).append("\t").append(indvid).append("\t0\t0\t0\t-9\t");

				// Family ID
				// Sample ID
				// Paternal ID
				// Maternal ID
				// Sex (1=male; 2=female; other=unknown)
				// Affection (0=unknown; 1=unaffected; 2=affected)
				// Genotypes (space or tab separated, 2 for each marker. 0=missing)

				for (int j = 0; j < refs.length; j++) {
					String allele1 = (String) varalleles[i][j];
					if (allele1.isEmpty())
						buff.append("0\t0");
					else {
						String alleles[] = allele1.split("/");
						if (alleles.length == 1)
							buff.append(allele1).append("\t").append(allele1);
						else
							buff.append(alleles[0]).append("\t").append(alleles[1]);
					}
					if (j < refs.length - 1)
						buff.append("\t");
				}
				buff.append("\n");
				bw.append(buff);
				bw.flush();
				buff = new StringBuffer();
			}
			bw.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void downloadBrapiJSONTSV(VariantAlignmentTableArraysImpl table, String filename, boolean isAppend,
			boolean isAppendFirst, String dataset, String studyId) {

		// MAP files
		// The fields in a MAP file are:
		// Chromosome
		// Marker ID
		// Genetic distance
		// Physical position
		//
		// PED files
		// The fields in a PED file are
		// Family ID
		// Sample ID
		// Paternal ID
		// Maternal ID
		// Sex (1=male; 2=female; other=unknown)
		// Affection (0=unknown; 1=unaffected; 2=affected)
		// Genotypes (space or tab separated, 2 for each marker. 0=missing)
		//

		// -HapMap file format:
		// The current release consists of text-table files only, with the following
		// columns:
		//
		// Col1: refSNP rs# identifier at the time of release (NB: it might merge
		// with another rs# in the future)
		// Col2: SNP alleles according to dbSNP
		// Col3: chromosome that SNP maps to
		// Col4: chromosome position of SNP, in basepairs on reference sequence
		// Col5: strand of reference sequence that SNP maps to
		// Col6: version of reference sequence assembly (currently NCBI build36)
		// Col7: HapMap genotyping center that produced the genotypes
		// Col8: LSID for HapMap protocol used for genotyping
		// Col9: LSID for HapMap assay used for genotyping
		// Col10: LSID for panel of individuals genotyped
		// Col11: QC-code, currently 'QC+' for all entries (for future use)
		// Col12 and on: observed genotypes of samples, one per column, sample
		// identifiers in column headers (Coriell catalog numbers, example:
		// NA10847).

		try {
			// String chr= this.selectChr.getValue();
			String chr = null;

			String refs[] = table.getReference();

			StringBuffer buff = new StringBuffer();
			if (!isAppend || (isAppend && isAppendFirst)) {

				String mapfile = filename + ".map";
				if (filename.endsWith("flapjack"))
					mapfile += "pl";
				BufferedWriter bw = new BufferedWriter(new FileWriter(mapfile));

				// buff.append("POSITION").append(delimiter).append("MISMATCH").append(delimiter);
				Position[] positions = table.getPosition();
				String contigs[] = table.getContigs();
				for (int i = 0; i < positions.length; i++) {
					if (!refs[i].equals("-")) {
						int pos = positions[i].getPosition().intValue();
						String contig = positions[i].getContig();
						if (contigs != null)
							contig = contigs[i];
						try {
							Integer.valueOf(AppContext.guessChrFromString(contig));
							buff.append(contig).append("\t")
									.append("1"
											+ String.format("%02d",
													Integer.valueOf(AppContext.guessChrFromString(contig)))
											+ String.format("%08d", pos))
									.append("\t0\t").append(pos).append("\n");
						} catch (Exception ex) {
							buff.append(contig).append("\t").append(contig + "-" + String.format("%08d", pos))
									.append("\t0\t").append(pos).append("\n");
						}

						/*
						 * if(contigs!=null) buff.append(chr).append("\t").append( "1" +
						 * String.format("%02d",
						 * Integer.valueOf(AppContext.guessChrFromString(contigs[i]))) +
						 * String.format("%08d", pos) ) .append("\t0\t").append(pos).append("\n");
						 * //buff.append(chr).append("\t").append("snp" + chr + "-" + pos)
						 * .append("\t0\t").append(pos+1).append("\n"); else
						 * buff.append(chr).append("\t").append( "1" + String.format("%02d",
						 * Integer.valueOf(chr.toLowerCase().replace("chr", ""))) +
						 * String.format("%08d", pos) ) .append("\t0\t").append(pos).append("\n");
						 */
					}
					bw.append(buff);
					bw.flush();
					buff = new StringBuffer();
				}
				bw.close();
			}

			BufferedWriter bw = null;
			if (isAppend) {
				if (isAppendFirst)
					bw = new BufferedWriter(new FileWriter(filename + ".ped"));
				else
					bw = new BufferedWriter(new FileWriter(filename + ".ped", true));
			} else
				bw = new BufferedWriter(new FileWriter(filename + ".ped"));

			buff = new StringBuffer();

			Object[][] varalleles = table.getVaralleles();
			varietyfacade = (VarietyFacade) AppContext.checkBean(varietyfacade, "VarietyFacade");
			Map<BigDecimal, Variety> mapVarId2Var = varietyfacade.getMapId2Variety(dataset);
			for (int i = 0; i < table.getVarid().length; i++) {

				// if(mapVarId2Var.get(table.getVarid()[i])==null) throw new
				// RuntimeException("null variety with id=" + table.getVarid()[i])
				String indvid = mapVarId2Var.get(BigDecimal.valueOf(table.getVarid()[i])).getIrisId().replaceAll(" ",
						"_");
				buff.append(indvid).append("\t").append(indvid).append("\t0\t0\t0\t-9\t");

				// Family ID
				// Sample ID
				// Paternal ID
				// Maternal ID
				// Sex (1=male; 2=female; other=unknown)
				// Affection (0=unknown; 1=unaffected; 2=affected)
				// Genotypes (space or tab separated, 2 for each marker. 0=missing)

				for (int j = 0; j < refs.length; j++) {
					String allele1 = (String) varalleles[i][j];
					if (allele1.isEmpty())
						buff.append("0\t0");
					else {
						String alleles[] = allele1.split("/");
						if (alleles.length == 1)
							buff.append(allele1).append("\t").append(allele1);
						else
							buff.append(alleles[0]).append("\t").append(alleles[1]);
					}
					if (j < refs.length - 1)
						buff.append("\t");
				}
				buff.append("\n");
				bw.append(buff);
				bw.flush();
				buff = new StringBuffer();
			}
			bw.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void downloadBigListboxFlapjackZip(VariantAlignmentTableArraysImpl table, String filename, boolean isAppend,
			boolean isAppendFirst, String dataset) {

		//
		// A data set contains an amalgamation of an imported map file and genotype
		// file.
		// The map file should contain information on the chromosomes (name and length;
		// see warning below) and the markers (name, chromosome, and position). Order
		// does not matter as Flapjack will group and sort them by chromosome and
		// distance once they are loaded. A short example is shown below:
		// # fjFile = MAP
		// 1H 125.0 # For future versions only - do not use yet!
		// Marker1 1H 32.5
		// Marker2 1H 45.4
		// Marker3 2H 23.8
		//
		// The genotype file should contain a list of variety lines, with allele data
		// per marker for that line. It also requires a header line specifying the
		// marker information for each column.
		// # fjFile = GENOTYPE
		// Marker1 Marker2 Marker3
		// Line1 A G G
		// Line2 A - G/T
		// Line3 T A C
		// Both the map file and the genotype file must be in plain-text, tab-delimited
		// format.
		//

		try {
			// String chr= this.selectChr.getValue();
			String chr = null;

			String refs[] = table.getReference();
			String markernames[] = null;

			StringBuffer buff = new StringBuffer();
			if (!isAppend || (isAppend && isAppendFirst)) {

				BufferedWriter bw = new BufferedWriter(new FileWriter(filename + ".map"));

				bw.append("# fjfile = MAP\n");
				// buff.append("POSITION").append(delimiter).append("MISMATCH").append(delimiter);
				Position[] positions = table.getPosition();
				String contigs[] = table.getContigs();

				markernames = new String[positions.length];

				for (int i = 0; i < positions.length; i++) {
					if (!refs[i].equals("-")) {
						int pos = positions[i].getPosition().intValue();
						chr = positions[i].getChr().toString();
						String contigname = null;
						if (contigs != null)
							contigname = contigs[i];
						else {
							try {
								Integer.valueOf(chr.toLowerCase().replace("chr", ""));
								contigname = "chr"
										+ String.format("%02d", Integer.valueOf(chr.toLowerCase().replace("chr", "")));
							} catch (Exception ex) {
								contigname = chr.toLowerCase();
							}

						}
						markernames[i] = "snp-" + contigname + "-" + String.format("%08d", pos);
						buff.append(markernames[i]).append("\t").append(contigname).append("\t").append(pos)
								.append("\n");
					} else {
						// snpid="1" + String.format("%02d",
						// Integer.valueOf(AppContext.guessChrFromString(contig))) +
						// String.format("%08d", pos.intValue()) + "." +
						// pos.subtract(BigDecimal.valueOf(pos.longValue())).multiply(BigDecimal.valueOf(100)).intValue();
						BigDecimal pos = positions[i].getPosition();
						chr = positions[i].getChr().toString();
						String contigname = null;
						if (contigs != null)
							contigname = contigs[i];
						else {
							try {
								Integer.valueOf(chr.toLowerCase().replace("chr", ""));
								contigname = "chr"
										+ String.format("%02d", Integer.valueOf(chr.toLowerCase().replace("chr", "")));
							} catch (Exception ex) {
								contigname = chr.toLowerCase();
							}

						}
						markernames[i] = "snp-" + contigname + "-" + String.format("%08d", pos.intValue()) + "."
								+ pos.subtract(BigDecimal.valueOf(pos.longValue())).multiply(BigDecimal.valueOf(100))
										.intValue(); // String.format("%08d", pos);
						buff.append(markernames[i]).append("\t").append(contigname).append("\t").append(pos)
								.append("\n");
					}
					bw.append(buff);
					bw.flush();
					buff = new StringBuffer();
				}
				bw.close();
			}

			BufferedWriter bw = null;

			if (new File(filename + ".genotype").exists()) {
				AppContext.debug(filename + ".genotype exists");
			}

			if (isAppend) {
				if (isAppendFirst) {
					bw = new BufferedWriter(new FileWriter(filename + ".genotype"));
					AppContext.debug(filename + ".genotype created");
				} else {
					bw = new BufferedWriter(new FileWriter(filename + ".genotype", true));
					AppContext.debug(filename + ".genotype appended");
				}
			} else {
				bw = new BufferedWriter(new FileWriter(filename + ".genotype"));
				AppContext.debug(filename + ".genotype created");
			}

			buff = new StringBuffer();

			if (markernames != null) {
				buff.append("# fjFile = GENOTYPE\n\t");
				for (int i = 0; i < markernames.length; i++) {
					buff.append(markernames[i]);
					if (i < markernames.length - 1)
						buff.append("\t");
				}
				buff.append("\n");
			}

			Object[][] varalleles = table.getVaralleles();
			varietyfacade = (VarietyFacade) AppContext.checkBean(varietyfacade, "VarietyFacade");
			// Map<BigDecimal,Variety> mapVarId2Var =
			// varietyfacade.getMapId2Variety(dataset);
			Set ds = new HashSet();
			ds.add(dataset);
			Map<String, Map<BigDecimal, StockSample>> mapDs = varietyfacade.getMapId2Sample(ds);

			for (int i = 0; i < table.getVarid().length; i++) {

				// if(mapVarId2Var.get(table.getVarid()[i])==null) throw new
				// RuntimeException("null variety with id=" + table.getVarid()[i])
				// String indvid= mapVarId2Var.get( BigDecimal.valueOf(table.getVarid()[i])
				// ).getIrisId().replaceAll(" ","_");
				Map<BigDecimal, StockSample> mapVarId2Var = mapDs.get(table.getDataset()[i]);
				String indvid = AppContext
						.createSampleUniqueName(mapVarId2Var.get(BigDecimal.valueOf(table.getVarid()[i])), ds);
				buff.append(indvid).append("\t");

				// Family ID
				// Sample ID
				// Paternal ID
				// Maternal ID
				// Sex (1=male; 2=female; other=unknown)
				// Affection (0=unknown; 1=unaffected; 2=affected)
				// Genotypes (space or tab separated, 2 for each marker. 0=missing)

				for (int j = 0; j < refs.length; j++) {
					String allele1 = (String) varalleles[i][j];
					if (allele1.isEmpty() || allele1.equals("?"))
						buff.append("-");
					else
						buff.append(allele1);
					if (j < refs.length - 1)
						buff.append("\t");
				}
				buff.append("\n");
				bw.append(buff);
				bw.flush();
				buff = new StringBuffer();
			}
			bw.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
