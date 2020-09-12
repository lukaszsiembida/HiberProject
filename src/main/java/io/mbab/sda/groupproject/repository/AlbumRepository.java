package io.mbab.sda.groupproject.repository;

import io.mbab.sda.groupproject.entity.Album;
import javax.persistence.EntityManager;
import java.util.List;

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

    public List<Album> findByReleaseOfYear(Integer relesaeOfYear) {
        var criteriaBuilder = em.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Album.class);
        var root = criteriaQuery.from(Album.class);
        var predicate = criteriaBuilder.equal(root.get("realaseYear"), relesaeOfYear);

        return em.createQuery(criteriaQuery.select(root).where(predicate)).getResultList();
    }

}
