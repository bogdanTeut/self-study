package enums.annotations;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by bogdan.teut on 25/09/2014.
 */
public class UseCaseTracker {

    public static void main(String[] args) {
        List<Integer> ids = new ArrayList<Integer>();
        Collections.addAll(ids, 47, 48, 49, 50);
        trackUseCases(PasswordUtils.class, ids);
    }

    private static void trackUseCases(Class<?> type, List<Integer> ids) {
        for (Method method:type.getMethods()){
            UseCase useCase = method.getAnnotation(UseCase.class);
            if (useCase != null){
                System.out.println(useCase.id()+ " " +useCase.description());
                ids.remove(new Integer(useCase.id()));
            }
        }
        System.out.println("Missed methods: ");
        System.out.println(Arrays.toString(ids.toArray()));
    }
}
