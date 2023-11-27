package routerDemo;

import org.dwcj.component.text.Label;
import org.dwcj.component.window.Panel;
import org.dwcj.component.window.Window;

public class OrdersPanel extends Panel {

  @Override
  protected void onCreate(Window p) {
    super.onCreate(p);
    this.add(new Label("Orders "+this.hashCode()));
  }
}
