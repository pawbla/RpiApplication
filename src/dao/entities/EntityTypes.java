package dao.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="entity_types")
public class EntityTypes {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "entity_type", nullable = false)
	private String entity_type;
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(
			name="followers",
			joinColumns = @JoinColumn(name="entity_type_id"),
			inverseJoinColumns = @JoinColumn(name="user_id")
	)
	private Set<Users> users =  new HashSet<>();
	
	public void addUser(Users user) {
		this.users.add(user);
		user.getEntityTypes().add(this);
	}
	
	public Set<Users> getUsers() {
		return users;
	}
	
	public void removeUser(Users user) {
		this.users.remove(user);
		user.getEntityTypes().remove(this);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEntity_type() {
		return entity_type;
	}

	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}
}
