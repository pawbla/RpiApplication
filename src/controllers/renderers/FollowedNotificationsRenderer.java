package controllers.renderers;

import dao.entities.EntityTypes;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.FollowersService;

import java.util.Set;

@Component
public class FollowedNotificationsRenderer {
    private JSONObject response;

    @Autowired
    private FollowersService followersService;

    public String getJSON(int user_id) {
        response = new JSONObject();

        Set<EntityTypes> followedEntities = followersService.getFollowedEntities(user_id);

        followersService.getAllEntityTypes().forEach(entityTypes -> {
            response.put(String.valueOf(entityTypes.getId()), getDescription(entityTypes, followedEntities));
        });

        return response.toString();
    }

    private JSONObject getDescription(EntityTypes entityTypes, Set<EntityTypes> followedEntities) {
        JSONObject descr = new JSONObject();
        descr.put("type", entityTypes.getEntityType());
        boolean followed = false;
        if (followedEntities.contains(entityTypes)) {
            followed = true;
        }
        descr.put("followed", followed);
        return descr;
    }
}
