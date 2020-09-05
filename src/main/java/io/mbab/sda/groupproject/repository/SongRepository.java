package io.mbab.sda.groupproject.repository;

import io.mbab.sda.groupproject.entity.Song;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class SongRepository implements CrudRepository<Song, Integer> {

  private final EntityManager em;

  @Override
  public List<Song> getAll() {
    return em.createQuery("FROM Song", Song.class).getResultList();
  }

  //  @Override
  //  public Song findById(Integer id) {
  //    return null;
  //  }

  @Override
  public Optional<Song> findById(Integer id) {

    try {
      var criteriaBuilder = em.getCriteriaBuilder();
      var criteriaQuery = criteriaBuilder.createQuery(Song.class);
      var root = criteriaQuery.from(Song.class);
      var predicate = criteriaBuilder.equal(root.get("id"), id);
      var entity = em.createQuery(criteriaQuery.select(root).where(predicate)).getSingleResult();
      return Optional.of(entity);
    } catch (NoResultException e) {
      return Optional.empty();
    }
  }

  @Override
  public Song create(Song entity) {
    em.getTransaction().begin();
    em.persist(entity);
    em.getTransaction().commit();
    return entity;
  }

  @Override
  public Song update(Song entity) {
    return em.merge(entity);
  }

  @Override
  public void delete(Integer id) {
    var criteriaBuilder = em.getCriteriaBuilder();
    var criteriaDelete = criteriaBuilder.createCriteriaDelete(Song.class);
    var root = criteriaDelete.from(Song.class);
    var predicate = criteriaBuilder.equal(root.get("id"), id);
    em.createQuery(criteriaDelete.where(predicate)).executeUpdate();
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
