package web_browser;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class WebBrowserController implements Initializable {

    @FXML
    private TabPane panee;

    @FXML
    private JFXTabPane tabLayout;

    @FXML
    private JFXButton minimalizeButton, widnowButton, closeButton;

    private double xOffset, yOffset;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Stage stage = Main.primaryStage;

        tabLayout.setOnMousePressed(event -> {
            xOffset = stage.getX() - event.getScreenX();
            yOffset = stage.getY() - event.getScreenY();
        });

        tabLayout.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() + xOffset);
            stage.setY(event.getScreenY() + yOffset);
        });

        minimalizeButton.setOnAction(event -> {
            stage.setIconified(true);
        });

        minimalizeButton.setOnAction(event -> {
        });

        closeButton.setOnAction(event -> stage.close());

        tabLayout.setTabClosingPolicy(JFXTabPane.TabClosingPolicy.SELECTED_TAB);

        Tab tabb = new Tab();
        tabb.setGraphic(createTabButton("fff"));
        tabb.setClosable(false);
        tabb.setTooltip(new Tooltip("Add new tab"));

        tabb.setStyle("-fx-pref-width: 50px; -fx-pref-height: 30px; -fx-background-color: transparent; -fx-rippler-fill: #000000;");
        tabb.getGraphic().setStyle("-fx-background-color: transparent; -fx-padding: 0px 0px 5px 0px;");
        tabb.setDisable(true);

        tabLayout.getTabs().add(tabb);


        ((Button) tabb.getGraphic()).setOnAction(new EventHandler<>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                try {
                    Tab tab = new Tab();
                    tab.setText("facebook.com");
                    tab.setContent(FXMLLoader.load(this.getClass().getResource("NavigationBar.fxml")));
                    tabLayout.getTabs().add(tabLayout.getTabs().size() - 1, tab);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Button createTabButton(String iconName) {
        Button button = new Button();
        ImageView imageView = new ImageView(new Image(getClass().getResource("add_icon3.png").toExternalForm(),
                20, 20, false, true));
        button.setGraphic(imageView);
        button.getStyleClass().add("tab-button");
        return button;
    }
}

