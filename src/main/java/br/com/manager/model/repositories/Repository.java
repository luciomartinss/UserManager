package br.com.manager.model.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Repository<E> {

	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager");
	private EntityManager em; 
	private Class<E> classe;
	
	public Repository() {
		this(null);
	}

	public Repository(Class<E> classe) {
		this.classe = classe;
	em = emf.createEntityManager();
	}
	
	public Repository<E> abrirT(){
		em.getTransaction().begin();
		return this;
	}
	
	public Repository<E> fecharT(){
		em.getTransaction().commit();
		return this;
	}
	
	public Repository<E> save(E entity){
		em.persist(entity);
		return this;
	}
	
	public Repository<E> saveAtomic(E entity){
		
		return this.abrirT().save(entity).fecharT();
	}
	
	public E findByID(Object id) {
		return em.find(classe, id);
	}

	public List<E> findAll(){
		return this.findAll(10, 0);
	}

	public List<E> findAll(int qtde, int deslocamento){
		if(classe == null) {
			throw new UnsupportedOperationException("Classe nula.");
		}
		
		String jpql = "select e from " + classe.getName() + " e";
		TypedQuery<E> query = em.createQuery(jpql, classe);
		query.setMaxResults(qtde);
		query.setFirstResult(deslocamento);
		return query.getResultList();
	}
	
	public List<E> consultar(String nomeConsulta, Object... params){
		TypedQuery<E> query = em.createNamedQuery(nomeConsulta, classe);
		
		for (int i = 0; i < params.length; i += 2) {
			query.setParameter(params[i].toString(), params[i + 1]);
		}
		
		
		return query.getResultList();
	}

	public E consultarUm(String nomeConsulta, Object... params){
		List<E> lista = consultar(nomeConsulta, params);
		return lista.isEmpty() ? null : lista.get(0);
 	}
	
	
	public void fechar() {
		em.close();
	}
	
	
	
	
}


