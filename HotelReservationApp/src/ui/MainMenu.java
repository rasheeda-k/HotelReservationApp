package ui;

import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainMenu {

    private static final HotelResource hotelResource = HotelResource.getInstance();
    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {

        displayMainMenu();
    }

    public static void displayMainMenu() {
        String option;
        do {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Find and reserve a room");
            System.out.println("2. See my reservations");
            System.out.println("3. Create an account");
            System.out.println("4. Admin");
            System.out.println("5. Exit");
            System.out.print("Please select a number for the menu option: ");

            option = scanner.nextLine();

            switch (option) {
                case "1":
                    findAndReserveRoom();
                    break;
                case "2":
                    seeMyReservations();
                    break;
                case "3":
                    createAccount();
                    break;
                case "4":
                    AdminMenu.displayAdminMenu();
                    break;
                case "5":
                    System.out.println("Exiting application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid input. Please select a valid option.");
            }
        } while (!option.equals("5"));
    }

    private static void findAndReserveRoom() {
        try {
            System.out.print("Enter Check-In Date (dd/MM/yyyy): ");
            Date checkIn = dateFormat.parse(scanner.nextLine());

            System.out.print("Enter Check-Out Date (dd/MM/yyyy): ");
            Date checkOut = dateFormat.parse(scanner.nextLine());

            Collection<IRoom> availableRooms = hotelResource.findARoom(checkIn, checkOut);

            if (availableRooms.isEmpty()) {
                System.out.println("No rooms available for these dates.");
                return;
            }

            System.out.println("Available Rooms:");
            for (IRoom room : availableRooms) {
                System.out.println(room);
            }

            System.out.print("Enter your email (name@domain.com): ");
            String email = scanner.nextLine();

            Customer customer = hotelResource.getCustomer(email);
            if (customer == null) {
                System.out.println("No account found. Please create an account first.");
                return;
            }

            System.out.print("Enter room number you want to reserve: ");
            String roomNumber = scanner.nextLine();

            IRoom room = hotelResource.getRoom(roomNumber);
            if (room == null) {
                System.out.println("Invalid room number.");
                return;
            }

            Reservation reservation = hotelResource.bookARoom(email, room, checkIn, checkOut);
            System.out.println("Reservation created successfully:\n" + reservation);

        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use dd/MM/yyyy.");
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    private static void seeMyReservations() {
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        Collection<Reservation> reservations = hotelResource.getCustomersReservations(email);

        if (reservations == null || reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter Email (name@domain.com): ");
        String email = scanner.nextLine();

        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        try {
            hotelResource.createACustomer(email, firstName, lastName);
            System.out.println("Account created successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid email format. Please try again.");
        }
    }
}


