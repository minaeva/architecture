package patterns.abstract_factory;

import patterns.abstract_factory.coffee.CoffeeFactory;
import patterns.abstract_factory.tea.TeaFactory;

public class FactoryProvider {
    public static AbstractFactory getFactory(String choice){

        if("Tea".equalsIgnoreCase(choice)){
            return new TeaFactory();
        }
        else if("Coffee".equalsIgnoreCase(choice)){
            return new CoffeeFactory();
        }

        return null;
    }
}
