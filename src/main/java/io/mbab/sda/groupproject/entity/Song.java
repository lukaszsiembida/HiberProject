package io.mbab.sda.groupproject.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Song {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 64, nullable = false)
  private String title;

  @ManyToOne
  @Column(length = 64, nullable = false)
  private SongAutor songAutor;

  @Column(length = 64, nullable = false)
  private Double songLength;

  @Column(length = 64, nullable = false)
  private Integer songView;



}
