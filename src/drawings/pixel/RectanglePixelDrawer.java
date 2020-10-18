package drawings.pixel;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectanglePixelDrawer implements PixelDrawer {
    private int size;

    public RectanglePixelDrawer(int size) {
        this.size = size;
    }

    @Override
    public void draw(double x, double y, Group group, Color color) {
        Rectangle rect = new Rectangle(x, y, size, size);
        rect.setFill(color);
        group.getChildren().add(rect);
    }
}
