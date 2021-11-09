package Polymorphism.Shapes;

public class Rectangle extends Shape {

    private Double height;
    private Double width;

    public Rectangle(Double height, Double width) {
        this.height = height;
        this.width = width;

    }

    @Override
    protected void calculateArea() {
        Double result = height * width;
        super.setArea(result);
    }

    @Override
    protected void calculatePerimeter() {
        Double result = height * 2 + width * 2;
        super.setPerimeter(result);
    }

    public Double getHeight() {
        return height;
    }

    public Double getWidth() {
        return width;
    }
}
