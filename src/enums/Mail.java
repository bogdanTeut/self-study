package enums;

/**
 * Created by bogdan on 17/09/14.
 */
enum GeneralDelivery{
    YES, NO
}

enum Scanability{
    YES, UNSCANNABLE
}

enum Readability{
    YES, ILLEGIBLE
}

enum Forwardability{
    YES, UNSCANNABLE
}

enum Address{
    CORRECT, INCORRECT
}

enum ReturnAddress{
    YES, MISSING
}

public class Mail {
    GeneralDelivery generalDelivery;
    Scanability scanability;
    Readability readability;
    Forwardability forwardability;
    ReturnAddress returnAddress;
    Address address;

    public Mail(String generalDelivery, String scanability, String readability, String forwardability, String returnAddress, String address) {
        this.generalDelivery = GeneralDelivery.valueOf(generalDelivery);
        this.scanability = Scanability.valueOf(scanability);
        this.readability = Readability.valueOf(readability);
        this.forwardability = Forwardability.valueOf(forwardability);
        this.returnAddress = ReturnAddress.valueOf(returnAddress);
        this.address = Address.valueOf(address);
    }
}
