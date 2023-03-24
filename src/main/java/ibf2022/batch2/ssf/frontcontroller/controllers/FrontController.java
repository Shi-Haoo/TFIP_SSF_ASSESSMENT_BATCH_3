package ibf2022.batch2.ssf.frontcontroller.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ibf2022.batch2.ssf.frontcontroller.model.Login;
import jakarta.validation.Valid;

@Controller
@RequestMapping
public class FrontController {

	// TODO: Task 2, Task 3, Task 4, Task 6

	@GetMapping(path="/")
	public String getLoginPg(Model model){

		model.addAttribute("login", new Login());
		return "view0";
	}

	@PostMapping(path="/login")
	public String postLogin(Model model, @Valid Login login, BindingResult binding){
		
		if(binding.hasErrors()){
			return "view0";
		}

		return "view1";
	}
	
}
