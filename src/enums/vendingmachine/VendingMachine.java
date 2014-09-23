package enums.vendingmachine;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import static enums.vendingmachine.Input.*;
import static enums.vendingmachine.Input.ABORT_TRANSACTION;

/**
 * Created by bogdan.teut on 19/09/2014.
 */
public class VendingMachine{
    public static State state = State.RESTING;
    public static int amount;
    public static Input selection;
    
    EnumMap<State, State> stateEnumMap;

    public VendingMachine() {
    }

    public VendingMachine(State state, int amount) {
        this.state = state;
        this.amount = amount;
    }

    public void run(Generator<Input> generator) {
        while (state != State.TERMINAL){
            state.next(generator.next());
            while (state.isTransient()){
                state.next();    
            }
            state.output();
        }
    }

    enum State {

        RESTING {
            @Override
            public void next(Input input) {
                System.out.println("While resting got as an input "+input);
                switch (Category.getCategory(input)){
                    case MONEY:
                        amount += input.amount();
                        state = State.ADDING_MONEY;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                        break;
                }
            }
        },
        
        ADDING_MONEY {
            @Override
            public void next(Input input) {
                System.out.println("While adding money got as an input "+input);
                switch (Category.getCategory(input)){
                    case MONEY:
                        amount += input.amount();
                        break;
                    case ITEM_SELECTION:
                        if (amount > input.amount()){
                            selection = input;
                            state = DISPENSING;
                        }else{
                            System.out.println("Insufficient money");
                        }
                        break;
                    case QUIT_TRANSACTION: 
                        state = GIVING_CHANGE;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                        break;
                }
            }
        },


        DISPENSING {
            @Override
            public void next() {
                System.out.println("Here is your selection: "+selection);
                amount -= selection.amount();
                state = GIVING_CHANGE;
            }

            @Override
            public boolean isTransient() {
                return true;
            }
        },

        GIVING_CHANGE {
            public void next() {
                if (amount>0){
                    System.out.println("This is your change: "+amount);
                    amount = 0;
                }       
                state = RESTING;
            }

            @Override
            public boolean isTransient() {
                return true;
            }
        
        },

        TERMINAL {
            @Override
            public void output() {
                System.out.println("Halt");
            }
        };
        
        enum Category{
            MONEY(DOLLAR, NICKEL, DIME, QUARTER),
            ITEM_SELECTION(CHIPS, SOAP, SODA, TOOTHPASTE),
            QUIT_TRANSACTION(ABORT_TRANSACTION),
            SHUT_DOWN(STOP);
            
            Input[] inputs;
            
            Category(Input ...inputs){
                this.inputs = inputs;               
            }
            
            private static EnumMap<Input, Category> map = new EnumMap<Input, Category>(Input.class);
            
            static{
                for (Category category:Category.values()){
                    for (Input input:category.inputs){
                        map.put(input, category);                        
                    }                        
                }
            }
            
            public static Category getCategory(Input input){
                return map.get(input);                
            }
            
        }

        public void next(Input input){
            throw new RuntimeException("Only call next(Input input) for non transient states");
        };

        public void next(){
            throw new RuntimeException("Only call next() for transient states");
        };
        
        public void output(){
            System.out.println(amount);    
        }

        public boolean isTransient() {
            return false;
        }
    }
}
