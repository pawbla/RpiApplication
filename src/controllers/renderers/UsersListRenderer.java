package controllers.renderers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dao.service.ManageUsersService;

@Component
public class UsersListRenderer implements RestRespRenderer  {
	
	private JSONObject response;

	@Autowired
	private ManageUsersService userService;
	
	@Override
	public String getJSON() {
		response = new JSONObject();
		JSONArray array = new JSONArray();
		userService.getUsers().forEach(user -> {
			JSONObject userItem = new JSONObject()
					.put("id", user.getId())
					.put("userName", user.getUserName())
					.put("firstName", user.getFirstName())
					.put("lastName", user.getLastName())
					.put("role", user.getRole().getRole())
					.put("email", user.getEmail());
			array.put(userItem);
		});
		response.put("users", array);
		return response.toString();
	}
}
