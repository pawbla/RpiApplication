package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import configurations.DatabaseConfiguration;
import configurations.SecurityConfig;
import sysInfo.ErrorMessage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DatabaseConfiguration.class, SecurityConfig.class, JUnitTestConfiguration.class})
@ActiveProfiles("dev")
public class ErrorMessageTest {
	
	/**
	 * For proper working of below junit test log4j2 configuration should be copy
	 * into /target/test-classes/
	 */
	
	@Autowired
	private ErrorMessage errMsg;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getWarningListTest() throws IOException {
		List<String> errors = new ArrayList<String>();
		int size = 5;
		errors.addAll(errMsg.getWarnigs(size));
		assertEquals(errors.size(), size);
	}
	
	@Test
	public void incorrectPathTest() throws IOException {
		List<String> errors = new ArrayList<String>();
		int size = 5;
		errors.addAll(errMsg.getWarnigs(size));
		assertEquals(errors.size(), size);
	}

}
