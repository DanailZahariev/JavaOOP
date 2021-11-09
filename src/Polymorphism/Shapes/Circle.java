package Polymorphism.Shapes;

public class Circle extends Shape {

    private Double radius;

    public Circle(Double radius) {
        this.radius = radius;
        calculateArea();
        calculatePerimeter();
    }

    @Override
    protected void calculateArea() {
        Double result = 2 * Math.PI * radius;
        setArea(result);
    }

    @Override
    protected void calculatePerimeter() {
        Double result = Math.PI * radius * radius;
        setPerimeter(result);
    }

    public final Double getRadius() {
        return radius;
    }
}
