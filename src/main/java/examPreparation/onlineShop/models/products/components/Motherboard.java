package examPreparation.onlineShop.models.products.components;

public class Motherboard extends BaseComponent {

    private static final double OVERALL_PERFORMANCE_MULTIPLIER = 1.25;

    public Motherboard(int id, String manufacturer, String model, double price, double overallPerformance, int generation) {
        super(id, manufacturer, model, price, OVERALL_PERFORMANCE_MULTIPLIER * overallPerformance, generation);
    }
}
