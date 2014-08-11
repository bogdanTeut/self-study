package rtti;

/**
 * Created by bogdan.teut on 06/08/2014.
 */
public class CaptureConversion {
    static <T> void f1(Holder<T> holder) {
        T t = holder.get();
        System.out.println(t.getClass().getSimpleName());
    }
    static void f2(Holder<?> holder) {
        f1(holder); // Call with captured type
    }
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Holder raw = new Holder<Integer>(1);
// f1(raw); // Produces warnings
        f2(raw); // No warnings
        Holder rawBasic = new Holder();
        rawBasic.set(new Object()); // Warning
        f2(rawBasic); // No warnings
// Upcast to Holder<?>, still figures it out:
        Holder<?> wildcarded = new Holder<Double>(1.0);
        f2(wildcarded);
        f1(wildcarded);
    }
}

class Holder<T>{
    T t;

    Holder(T t) {
        this.t = t;
    }

    Holder() {
    }

    public T get() {
        return t;
    }

    public void set(T t) {
        this.t = t;
    }
}
