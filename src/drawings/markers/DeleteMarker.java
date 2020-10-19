package drawings.markers;

import drawings.figures.Sun;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.Deque;

public class DeleteMarker extends Marker {
    private Deque<Sun> suns;
    public DeleteMarker(Point2D point, Sun sun, Deque<Sun> suns) {
        super(point, sun);
        this.suns = suns;
        circle.setFill(Color.RED);
        circle.setOnMouseClicked(e -> {
            sun.getPane().getChildren().remove(sun.getGroup());
            suns.remove(sun);
        });
    }

    @Override
    void setEvent(MouseEvent e) {

    }
}
