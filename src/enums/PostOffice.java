package enums;

/**
 * Created by bogdan on 17/09/14.
 */
public class PostOffice {

    private GeneralDeliveryHandler generalDeliveryHandler;
    private AutomaticDeliveryHandler automaticDeliveryHandler;
    private ManualDeliveryHandler manualDeliveryHandler;
    private ForwardDeliveryHandler forwardDeliveryHandler;
    private ReturnDeliveryHandler returnDeliveryHandler;

    public boolean handle(Mail mail) {
        boolean result = false;
        for (MailHandler mailHandler:MailHandler.values()){
             result = mailHandler.handle(mail,this);
             if (result) return result;
        }
        return result;
    }

    public GeneralDeliveryHandler getGeneralDeliveryHandler() {
        return generalDeliveryHandler;
    }

    public void setGeneralDeliveryHandler(GeneralDeliveryHandler generalDeliveryHandler) {
        this.generalDeliveryHandler = generalDeliveryHandler;
    }

    public AutomaticDeliveryHandler getAutomaticDeliveryHandler() {
        return automaticDeliveryHandler;
    }

    public void setAutomaticDeliveryHandler(AutomaticDeliveryHandler automaticDeliveryHandler) {
        this.automaticDeliveryHandler = automaticDeliveryHandler;
    }

    public ManualDeliveryHandler getManualDeliveryHandler() {
        return manualDeliveryHandler;
    }

    public void setManualDeliveryHandler(ManualDeliveryHandler manualDeliveryHandler) {
        this.manualDeliveryHandler = manualDeliveryHandler;
    }

    public ForwardDeliveryHandler getForwardDeliveryHandler() {
        return forwardDeliveryHandler;
    }

    public void setForwardDeliveryHandler(ForwardDeliveryHandler forwardDeliveryHandler) {
        this.forwardDeliveryHandler = forwardDeliveryHandler;
    }

    public ReturnDeliveryHandler getReturnDeliveryHandler() {
        return returnDeliveryHandler;
    }

    public void setReturnDeliveryHandler(ReturnDeliveryHandler returnDeliveryHandler) {
        this.returnDeliveryHandler = returnDeliveryHandler;
    }

    enum MailHandler{
        GENERAL_DELIVERY {
            @Override
            boolean handle(Mail mail, PostOffice postOffice) {
                return postOffice.getGeneralDeliveryHandler().handle(mail);
            }
        },
        MACHINE_SCAN {
            @Override
            boolean handle(Mail mail, PostOffice postOffice) {
                return postOffice.getAutomaticDeliveryHandler().handle(mail);
            }
        },
        VISUAL_INSPECTION {
            @Override
            boolean handle(Mail mail, PostOffice postOffice) {
                return postOffice.getManualDeliveryHandler().handle(mail);
            }
        },
        FORWARD_MAIL {
            @Override
            boolean handle(Mail mail, PostOffice postOffice) {
                return postOffice.getForwardDeliveryHandler().handle(mail);
            }
        },
        RETURN_TO_ADDRESS {
            @Override
            boolean handle(Mail mail, PostOffice postOffice) {
                return postOffice.getReturnDeliveryHandler().handle(mail);
            }
        };

    abstract boolean handle(Mail mail, PostOffice postOffice);
}
}



