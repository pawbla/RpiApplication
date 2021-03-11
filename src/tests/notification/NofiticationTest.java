package tests.notification;

import configurations.DataSourceConfigurationDev;
import configurations.DatabaseConfiguration;
import configurations.SecurityConfig;
import dao.entities.Notification;
import dao.entities.NotificationEntity;
import dao.entities.Users;
import dao.repository.NotificationRepository;
import notifications.Observer;
import notifications.Subject;
import notifications.wrapper.NotificationWrapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tests.ConfigurationTest;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DatabaseConfiguration.class, DataSourceConfigurationDev.class,
        SecurityConfig.class, ConfigurationTest.class})
@ActiveProfiles("dev")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class NofiticationTest {

    @Autowired
    Observer observer;

    @Autowired
    Subject sub;

    @Autowired
    @Qualifier("addUser")
    NotificationWrapper registerUserWrapper;

    @Autowired
    @Qualifier("removeUser")
    NotificationWrapper removeUserWrapper;

    @Autowired
    @Qualifier("sensorError")
    NotificationWrapper sensorErrorWrapper;

    @Autowired
    @Qualifier("removeExpired")
    NotificationWrapper removeExpiredNotificationWrapper;

    //only for test purpose to do not create unusbale service method for get notification by id
    @Resource
    private NotificationRepository notificationRepository;

    @Before
    public void setup() {
        sub.registerObserver(observer);
    }

    @Test
    public void testNotificationObserver() {
        //given
        int sender_id = 123;
        int entity_type_id = 2;
        String text_1 = "Text message test 1";
        String text_2 = "Text message test 2";

        int notificationSize = notificationRepository.findAll().size();

        //when
        //sub.registerObserver(observer);
        sub.notifyObserver(entity_type_id, sender_id, text_1);
        sub.notifyObserver(entity_type_id, sender_id, text_2);

        //then
        Assert.assertEquals("Notification entities list size", notificationSize + 2,
                notificationRepository.findAll().size());
    }

    @Test
    public void testRegisterUserNotificationWrapper() {
        //given
        int sender_id = 123;
        Users user = new Users();
        user.setUserName("UserRegisterWrapper");
        int notificationSize = notificationRepository.findAll().size();
        //when
        registerUserWrapper.notifyObserver(sender_id, user);
        //then
        Assert.assertEquals("Notification entities list size", notificationSize + 1,
                notificationRepository.findAll().size());
        NotificationEntity entity = this.getNotificationEntity();
        Assert.assertEquals("Entity Type ID", 1, entity.getEntity_type_id());
        Assert.assertEquals("Sender ID", sender_id, entity.getSender_id());
        Assert.assertEquals("Message",
                "Nowy użytkownik: " + user.getUserName() + " został zarejestrowany.", entity.getMessage());
    }

    @Test
    public void testRemoveUserWrapper() {
        //given
        int sender_id = 123;
        int user_id = 567;
        int notificationSize = notificationRepository.findAll().size();
        //when
        removeUserWrapper.notifyObserver(sender_id, user_id);
        //then
        Assert.assertEquals("Notification entities list size", notificationSize + 1,
                notificationRepository.findAll().size());
        NotificationEntity entity = this.getNotificationEntity();
        Assert.assertEquals("Entity Type ID", 2, entity.getEntity_type_id());
        Assert.assertEquals("Sender ID", sender_id, entity.getSender_id());
        Assert.assertEquals("Message", "Użytkownik o ID " + user_id + " został usunięty.",
                entity.getMessage());
    }

    @Test
    public void testSensorErrorNotificationWrapper() {
        //given
        int sender_id = 123;
        String msg = "Test error message";
        int notificationSize = notificationRepository.findAll().size();
        //when
       sensorErrorWrapper.notifyObserver(sender_id, msg);
        //then
        Assert.assertEquals("Notification entities list size", notificationSize + 1,
                notificationRepository.findAll().size());
        NotificationEntity entity = this.getNotificationEntity();
        Assert.assertEquals("Entity Type ID", 3, entity.getEntity_type_id());
        Assert.assertEquals("Sender ID", sender_id, entity.getSender_id());
        Assert.assertEquals("Message", msg, entity.getMessage());
    }

    @Test
    public void testRemoveExpiredNotificationWrapper() {
        //given
        int sender_id = 123;
        Notification notification = notificationRepository.findAll().get(0);
        int notificationSize = notificationRepository.findAll().size();
        //when
        removeExpiredNotificationWrapper.notifyObserver(sender_id, notification);
        //then
        Assert.assertEquals("Notification entities list size", notificationSize + 1,
                notificationRepository.findAll().size());
        NotificationEntity entity = this.getNotificationEntity();
        Assert.assertEquals("Entity Type ID", 4, entity.getEntity_type_id());
        Assert.assertEquals("Sender ID", sender_id, entity.getSender_id());
        Assert.assertEquals("Message",
                "Powiadomienie " + notification.getId()    + " zostało usunięte jako przestarzałe",
                entity.getMessage());
    }

    private NotificationEntity getNotificationEntity() {
        List<Notification> notifications = notificationRepository.findAll();
        return notifications.get(notifications.size() - 1).getNotificationEntity();
    }
}
