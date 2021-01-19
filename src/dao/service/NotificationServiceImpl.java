package dao.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.entities.Notification;
import dao.entities.NotificationEntity;
import dao.repository.EntityTypesRepository;
import dao.repository.ManageUsersRepository;
import dao.repository.NotificationRepository;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {
	
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
		notificationRepository.deleteByUserIdAndNotificationId(user_id, id);
		if (notificationRepository.findById(id).getUsers().size() == 0) {
			notificationRepository.deleteNotificationEntityById(id);
		}
	}

	@Override
	public void setReadStatus(boolean status, int id) {
		notificationRepository.updateReadStatus(status, id);
	}
}
