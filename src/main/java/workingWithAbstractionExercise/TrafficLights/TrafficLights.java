package workingWithAbstractionExercise.TrafficLights;

public class TrafficLights {
    private Colors colors;

    public TrafficLights(Colors colors) {
        this.colors = colors;
    }

    public void changeColor() {
        switch (this.colors) {
            case RED:
                this.colors = Colors.GREEN;
                break;
            case GREEN:
                this.colors = Colors.YELLOW;
                break;
            case YELLOW:
                this.colors = Colors.RED;
                break;
        }
    }

    public Colors getColors() {
        return colors;
    }
}
