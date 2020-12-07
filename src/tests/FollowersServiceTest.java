package tests;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import configurations.DataSourceConfigurationDev;
import configurations.DatabaseConfiguration;
import configurations.SecurityConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DatabaseConfiguration.class, DataSourceConfigurationDev.class, SecurityConfig.class})
@ActiveProfiles("dev")
public class FollowersServiceTest {

}
