package resources;

import pojo.AdminLoginPayload;

public class TestDataBuild {
	
	public AdminLoginPayload adminUserLoginPayload(String user_name, String password ) {
		
		AdminLoginPayload loginRequest = new AdminLoginPayload();
		loginRequest.setUser_name(user_name);
		loginRequest.setPassword(password);
		return loginRequest;
	}

}
