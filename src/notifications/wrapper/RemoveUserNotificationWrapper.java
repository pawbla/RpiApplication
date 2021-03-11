package notifications.wrapper;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("removeUser")
public class RemoveUserNotificationWrapper extends NotificationWrapper {
    private final static int ENTITY_TYPE = 2;

    @Override
    public void notifyObserver(int sender_id, Object obj) {
        subject.notifyObserver(ENTITY_TYPE, sender_id, this.prepareMessage((int) obj));
    }

    private String prepareMessage(int user_id) {
        return "Użytkownik o ID " + user_id + " został usunięty.";
    }
}
