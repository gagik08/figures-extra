package com.epam.rd.autotasks.figures;

import java.util.Arrays;

class Triangle extends Figure{
    private final Point p1;
    private final Point p2;
    private final Point p3;

    public Triangle(Point p1, Point p2, Point p3) {
        validateTriangle(p1, p2, p3);
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    @Override
    public Point centroid() {
        double cx = (p1.getX() + p2.getX() + p3.getX()) / 3.0;
        double cy = (p1.getY() + p2.getY() + p3.getY()) / 3.0;
        return new Point(cx, cy);
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (figure instanceof Triangle) {
            Triangle other = (Triangle) figure;
            Point[] otherVertices = {other.p1, other.p2, other.p3};
            return Arrays.asList(otherVertices).containsAll(Arrays.asList(p1, p2, p3));
        }
        return false;
    }

    private void validateTriangle(Point p1, Point p2, Point p3) {
        if (p1 == null || p2 == null || p3 == null) {
            throw new IllegalArgumentException("Triangle vertices cannot be null");
        }

        double area = 0.5 * (p1.getX() * (p2.getY() - p3.getY())
                + p2.getX() * (p3.getY() - p1.getY())
                + p3.getX() * (p1.getY() - p2.getY()));

        if (area == 0) {
            throw new IllegalArgumentException("Triangle is degenerate");
        }
    }
}
