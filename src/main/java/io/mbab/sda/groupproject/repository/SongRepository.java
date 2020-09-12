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

  public List<Song> findByTitle(String songTitle) {
    var criteriaBuilder = em.getCriteriaBuilder();
    var criteriaQuery = criteriaBuilder.createQuery(Song.class);
    var root = criteriaQuery.from(Song.class);
    var predicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), songTitle.toLowerCase());
    return em.createQuery(criteriaQuery.select(root).where(predicate)).getResultList();
  }

  public List<Song> findBySongLength(Double songLength) {
      var criteriaBuilder = em.getCriteriaBuilder();
      var criteriaQuery = criteriaBuilder.createQuery(Song.class);
      var root = criteriaQuery.from(Song.class);
      var predicate = criteriaBuilder.equal(root.get("songLength"), songLength);
      return em.createQuery(criteriaQuery.select(root).where(predicate)).getResultList();
  }

  public List<Song> findBySongAuthor(String author) { var criteriaBuilder = em.getCriteriaBuilder();
    var criteriaQuery = criteriaBuilder.createQuery(Song.class);
    var root = criteriaQuery.from(Song.class);
    var predicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("songAuthor")), author.toLowerCase());
    return em.createQuery(criteriaQuery.select(root).where(predicate)).getResultList();
  }

  public List<Song> findByAlbumId(int albumId) {
    String sql = "SELECT * FROM song WHERE album_id = :id";
    return em.createNativeQuery(sql, Song.class).setParameter("id", albumId).getResultList();
  }
}
