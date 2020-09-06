package io.mbab.sda.groupproject.repository;

import io.mbab.sda.groupproject.entity.Song;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

public class SongRepository extends AbstractCrudRepository<Song, Integer> {

  public SongRepository(EntityManager em) {
    super(em, Song.class);
  }

  @Override
  public List<Song> getAll() {
    String jpql = "FROM Song";
    return em.createQuery(jpql, Song.class).getResultList();
  }

  @Override
  public Optional<Song> findById(Integer id) {
    String jpql = "FROM Song";
    try {
      var entity = em.createQuery(jpql, Song.class).setParameter("id", id).getSingleResult();
      return Optional.of(entity);
    } catch (NoResultException e) {
      return Optional.empty();
    }
  }

  public Optional<Song> findByTitle(String title) {
    try {
      var criteriaBuilder = em.getCriteriaBuilder();
      var criteriaQuery = criteriaBuilder.createQuery(Song.class);
      var root = criteriaQuery.from(Song.class);
      var predicate = criteriaBuilder.equal(root.get("title"), title);
      var entity = em.createQuery(criteriaQuery.select(root).where(predicate)).getSingleResult();
      return Optional.of(entity);
    } catch (NoResultException e) {
      return Optional.empty();
    }
  }

  public Optional<Song> findBySongLength(Double songLength) {
    try {
      var criteriaBuilder = em.getCriteriaBuilder();
      var criteriaQuery = criteriaBuilder.createQuery(Song.class);
      var root = criteriaQuery.from(Song.class);
      var predicate = criteriaBuilder.equal(root.get("songLength"), songLength);
      var entity = em.createQuery(criteriaQuery.select(root).where(predicate)).getSingleResult();
      return Optional.of(entity);
    } catch (NoResultException e) {
      return Optional.empty();
    }
  }

  public Optional<Song> findBySongAutor(String songAutor) {
    try {
      var criteriaBuilder = em.getCriteriaBuilder();
      var criteriaQuery = criteriaBuilder.createQuery(Song.class);
      var root = criteriaQuery.from(Song.class);
      var predicate = criteriaBuilder.equal(root.get("songAutor"), songAutor);
      var entity = em.createQuery(criteriaQuery.select(root).where(predicate)).getSingleResult();
      return Optional.of(entity);
    } catch (NoResultException e) {
      return Optional.empty();
    }
  }
}
