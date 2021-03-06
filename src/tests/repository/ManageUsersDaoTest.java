package tests.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import configurations.DataSourceConfigurationDev;
import configurations.DatabaseConfiguration;
import configurations.SecurityConfig;
import dao.entities.Role;
import dao.entities.Users;
import dao.repository.ManageUsersRepository;
import tests.ConfigurationTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DatabaseConfiguration.class, DataSourceConfigurationDev.class,
		SecurityConfig.class, ConfigurationTest.class})
@ActiveProfiles("dev")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class ManageUsersDaoTest {
	
	@Autowired
	private ManageUsersRepository dao;

	@Test
	public void getUserDetails() {
		Users user = dao.findByUsername("user");
		Assert.assertEquals("First name for user", "userName1", user.getFirstName());
		Assert.assertEquals("Password for user", 
				"$2a$10$vz/0q8l5p7f6fNFUgFn/fueDX65INhr47s/LqLMoYPlKrtUbmAqxK", user.getPassword());
		Assert.assertFalse("Enabled for user", user.isEnabled());
		Assert.assertEquals("Role for user", "ROLE_USER", user.getRole().getRole());
	}
	
	@Test
	public void getGuestDetails() {
		Users user = dao.findByUsername("guest");
		Assert.assertEquals("First name for guest", "userName2", user.getFirstName());
		Assert.assertEquals("Password for guest", 
				"$2a$10$vz/0q8l5p7f6fNFUgFn/fueDX65INhr47s/LqLMoYPlKrtUbmAqxK", user.getPassword());
		Assert.assertFalse("Enabled for guest", user.isEnabled());
		Assert.assertEquals("Role for guest", "ROLE_GUEST", user.getRole().getRole());
	}
	
	@Test
	public void getAdminDetails() {
		Users user = dao.findByUsername("admin");
		Assert.assertEquals("First name for admin", "adminFirstName", user.getFirstName());
		Assert.assertEquals("Last name for admin", "adminLastName", user.getLastName());
		Assert.assertEquals("Role for admin", "ROLE_ADMIN", user.getRole().getRole());
		Assert.assertTrue("Enabled for admin", user.isEnabled());
		Assert.assertEquals("Email for admin", "adres.email@email.com", user.getEmail());
		Assert.assertEquals("Password for guest", 
				"$2a$10$vz/0q8l5p7f6fNFUgFn/fueDX65INhr47s/LqLMoYPlKrtUbmAqxK", user.getPassword());
	}
	
	@Test
	public void getUserById() {
		//given
		int user_id = 1;
		//when
		//then
		Users user = dao.findByUserId(user_id);
		Assert.assertEquals("First name for user", "userName1", user.getFirstName());
		Assert.assertEquals("Password for user", 
				"$2a$10$vz/0q8l5p7f6fNFUgFn/fueDX65INhr47s/LqLMoYPlKrtUbmAqxK", user.getPassword());
		Assert.assertFalse("Enabled for user", user.isEnabled());
		Assert.assertEquals("Role for user", "ROLE_USER", user.getRole().getRole());		
	}
	
	@Test
	public void getUsers() {
		//given
		List<String> usernames = new ArrayList<String>();
		usernames.add("user");
		usernames.add("guest");
		usernames.add("admin");
		usernames.add("deleteUser");
		usernames.add("updateUser");
		usernames.add("UserPassChange");
		usernames.add("AddUserNick");
		
		List<String> roles = new ArrayList<String>();
		roles.add("ROLE_USER");
		roles.add("ROLE_GUEST");
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		roles.add("ROLE_USER");
		roles.add("ROLE_USER");
		roles.add("TEST_ROLE");
		//when
		List<Users> users = dao.findUsers();
		//then
		Assert.assertEquals("List should contain 6 users", 6, users.size());
		users.forEach(user -> {
			Assert.assertEquals("UserName for user: " + user.getId(), usernames.get(user.getId()-1), user.getUserName());
			Assert.assertEquals("Role for user: " + user.getId(), roles.get(user.getId()-1), user.getRole().getRole());
		});
	}
	
	@Test
	public void getRole() {
		Assert.assertEquals("Role admin should be fetched", "ROLE_ADMIN", dao.findRole("ROLE_ADMIN").getRole());
		Assert.assertEquals("Role user should be fetched", "ROLE_USER", dao.findRole("ROLE_USER").getRole());
		Assert.assertEquals("Role guest should be fetched", "ROLE_GUEST", dao.findRole("ROLE_GUEST").getRole());
	}
	
	@Test
	public void getIncorrectRole() {
		Assert.assertNull("Empty object expected", dao.findRole("No_existing_role"));
	}
	
	@Test
	public void numberOfAdmins () {
		//given 
		int expectedNoAdmins = 1;
		//when
		//then
		Assert.assertEquals("One admin available in databse", expectedNoAdmins, dao.countAdmins());
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
		dao.save(testUser);
		//then
		Users user = dao.findByUsername(NICKNAME);
		Assert.assertEquals("Nickname for added user", NICKNAME, user.getUserName());
		Assert.assertEquals("First name for added user", FIRST_NAME, user.getFirstName());
		Assert.assertEquals("Last name for added user", LAST_NAME, user.getLastName());
		Assert.assertFalse("Enabled for added user", user.isEnabled());
		Assert.assertEquals("Email for added user", EMAIL, user.getEmail());
		Assert.assertEquals("Password for guest", PASSWORD, user.getPassword());
		Assert.assertEquals("Role for added user", ROLE, user.getRole().getRole());
	}	
	
	@Test //PUT
	public void updateUser() {
		//given
		String NICKNAME = "updateUser";
		String FIRST_NAME = "NewFirstName";
		String LAST_NAME = "NewLastName";
		String EMAIL = "new@email.user";
		String ROLE = "ROLE_USER";
		
		Users testUser = dao.findByUsername(NICKNAME);
		testUser.setUserName(NICKNAME);
		testUser.setFirstName(FIRST_NAME);
		testUser.setLastName(LAST_NAME);
		testUser.setEmail(EMAIL);
		testUser.setEnabled(false);
		
		Role role = dao.findRole(ROLE);
		testUser.setRole(role);
		
		//when
		dao.save(testUser);
		
		//then
		Users user = dao.findByUsername(NICKNAME);
		Assert.assertEquals("Nickname for updated user", NICKNAME, user.getUserName());
		Assert.assertEquals("First name for updated user", FIRST_NAME, user.getFirstName());
		Assert.assertEquals("Last name for updated user", LAST_NAME, user.getLastName());
		Assert.assertFalse("Enabled for updated user", user.isEnabled());
		Assert.assertEquals("Email for updated user", EMAIL, user.getEmail());
		Assert.assertEquals("Role for updated user", ROLE, user.getRole().getRole());
	}
	
	@Test
	public void deleteUser() {
		//given
		String NICKNAME = "deleteUser";
		Users user = dao.findByUsername(NICKNAME);
		Assert.assertNotNull("User exists in database", user);
		//when
		dao.delete(user);
		//then
		Assert.assertNull("User not exists in database", dao.findByUsername(NICKNAME));
	}
	

	@Test
	public void updatePassword() {
		//given
		String NEW_PASSWORD = "nowe_haslo";
		int USER_ID = 6;
		//when
		dao.updatePassword(USER_ID, NEW_PASSWORD);
		//then
		Users user = dao.findByUserId(USER_ID);
		Assert.assertEquals("New password", NEW_PASSWORD, user.getPassword());
	}
}
	