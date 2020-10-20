package drawings;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CoordinatePlane {
    private double step;
    private Pane pane;
    private Group group;
    private Point2D center;
    private double[] borders;

    public CoordinatePlane(Pane pane, double step) {
        this.pane = pane;
        this.group = new Group();
        this.step = step;
        center = new Point2D(600, 400);
        pane.getChildren().add(group);
        borders = new double[]{600, -600, 400, -400};

        group.setLayoutX(center.getX());
        group.setLayoutY(center.getY());
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public double getScale() {
        return step;
    }

    public void setScale(double scale) {
        step = scale;
    }

    public Point2D getCenter() {
        return center;
    }

    public void setCenter(Point2D center) {
        borders[0] -= center.getX() - this.center.getX();
        borders[1] -= center.getX() - this.center.getX();
        borders[2] -= center.getY() - this.center.getY();
        borders[3] -= center.getY() - this.center.getY();
        this.center = center;

    }

    public double[] getBorders() {
        return borders;
    }

    public void setBorders(double[] borders) {
        this.borders = borders;
    }

    public void draw() {

        drawVLines(borders[2], borders[3], borders[0]);
        drawVLines(borders[2], borders[3], borders[1]);
        drawHLines(borders[0], borders[1], borders[2]);
        drawHLines(borders[0], borders[1], borders[3]);

        Line lineY = new Line(0, borders[2], 0, borders[3]);
        lineY.setStrokeWidth(2);
        Line lineX = new Line(borders[0], 0, borders[1], 0);
        lineX.setStrokeWidth(2);


        group.getChildren().addAll(lineX, lineY);
    }

    public void clear() {
        group.getChildren().clear();
    }

    public  void translate() {
        clear();
        draw();
        group.setLayoutX(center.getX());
        group.setLayoutY(center.getY());
    }

    private void drawVLines(double up, double down, double border) {
        int i = 0, n = (int) (border / step);
        while (i < Math.abs(n)) {
            Line line = new Line(step * i  * Math.signum(border), down, step * Math.signum(border) * i++, up);
            line.setStroke(Color.LIGHTGRAY);
            group.getChildren().addAll(line, createHPointNumber((int) (Math.signum(border) * i)));
        }
        Line ln = new Line(step * i  * Math.signum(border), down, step * Math.signum(border) * i, up);
        ln.setStroke(Color.LIGHTGRAY);
        group.getChildren().add(ln);
    }

    private void drawHLines(double left, double right, double border) {
        int i = 0, n = (int) (border / step);
        while (i < Math.abs(n)) {
            Line line = new Line(right, step * i  * Math.signum(border), left, step * Math.signum(border) * i++);
            line.setStroke(Color.LIGHTGRAY);
            group.getChildren().addAll(line, createVPointNumber((int) (Math.signum(border) * i)));
        }
        Line ln = new Line(right, step * i  * Math.signum(border), left, step * Math.signum(border) * i);
        ln.setStroke(Color.LIGHTGRAY);
        group.getChildren().add(ln);
    }

    private Group createHPointNumber(int n) {
        Group group = new Group();
        group.setLayoutX(n * step);
        Line line = new Line(0, -4, 0, 4);
        line.setStrokeWidth(2);
        Text txt = new Text(Integer.toString(n * 100));
        txt.setFont(new Font(10));
        txt.setLayoutY(10);
        txt.setLayoutX(10);
        group.getChildren().addAll(line, txt);
        return group;
    }


    private Group createVPointNumber(int n) {
        Group group = new Group();
        group.setLayoutY(n * step);
        Line line = new Line(-4, 0, 4, 0);
        line.setStrokeWidth(2);
        Text txt = new Text(Integer.toString(-n * 100));
        txt.setFont(new Font(10));
        txt.setLayoutY(10);
        txt.setLayoutX(10);
        group.getChildren().addAll(line, txt);
        return group;
    }


}
