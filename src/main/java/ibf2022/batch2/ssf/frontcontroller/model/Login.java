package ibf2022.batch2.ssf.frontcontroller.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Login {
    
    @NotBlank(message = "Please enter your username")
    @Size(min=2, message="username must be at least 2 characters long")
    private String username;
    
    @NotBlank(message = "Please enter your password")
    @Size(min=2, message="password must be at least 2 characters long")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
