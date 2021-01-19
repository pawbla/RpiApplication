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
	
	/**
	 * Remove notification from database. When there is no notification connected to entity, 
	 * notification entity will be removed too.
	 * @param id notification id
	 * @param user_id user id
	 */
	public void removeNotification(final int id, final int user_id);
	
	/**
	 * Set read/unread status for notification
	 * @param status status to be set
	 * @param id notification id
	 */
	public void setReadStatus(boolean status, int id);
}
