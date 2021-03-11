package dao.entities;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name="notification")
public class Notification {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "read", nullable = false)
	@ColumnDefault("false")
	private boolean read;

	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="notification_entity_id", referencedColumnName = "id")
	private NotificationEntity notificationEntity;

	public void setNotificationEntity(NotificationEntity notificationEntity) {
		this.notificationEntity = notificationEntity;
		notificationEntity.setNotification(this);
	}

	public NotificationEntity getNotificationEntity() {
		return this.notificationEntity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}	
}