package dao.service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.entities.Notification;
import dao.entities.NotificationEntity;
import dao.entities.Users;
import dao.repository.EntityTypesRepository;
import dao.repository.ManageUsersRepository;
import dao.repository.NotificationRepository;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@Resource
	private EntityTypesRepository entityTypesRepository;
	
	@Resource
	private NotificationRepository notificationRepository;
	
	@Resource
	private ManageUsersRepository manageUsersRepository;

	@Override
	public void addNotificationForFollowers(NotificationEntity notificationEntity) {
		entityTypesRepository.findById(notificationEntity.getEntity_type_id()).getUsers().forEach(user -> {
			notificationEntity.addUser(manageUsersRepository.findByUserId(user.getId()));
			notificationRepository.save(notificationEntity);
		});
	}

	@Override
	public List<Notification> getNotifications(final int user_id) {
		return notificationRepository.findNotificationsByUserId(user_id);
	}

	@Override
	public NotificationEntity getNotificationEntity(int id) {
		return notificationRepository.findById(id);
	}

	@Override
	public void removeNotification(int id, int user_id) {
		NotificationEntity entity = notificationRepository.findById(id);
		Optional<Users> usert = entity.getUsers().stream().filter(userL -> userL.getId() == user_id).findAny();
		usert.get().getEntityTypes().remove(entity);
		entity.getUsers().remove(usert.get());
		notificationRepository.save(entity);
		this.removeNotificationEntityWhenNoRelation(id);
	}

	@Override
	public void setReadStatus(boolean status, int id) {
		notificationRepository.updateReadStatus(status, id);
	}

	@Override
	@Scheduled(cron = "0 0 1 * * MON")
	public void removeExpiredAndReadNotifications() {
		logger.info("Execute scheduled remove expired and read notification");
		//set one month ago date
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		System.out.println(" read");
		notificationRepository.findByCreateBefore(cal.getTime()).forEach(entity -> {
			notificationRepository.findNotificationByEntityIdAndRead(entity.getId(), true).forEach(notification -> {
				this.removeNotification(notification.getNotification_id(), notification.getUser_id());
			});
		});
	}

	@Override
	@Scheduled(cron = "0 0 1 * * MON")
	public void removeExpiredAndUnReadNotifications() {
		logger.info("Execute scheduled remove expired and unread notification");
		System.out.println(" unread");
		//set date half a year ago
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -6);
		notificationRepository.findByCreateBefore(cal.getTime()).forEach(entity -> {
			notificationRepository.findNotificationByEntityIdAndRead(entity.getId(), false).forEach(notification -> {
				this.removeNotification(notification.getNotification_id(), notification.getUser_id());
			});
		});				
	}
	
	private void removeNotificationEntityWhenNoRelation(int id) {
		if (notificationRepository.findById(id).getUsers().size() == 0) {
			notificationRepository.deleteNotificationEntityById(id);
		}
	}
}
