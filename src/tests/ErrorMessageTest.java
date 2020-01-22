package tests;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	
	/*private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@Autowired
	private ErrorMessage errMsg;

	@Before
	public void setUp() {
		// Create traces stored into DB for test
		for (int i = 0; i < 20; i++) {
			logger.warn("Error no: " + i);
		}
	}

	@Test
	public void getWarningListTest() {
		List<Map<String, Object>> rows = errMsg.getWarnigs();
		for (Map<String, Object> row : rows) {
			System.out.println("===" + row.get("date")  + row.get("level") + row.get("message"));
		}
		assertEquals(20, rows.size());
	} */
}