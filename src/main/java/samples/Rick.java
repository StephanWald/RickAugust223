package samples;

import org.dwcj.App;
import org.dwcj.annotation.InlineStyleSheet;
import org.dwcj.component.Expanse;
import org.dwcj.component.button.Button;
import org.dwcj.component.button.ButtonTheme;
import org.dwcj.component.button.event.ButtonClickEvent;
import org.dwcj.component.htmledit.HtmlEdit;
import org.dwcj.component.window.Frame;
import org.dwcj.exceptions.DwcjException;
import org.dwcj.kitchen.fileupload.FileUpload;
import org.dwcj.kitchen.fileupload.event.FileUploadEvent;

@InlineStyleSheet(/* css */"""
    .frame {
      display: inline-grid;
      gap: 20px;
      margin: 20px;
      padding: 20px;
      border: 1px dotted;
    }
      """)
public class Rick extends App {

  private HtmlEdit htmlEdit;

  @Override
  public void run() throws DwcjException {

    Frame frame = new Frame();
    frame.addClassName("frame");

    //HTMLEdit
    htmlEdit = new HtmlEdit();
    frame.add(htmlEdit);

    htmlEdit.setText("Type something...");
    Button btn2 = new Button("Get HTMLEdit Contents");
    btn2.setTheme(ButtonTheme.SUCCESS)
        .setExpanse(Expanse.XLARGE)
        .onClick(this::getHTML);
    frame.add(btn2);

    //FileUpload
    FileUpload fileUpload = new FileUpload();
    frame.add(fileUpload);
    fileUpload.setMultipleFilesAllowed(true);
    fileUpload.onFileUpload(this::onFileUpload);

  }

  private void onFileUpload(FileUploadEvent fileUploadEvent) {
    App.msgbox(fileUploadEvent.getEventMap().toString(),0,"File Uploaded");
  }

  private void getHTML(ButtonClickEvent buttonClickEvent) {
    App.msgbox(htmlEdit.getText());
  }
}
