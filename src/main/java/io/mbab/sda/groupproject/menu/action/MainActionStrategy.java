package io.mbab.sda.groupproject.menu.action;

import io.mbab.sda.groupproject.menu.MenuActionContext;
import java.util.Map;
import java.util.Optional;

public class MainActionStrategy {
  private final Map<String, Class<? extends MenuAction>> map;

  public MainActionStrategy() {
    this.map = initStrategyMap();
  }

  public Optional<MenuActionContext> prepareCtx(String input, MenuActionContext ctx) {
    if (map.containsKey(input)) {
      return Optional.of(ctx.use(map.get(input)));
    } else {
      return Optional.empty();
    }
  }

  private Map<String, Class<? extends MenuAction>> initStrategyMap() {
    return Map.ofEntries(
        Map.entry("0", ExitAction.class),
        Map.entry("1", CreateAlbumAction.class),
        Map.entry("2", ViewAlbumsAction.class),
        Map.entry("3", CreateSongAction.class),
        Map.entry("4", ViewSongsAction.class),
        Map.entry("5", ViewAlbumsByTitleAction.class),
        Map.entry("6", ViewAlbumsByRealaseYearAction.class),
        Map.entry("7", ViewSongsByTitleAction.class),
        Map.entry("8", ViewSongsByAuthorAction.class),
        Map.entry("9", ViewSongsByLengthAction.class));
  }
}
