package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import configurations.DatabaseConfiguration;
import configurations.SecurityConfig;
import users.ManageUsers;
import users.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DatabaseConfiguration.class, SecurityConfig.class})
@ActiveProfiles("dev")
public class ManageUserDBTest {
	
	@Autowired
	private ManageUsers db;

	private User user1;
	private User user2;
	private User user3;
	private User user4;
	private User userA_1;
	private User userA_2;
	private User checkUser_e;
	private User checkUser_d;
	
	@Before
	public void createUsers() {
		System.out.println("T");
		user1 = new User();
		user1.setUsername("User1");
		user1.setPassword("Pass1");
		user1.setEmail("email@01");
		
		user2 = new User();
		user2.setUsername("User2");
		user2.setPassword("Pass2");
		user2.setEmail("email@02");	
		
		user3 = new User();
		user3.setUsername("User3");
		user3.setPassword("Pass3");
		user3.setEmail("email@03");
		
		user4 = new User();
		user4.setUsername("User4");
		user4.setPassword("Pass4");
		user4.setEmail("email@04");
		
		userA_1 = new User();
	    userA_1.setUsername("Usertt");
		userA_1.setPassword("Passt41");
		
		userA_2 = new User();
	    userA_2.setUsername("Usertt2");
		userA_2.setPassword("Passt42");
		
		checkUser_e = new User();
		checkUser_e.setUsername("User_checkEnabled");
		checkUser_e.setPassword("pass");
		checkUser_e.setEnabled(true);
		
		checkUser_d = new User();
		checkUser_d.setUsername("User_checkDisabled");
		checkUser_d.setPassword("pass");
		checkUser_d.setEnabled(false);
	}
	
	@Test 
	public void addUserTest () {
		db.addUser(user1);
		assertEquals(" ",user1.getPassword());
		db.addUser(user2);
		assertEquals(" ",user2.getPassword());
	}
	
	@Test 
	public void getPassword () {
		assertEquals("Pass1", db.getPassword(user1));
	}
	
	@Test 
	public void setPassword () {
		db.addUser(user3);
		user3.setPassword("NewPass");
		db.setPassword("NewPass", "User3");
		assertEquals("NewPass", db.getPassword(user3));
	}
	
	@Test 
	public void changeEmail () {
		db.addUser(user4);
		user4.setEmail("NewMail@2");
		db.setEmail(user4);
	}
	
    
    @Test
    public void getUserData() {
    	List<User> users = db.getUsers();
    	for (User u : users) {
    		System.out.println("User: " + u.getUsername());
    		System.out.println("User: " + u.getId());
    	}
    }
    
    @Test
    public void addUserAndroidPositiveTest () {
		assertEquals("User1",user1.getUsername());
		db.loadUsers();
		assertTrue(db.addUser(userA_2));
    }
    
    @Test
    public void addUserAndroidNegativeTest () {
    	db.addUser(userA_1);
		assertEquals("Usertt",userA_1.getUsername());
		db.loadUsers();
		assertFalse(db.addUser(userA_1));
    }
    
    
    @Test
    public void checkUserStatus_userAddAndDisabled ( ) {
    	db.addUser(checkUser_d);
    	String testUserName = "User_checkDisabled";
    	assertEquals(HttpStatus.FORBIDDEN, db.checkUserStatus(testUserName));
    }
    
    @Test
    public void checkUserStatus_NoAdd ( ) {
    	String testUserName = "User_checkNoAdd";
    	assertEquals(HttpStatus.UNAUTHORIZED, db.checkUserStatus(testUserName));
    }
}
	