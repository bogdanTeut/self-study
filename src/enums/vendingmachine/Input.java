package enums.vendingmachine;

/**
 * Created by bogdan.teut on 18/09/2014.
 */
public enum Input {
    NICKEL(5), DIME(10), QUARTER(25), DOLLAR(100),
    TOOTHPASTE(200), CHIPS(75), SODA(100), SOAP(50),
    ABORT_TRANSACTION{
        public int value(){
            throw new RuntimeException("Abort");            
        }
    }, STOP {
        public int value() {
            throw new RuntimeException("Stop");
        }

        ;
    };
    
    private int value;

    Input(int value) {
        this.value = value;
    }
    
    Input(){}

    public int amount (){
        return value;
    }
}
