public abstract class Mammal extends Animal implements Walkable {
    protected Mammal(double weight) {
        super(weight);
    }


    @Override
    public void walk() {
        System.out.println("Mammal is walking...");
    }


    public abstract void growHair();
}