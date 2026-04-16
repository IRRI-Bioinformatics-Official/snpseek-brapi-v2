package org.irri.iric.ds.test;

import java.math.BigDecimal;
import java.util.List;

import org.irri.iric.ds.SnpSeekDatasource;
import org.irri.iric.ds.chado.dao.ScaffoldDAO;
import org.irri.iric.ds.chado.dao.SequenceDAO;
import org.irri.iric.ds.chado.dao.SubscriptionDAO;
import org.irri.iric.ds.chado.dao.UserDAO;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.jmchilton.blend4j.galaxy.beans.User;



public class ScaffoldDaoTest {


	
	public void testGetStockSampleByDataset() {

		ApplicationContext context = new ClassPathXmlApplicationContext("LOCAL_applicationContextDs2.xml");
		ScaffoldDAO ssDs = (ScaffoldDAO) context.getBean("ScaffoldDAO");

		List result = ssDs.getScaffolds(new BigDecimal(9));

		System.out.println("result size" + result.size());

	}
	
	
	public void testUsert() {

		ApplicationContext context = new ClassPathXmlApplicationContext("LOCAL_applicationContextDs2.xml");
		UserDAO ssDs = (UserDAO) context.getBean("UserDAO");

		org.irri.iric.ds.chado.domain.model.User user = new org.irri.iric.ds.chado.domain.model.User();
		user.setEmail("lhendrixbarbasowza3@gmail.com");
		user.setUsername("lhendrixasbarbozwa3@gmail.com");
		user.setPasswordHash("test");
		
		ssDs.save(user);
		
	

		
	}
	
	@Test
	public void pub() {
		ApplicationContext context = new ClassPathXmlApplicationContext("LOCAL_applicationContextDs2.xml");
		UserDAO ssDs = (UserDAO) context.getBean("UserDAO");

		List<org.irri.iric.ds.chado.domain.model.User> users = ssDs.getByUserName("l.h.barboza@irri.org");
		System.out.println(users.size());

	}
	
	
	public void getSubscritpionByShortName() {
		ApplicationContext context = new ClassPathXmlApplicationContext("LOCAL_applicationContextDs2.xml");
		SubscriptionDAO ssDs = (SubscriptionDAO) context.getBean("SubscriptionDAO");

		List<org.irri.iric.ds.chado.domain.model.Subscription> sub = ssDs.getByShortName("adm");
		System.out.println(sub.size());

	}

	public void testGetSequenceDao() {

		ApplicationContext context = new ClassPathXmlApplicationContext("LOCAL_applicationContextDs2.xml");
		
		SequenceDAO ssDs = (SequenceDAO) context.getBean("FeatureDAO");

		try {
			String result = ssDs.getSubSequence("", 1, 59, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	

}
