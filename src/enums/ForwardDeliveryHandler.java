package enums;

/**
 * Created by bogdan on 17/09/14.
 */
public class ForwardDeliveryHandler {
    public boolean handle(Mail mail) {
        switch (mail.returnAddress){
            case MISSING:
                return false;
            default:
                System.out.println("Forward "+mail);
                return true;
        }
    }
}
