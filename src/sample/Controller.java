package sample;

import java.awt.*;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import drawings.CoordinatePlane;
import drawings.figures.Sun;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

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
    private TextField lightsRadius;

    @FXML
    private Button ScaleButton;

    @FXML
    private TextField ScaleTextField;

    @FXML
    private TextField numberOfLightstextField;

    @FXML
    private TextField mainRadiusTextField;

    @FXML
    private TextField angleTextField;

    private final Deque<Sun> list = new LinkedList<>();

    @FXML
    void initialize() {
        CoordinatePlane cp = new CoordinatePlane(innerAnchorPane, 100);
        cp.draw();
        buttonClear.setOnAction(e -> {
            innerAnchorPane.getChildren().removeAll(innerAnchorPane.getChildren().stream().skip(1).collect(Collectors.toList()));
            list.clear();
        });

        ScaleButton.setOnAction(e -> {
            double scale = Double.parseDouble(ScaleTextField.getText());
            cp.clear();
            cp.setScale(100 * scale);
            cp.draw();
            list.forEach(x -> x.setScale(scale));
            list.forEach(Sun::redraw);
        });

        innerAnchorPane.setOnMouseClicked(e -> {
            if (e.isControlDown()) {
                try {
                    list.addFirst(new Sun(new Point2D(e.getX() - cp.getCenter().getX(), e.getY() - cp.getCenter().getY()), Integer.parseInt(mainRadiusTextField.getText()), Integer.parseInt(lightsRadius.getText()),
                            Double.parseDouble(angleTextField.getText()) * Math.PI / 180, Integer.parseInt(numberOfLightstextField.getText()), innerAnchorPane, list, cp, Double.parseDouble(ScaleTextField.getText())));
                    list.getFirst().draw();
                } catch (Exception ex) {
                    alert(ex.getMessage());
                }
            }
        });

         double[] x = new double[1];
         double[] y = new double[1];
         double[] cpX = new double[1];
         double[] cpY = new double[1];
        innerAnchorPane.setOnMousePressed(e -> {
            if (e.isShiftDown()) {
                x[0] = e.getSceneX() - 192;
                y[0] = e.getSceneY();
                cpX[0] = cp.getCenter().getX();
                cpY[0] = cp.getCenter().getY();
            }
        });

        innerAnchorPane.setOnMouseDragged(e -> {
            if (e.isShiftDown()) {
                cp.setCenter(new Point2D(cpX[0] + e.getSceneX() - 192 - x[0], cpY[0] + e.getSceneY() - y[0]));
                cp.translate();
                list.forEach(sun -> {
                    sun.getGroup().setLayoutX(cp.getCenter().getX() + sun.getPosition().getX());
                    sun.getGroup().setLayoutY(cp.getCenter().getY() + sun.getPosition().getY());
                });
            }
        });
        innerAnchorPane.setMaxWidth(1200);
        innerAnchorPane.setMaxHeight(800);
    }

    private void alert(String str) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText(str);
        alert.showAndWait();
    }
}
