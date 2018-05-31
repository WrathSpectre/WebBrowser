package web_browser;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.javafx.BrowserView;

public class NavigationBarController implements Initializable {

    @FXML
    private JFXTextField URLTextField;

    @FXML
    private JFXButton backButton, forwardButton, reloadButton, homeButton;

    @FXML
    private GridPane gridPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Browser browser = new Browser();
        BrowserView browserView = new BrowserView(browser);
        gridPane.add(browserView, 0, 1);

        URLTextField.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)) {
                browser.loadURL("https://" + URLTextField.getText());
            }
        });

        backButton.setOnAction(event -> browser.goBack());
        forwardButton.setOnAction(event -> browser.goForward());
        reloadButton.setOnAction(event -> browser.reload()); //if cannot opacity 0.5
    }
}
