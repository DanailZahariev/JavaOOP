package polymorphism.Shapes;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Shape shape = new Rectangle(13.00, 2.00);
        Shape shape2 = new Circle(3D);

        List<Shape> shapes = new ArrayList<>();
        shapes.add(shape);
        shapes.add(shape2);

        shapes.forEach(shape1 -> {
            System.out.println(shape1.getPerimeter());
            System.out.println(shape1.getArea());
        });

    }
}
