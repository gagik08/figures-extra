package com.epam.rd.autotasks.figures;

import java.util.List;
import java.util.Arrays;

public class Quadrilateral extends Figure {
    private final Point p1;
    private final Point p2;
    private final Point p3;
    private final Point p4;

    public Quadrilateral(Point p1, Point p2, Point p3, Point p4) {
        if (p1 == null || p2 == null || p3 == null || p4 == null) {
            throw new IllegalArgumentException("Points cannot be null");
        }

        if (!isPlain(p1, p2, p3, p4) || !isConvex(p1, p2, p3, p4)) {
            throw new IllegalArgumentException("Invalid quadrilateral");
        }

        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }

    private boolean isPlain(Point p1, Point p2, Point p3, Point p4) {
        double ax = p1.getX();
        double ay = p1.getY();
        double bx = p2.getX();
        double by = p2.getY();
        double cx = p3.getX();
        double cy = p3.getY();
        double dx = p4.getX();
        double dy = p4.getY();

        return (bx - ax) * (cy - ay) == (cx - ax) * (by - ay)
                && (bx - ax) * (dy - ay) == (dx - ax) * (by - ay);
    }

    private boolean isConvex(Point p1, Point p2, Point p3, Point p4) {
        double ax = p1.getX();
        double ay = p1.getY();
        double bx = p2.getX();
        double by = p2.getY();
        double cx = p3.getX();
        double cy = p3.getY();
        double dx = p4.getX();
        double dy = p4.getY();

        double crossProductAB = (cx - ax) * (by - ay) - (bx - ax) * (cy - ay);
        double crossProductBC = (dx - bx) * (cy - by) - (cx - bx) * (dy - by);
        double crossProductCD = (ax - cx) * (dy - cy) - (dx - cx) * (ay - cy);
        double crossProductDA = (bx - dx) * (ay - dy) - (ax - dx) * (by - dy);

        return (crossProductAB >= 0 && crossProductBC >= 0 && crossProductCD >= 0 && crossProductDA >= 0)
                || (crossProductAB <= 0 && crossProductBC <= 0 && crossProductCD <= 0 && crossProductDA <= 0);
    }

    public boolean isTheSame(Figure figure) {
        if (figure instanceof Quadrilateral) {
            Quadrilateral other = (Quadrilateral) figure;
            List<Point> thisVertices = Arrays.asList(p1, p2, p3, p4);
            List<Point> otherVertices = Arrays.asList(other.p1, other.p2, other.p3, other.p4);

            // Check if all vertices of both quadrilaterals are the same, regardless of order
            boolean sameVertices = thisVertices.containsAll(otherVertices) && otherVertices.containsAll(thisVertices);

            // Check if both quadrilaterals are convex
            boolean convex = isConvex(p1, p2, p3, p4) && isConvex(other.p1, other.p2, other.p3, other.p4);

            return sameVertices && convex;
        }
        return false;
    }

    public Point centroid() {
        double centroidX = (p1.getX() + p2.getX() + p3.getX() + p4.getX()) / 4.0;
        double centroidY = (p1.getY() + p2.getY() + p3.getY() + p4.getY()) / 4.0;
        return new Point(centroidX, centroidY);
    }
}








