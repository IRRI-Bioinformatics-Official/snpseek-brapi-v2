package org.irri.iric.ds.chado.domain.object;

import java.util.Comparator;

import org.irri.iric.ds.chado.domain.SnpsAllvarsPos;

public class SnpsAllvarsPosSorter implements Comparator {
	@Override
	public int compare(Object o1, Object o2) {
		
		SnpsAllvarsPos pos1 = (SnpsAllvarsPos) o1;
		SnpsAllvarsPos pos2 = (SnpsAllvarsPos) o2;
		int ret = pos1.getContig().compareTo(pos2.getContig());
		if (ret != 0)
			return ret;
		ret = pos1.getPosition().compareTo(pos2.getPosition());
		return ret;
	}
}