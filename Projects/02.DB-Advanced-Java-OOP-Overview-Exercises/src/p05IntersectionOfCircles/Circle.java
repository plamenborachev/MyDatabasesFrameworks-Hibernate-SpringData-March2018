package p05IntersectionOfCircles;

public class Circle {
    private Point center;
    private int radius;

    public Circle(int x, int y, int radius) {
        this.center = new Point(x, y);
        this.radius = radius;
    }

    public Point getCenter() {
        return this.center;
    }

    public int getRadius() {
        return this.radius;
    }

    public static boolean intersect(Circle c1, Circle c2){
        double distanceBetweenTheCircleCenters = Math.sqrt(Math.pow(Math.abs(c1.getCenter().getX() - c2.getCenter().getX()), 2) + Math.pow(Math.abs(c1.getCenter().getY() - c2.getCenter().getY()), 2));
        if (distanceBetweenTheCircleCenters <= c1.getRadius() + c2.getRadius()){
            return true;
        }
        return false;
    }
}
