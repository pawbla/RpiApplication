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
	public void removeUser(final String username) {	
		Users user = getUserByName(username);
		
		getSession().delete(user);
		getSession().flush();
	}

	@Override
	public void updateUser(final Users updatedUser) {
		Users userToUpdate = getUserByName(updatedUser.getUserName());
		userToUpdate.setFirstName(updatedUser.getFirstName());
		userToUpdate.setLastName(updatedUser.getLastName());
		userToUpdate.setEnabled(updatedUser.isEnabled());
		userToUpdate.setEmail(updatedUser.getEmail());
		userToUpdate.setRole(updatedUser.getRole());
		
		getSession().update(userToUpdate);
		
	}

}