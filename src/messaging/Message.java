package messaging;

public class Message {

    private String text;
    private Urgency urgency;

    public Message(String text, Urgency urgency) {
        this.text = text;
        this.urgency = urgency;
    }

    public String getText() {
        return text;
    }

    public Urgency getUrgency() {
        return urgency;
    }
}
