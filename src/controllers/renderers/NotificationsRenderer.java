package controllers.renderers;

import dao.entities.Notification;
import dao.entities.NotificationEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.NotificationService;

@Component
public class NotificationsRenderer {

    @Autowired
    private NotificationService notificationService;

    public String getJSON(int user_id) {
        JSONObject response = new JSONObject();
        JSONArray array = new JSONArray();
        notificationService.getNotifications(user_id).forEach(notification -> {
            array.put(this.parseNotifications(notification));
        });
        response.put("notifications", array);
        return response.toString();
    }

    private JSONObject parseNotifications(Notification notification) {
        return new JSONObject()
                .put("id", notification.getId())
                .put("read", notification.isRead())
                .put("entity", this.parseNotificationEntity(notification.getNotificationEntity()));
    }

    private JSONObject parseNotificationEntity(NotificationEntity entity) {
        return new JSONObject()
                .put("id", entity.getId())
                .put("entity_type_id", entity.getEntity_type_id())
                .put("sender_id", entity.getSender_id())
                .put("create", entity.getCreate())
                .put("message", entity.getMessage());
    }
}
