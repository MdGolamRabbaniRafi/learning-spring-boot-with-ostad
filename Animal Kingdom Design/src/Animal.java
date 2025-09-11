public abstract class Animal {
    private final double weight;


    protected Animal(double weight) {
        this.weight = weight;
    }


    public double getWeight() {
        return weight;
    }


    public abstract void breathe();
}