package io.mbab.sda.groupproject.entity;

import javax.persistence.*;
import java.util.List;

public class SongAutor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 64, nullable = false)
  private String firstName;

  @Column(length = 64, nullable = false)
  private String lastName;

  @OneToMany private List<Song> songs;
}
