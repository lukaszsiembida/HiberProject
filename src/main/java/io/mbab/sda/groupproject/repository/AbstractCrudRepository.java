package io.mbab.sda.groupproject.repository;

import lombok.RequiredArgsConstructor;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractCrudRepository<ENTITY, ID> implements CrudRepository<ENTITY, ID> {

  protected final EntityManager em;
  private final Class<ENTITY> entityClass;

  public List<ENTITY> getAll() {
    var criteriaBuilder = em.getCriteriaBuilder();
    var criteriaQuery = criteriaBuilder.createQuery(entityClass);
    var root = criteriaQuery.from(entityClass);

    return em.createQuery(criteriaQuery.select(root)).getResultList();
  }

  public Optional<ENTITY> findById(ID id) {
    try {
      var criteriaBuilder = em.getCriteriaBuilder();
      var criteriaQuery = criteriaBuilder.createQuery(entityClass);
      var root = criteriaQuery.from(entityClass);
      var select = criteriaQuery.select(root);
      var predicate = criteriaBuilder.equal(root.get("id"), id);

      var entity = em.createQuery(select.where(predicate)).getSingleResult();

      return Optional.of(entity);

    } catch (NoResultException e) {
      return Optional.empty();
    }
  }

  @Override
  public ENTITY create(ENTITY entity) {
    em.getTransaction().begin();
    em.persist(entity);
    em.getTransaction().commit();
    return entity;
  }

  public ENTITY update(ENTITY entity) {
    return em.merge(entity);
  }

  public void delete(ID id) {
    var criteriaBuilder = em.getCriteriaBuilder();
    var criteriaDelete = criteriaBuilder.createCriteriaDelete(entityClass);
    var root = criteriaDelete.from(entityClass);
    var predicate = criteriaBuilder.equal(root.get("id"), id);
    em.createQuery(criteriaDelete.where(predicate)).executeUpdate();
  }
}
