package notifications;

import dao.entities.NotificationEntity;

public interface Observer {

	/**
	 *	Update notifification entity in database
	 * @param entity notification entity to be updated
	 */
	public void update(NotificationEntity entity);
}
