package sk.jaroslavbeno.crud.impl;

import sk.jaroslavbeno.crud.CrudRepository;
import sk.jaroslavbeno.model.Adresa;

import javax.persistence.EntityManager;

public class AdresaRepository implements CrudRepository<Adresa> {

    private EntityManager entityManager;

    public AdresaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(Adresa entita) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entita);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            try {
                entityManager.getTransaction().rollback();
            }catch (Exception e1){
                //nepodarilo sa rollback
            }
        }
    }

    @Override
    public Adresa read(Class<Adresa> entityClass, Object id) {
        entityManager.getTransaction().begin();
        Adresa adresa = entityManager.find(entityClass,id);
        entityManager.getTransaction().commit();
        return adresa;
    }

    @Override
    public Adresa update(Adresa entita) {
        entityManager.getTransaction().begin();
        Adresa adresa = entityManager.merge(entita);
        entityManager.getTransaction().commit();
        return adresa;
    }

    @Override
    public void delete(Adresa entita) {
        entityManager.getTransaction().begin();
        entityManager.remove(entita);
        entityManager.getTransaction().commit();
    }


}
