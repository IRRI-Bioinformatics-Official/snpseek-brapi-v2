package org.irri.iric.portal.variety;

import java.util.Map;

/**
 * Get dynamically added (not loaded in database iric_stockprop) properties for
 * varieties
 * 
 * @author LMansueto
 *
 */
public interface VarietyPropertiesService {

	public static String ID_ERS = "IDERS";
	public static String ID_SRA = "IDSRA";

	public Map getProperties(String propname);

}
