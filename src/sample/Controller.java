package sample;

import java.net.URL;
import java.util.ResourceBundle;

import drawings.figures.Sun;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    void initialize() {
        Sun sun = new Sun(new Point2D(500, 500), 50, 150, Math.PI, 1, anchorPane);
        sun.draw();
        Sun sun1 = new Sun(new Point2D(200, 200), 50, 150, Math.PI / 4, 4, anchorPane);
        sun1.draw();
    }
}
