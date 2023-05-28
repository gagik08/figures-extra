package com.epam.rd.autotasks.figures;

import java.util.Arrays;

class Quadrilateral extends Figure {
    private final Point p1;
    private final Point p2;
    private final Point p3;
    private final Point p4;

    public Quadrilateral(Point p1, Point p2, Point p3, Point p4) {
        validateQuadrilateral(p1, p2, p3, p4);
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }

    @Override
    public Point centroid() {
        double cx = (p1.getX() + p2.getX() + p3.getX() + p4.getX()) / 4.0;
        double cy = (p1.getY() + p2.getY() + p3.getY() + p4.getY()) / 4.0;
        return new Point(cx, cy);
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (figure instanceof Quadrilateral) {
            Quadrilateral other = (Quadrilateral) figure;
            Point[] otherVertices = {other.p1, other.p2, other.p3, other.p4};
            return Arrays.asList(otherVertices).containsAll(Arrays.asList(p1, p2, p3, p4)) &&
                    Arrays.asList(p1, p2, p3, p4).containsAll(Arrays.asList(otherVertices));
        }
        return false;
    }

    private void validateQuadrilateral(Point p1, Point p2, Point p3, Point p4) {
        if (p1 == null || p2 == null || p3 == null || p4 == null) {
            throw new IllegalArgumentException("Points cannot be null");
        }

        double area1 = 0.5 * (p1.getX() * (p2.getY() - p4.getY())
                + p2.getX() * (p4.getY() - p1.getY())
                + p4.getX() * (p1.getY() - p2.getY()));

        double area2 = 0.5 * (p2.getX() * (p3.getY() - p1.getY())
                + p3.getX() * (p1.getY() - p2.getY())
                + p1.getX() * (p2.getY() - p3.getY()));

        if (area1 == 0 || area2 == 0) {
            throw new IllegalArgumentException("Quadrilateral is degenerate");
        }

        double areaSum = Math.abs(area1) + Math.abs(area2);
        if (Math.abs(areaSum) <= 0.000001) {
            throw new IllegalArgumentException("Quadrilateral is degenerate");
        }

        double crossProduct1 = (p2.getX() - p1.getX()) * (p4.getY() - p3.getY()) - (p2.getY() - p1.getY()) * (p4.getX() - p3.getX());
        double crossProduct2 = (p3.getX() - p2.getX()) * (p1.getY() - p4.getY()) - (p3.getY() - p2.getY()) * (p1.getX() - p4.getX());
        if (crossProduct1 * crossProduct2 >= 0) {
            throw new IllegalArgumentException("Quadrilateral is not convex");
        }
    }
}



