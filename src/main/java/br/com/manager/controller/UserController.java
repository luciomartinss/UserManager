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
	private User user;

	private UserService service = new UserService();

	private List<User> users = new ArrayList<>(service.findAll());

	public String saveUser() {

		this.service.saveAtomic(user);
		System.out.println("Usuario " + user.getNome() + " adicionado com sucesso!");
		user = new User();
		return null;
	}

	public void update() {
		if (user.getId() != null) {

			this.service.abrirT();
			User findId = service.findByID(user.getId());
			this.service.update(findId);

			{ /* Verificação */
				if (user.getNome() != "") {
					findId.setNome(user.getNome());
				}
				if (user.getGenero() != "") {
					findId.setGenero(user.getGenero());
				}
				if (user.getEmail() != "") {
					findId.setEmail(user.getEmail());
				}
				if (user.getTelefone() != "") {
					findId.setTelefone(user.getTelefone());
				}
				if (user.getCidade() != "") {
					findId.setCidade(user.getCidade());
				}
				if (user.getUf() != "") {
					findId.setUf(user.getUf());
				}
			}

			this.service.fecharT();
			System.out.println("Usuario: " + user.getId() + " - " + user.getNome() + ", alterado com sucesso!");
			user = new User();
		} else {
			System.out.println("Obrigatório informar Id do Usuario para realizar alterarções");
		}
	}

	public void delete() {
		if (user.getId() != null) {
			this.service.abrirT();
			User findId = service.findByID(user.getId());
			this.service.delete(findId);
			this.service.fecharT();
			System.out.println("Usuario " + findId.getId() + " - " + findId.getNome() + ", deletado com sucesso!");
			user = new User();
		} else {
			System.out.println("Obrigatório informar Id do Usuario para deletar");
		}
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
