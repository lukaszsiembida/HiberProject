package io.mbab.sda.groupproject.menu.action;

import io.mbab.sda.groupproject.entity.Song;
import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.repository.SongRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class ViewSongsByLengthAction implements MenuAction {

  private final CustomScanner scanner;
  private final MenuActionContext ctx;
  private final SongRepository repository;

  @Override
  public void execute() {

    System.out.println("Podaj długość utworu:");
    Double songLength = scanner.nextDouble();
    List<Song> songPlaylist = repository.findBySongLength(songLength);

    if(songPlaylist.isEmpty()){
      System.out.println("Brak danych do wyświetlenia");
    } else {
      System.out.println(songPlaylist);
    }

    ctx.use(MainAction.class).execute();
  }
}
