package patterns.factory;

public class CoffeeShop {
    private final SimpleCoffeeFactory coffeeFactory;

    public CoffeeShop(SimpleCoffeeFactory coffeeFactory) {
        this.coffeeFactory = coffeeFactory;
    }

    public Coffee orderCoffee(CoffeeType type) {
        Coffee coffee = coffeeFactory.createCoffee(type);
        coffee.grindCoffee();
        coffee.makeCoffee();
        coffee.pourIntoCup();

        System.out.println("Вот ваш кофе! Спасибо, приходите еще!");
        return coffee;
    }

    public static void main(String[] args) {
        SimpleCoffeeFactory factory = new SimpleCoffeeFactory();
        CoffeeShop shop = new CoffeeShop(factory);

        shop.orderCoffee(CoffeeType.AMERICANO);

    }
}
