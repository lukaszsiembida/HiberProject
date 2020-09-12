package io.mbab.sda.groupproject.menu;

import io.mbab.sda.groupproject.menu.action.*;
import io.mbab.sda.groupproject.repository.AlbumRepository;
import io.mbab.sda.groupproject.repository.CrudRepositoryFactory;
import io.mbab.sda.groupproject.repository.SongRepository;

import java.util.HashMap;
import java.util.Map;

public class MenuActionContext {

  private MenuAction action;
  private Map<Class<? extends MenuAction>, MenuAction> holder = new HashMap<>();

  public MenuActionContext(CustomScanner scanner, CrudRepositoryFactory repositoryFactory) {
    initHolder(scanner, repositoryFactory);
  }

  public MenuActionContext use(Class<? extends MenuAction> actionClass) {
    action = holder.get(actionClass);
    return this;
  }

  public void execute() {
    if (action == null) throw new RuntimeException("Menu action not set");
    action.execute();
  }

  // MainAction zamieniam na AlbumAction
  private void initHolder(CustomScanner scanner, CrudRepositoryFactory repositoryFactory) {
    holder.put(MainAction.class, new MainAction(scanner, this, new MainActionStrategy()));
    holder.put(
        CreateAlbumAction.class,
        new CreateAlbumAction(scanner, this, repositoryFactory.get(AlbumRepository.class)));
    holder.put(
        ViewAlbumsAction.class,
        new ViewAlbumsAction(this, repositoryFactory.get(AlbumRepository.class)));
    holder.put(
        CreateSongAction.class,
        new CreateSongAction(
            scanner,
            this,
            repositoryFactory.get(SongRepository.class),
            repositoryFactory.get(AlbumRepository.class)));
    holder.put(
        ViewSongsAction.class,
        new ViewSongsAction(scanner, this, repositoryFactory.get(SongRepository.class)));
    holder.put(
        ViewAlbumsByTitleAction.class,
        new ViewAlbumsByTitleAction(scanner, this, repositoryFactory.get(AlbumRepository.class)));
    holder.put(
        ViewAlbumsByRealaseYearAction.class,
        new ViewAlbumsByRealaseYearAction(
            scanner, this, repositoryFactory.get(AlbumRepository.class)));
    holder.put(
        ViewSongsByTitleAction.class,
        new ViewSongsByTitleAction(scanner, this, repositoryFactory.get(SongRepository.class)));
    holder.put(
        ViewSongsByAuthorAction.class,
        new ViewSongsByAuthorAction(scanner, this, repositoryFactory.get(SongRepository.class)));
    holder.put(
        ViewSongsByLengthAction.class,
        new ViewSongsByLengthAction(scanner, this, repositoryFactory.get(SongRepository.class)));
  }
}
