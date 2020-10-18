package drawings.figures;

import drawings.markers.Marker;
import javafx.geometry.Point2D;

import java.util.List;

public interface IFigure {

    void draw();
    List<Marker> getMarkers();
    void moveMarker(Point2D from, Point2D to);
}
