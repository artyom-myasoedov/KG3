package drawings.markers;

import drawings.figures.Sun;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

public class ScalingCircleMarker extends Marker {
    public ScalingCircleMarker(Point2D point, Sun sun) {
        super(point, sun);
    }

    @Override
    void setEvent(MouseEvent e) {
        setPoint(new Point2D((int)e.getX(), 0));
        getCircle().setCenterX(getPoint().getX());
        circle.setOnMouseReleased(e1 -> {
            sun.clearCircle();
            sun.drawCircle();
        });
    }
}
