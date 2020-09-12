package io.mbab.sda.groupproject.menu.action;

import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MainAction implements MenuAction {

  private final CustomScanner scanner;
  private final MenuActionContext ctx;

  @Override
  public void execute() {
    System.out.println("========= MENU ===========");
    System.out.println("0) Zamknij aplikację");
    System.out.println("1) Dodaj album");
    System.out.println("2) Wyswietl album");
    System.out.println("3) Dodaj piosenkę do albumu");
    System.out.println("4) Wyświetl piosenki dla danego albumu");
    System.out.println("5) Wyszukiwanie albumu po tytule");
    System.out.println("6) Wyszukiwanie albumu po roku wydania");
    System.out.println("7) Wyszukiwanie utworu po tytule");
    System.out.println("8) Wyszukiwanie utworu po autorze utworu");
    System.out.println("9) Wyszukiwanie utworu po dlugości utworu");

    var input = scanner.nextLine();

    if (input.equals("0")) {
      System.exit(0);
      return;
    }

    if (input.equals("1")) {
      ctx.use(CreateAlbumAction.class).execute();
      return;
    }

    if (input.equals("2")) {
      ctx.use(ViewAlbumsAction.class).execute();
      return;
    }

    if (input.equals("3")) {
      ctx.use(CreateSongAction.class).execute();
      return;
    }

    if (input.equals("4")) {
      ctx.use(ViewSongsAction.class).execute();
      return;
    }

    if (input.equals("5")) {
      ctx.use(ViewAlbumsByTitleAction.class).execute();
      return;
    }

    if (input.equals("6")) {
      ctx.use(ViewAlbumsByRealaseYearAction.class).execute();
      return;
    }
    if (input.equals("7")) {
      ctx.use(ViewSongsByTitleAction.class).execute();
      return;
    }
    if (input.equals("8")) {
      ctx.use(ViewSongsByAuthorAction.class).execute();
      return;
    }
    if (input.equals("9")) {
      ctx.use(ViewSongsByLengthAction.class).execute();
      return;
    }
    execute();
  }
}
