package immo.portal.bean;

import java.io.Serializable;

public class LoginBean implements Serializable{
	private static final long serialVersionUID = 1L;
    private String loginname;
    private String passwort;
    
    
	public LoginBean() {

	}
    
    public LoginBean(String loginname, String passwort) {
    	super();
    	this.loginname = loginname;
    	this.passwort = passwort;
    }
    
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPasswort() {
		return passwort;
	}
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
    
    
    
}
