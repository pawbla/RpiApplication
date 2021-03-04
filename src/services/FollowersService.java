package services;

import java.util.List;
import java.util.Map;
import java.util.Set;

import dao.entities.EntityTypes;

public interface FollowersService {
	
	/**
	 * Get entity type list followed by the user
	 * @param user_id id of the user
	 * @return set of entity types followed by the user
	 */
	Set<EntityTypes> getFollowedEntities(final int user_id);
	
	/**
	 * Get all existing entity types
	 * @return list of existing entity types
	 */
	List<EntityTypes> getAllEntityTypes();

	/**
	 * Update followed/unfollowed notifications for user
	 * @param user_id user id
	 * @param changedEntity map of notification type id and enabled/disabled status to be changed
	 */
	void updateFollowedEntities(int user_id, Map<String, Boolean>  changedEntity);
}
