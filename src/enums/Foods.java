package enums;

import java.util.Random;

/**
 * Created by bogdan.teut on 12/09/2014.
 */
public class Foods {

    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            for (Meal course:Meal.values()){
                System.out.println(course.randomValue());                
            }            
        }
    }
}

enum Meal{
    APPETIZER(Appetizer.class),
    MAIN_COURSE(MainCourse.class),
    DESSERT(Dessert.class),
    COFFEE(Coffee.class);

    private Food[] values;
    private Meal(Class<? extends Food> clazz){
        values = clazz.getEnumConstants();
    }

    public Food randomValue(){
        return values[new Random().nextInt(values.length)];
    }
    
    interface Food {
    }
        enum Appetizer implements Food {
            SALAD, SOUP, SPRING_ROLS;
        }

        enum MainCourse implements Food {
            LASAGNE, BURRITO, PAD_THAI, LENTILS, HUMMOUS, VINDALOO;
        }

        enum Dessert implements Food {
            TIRAMISU, GELATO, BLACK_FOREST_CAKE, FRUIT, CREME_CARAMEL;
        }

        enum Coffee implements Food {
            BLACK_COFFEE, DECAF_COFFEE, ESPRESSO, LATTE, CAPPUCCINO, TEA, HERB_TEA;
        }
    }
//}

//enum Course{
//    APPETIZER(Food.Appetizer.class),
//    MAIN_COURSE(Food.MainCourse.class),
//    DESSERT(Food.Dessert.class),
//    COFFEE(Food.Coffee.class);
//    
//    private Food[] values;
//    private Course(Class<? extends Food> clazz){
//        values = clazz.getEnumConstants();
//    }
//    
//    public Food randomValue(){
//        return values[new Random().nextInt(values.length)];        
//    }
//}

