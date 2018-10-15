package users;

import homeSystem.EmbeddedApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class ManageUserDB implements ManageUsers {
	private static final Logger logger = LogManager.getLogger(EmbeddedApp.class);	
	
	private static final String INSERT_USER = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
	private static final String INSERT_USER_INROLE = "INSERT INTO roles (username) VALUES (?)";
	private static final String SQL_SELECT_PASSWORD = "SELECT password FROM users WHERE username = ?";
	private static final String SQL_SELECT_USERS_DATA = "SELECT users.id, users.username, users.email, users.enabled, roles.role FROM users INNER JOIN roles ON users.username = roles.username";
	private static final String SQL_SELECT_ENABLED = "SELECT enabled FROM users WHERE username = ?";
	private static final String SQL_UPDATE_PASSWORD = "UPDATE users SET password = ? WHERE username = ? ";
	private static final String SQL_UPDATE_ENABLED = "UPDATE users SET enabled = ? WHERE username = ? ";
	private static final String SQL_UPDATE_ROLE = "UPDATE roles SET role = ? WHERE username = ? ";
	private static final String SQL_UPDATE_EMAIL = "UPDATE users SET email = ? WHERE username = ? ";
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	private ArrayList<User> users;
	private ArrayList<String> roles;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public ManageUserDB () {
		logger.info("Create object");
		users = new ArrayList<User>();
		roles = new ArrayList<String>();
		roles.add("ROLE_USER");
		roles.add("ROLE_ADMIN");
	}
	
	public boolean addUser (User u) {
		// if false - user has existing
		logger.info("Add user " + u.getUsername());
		try {
			jdbcTemplate.update(INSERT_USER, u.getUsername(), passwordEncoder.encode(u.getPassword()),u.getEmail());
			u.setPassword(" ");
			jdbcTemplate.update(INSERT_USER_INROLE,u.getUsername());
		} catch (DataAccessException e) {
			logger.warn("Exception during addUser has occured: " + e);
			return false;			
		}

		return true;
	}

	public void setPassword (String p, String n) {
		logger.info("Set password for user: " + n);
		jdbcTemplate.update(SQL_UPDATE_PASSWORD, p, n);
	}
	
	public boolean setEmail (User user) {
		logger.info("Set email for user: " + user.getUsername());
		if (user.getUsername().equals(null) || user.getEmail().equals(null)) {
			logger.info("Password cannot be changed due to comparision issue.");
			return false;
		}
		jdbcTemplate.update(SQL_UPDATE_EMAIL, user.getEmail(), user.getUsername());
		return true;
	}
	
	public String getPassword (User user) {
		logger.info("Get password for user: " + user.getUsername());
		String password;
		 try {
			 password = (String) jdbcTemplate.queryForObject(SQL_SELECT_PASSWORD, new Object[] { user.getUsername() }, String.class);
		 } catch (Exception e) {
			 return null;
		 }
		 return password;
	}
	
	public void loadUsers() {
		logger.info("Load users");
		users.clear();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL_SELECT_USERS_DATA);
		for (Map<String, Object> row : rows) {
			User u = new User();
			u.setId((int)row.get("id"));
			u.setUsername((String)row.get("username"));
			u.setEmail((String)row.get("email"));
			u.setEnabled((boolean)row.get("enabled"));
			u.setRole((String)row.get("role"));
			users.add(u);
		}
	}
	
	public ArrayList<User> getUsers() {
		logger.info("get Users");
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		logger.info("set Users");
		this.users = users;
	}	
	
	public String updateEnabled(ArrayList<User> users) {
		logger.info("Update enabled disabled users");
		int i = 0;
		if (!checkAdminEnabled(users)) {
			return "Nie można zmienić. Conajmniej jedna rola admin musi pozostać aktywna.";
		}
		for (User u : users) {
			if (this.users.get(i).getEnabled() != u.getEnabled()) {
				jdbcTemplate.update(SQL_UPDATE_ENABLED, u.getEnabled(),this.users.get(i).getUsername());
			}
			if (!this.users.get(i).getRole().equals(u.getRole())) {
				jdbcTemplate.update(SQL_UPDATE_ROLE, u.getRole(), this.users.get(i).getUsername());
			}
			i++;
		}
		return "Lista użytkowników została zaktualizowana.";
	}
	
	public ArrayList<String> getRoles() {
		logger.info("Get roles form DB");
		return roles;
	}
	
	//check if at least one admin role is set as enabled in db
	private boolean checkAdminEnabled (ArrayList<User> users) {
		logger.info("Check if at least one admin is enabled?");
		//check changes to admin
		for (User u : users) {
			if (u.getRole().equals("ROLE_ADMIN") && u.getEnabled()) {
				return true;
			}
		}
		//no admin role enabled has found
		return false;
	}
	
	public HttpStatus checkUserStatus (String userName) {
		System.out.println("checkUserStatus: " + userName);
		HttpStatus status;
		//get enabled from sql database
		try {
			Boolean ifEnabled = (Boolean) jdbcTemplate.queryForObject(SQL_SELECT_ENABLED, new Object[] { userName }, Boolean.class);
			System.out.println("checkUserStatus: " + ifEnabled);
			if (!ifEnabled) {
				//user add but disabled - 403
				status = HttpStatus.FORBIDDEN;
			} else {
				status = HttpStatus.OK;
			}
		} catch (DataAccessException e) {
			//no user in db - 401
			logger.trace("EXPECTED Exception !! probably user checks status for no existing username: " + e);
			status = HttpStatus.UNAUTHORIZED;
		}
		return status;
	}
}