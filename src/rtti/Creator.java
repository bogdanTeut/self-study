package rtti;

import java.util.Iterator;

/**
 * Created by bogdan.teut on 01/08/2014.
 */
public interface Creator<T> {
    T create();
    Iterator<T> iterator();
}
