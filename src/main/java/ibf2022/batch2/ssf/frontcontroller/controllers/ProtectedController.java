package ibf2022.batch2.ssf.frontcontroller.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ibf2022.batch2.ssf.frontcontroller.model.Login;

@Controller
@RequestMapping(path="/protected")
public class ProtectedController {

	// TODO Task 5
	// Write a controller to protect resources rooted under /protected
@GetMapping
public String access(){
	
	String authenticatedUser = (String) req.getSession().getAttribute("authenticatedUser");

	if(Login.getUsername().equals(authenticatedUser))
}
	

}
