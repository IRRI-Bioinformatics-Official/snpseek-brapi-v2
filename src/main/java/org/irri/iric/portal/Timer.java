package org.irri.iric.portal;

import java.util.concurrent.TimeUnit;

public class Timer {

	long startTime;
	long endTime;   
	long totalTime;
	
	public void start() {
		startTime = System.nanoTime();
		
		
	}
	
	public void stopAndGetTime() {
		
		endTime   = System.nanoTime();
		totalTime = endTime - startTime;
		
		AppContext.debug(new String("TOTAL TIME >>>>>>>>"+TimeUnit.SECONDS.convert(totalTime, TimeUnit.NANOSECONDS)));
	}
}
