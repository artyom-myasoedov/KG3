package drawings.markers;

import drawings.figures.Sun;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;


public class MovingMarker extends Marker {

    public MovingMarker(Point2D point, Sun sun) {
        super(point, sun);
        circle = createCircle(Point2D.ZERO);
        setOnMouseEvent();
    }

    @Override
    void setEvent(MouseEvent e) {
        setPoint(new Point2D(0, 0));
        sun.getGroup().setLayoutX(e.getSceneX() - 192);
        sun.getGroup().setLayoutY(e.getSceneY());
    }
}
