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

  public List<Album> findByTitle(String tittle) {
      var criteriaBuilder = em.getCriteriaBuilder();
      var criteriaQuery = criteriaBuilder.createQuery(Album.class);
      var root = criteriaQuery.from(Album.class);
      var predicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("albumTitle")), tittle.toLowerCase());
      return em.createQuery(criteriaQuery.select(root).where(predicate)).getResultList();
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
