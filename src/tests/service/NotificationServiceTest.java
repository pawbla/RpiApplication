package tests.service;

import configurations.DataSourceConfigurationDev;
import configurations.DatabaseConfiguration;
import configurations.SecurityConfig;
import dao.entities.Notification;
import dao.entities.NotificationEntity;
import dao.repository.NotificationRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import services.FollowersService;
import services.NotificationService;
import tests.ConfigurationTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DatabaseConfiguration.class, DataSourceConfigurationDev.class,
		SecurityConfig.class, ConfigurationTest.class})
@ActiveProfiles("dev")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class NotificationServiceTest {
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private FollowersService followersService;
	
	//only for test purpose to do not create unusbale service method for get notification by id
	@Resource
	private NotificationRepository notificationRepository;

	@Test
	public void getNotificationsTest() {
		//given
		int user_id = 1;
		//when
		List<Notification> notifications = notificationService.getNotifications(user_id);
		//then
		Assert.assertEquals("Notification list size for user 1", 2, notifications.size());
	}

	@Test
	public void getNotificatoinEntityTest() {
		//given
		int notification_id = 1;
		//when
		NotificationEntity notificationEntity = notificationService.getNotificationEntity(notification_id);
		//then
		Assert.assertEquals("Entity type id", 3, notificationEntity.getEntity_type_id());
		Assert.assertEquals("Sender id", 7, notificationEntity.getSender_id());
		Assert.assertEquals("Message", "NOTIFICATION TO ADD TEST", notificationEntity.getMessage());
	}

	@Test
	public void addNotificationTest() {
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
		notificationService.addNotification(notificationEntity);
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
	public void removeExpiredAndUnReadNotificationsTest() {
		//given
		notificationService.removeExpiredAndReadNotifications();
		int size = notificationRepository.findAll().size();
		int sizeEntity = notificationRepository.findAllNotificationEntities().size();
		//then
		notificationService.removeExpiredAndUnReadNotifications();
		//when
		Assert.assertEquals("Notification list size", size - 1, notificationRepository.findAll().size());
		Assert.assertEquals("Notification Entity list size", sizeEntity - 1,
				notificationRepository.findAllNotificationEntities().size());
		Assert.assertTrue(notificationRepository.findById(1).isPresent());
		Assert.assertFalse(notificationRepository.findById(2).isPresent());
		Assert.assertFalse(notificationRepository.findById(3).isPresent());
	}

	@Test
	public void removeExpiredAndReadNotificationsTest() {
		//given
		int size = notificationRepository.findAll().size();
		int sizeEntity = notificationRepository.findAllNotificationEntities().size();
		//then
		notificationService.removeExpiredAndReadNotifications();
		//when
		Assert.assertEquals("Notification list size", size - 2, notificationRepository.findAll().size());
		Assert.assertEquals("Notification Entity list size", sizeEntity - 2,
				notificationRepository.findAllNotificationEntities().size());
		Assert.assertTrue(notificationRepository.findById(1).isPresent());
		Assert.assertFalse(notificationRepository.findById(2).isPresent());
		Assert.assertFalse(notificationRepository.findById(3).isPresent());
	}

	@Test
	public void switchReadUnreadTest() {
		//given
		int id = 2;
		//from false to true
		//when
		notificationService.changeReadStatus(id, true);
		//then
		Assert.assertTrue("Read status true", notificationRepository.findById(id).get().isRead());
		//from true to false
		//when
		notificationService.changeReadStatus(id, false);
		//then
		Assert.assertFalse("Read status false", notificationRepository.findById(id).get().isRead());
	}
}
