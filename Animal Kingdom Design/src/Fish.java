public abstract class Fish extends Animal implements Swimmable {
    protected Fish(double weight) {
        super(weight);
    }


    @Override
    public void swim() {
        System.out.println("Fish is swimming...");
    }


    public abstract void layEggs();
}