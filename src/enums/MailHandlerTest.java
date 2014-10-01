package enums;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.mockito.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by bogdan on 17/09/14.
 */
public class MailHandlerTest {

    @Mock
    GeneralDeliveryHandler generalDeliveryHandler;
    @Mock
    AutomaticDeliveryHandler automaticDeliveryHandler;
    @Mock
    ManualDeliveryHandler manualDeliveryHandler;
    @Mock
    ForwardDeliveryHandler forwardDeliveryHandler;
    @Mock
    ReturnDeliveryHandler returnDeliveryHandler;
    @InjectMocks
    PostOffice handler = new PostOffice();


    @BeforeMethod(alwaysRun=true)
    public void injectDoubles() {
        MockitoAnnotations.initMocks(this); //This could be pulled up into a shared base class
    }

    @Test
    public void generalDelivery(){
        Mail mail = new Mail("YES", "UNSCANNABLE", "ILLEGIBLE", "UNSCANNABLE", "MISSING", "INCORRECT");

        //given
        given(generalDeliveryHandler.handle(mail)).willReturn(true);
        //when
        boolean result = handler.handle(mail);
        //then
        assertTrue(result);
        verify(generalDeliveryHandler).handle(mail);
    }

    @Test
    public void automaticDelivery(){
        Mail mail = new Mail("NO", "YES", "ILLEGIBLE", "UNSCANNABLE", "MISSING", "CORRECT");

        //given
        given(automaticDeliveryHandler.handle(mail)).willReturn(true);
        //when
        boolean result = handler.handle(mail);
        //then
        assertTrue(result);
        verify(automaticDeliveryHandler).handle(mail);
    }

    @Test
    public void manualDelivery(){
        Mail mail = new Mail("NO", "UNSCANNABLE", "YES", "UNSCANNABLE", "MISSING", "CORRECT");

        //given
        given(manualDeliveryHandler.handle(mail)).willReturn(true);
        //when
        boolean result = handler.handle(mail);
        //then
        assertTrue(result);
        verify(manualDeliveryHandler).handle(mail);
    }

    @Test
    public void forwardDelivery(){
        Mail mail = new Mail("NO", "UNSCANNABLE", "ILLEGIBLE", "YES", "MISSING", "INCORRECT");

        //given
        given(forwardDeliveryHandler.handle(mail)).willReturn(true);
        //when
        boolean result = handler.handle(mail);
        //then
        assertTrue(result);
        verify(forwardDeliveryHandler).handle(mail);
    }

    @Test
    public void returnToSender(){
        Mail mail = new Mail("NO", "UNSCANNABLE", "ILLEGIBLE", "UNSCANNABLE", "YES", "INCORRECT");

        //given
        given(returnDeliveryHandler.handle(mail)).willReturn(true);
        //when
        boolean result = handler.handle(mail);
        //then
        assertTrue(result);
        verify(returnDeliveryHandler).handle(mail);
    }
}
