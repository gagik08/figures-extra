package com.epam.rd.autotasks.figures;

class Circle extends Figure {
    private final Point center;
    private final double radius;

    public Circle(Point center, double radius) {
        validateCircle(center, radius);
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
            Circle other = (Circle) figure;
            return center.isTheSame(other.center) && Math.abs(radius - other.radius) <= 0.000001;
        }
        return false;
    }

    private void validateCircle(Point center, double radius) {
        if (center == null) {
            throw new IllegalArgumentException("Circle center cannot be null");
        }
        if (radius <= 0) {
            throw new IllegalArgumentException("Circle radius must be positive");
        }
    }
}


