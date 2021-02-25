package notifications;

import java.util.Date;

public interface Subject {
	/**
	 * Register observer to be notified about new notification entity
	 * @param o observer to be registered
	 */
	public void registerObserver(Observer o);

	/**
	 * Notifiy observer about new notification entity
	 * @param entity_type_id entity type id
	 * @param sender_id sender id
	 * @param message message
	 */
	public void notifyObserver(final int entity_type_id, final int sender_id, final String message);
}
