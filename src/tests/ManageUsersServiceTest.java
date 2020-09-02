package tests;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import configurations.DataSourceConfigurationDev;
import configurations.DatabaseConfiguration;
import configurations.SecurityConfig;
import dao.entities.Role;
import dao.entities.Users;
import dao.service.ManageUsersService;
import exceptions.RemoveAllAdminsException;
import exceptions.UpdatePasswordException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DatabaseConfiguration.class, DataSourceConfigurationDev.class, SecurityConfig.class})
@ActiveProfiles("dev")
public class ManageUsersServiceTest {
	
	@Autowired
	private ManageUsersService service;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
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
		Assert.assertTrue("Password for guest", passwordEncoder.matches(PASSWORD, user.getPassword()));
		Assert.assertEquals("Role for added user", "ROLE_USER", user.getRole().getRole()); //user always stored with default role ROLE_USER
	}

	@Test
	public void getUsers() {
		//given
		List<String> usernames = new ArrayList<String>();
		usernames.add("user");
		usernames.add("guest");
		usernames.add("admin");
		usernames.add("empty");
		usernames.add("updateUser");
		usernames.add("UserPassChange");
		usernames.add("sAddNick");
		
		List<String> roles = new ArrayList<String>();
		roles.add("ROLE_USER");
		roles.add("ROLE_GUEST");
		roles.add("ROLE_ADMIN");
		roles.add("empty");
		roles.add("ROLE_USER");
		roles.add("ROLE_USER");
		roles.add("ROLE_USER");
		//when
		List<Users> users = service.getUsers();
		//then
		Assert.assertEquals("List should contain 6 users", 6, users.size());
		users.forEach(user -> {
			Assert.assertEquals("UserName for user: " + user.getId(), usernames.get(user.getId()-1), user.getUserName());
			Assert.assertEquals("Role for user: " + user.getId(), roles.get(user.getId()-1), user.getRole().getRole());
		});
	}
	
	@Test
	public void deleteUser() throws RemoveAllAdminsException {
		//given
		String NICKNAME = "deleteUser";
		Assert.assertNotNull("User exists in database", service.getUserByName(NICKNAME));
		//when
		service.removeUser(4);
		//then
		Assert.assertNull("User not exists in database", service.getUserByName(NICKNAME));
	}
	
	@Test //PUT
	public void updateUser() throws RemoveAllAdminsException {
		//given
		String NICKNAME = "updateUser";
		String FIRST_NAME = "NewFirstName";
		String LAST_NAME = "NewLastName";
		String EMAIL = "new@email.user";
		String ROLE = "ROLE_USER";
		
		Users testUser = new Users();
		testUser.setUserName(NICKNAME);
		testUser.setFirstName(FIRST_NAME);
		testUser.setLastName(LAST_NAME);
		testUser.setEmail(EMAIL);
		testUser.setEnabled(false);
		
		Role role = new Role();
		role.setRole(ROLE);
		testUser.setRole(role);
		
		//when
		service.updateUser(5, testUser);
		
		//then
		Users user = service.getUserByName(NICKNAME);
		Assert.assertEquals("Nickname for updated user", NICKNAME, user.getUserName());
		Assert.assertEquals("First name for updated user", FIRST_NAME, user.getFirstName());
		Assert.assertEquals("Last name for updated user", LAST_NAME, user.getLastName());
		Assert.assertFalse("Enabled for updated user", user.isEnabled());
		Assert.assertEquals("Email for updated user", EMAIL, user.getEmail());
		Assert.assertEquals("Role for updated user", ROLE, user.getRole().getRole());
	}
	
	@Test(expected = RemoveAllAdminsException.class)
	public void removeLastAdmin() throws RemoveAllAdminsException {
		//given
		//when
		for (Users user : getAdminUserId()) {
			service.removeUser(user.getId());
		}
		//then
		Assert.fail();
	}
	
	@Test(expected = RemoveAllAdminsException.class)
	@Ignore
	public void changeLastAdminRole() throws RemoveAllAdminsException {
		//given
		String NICKNAME = "updateUser";
		String FIRST_NAME = "NewFirstName";
		String LAST_NAME = "NewLastName";
		String EMAIL = "new@email.user";
		String ROLE = "ROLE_USER";
		
		Users testUser = new Users();
		testUser.setUserName(NICKNAME);
		testUser.setFirstName(FIRST_NAME);
		testUser.setLastName(LAST_NAME);
		testUser.setEmail(EMAIL);
		testUser.setEnabled(false);
		
		Role role = new Role();
		role.setRole(ROLE);
		testUser.setRole(role);
		
		//when
		for (Users user : getAdminUserId()) {
			service.updateUser(user.getId(), testUser);
		}		
		//then	
		Assert.fail();
	}
	
	private List<Users> getAdminUserId() {
		List<Users> users = service.getUsers();
		List<Users> adminFiltered = users.stream()
			 .filter(user -> "ROLE_ADMIN".equals(user.getRole().getRole()))
			 .collect(Collectors.toList());
		return adminFiltered;
	}
	
	@Test
	public void updatePassword() throws UpdatePasswordException {
		//given
		String OLD_PASSWORD = "password";
		String NEW_PASSWORD = "nowe_haslo";
		String USER_NAME = "UserPassChange";
		int USER_ID = 6;
		//when
		service.updatePassword(USER_ID, OLD_PASSWORD, NEW_PASSWORD);
		//then
		Users user = service.getUserByName(USER_NAME);
		Assert.assertTrue("New password", passwordEncoder.matches(NEW_PASSWORD, user.getPassword()));
	}
	
	@Test(expected = UpdatePasswordException.class)
	public void updateSamePasswords() throws UpdatePasswordException {
		//given
		String PASSWORD = "password";
		int USER_ID = 6;
		//when
		service.updatePassword(USER_ID, PASSWORD, PASSWORD);
		//then
		//then	
		Assert.fail();
	}
	
	@Test(expected = UpdatePasswordException.class)
	public void updateIncorrectPassword() throws UpdatePasswordException {
		//given
		String OLD_PASSWORD = "incpassword";
		String NEW_PASSWORD = "nowe_haslo";
		int USER_ID = 6;
		//when
		service.updatePassword(USER_ID, OLD_PASSWORD, NEW_PASSWORD);
		//then
		//then	
		Assert.fail();
	}
}
	