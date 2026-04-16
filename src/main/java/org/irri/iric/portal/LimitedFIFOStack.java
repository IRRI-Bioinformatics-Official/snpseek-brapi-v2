package org.irri.iric.portal;

import java.util.Stack;

public class LimitedFIFOStack extends Stack {

	private int limit;
	
	

	public LimitedFIFOStack(int limit) {
		super();
		this.limit = limit;
	}



	@Override
	public Object push(Object arg0) {
		
		 super.push(arg0);
	     while (size() > limit) { super.remove(super.firstElement()); }; 
	     return true;
	       
	}
	
	
}
