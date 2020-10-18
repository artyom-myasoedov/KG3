package drawings;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public interface Drawer {

    void draw(double... coordinates);

    Color getColor();

    void setColor(Color color);
}
