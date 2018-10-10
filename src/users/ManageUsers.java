package users;

import java.util.ArrayList;

public interface ManageUsers {
	public boolean addUser (User user);
	public boolean setEmail (User user);
	public void setPassword (String p, String n);
	public String getPassword (User user);
	public void loadUsers();
	public String updateEnabled(ArrayList<User> users);
	public ArrayList<User> getUsers();
	public void setUsers(ArrayList<User> users);
	public ArrayList<String> getRoles();
	public String checkUserStatus (String userName);
}