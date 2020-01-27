package dao.repository;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dao.AbstractDao;
import dao.entities.Users;

@Repository
@Transactional
public class ManageUsersDaoImpl extends AbstractDao implements ManageUsersDao {

	@Override
	public Users getUserByName(final String username) {
		Query query = getSession().createQuery("from Users where username=:username")
				.setParameter("username", username);
		Users user = (Users) query.uniqueResult();
		return user;
	}

	@Override
	public List<Users> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUser(final Users user) {
		// TODO Auto-generated method stub
		
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
