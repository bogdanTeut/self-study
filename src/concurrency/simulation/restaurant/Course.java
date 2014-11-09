package concurrency.simulation.restaurant;

import java.util.Random;
/**
 * Created by bogdan on 09/11/14.
 */
interface Food{}

enum Appetizer implements Food{
    SALAD, SOUP, SPRING_ROLLS;
}

enum MainCourse implements Food{
    LASAGNE, BURRITO, PAD_THAI,
    LENTILS, HUMMOUS, VINDALOO;
}

enum Dessert implements Food{
    TIRAMISU, GELATO, BLACK_FOREST_CAKE,
    FRUIT, CREME_CARAMEL;
}

enum Coffee implements Food{
    BLACK_COFFEE, DECAF_COFFEE, ESPRESSO,
    LATTE, CAPPUCCINO, TEA, HERB_TEA;
}

public enum Course {
    APPETISER(Appetizer.class),
    MAIN_COURSE(MainCourse.class),
    DESSERT(Dessert.class),
    COFFEE(Coffee.class);

    Food[] values;

    Course(Class<? extends Food> type) {
        values = type.getEnumConstants();
    }

    public Food randomValues(){
       return values[new Random().nextInt(values.length)];
    }

}