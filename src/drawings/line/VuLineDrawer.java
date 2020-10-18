package drawings.line;

import drawings.pixel.PixelDrawer;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class VuLineDrawer implements LineDrawer {
    private Color color;
    private PixelDrawer pixelDrawer;
    private Group group;

    public VuLineDrawer(PixelDrawer pixelDrawer, Group group) {
        this.pixelDrawer = pixelDrawer;
        this.group = group;
        color = Color.BLACK;

    }

    @Override
    public void draw(double... coordinates) {
        if (checkCoordinates(coordinates)) {
            double x = coordinates[0],
                    y = coordinates[1],
                    dx = coordinates[2] - coordinates[0],
                    dy = coordinates[3] - coordinates[1],
                    max, sx = dx < 0 ? -1 : 1, sy = dy < 0 ? -1 : 1, e, minLength, maxLength,
                    bright, enterBright;
            maxLength = Math.max(Math.abs(dy), Math.abs(dx));
            minLength = Math.min(Math.abs(dy), Math.abs(dx));
            boolean vector = Math.abs(dy) - Math.abs(dx) < 0;
            e = 2 * minLength - maxLength;
            max = maxLength;
            pixelDrawer.draw(x, y, group, color);
            bright = minLength / maxLength;
            if (vector) {
                enterBright = x + 2 * bright + Math.round(y) - y;
            } else {
                enterBright = y + 2 * bright + Math.round(x) - x;
            }
            while (max-- > 0) {
                if (e > 0) {
                    if (vector) {
                        y += sy;

                    } else {
                        x += sx;
                    }
                    e -= 2 * maxLength;
                }
                e += 2 * minLength;
                if (vector) {
                    x += sx;
                } else {
                    y += sy;
                }
                if (dx != 0 && dy != 0) {
                    pixelDrawer.draw(x, y, group, new Color(color.getRed(), color.getGreen(), color.getBlue(), 1 - enterBright + (int) enterBright));
                    if (maxLength == Math.abs(dx)) {
                        pixelDrawer.draw(x, y + 1, group, new Color(color.getRed(), color.getGreen(), color.getBlue(), enterBright - (int) enterBright));
                    } else {
                        pixelDrawer.draw(x + 1, y, group, new Color(color.getRed(), color.getGreen(), color.getBlue(), enterBright - (int) enterBright));
                    }
                    enterBright += bright;
                } else {
                    pixelDrawer.draw(x, y, group, color);

                }
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
