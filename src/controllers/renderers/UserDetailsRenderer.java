package controllers.renderers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dao.entities.User;
import dao.service.ManageUsersService;

@Component
public class UserDetailsRenderer {
	
	private JSONObject response;
	
	@Autowired
	private ManageUsersService userService;
	
	public String getJSON(String login) {
		User user = userService.getUser(login);
		response = new JSONObject()
				.put("login", user.getNickName())
				.put("firstName", user.getFirstName())
				.put("lastName", user.getLastName())
				.put("role", user.getRole().getRole());
		return response.toString();
	}

}
