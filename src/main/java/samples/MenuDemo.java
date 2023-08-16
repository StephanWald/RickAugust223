package samples;

import org.dwcj.App;
import org.dwcj.annotation.InlineStyleSheet;
import org.dwcj.component.window.Frame;
import org.dwcj.exceptions.DwcjException;
import org.dwcj.kitchen.navbar.Navbar;
import org.dwcj.kitchen.navbar.NavbarItem;
import org.dwcj.kitchen.navbar.event.NavbarSelectedEvent;

import java.util.ArrayList;
import java.util.Map;


@InlineStyleSheet(/* css */"""
bbj-app-layout {
    --bbj-app-layout-drawer-width: 340px;
}
""")
public class MenuDemo extends App {
Frame frame;
Navbar navbar;

@Override
public void run() throws DwcjException {


    frame = new Frame();
    navbar = new Navbar();
    frame.add(navbar);

    ArrayList<NavbarItem> windowChildren = new ArrayList();
    windowChildren.add(new NavbarItem("4", "tabler:alien", "alien", "/window/alien", false, null));
    windowChildren.add(new NavbarItem("3", "tabler:album", "album", "/window/album", true, null));

    ArrayList<NavbarItem> apiChildren = new ArrayList();
    apiChildren.add(new NavbarItem("22", "tabler:biohazard", "biohazard", "/bookmark/api-app/biohazard", true, null));
    apiChildren.add(new NavbarItem("23", "tabler:bluetooth", "bluetooth", "/bookmark/api-app/bluetooth", true, null));
    apiChildren.add(new NavbarItem("24", "tabler:box", "box", "/bookmark/api-app/box", false, null));

    ArrayList<NavbarItem> bookmarkChildren = new ArrayList();
    bookmarkChildren.add(new NavbarItem("9", "tabler:brand-facebook", "facebook", "/bookmark/brand-facebook", true, null));
    bookmarkChildren.add(new NavbarItem("11", "tabler:api-app", "api app", "/bookmark/api-app", false, apiChildren));
    bookmarkChildren.add(new NavbarItem("10", "tabler:beach", "beach", "/bookmark/beach", true, null));

    ArrayList<NavbarItem> navItems = new ArrayList();
    navItems.add(new NavbarItem("1", "tabler:home", "home", "/", false, null));
    navItems.add(new NavbarItem("2", "tabler:window", "window", "/window", false, windowChildren));
    navItems.add(new NavbarItem("5", "tabler:ad", "Ad", "/ad", true, null));
    navItems.add(new NavbarItem("6", "tabler:angle", "Angle", "/angle", true, null));
    navItems.add(new NavbarItem("7", "tabler:bookmark", "bookmark", "/bookmark", false, bookmarkChildren));
    navItems.add(new NavbarItem("8", "tabler:brand-flickr", "flickr", "/brand-flickr", false, null));

    navbar
        .setShowNewTab(true)
        .setShowFavorites(true)
        .setExpanse(Navbar.Expanse.MEDIUM)
        .setNavItemsTitle("Navigation")
        .setInputPlaceholder("Search");

    navbar.addSelectedEventListener(this::onSelectedMenuItem);

    navbar
        .setNavItems(navItems);


}

private void onSelectedMenuItem(NavbarSelectedEvent navbarSelectedEvent) {
    Map x = (Map) navbarSelectedEvent.getEventMap().get("detail");
    App.consoleLog("ID Selected: "+x.get("id").toString());
}
}
