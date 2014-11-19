package concurrency.housebuilding;

/**
 * Created by bogdan.teut on 13/11/2014.
 */
public class House {
    private static int counter;
    private final int id = counter++;

    private boolean footings, steel, concreteForms, foundation, framing;

    public House() {
        footings = true;
    }

    public synchronized void addFootings(){
        footings = true;
    }

    public synchronized void addSteel(){
        steel = true;
    }

    public synchronized void addConcreteForms(){
        concreteForms = true;
    }

    public synchronized void addFoundation(){
        foundation = true;
    }

    public synchronized void addFraming(){
        framing = true;
    }

    @Override
    public String toString() {
        return "House "+id+" [footings "+footings+" ,steel "+steel+" ,concreteForms "+ concreteForms +" ,foundation "+foundation+" ,framing "+framing+" ]";
    }
}
