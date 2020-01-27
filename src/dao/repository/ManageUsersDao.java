package dao.repository;

import java.util.List;

import dao.entities.Users;

public interface ManageUsersDao {

	public Users getUserByName(final String username);
	public List<Users> getUsers();
	public void addUser(final Users user);
	public void removeUser(final Users user);
	public void updateUser(final Users user);
}
