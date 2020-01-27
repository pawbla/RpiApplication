package dao.repository;

import java.util.List;

import dao.entities.User;

public interface ManageUsersDao {

	public User getUser(String nickname);
	public List<User> getUsers();
	public void addUser(User user);
	public void removeUser(User user);
	public void updateUser(User user);
}
