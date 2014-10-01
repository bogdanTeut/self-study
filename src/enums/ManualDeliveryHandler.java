package enums;

/**
 * Created by bogdan on 17/09/14.
 */
public class ManualDeliveryHandler {
    public boolean handle(Mail mail) {
        switch (mail.readability){
            case ILLEGIBLE:
                return false;
            default:
                switch (mail.address) {
                    case CORRECT:
                        return false;
                    default:
                        System.out.println("Manual delivery for " + mail);
                        return true;
                }
        }
    }
}
