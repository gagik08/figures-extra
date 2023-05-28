package com.epam.rd.autotasks.figures;

public class Circle extends Figure {
    private final Point center;
    private final double radius;

    public Circle(Point center, double radius) {
        if (center == null) {
            throw new IllegalArgumentException("Circle center cannot be null.");
        }
        if (radius <= 0) {
            throw new IllegalArgumentException("Circle has non-positive radius.");
        }
        this.center = center;
        this.radius = radius;
    }

    @Override
    public Point centroid() {
        return center;
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (figure instanceof Circle) {
            Circle otherCircle = (Circle) figure;
            return center.isTheSame(otherCircle.center) && radius == otherCircle.radius;
        }
        return false;
    }
}

