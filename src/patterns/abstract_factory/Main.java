package patterns.abstract_factory;

import patterns.abstract_factory.coffee.CoffeeFactory;
import patterns.abstract_factory.coffee.CoffeeInterface;
import patterns.abstract_factory.tea.TeaFactory;
import patterns.abstract_factory.tea.TeaInterface;

public class Main {

    public static void main(String[] args) {
        AbstractFactory<TeaFactory> teaFactory = FactoryProvider.getFactory("tea");
        TeaInterface tea = (TeaInterface) teaFactory.getDrink(DrinkType.GREEN_TEA);
        System.out.println(tea.isTasty());

        AbstractFactory<CoffeeFactory> coffeeFactory = FactoryProvider.getFactory(
                "coffee");
        CoffeeInterface coffee = (CoffeeInterface) coffeeFactory.getDrink(DrinkType.AMERICANO);
        System.out.println(coffee.getColor());
    }
}
