package com.epam.rd.autotasks.figures;

class Point {
    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public boolean isTheSame(Point point) {
        if (point == null) {
            return false;
        }
        return Math.abs(x - point.x) <= 0.000001 && Math.abs(y - point.y) <= 0.000001;
    }
}
