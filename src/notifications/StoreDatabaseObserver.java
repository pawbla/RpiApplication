package notifications;

import dao.entities.NotificationEntity;
import services.NotificationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StoreDatabaseObserver implements Observer {

	private final Logger logger = LogManager.getLogger(this.getClass().getName());

	@Autowired
	private NotificationService notificationService;

	@Override
	public void update(NotificationEntity entity) {
		logger.debug("Update database with notification entity ");
		notificationService.addNotification(entity);
	}

}
