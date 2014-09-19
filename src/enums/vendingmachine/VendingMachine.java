package enums.vendingmachine;

/**
 * Created by bogdan.teut on 19/09/2014.
 */
public class VendingMachine{
    public State state = State.RESTING;
    public int amount;

    public void next(Input input) {
        switch (input){
            case DOLLAR:
            case NICKEL:
            case DIME:
            case QUARTER:
                amount += input.amount();
                state = State.ADDING_MONEY;
                break;
            case STOP:
                state = State.TERMINAL;
                break;
        }
    }
}
