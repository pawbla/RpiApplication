package dao.repository;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dao.AbstractDao;
import dao.entities.Role;
import dao.entities.Users;

@Repository
@Transactional
public class ManageUsersDaoImpl extends AbstractDao implements ManageUsersDao {
	
	private String GET_USER_BY_NAME_QUERY = "FROM Users WHERE username=:username";
	private String GET_ROLE_QUERY = "FROM Role WHERE role=:role";
	private String GET_USERS_QUERY = "FROM Users";
	private String GET_NUMBER_ADMINS = "SELECT count(*) FROM Users WHERE role_id = (SELECT role_id FROM Role WHERE role='ROLE_ADMIN')";

	@Override
	public Users getUserByName(final String username) {
		Query query = getSession().createQuery(GET_USER_BY_NAME_QUERY)
				.setParameter("username", username);
		return (Users) query.uniqueResult();
	}
	
	@Override
	public void addUser(final Users users) {   
		persist(users);
	}
	
	@Override 
	public Role getRole(final String role) {
		Query query = getSession().createQuery(GET_ROLE_QUERY)
				.setParameter("role", role);		
		return (Role) query.uniqueResult();
	}
 
	@SuppressWarnings("unchecked")
	@Override
	public List<Users> getUsers() {
		Query query = getSession().createQuery(GET_USERS_QUERY);
		return query.list();
	}

	@Override
	public void removeUser(final int user_id) {	
		Users user = getSession().get(Users.class, user_id);
		
		getSession().delete(user);
		getSession().flush();
	}

	@Override
	public void updateUser(final int user_id, final Users updatedUser) {
		Users userToUpdate = getSession().get(Users.class, user_id);
		
		userToUpdate.setFirstName(updatedUser.getFirstName());
		userToUpdate.setLastName(updatedUser.getLastName());
		userToUpdate.setEnabled(updatedUser.isEnabled());
		userToUpdate.setEmail(updatedUser.getEmail());
		userToUpdate.setRole(updatedUser.getRole());
		
		getSession().update(userToUpdate);
		
	}
	
	@Override
	public int getNumberOfAdmins() {
		Query query = getSession().createQuery(GET_NUMBER_ADMINS);
		Long longNumber = (Long) query.uniqueResult();
		return Long.valueOf(longNumber).intValue();
	}
	
	public Users getUserById(final int user_id) {
		return getSession().get(Users.class, user_id);
	}

	public void updatePassword(final int user_id, String password) {
		Users user = this.getUserById(user_id);
		user.setPassword(password);
		persist(user);
	}
}
