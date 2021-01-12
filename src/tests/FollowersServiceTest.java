package tests;

import java.util.Set;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import configurations.DataSourceConfigurationDev;
import configurations.DatabaseConfiguration;
import configurations.SecurityConfig;
import dao.entities.EntityTypes;
import dao.service.FollowersService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DatabaseConfiguration.class, DataSourceConfigurationDev.class, SecurityConfig.class})
@ActiveProfiles("dev")
public class FollowersServiceTest {
	
	@Autowired
	private FollowersService followersService;
	
	@Test
	public void followEntityTest() {
		//given
		final int user_id = 1;
		final int entity_type_id = 1;
		Assert.assertEquals("Entity list size before add", 1, followersService.getFollowedEntities(user_id).size());
		//when
		followersService.addFollowedEntity(user_id, entity_type_id);
		//then
		Assert.assertEquals("Entity list size", 2, followersService.getFollowedEntities(user_id).size());
	}
	
	@Test
	public void unfollowEntityTest() {
		//given
		final int user_id = 3;
		final int entity_type_id = 1;
		followersService.addFollowedEntity(user_id, entity_type_id);
		Assert.assertEquals("Entity list size before remove", 2, followersService.getFollowedEntities(user_id).size());
		//when
		followersService.removeFollowedEntity(user_id, entity_type_id);
		//then
		Assert.assertEquals("Entity list size after remove", 1, followersService.getFollowedEntities(user_id).size());
	}
	
	@Test
	public void entityListTest() {
		//given
		final int user_id = 2;
		//when
		Set<EntityTypes> entityTypes = followersService.getFollowedEntities(user_id);
		//then	
		Assert.assertEquals("Entity list size", 2, entityTypes.size());
	}
}
