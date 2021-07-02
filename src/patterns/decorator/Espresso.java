package patterns.decorator;

public class Espresso implements Beverage {

    @Override
    public int cost() {
        return 15;
    }
}
