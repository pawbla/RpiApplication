package dao.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

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
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
	@JoinTable(
			name="notification",
			joinColumns = @JoinColumn(name="notification_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name="user_id", referencedColumnName = "user_id")
	)
	private Set<Users> users =  new HashSet<>();
	
	public void addUser(Users user) {
		this.users.add(user);
		user.getNotificationEntity().add(this);
	}
	
	public Set<Users> getUsers() {
		return this.users;
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