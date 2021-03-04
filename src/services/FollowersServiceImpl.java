package services;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.entities.EntityTypes;
import dao.entities.Users;
import dao.repository.EntityTypesRepository;
import dao.repository.ManageUsersRepository;

@Service
public class FollowersServiceImpl implements FollowersService {
	
	@Resource
	private ManageUsersRepository manageUsersRepository;
	
	@Resource
	private EntityTypesRepository entityTypesRepository;

	@Override
	public void updateFollowedEntities(int user_id, Map<String, Boolean> changedEntity) {
		changedEntity.forEach((id, isFollowed) -> {
			if (isFollowed) {
				this.addFollowedEntity(user_id, Integer.parseInt(id));
			} else {
				this.removeFollowedEntity(user_id, Integer.parseInt(id));
			}
		});
	}

	private void addFollowedEntity(int user_id, int entity_type_id) {
		Users user = manageUsersRepository.findByUserId(user_id);
		EntityTypes entity = entityTypesRepository.findById(entity_type_id);
		entity.addUser(user);
		entityTypesRepository.save(entity);
	}

	private void removeFollowedEntity(int user_id, int entity_type_id) {
		entityTypesRepository.deleteByUserIdAndEntityType(user_id, entity_type_id);
	}

	@Override
	public Set<EntityTypes> getFollowedEntities(int user_id) {	
		return manageUsersRepository.findByUserId(user_id).getEntityTypes();
	}

	@Override
	public List<EntityTypes> getAllEntityTypes() {
		return entityTypesRepository.findAll();
	}
}
