package main;

import messaging.EmailAddress;
import messaging.Message;
import messaging.MessagingService;
import messaging.PhoneNumber;
import messaging.PostalAddress;
import messaging.Response;
import messaging.Urgency;
import messaging.User;

public class Main {

    private static final int REPEAT_COUNT = 5; //the amount of times that Test 9 resends the same message

    private static final MessagingService service = new MessagingService(); // only route to the messaging system
    private static int testNumber = 0; // lets tests name themselves

    public static void main(String[] args) {
        // test data with all feilds filled out correctly, test data was built using Claude.
        User valid = new User("Alice Smith",
                new PhoneNumber("07700900123"),
                new EmailAddress("alice@example.com"),
                new PostalAddress("1 High Street", "Liverpool", "L69 3BX"));

        Message urgent = new Message("Server is down", Urgency.URGENT);
        Message normal = new Message("Your order has shipped", Urgency.NORMAL);
        Message low = new Message("Monthly newsletter", Urgency.LOW);

        //user is sent 3 messages
        heading("valid user, URGENT message");
        send(valid, urgent);

        heading("valid user, NORMAL message");
        send(valid, normal);

        heading("valid user, LOW message");
        send(valid, low);

        heading("invalid email");

        //validation, each test builds a user with one bad detail and throws an error
        try {
            User user = new User("Bob Jones", null, new EmailAddress("bob.at.example"), null);
            send(user, normal);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        heading("invalid phone number");
        try {
            User user = new User("Carol White", new PhoneNumber("12ab"), null, null);
            send(user, urgent);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        heading("invalid postcode");
        try {
            User user = new User("Dan Brown", null, null,
                    new PostalAddress("2 Low Road", "Liverpool", "NOT A POSTCODE"));
            send(user, low);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        // null is allowed but invalid details are rejected
        heading("missing phone, URGENT message");
        User noPhone = new User("Eve Green", null,
                new EmailAddress("eve@example.com"),
                new PostalAddress("3 Park Lane", "Liverpool", "L1 8JQ"));
        send(noPhone, urgent);

        heading("postal address only, NORMAL message");
        User postOnly = new User("Frank Hall", null, null,
                new PostalAddress("4 Church Street", "Liverpool", "L1 3AY"));
        send(postOnly, normal);

        //repeated sends
        heading("same message sent " + REPEAT_COUNT + " times (shows fall-through on random failures)");
        for (int i = 1; i <= REPEAT_COUNT; i++) {
            System.out.print("Attempt " + i + ": ");
            send(valid, normal);
        }

        heading("no contact details at all (every provider fails or can't apply)");
        User noContact = new User("Grace Lee", null, null, null);
        send(noContact, urgent);

        heading("missing user");
        send(null, normal);

        heading("missing message");
        send(valid, null);

        heading("empty message text");
        send(valid, new Message("   ", Urgency.NORMAL));
    }
    // helper methods
    private static void heading(String title) {
        testNumber++;
        System.out.println();
        System.out.println("--- Test " + testNumber + ": " + title + " ---");
    }

    private static void send(User user, Message message) {
        Response response = service.send(user, message);
        System.out.printf("%s | %s | cost: %.2f%n",
                response.getCode(), response.getText(), response.getCost());
    }
}
