package dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.entities.Role;
import dao.entities.Users;
import dao.repository.ManageUsersDao;

@Service
@Transactional
public class ManageUsersImpl implements ManageUsersService {
	
	@Autowired
	ManageUsersDao dao;

	@Override
	public Users getUserByName(final String username) {
		return dao.getUserByName(username);
	}
	
	@Override
	public void addUser(final Users user) {
		//always store user with role as below
		user.setRole(dao.getRole("ROLE_USER"));
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
	public void removeUser(final String username) {
		dao.removeUser(username);	
	}

	@Override
	public void updateUser(final Users user) {
		user.setRole(dao.getRole(user.getRole().getRole()));
		dao.updateUser(user);
	}
}
