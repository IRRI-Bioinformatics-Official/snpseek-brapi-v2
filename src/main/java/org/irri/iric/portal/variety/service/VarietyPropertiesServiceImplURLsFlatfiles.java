package org.irri.iric.portal.variety.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.variety.VarietyPropertiesService;
import org.springframework.stereotype.Service;

@Service("VarietyPropertiesService")
public class VarietyPropertiesServiceImplURLsFlatfiles implements VarietyPropertiesService {

	private Map<String, Map<String, String>> mapProp2Var2Values;

	@Override
	public Map getProperties(String propname) {
		

		if (mapProp2Var2Values == null) {
			mapProp2Var2Values = new HashMap();
			if (propname.equals(ID_ERS) || propname.equals(ID_SRA)) {

				Map mapVar2ERS = mapProp2Var2Values.get(ID_ERS);
				if (mapVar2ERS == null) {

					Map mapVar2SRA = new HashMap();
					try {

						mapVar2ERS = new HashMap();
						BufferedReader br = new BufferedReader(
								new FileReader(AppContext.getFlatfilesDir() + "iris2ersmap.txt"));
						String line = "";
						while ((line = br.readLine()) != null) {
							String cols[] = line.split("\\t");
							// B015 ERS470233 3089826 SAMEA2730335
							mapVar2ERS.put(cols[0], cols[1]);
							mapVar2SRA.put(cols[0], cols[2]);
						}

						br.close();

						mapProp2Var2Values.put(ID_ERS, mapVar2ERS);
						mapProp2Var2Values.put(ID_SRA, mapVar2SRA);

					} catch (Exception ex) {
						ex.printStackTrace();

						mapVar2ERS = null;
						mapVar2SRA = null;
					}

				}
			}
		}

		return mapProp2Var2Values.get(propname);

	}

}
