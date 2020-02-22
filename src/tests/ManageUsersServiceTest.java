package tests;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import configurations.DataSourceConfigurationDev;
import configurations.DatabaseConfiguration;
import dao.entities.Role;
import dao.entities.Users;
import dao.service.ManageUsersService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DatabaseConfiguration.class, DataSourceConfigurationDev.class})
@ActiveProfiles("dev")
public class ManageUsersServiceTest {
	
	@Autowired
	private ManageUsersService service;
	
	@Autowired
    private SessionFactory sessionFactory;

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
	
	@Test
	public void addUserExistingRole() {
		//given
		String NICKNAME = "sAddNick";
		String PASSWORD = "sAddPass";
		String FIRST_NAME = "sAddFirstN";
		String LAST_NAME = "sAddLastN";
		String EMAIL = "service@add.user";
		String ROLE = "ROLE_ADMIN";
		
		Users testUser = new Users();
		testUser.setUserName(NICKNAME);
		testUser.setPassword(PASSWORD);
		testUser.setFirstName(FIRST_NAME);
		testUser.setLastName(LAST_NAME);
		testUser.setEmail(EMAIL);
		testUser.setEnabled(false);
		Role role = new Role();
		role.setRole(ROLE);
		testUser.setRole(role);
		//when
		service.addUser(testUser);
		//then
		Users user = service.getUserByName(NICKNAME);
		Assert.assertEquals("Nickname for added user", NICKNAME, user.getUserName());
		Assert.assertEquals("First name for added user", FIRST_NAME, user.getFirstName());
		Assert.assertEquals("Last name for added user", LAST_NAME, user.getLastName());
		Assert.assertFalse("Enabled for added user", user.isEnabled());
		Assert.assertEquals("Email for added user", EMAIL, user.getEmail());
		Assert.assertEquals("Password for guest", PASSWORD, user.getPassword());
		Assert.assertEquals("Role for added user", ROLE, user.getRole().getRole());
	}
	
	@Test
	public void addUserNoExistingRole() {
		//given
		String NICKNAME = "sAddNick2";
		String PASSWORD = "sAddPass2";
		String FIRST_NAME = "sAddFirstN2";
		String LAST_NAME = "sAddLastN2";
		String EMAIL = "service@add.user2";
		String ROLE = "ROLE_NO_EXISTING_ROLE";
		
		Users testUser = new Users();
		testUser.setUserName(NICKNAME);
		testUser.setPassword(PASSWORD);
		testUser.setFirstName(FIRST_NAME);
		testUser.setLastName(LAST_NAME);
		testUser.setEmail(EMAIL);
		testUser.setEnabled(false);
		Role role = new Role();
		role.setRole(ROLE);
		testUser.setRole(role);
		//when
		service.addUser(testUser);
		//then
		Users user = service.getUserByName(NICKNAME);
		Assert.assertEquals("Nickname for added user", NICKNAME, user.getUserName());
		Assert.assertEquals("First name for added user", FIRST_NAME, user.getFirstName());
		Assert.assertEquals("Last name for added user", LAST_NAME, user.getLastName());
		Assert.assertFalse("Enabled for added user", user.isEnabled());
		Assert.assertEquals("Email for added user", EMAIL, user.getEmail());
		Assert.assertEquals("Password for guest", PASSWORD, user.getPassword());
		Assert.assertEquals("Role for added user", ROLE, user.getRole().getRole());
	}
}
	