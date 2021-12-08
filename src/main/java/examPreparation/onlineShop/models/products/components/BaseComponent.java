package examPreparation.onlineShop.models.products.components;

import examPreparation.onlineShop.models.products.BaseProduct;

import static examPreparation.onlineShop.common.constants.OutputMessages.COMPONENT_TO_STRING;

public abstract class BaseComponent extends BaseProduct implements Component {

    private int generation;

    protected BaseComponent(int id, String manufacturer, String model, double price, double overallPerformance, int generation) {
        super(id, manufacturer, model, price, overallPerformance);
        this.generation = generation;
    }

    @Override
    public int getGeneration() {
        return generation;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(COMPONENT_TO_STRING, getGeneration());
    }
}
