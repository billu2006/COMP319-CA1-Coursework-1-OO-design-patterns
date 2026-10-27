package messaging;

public class MessagingService {

    private final ChainFactory chainFactory;

    public MessagingService() {
        this(new ChainFactory());
    }

    public MessagingService(ChainFactory chainFactory) {
        this.chainFactory = chainFactory;
    }

    public Response send(User user, Message message) {
        if (user == null) {
            return new Response(ResponseCode.INVALID_INPUT, "No user supplied", 0);
        }
        if (message == null) {
            return new Response(ResponseCode.INVALID_INPUT, "No message supplied", 0);
        }
        if (message.getText() == null || message.getText().isBlank()) {
            return new Response(ResponseCode.INVALID_INPUT, "Message text is empty", 0);
        }
        if (message.getUrgency() == null) {
            return new Response(ResponseCode.INVALID_INPUT, "Message has no urgency", 0);
        }

        MessageHandler chain = chainFactory.createChain(message.getUrgency());
        return chain.handle(user, message);
    }
}
