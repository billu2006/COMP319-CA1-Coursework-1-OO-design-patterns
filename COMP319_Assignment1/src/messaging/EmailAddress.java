package messaging;

public class EmailAddress implements ContactDetail {

    private String address;

    public EmailAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
