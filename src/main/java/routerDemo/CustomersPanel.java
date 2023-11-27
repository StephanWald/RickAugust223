package routerDemo;

import org.dwcj.App;
import org.dwcj.component.text.Label;
import org.dwcj.component.window.Panel;
import org.dwcj.component.window.Window;
import org.dwcj.kitchen.simplerouter.Route;
import org.dwcj.kitchen.simplerouter.SimpleRouter;

public class CustomersPanel extends Panel {

  private SimpleRouter router = SimpleRouter.getInstance();

  private Route showRoute = new Route("customers/:custnum");

  private Label lastRouteDisplay;
  private Label customerDisplay;

  @Override
  protected void onCreate(Window p) {
    super.onCreate(p);
    setStyle("display","inline-grid");

    lastRouteDisplay = new Label("Route: "+router.getCurrentRoute());

    customerDisplay = new Label("");

    this.add(new Label("<html><h1>Customers "+this.hashCode()+"</h1>"), lastRouteDisplay, customerDisplay);

  }

  @Override
  public Panel setVisible(Boolean visible) {
    super.setVisible(visible);
    lastRouteDisplay.setText("Route: "+router.getCurrentRoute());

    App.consoleLog(router.getCurrentRoute()+" "+showRoute.getRoute()+ " matches: "+showRoute.matches(router.getCurrentRoute()));

    if (showRoute.matches(router.getCurrentRoute())) {
      String custNum = showRoute.getString(router.getCurrentRoute(),"custnum");
      customerDisplay.setText("Showing Customer "+custNum);
    } else {
      customerDisplay.setText("No particular customer to show");
    }
    if (visible)
      setStyle("display","inline-grid");
    return this;
  }
}
