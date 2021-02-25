package services;

import java.util.List;
import java.util.Set;

import dao.entities.EntityTypes;

public interface FollowersService {
	
	/**
	 * Follow entity
	 * @param user_id id of user
	 * @param entity_type_id id of followed entity type
	 */
	public void addFollowedEntity(final int user_id, final int entity_type_id);
	
	/**
	 * Unfollow entity
	 * @param user_id id of user
	 * @param entity_type_id id of entity type to unfollow
	 */
	public void removeFollowedEntity(final int user_id, final int entity_type_id);
	
	/**
	 * Get entity type list followed by the user
	 * @param user_id id of the user
	 * @return set of entity types followed by the user
	 */
	public Set<EntityTypes> getFollowedEntities(final int user_id);
	
	/**
	 * Get all existing entity types
	 * @return list of existing entity types
	 */
	public List<EntityTypes> getAllEntityTypes();
}
