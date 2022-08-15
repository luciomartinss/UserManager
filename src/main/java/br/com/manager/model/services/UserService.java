package br.com.manager.model.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import br.com.manager.model.DaoException;
import br.com.manager.model.entities.User;
import br.com.manager.model.repositories.DAO;


@Stateless
@LocalBean
public class UserService {

	@EJB
	private DAO repository;

	public void save(User user) throws DaoException {
		this.repository.salvar(user);
	}

	public List<User> findAll() throws DaoException {
		return this.repository.listaPaginada("User.findAll", 0, 100);
	}
	
	
	public User findById(User user) throws DaoException {
		return (User) this.repository.buscarPorId(User.class, user.getId());
	}
	@Transactional(value = TxType.REQUIRES_NEW)
	public void delete(User user) throws DaoException {
		this.repository.excluir(user);
//		if (user.getId() != null) {
//			this.service.abrirT();
//			User findId = service.findByID(user.getId());
//			this.service.delete(findId);
//			this.service.fecharT();
//			System.out.println("Usuario " + findId.getId() + " - " + findId.getNome() + ", deletado com sucesso!");
//			user = new User();
//		} else {
//			System.out.println("Obrigatório informar Id do Usuario para deletar");
//		}
	}

	@Transactional(value = TxType.REQUIRES_NEW)
	public void update(User user) throws DaoException {
		this.repository.alterar(user);
//		if (user.getId() != null) {
//
//			this.repository.abrirT();
//			User findId = repository.findByID(user.getId());
//			this.repository.update(findId);
//
//			{ /* Verificação */
//				if (user.getNome() != "") {
//					findId.setNome(user.getNome());
//				}
//				if (user.getGenero() != "") {
//					findId.setGenero(user.getGenero());
//				}
//				if (user.getEmail() != "") {
//					findId.setEmail(user.getEmail());
//				}
//				if (user.getTelefone() != "") {
//					findId.setTelefone(user.getTelefone());
//				}
//				if (user.getCidade() != "") {
//					findId.setCidade(user.getCidade());
//				}
//				if (user.getUf() != "") {
//					findId.setUf(user.getUf());
//				}
//			}
//		this.service.fecharT();
//		System.out.println("Usuario: " + user.getId() + " - " + user.getNome() + ", alterado com sucesso!");
//		user = new User();
//	} else {
//		System.out.println("Obrigatório informar Id do Usuario para realizar alterarções");
//	}
//}

	}
}
