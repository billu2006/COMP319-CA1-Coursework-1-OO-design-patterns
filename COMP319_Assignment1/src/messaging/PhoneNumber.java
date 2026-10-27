package messaging;

public class PhoneNumber implements ContactDetail {

    private String number;

    public PhoneNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
}
