package org.irri.iric.ds.chado.domain.object;

import java.util.Collection;

import org.irri.iric.ds.chado.domain.MultiReferenceLocus;

/**
 * Sequence download parameters
 * 
 * @author LMansueto
 *
 */
public class VariantSequenceQuery extends Query {

	Collection colVars;
	Collection colLocus;

	//String method = "gatk";
	String method = "galaxy";
	String reference;

	public VariantSequenceQuery(Collection<String> colVars, Collection<MultiReferenceLocus> colLocus) {
		this(colVars, colLocus, null, "Nipponbare");
	}

	public VariantSequenceQuery(Collection<String> colVars, Collection<MultiReferenceLocus> colLocus, String thisjobid,
			String reference) {
		super();
		this.colVars = colVars;
		this.colLocus = colLocus;
		jobid = thisjobid;
		this.reference = reference;
	}

	public Collection getColVars() {
		return colVars;
	}

	public Collection getColLocus() {
		return colLocus;
	}

	public String getMethod() {
		return method;
	}

	@Override
	public String toString() {
		
		return "VariantSequenceQuery: " + super.toString() + ";colVars=" + colVars.size() + ";colLocus="
				+ colLocus.size() + "; ref=" + reference;
	}

	public String getReference() {
		return reference;
	}

	boolean sync;
	public void setSync(boolean sync) {
		this.sync=sync;
	}
	public boolean isSync() {
		return sync;
	}

}
