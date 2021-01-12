package dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dao.entities.EntityTypes;

@Repository
@Transactional
public interface EntityTypesRepository extends JpaRepository<EntityTypes, Long> {
	EntityTypes findByEntityType(String entityType);
	EntityTypes findById(int id);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Followers WHERE user_id=:user_id AND entity_type_id=:entity_type_id")
	public void deleteByUserIdAndEntityType(final int user_id, final int entity_type_id);
	
}
