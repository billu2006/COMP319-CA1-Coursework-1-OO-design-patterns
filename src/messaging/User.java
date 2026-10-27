package messaging;

public class User {

    private String name;
    private PhoneNumber phone;
    private EmailAddress email;
    private PostalAddress address;

    public User(String name, PhoneNumber phone, EmailAddress email, PostalAddress address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public PhoneNumber getPhone() {
        return phone;
    }

    public EmailAddress getEmail() {
        return email;
    }

    public PostalAddress getAddress() {
        return address;
    }
}
