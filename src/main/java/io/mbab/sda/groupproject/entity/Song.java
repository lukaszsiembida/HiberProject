package io.mbab.sda.groupproject.entity;

import io.mbab.sda.groupproject.repository.SongRepository;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@ToString
@NoArgsConstructor
public class Song {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 64, nullable = false)
  private String title;

  @Column(length = 64, nullable = false)
  private String songAuthor;

  @Column(length = 64, nullable = false)
  private Double songLength;

  @ManyToOne
  private Album album;

  @Builder (toBuilder = true)
  public Song(Integer id, String title, String songAuthor, Double songLength, Album album){
    this.id=id;
    this.title=title;
    this.songAuthor = songAuthor;
    this.songLength = songLength;
    this.album = album;
  }

}
