package ui;

import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.*;

public class AdminMenu {

    private static final AdminResource adminResource = AdminResource.getInstance();
    private static final Scanner scanner = new Scanner(System.in);

    public static void displayAdminMenu() {
        String option;
        do {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1. See all Customers");
            System.out.println("2. See all Rooms");
            System.out.println("3. See all Reservations");
            System.out.println("4. Add a Room");
            System.out.println("5. Back to Main Menu");
            System.out.print("Please select a number for the menu option: ");

            option = scanner.nextLine();

            switch (option) {
                case "1":
                    seeAllCustomers();
                    break;
                case "2":
                    seeAllRooms();
                    break;
                case "3":
                    adminResource.displayAllReservations();
                    break;
                case "4":
                    addRoom();
                    break;
                case "5":
                    MainMenu.displayMainMenu();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (!option.equals("5"));
    }

    private static void seeAllCustomers() {
        Collection<Customer> customers = adminResource.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            customers.forEach(System.out::println);
        }
    }

    private static void seeAllRooms() {
        Collection<IRoom> rooms = adminResource.getAllRooms();
        if (rooms.isEmpty()) {
            System.out.println("No rooms found.");
        } else {
            rooms.forEach(System.out::println);
        }
    }

    private static void addRoom() {
        List<IRoom> newRooms = new ArrayList<>();
        String addMore;
        do {
            System.out.print("Enter room number: ");
            String roomNumber = scanner.nextLine();

            System.out.print("Enter room price per night: ");
            double price = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter room type: 1 for SINGLE, 2 for DOUBLE: ");
            RoomType roomType = scanner.nextLine().equals("1") ? RoomType.SINGLE : RoomType.DOUBLE;

            IRoom room = new Room(roomNumber, price, roomType);
            newRooms.add(room);

            System.out.print("Would you like to add another room? (y/n): ");
            addMore = scanner.nextLine();

        } while (addMore.equalsIgnoreCase("y"));

        adminResource.addRoom(newRooms);
        System.out.println("Rooms added successfully!");
    }
}
