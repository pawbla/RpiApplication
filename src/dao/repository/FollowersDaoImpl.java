package dao.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dao.AbstractDao;

@Repository
@Transactional
public class FollowersDaoImpl extends AbstractDao implements FollowersDao {

	@Override
	public void followNotification(int userId, int entityTypeId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unfollowNotification(int userId, int entityTypeId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getFollowedNotifications(int userId) {

	}

}
