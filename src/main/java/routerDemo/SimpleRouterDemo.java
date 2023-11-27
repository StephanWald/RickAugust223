package routerDemo;

import org.dwcj.App;
import org.dwcj.annotation.InlineStyleSheet;
import org.dwcj.component.layout.applayout.AppLayout;
import org.dwcj.component.tabbedpane.TabbedPane;
import org.dwcj.component.tabbedpane.event.TabSelectEvent;
import org.dwcj.component.text.Label;
import org.dwcj.component.window.Frame;
import org.dwcj.component.window.Panel;
import org.dwcj.exceptions.DwcjException;
import org.dwcj.kitchen.simplerouter.SimpleRouter;
import org.dwcj.kitchen.simplerouter.event.SimpleRouteMatchEvent;

import java.util.HashMap;
import java.util.Map;

@InlineStyleSheet("context://applayout.css")
public class SimpleRouterDemo extends App {

  Label contentLabel;

  private SimpleRouter router = SimpleRouter.getInstance();


  private Map<String, Class> menuPanels = new HashMap<>();

  private Map<String,Integer> menuItemsOrder = new HashMap<>();
  private Integer currentTab = 0;

  private Map<String,Panel> contentPanels = new HashMap<>();

  private Panel currentPanel = null;
  private AppLayout layout;
  private TabbedPane drawerMenu;


  @Override
  public void run() throws DwcjException {
    Frame window = new Frame();
    layout = new AppLayout();
    window.add(layout);

    // Header
    layout.getHeader().addClassName("layout__header")
        .add(new Label("<html><bbj-icon-button name='menu-2' data-drawer-toggle><bbj-icon-button></html>"),
            new Label("DWCJ Application")
                .addClassName("layout__header--title"));

    // Drawer
    Panel drawer = layout.getDrawer();
    drawer.addClassName("app-layout-drawer");

    // Drawer's logo container and logo
    drawer.add(new Panel().addClassName("drawer__logo").add(
        new Label("<html><img src='" + "https://i.ibb.co/1n4n1Nh/logo.png" + "'</img></html>")));

    // Drawer's Menu
    drawerMenu = new TabbedPane();
    drawer.add(drawerMenu);

    // Setting drawer menu's attributes
    drawerMenu.setAttribute("nobody", "true");
    drawerMenu.setAttribute("borderless", "true");
    drawerMenu.setAttribute("placement", "left");

    // Adding tabs to drawer menu
    drawerMenu.add("<bbj-icon name='dashboard'></bbj-icon>      Dashboard")
        .add("<bbj-icon name='shopping-cart'></bbj-icon>  Orders")
        .add("<bbj-icon name='users'></bbj-icon>          Customers")
        .add("<bbj-icon name='box'></bbj-icon>            Products")
        .add("<bbj-icon name='files'></bbj-icon>          Documents");

    menuItemsOrder.put("dashboard/*",0);
    menuItemsOrder.put("orders/*",1);
    menuItemsOrder.put("customers/*",2);
    menuItemsOrder.put("products/*",3);
    menuItemsOrder.put("documents/*",4);

    menuPanels.put("dashboard/*",routerDemo.DashboardPanel.class);
    menuPanels.put("orders/*",routerDemo.OrdersPanel.class);
    menuPanels.put("customers/*",routerDemo.CustomersPanel.class);
    menuPanels.put("products/*",routerDemo.ProductsPanel.class);
    menuPanels.put("documents/*",routerDemo.DocumentsPanel.class);


    router.onRouteMatch(this::onRouteMatch,
        "dashboard/*",
        "orders/*",
        "customers/*",
        "products/*",
        "documents/*");


    router.navigate();

    drawerMenu.onSelect(this::onTabChange);
  }


  private void onRouteMatch(SimpleRouteMatchEvent ev) {
    String matchingRoute = ev.getRoute().getRoute();

    Panel p = contentPanels.get(matchingRoute);
    if (p == null) {
      Class c = menuPanels.get(matchingRoute);
      try {
        p = (Panel) c.newInstance();
      } catch (InstantiationException e) {
        throw new RuntimeException(e);
      } catch (IllegalAccessException e) {
        throw new RuntimeException(e);
      }
      if (p != null){
        contentPanels.put(matchingRoute,p);
        layout.getContent().add(p);

      }
    }
    if (p != null && !p.equals(currentPanel)) {
      if (currentPanel != null) {
        currentPanel.setVisible(false);
      }
      p.setVisible(true);
      currentPanel = p;

      Integer menuitem = menuItemsOrder.get(matchingRoute);
      if (menuitem != null && menuitem != currentTab) {
        currentTab = menuitem;
        drawerMenu.selectIndex(menuitem);

      }

    }
  }

  private void onTabChange(TabSelectEvent ev) {
    String value = ev.getTitle().replaceAll("<[^>]*>", "").trim();
    if (router != null && currentTab != ev.getIndex())
      router.navigate(value.toLowerCase());
  }
}
