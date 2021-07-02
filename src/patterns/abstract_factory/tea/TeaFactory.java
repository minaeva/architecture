package patterns.abstract_factory.tea;

import patterns.abstract_factory.AbstractFactory;
import patterns.abstract_factory.DrinkType;

public class TeaFactory implements AbstractFactory<TeaInterface> {

    @Override
    public TeaInterface getDrink(DrinkType type) {
        if (DrinkType.BLACK_TEA.equals(type)) {
            return new BlackTea();
        } else if (DrinkType.GREEN_TEA.equals(type)) {
            return new GreenTea();
        }
        throw new RuntimeException("not supported type " + type);
    }
}
