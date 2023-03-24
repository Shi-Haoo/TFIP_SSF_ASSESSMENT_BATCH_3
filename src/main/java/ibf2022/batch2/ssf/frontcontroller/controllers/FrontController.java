package ibf2022.batch2.ssf.frontcontroller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ibf2022.batch2.ssf.frontcontroller.model.Login;
import ibf2022.batch2.ssf.frontcontroller.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping
public class FrontController {

	// TODO: Task 2, Task 3, Task 4, Task 6

	@Autowired
	AuthenticationService aSvc;

	@Autowired
    private HttpServletRequest req;

	@GetMapping(path="/")
	public String getLoginPg(Model model){

		model.addAttribute("login", new Login());
		return "view0";
	}

	@PostMapping(path="/login")
	public String postLogin(Model model, @RequestBody MultiValueMap<String,String> userAns, @Valid Login login, BindingResult binding) throws Exception{
		
		if(binding.hasErrors()){
			return "view0";
		}

		String ans = userAns.getFirst("userAns");
		String qn = aSvc.generateMathQn();
		int failedLogin = aSvc.failedLogin;
		if(failedLogin > 0 && failedLogin < 3){
			aSvc.generateCaptchaAnswer(qn);
			if(!aSvc.compareAns(Integer.parseInt(ans))){
				model.addAttribute("captcha", qn);
				model.addAttribute("errorMessage", "incorrect captcha answer!");
				return "view0";
			}
			model.addAttribute("failedLogin", failedLogin);
		}

		if(failedLogin == 3){
			aSvc.disableUser(login.getUsername());
		}

		try{
			aSvc.authenticate(login.getUsername(), login.getPassword());
		}catch(Exception ex){
			
			model.addAttribute("captcha", qn);
			model.addAttribute("errorMsg", "invalid username/password"); //ex.getMessage());
			return "view0";
		}
		
		String authenticatedUser = (String) req.getSession().getAttribute("authenticatedUser");

		if(login.getUsername().equals(authenticatedUser))
		return "redirect: /protected/view1.html";
		
		
		return "view0";
	}
	
}
