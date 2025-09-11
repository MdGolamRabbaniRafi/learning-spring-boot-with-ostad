public class Crocodile extends Reptile {
    public Crocodile(double weight) {
        super(weight);
    }


    @Override
    public void breathe() {
        System.out.println("Reptile breath");
    }


    @Override
    public void baskInSun() {
        System.out.println("Crocodile bask");
    }
}