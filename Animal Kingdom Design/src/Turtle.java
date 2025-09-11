public class Turtle extends Reptile {
    public Turtle(double weight) {
        super(weight);
    }


    @Override
    public void breathe() {
        System.out.println("Reptile breath");
    }


    @Override
    public void baskInSun() {
        System.out.println("Turtle bask");
    }
}