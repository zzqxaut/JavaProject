package test;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.po.Person;
import com.service.UserService;

public class TestOneToOne {
	@Test
	public void test1() {
		ApplicationContext appCon = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) appCon.getBean("userServiceImpl");
		Person pe = userService.selectPersonById1(1);
		System.out.println(pe);
	}
	
}
