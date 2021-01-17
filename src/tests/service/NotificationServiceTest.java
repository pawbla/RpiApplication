package tests.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
import dao.service.FollowersService;
import dao.service.NotificationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DatabaseConfiguration.class, DataSourceConfigurationDev.class, SecurityConfig.class})
@ActiveProfiles("dev")
public class NotificationServiceTest {
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private FollowersService followersService;

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
	public void getNotificationTest() {
		//given
		int user_id = 2;
		//when
		List<Notification> notifications = notificationService.getNotifications(user_id);
		//then
		Assert.assertEquals("Notification list size", 3, notifications.size());
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
}
