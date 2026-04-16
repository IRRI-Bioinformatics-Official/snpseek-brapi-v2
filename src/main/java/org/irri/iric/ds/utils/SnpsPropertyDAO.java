package org.irri.iric.ds.utils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.irri.iric.ds.chado.domain.Locus;
import org.irri.iric.ds.chado.domain.impl.MultiReferencePositionImpl;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;

abstract public class SnpsPropertyDAO<T> extends AbstractJpaDao<T> {

	private Logger log = Logger.getLogger(SnpsPropertyDAO.class.getName());

	protected abstract Session getSession();

	protected List executeSQL(String sql, Class retclass) {
		// System.out.println("executing :" + sql);
		// log.info("executing :" + sql);
		log.info("executing " + sql);
		List listResult = null;
		try {
			listResult = getSession().createSQLQuery(sql).addEntity(retclass).list();
		} catch (Exception ex) {
			log.info(ex.getMessage());
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		log.info("result " + listResult.size() + " SnpsProps:" + retclass.getCanonicalName());
		return listResult;
	}

	public Set findSnpsPropertyByChrPosIn(Integer organismId, String chr, Collection poslist, Set variantset, String propname,
			String viewname, String columnname, Class retclass) {

		Set setSnpfeatureid = new HashSet();
		if (chr.toLowerCase().equals("any")) {

			Set setAll = new HashSet();

			Map mapChr2Pos = MultiReferencePositionImpl.getMapContig2SNPPos(poslist);
			Iterator<String> itChr = mapChr2Pos.keySet().iterator();
			while (itChr.hasNext()) {
				String chrstr = itChr.next();
				Set sets[] = DbUtils.setSlicer(new LinkedHashSet((Set) mapChr2Pos.get(chrstr)));
				for (int iset = 0; iset < sets.length; iset++) {

					// TODO check for bypass

					log.info("Check if it is bypass");
					// if (AppContext.isBypassViews() && AppContext.isPostgres()) {
					setAll.addAll(findSnpsPropertyByChrPosIn(organismId, chrstr, sets[iset], variantset, propname, viewname,
							columnname, retclass));

					// }
					// Edited and commented this part
					// else {
					//
					// Query query = createNamedQuery("find" + viewname + "ByChrPositionInSnpset",
					// -1, -1,
					// BigDecimal.valueOf(Long.valueOf(AppContext.guessChrFromString(chrstr))),
					// sets[iset],
					// variantset);
					// setAll.addAll(query.getResultList());
					// }
				}
			}

			return setAll;

		} else if (chr.toLowerCase().equals("loci")) {
			Iterator<Locus> it = poslist.iterator();

			Set setPos = new TreeSet();
			while (it.hasNext()) {
				Locus loc = it.next();
				setPos.addAll(findSnpsPropertyByChrPosBetween(organismId, loc.getContig(), loc.getFmin(), loc.getFmax(), variantset,
						propname, viewname, columnname, retclass));
			}
			return setPos;
		} else {

			Set setAll = new HashSet();
			Set sets[] = DbUtils.setSlicer(new LinkedHashSet(poslist));
			if (columnname == null)
				columnname = "";
			else if (columnname != null && !columnname.isEmpty() && !columnname.startsWith(","))
				columnname = ", sfp.value AS " + columnname;

			for (int iset = 0; iset < sets.length; iset++) {

				// TODO check for bypass
				// if (AppContext.isBypassViews() && AppContext.isPostgres()) {

				String sqldirect = "";
				sqldirect += "SELECT sfl.snp_feature_id, sfl.srcfeature_id-" + DbUtils.chr2srcfeatureidOffset(organismId)
						+ " AS chromosome, sfl.position + 1 AS \"position\", v.variantset_id AS type_id, v.name AS variantset "
						+ columnname;
				sqldirect += " FROM public.snp_featureloc sfl, public.variant_variantset vvs, public.variantset v ";
				sqldirect += " , snp_featureprop sfp  WHERE  sfl.snp_feature_id = sfp.snp_feature_id and sfl.snp_feature_id = vvs.variant_feature_id AND vvs.variantset_id = v.variantset_id ";
				sqldirect += "  AND sfp.type_id in (select cvterm_id from cvterm where name='" + propname
						+ "') and v.name in (" + DbUtils.toCSVquoted(variantset, "'") + ")";
				sqldirect += " and sfl.organism_id=" + DbUtils.getDefaultOrganismId() + " and sfl.srcfeature_id="
						+ DbUtils.guessSrcfeataureidFromString(chr, organismId);
				sqldirect += " and exists ( select t.column_value from (select unnest(ARRAY" + sets[iset]
						+ ")column_value) t where t.column_value-1=sfl.position ) ";
				sqldirect += " order by sfl.position";
				setAll.addAll(executeSQL(sqldirect, retclass));

				// }
				// Edited and commented this part
				// else {
				// Query query = createNamedQuery("find" + viewname + "ByChrPositionInSnpset",
				// -1, -1,
				// BigDecimal.valueOf(Long.valueOf(AppContext.guessChrFromString(chr))),
				// sets[iset],
				// variantset);
				// setAll.addAll(query.getResultList());
				// }
			}

			return setAll;
		}
	}

	public Set findSnpsPropertyByChrPosBetween(Integer organismId, String chr, Integer start, Integer end, BigDecimal typeid,
			String propname, String viewname, String columnname, Class retclass) throws DataAccessException {

		// TODO check for the bypass
		// if (AppContext.isBypassViews()) {
		if (columnname == null)
			columnname = "";
		else if (columnname != null && !columnname.isEmpty() && !columnname.startsWith(","))
			columnname = ", sfp.value AS " + columnname;

		String sqldirect = "";
		sqldirect += "SELECT sfl.snp_feature_id, sfl.srcfeature_id-" + DbUtils.chr2srcfeatureidOffset(organismId)
				+ " AS chromosome, sfl.position + 1 AS \"position\", v.variantset_id AS type_id, v.name AS variantset "
				+ columnname;
		sqldirect += " FROM public.snp_featureloc sfl, public.variant_variantset vvs, public.variantset v ";
		sqldirect += " , snp_featureprop sfp  WHERE  sfl.snp_feature_id = sfp.snp_feature_id and sfl.snp_feature_id = vvs.variant_feature_id AND vvs.variantset_id = v.variantset_id ";
		sqldirect += "  AND sfp.type_id in (select cvterm_id from cvterm where name='" + propname + "')";
		sqldirect += " and sfl.organism_id=" + +organismId + " and sfl.srcfeature_id="
				+ DbUtils.guessSrcfeataureidFromString(chr, organismId);
		sqldirect += " and sfl.position between " + start + "-1 and " + end + "-1 and v.variantset_id=" + typeid
				+ " order by sfl.position";
		return new LinkedHashSet(executeSQL(sqldirect, retclass));

		// }
		// return null;
	}

	public Set findSnpsPropertyByChrPosBetween(Integer organismId, String chr, Integer start, Integer end, Set variantset, String propname,
			String viewname, String columnname, Class retclass) throws DataAccessException {

		// TODO Check for the bypass option
		// if (AppContext.isBypassViews()) {
		if (columnname == null)
			columnname = "";
		else if (columnname != null && !columnname.isEmpty() && !columnname.startsWith(","))
			columnname = ", sfp.value AS " + columnname;

		// using position
		String sqldirect = "";
		sqldirect += "SELECT sfl.snp_feature_id, sfl.srcfeature_id-" + DbUtils.chr2srcfeatureidOffset(organismId)
				+ " AS chromosome, sfl.position + 1 AS \"position\", v.variantset_id AS type_id, v.name AS variantset "
				+ columnname;
		sqldirect += " FROM public.snp_featureloc sfl, public.variant_variantset vvs, public.variantset v, ";
		sqldirect += "public.snp_featureloc sfl3k ";
		sqldirect += " , snp_featureprop sfp  WHERE  sfl3k.snp_feature_id = sfp.snp_feature_id and sfl.snp_feature_id = vvs.variant_feature_id AND vvs.variantset_id = v.variantset_id ";
		sqldirect += "  AND sfp.type_id in (select cvterm_id from cvterm where name='" + propname + "')";
		sqldirect += " and sfl3k.organism_id=" + DbUtils.getDefaultOrganismId() + " and sfl3k.srcfeature_id="
				+ DbUtils.guessSrcfeataureidFromString(chr, organismId);
		sqldirect += " and sfl3k.position=sfl.position and sfl3k.srcfeature_id=sfl.srcfeature_id and sfl.organism_id="
				+ DbUtils.getDefaultOrganismId();
		sqldirect += " and sfl3k.position between " + start + "-1 and " + end + "-1 and v.name in ("
				+ DbUtils.toCSVquoted(variantset, "'") + ") order by sfl3k.position";
		return new LinkedHashSet(executeSQL(sqldirect, retclass));

		// }

		// TODO if bypass then this code is not reached
		// Query query = createNamedQuery("find" + viewname +
		// "ByChrPositionBetweenSnpset", -1, -1,
		// BigDecimal.valueOf(Long.valueOf(AppContext.guessChrFromString(chr))),
		// BigDecimal.valueOf(start),
		// BigDecimal.valueOf(end), variantset);
		// java.util.List list = query.getResultList();
		// java.util.Set set = new LinkedHashSet<SnpsNonsynAllele>(list);
		// log.info("find" + viewname + "ByChrPosBetween list=" + list.size() + " set="
		// + set.size());
		// return set;
	}

	public Set findSnpsPropertyByFeatureidIn(Integer organismId, Collection featureid, String propname, String viewname, String columnname,
			Class retclass) throws DataAccessException {

		if (columnname != null && !columnname.isEmpty()) {
			if (!columnname.startsWith(","))
				columnname = ", sfp.value AS " + columnname;
		} else
			columnname = "";
		Set sets[] = DbUtils.setSlicer((Set) featureid);
		Set setAll = new LinkedHashSet();

		for (int iset = 0; iset < sets.length; iset++) {

			// TODO check bypass
			// if (AppContext.isBypassViews() && AppContext.isPostgres()) {
			// using position
			String sqldirect = "";
			sqldirect += "SELECT sfl.snp_feature_id, sfl.srcfeature_id-" + DbUtils.chr2srcfeatureidOffset(organismId)
					+ " AS chromosome, sfl.position + 1 AS \"position\", v.variantset_id AS type_id, v.name AS variantset "
					+ columnname;
			sqldirect += " FROM public.snp_featureloc sfl, public.variant_variantset vvs, public.variantset v, ";
			sqldirect += "public.snp_featureloc sfl3k ";
			sqldirect += " , snp_featureprop sfp  WHERE  sfl3k.snp_feature_id = sfp.snp_feature_id and sfl.snp_feature_id = vvs.variant_feature_id AND vvs.variantset_id = v.variantset_id ";
			sqldirect += "  AND sfp.type_id in (select cvterm_id from cvterm where name='" + propname + "')";
			sqldirect += " and sfl3k.organism_id=" + organismId
					+ " and sfl3k.position=sfl.position and sfl3k.srcfeature_id=sfl.srcfeature_id and sfl.organism_id=9 ";
			sqldirect += " and exists ( select t.column_value from (select unnest(ARRAY" + sets[iset]
					+ ")column_value) t where t.column_value=sfl.snp_feature_id ) ";
			sqldirect += " order by sfl.position";
			setAll.addAll(executeSQL(sqldirect, retclass));
			// } else {
			// Query query = createNamedQuery("find" + viewname + "BySnpFeatureIdIn", -1,
			// -1, sets[iset]);
			// setAll.addAll(query.getResultList());
			// }
		}
		return setAll;

	}

	public Map variantset2snppropCount(Integer organismId, String propname, Set variantset) {

		// using position
		String sql = "select vs.name variantset, count(1) from cvterm ct, snp_featureprop sfp, variant_variantset vvs, variantset vs, "
				+ " snp_featureloc sfl, snp_featureloc sfl3k " + " where ct.name in ('" + propname + "') "
				+ " and sfl3k.organism_id=" + organismId
				+ " and sfl3k.position=sfl.position and sfl3k.srcfeature_id=sfl.srcfeature_id and sfl.organism_id=9 "
				+ " and sfl3k.snp_feature_id=sfp.snp_feature_id "
				+ " and ct.cvterm_id=sfp.type_id and sfl.snp_feature_id=vvs.variant_feature_id"
				+ " and vvs.variantset_id=vs.variantset_id and  vs.name in ('" + DbUtils.toCSVquoted(variantset, "'")
				+ "') group by vs.name";
		List<LabelCount> labelcount = executeSQL(sql, LabelCount.class);
		Map m = new HashMap();
		for (LabelCount lc : labelcount)
			m.put(lc.getLabel(), lc.getCount());
		return m;
	}

}
