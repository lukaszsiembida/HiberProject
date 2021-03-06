package io.mbab.sda.groupproject.menu.action;

import io.mbab.sda.groupproject.entity.Album;
import io.mbab.sda.groupproject.entity.Song;
import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CreateAlbumAction implements MenuAction {

  private final CustomScanner scanner;
  private final MenuActionContext ctx;
  private final AlbumRepository repository;

  @Override
  public void execute() {
    System.out.println("0) Przejdź do poprzedniego menu");

    System.out.println("Podaj nazwę albumu:");
    var input = scanner.nextLine();
    if (pressedZero(input)) return;
    var builder = Album.builder().albumTitle(input);

    System.out.println("Podaj rok wydania albumu:");
    var input2 = scanner.nextInt();
    if (pressedZero(input2)) return;

    List<Song> songList = new ArrayList<Song>();
    var album =  builder.realaseYear(input2).songs(songList).build();

    repository.create(album);
    ctx.use(MainAction.class).execute();
    System.out.println("Pusty album został dodany");
  }

  private boolean pressedZero(String input) {
    if (input.equals("0")) {
      ctx.use(MainAction.class).execute();
      return true;
    }
    return false;
  }

  private boolean pressedZero(Integer input) {
    if (input.equals("0")) {
      ctx.use(MainAction.class).execute();
      return true;
    }
    return false;
  }

}
