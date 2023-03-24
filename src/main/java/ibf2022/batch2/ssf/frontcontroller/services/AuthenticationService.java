package ibf2022.batch2.ssf.frontcontroller.services;

import java.util.Random;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpSession;



@Service
public class AuthenticationService {

	// TODO: Task 2
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write the authentication method in here
	@Autowired
    private HttpSession session;
	
	private static final String url = "https://auth.chuklee.com";
	public int failedLogin = 0;
	private int result = 0;

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

	   if (resp.getStatusCode().value() == 201) {
		String authenticatedUser = resp.getBody().substring("Authenticated".length());
		session.setAttribute("authenticatedUser", authenticatedUser);
	   }

	   else if(resp.getStatusCode().value() == 400|| resp.getStatusCode().value() == 401){
		failedLogin++;
		throw new Exception("Authentication failed: Invalid username or password");
	   }

	}

	public String generateMathQn(){
		Random random = new Random();
		int num1 = random.nextInt(50)+1;
		int num2 = random.nextInt(50)+1;
		int operation = random.nextInt(4);
		String opStr = "";

		switch (operation) {
			case 0:
				opStr = "+";
				break;
			case 1:
				opStr = "-";
				break;
			case 2:
				opStr = "*";
				break;

			case 3:
				opStr = "/";
				break;
		}

    return num1 + " " + opStr + " " + num2 + " =";
	}

	public int generateCaptchaAnswer(String problem){
		String[] eachPart = problem.split(" ");
		int num1 = Integer.parseInt(eachPart[0]);
		int num2 = Integer.parseInt(eachPart[2]);
		String operator = eachPart[1];

		// Compute the result based on the operator
		switch (operator) {
			case "+":
			 	result = num1 + num2;
				return num1 + num2;

			case "-":
				result = num1 - num2;
				return num1 - num2;

			case "*":
				result = num1 * num2;
				return num1 * num2;

			case "/":
				result = num1 / num2;
				return num1 / num2;

			default:
				throw new IllegalArgumentException("Invalid operator: " + operator);
		}
	}

	public Boolean compareAns(int userAns){
		if(userAns == result)
		return true;

		else{
			failedLogin++;
			return false;
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
