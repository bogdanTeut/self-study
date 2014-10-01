package enums;

/**
 * Created by bogdan on 17/09/14.
 */
public class ReturnDeliveryHandler {
    public boolean handle(Mail mail) {
        switch (mail.returnAddress){
            case MISSING:
                return false;
            default:
                System.out.println("Return to sender "+mail);
                return true;
        }
    }
}
