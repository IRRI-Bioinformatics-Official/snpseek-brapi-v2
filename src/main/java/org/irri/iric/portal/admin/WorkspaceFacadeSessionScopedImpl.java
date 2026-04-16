package org.irri.iric.portal.admin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

//import javax.servlet.http.HttpSession;


import org.irri.iric.portal.AppContext;
import org.springframework.context.annotation.Scope;
//import org.zkoss.zk.ui.Sessions;
//import org.zkoss.zul.Filedownload;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.zkoss.zkmax.zul.Filedownload;

@Service("WorkspaceFacade")
@Scope(value="session",  proxyMode = ScopedProxyMode.INTERFACES)
public class WorkspaceFacadeSessionScopedImpl  implements WorkspaceFacade , Serializable{

	


	//@Autowired
	private UserSessionListsManager sessionmgr;

	public WorkspaceFacadeSessionScopedImpl() {
		super();
		
		AppContext.debug("created WorkspaceFacadeSessionScopedImpl:" + this);
	}
	
	

	@Override
	public void queryIric() {
		
		
	}


	
	private UserSessionListsManager getSessionManager() {
		
		if(sessionmgr==null) sessionmgr=new UserSessionListsManager();
		
		
		return sessionmgr;
	}
	
	@Override
	public Set getVarietylistNames()
	{
		UserSessionListsManager sessionmgr = getSessionManager();
		Set s=sessionmgr.getVarietylistNames();
		AppContext.debug("varlist=" + s.size());
		return s;
	}

	@Override
	public Set getVarieties( String listname) {
		UserSessionListsManager sessionmgr =  getSessionManager();
		return sessionmgr.getVarieties(listname);
	}

	@Override
	public boolean addVarietyList(String name, Set setVarieties, Set dataset) {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.addVarietyList(name, setVarieties,  dataset);
	}


	@Override
	public boolean addVarietyList(String name, Set setVarieties, Set dataset, int hasPhenotype, List phenname, Map<BigDecimal, Object[]> mapVarid2Phen) {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		boolean suc=true;
		for(Object ds:dataset) {
			suc=suc && sessionmgr.addVarietyList(name, setVarieties,  (String)ds, hasPhenotype,phenname, mapVarid2Phen);
		}
		return suc;
	}
	
	
	
	@Override
	public void deleteVarietyList(String listname) {
		UserSessionListsManager sessionmgr = getSessionManager();
		sessionmgr.deleteVarietyList( listname); 
	}


	
	
	/*************************************************************/
	
	@Override
	public Set getLocuslistNames()
	{
		UserSessionListsManager sessionmgr = getSessionManager();
		Set s=sessionmgr.getLocuslistNames();
		AppContext.debug("loclist=" + s.size());
		return s;
	}

	@Override
	public Set getLoci( String listname) {
		UserSessionListsManager sessionmgr =  getSessionManager();
		return sessionmgr.getLoci(listname);
	}


	@Override
	public boolean addLocusList(String name, Set locuslist) {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.addLocusList(name, locuslist);
	}

	@Override
	public void deleteLocusList(String listname) {
		UserSessionListsManager sessionmgr = getSessionManager();
		sessionmgr.deleteLocusList( listname); 
	}
	
	
	
	/*************************************************************/
	
	
	@Override
	public Set getSnpPositions(String chromosome, String name) {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		Set s=sessionmgr.getSNPs(chromosome, name);
		AppContext.debug("snplist=" + s.size());
		return s;
	}


	@Override
	//public boolean addSnpPositionList(Integer chromosome, String name, Set poslist) {
	public boolean addSnpPositionList(String contig, String name, Set poslist, boolean hasAllele, boolean hasPvalue) {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.addSNPList(contig, name, poslist, hasAllele, hasPvalue);
	}
	
	
	


	@Override
	public boolean SNPListhasAllele(String listname) {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.SNPhasAllele(listname);

	}


	@Override
	public boolean SNPListhasPvalue(String listname) {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.SNPhasPvalue(listname);
	}


	@Override
	public Set getSnpPositionListNames() {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.getSNPlistNames();
	}

	@Override
	public Set getSnpPositionAlleleListNames() {
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.getSNPlistAlleleNames();
	}

	@Override
	public Set getSnpPositionPvalueListNames() {
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.getSNPlistPvalueNames();
	}

	
	
	
	@Override
	public Set getVarietyQuantPhenotypelistNames(String dataset) {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.getVarietyQuantPhenotypelistNames(dataset);
	}

	@Override
	public Set getVarietyCatPhenotypelistNames(String dataset) {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.getVarietyCatPhenotypelistNames(dataset);
	}


	@Override
	public Collection getVarietyQuantPhenotypelistNames() {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.getVarietyQuantPhenotypelistNames();
	}

	@Override
	public Collection getVarietyCatPhenotypelistNames() {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.getVarietyCatPhenotypelistNames();
	}
	
	

	@Override
	public Map getVarietylistPhenotypeValues(String phenotype, String dataset) {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		Map m=sessionmgr.getVarietyPhenotypeValues(phenotype.split("\\:\\:")[0], dataset, phenotype.split("\\:\\:")[1]);
		AppContext.debug("1phenvalues for " + phenotype + " , " + m.size());
		m=sessionmgr.getVarietyPhenotypeValues(phenotype.split("\\:\\:")[0], dataset, phenotype.split("\\:\\:")[1]);
		AppContext.debug("1phenvalues for " + phenotype + " , " + m.size());
		return m;
	}
	
	/*
	@Override
	public Set getLocusListNames() {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.getLocuslistNames();
	}
*/

	@Override
	public boolean uploadLists(String mylist) throws Exception {
		//Messagebox.show("Temporarily disabled");
		 UserSessionListsManager sessionmgr = getSessionManager();
		 return sessionmgr.uploadList(mylist);
	}
	
	
	@Override
	public String getMyLists() {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.downloadLists();
	}

	@Override
	public String getMyListsCookie() {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.downloadListsCookie();
	}

	@Override
	public void setMyListsCookie(String mylist) {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		sessionmgr.uploadListCookie(mylist);
	}
	
	
	@Override
	public void downloadLists() {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		String filename = AppContext.getTempDir() + "mylists-" + AppContext.createTempFilename()+ ".txt";
		
		try {
			
			File file = new File(filename);
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write( sessionmgr.downloadLists() );
			bw.flush();
			bw.close();
			
			AppContext.debug("Mylist write complete! Saved to: "+ file.getAbsolutePath());
			
			try {
				String filetype = "text/plain";
				//Filedownload.save(  new File(filename), filetype);
				
				Filedownload.save(  new File(filename), filetype);
				} catch(Exception ex)
				{
					ex.printStackTrace();
				}

		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}


	@Override
	public void deleteSNPList(String chromosome, String listname) {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		sessionmgr.deleteSNPList(chromosome, listname);  
	}



	
}
