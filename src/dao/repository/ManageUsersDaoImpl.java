package dao.repository;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dao.AbstractDao;
import dao.entities.User;

@Repository
@Transactional
public class ManageUsersDaoImpl extends AbstractDao implements ManageUsersDao {

	@Override
	public User getUser(String nickname) {
	
		Query query = getSession().createQuery("from User where nickname=:nickname")
				.setParameter("nickname", nickname);
		User user = (User) query.uniqueResult();
		return user;
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

}
