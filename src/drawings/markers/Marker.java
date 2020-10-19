package drawings.markers;

import drawings.figures.Sun;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public abstract class Marker {
    private static final double RADIUS = 4;
    private Point2D point;
    protected Circle circle;
    protected Sun sun;

    public Marker(Point2D point, Sun sun) {
        this.point = point;
        this.sun = sun;
        circle = createCircle(point);
        setOnMouseEvent();
    }

    public Point2D getPoint() {
        return point;
    }

    public void setPoint(Point2D point) {
        this.point = point;
        circle.setCenterX(point.getX());
        circle.setCenterY(point.getY());
    }

    public Circle getCircle() {
        return circle;
    }

    protected static Circle createCircle(Point2D point) {
        return new Circle(point.getX(), point.getY(), RADIUS, Color.LIGHTGRAY);
    }

    protected void setOnMouseEvent() {
        circle.setOnMouseDragged(this::setEvent);
    }

    abstract void setEvent(MouseEvent e);

}
