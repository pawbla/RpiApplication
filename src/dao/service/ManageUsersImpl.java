package dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.entities.Users;
import dao.repository.ManageUsersDao;
import exceptions.RemoveAllAdminsException;

@Service
@Transactional
public class ManageUsersImpl implements ManageUsersService {
	
	@Autowired
	ManageUsersDao dao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private static final String LAST_ADMIN_ROLE_MSG = "Operation could remove admin access to application.";

	@Override
	public Users getUserByName(final String username) {
		return dao.getUserByName(username);
	}
	
	@Override
	public void addUser(final Users user) {
		//always store user with role as below
		user.setRole(dao.getRole("ROLE_USER"));
		//password should be encoded before save
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		//user should be allways stored as disabled
		if (user.isEnabled()) {
			user.setEnabled(false);
		}
		dao.addUser(user);
	}

	@Override
	public List<Users> getUsers() {
		return dao.getUsers();
	}

	@Override
	public void removeUser(final int user_id) throws RemoveAllAdminsException {
		//do not allow to remove all admins from database
		if (allowRemoveIfAdmin(user_id)) {
			dao.removeUser(user_id);
		} else {
			throw new RemoveAllAdminsException(LAST_ADMIN_ROLE_MSG);	
		}
	}

	@Override
	public void updateUser(final int user_id, final Users user) throws RemoveAllAdminsException {
		if (allowChangeIfAdminRole(user_id, user.getRole().getRole())) {
			user.setRole(dao.getRole(user.getRole().getRole()));
			dao.updateUser(user_id, user);
		} else {
			throw new RemoveAllAdminsException(LAST_ADMIN_ROLE_MSG);
		}

	}
	
	private boolean allowRemoveIfAdmin(final int user_id) {
		boolean allowOperation = true;
		Users user = dao.getUserById(user_id);
		if ("ROLE_ADMIN".equals(user.getRole().getRole()) && dao.getNumberOfAdmins() <= 1) {
			allowOperation = false;
		}
		return allowOperation;
	}
	
	private boolean allowChangeIfAdminRole(final int user_id, String updatedRole) {
		boolean allowOperation = true;
		Users user = dao.getUserById(user_id);
		if ("ROLE_ADMIN".equals(user.getRole().getRole()) && dao.getNumberOfAdmins() <= 1) {
			if (!"ROLE_ADMIN".equals(updatedRole)) {
				allowOperation = false;
			}
		}
		return allowOperation;
	}
}
