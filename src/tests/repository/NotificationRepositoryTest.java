package tests.repository;

import configurations.DataSourceConfigurationDev;
import configurations.DatabaseConfiguration;
import configurations.SecurityConfig;
import dao.entities.Notification;
import dao.entities.NotificationEntity;
import dao.repository.ManageUsersRepository;
import dao.repository.NotificationRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tests.ConfigurationTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DatabaseConfiguration.class, DataSourceConfigurationDev.class,
		SecurityConfig.class, ConfigurationTest.class})
@ActiveProfiles("dev")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class NotificationRepositoryTest {
	
	@Resource
	private NotificationRepository notificationRepository;
	
	@Resource
	private ManageUsersRepository manageUsersRepository;

	@Test
	public void addNotification() {
		//allows to add notification entity and one to one notification, notification default unread
		//given
		int entity_type_id = 1;
		int sender_id = 4;
		String message = "Notification Entity Test Message";
		Date dateNow = new Date();
		NotificationEntity notificationEntity = new NotificationEntity();
		notificationEntity.setEntity_type_id(entity_type_id);
		notificationEntity.setSender_id(sender_id);
		notificationEntity.setMessage(message);
		notificationEntity.setCreate(dateNow);
		Notification notification = new Notification();
		notification.setNotificationEntity(notificationEntity);
		int size = notificationRepository.findAll().size();
		//when
		notificationRepository.save(notification);
		//then
		Assert.assertEquals("Notification size", size + 1, notificationRepository.findAll().size());
		NotificationEntity actNotificationEntity = notificationRepository.findAll().get(size).getNotificationEntity();
		Assert.assertEquals("Entity type id", entity_type_id, actNotificationEntity.getEntity_type_id());
		Assert.assertEquals("Sender id", sender_id, actNotificationEntity.getSender_id());
		Assert.assertEquals("Message", message, actNotificationEntity.getMessage());
		Assert.assertEquals("Date", dateNow, actNotificationEntity.getCreate());
		Assert.assertFalse("Read status", notificationRepository.findAll().get(size).isRead());
	}

	@Test
	public void removeNotification() {
		//given
		int notification_id = 1;
		int size = notificationRepository.findAll().size();
		int sizeEntity = notificationRepository.findAllNotificationEntities().size();
		//when
		notificationRepository.deleteById(notification_id);
		//then
		Assert.assertEquals("Notification size", size - 1, notificationRepository.findAll().size());
		Assert.assertEquals("Notification Entity size", sizeEntity - 1, notificationRepository.findAllNotificationEntities().size());
	}

	@Test
	public void getNotificationList() {
		//given
		//when
		List<Notification> notifications = notificationRepository.findAll();
		//then
		Assert.assertEquals("Notification list size", 4, notifications.size());
	}

	@Test
	public void getNotificationEntityByNotificationId() {
		//given
		int notification_id = 1;
		//when
		NotificationEntity notificationEntity = notificationRepository.findById(notification_id)
				.get().getNotificationEntity();
		//then
		Assert.assertEquals("Entity type id", 3, notificationEntity.getEntity_type_id());
		Assert.assertEquals("Sender id", 7, notificationEntity.getSender_id());
		Assert.assertEquals("Message", "NOTIFICATION TO ADD TEST", notificationEntity.getMessage());
	}

	@Test
	public void changeReadStatus() {
		//given
		int id = 3;
		// change from false (default) to true
		//when
		notificationRepository.updateReadStatus(true, id);
		//then
		Assert.assertTrue("Read status true", notificationRepository.findById(id).get().isRead());
		//change from true to false
		//when
		notificationRepository.updateReadStatus(false, id);
		//then
		Assert.assertFalse("Read status false", notificationRepository.findById(id).get().isRead());
	}
}
