package samples;

import org.dwcj.App;
import org.dwcj.annotation.InlineStyleSheet;
import org.dwcj.component.button.Button;
import org.dwcj.component.button.event.ButtonClickEvent;
import org.dwcj.component.window.Frame;
import org.dwcj.exceptions.DwcjException;
import org.dwcj.kitchen.multiselectcombo.MultiSelectComboBox;
import org.dwcj.kitchen.multiselectcombo.MultiSelectComboBoxItem;
import org.dwcj.kitchen.multiselectcombo.event.MultiSelectComboBoxFilterChangedEvent;
import org.dwcj.kitchen.multiselectcombo.event.MultiSelectComboBoxInputEvent;
import org.dwcj.kitchen.multiselectcombo.event.MultiSelectComboBoxOpenedChangedEvent;
import org.dwcj.kitchen.multiselectcombo.event.MultiSelectComboBoxSelectedChangedEvent;

import java.util.ArrayList;
import java.util.List;

@InlineStyleSheet(/* css */"""
    .frame {
        height: 100vh;
        display: flex;
        align-items: center;
        justify-content: center;
    }
      
    bbj-multi-select-combo-box {
        width: 400px;
    }
""")
public class MultiSelectComboDemo extends App {

  MultiSelectComboBox multiSelectComboBox;
  ArrayList<MultiSelectComboBoxItem> items;
  @Override
  public void run() throws DwcjException {

    Frame frame = new Frame();
    frame.addClassName("frame");

    items = new ArrayList();
    items.add(new MultiSelectComboBoxItem("iraq", "Iraq"));
    items.add(new MultiSelectComboBoxItem("iran", "Iran"));
    items.add(new MultiSelectComboBoxItem("syria", "Syria"));
    items.add(new MultiSelectComboBoxItem("lebanon", "Lebanon"));
    items.add(new MultiSelectComboBoxItem("jordan", "Jordan"));
    items.add(new MultiSelectComboBoxItem("egypt", "Egypt"));
    items.add(new MultiSelectComboBoxItem("saudi", "Saudi Arabia"));
    items.add(new MultiSelectComboBoxItem("uae", "United Arab Emirates"));
    items.add(new MultiSelectComboBoxItem("kuwait", "Kuwait"));
    items.add(new MultiSelectComboBoxItem("qatar", "Qatar"));

    ArrayList<String> selectedItems = new ArrayList();
    selectedItems.add("jordan");
    selectedItems.add("kuwait");
    selectedItems.add("syria");

    ArrayList<MultiSelectComboBox.HighlightBehaviours> highlightBehaviours = new ArrayList();
    highlightBehaviours.add(MultiSelectComboBox.HighlightBehaviours.KEY);
    highlightBehaviours.add(MultiSelectComboBox.HighlightBehaviours.MOUSE);

    multiSelectComboBox = new MultiSelectComboBox();

    frame.add(multiSelectComboBox);

    multiSelectComboBox
        .setPlaceholder("Search")
        .setLabel("Countries")
        .setAllowCustomValue(true)
        .setMaxChipsLength(50)
        .setName("countries")
        .setAttribute("data-test", "just for testing")
        .setPlacement(MultiSelectComboBox.Placement.BOTTOM)
        .setExpanse(MultiSelectComboBox.Expanse.MEDIUM)
        .setTabTraversable(0)
        .setRequired(false)
        .setSkidding(10f)
        .setAutoFocus(true)
        .setHighlightBehaviors(highlightBehaviours)
        .setMaxRowCount(5)
        .setReadOnly(false)

        .setStyle("opacity", "1")
        .setStyle("will-change", "auto")
        .removeStyle("will-change")
        .addInputListener(this::inputChangeHandler)
        .addFilterChangedListener(this::filterChangedHandler)
        .addOpenedChangedListener(this::openedChangedHandler)
        .addSelectedChangedListener(this::selectedChangedHandler)
        .setDistance(5f);

    multiSelectComboBox.setItems(items);
    multiSelectComboBox.setSelected(selectedItems);

    Button getSelectionButton = new Button("get Selection");
    frame.add(getSelectionButton);

    frame.setStyle("display","grid");
    frame.setStyle("gap","20px");

    getSelectionButton.onClick(this::getSelection);
  }

  private void getSelection(ButtonClickEvent buttonClickEvent) {
    List<String> s = multiSelectComboBox.getSelected();
    App.msgbox("Selection: "+s);

  }

  public void inputChangeHandler(MultiSelectComboBoxInputEvent event) {
    consoleLog(event.toString());
  }
  public void filterChangedHandler(MultiSelectComboBoxFilterChangedEvent event) {
    consoleLog(event.toString());
  }
  public void openedChangedHandler(MultiSelectComboBoxOpenedChangedEvent event) {
    consoleLog(event.toString());
  }
  public void selectedChangedHandler(MultiSelectComboBoxSelectedChangedEvent event) {
    consoleLog(event.toString());
  }
}
