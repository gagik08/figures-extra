package com.epam.rd.autotasks.figures;


public class Triangle extends Figure {
    private final Point p1;
    private final Point p2;
    private final Point p3;

    public Triangle(Point p1, Point p2, Point p3) {
        if (p1 == null || p2 == null || p3 == null) {
            throw new IllegalArgumentException("Triangle vertices cannot be null.");
        }
        if (p1.isTheSame(p2) || p1.isTheSame(p3) || p2.isTheSame(p3)) {
            throw new IllegalArgumentException("Triangle has degenerate vertices.");
        }
        if (isCollinear(p1, p2, p3)) {
            throw new IllegalArgumentException("Triangle is collinear.");
        }

        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    private boolean isCollinear(Point p1, Point p2, Point p3) {
        double area = 0.5 * ((p2.getX() - p1.getX()) * (p3.getY() - p1.getY()) - (p3.getX() - p1.getX()) * (p2.getY() - p1.getY()));
        return Math.abs(area) <= 1e-10;
    }

    @Override
    public Point centroid() {
        double centerX = (p1.getX() + p2.getX() + p3.getX()) / 3.0;
        double centerY = (p1.getY() + p2.getY() + p3.getY()) / 3.0;
        return new Point(centerX, centerY);
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (figure instanceof Triangle) {
            Triangle otherTriangle = (Triangle) figure;
            return p1.isTheSame(otherTriangle.p1) && p2.isTheSame(otherTriangle.p2) && p3.isTheSame(otherTriangle.p3)
                    || p1.isTheSame(otherTriangle.p2) && p2.isTheSame(otherTriangle.p3) && p3.isTheSame(otherTriangle.p1)
                    || p1.isTheSame(otherTriangle.p3) && p2.isTheSame(otherTriangle.p1) && p3.isTheSame(otherTriangle.p2);
        }
        return false;
    }
}
