package tests;

import java.util.Set;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import configurations.DataSourceConfigurationDev;
import configurations.DatabaseConfiguration;
import configurations.SecurityConfig;
import dao.entities.EntityTypes;
import dao.entities.Users;
import dao.repository.EntityTypesRepository;
import dao.repository.ManageUsersRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DatabaseConfiguration.class, DataSourceConfigurationDev.class, SecurityConfig.class})
@ActiveProfiles("dev")
public class FollowersDaoTest {

	@Resource
	private EntityTypesRepository entityTypesRepository;
	
	@Resource
	private ManageUsersRepository manageUsersRepository;
	
	@Test
	public void addFollowersTest() {
		//given
		Users user = manageUsersRepository.findByUserId(1);
		EntityTypes entity = entityTypesRepository.findById(1);
		entity.addUser(user);
		Assert.assertEquals("EntityList size before add", 1, manageUsersRepository.findByUserId(1).getEntityTypes().size());
		//when
		entityTypesRepository.save(entity);
		//then
		user = manageUsersRepository.findByUserId(1);
		Set<EntityTypes> entityTypes = user.getEntityTypes();
		Assert.assertEquals("EntityList size", 2, entityTypes.size());
	}
	
	@Test
	public void removeFollowedTest() {
		/* Remove only one pair of entitytype and user*/
		//given
		Users user = manageUsersRepository.findByUserId(3);
		EntityTypes entity = entityTypesRepository.findByEntityType("New user registered");
		entity.addUser(user);		
		entityTypesRepository.save(entity);
		user = manageUsersRepository.findByUserId(3);
		Assert.assertEquals("EntityList size before delete", 2, user.getEntityTypes().size());
		//when
		entityTypesRepository.deleteByUserIdAndEntityType(3, 1);
		//then
		user = manageUsersRepository.findByUserId(3);
		Assert.assertEquals("EntityList size after delete", 1, user.getEntityTypes().size());
	}

	@Test
	public void getFollowersForUser_1_Test() {
		//given
		Users user = manageUsersRepository.findByUserId(1);
		//when
		Set<EntityTypes> entityTypes = user.getEntityTypes();
		//then
		Assert.assertEquals("EntityList size", 1, entityTypes.size());		
	}
	
	@Test
	public void getFollowersForUser_2_Test() {
		//given
		Users user = manageUsersRepository.findByUserId(2);
		//when
		Set<EntityTypes> entityTypes = user.getEntityTypes();
		//then
		Assert.assertEquals("EntityList size", 2, entityTypes.size());
		
	}
}
