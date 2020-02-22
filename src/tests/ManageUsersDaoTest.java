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
import dao.entities.Role;
import dao.entities.Users;
import dao.repository.ManageUsersDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DatabaseConfiguration.class, DataSourceConfigurationDev.class})
@ActiveProfiles("dev")
public class ManageUsersDaoTest {
	
	@Autowired
	private ManageUsersDao dao;

	@Test
	public void getUserDetails() {
		Users user = dao.getUserByName("user");
		Assert.assertEquals("First name for user", "userName1", user.getFirstName());
		Assert.assertEquals("Password for user", 
				"40408537d958bee8c46851120c94e4ac6ba054458ba1d791aeaf8e365ec2d2374817ba13edd03fdf", user.getPassword());
		Assert.assertFalse("Enabled for user", user.isEnabled());
		Assert.assertEquals("Role for user", "ROLE_USER", user.getRole().getRole());
	}
	
	@Test
	public void getGuestDetails() {
		Users user = dao.getUserByName("guest");
		Assert.assertEquals("First name for guest", "userName2", user.getFirstName());
		Assert.assertEquals("Password for guest", 
				"40408537d958bee8c46851120c94e4ac6ba054458ba1d791aeaf8e365ec2d2374817ba13edd03fdf", user.getPassword());
		Assert.assertFalse("Enabled for guest", user.isEnabled());
		Assert.assertEquals("Role for guest", "ROLE_GUEST", user.getRole().getRole());
	}
	
	@Test
	public void getAdminDetails() {
		Users user = dao.getUserByName("admin");
		Assert.assertEquals("First name for admin", "adminFirstName", user.getFirstName());
		Assert.assertEquals("Last name for admin", "adminLastName", user.getLastName());
		Assert.assertEquals("Role for admin", "ROLE_ADMIN", user.getRole().getRole());
		Assert.assertTrue("Enabled for admin", user.isEnabled());
		Assert.assertEquals("Email for admin", "adres.email@email.com", user.getEmail());
		Assert.assertEquals("Password for guest", 
				"40408537d958bee8c46851120c94e4ac6ba054458ba1d791aeaf8e365ec2d2374817ba13edd03fdf", user.getPassword());
	}
	
	@Test
	public void addUser() {
		//given
		String NICKNAME = "AddUserNick";
		String PASSWORD = "AddUserPass";
		String FIRST_NAME = "FirstName";
		String LAST_NAME = "LastName";
		String EMAIL = "user@add.user";
		String ROLE = "TEST_ROLE";
		
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
		dao.addUser(testUser);
		//then
		Users user = dao.getUserByName(NICKNAME);
		Assert.assertEquals("Nickname for added user", NICKNAME, user.getUserName());
		Assert.assertEquals("First name for added user", FIRST_NAME, user.getFirstName());
		Assert.assertEquals("Last name for added user", LAST_NAME, user.getLastName());
		Assert.assertFalse("Enabled for added user", user.isEnabled());
		Assert.assertEquals("Email for added user", EMAIL, user.getEmail());
		Assert.assertEquals("Password for guest", PASSWORD, user.getPassword());
		Assert.assertEquals("Role for added user", ROLE, user.getRole().getRole());
	}	
	
	@Test
	public void getRole() {
		Assert.assertEquals("Role admin should be fetched", "ROLE_ADMIN", dao.getRole("ROLE_ADMIN").getRole());
		Assert.assertEquals("Role user should be fetched", "ROLE_USER", dao.getRole("ROLE_USER").getRole());
		Assert.assertEquals("Role guest should be fetched", "ROLE_GUEST", dao.getRole("ROLE_GUEST").getRole());
	}
	
	@Test
	public void getIncorrectRole() {
		Assert.assertNull("Empty object expected", dao.getRole("No_existing_role"));
	}
}
	