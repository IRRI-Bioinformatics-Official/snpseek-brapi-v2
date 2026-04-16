package org.irri.iric.ds.chado.dao;

import java.util.Collection;
import java.util.Set;

import org.irri.iric.ds.chado.domain.MultiReferencePosition;

public interface SnpsInExonDAO {

	/**
	 * Get SNPs in exon in chromosome, from start to end
	 * 
	 * @param chr
	 * @param start
	 * @param end
	 * @return
	 */
	Set getSnps(String chr, Integer start, Integer end);

	Set getSnps(String chr, Collection<MultiReferencePosition> poslist);

}
