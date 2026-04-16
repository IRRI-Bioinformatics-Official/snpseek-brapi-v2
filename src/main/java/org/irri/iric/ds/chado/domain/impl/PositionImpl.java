package org.irri.iric.ds.chado.domain.impl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.irri.iric.ds.chado.domain.Locus;
import org.irri.iric.ds.chado.domain.MultiReferencePosition;
import org.irri.iric.ds.chado.domain.Position;
import org.irri.iric.ds.utils.DbUtils;

/**
 * Implementation for Position
 * 
 * @author LMansueto
 *
 */
public class PositionImpl implements Position {

	protected String contig;
	private String refcall;
	protected BigDecimal position;
	protected Integer chr;

	public PositionImpl() {
		super();
		
	}

	public PositionImpl(String orgcontigpos) {
		super();
		orgcontigpos = orgcontigpos = orgcontigpos.replace("(", "").replace(")", "");
		String fields[] = orgcontigpos.split(",");
		contig = fields[0].trim();
		position = BigDecimal.valueOf(Long.valueOf(fields[1].trim()));
		if (fields.length > 2)
			refcall = fields[2].trim();
		Integer guesschr = null;
		try {
			guesschr = Integer.valueOf(DbUtils.guessChrFromString(contig));
			this.chr = guesschr;
		} catch (Exception ex) {
		}
		;
	}

	public PositionImpl(String contig, BigDecimal position, Integer chr) {
		this(contig, position, null, chr);
	}

	public PositionImpl(String contig, BigDecimal position) {
		this(contig, position, null, null);
		Integer guesschr = null;
		try {
			guesschr = Integer.valueOf(DbUtils.guessChrFromString(contig));
			this.chr = guesschr;
		} catch (Exception ex) {
		}
		;
	}

	public PositionImpl(String contig, BigDecimal position, String refcall, Integer chr) {
		super();
		this.contig = contig;
		this.refcall = refcall;
		this.position = position;
		this.chr = chr;

		if (contig == null && chr != null) {
			if (chr < 10)
				this.contig = "chr0" + chr;
			else
				this.contig = "chr" + chr;
		}
	}

	@Override
	public String getContig() {
		
		return contig;
	}

	@Override
	public BigDecimal getPosition() {
		
		return position;
	}

	@Override
	public String getRefnuc() {
		
		return refcall;
	}

	@Override
	public int compareTo(Object o) {
		
		
		PositionImpl p2 = (PositionImpl) o;
		int ret = getContig().compareTo(p2.getContig());
		if (ret == 0)
			ret = getPosition().compareTo(p2.getPosition());
		return ret;
	}

	@Override
	public int hashCode() {
		
		return toString().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		
		return compareTo(obj) == 0;
	}

	@Override
	public String toString() {
		
		if (refcall == null)
			return "(" + contig + "," + position + ")";
		else
			return "(" + contig + "," + position + "," + refcall + ")";

	}

	/**
	 * Convert Collection<MultiReferencePosition> to TreeMap<String, TreeSet>
	 * mapChr2Pos
	 * 
	 * @param posset
	 * @return
	 */
	public static Map<String, Set<BigDecimal>> getMapContig2SNPPos(Collection<MultiReferencePosition> posset) {
		Iterator<MultiReferencePosition> itMultiPos = posset.iterator();
		Map<String, Set<BigDecimal>> mapChr2Pos = new TreeMap();
		while (itMultiPos.hasNext()) {
			MultiReferencePosition refpos = itMultiPos.next();
			Set setPos = mapChr2Pos.get(refpos.getContig());
			if (setPos == null) {
				setPos = new TreeSet();
				mapChr2Pos.put(refpos.getContig(), setPos);
			}
			setPos.add(refpos.getPosition());
		}
		return mapChr2Pos;
	}

	/**
	 * Convert Collection<Locus> to TreeMap<String, TreeSet> mapChr2Loci
	 * 
	 * @param posset
	 * @return
	 */
	public static Map<String, Set<Locus>> getMapContig2Loci(Collection<Locus> lociset) {
		Iterator<Locus> itMultiPos = lociset.iterator();
		Map<String, Set<Locus>> mapChr2Pos = new TreeMap();
		while (itMultiPos.hasNext()) {
			Locus refpos = itMultiPos.next();
			Set setPos = mapChr2Pos.get(refpos.getContig());
			if (setPos == null) {
				setPos = new TreeSet();
				mapChr2Pos.put(refpos.getContig(), setPos);
			}
			setPos.add(refpos);
		}
		return mapChr2Pos;
	}

	@Override
	public Long getChr() {
		
		return Long.valueOf(DbUtils.guessChrFromString(contig));
	}

}
