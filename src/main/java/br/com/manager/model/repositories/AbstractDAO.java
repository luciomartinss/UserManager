package br.com.manager.model.repositories;

import br.com.manager.model.DaoException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;


public abstract class AbstractDAO<T> {

    protected EntityManager entityManager;

    public abstract void setEntityManager(EntityManager entityManager);

    public EntityManager getEntityManager() {
        return entityManager;
    }

    
    public void salvar(T o) throws DaoException {
        try {
            entityManager.persist(o);
      
        } catch (PersistenceException e) {
            throw new PersistenceException(e);
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }

    }

    
    public T alterar(T o) throws DaoException {
        try {
            return entityManager.merge(o);
        } catch (PersistenceException e) {
            throw new PersistenceException(e);
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
    }

    
    public void excluir(T o) throws DaoException {
        try {
            entityManager.remove(entityManager.merge(o));
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
    }

    
    public T buscarPorId(Class<T> clazz, Integer id) throws DaoException {
        try {
            return entityManager.find(clazz, id);
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
    }

    
    public T buscaEntidade(String comando, Object... params) throws DaoException {
        try {
            Query query = entityManager.createNamedQuery(comando);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    query.setParameter("p" + i, params[i]);
                }
            }
            return (T) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    
    public List<T> listaEntidades(String comando, Object... params) throws DaoException {
        Query query = entityManager.createNamedQuery(comando);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter("p" + i, params[i]);
            }
        }
        return query.getResultList();
    }

    
    public List<T> listaPaginada(String comando, int inicio, int fim, Object... params) throws DaoException {
        Query query = entityManager.createNamedQuery(comando);
        query.setFirstResult(inicio);
        query.setMaxResults(fim);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter("p" + i, params[i]);
            }
        }
        return query.getResultList();
    }

}

