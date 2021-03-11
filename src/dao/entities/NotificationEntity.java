package dao.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="notification_entity")
public class NotificationEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "entity_type_id", nullable = false)
	private int entity_type_id;
	
	@Column(name = "sender_id", nullable = false)
	private int sender_id;
	
	@Column(name = "create", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date create;

	@Column(name = "message", nullable = false)
	private String message;

	@OneToOne(mappedBy="notificationEntity")
	private Notification notification;

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	public NotificationEntity() {};

	public NotificationEntity(final int entity_type_id, final int sender_id,
							  final Date create, final String message) {
		this.entity_type_id = entity_type_id;
		this.sender_id = sender_id;
		this.create = create;
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSender_id() {
		return sender_id;
	}

	public void setSender_id(int sender_id) {
		this.sender_id = sender_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getEntity_type_id() {
		return entity_type_id;
	}

	public void setEntity_type_id(int entity_type_id) {
		this.entity_type_id = entity_type_id;
	}

	public Date getCreate() {
		return create;
	}

	public void setCreate(Date create) {
		this.create = create;
	}
}