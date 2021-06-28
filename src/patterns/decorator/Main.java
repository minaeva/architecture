package patterns.decorator;

public class Main {

    public static void main(String[] args) {
        System.out.println("espresso 15; americano 20; milk 3; chocolate 10");
        Beverage americano = new Americano();
        System.out.println("americano.cost " + americano.cost());

        Beverage americanoWithMilk = new Milk(americano);
        System.out.println("americanoWithMilk.cost " + americanoWithMilk.cost());

        Beverage espresso = new Espresso();
        System.out.println("espresso.cost " + espresso.cost());

        Beverage espressoWithMilk = new Milk(espresso);
        System.out.println("espressoWithMilk.cost " + espressoWithMilk.cost());

        Beverage espressoWithChocolate = new Chocolate(espresso);
        System.out.println("espressoWithChocolate.cost " + espressoWithChocolate.cost());

        Beverage espressoWithChocolateAndMilk = new Milk(espressoWithChocolate);
        System.out.println("espressoWithChocolateAndMilk.cost " + espressoWithChocolateAndMilk.cost());

    }
}
