public class Cow extends Mammal {
    public Cow(double weight) {
        super(weight);
    }


    @Override
    public void breathe() {
        System.out.println("Mammal breath");
    }


    @Override
    public void growHair() {
        System.out.println("Cow hair");
    }
}