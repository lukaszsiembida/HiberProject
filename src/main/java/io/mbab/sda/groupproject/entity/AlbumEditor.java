package io.mbab.sda.groupproject.entity;

import java.util.List;
import java.util.Optional;

public interface AlbumEditor <Song> {

    Song add (Song song);
    List<Album> getAll();
}
