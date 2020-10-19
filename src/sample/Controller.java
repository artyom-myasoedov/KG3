package sample;

import java.net.URL;
import java.util.*;

import drawings.figures.Sun;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private AnchorPane innerAnchorPane;

    @FXML
    private Button buttonClear;

    @FXML
    private Button ScaleButton;

    @FXML
    private TextField ScaleTextField;

    @FXML
    private TextField lightsRadius;

    @FXML
    private TextField numberOfLightstextField;

    @FXML
    private TextField mainRadiusTextField;

    @FXML
    private TextField angleTextField;

    private final Deque<Sun> list = new LinkedList<>();

    @FXML
    void initialize() {
        buttonClear.setOnAction(e -> {
            innerAnchorPane.getChildren().clear();
        });

        ScaleButton.setOnAction(e -> {
            double scale = Double.parseDouble(ScaleTextField.getText());
            list.forEach(x -> x.getMarkers().stream().skip(1).forEach(m -> m.setPoint(new Point2D(m.getPoint().getX() * scale, m.getPoint().getY() * scale))));
            list.forEach(Sun::redraw);
        });

        innerAnchorPane.setOnMouseClicked(e -> {
            if (e.isControlDown()) {
                try {
                    list.addFirst(new Sun(new Point2D(e.getX(), e.getY()), Integer.parseInt(mainRadiusTextField.getText()), Integer.parseInt(lightsRadius.getText()),
                            Double.parseDouble(angleTextField.getText()), Integer.parseInt(numberOfLightstextField.getText()), innerAnchorPane, list));
                    list.getFirst().draw();
                } catch (Exception ex) {
                    alert(ex.getMessage());
                }
            }
        });
    }

    private void alert(String str) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText(str);
        alert.showAndWait();
    }
}
