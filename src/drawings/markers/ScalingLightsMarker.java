package drawings.markers;

import drawings.figures.Sun;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

public class ScalingLightsMarker extends Marker {

    public ScalingLightsMarker(Point2D point, Sun sun) {
        super(point, sun);
    }

    @Override
    void setEvent(MouseEvent e) {
        double x, y;
        if (Math.abs(e.getX()) > Math.abs(e.getY())) {
            x = e.getX();
            y = -x * Math.tan(sun.getStartAngle());
        } else {
            y = e.getY();
            x = -y / Math.tan(sun.getStartAngle());
        }
        setPoint(new Point2D((int)x, (int)y));
        getCircle().setCenterX(getPoint().getX());
        getCircle().setCenterY(getPoint().getY());
        Marker mk = sun.getMarkers().get(3);
        mk.setPoint(new Point2D(getPoint().getX() * 1.2, getPoint().getY() * 1.2));
        mk.getCircle().setCenterX(mk.getPoint().getX());
        mk.getCircle().setCenterY(mk.getPoint().getY());
        circle.setOnMouseReleased(e1 -> {
            sun.clearLights();
            sun.drawLights();
        });
    }
}
