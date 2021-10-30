package EncapsulationExercise.ClassBox;

public class Box {
    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        setLength(length);
        setWidth(width);
        setHeight(height);
    }

    private void ensureSize(double size, String messagePrefix) {
        if (size <= 0) {
            throw new IllegalArgumentException(messagePrefix + " cannot be zero or negative.");
        }
    }

    private void setHeight(double height) {
        ensureSize(height, "Height");

        this.height = height;
    }

    private void setWidth(double width) {
        ensureSize(width, "Width");
        this.width = width;
    }

    private void setLength(double length) {
        ensureSize(length, "Length");
        this.length = length;
    }

    public double calculateSurfaceArea() {
        return 2 * (length * width + length * height + width * height);
    }

    public double calculateLateralSurfaceArea() {
        return 2 * height * (length + width);
    }

    public double calculateVolume() {
        return this.length * this.width * this.height;
    }

}
