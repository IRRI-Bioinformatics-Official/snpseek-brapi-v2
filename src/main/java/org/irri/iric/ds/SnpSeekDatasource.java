package org.irri.iric.ds;

import java.util.logging.Logger;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SnpSeekDatasource {

	private static ClassPathXmlApplicationContext context;

	private static Logger log = Logger.getLogger(SnpSeekDatasource.class.getName());

	static {
		log.info("Loading XML...");
		context = new ClassPathXmlApplicationContext("classpath*:applicationContextDs.xml");

	}

	public SnpSeekDatasource() {
		log.info("Loading Class.................");
		context = new ClassPathXmlApplicationContext("classpath*:applicationContextDs.xml");

	}

	public static ClassPathXmlApplicationContext getContext() {

		
		if (context == null) {
			log.info("Getting Context.................");
			context = new ClassPathXmlApplicationContext("classpath*:applicationContextDs.xml");
			
		}
		return context;
	}

}
