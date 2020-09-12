package io.mbab.sda.groupproject.repository;

import io.mbab.sda.groupproject.entity.Album;
import io.mbab.sda.groupproject.entity.Song;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

public class SongRepository extends AbstractCrudRepository<Song, Integer> {

  public SongRepository(EntityManager em) {
    super(em, Song.class);
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

  public List<Song> findByAlbumId(int albumId) {
    String sql = "SELECT * FROM song WHERE album_id = :id";
    return em.createNativeQuery(sql, Song.class).setParameter("id", albumId).getResultList();
  }
}
