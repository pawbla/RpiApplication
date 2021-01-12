package dao.service;

import java.util.Set;

import dao.entities.EntityTypes;

public interface FollowersService {
	public void addFollowedEntity(final int user_id, final int entity_type_id);
	public void removeFollowedEntity(final int user_id, final int entity_type_id);
	public Set<EntityTypes> getFollowedEntities(final int user_id);
}
