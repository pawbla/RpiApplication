package notifications.wrapper;

import dao.entities.Notification;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("removeExpired")
public class RemoveExpiredNotificationWrapper extends NotificationWrapper {
    private final static int ENTITY_TYPE = 4;

    @Override
    public void notifyObserver(int sender_id, Object obj) {
        subject.notifyObserver(ENTITY_TYPE, sender_id, this.prepareMessage((Notification) obj));
    }

    private String prepareMessage(Notification notification) {
        return "Powiadomienie " + notification.getId()    + " zostało usunięte jako przestarzałe";
    }
}
