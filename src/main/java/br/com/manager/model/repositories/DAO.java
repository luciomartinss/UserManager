package br.com.manager.model.repositories;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class DAO extends AbstractDAO {

    @Override
    @PersistenceContext(name = "jdbc/managerDS")
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
