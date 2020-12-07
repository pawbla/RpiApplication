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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class Users {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;
	
	@Column(name = "username", nullable = false, unique=true)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "enabled", nullable = false)
	private boolean enabled;
	
	@Column(name = "email")
	private String email;
	
	@ManyToOne(cascade= CascadeType.PERSIST)
	@JoinColumn(name="role_id")
	private Role role;
	
	@ManyToMany(mappedBy = "users")
	private Set<EntityTypes> entityTypes = new HashSet<>();
	
	public int getId() {
		return user_id;
	}
	public void setId(int user_id) {
		this.user_id = user_id;
	}
	public String getUserName() {
		return username;
	}
	public void setUserName(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Set<EntityTypes> getEntityTypes() {
		return entityTypes;
	}
	public void setEntityTypes(Set<EntityTypes> entityTypes) {
		this.entityTypes = entityTypes;
	}
}
