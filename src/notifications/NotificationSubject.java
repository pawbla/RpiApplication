package notifications;

import dao.entities.NotificationEntity;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class NotificationSubject implements Subject {

	final private List<Observer> observers = new ArrayList<>();

	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void notifyObserver(final int entity_type_id, final int sender_id, final String message) {
		observers.forEach(observer -> {
			observer.update(new NotificationEntity(entity_type_id, sender_id, this.getCreateDate(), message));
		});
	}

	private Date getCreateDate() {
		LocalDate localDate = LocalDate.now();
		Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		return Date.from(instant);
	}
	
}
