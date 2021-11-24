package polymorphism.Shapes;

public abstract class Shape {

    private Double perimeter;
    private Double area;


    public Double getPerimeter() {
        if (perimeter == null) {
            calculatePerimeter();
        }
        return perimeter;
    }

    public Double getArea() {
        if (area == null) {
            calculateArea();
        }
        return area;
    }

    protected abstract void calculateArea();

    protected abstract void calculatePerimeter();

    protected void setPerimeter(Double perimeter) {
        this.perimeter = perimeter;
    }

    protected void setArea(Double area) {
        this.area = area;
    }

}
