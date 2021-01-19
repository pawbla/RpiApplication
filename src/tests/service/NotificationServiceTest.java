package tests.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import configurations.DataSourceConfigurationDev;
import configurations.DatabaseConfiguration;
import configurations.SecurityConfig;
import dao.entities.EntityTypes;
import dao.entities.Notification;
import dao.entities.NotificationEntity;
import dao.entities.Users;
import dao.repository.NotificationRepository;
import dao.service.FollowersService;
import dao.service.NotificationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DatabaseConfiguration.class, DataSourceConfigurationDev.class, SecurityConfig.class})
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
	public void addNotificationByFollowersTest() {
		//given
		int user_id = 1;
		int entity_type_id = 2;
		int sender_id = 4;
		//follow entity
		followersService.addFollowedEntity(user_id, entity_type_id);
		//add entity
		String message = "Notification Entityt Test Message";
		NotificationEntity notificationEntity = new NotificationEntity();
		notificationEntity.setEntity_type_id(entity_type_id);
		notificationEntity.setSender_id(sender_id);
		notificationEntity.setMessage(message);
		notificationEntity.setCreate(new Date());
		int notificationsSize = notificationService.getNotifications(user_id).size();
		//when
		notificationService.addNotificationForFollowers(notificationEntity);
		//then
		Assert.assertEquals("Entity list size", notificationsSize+1, notificationService.getNotifications(user_id).size());
	}
	
	@Test
	public void getNotificationsTest() {
		//given
		int user_id = 2;
		//when
		List<Notification> notifications = notificationService.getNotifications(user_id);
		//then
		Assert.assertEquals("Notification list size", 2, notifications.size());
	}
	
	@Test
	public void getNotificationEntityTest() {
		//given
		int notification_id = 1;
		Calendar createDate = Calendar.getInstance();
		createDate.set(2021, 01, 12);
		//when
		NotificationEntity notificationEntity = notificationService.getNotificationEntity(notification_id);
		//then	
		Assert.assertEquals("Entity type id", 1, notificationEntity.getEntity_type_id());
		Assert.assertEquals("Sender id", 5, notificationEntity.getSender_id());
		Assert.assertEquals("Message", "CREATED NOTIFICATION ENTITY", notificationEntity.getMessage());
		Assert.assertEquals("Create date", new GregorianCalendar(2021, 00, 12).getTime().getTime(), 
				notificationEntity.getCreate().getTime());
	}
	
	@Test
	public void removeNotificationTest() {
		//given
		int user_id_1 = 5;
		int user_id_2 = 6;
		int notification_id = 5;
		int notification_user_id_1 = notificationService.getNotifications(user_id_1).size();
		int notification_user_id_2 = notificationService.getNotifications(user_id_2).size();
		//when
		notificationService.removeNotification(notification_id, user_id_1);
		Assert.assertNotNull("NotificationEntity exist", notificationService.getNotificationEntity(notification_id));
		notificationService.removeNotification(notification_id, user_id_2);
		//then
		Assert.assertEquals("Notifications for the first user", notification_user_id_1 - 1, 
				notificationService.getNotifications(user_id_1).size());
		Assert.assertEquals("Notifications for the second user", notification_user_id_2 - 1, 
				notificationService.getNotifications(user_id_2).size());
		Assert.assertNull("NotificationEntity not exist", notificationService.getNotificationEntity(notification_id));
	}
	
	@Test
	public void switchReadUnreadTest() {
		//given
		int id = 2;
		//from false to true
		//when
		notificationService.setReadStatus(true, id);
		//then
		Assert.assertTrue("Read status true", notificationRepository.findNotificationsById(id).isRead());
		//from true to false
		//when
		notificationService.setReadStatus(false, id);
		//then
		Assert.assertFalse("Read status false", notificationRepository.findNotificationsById(id).isRead());
	}
}
