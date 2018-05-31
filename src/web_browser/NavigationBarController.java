package web_browser;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class NavigationBarController implements Initializable {

    @FXML
    private JFXTextField addressTextField;

    @FXML
    private JFXButton backButton, forwardButton, reloadButton, homeButton;

    @FXML
    private WebView webView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        WebEngine webEngine = webView.getEngine();
webEngine.setJavaScriptEnabled(true);
webEngine.setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko");
        addressTextField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                webEngine.load("https://" + addressTextField.getText());
            }
        });

        backButton.setOnAction(event -> Platform.runLater(() -> webEngine.executeScript("history.back()")));
        forwardButton.setOnAction(event -> Platform.runLater(() -> webEngine.executeScript("history.forward()")));
        reloadButton.setOnAction(event -> Platform.runLater(webEngine::reload));
        homeButton.setOnAction(event -> Platform.runLater(() -> webEngine.executeScript("history.back()")));
    }
}
