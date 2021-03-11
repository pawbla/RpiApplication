package services;

import java.util.List;

import dao.entities.Notification;
import dao.entities.NotificationEntity;

public interface NotificationService {

	/**
	 * Get list of stored notifications filtered by followed notifications
	 * @param user_id user id to filter out only followed notifications
	 * @return notifications' list
	 */
	List<Notification> getNotifications(final int user_id);

	/**
	 * Get notification entity associated with notification
	 * @param id notification id
	 * @return notification entity object
	 */
	NotificationEntity getNotificationEntity(final int id);
	
	/**
	 * Remove expired and read notification when notifications created date is older than one month. 
	 * When there is no notification connected to entity, notification entity will be removed too. 
	 * Method is annonated with @Scheduled and is executed at every 1st day of month 
	 */
	void removeExpiredAndReadNotifications();

	/**
	 * Remove expired and read notification when notifications created date is older than half a year. 
	 * When there is no notification connected to entity, notification entity will be removed too. 
	 * Method is annonated with @Scheduled and is executed at every 1st day of month 
	 */
	void removeExpiredAndUnReadNotifications();

	/**
	 * Add notification entity
	 * @param notificationEntity notification entity to be added
	 */
	void addNotification(NotificationEntity notificationEntity);

	/**
	 * Change read status
	 * @param id notification id
	 * @param status read/ unread status
	 */
	void changeReadStatus(int id, boolean status);
}
