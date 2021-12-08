package examPreparation.glacialExpedition.models.explorers;

public class NaturalExplorer extends BaseExplorer {

    private static final double INITIAL_ENERGY = 60;

    public NaturalExplorer(String name) {
        super(name, INITIAL_ENERGY);
    }

    @Override
    public void search() {
        if (getEnergy() <= 7) {
            this.setEnergy(0);
        } else {
            this.setEnergy(this.getEnergy() - 7);
        }
    }
}