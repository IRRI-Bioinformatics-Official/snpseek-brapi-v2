package org.irri.iric.ds.chado.domain.impl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

import org.irri.iric.ds.chado.domain.Locus;
import org.irri.iric.ds.chado.domain.Position;

public class MarkerAnnotationByLocusImpl extends MarkerAnnotationImpl implements Locus {

	private Locus locus;
	private int plusminus = 0;
	
	private Logger log = Logger.getLogger(MarkerAnnotationByLocusImpl.class.getName());

	public MarkerAnnotationByLocusImpl(Locus locus, int plusminus) {
		// String contig, Long chr, BigDecimal pos
		// super(locus.getContig(), locus.getChr(),
		// BigDecimal.valueOf(locus.getFmin()));
		super();
		
		this.locus = locus;
		this.plusminus = plusminus;
	}

	@Override
	public String getUniquename() {
		
		return locus.getUniquename();
	}

	@Override
	public Integer getFmin() {
		
		return locus.getFmin();
	}

	@Override
	public Integer getFmax() {
		
		return locus.getFmax();
	}

	@Override
	public Integer getStrand() {
		
		return locus.getStrand();
	}

	@Override
	public String getDescription() {
		
		return locus.getDescription();
	}

	@Override
	public BigDecimal getFeatureId() {
		
		return locus.getFeatureId();
	}

	@Override
	public int compareTo(Object o) {
		
		Locus obj = ((MarkerAnnotationByLocusImpl) o).locus;
		return locus.compareTo(obj);
	}

	@Override
	public int hashCode() {
		
		return locus.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		
		return locus.equals(obj);
	}

	@Override
	public String toString() {
		
		return locus.toString();
	}

	@Override
	public String getContig() {
		
		return locus.getContig();
	}

	@Override
	public Long getChr() {
		
		return locus.getChr();
	}

	private Collection filterOverlap(Collection col) {
		Set included = new TreeSet();
		if (col == null)
			return included;

		Iterator itLoc = col.iterator();
		while (itLoc.hasNext()) {
			Object marker = itLoc.next();
			if (marker instanceof Locus)
				included.addAll(filterOverlap((Locus) marker));
			else if (marker instanceof Position)
				included.addAll(filterOverlap((Position) marker));
		}
		/*
		 * Iterator<Locus> itLoc=col.iterator(); while(itLoc.hasNext()) { Locus
		 * l=itLoc.next(); if( locus.getContig().equals(l.getContig())) {
		 * if(l.getFmin()>=locus.getFmin() && l.getFmin()<=locus.getFmax() )
		 * included.add(l); else if(l.getFmax()>=locus.getFmin() &&
		 * l.getFmax()<=locus.getFmax() ) included.add(l); else
		 * if(l.getFmax()<=locus.getFmax() && l.getFmin()>=locus.getFmin() )
		 * included.add(l); else if(locus.getFmax()<=l.getFmax() &&
		 * locus.getFmin()>=l.getFmin() ) included.add(l); } }
		 */

		return included;
	}

	private Collection filterOverlap(Position p) {
		Set included = new TreeSet();
		if (p == null)
			return included;
		if (locus.getContig().equals(p.getContig())) {
			if (plusminus == 0) {
				if (p.getPosition().intValue() >= locus.getFmin() && p.getPosition().intValue() <= locus.getFmax())
					included.add(p);
			} else {
				int pmin = p.getPosition().intValue() - plusminus;
				int pplus = p.getPosition().intValue() + plusminus;
				if (pmin >= locus.getFmin() && pmin <= locus.getFmax())
					included.add(p);
				else if (pplus >= locus.getFmin() && pplus <= locus.getFmax())
					included.add(p);
				else if (pplus <= locus.getFmax() && pmin >= locus.getFmin())
					included.add(p);
				else if (locus.getFmax() <= pplus && locus.getFmin() >= pmin)
					included.add(p);

			}
		}
		return included;
	}

	private Collection filterOverlap(Locus l) {
		Set included = new TreeSet();
		if (l == null)
			return included;

		if (locus.getContig().equals(l.getContig())) {
			if (l.getFmin() >= locus.getFmin() && l.getFmin() <= locus.getFmax())
				included.add(l);
			else if (l.getFmax() >= locus.getFmin() && l.getFmax() <= locus.getFmax())
				included.add(l);
			else if (l.getFmax() <= locus.getFmax() && l.getFmin() >= locus.getFmin())
				included.add(l);
			else if (locus.getFmax() <= l.getFmax() && locus.getFmin() >= l.getFmin())
				included.add(l);
		}

		return included;
	}

	@Override
	public void addQTL(String name, Locus loc) {
		
		// super.addQTL(name, filterOverlap(loc));
		super.addQTL(name, loc);
	}

	@Override
	public void addTraitGene(String name, Locus loc) {
		
		// super.addTraitGene(name, filterOverlap(loc));
		super.addTraitGene(name, loc);
	}

	@Override
	public void addNetworkGene(String name, Locus loc) {
		
		// super.addNetworkGene(name, filterOverlap(loc));
		super.addNetworkGene(name, loc);
	}

	@Override
	public void addGene(String name, Locus loc) {
		
		// super.addGene(name, filterOverlap(loc));
		super.addGene(name, loc);
	}

	@Override
	public void addMarker(String name, Locus loc) {
		
		Collection col = filterOverlap(loc);
		if (col.size() == 0)
			log.info(loc.toString() + " not added to " + this.getUniquename());
		super.addMarker(name, filterOverlap(loc));
	}

	@Override
	public void addOntologyGene(String name, Locus loc) {
		
		// super.addOntologyGene(name, filterOverlap(loc));
		super.addOntologyGene(name, loc);
	}

	@Override
	public void addPromoterGene(String name, Locus loc) {
		
		// super.addPromoterGene(name, filterOverlap(loc));
		super.addPromoterGene(name, loc);
	}

	@Override
	public void addTraitGene(String name, Collection loc) {
		
		// super.addTraitGene(name, filterOverlap(loc));
		super.addTraitGene(name, loc);
	}

	@Override
	public void addNetworkGene(String name, Collection loc) {
		
		// super.addNetworkGene(name, filterOverlap(loc));
		super.addNetworkGene(name, loc);
	}

	@Override
	public void addOntologyGene(String name, Collection loc) {
		
		// super.addOntologyGene(name, filterOverlap(loc));
		super.addOntologyGene(name, loc);
	}

	@Override
	public void addPromoterGene(String name, Collection loc) {
		
		// super.addPromoterGene(name, filterOverlap(loc));
		super.addPromoterGene(name, loc);
	}

	@Override
	public void addQTL(String name, Collection loc) {
		
		// super.addQTL(name, filterOverlap(loc));
		super.addQTL(name, loc);
	}

	@Override
	public void addGene(String name, Collection loc) {
		
		// super.addGene(name, filterOverlap(loc));
		super.addGene(name, loc);
	}

	@Override
	public void addMarker(String name, Collection loc) {
		
		Collection col = filterOverlap(loc);
		if (col.size() == 0)
			log.info(loc.toString() + " not added to " + this.getUniquename());
		super.addMarker(name, col);
	}

	@Override
	public Position getContigPosition() {
		
		return super.getContigPosition();
	}

	@Override
	public String getFeatureType() {
		return null;
	}

}
