package io.mbab.sda.groupproject.menu.action;

import io.mbab.sda.groupproject.entity.Album;
import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;

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

    if (pressedZero(input)) return;

    var album =  builder.realaseYear(input2).build();

    repository.create(album);
    ctx.use(MainAction.class).execute();
  }

  private boolean pressedZero(String input) {
    if (input.equals("0")) {
      ctx.use(MainAction.class).execute();
      return true;
    }
    return false;
  }
}
