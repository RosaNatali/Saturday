package ctr;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import persistence.Agent;
import persistence.User;
import services.BasicOpsLocal;

@ManagedBean
@SessionScoped
public class Identity {
	private Boolean isLogged = false;
	private User user = new User();
	private Boolean loggedInAsAgent = false;
	@EJB
	private BasicOpsLocal basicOpsLocal;

	public String logout() {
		isLogged = false;
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login?faces-redirect=true";
	}

	public String doLogin() {
		String navigateTo = "";
		User userLoggedIn = basicOpsLocal.login(user.getLogin(), user.getPassword());
		if (userLoggedIn != null) {
			isLogged = true;
			user = userLoggedIn;
			if (userLoggedIn instanceof Agent) {
				loggedInAsAgent = true;
				navigateTo = "/pages/adminHome?faces-redirect=true";

			} else {
				System.out.println("not yet implemented");
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Veuillez insérer un login et un mot de passe valide", ""));
			return "";

		}
		return navigateTo;

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getLoggedInAsAgent() {
		return loggedInAsAgent;
	}

	public void setLoggedInAsAgent(Boolean loggedInAsAgent) {
		this.loggedInAsAgent = loggedInAsAgent;
	}

	public Boolean getIsLogged() {
		return isLogged;
	}

	public void setIsLogged(Boolean isLogged) {
		this.isLogged = isLogged;
	}

}
