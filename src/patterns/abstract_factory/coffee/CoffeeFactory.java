package patterns.abstract_factory.coffee;

import patterns.abstract_factory.AbstractFactory;
import patterns.abstract_factory.DrinkType;

public class CoffeeFactory implements AbstractFactory<CoffeeInterface> {

    public CoffeeInterface getDrink(DrinkType type) {
        if (DrinkType.AMERICANO.equals(type)) {
            return new Americano();
        } else if (DrinkType.ESPRESSO.equals(type)) {
            return new Espresso();
        }
        throw new RuntimeException("not supported type " + type);
    }
}
