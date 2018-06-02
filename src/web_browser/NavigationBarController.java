package web_browser;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

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

        backButton.setDisable(true);
        backButton.setOpacity(0.5);

        forwardButton.setDisable(true);
        forwardButton.setOpacity(0.5);


        URLTextField.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)) {
                browser.loadURL("https://" + URLTextField.getText());

                if (browser.canGoBack()) {
                    backButton.setDisable(false);
                    backButton.setOpacity(1);
                }

                if (browser.canGoForward()) {
                    forwardButton.setDisable(false);
                    forwardButton.setOpacity(1);
                }
            }
        });

        backButton.setOnAction(event -> {
            if(browser.canGoBack()) {
                browser.goBack();

                if(forwardButton.isDisable()) {
                    forwardButton.setDisable(false);
                    forwardButton.setOpacity(1);
                }
            }

            if(!browser.canGoBack()) {
                backButton.setOpacity(0.5);
                backButton.setDisable(true);
            }
        });

        forwardButton.setOnAction(event -> {
            if(browser.canGoForward()) {
                browser.goForward();

                if(backButton.isDisable()) {
                    backButton.setDisable(false);
                    backButton.setOpacity(1);
                }
            }

            if(!browser.canGoBack()) {
                forwardButton.setOpacity(0.5);
                forwardButton.setDisable(true);
            }
        });
        reloadButton.setOnAction(event -> browser.reload()); //if cannot opacity 0.5
    }
}
