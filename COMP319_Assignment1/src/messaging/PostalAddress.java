package messaging;

public class PostalAddress implements ContactDetail {

    private String street;
    private String town;
    private String postcode;

    public PostalAddress(String street, String town, String postcode) {
        this.street = street;
        this.town = town;
        this.postcode = postcode;
    }

    public String getStreet() {
        return street;
    }

    public String getTown() {
        return town;
    }

    public String getPostcode() {
        return postcode;
    }
}
