package enums.vendingmachine;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static enums.vendingmachine.VendingMachine.State;

/**
 * Created by bogdan.teut on 19/09/2014.
 */
public class VendingMachineTest {
    
    @Test
    public void createVendingMachine(){
        VendingMachine vendingMachine =  new VendingMachine();
        assertNotNull(vendingMachine);
        assertEquals(vendingMachine.state, State.RESTING);
    }
    
    @DataProvider(name = "Money")
    public Object[][] money(){
        return new Object[][]{
                {Input.DOLLAR, State.ADDING_MONEY},
                {Input.NICKEL, State.ADDING_MONEY},
                {Input.DIME, State.ADDING_MONEY},
                {Input.QUARTER, State.ADDING_MONEY}
        };
    }
    
    @Test(dataProvider = "Money")
    public void whenRestingAndInputMoney(Input input, State state){
        VendingMachine vendingMachine = new VendingMachine(State.RESTING, 0);
        vendingMachine.state.next(input);
        assertEquals(vendingMachine.state, State.ADDING_MONEY);
        assertEquals(vendingMachine.amount, vendingMachine.amount);
    }

    @Test
    public void whenRestingAndInputStop(){
        VendingMachine vendingMachine = new VendingMachine(State.RESTING, 0);
        Input input = Input.STOP;
        vendingMachine.state.next(input);
        assertEquals(vendingMachine.state, State.TERMINAL);
    }
    
    @Test
    public void whenAddingMoneyAndInputMoney(){
        VendingMachine vendingMachine = new VendingMachine(State.ADDING_MONEY, 0);
        vendingMachine.state.next(Input.DOLLAR);
        assertEquals(vendingMachine.state, State.ADDING_MONEY);
        assertEquals(vendingMachine.amount, Input.DOLLAR.amount());
    }

    @Test
    public void whenAddingMoneyAndInputItemSelection(){
        VendingMachine vendingMachine = new VendingMachine(State.ADDING_MONEY, 200);
        vendingMachine.state.next(Input.CHIPS);
        assertEquals(vendingMachine.state, State.DISPENSING);

        //negativ case, insufficient money
        vendingMachine = new VendingMachine(State.ADDING_MONEY, 0);
        vendingMachine.state.next(Input.CHIPS);
        assertEquals(vendingMachine.state, State.ADDING_MONEY);
    }

    @Test
    public void whenAddingMoneyAndQuitingTransaction(){
        VendingMachine vendingMachine = new VendingMachine(State.ADDING_MONEY, 0);
        vendingMachine.state.next(Input.ABORT_TRANSACTION);
        assertEquals(vendingMachine.state, State.GIVING_CHANGE);
    }

    @Test
    public void whenAddingMoneyAndInputStop(){
        VendingMachine vendingMachine = new VendingMachine(State.ADDING_MONEY, 0);
        vendingMachine.state.next(Input.STOP);
        assertEquals(vendingMachine.state, State.TERMINAL);
    }

    @Test
    public void whenDispensing(){
        VendingMachine vendingMachine = new VendingMachine(State.DISPENSING, 200);
        vendingMachine.selection = Input.SODA;
        vendingMachine.state.next();
        assertEquals(vendingMachine.state, State.GIVING_CHANGE);
        assertEquals(vendingMachine.amount, 200-vendingMachine.selection.amount());
    }

    @Test
    public void whenGivingChange(){
        VendingMachine vendingMachine = new VendingMachine(State.GIVING_CHANGE, 200);
        vendingMachine.state.next();
        assertEquals(vendingMachine.state, State.RESTING);
        assertEquals(vendingMachine.amount, 0);
    }
    
    @Test
    public void testRandomInput(){
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.run(new InputGenerator());
        
    }
}
