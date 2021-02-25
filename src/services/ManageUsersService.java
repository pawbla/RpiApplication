package services;

import java.util.List;

import dao.entities.Users;
import exceptions.RemoveAllAdminsException;
import exceptions.UpdatePasswordException;

public interface ManageUsersService {
	public Users getUserByName(final String nickname);
	public List<Users> getUsers();
	public void addUser(final Users user);
	public void removeUser(final int user_id) throws RemoveAllAdminsException;
	public void updateUser(final int user_id, final Users user) throws RemoveAllAdminsException;
	public void updatePassword(final int user_id, String oldPassword, String newPassword) throws UpdatePasswordException;
}
