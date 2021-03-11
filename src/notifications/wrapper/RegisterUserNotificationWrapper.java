package notifications.wrapper;

import dao.entities.Users;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("addUser")
public class RegisterUserNotificationWrapper extends NotificationWrapper {
    private final static int ENTITY_TYPE = 1;

    public void notifyObserver(final int sender_id,  final Object obj) {
        subject.notifyObserver(ENTITY_TYPE, sender_id, this.prepareMessage((Users) obj));
    }

    private String prepareMessage(Users user) {
        return "Nowy użytkownik: " + user.getUserName() + " został zarejestrowany.";
    }
 }
