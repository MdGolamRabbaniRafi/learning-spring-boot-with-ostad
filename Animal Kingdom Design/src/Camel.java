public class Camel extends Mammal {
    public Camel(double weight) {
        super(weight);
    }


    @Override
    public void breathe() {
        System.out.println("Mammal breath");
    }


    @Override
    public void growHair() {
        System.out.println("Camel hair");
    }
}