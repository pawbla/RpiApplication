package services;

import dao.entities.Notification;
import dao.entities.NotificationEntity;
import dao.repository.NotificationRepository;
import notifications.wrapper.NotificationWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(this.getClass().getName());

	@Resource
	private NotificationRepository notificationRepository;

	@Autowired
	private FollowersService followersService;

	@Autowired
	@Qualifier("removeExpired")
	NotificationWrapper notifyRemoveExpired;

	private static final int SENDER_ID = 2;

	@Override
	public List<Notification> getNotifications(int user_id) {
		List<Integer> followedIds = followersService.getFollowedEntities(user_id).stream()
				.map(followedEntity -> followedEntity.getId()).collect(Collectors.toList());
		return notificationRepository.findAll().stream()
				.filter(notification -> followedIds.contains(notification.getNotificationEntity().getEntity_type_id()))
				.collect(Collectors.toList());
	}

	@Override
	public NotificationEntity getNotificationEntity(int id) {
		return notificationRepository.findById(id).get().getNotificationEntity();
	}

	@Override
	public void addNotification(NotificationEntity notificationEntity) {
		Notification notification = new Notification();
		notification.setNotificationEntity(notificationEntity);
		notificationRepository.save(notification);
	}

	@Override
	public void changeReadStatus(int id, boolean status) {
		notificationRepository.updateReadStatus(status, id);
	}

	@Override
	@Scheduled(cron = "0 0 1 * * MON")
	public void removeExpiredAndReadNotifications() {
		logger.info("Execute scheduled remove expired and read notification");
		//set one month ago date
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		notificationRepository.findAll().stream().filter(notification ->
			notification.getNotificationEntity().getCreate().before(cal.getTime())
				&& notification.isRead()
		).forEach(notification -> {
			this.removeNotification(notification.getId());
			notifyRemoveExpired.notifyObserver(SENDER_ID, notification);
		});
	}

	@Override
	@Scheduled(cron = "0 0 1 * * MON")
	public void removeExpiredAndUnReadNotifications() {
		logger.info("Execute scheduled remove expired and unread notification");
		//set date half a year ago
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -6);
		notificationRepository.findAll().stream().filter(notification ->
				notification.getNotificationEntity().getCreate().before(cal.getTime())
		).forEach(notification -> {
			this.removeNotification(notification.getId());
			notifyRemoveExpired.notifyObserver(SENDER_ID, notification);
		});
	}

	private void removeNotification(int id) {
		notificationRepository.deleteById(id);
	}
}
