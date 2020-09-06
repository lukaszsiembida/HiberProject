package io.mbab.sda.groupproject.menu.action;

import io.mbab.sda.groupproject.entity.Album;
import io.mbab.sda.groupproject.entity.Song;
import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.repository.AlbumRepository;
import io.mbab.sda.groupproject.repository.SongRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateSongAction implements MenuAction {

  private final CustomScanner scanner;
  private final MenuActionContext ctx;
  private final SongRepository repository;
  private final AlbumRepository albumRepository;

  @Override
  public void execute() {
    System.out.println("0) Przejdź do poprzedniego menu");

    System.out.println("Podaj id albumu do którego dodajemy utwór: ");
    var input1 = scanner.nextInt();
    if (pressedZero(input1)) return;
    var optAlbum = albumRepository.findById(input1);
    Album album;
    if (optAlbum.isPresent()) {
      album = optAlbum.get();
    } else {
      return;
    }

    System.out.println("Podaj tytuł utworu:");
    var input = scanner.nextLine();
    if (pressedZero(input)) return;
    var builder = Song.builder().title(input);

    System.out.println("Podaj nazwisko autora:");
    input = scanner.nextLine();
    if (pressedZero(input)) return;
    builder = builder.songAutor(input);

    System.out.println("Podaj długość utworu [min]:");
    var input2 = scanner.nextDouble();
    if (pressedZero(input2)) return;
    builder = builder.songLength(input2);

    var song = builder.album(album).build();

    repository.create(song);
    ctx.use(MainAction.class).execute();
  }

  private boolean pressedZero(String input) {
    if (input.equals("0")) {
      ctx.use(MainAction.class).execute();
      return true;
    }
    return false;
  }

  private boolean pressedZero(Double input) {
    if (input.equals(0)) {
      ctx.use(MainAction.class).execute();
      return true;
    }
    return false;
  }

  private boolean pressedZero(Integer input) {
    if (input.equals(0)) {
      ctx.use(MainAction.class).execute();
      return true;
    }
    return false;
  }
}
