package enums;

/**
 * Created by bogdan on 17/09/14.
 */
public class AutomaticDeliveryHandler {
    public boolean handle(Mail mail) {
        switch (mail.scanability) {
            case UNSCANNABLE:
                return false;
            default:
                switch (mail.address) {
                    case INCORRECT:
                        return false;
                    default:
                        System.out.println("Automatic delivery " + mail);
                        return true;
                }
        }
    }
}
