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

	@Override
	public Users getUserByName(final String username) {
		Query query = getSession().createQuery("from Users where username=:username")
				.setParameter("username", username);
		return (Users) query.uniqueResult();
	}
	
	@Override
	public void addUser(final Users users) {   
		persist(users);
	}
	
	@Override 
	public Role getRole(final String role) {
		Query query = getSession().createQuery("FROM Role WHERE role=:role")
				.setParameter("role", role);		
		return (Role) query.uniqueResult();
	}
 
	@Override
	public List<Users> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeUser(final Users user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(final Users user) {
		// TODO Auto-generated method stub
		
	}

}
