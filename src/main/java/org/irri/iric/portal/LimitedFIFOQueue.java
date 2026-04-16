package org.irri.iric.portal;

import java.util.LinkedList;
import java.util.Queue;

public class LimitedFIFOQueue extends LinkedList implements Queue {

	private int limit;

	public LimitedFIFOQueue(int limit) {
		super();
		this.limit = limit;
	}

	@Override
	public boolean add(Object e) {
		
	       super.add(e);
	       while (size() > limit) { super.remove(); }
	       return true;
	}
	
	
	
}
