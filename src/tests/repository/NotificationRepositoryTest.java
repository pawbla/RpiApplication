package tests.repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import configurations.DataSourceConfigurationDev;
import configurations.DatabaseConfiguration;
import configurations.SecurityConfig;
import dao.entities.Notification;
import dao.entities.NotificationEntity;
import dao.entities.Users;
import dao.repository.ManageUsersRepository;
import dao.repository.NotificationRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DatabaseConfiguration.class, DataSourceConfigurationDev.class, SecurityConfig.class})
@ActiveProfiles("dev")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class NotificationRepositoryTest {
	
	@Resource
	private NotificationRepository notificationRepository;
	
	@Resource
	private ManageUsersRepository manageUsersRepository;
	
	@Test
	public void addNotificationEntity() {
		//given
		int entity_type_id = 1;
		int sender_id = 4;
		String message = "Notification Entityt Test Message";
		Date dateNow = new Date();
		NotificationEntity notificationEntity = new NotificationEntity();
		notificationEntity.setEntity_type_id(entity_type_id);
		notificationEntity.setSender_id(sender_id);
		notificationEntity.setMessage(message);
		notificationEntity.setCreate(dateNow);
		int size = notificationRepository.findAll().size();
		//when
		notificationRepository.save(notificationEntity);
		//then
		List<NotificationEntity> notificationEntityList = notificationRepository.findAll();
		Assert.assertEquals("NotificationEntity size", size + 1, notificationEntityList.size());
		Assert.assertEquals("NotificationEntity entity type id", entity_type_id, notificationEntityList.get(size).getEntity_type_id());
		Assert.assertEquals("NotificationEntity sender id", sender_id, notificationEntityList.get(size).getSender_id());
		Assert.assertEquals("NotificationEntity message", message, notificationEntityList.get(size).getMessage());
		Assert.assertEquals("NotificationEntity date", dateNow, notificationEntityList.get(size).getCreate());
	}

	@Test
	public void removeNotificationEntity() {
		//given
		int notification_entity_id = 2;
		int size = notificationRepository.findAll().size();
		NotificationEntity notificationEntity = notificationRepository.findById(notification_entity_id);
		//when
		notificationRepository.delete(notificationEntity);
		//then	
		List<NotificationEntity> notificationEntityList = notificationRepository.findAll();
		Assert.assertEquals("NotificationEntity size", size - 1, notificationEntityList.size());
	}
	
	@Test
	public void addNotification() {
		//given
		int notification_entity_id = 2;
		int user_id = 2;
		int notificationsSize = manageUsersRepository.findByUserId(user_id).getNotificationEntity().size();
		NotificationEntity notificationEntity = notificationRepository.findById(notification_entity_id);
		Users user = manageUsersRepository.findByUserId(user_id);
		notificationEntity.addUser(user);
		//when
		notificationRepository.save(notificationEntity);
		//then	
		Assert.assertEquals("NotificationEntityList size", notificationsSize + 1, manageUsersRepository.findByUserId(user_id).getNotificationEntity().size());
	}
	
	@Test
	public void getNotificationsByUser() {
		//given
		int user_id = 2;
		int notification_entity_id_1 = 1;
		int notification_entity_id_2 = 4;
		Users user = manageUsersRepository.findByUserId(user_id);
		//add first notification to the user
		NotificationEntity notificationEntity_1 = notificationRepository.findById(notification_entity_id_1);
		notificationEntity_1.addUser(user);
		notificationRepository.save(notificationEntity_1);
		//add second notification to the user
		NotificationEntity notificationEntity_2 = notificationRepository.findById(notification_entity_id_2);
		notificationEntity_2.addUser(user);
		notificationRepository.save(notificationEntity_2);
		//when
		List<Notification> notificationsList = notificationRepository.findNotificationsByUserId(user_id);
		//then		
		Assert.assertEquals("Notification list", 3, notificationsList.size());
	}
	
	@Test
	public void getNotificationsById() {
		//given
		int id = 1;
		//when
		Notification notifications = notificationRepository.findNotificationsById(id);
		//then		
		Assert.assertEquals("User id", 2, notifications.getUser_id());
		Assert.assertEquals("Notification entity", 3, notifications.getNotification_id());
		Assert.assertFalse("Unread", notifications.isRead());
	}
	
	@Test
	public void getNotificationEntities() {
		int user_id = 3;
		int notification_entity_id_1 = 1;
		int notification_entity_id_2 = 3;
		Users user = manageUsersRepository.findByUserId(user_id);
		//add first notification to the user
		NotificationEntity notificationEntity_1 = notificationRepository.findById(notification_entity_id_1);
		notificationEntity_1.addUser(user);
		notificationRepository.save(notificationEntity_1);
		//add second notification to the user
		NotificationEntity notificationEntity_2 = notificationRepository.findById(notification_entity_id_2);
		notificationEntity_2.addUser(user);
		notificationRepository.save(notificationEntity_2);	
		List<Notification> notificationsList = notificationRepository.findNotificationsByUserId(user_id);
		List<NotificationEntity> notificationEntityList = new ArrayList<>();
		//when
		notificationsList.forEach(notification -> {
			notificationEntityList.add(notificationRepository.findById(notification.getNotification_id()));
		});		
		//then
		Assert.assertEquals("NotificationEntity list", 2, notificationEntityList.size());
	}
	
	@Test
	public void removeNotification() {
		//given
		int notification_entity_id = 3;
		int user_id = 1;
		NotificationEntity notificationEntity = notificationRepository.findById(notification_entity_id);
		Users user = manageUsersRepository.findByUserId(user_id);
		notificationEntity.addUser(user);
		notificationRepository.save(notificationEntity);
		int notificationsSize = manageUsersRepository.findByUserId(user_id).getNotificationEntity().size();
		//when
		NotificationEntity entity = notificationRepository.findById( notification_entity_id);
		Optional<Users> usert = entity.getUsers().stream().filter(userL -> userL.getId() == user_id).findAny();
		usert.get().getEntityTypes().remove(entity);
		entity.getUsers().remove(usert.get());
		notificationRepository.save(entity);
		//then		
		Assert.assertEquals("NotificationEntityList size", notificationsSize - 1, 
				manageUsersRepository.findByUserId(user_id).getNotificationEntity().size());
	}
	
	@Test
	public void removeFollowedNotificationEntity() {
		//given
		int notification_id = 3;
		int user_id = 2;
		int size = notificationRepository.findNotificationsByUserId(user_id).size();
		//when
		notificationRepository.deleteNotificationEntityById(notification_id);
		//then
		Assert.assertEquals("NotificationEntity list", size - 1, 
				notificationRepository.findNotificationsByUserId(user_id).size());
	}
	
	@Test
	public void changeReadStatusTest() {
		//given
		int id = 3;
		// change from false (default) to true
		//when
		notificationRepository.updateReadStatus(true, id);
		//then
		Assert.assertTrue("Read status true", notificationRepository.findNotificationsById(id).isRead());
		//change from true to false
		//when
		notificationRepository.updateReadStatus(false, id);
		//then
		Assert.assertFalse("Read status false", notificationRepository.findNotificationsById(id).isRead());
	}
	
	@Test
	public void getOldEnitytTypesTest() {
		//given
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -6); //six months ago
		//when
		List<NotificationEntity> oldNotification = notificationRepository.findByCreateBefore(cal.getTime());
		//then
		Assert.assertEquals("EntityTypes older than six months", 4, oldNotification.size());
	}
	
	/*@Test
	public void deleteReadNotificationTest() {
		//given
		int notification_id = 1;
		//then
		notificationRepository.deleteByNotificationIdAndRead(notification_id, true);
		//then
		Assert.assertNotNull("Unread notification exists", notificationRepository.findNotificationsById(4));
		Assert.assertNull("Read notification do not exists", notificationRepository.findNotificationsById(3));
	}
	
	@Test
	public void deleteUnReadNotificationTest() {
		//given
		int notification_id = 1;
		//then
		notificationRepository.deleteByNotificationIdAndRead(notification_id, false);
		//then
		Assert.assertNotNull("Unread notification exists", notificationRepository.findNotificationsById(3));
		Assert.assertNull("Read notification do not exists", notificationRepository.findNotificationsById(4));
	}*/
	
	
}
