package io.mbab.sda.groupproject.menu.action;

import io.mbab.sda.groupproject.entity.Album;
import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ViewAlbumsByRealaseYearAction implements MenuAction {

    private final CustomScanner scanner;
    private final MenuActionContext ctx;
    private final AlbumRepository repository;

    @Override
    public void execute() {

        System.out.println("Podaj rok wydania albumu:");
        Integer relesaeOfYear = scanner.nextInt();
        List<Album> albumPlaylist = repository.findByReleaseOfYear(relesaeOfYear);

        if (albumPlaylist.isEmpty()) {
            System.out.println("Brak danych do wyświetlenia");
        } else {
            System.out.println(albumPlaylist);
        }

        ctx.use(MainAction.class).execute();
    }
}