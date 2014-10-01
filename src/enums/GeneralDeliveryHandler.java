package enums;

/**
 * Created by bogdan on 17/09/14.
 */
public class GeneralDeliveryHandler {
    public boolean handle(Mail mail) {
        switch (mail.generalDelivery){
            case YES:
                System.out.println("General delivery for "+mail);
                return true;
            default:
                return false;

        }
    }
}
