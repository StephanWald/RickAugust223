package routerDemo;

import org.dwcj.component.button.Button;
import org.dwcj.component.button.event.ButtonClickEvent;
import org.dwcj.component.field.TextField;
import org.dwcj.component.text.Label;
import org.dwcj.component.window.Panel;
import org.dwcj.component.window.Window;
import org.dwcj.kitchen.simplerouter.SimpleRouter;

public class DashboardPanel extends Panel {

  private TextField custNum;

  @Override
  protected void onCreate(Window p) {
    super.onCreate(p);
    this.add(new Label("<html><h1>Dashboard "+this.hashCode()+"</h1>"));

    custNum = new TextField();
    custNum.setText("4711");
    custNum.setLabel("Customer Number:");

    add(custNum);
    
    Button btn = new Button("navigate to this Customer.");
    btn.onClick(this::navigateToCustomer);
    add(btn);

    setStyle("display","inline-grid");

  }

  @Override
  public Panel setVisible(Boolean visible) {
    super.setVisible(visible);
    if (visible)
      setStyle("display","inline-grid");
    return this;
  }

  private void navigateToCustomer(ButtonClickEvent buttonClickEvent) {
    SimpleRouter.getInstance().navigate("customers/"+custNum.getText());
  }
}
