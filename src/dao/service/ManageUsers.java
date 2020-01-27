package dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.entities.User;

import dao.repository.ManageUsersDao;

@Service
@Transactional
public class ManageUsers implements ManageUsersService {
	
	@Autowired
	ManageUsersDao dao;

	@Override
	public User getUser(String nickname) {
		return dao.getUser(nickname);
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}
}
