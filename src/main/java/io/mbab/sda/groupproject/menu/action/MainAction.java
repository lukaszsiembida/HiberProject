package io.mbab.sda.groupproject.menu.action;

import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MainAction implements MenuAction {
  private final CustomScanner scanner;
  private final MenuActionContext ctx;
  private final MainActionStrategy strategy;

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
    strategy
        .prepareCtx(input, ctx)
        .ifPresentOrElse(
            MenuActionContext::execute,
            () -> {
              System.out.println("Wprowadzono nieprawidłowa wartość!");
              execute();
            });
  }
}
