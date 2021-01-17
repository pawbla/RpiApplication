package dao.entities;

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

@Entity
@Table(name="entity_types")
public class EntityTypes {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "entitytype", nullable = false)
	private String entityType;
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
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
		return this.users;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(final String entityType) {
		this.entityType = entityType;
	}
	
	@Override
    public int hashCode(){
        int hashcode = 0;
        hashcode = id * 20;
        return hashcode;
    }
	
    @Override
    public boolean equals(Object obj){       
        if (obj instanceof EntityTypes) {
        	EntityTypes entityType = (EntityTypes) obj;
            return (entityType.getId() == this.id);
        } else {
            return false;
        }
    }
}
