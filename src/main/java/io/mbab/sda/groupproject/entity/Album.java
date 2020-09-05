package io.mbab.sda.groupproject.entity;

import lombok.*;
import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Album {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Integer id;

  @Column(length = 64, nullable = false)
  private String albumTitle;

  @Column private Integer realaseYear;

  @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Song> songs;

  @Builder(toBuilder = true)
  public Album(Integer id, String albumTitle, Integer realaseYear, List<Song> songs) {
    this.id = id;
    this.albumTitle = albumTitle;
    this.realaseYear = realaseYear;
    this.songs = attachParentToSongs(songs);
  }

  private List<Song> attachParentToSongs(List<Song> songs) {
    return songs.stream()
        .map(song -> song.toBuilder().album(this).build())
        .collect(Collectors.toUnmodifiableList());
  }
}
