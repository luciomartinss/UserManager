package br.com.manager.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.manager.model.entities.User;
import br.com.manager.model.services.UserService;

@Named
@SessionScoped
public class UserController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private User user = new User();

	private UserService service = new UserService();

	public String saveUser() {

		this.service.saveAtomic(getUser());
		System.out.println("Usuario " + user.getNome() + " adicionado com sucesso!");
		user = new User();
		return null;
	}

	private List<User> users = new ArrayList<>(service.findAll());
	
	
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
