package enums.vendingmachine;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

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
    public void whenInputMoneyChangeStateToAddingMoney(Input input, State state){
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.next(input);
        assertEquals(vendingMachine.state, State.ADDING_MONEY);
        assertEquals(vendingMachine.amount, input.amount());
    }

    @Test
    public void whenInputStopChangeStateToTerminal(){
        VendingMachine vendingMachine = new VendingMachine();
        Input input = Input.STOP;
        vendingMachine.next(input);
        assertEquals(vendingMachine.state, State.TERMINAL);
    }
}
