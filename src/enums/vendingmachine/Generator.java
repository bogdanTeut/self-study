package enums.vendingmachine;

/**
 * Created by bogdan.teut on 22/09/2014.
 */
public interface Generator<T> {
    T next();
}
