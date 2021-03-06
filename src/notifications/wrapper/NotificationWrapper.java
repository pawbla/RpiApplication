package notifications.wrapper;

import notifications.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class NotificationWrapper {

    @Autowired
    protected Subject subject;

    public abstract void notifyObserver(final int sender_id, final Object obj);
}
