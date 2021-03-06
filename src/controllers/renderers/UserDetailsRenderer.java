package controllers.renderers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dao.entities.Users;
import services.ManageUsersService;

@Component
public class UserDetailsRenderer {
	
	private JSONObject response;
	
	@Autowired
	private ManageUsersService userService;
	
	public String getJSON(String login) {
		Users user = userService.getUserByName(login);
		response = new JSONObject()
				.put("user_id", user.getId())
				.put("username", user.getUserName())
				.put("firstName", user.getFirstName())
				.put("lastName", user.getLastName())
				.put("email", user.getEmail())
				.put("enabled", user.isEnabled())
				.put("role", user.getRole().getRole());
		return response.toString();
	}

}
