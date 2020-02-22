package dao.repository;

import java.util.List;

import dao.entities.Role;
import dao.entities.Users;

public interface ManageUsersDao {

	public Users getUserByName(final String username);
	public void addUser(final Users user);
	public List<Users> getUsers();
	public Role getRole(final String role);
	public void removeUser(final Users user);
	public void updateUser(final Users user);
}
