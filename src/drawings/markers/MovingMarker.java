package drawings.markers;

import drawings.figures.Sun;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;


public class MovingMarker extends Marker {
    private double oldX;
    private double oldY;
    private double oldPosX;
    private double oldPosY;


    public MovingMarker(Point2D point, Sun sun) {
        super(point, sun);
        circle = createCircle(Point2D.ZERO);
        setOnMouseEvent();
        circle.setOnMousePressed(e -> {
            oldX = e.getSceneX();
            oldY = e.getSceneY();
            oldPosX = sun.getPosition().getX();
            oldPosY = sun.getPosition().getY();

        });
    }

    @Override
    void setEvent(MouseEvent e) {
        setPoint(new Point2D(0, 0));
        sun.setPosition(new Point2D(oldPosX * sun.getScale() + e.getSceneX() - oldX, oldPosY * sun.getScale() + e.getSceneY() - oldY));
        sun.getGroup().setLayoutX(sun.getCp().getCenter().getX() + sun.getPosition().getX());
        sun.getGroup().setLayoutY(sun.getCp().getCenter().getY() + sun.getPosition().getY());
    }
}
