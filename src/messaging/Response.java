package messaging;

public class Response {

    private ResponseCode code;
    private String text;
    private double cost;

    public Response(ResponseCode code, String text, double cost) {
        this.code = code;
        this.text = text;
        this.cost = cost;
    }

    public ResponseCode getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public double getCost() {
        return cost;
    }
}
