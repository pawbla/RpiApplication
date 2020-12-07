package dao.repository;

public interface FollowersDao {
	public void followNotification(final int userId, final int entityTypeId);
	public void unfollowNotification(final int userId, final int entityTypeId);
	public void getFollowedNotifications(final int userId); //TBD WHAT SHOULD BE RETURNED
}
