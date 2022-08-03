package br.com.manager.controller;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.manager.model.entities.User;
import br.com.manager.model.services.UserService;

public class UserController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private User user = new User();
	
		
	private UserService service = new UserService();

	public void saveUsuario() {
		
		this.service.saveAtomic(user);
		System.out.println("Usuario " + user.getNome() + " adicionado com sucesso!");
		user = new User();
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
		
}
