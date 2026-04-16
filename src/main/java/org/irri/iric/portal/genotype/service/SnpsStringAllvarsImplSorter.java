package org.irri.iric.portal.genotype.service;

import java.util.Comparator;
import java.util.Set;

import org.irri.iric.ds.chado.domain.SnpsStringAllvars;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Sorts variety by mismatch desc, subpopulation, then country, then id Used in
 * Mismatch ordering for the same number of mismatch, assuming variety from same
 * subpopulation, then country will be closer relative than random
 * 
 * @author lmansueto
 *
 */
public class SnpsStringAllvarsImplSorter implements Comparator {
	@Autowired
	@Qualifier("ListItems")
	private ListItemsDAO listitemsdao;

	private boolean sortByVarId = false;
	private Set dataset;

	public SnpsStringAllvarsImplSorter(Set dataset) {
		super();
		// TODO Auto-generated constructor stub
		listitemsdao = (ListItemsDAO) AppContext.checkBean(listitemsdao, "ListItems");
		this.dataset = dataset;
	}

	public SnpsStringAllvarsImplSorter(boolean sortByVarId, Set dataset) {
		super();
		this.sortByVarId = sortByVarId;
		listitemsdao = (ListItemsDAO) AppContext.checkBean(listitemsdao, "ListItems");
		this.dataset = dataset;
	}

	public SnpsStringAllvarsImplSorter(boolean sortByVarId) {
		super();
		this.sortByVarId = sortByVarId;
		listitemsdao = (ListItemsDAO) AppContext.checkBean(listitemsdao, "ListItems");
	}

	public SnpsStringAllvarsImplSorter() {
		super();
		listitemsdao = (ListItemsDAO) AppContext.checkBean(listitemsdao, "ListItems");
	}

	@Override
	public int compare(Object o1, Object o2) {
		
		SnpsStringAllvars s1 = (SnpsStringAllvars) o1;
		SnpsStringAllvars s2 = (SnpsStringAllvars) o2;

		if (sortByVarId) {
			return s1.getVar().compareTo(s2.getVar());
		}

		int ret = -s1.getMismatch().compareTo(s2.getMismatch());
		if (ret == 0)
			return s1.getVar().compareTo(s2.getVar());
		return ret;

		// if(ret==0) {
		// //return s1.getVar().compareTo( s2.getVar());
		// if(s1 instanceof IndelsStringAllvars && s2 instanceof IndelsStringAllvars) {
		// IndelsStringAllvars is1 = (IndelsStringAllvars)s1;
		// IndelsStringAllvars is2 = (IndelsStringAllvars)s2;
		// //if(is1.getMapPos2Indels().size()<is2.getMapPos2Indels().size()) ret = 1;
		// //else if(is1.getMapPos2Indels().size()>is2.getMapPos2Indels().size()) ret =
		// -1;
		//
		// //int sumIns1 = 0;
		// //int sumIns2 = 0;
		// //int sumDel1 = 0;
		// //int sumDel2 = 0;
		//
		// Set setAlleles1 = new HashSet();
		// Set setAlleles2 = new HashSet();
		// Iterator<IndelsAllvars> itIndels1 =
		// is1.getMapPos2Indels().values().iterator();
		// Iterator<IndelsAllvars> itIndels2 =
		// is1.getMapPos2Indels().values().iterator();
		// while(itIndels1.hasNext()) {
		// IndelsAllvars indel = itIndels1.next();
		// setAlleles1.add( indel.getAllele1() );
		// }
		// while(itIndels2.hasNext()) {
		// IndelsAllvars indel = itIndels2.next();
		// setAlleles2.add( indel.getAllele1() );
		// }
		// Set allele1notin2 = new HashSet(setAlleles1);
		// allele1notin2.removeAll(setAlleles2);
		// Set allele2notin1 = new HashSet(setAlleles2);
		// allele2notin1.removeAll(setAlleles1);
		// Set uniques = new HashSet(allele1notin2);
		// uniques.addAll(allele2notin1);
		// if(allele1notin2.size()>allele2notin1.size())
		// ret = uniques.size();
		// else if(allele1notin2.size()<allele2notin1.size())
		// ret = -uniques.size();
		// else if(uniques.size()!=0) {
		// if(setAlleles1.size()>setAlleles2.size())
		// return setAlleles1.size();
		// else if(setAlleles1.size()<setAlleles2.size())
		// return -setAlleles2.size();
		// else ret = 0;
		// } else ret=0;
		// }
		// }
		//
		//
		// if(ret==0)
		// {
		//
		// Variety v1 =listitemsdao.getMapId2Variety(dataset).get(s1.getVar());
		// Variety v2 =listitemsdao.getMapId2Variety(dataset).get(s2.getVar());
		// if(v1==null || v2==null) {
		// AppContext.debug("v1==null || v2==null for " + s1.getVar() + " , " +
		// s2.getVar());
		// //return 0;
		// }
		// if(v1.getSubpopulation()!=null && v2.getSubpopulation()!=null)
		// ret=v1.getSubpopulation().compareTo(v2.getSubpopulation());
		//
		// if(ret!=0) return ret;
		// if(v1.getCountry()!=null && v2.getCountry()!=null)
		// ret = v1.getCountry().compareTo(v2.getCountry());
		// if(ret!=0) return ret;
		//
		// ret = v1.getVarietyId().compareTo(v2.getVarietyId());
		// return ret;
		//
		// } else return ret;
	}
}