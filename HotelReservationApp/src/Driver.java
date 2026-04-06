package model;

public class Driver {
    public static void main(String[] args) {
        // Valid email
        Customer validCustomer = new Customer("Rashi", "K", "rashi@example.com");
        System.out.println(validCustomer);

        // Invalid email - test
        try {
            Customer invalidCustomer = new Customer("John", "Doe", "john.doeexample.com");
            System.out.println(invalidCustomer);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Program finished running successfully!");
    }
}
