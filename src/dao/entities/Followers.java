package dao.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="followers")
public class Followers {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "user_id", nullable = false)
	private int user_id;
	
	@Column(name = "entity_type_id", nullable = false)
	private int entity_type_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getEntity_type_id() {
		return entity_type_id;
	}

	public void setEntity_type_id(int entity_type_id) {
		this.entity_type_id = entity_type_id;
	}
}