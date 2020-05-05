package dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.entities.Users;
import dao.repository.ManageUsersDao;

@Service
@Transactional
public class ManageUsersImpl implements ManageUsersService {
	
	@Autowired
	ManageUsersDao dao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

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
	public void removeUser(final int user_id) {
		dao.removeUser(user_id);	
	}

	@Override
	public void updateUser(final int user_id, final Users user) {
		user.setRole(dao.getRole(user.getRole().getRole()));
		dao.updateUser(user_id, user);
	}
}
