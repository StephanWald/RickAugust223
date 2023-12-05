package samples;

import org.dwcj.App;
import org.dwcj.annotation.InlineStyleSheet;
import org.dwcj.component.button.Button;
import org.dwcj.component.button.event.ButtonClickEvent;
import org.dwcj.component.layout.applayout.AppLayout;
import org.dwcj.component.text.Label;
import org.dwcj.component.window.Frame;
import org.dwcj.component.window.Panel;
import org.dwcj.exceptions.DwcjException;
import org.dwcj.kitchen.navbar.Navbar;
import org.dwcj.kitchen.navbar.NavbarItem;
import org.dwcj.kitchen.navbar.event.NavbarSelectedEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@InlineStyleSheet("context://AppWithMenu.css")
public class AppWithMenu extends App {

  Label contentLabel;
  private Button getMenuDataBtn;
  private Navbar navbar;
  private Label itemsLabel;

  @Override
  public void run() throws DwcjException {
    Frame window = new Frame();
    AppLayout demo = new AppLayout();
    window.add(demo);
    // demo.setDrawerWidth("800px");

    // Header
    Panel header = new Panel();
    header.addClassName("layout__header");
        header.add(new Label("<html><bbj-icon-button name='menu-2' data-drawer-toggle><bbj-icon-button></html>"),
            new Label("DWCJ Application")
                .addClassName("layout__header--title"));
    demo.addToHeader(header);

    // Drawer
    Panel drawer = new Panel();
    drawer.addClassName("app-layout-drawer");

    // Drawer's logo container and logo
    drawer.add(new Panel().addClassName("drawer__logo").add(
        new Label("<html><img src='" + "https://i.ibb.co/1n4n1Nh/logo.png" + "'</img></html>")));


    // Content
    this.contentLabel = new Label();
    Panel content = new Panel();
    content.add(
        new Label("<html><h1>Application Title</h1></html>"),
        this.contentLabel);

    this.getMenuDataBtn = new Button("Get Menu Data");
    getMenuDataBtn.onClick(this::getMenuDta);

    this.itemsLabel = new Label();

    content.add(getMenuDataBtn,itemsLabel);
    content.setStyle("display","inline-grid");
    demo.addToContent(content);
    navbar = new Navbar();
    drawer.add(navbar);
    demo.addToDrawer(drawer);
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
        //.setShowNewTab(true)
        .setShowFavorites(true)
        .setExpanse(Navbar.Expanse.MEDIUM)
        //.setNavItemsTitle("Navigation")
        .setInputPlaceholder("Search");



    navbar.addSelectedEventListener(this::onSelectedMenuItem);

    navbar
        .setNavItems(navItems);

  }

  private void getMenuDta(ButtonClickEvent buttonClickEvent) {
    List<NavbarItem> items = navbar.getNavItems();
    String x = "<html>";

    x = getSubItems(items, x,"");

    itemsLabel.setText(x);
  }

  private static String getSubItems(List<NavbarItem> items, String x, String indent) {
    Iterator<NavbarItem> it = items.iterator();
    while (it.hasNext()){
      NavbarItem item = it.next();
      x +=indent+item.getId()+": "+item.getCaption()+" "+item.getFavorite()+"<br>";
    }
    return x;
  }

  private void onSelectedMenuItem(NavbarSelectedEvent navbarSelectedEvent) {
    Map x = (Map) navbarSelectedEvent.getEventMap().get("detail");
    contentLabel.setText("ID Selected: "+x.get("id").toString());
    itemsLabel.setText(x.toString());
  }


}
