package br.com.manager.model.services;

import br.com.manager.model.entities.User;
import br.com.manager.model.repositories.Repository;

public class UserService extends Repository<User>  {

	public UserService() {
		super (User.class);
	}
}
