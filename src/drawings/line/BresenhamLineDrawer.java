package drawings.line;

import drawings.pixel.PixelDrawer;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class BresenhamLineDrawer implements LineDrawer {
    private PixelDrawer pixelDrawer;
    private Group group;
    private Color color;
    private int k;

    public BresenhamLineDrawer(PixelDrawer pixelDrawer, Group group, int k) {
        this.pixelDrawer = pixelDrawer;
        this.group = group;
        color = Color.BLACK;
        this.k = k;
    }

    @Override
    public void draw(double... coordinates) {
        if (checkCoordinates(coordinates)) {
            double x = coordinates[0],
                    t = 90,
                    y = coordinates[1],
                    dx = coordinates[2] - coordinates[0],
                    dy = coordinates[3] - coordinates[1],
                    max, sx = dx < 0 ? -1 : 1, sy = dy < 0 ? -1 : 1, e, minLength, maxLength;
            maxLength = Math.max(Math.abs(dy), Math.abs(dx));
            minLength = Math.min(Math.abs(dy), Math.abs(dx));
            boolean vector = Math.abs(dy) - Math.abs(dx) < 0;
            e = 2 * minLength - maxLength;
            max = maxLength / k;
            pixelDrawer.draw(x, y, group, color);
            while (max-- > 0) {
                if (e > 0) {
                    if (vector) {
                        y += k * sy;
                    } else {
                        x += k * sx;
                    }
                    e -= 2 * maxLength;
                }
                e += 2 * minLength;
                if (vector) {
                    x += k * sx;
                } else {
                    y += k * sy;
                }
                pixelDrawer.draw(x, y, group, color);
            }
        }
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    private boolean checkCoordinates(double[] coordinates) {
        return coordinates.length == 4;
    }
}
