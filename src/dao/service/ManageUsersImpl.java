package dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.entities.Users;
import dao.repository.ManageUsersRepository;
import exceptions.RemoveAllAdminsException;
import exceptions.UpdatePasswordException;

@Service
@Transactional
public class ManageUsersImpl implements ManageUsersService {
	
	@Autowired
	ManageUsersRepository dao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private static final String LAST_ADMIN_ROLE_MSG = "Operation could remove admin access to application.";
	private static final String OLD_NEW_PASS_SAME_MSG = "Old and new password cannot be the same";
	private static final String INCORRECT_PASS_MSG = "Incorrect password";
	
	@Override
	public Users getUserByName(String username) {
		return dao.findByUsername(username);
	}
	@Override
	public List<Users> getUsers() {
		return dao.findUsers();
	}
	@Override
	public void addUser(Users user) {
		//always store user with role as below
		user.setRole(dao.findRole("ROLE_USER"));
		//password should be encoded before save
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		//user should be allways stored as disabled
		if (user.isEnabled()) {
			user.setEnabled(false);
		}
		dao.save(user);
		
	}
	@Override
	public void removeUser(int user_id) throws RemoveAllAdminsException {
		Users user = dao.findByUserId(user_id);
		//do not allow to remove all admins from database
		if (allowRemoveIfAdmin(user_id)) {
			dao.delete(user);
		} else {
			throw new RemoveAllAdminsException(LAST_ADMIN_ROLE_MSG);	
		}
		
	}
	@Override
	public void updateUser(int user_id, Users user) throws RemoveAllAdminsException {
		Users userDao = dao.findByUserId(user_id);
		userDao.setEmail(user.getEmail());
		userDao.setEnabled(user.isEnabled());
		userDao.setFirstName(user.getFirstName());
		userDao.setLastName(user.getLastName());
		if (allowChangeIfAdminRole(user_id, user.getRole().getRole(), user.isEnabled())) {
			userDao.setRole(dao.findRole(user.getRole().getRole()));
			dao.save(userDao);
		} else {
			throw new RemoveAllAdminsException(LAST_ADMIN_ROLE_MSG);
		}
		
	}
	@Override
	public void updatePassword(int user_id, String oldPassword, String newPassword) throws UpdatePasswordException {
		if (oldPassword.equals(newPassword)) {
			throw new UpdatePasswordException(OLD_NEW_PASS_SAME_MSG);
		}
		if (passwordEncoder.matches(oldPassword, dao.findByUserId(user_id).getPassword())) {
			dao.updatePassword(user_id, passwordEncoder.encode(newPassword));
		} else {
			throw new UpdatePasswordException(INCORRECT_PASS_MSG);
		}
	}
	
	private boolean allowChangeIfAdminRole(final int user_id, String updatedRole, boolean updatedEnable) {
		boolean allowOperation = true;
		Users user = dao.findByUserId(user_id);
		if ("ROLE_ADMIN".equals(user.getRole().getRole()) && dao.countAdmins() <= 1) {
			if (!("ROLE_ADMIN".equals(updatedRole) && updatedEnable)) {
				allowOperation = false;
			}
		}
		return allowOperation;
	}
	
	private boolean allowRemoveIfAdmin(final int user_id) {
		boolean allowOperation = true;
		Users user = dao.findByUserId(user_id);
		if ("ROLE_ADMIN".equals(user.getRole().getRole()) && dao.countAdmins() <= 1) {
			allowOperation = false;
		}
		return allowOperation;
	}
}
