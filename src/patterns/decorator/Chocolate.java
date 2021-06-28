package patterns.decorator;

public class Chocolate implements Decorator {
    private Beverage beverage;

    public Chocolate(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public int cost() {
        return this.beverage.cost() + 10;
    }
}
