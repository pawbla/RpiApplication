package tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import configurations.DataSourceConfigurationDev;
import configurations.DatabaseConfiguration;
import dao.entities.Users;
import dao.service.ManageUsersService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DatabaseConfiguration.class, DataSourceConfigurationDev.class})
@ActiveProfiles("dev")
public class ManageUsersServiceTest {
	
	@Autowired
	private ManageUsersService service;

	@Test
	public void getUserDetails() {
		Users user = service.getUserByName("user");
		Assert.assertEquals("First name for user", "userName1", user.getFirstName());
		Assert.assertEquals("Password for user", 
				"40408537d958bee8c46851120c94e4ac6ba054458ba1d791aeaf8e365ec2d2374817ba13edd03fdf", user.getPassword());
		Assert.assertFalse("Enabled for user", user.isEnabled());
		Assert.assertEquals("Role for user", "ROLE_USER", user.getRole().getRole());
	}
	
	@Test
	public void getGuestDetails() {
		Users user = service.getUserByName("guest");
		Assert.assertEquals("First name for guest", "userName2", user.getFirstName());
		Assert.assertEquals("Password for guest", 
				"40408537d958bee8c46851120c94e4ac6ba054458ba1d791aeaf8e365ec2d2374817ba13edd03fdf", user.getPassword());
		Assert.assertFalse("Enabled for guest", user.isEnabled());
		Assert.assertEquals("Role for guest", "ROLE_GUEST", user.getRole().getRole());
	}
	
	@Test
	public void getAdminDetails() {
		Users user = service.getUserByName("admin");
		Assert.assertEquals("First name for admin", "adminFirstName", user.getFirstName());
		Assert.assertEquals("Last name for admin", "adminLastName", user.getLastName());
		Assert.assertEquals("Role for admin", "ROLE_ADMIN", user.getRole().getRole());
		Assert.assertTrue("Enabled for admin", user.isEnabled());
		Assert.assertEquals("Email for admin", "adres.email@email.com", user.getEmail());
		Assert.assertEquals("Password for guest", 
				"40408537d958bee8c46851120c94e4ac6ba054458ba1d791aeaf8e365ec2d2374817ba13edd03fdf", user.getPassword());
		
	}
}
	