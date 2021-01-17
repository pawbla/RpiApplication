package dao.service;

import java.util.List;

import dao.entities.Notification;
import dao.entities.NotificationEntity;

public interface NotificationService {
	
	/**
	 * Add created notification to all followed users
	 * @param notification created notification
	 */
	public void addNotificationForFollowers(NotificationEntity notificationEntity);
	
	/**
	 * Get all notification by user id
	 * @param user_id user id
	 * @return list of notifications
	 */
	public List<Notification> getNotifications(final int user_id);
	
	/**
	 * Get notification entity by id
	 * @param id notification id
	 * @return notification entity object
	 */
	public NotificationEntity getNotificationEntity(final int id);
}
