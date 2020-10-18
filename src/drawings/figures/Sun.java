package drawings.figures;

import drawings.curves.CurveDrawer;
import drawings.fill.FillOvalDrawer;
import drawings.line.LineDrawer;
import drawings.line.VuLineDrawer;
import drawings.markers.*;
import drawings.pixel.RectanglePixelDrawer;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sun implements IFigure {
    private double startAngle;
    private Pane pane;
    private final int numberOfLights;
    private final Group group;
    private final LineDrawer lineDrawer;
    private final CurveDrawer fillDrawer;
    private final List<Marker> markers;
    private final Group innerCircleGroup;
    private final Group innerLightsGroup;
    private boolean isMarkersShows;

    public double getStartAngle() {
        return startAngle;
    }

    public void setStartAngle(double startAngle) {
        this.startAngle = startAngle;
    }

    public Group getGroup() {
        return group;
    }

    public Pane getPane() {
        return pane;
    }

    public Sun(Point2D center, double mainRadius, double lightRadius, double startAngle, int numberOfLights, Pane pane) {
        this.startAngle = startAngle;
        this.numberOfLights = numberOfLights;
        this.pane = pane;
        Color color = Color.YELLOW;
        group = new Group();
        innerCircleGroup = new Group();
        innerLightsGroup = new Group();
        markers = new ArrayList<>();
        lineDrawer = new VuLineDrawer(new RectanglePixelDrawer(1), innerLightsGroup);
        fillDrawer = new FillOvalDrawer(new RectanglePixelDrawer(1), innerCircleGroup);

        markers.add(new MovingMarker(center, this));
        markers.add(new ScalingCircleMarker(new Point2D(mainRadius, 0), this));
        markers.add(new ScalingLightsMarker(new Point2D(lightRadius * Math.cos(startAngle), lightRadius * -Math.sin(startAngle)), this));
        markers.add( new AngleChangingMarker(new Point2D(lightRadius * 1.2 * Math.cos(startAngle), -lightRadius * 1.2 * Math.sin(startAngle)), this));
        markers.add(new DeleteMarker(new Point2D(-mainRadius / 2, 0), this));

        lineDrawer.setColor(color);
        fillDrawer.setColor(color);

        group.getChildren().addAll(innerCircleGroup, innerLightsGroup);
        pane.getChildren().add(group);

        group.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY && !isMarkersShows) {
                isMarkersShows = true;
                showMarkers();
            } else {
                hideMarkers();
                isMarkersShows = false;
            }
        });
    }

    @Override
    public void draw() {
        drawCircle();
        drawLights();
        group.setLayoutX(markers.get(0).getPoint().getX());
        group.setLayoutY(markers.get(0).getPoint().getY());
    }

    public void drawCircle() {
        int radius = (int) Math.abs(markers.get(1).getPoint().getX());
        fillDrawer.draw(0, 0, radius, radius);
    }

    public void drawLights() {
        int lightRadius = (int)markers.get(2).getPoint().distance(Point2D.ZERO);
        for (int i = 0; i < numberOfLights; i++) {
            double cos = Math.cos(startAngle - i * 2 * Math.PI / numberOfLights),
                    sin = -Math.sin(startAngle - i * 2 * Math.PI / numberOfLights);
            lineDrawer.draw(0, 0, lightRadius * cos, lightRadius * sin);
            lineDrawer.draw(1, 0, lightRadius * cos + 1, lightRadius * sin);
        }
    }

    @Override
    public List<Marker> getMarkers() {
        return markers;
    }

    @Override
    public void moveMarker(Point2D from, Point2D to) {

        //clearFigure();
        //draw();
    }

    public void clearFigure() {
        clearCircle();
        clearLights();
    }
    public void clearLights() {
        innerLightsGroup.getChildren().clear();
    }

    public void clearCircle() {
        innerCircleGroup.getChildren().clear();
    }

    private void showMarkers() {
        group.getChildren().addAll(markers.stream().map(Marker::getCircle).collect(Collectors.toList()));

    }

    private void hideMarkers() {
        group.getChildren().removeAll(markers.stream().map(Marker::getCircle).collect(Collectors.toList()));
    }
}
