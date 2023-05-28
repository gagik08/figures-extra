package com.epam.rd.autotasks.figures;

import java.util.Arrays;

public class Quadrilateral extends Figure {
    private final Point[] vertices;

    public Quadrilateral(Point vertex1, Point vertex2, Point vertex3, Point vertex4) {
        this.vertices = new Point[]{vertex1, vertex2, vertex3, vertex4};
        validateQuadrilateral();
    }

    private void validateQuadrilateral() {
        if (vertices[0].equals(vertices[1]) || vertices[0].equals(vertices[2]) || vertices[0].equals(vertices[3])
                || vertices[1].equals(vertices[2]) || vertices[1].equals(vertices[3]) || vertices[2].equals(vertices[3])) {
            throw new IllegalArgumentException("Quadrilateral is degenerative");
        }

        if (!isConvex(vertices)) {
            throw new IllegalArgumentException("Quadrilateral is not convex");
        }
    }

    private boolean isConvex(Point[] vertices) {
        int orientationABD = getOrientation(vertices[0], vertices[1], vertices[3]);
        int orientationABC = getOrientation(vertices[0], vertices[1], vertices[2]);
        int orientationBCD = getOrientation(vertices[1], vertices[2], vertices[3]);
        int orientationCDA = getOrientation(vertices[2], vertices[3], vertices[0]);

        return orientationABD == orientationABC && orientationABC == orientationBCD && orientationBCD == orientationCDA;
    }

    private int getOrientation(Point p, Point q, Point r) {
        double value = (q.getY() - p.getY()) * (r.getX() - q.getX())
                - (q.getX() - p.getX()) * (r.getY() - q.getY());

        if (value == 0) {
            return 0; // Collinear
        } else if (value > 0) {
            return 1; // Clockwise orientation
        } else {
            return -1; // Counterclockwise orientation
        }
    }

    @Override
    public Point centroid() {
        double centerX = (vertices[0].getX() + vertices[1].getX() + vertices[2].getX() + vertices[3].getX()) / 4;
        double centerY = (vertices[0].getY() + vertices[1].getY() + vertices[2].getY() + vertices[3].getY()) / 4;
        return new Point(centerX, centerY);
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (figure instanceof Quadrilateral) {
            Quadrilateral other = (Quadrilateral) figure;
            return Arrays.equals(this.vertices, other.vertices);
        }
        return false;
    }
}






