package com.comolroy.saajs;

//Static Import to use the method directly
import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
/*
 * @SpringApplicationConfiguration Class-level annotation that is used to
 * determine how to load and configure an ApplicationContext for integration
 * tests
 */
@SpringApplicationConfiguration(classes = SpringAndAngularJsApplication.class)
/*
 * @WebAppConfiguration is a class-level annotation that is used to declare that
 * the ApplicationContext loaded for an integration test should be a
 * WebApplicationContext.
 */
@WebAppConfiguration
public class SpringAndAngularJsApplicationTests {

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void contextLoads() {
		assertEquals(2, 1);
	}

}
