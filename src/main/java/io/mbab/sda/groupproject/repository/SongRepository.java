package io.mbab.sda.groupproject.repository;

import io.mbab.sda.groupproject.entity.Song;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
public class SongRepository implements CrudRepository2<Song, Integer> {

  private final EntityManager x;

  @Override
  public List<Song> getAll() {
    return x.createQuery("FROM Song", Song.class).getResultList();
  }

  @Override
  public Song findById(Integer id) {
    return null;
  }

  @Override
  public Song findByTitle(String title) {
    return null;
  }

  public Song findBySongLength(Double songLength) {
    return null;
  }

  public Song findBySongAutor(String songAutor) {
    return null;
  }

  @Override
  public Song create(Song entity) {
    x.getTransaction().begin();
    x.persist(entity);
    x.getTransaction().commit();
    return entity;
  }

  @Override
  public Song update(Song entity) {
    return null;
  }

  @Override
  public Song save(Song entity) {
    return null;
  }

  @Override
  public void delete(Integer o) {}
}
