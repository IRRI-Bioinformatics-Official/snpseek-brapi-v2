package org.irri.iric.ds;

import java.math.BigDecimal;
import java.util.logging.Logger;

public class AppContext {

	private static Logger logger = Logger.getLogger(AppContext.class.getName());

	public static void debug(String msg) {

		logger.info(msg);

	}
	
	public static void debug(String msg, String className) {

		logger.info(className + ":"+ msg);

	}
	
	public static String REFERENCE_NIPPONBARE() {
		return AppContext.getDefaultOrganism();
	}

	private static String getDefaultOrganism() {
		
		return "Japonica nipponbare";
	}

	public static BigDecimal REFERENCE_NIPPONBARE_ID() {
		return BigDecimal.valueOf(AppContext.getDefaultOrganismId());
	}

	private static long getDefaultOrganismId() {
		return 9;
	}

	public static String MH3() {
		return "MH3";
	}

	public static String guessChrFromString(String contig) {
		return contig.toUpperCase().replace("CHR0", "").replace("CHR", "");
	}



}
