package messaging;

public abstract class MessageHandler {

    private MessageHandler next;
    private double cost;
    private String providerName;

    public Response handle(User user, Message message) {
        return null;
    }
}
