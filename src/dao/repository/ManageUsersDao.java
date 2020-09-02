package dao.repository;

import java.util.List;

import dao.entities.Role;
import dao.entities.Users;

public interface ManageUsersDao {

	public Users getUserByName(final String username);
	public Users getUserById(final int user_id);
	public void addUser(final Users user);
	public List<Users> getUsers();
	public Role getRole(final String role);
	public void removeUser(final int user_id);
	public void updateUser(final int user_id, final Users updatedUser);
	public int getNumberOfAdmins();
	public void updatePassword(final int user_id, String password);
}
