package ibf2022.batch2.ssf.frontcontroller.services;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class AuthenticationService {

	// TODO: Task 2
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write the authentication method in here

	private static final String url = "https://auth.chuklee.com";

	public void authenticate(String username, String password) throws Exception {

		JSONObject requestBody = new JSONObject();
    	requestBody.put("username", username);
    	requestBody.put("password", password);

		RequestEntity<String> req = RequestEntity	
											.post(url)
											.contentType(MediaType.APPLICATION_JSON)
											.body(requestBody.toString());

		RestTemplate template = new RestTemplate();

		ResponseEntity<String> resp;
		
		try{
			resp = template.exchange(req, String.class);
	   } catch(Exception ex){
		   throw ex;
	   }

	}

	// TODO: Task 3
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to disable a user account for 30 mins
	public void disableUser(String username) {
	}

	// TODO: Task 5
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to check if a given user's login has been disabled
	public boolean isLocked(String username) {
		return false;
	}
}
