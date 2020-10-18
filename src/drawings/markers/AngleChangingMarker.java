package drawings.markers;

import drawings.figures.Sun;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

public class AngleChangingMarker extends Marker {

    public AngleChangingMarker(Point2D point, Sun sun) {
        super(point, sun);
    }

    @Override
    void setEvent(MouseEvent e) {
        double x, y,
                ang = -Math.atan(e.getY() / e.getX()),
                radius = sun.getMarkers().get(2).getPoint().distance(Point2D.ZERO);

        x = radius * 1.2 * Math.cos(ang);
        y = -radius * 1.2 * Math.sin(ang);
        if (e.getX() < 0) {
            x = -x;
            y = -y;
        }
        setPoint(new Point2D(x, y));
        getCircle().setCenterX(getPoint().getX());
        getCircle().setCenterY(getPoint().getY());
        Marker mk = sun.getMarkers().get(2);
        mk.setPoint(new Point2D(getPoint().getX() / 1.2, getPoint().getY() / 1.2));
        mk.getCircle().setCenterX(mk.getPoint().getX());
        mk.getCircle().setCenterY(mk.getPoint().getY());
        double finalX = x,
                finalY = y,
                finalAngle = finalX < 0 ? Math.atan(-finalY / finalX) + Math.PI : Math.atan(-finalY / finalX);
        circle.setOnMouseReleased(e1 -> {
            sun.setStartAngle(finalAngle);
            sun.clearLights();
            sun.drawLights();
        });
    }
}
