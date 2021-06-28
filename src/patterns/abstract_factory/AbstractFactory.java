package patterns.abstract_factory;

public interface AbstractFactory<T> {

    T getDrink(DrinkType type);

}
