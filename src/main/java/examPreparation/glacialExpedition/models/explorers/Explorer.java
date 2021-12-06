package examPreparation.glacialExpedition.models.explorers;

import examPreparation.glacialExpedition.models.suitcases.Suitcase;

public interface Explorer {

    String getName();

    double getEnergy();

    boolean canSearch();

    Suitcase getSuitcase();

    void search();
}
