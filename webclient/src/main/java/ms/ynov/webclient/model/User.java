package ms.ynov.webclient.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class User {
    
    private Integer id;

    @NotEmpty
    @NotNull
    private String username;

    @NotEmpty
    @NotNull
    private String mail;

    @NotEmpty
    @NotNull
    private String password;
    
    @NotEmpty
    @NotNull
    private String verifyPassword;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerifyPassword() {
		return verifyPassword;
	}

	public void setVerifyPassword(String verifyPassword) {
		this.verifyPassword = verifyPassword;
	}
    
    
}
