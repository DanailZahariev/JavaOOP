package WorkingWithAbstraction.PointInRectangle;

public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isGreaterOrEqual(Point p) {
        return x >= p.x && y >= p.y;
    }

    public boolean isLessOrEqual(Point p) {
        return x <= p.x && y <= p.y;
    }
}
