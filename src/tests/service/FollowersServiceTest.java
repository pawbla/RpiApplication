package tests.service;

import configurations.DataSourceConfigurationDev;
import configurations.DatabaseConfiguration;
import configurations.SecurityConfig;
import dao.entities.EntityTypes;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import services.FollowersService;
import services.ManageUsersService;
import tests.ConfigurationTest;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DatabaseConfiguration.class, DataSourceConfigurationDev.class,
		SecurityConfig.class, ConfigurationTest.class})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("dev")
public class FollowersServiceTest {
	
	@Autowired
	private FollowersService followersService;

	@Autowired
	private ManageUsersService usersService;
	
	@Test
	public void entityListTest() {
		//given
		final int user_id = 2;
		//when
		Set<EntityTypes> entityTypes = followersService.getFollowedEntities(user_id);
		//then	
		Assert.assertEquals("Entity list size", 2, entityTypes.size());
	}
	
	@Test
	public void allEntityListTest() {
		//given
		//when
		List<EntityTypes> entityTypes = followersService.getAllEntityTypes();
		//then
		Assert.assertEquals("EntityList size", 4, entityTypes.size());
	}

	@Test
	public void updateFollowedEntities() {
		//given
		int user_id = 2;
		String nickname = "guest";
		Map<String, Boolean> changedEntity = Map.of("1", true, "2", false,
				"3", true, "4", false);
		//when
		followersService.updateFollowedEntities(user_id, changedEntity);
		//then
		Set<EntityTypes> entityTypes = followersService.getFollowedEntities(user_id);
		Assert.assertEquals("Size of list", 2, entityTypes.size());
		List<EntityTypes> entityTypesList = followersService.getAllEntityTypes();
		Assert.assertTrue("Entity is followed",
				entityTypesList.get(0).getUsers().contains(usersService.getUserByName(nickname)));
		Assert.assertFalse("Entity is not followed",
				entityTypesList.get(1).getUsers().contains(usersService.getUserByName(nickname)));
		Assert.assertTrue("Entity is followed",
				entityTypesList.get(2).getUsers().contains(usersService.getUserByName(nickname)));
		Assert.assertFalse("Entity is not followed",
				entityTypesList.get(3).getUsers().contains(usersService.getUserByName(nickname)));
	}
}
