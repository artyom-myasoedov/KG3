package drawings.pixel;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public interface PixelDrawer {

    void draw(double x, double y, Group group, Color color);
}
