package drawings.markers;

import drawings.figures.Sun;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class DeleteMarker extends Marker {
    public DeleteMarker(Point2D point, Sun sun) {
        super(point, sun);
        circle.setFill(Color.RED);
        circle.setOnMouseClicked(e -> {
            sun.getPane().getChildren().remove(sun.getGroup());
        });
    }

    @Override
    void setEvent(MouseEvent e) {

    }
}
