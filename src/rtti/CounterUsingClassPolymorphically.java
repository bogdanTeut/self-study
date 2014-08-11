package rtti;

import rtti.individuals.FibonacciCreator;
import rtti.individuals.Pet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: bogdan.teut
 * Date: 28/07/14
 * Time: 09:48
 * To change this template use File | Settings | File Templates.
 */
public class CounterUsingClassPolymorphically<T> extends HashMap<Class<?>,Integer>{

    public void count(Class<?> type){
        Integer counter = get(type);
        this.put(type, counter!=null?counter+1:1);
        Class superType = type.getSuperclass();
        if (superType != null && superType.isAssignableFrom(type)){
            count(superType);
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("{");
        for (Map.Entry< Class<?>, Integer > entry:entrySet()){
            sb.append(entry.getKey().getSimpleName());
            sb.append("=");
            sb.append(entry.getValue());
            sb.append(", ");
        }
        sb.delete(sb.length()-2, sb.length());
        sb.append("}");
        return sb.toString();
    }



}
