package br.com.manager.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.manager.model.DaoException;
import br.com.manager.model.entities.User;
import br.com.manager.model.services.UserService;

@Named
@SessionScoped
public class UserController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private User user;

	@EJB
	private UserService service;

	private List<User> users;

	public UserController() {
		this.findAll();
	}

	public void findAll() {
		try {
			this.users = service.findAll();
		} catch (DaoException e) {
			addMessage("Falha ao listar", FacesMessage.SEVERITY_WARN);
		}
	}

	public void saveUser() {
		try {
			this.service.save(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			addMessage("Falha ao salvar: "+ e.getMessage(), FacesMessage.SEVERITY_WARN);
		}
	}

	public void update() {
		try {
			this.service.update(user);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			addMessage("Falha ao listar", FacesMessage.SEVERITY_WARN);
		}
	}

	public void delete() {
		try {
			this.service.delete(user);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			addMessage("Falha ao listar", FacesMessage.SEVERITY_WARN);
		}
	}
	
	private void addMessage(String msg , FacesMessage.Severity severity) {
		FacesContext.getCurrentInstance()
		.addMessage("Mensagem",  
				new FacesMessage(severity, msg, "Mensagem do Sistema"));
	}
	

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
