package io.mbab.sda.groupproject.repository;

import io.mbab.sda.groupproject.entity.Album;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

public class AlbumRepository extends AbstractCrudRepository<Album, Integer> {

  public AlbumRepository(EntityManager em) {
    super(em, Album.class);
  }

  @Override
  public List<Album> getAll() {
    String jpql = "FROM Album";
    return em.createQuery(jpql, Album.class).getResultList();
  }

  @Override
  public Optional<Album> findById(Integer id) {
    String jpql = "FROM Album";
    try {
      var entity = em.createQuery(jpql, Album.class).setParameter("id", id).getSingleResult();
      return Optional.of(entity);
    } catch (NoResultException e) {
      return Optional.empty();
    }
  }

  public Optional<Album> findByTitle(String albumTittle) {
    try {
      var criteriaBuilder = em.getCriteriaBuilder();
      var criteriaQuery = criteriaBuilder.createQuery(Album.class);
      var root = criteriaQuery.from(Album.class);
      var predicate = criteriaBuilder.equal(root.get("title"), albumTittle);
      var entity = em.createQuery(criteriaQuery.select(root).where(predicate)).getSingleResult();
      return Optional.of(entity);
    } catch (NoResultException e) {
      return Optional.empty();
    }
  }

  public Optional<Album> findByReleaseOfYear(Integer relesaeOfYear) {
    try {
      var criteriaBuilder = em.getCriteriaBuilder();
      var criteriaQuery = criteriaBuilder.createQuery(Album.class);
      var root = criteriaQuery.from(Album.class);
      var predicate = criteriaBuilder.equal(root.get("releaseOfYear"), relesaeOfYear);
      var entity = em.createQuery(criteriaQuery.select(root).where(predicate)).getSingleResult();
      return Optional.of(entity);
    } catch (NoResultException e) {
      return Optional.empty();
    }
  }
}
