package org.irri.iric.portal.admin;

import org.irri.iric.portal.AppContext;
import org.springframework.stereotype.Component;

@Component("QueryIric")
public class QueryIric {

	public QueryIric() {
		super();
	}

	// @Autowired
	// private VSnpAllvarsMinDAO allvarsdao;
	//
	// @Autowired
	// private VSnpAllvarsPosDAO allvarsposdao;
	//
	// private static final String CONFIG_PATH =
	// "classpath*:applicationContext-business.xml";
	//
	//
	//
	// public QueryIric() {
	// super();
	// 
	//
	//
	// allvarsdao = (VSnpAllvarsMinDAO)AppContext.checkBean(allvarsdao,
	// "VSnpAllvarsMinDAO");
	// allvarsposdao = (VSnpAllvarsPosDAO)AppContext.checkBean(allvarsposdao,
	// "SnpsAllvarsPosDAO");
	//
	// System.out.println("QueryIric created");
	//// allvarsdao = new VSnpAllvarsMinDAOImpl();
	//// allvarsposdao = new VSnpAllvarsPosDAOImpl();
	// }
	//
	// public void createSNPFileAllvars() {
	//
	//
	// BigDecimal start = new BigDecimal(1000000);
	// BigDecimal end = new BigDecimal(5000000);
	//
	// try {
	//
	//
	// for(int iChr=12; iChr>0; iChr-- ) {
	// Integer chr = Integer.valueOf(iChr);
	//
	// System.out.println("Reading chr " + chr + " bp " + start + "-" + end);
	//
	// Set setsortedpos = new TreeSet( new SnpAllvarsPosSorter());
	// setsortedpos.addAll(allvarsposdao.findVSnpAllvarsPosByChrPosBetween(chr,
	// start, end)); // .findVSnpAllvarsPosByChr(chr));
	// List<SnpsAllvarsPos> snpposlist = new ArrayList();
	// snpposlist.addAll(setsortedpos);
	// setsortedpos = null;
	//
	// long nSnps = snpposlist.size();
	//
	// //File file = new File("E:/My Document/Transfer/iricquery_out/allvarsnp-chr"
	// + chr + ".txt");
	// File file = new File(AppContext.getTempDir() + "allvarsnp-chr" + chr +
	// ".txt");
	//
	// System.out.println(nSnps + " total snps for chr " + chr + ", writing to
	// file:" + file.getAbsolutePath());
	//
	// BufferedWriter output = new BufferedWriter(new FileWriter(file));
	//
	// AppContext.startTimer();
	//
	// //for(int iVar=1; iVar<=3000; iVar++) {
	//
	// System.out.println("reading all vars chr" + chr);
	// Set setsortedvars = new TreeSet(new SnpAllvarsVarPosSorter());
	// setsortedvars.addAll( allvarsdao.findVSnpAllvarsByChrPosBetween(chr, start,
	// end )); // .findVSnpAllvarsMinByChr( chr ) );
	//
	// //if(setsortedvars.size()!=nSnps) {
	// // System.out.println( "number of snps did not match:" + nSnps + " refpos, "
	// + setsortedvars.size() + " varid=" + iVar );
	// //throw new RuntimeException( "number of snps did not match:" + nSnps + "
	// refpos, " + setsortedvars.size() + " varid=" + iVar);
	// // continue;
	// //}
	//
	// System.out.println(setsortedvars.size() + " alleles");
	//
	// StringBuffer buff = new StringBuffer();
	// Iterator<SnpsAllvars> itSet = setsortedvars.iterator();
	// int snpnum = 0;
	// BigDecimal prevVar= BigDecimal.ZERO;
	// boolean firstVar=true;
	// while(itSet.hasNext()) {
	// SnpsAllvars snpvar = itSet.next();
	//
	// if(!firstVar && !snpvar.getVar().equals(prevVar))
	// {
	// // new var, finish prev var up to end
	// while(snpnum<nSnps) {
	// buff.append( "\t0");
	// snpnum++;
	// //if(snpnum<nSnps) buff.append( "\t");
	// }
	// buff.append("\n");
	// output.write(buff.toString());
	// output.flush();
	//
	// AppContext.resetTimer(" next variety," + snpvar.getVar());
	// prevVar=snpvar.getVar();
	// snpnum = 0;
	// firstVar=false;
	// }
	//
	//
	// while(
	// snpposlist.get(snpnum).getPos().longValue()<snpvar.getPos().longValue() ) {
	// buff.append( "0\t");
	// snpnum++;
	// }
	//
	// buff.append( snpvar.getVarnuc() );
	// if( itSet.hasNext() ) buff.append("\t");
	// snpnum++;
	//
	// }
	//
	// while(snpnum<nSnps) {
	// buff.append( "\t0");
	// snpnum++;
	// //if(snpnum<nSnps) buff.append( "\t");
	// }
	// buff.append("\n");
	// output.write(buff.toString());
	// output.flush();
	//
	//
	//
	// //}
	// output.close();
	// }
	//
	//
	// } catch ( IOException e ) {
	// e.printStackTrace();
	// }
	//
	// }
	//
	//
	// public void createSNPFile() {
	//
	// System.out.println("in createSNPFile..." );
	// SNPUni3kVarietiesAllele1DAO snpuniDAO = new SNPUni3kVarietiesAllele1DAO();
	// Map mapVar2Str = snpuniDAO.readSNPString("1", 1000, 1100);
	// Iterator itVar = mapVar2Str.keySet().iterator();
	// while(itVar.hasNext()) {
	// Object var = itVar.next();
	// System.out.println( var + " : " + mapVar2Str.get(var));
	// }
	//
	// }
	// public void createSNPFile2() {
	// try {
	//
	//
	// for(int iChr=12; iChr>0; iChr-- ) {
	// Integer chr = Integer.valueOf(iChr);
	//
	// System.out.println("Reading chr " + chr);
	//
	// Set setsortedpos = new TreeSet( new SnpAllvarsPosSorter());
	// setsortedpos.addAll(allvarsposdao.findVSnpAllvarsPosByChr(chr));
	// List<SnpsAllvarsPos> snpposlist = new ArrayList();
	// snpposlist.addAll(setsortedpos);
	// setsortedpos = null;
	//
	// long nSnps = snpposlist.size();
	//
	// //File file = new File("E:/My Document/Transfer/iricquery_out/allvarsnp-chr"
	// + chr + ".txt");
	// File file = new File(AppContext.getTempDir() + "allvarsnp-chr" + chr +
	// ".txt");
	//
	// System.out.println(nSnps + " total snps for chr " + chr + ", writing to
	// file:" + file.getAbsolutePath());
	//
	// BufferedWriter output = new BufferedWriter(new FileWriter(file));
	//
	// AppContext.startTimer();
	//
	// for(int iVar=1; iVar<=3000; iVar++) {
	//
	// System.out.println("reading var " + iVar + ", chr" + chr);
	// Set setsortedvars = new TreeSet(new SnpAllvarsVarPosSorter());
	// setsortedvars.addAll( allvarsdao.findVSnpAllvarsMinByChrVar( chr ,
	// BigDecimal.valueOf(iVar)) );
	//
	// //if(setsortedvars.size()!=nSnps) {
	// // System.out.println( "number of snps did not match:" + nSnps + " refpos, "
	// + setsortedvars.size() + " varid=" + iVar );
	// //throw new RuntimeException( "number of snps did not match:" + nSnps + "
	// refpos, " + setsortedvars.size() + " varid=" + iVar);
	// // continue;
	// //}
	//
	//
	// StringBuffer buff = new StringBuffer();
	// Iterator<SnpsAllvars> itSet = setsortedvars.iterator();
	// int snpnum = 0;
	// while(itSet.hasNext()) {
	// SnpsAllvars snpvar = itSet.next();
	//
	// while(
	// snpposlist.get(snpnum).getPos().longValue()<snpvar.getPos().longValue() ) {
	// buff.append( "0\t");
	// snpnum++;
	// }
	//
	// buff.append( snpvar.getVarnuc() );
	// if( itSet.hasNext() ) buff.append("\t");
	// snpnum++;
	// }
	//
	// while(snpnum<nSnps) {
	// buff.append( "\t0");
	// snpnum++;
	// //if(snpnum<nSnps) buff.append( "\t");
	// }
	//
	// buff.append("\n");
	// output.write(buff.toString());
	// output.flush();
	// AppContext.resetTimer(" next variety");
	// }
	// output.close();
	// }
	//
	//
	// } catch ( IOException e ) {
	// e.printStackTrace();
	// }
	//
	// }
	//
	// class SnpAllvarsPosSorter implements Comparator {
	//
	// @Override
	// public int compare(Object o1, Object o2) {
	// 
	// return ((SnpsAllvarsPos)o1).getPos().compareTo( ((SnpsAllvarsPos)o2).getPos()
	// );
	// //return 0;
	// }
	// }
	//
	// class SnpAllvarsVarPosSorter implements Comparator {
	//
	// @Override
	// public int compare(Object o1, Object o2) {
	// 
	// int compVar = ((SnpsAllvars)o1).getVar().compareTo(
	// ((SnpsAllvars)o2).getVar() );
	// if(compVar==0)
	// return ((SnpsAllvars)o1).getPos().compareTo( ((SnpsAllvars)o2).getPos() );
	//
	// return compVar;
	// }
	// }
	//

	public static void main(String[] args) {
		

		AppContext.debug("hello world");

		// final ApplicationContext context = new
		// ClassPathXmlApplicationContext(CONFIG_PATH);
		// final QueryIric minimalSpringApp = context.getBean(QueryIric.class);
		// minimalSpringApp.createSNPFile();
		QueryIric q = new QueryIric();
		// q.createSNPFile();

	}

}
