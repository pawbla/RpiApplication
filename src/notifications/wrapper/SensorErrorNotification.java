package notifications.wrapper;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("sensorError")
public class SensorErrorNotification  extends NotificationWrapper  {
    private final static int ENTITY_TYPE = 3;

    @Override
    public void notifyObserver(int sender_id, Object obj) {
        subject.notifyObserver(ENTITY_TYPE, sender_id, (String) obj);
    }
}
