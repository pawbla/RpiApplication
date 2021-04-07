package controllers.renderers;

import dao.entities.EntityTypes;
import dao.entities.Notification;
import org.json.JSONArray;
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
        JSONArray array = new JSONArray();
        Set<EntityTypes> followedEntities = followersService.getFollowedEntities(user_id);

        followersService.getAllEntityTypes().forEach(entityTypes -> {
           array.put(this.parseFollowedNotifications(entityTypes, followedEntities));
        });
        response.put("followed_notifications", array);
        return response.toString();
    }

    private JSONObject parseFollowedNotifications(EntityTypes entity, Set<EntityTypes> followedEntities) {
        return new JSONObject()
                .put("id", entity.getId())
                .put("type", entity.getEntityType())
                .put("followed", followedEntities.contains(entity));
    }
}
