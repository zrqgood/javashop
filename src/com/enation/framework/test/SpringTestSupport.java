package com.enation.framework.test;

import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

/**
 * 单元测试基类<br/>
 * 应用此类必须保证以classpath下有spring包，且含有声明了simpleJdbcTemplate和jdbcTemplate的application的xml配置文件
 * 
 * @author apexking
 * 
 */
public class SpringTestSupport {

	private static ApplicationContext context;

	protected static SimpleJdbcTemplate simpleJdbcTemplate;

	protected static JdbcTemplate jdbcTemplate;

	@BeforeClass
	public  static void setup() {
		context = new ClassPathXmlApplicationContext(
				new String[] { "classpath*:spring/*.xml","classpath*:testspring/*.xml" });
		simpleJdbcTemplate = (SimpleJdbcTemplate)   getBean("simpleJdbcTemplate");
		jdbcTemplate = (JdbcTemplate)  getBean("jdbcTemplate");
//		SpringContextHolder springContextHolder = this.getBean("springContextHolder");
//		springContextHolder.setApplicationContext(context);
	}

	protected static <T> T getBean(String name) {
		return (T)context.getBean(name);

	}

}
