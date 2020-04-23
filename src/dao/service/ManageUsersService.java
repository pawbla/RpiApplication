package dao.service;

import java.util.List;

import dao.entities.Users;

public interface ManageUsersService {
	public Users getUserByName(final String nickname);
	public List<Users> getUsers();
	public void addUser(final Users user);
	public void removeUser(final String username);
	public void updateUser(final Users user);
}
