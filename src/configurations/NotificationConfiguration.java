package configurations;

import notifications.Observer;
import notifications.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
@ComponentScan({"notifications"})
public class NotificationConfiguration {

    @Autowired
    Observer databseObserver;

    @Autowired
    Subject notificationSubject;

    @EventListener(ApplicationReadyEvent.class)
    public void setDatabaseObserver() {
        notificationSubject.registerObserver(databseObserver);
    }
}
