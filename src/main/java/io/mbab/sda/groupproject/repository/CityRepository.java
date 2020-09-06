package io.mbab.sda.groupproject.repository;

import io.mbab.sda.groupproject.entity.City;
import io.mbab.sda.groupproject.entity.Song;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CityRepository implements CrudRepository<City, Integer> {

  private final EntityManager em;

  @Override
  public List<City> getAll() {
    return em.createQuery("FROM City", City.class)
            .getResultList();
  }

  @Override
  public Optional<City> findById(Integer id) {
    return null;
  }

  @Override
  public City create(City entity) {
    em.getTransaction().begin();
    em.persist(entity);
    em.getTransaction().commit();
    return entity;
  }

  @Override
  public City update(City entity) {
    return null;
  }

  @Override
  public void delete(Integer o) {}
}
