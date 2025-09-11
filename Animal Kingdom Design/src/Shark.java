public class Shark extends Fish {
    public Shark(double weight) {
        super(weight);
    }


    @Override
    public void breathe() {
        System.out.println("Fish breath");
    }


    @Override
    public void layEggs() {
        System.out.println("Shark eggs");
    }
}