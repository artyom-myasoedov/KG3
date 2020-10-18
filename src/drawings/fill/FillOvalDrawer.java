package drawings.fill;

import drawings.curves.CurveDrawer;
import drawings.line.BresenhamLineDrawer;
import drawings.line.LineDrawer;
import drawings.line.VuLineDrawer;
import drawings.pixel.PixelDrawer;
import drawings.pixel.RectanglePixelDrawer;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class FillOvalDrawer implements CurveDrawer {
    private Color color;
    private PixelDrawer pixelDrawer;
    private Group group;

    public FillOvalDrawer(PixelDrawer pixelDrawer, Group group) {
        this.pixelDrawer = pixelDrawer;
        this.group = group;
        color = Color.BLACK;
    }

    @Override
    public void draw(double... coordinates) {
        if (checkCoordinates(coordinates)) {
            double x1 = 0;
            double y1 = coordinates[3];
            double a_sqr = Math.pow(coordinates[2], 2);
            double b_sqr = Math.pow(coordinates[3], 2);
            double delta = 4 * b_sqr * ((x1 + 1) * (x1 + 1)) + a_sqr * ((2 * y1 - 1) * (2 * y1 - 1)) - 4 * a_sqr * b_sqr;
            while (a_sqr * (2 * y1 - 1) > 2 * b_sqr * (x1 + 1)) {
                Pixel_circle(coordinates[0], coordinates[1], x1, y1, color);
                x1++;
                if (delta < 0) {
                    delta += 4 * b_sqr * (2 * x1 + 3);
                } else {
                    delta = delta - 8 * a_sqr * (y1 - 1) + 4 * b_sqr * (2 * x1 + 3);
                    y1--;
                }
            }
            delta = b_sqr * ((2 * x1 + 1) * (2 * x1 + 1)) + 4 * a_sqr * ((y1 + 1) * (y1 + 1)) - 4 * a_sqr * b_sqr;
            while (y1 + 1 != 0) {
                Pixel_circle(coordinates[0], coordinates[1], x1, y1, color);
                y1--;
                if (delta < 0) // Переход по вертикали
                {
                    delta += 4 * a_sqr * (2 * y1 + 3);
                } else {
                    delta = delta - 8 * b_sqr * (x1 + 1) + 4 * a_sqr * (2 * y1 + 3);
                    x1++;
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

    private void Pixel_circle(double xc, double yc, double x1, double y1, Color color) {
        LineDrawer br = new VuLineDrawer(new RectanglePixelDrawer(1), group);
        br.setColor(color);
        pixelDrawer.draw(xc + x1, yc + y1, group, color);
        br.draw( xc + x1, yc - y1, xc + x1, yc + y1);
        pixelDrawer.draw(xc + x1, yc - y1, group, color);
        br.draw(xc + x1, yc - y1, xc + x1, yc - y1);
        br.draw( xc - x1, yc + y1, xc - x1, yc - y1);
        br.draw( xc - x1, yc - y1, xc - x1, yc + y1);
    }
}
