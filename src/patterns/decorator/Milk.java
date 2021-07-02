package patterns.decorator;

public class Milk implements Decorator {

    private Beverage beverage;

    public Milk(Beverage beverage){
        this.beverage = beverage;
    }

    public int cost() {
        return this.beverage.cost() + 3;
    }
}
