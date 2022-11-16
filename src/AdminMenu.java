import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.*;


public class AdminMenu {


    private static AdminResource adminResource = AdminResource.getAdminResource();

    public static void menuOfAdmin() {
        System.out.println("\n" + "                  Admin Menu                  ");
        System.out.println("----------------------------------------------");
        System.out.println("1. See all Customers");
        System.out.println("2.  See all Rooms");
        System.out.println("3.  See all Reservations");
        System.out.println("4.  Add a Room");
        System.out.println("5.  Back to Main Menu");
        System.out.println("----------------------------------------------");
        System.out.println("Please select a number for the option");
        options();
    }

    public static void options() {
            Scanner scanner = new Scanner(System.in);
            int opt = scanner.nextInt();
            switch (opt) {
                case 1:
                    getAllCustomers();
                    break;
                case 2:
                    getAllRooms();
                    break;
                case 3:
                    getAllReservations();
                    break;
                case 4:
                    addARoom();
                    break;
                case 5:
                    MainMenu.mainMenu();
                    break;
                default:
                    System.out.println("Please enter a valid choice");
                    menuOfAdmin();
                    break;
            }
        }

    private static void getAllCustomers(){
        Collection<Customer> allCustomers =adminResource.getAllCustomers();
        if (!allCustomers.isEmpty()) {
            for (Customer customer : allCustomers) {
                System.out.println(customer);
            }
        } else {
            System.out.println("There are no customers");
        }
        menuOfAdmin();
    }

    private static void getAllRooms(){
        Collection<IRoom> allRoom =adminResource.getAllRooms();
        if (!allRoom.isEmpty()) {
            for (IRoom room : allRoom) {
                System.out.println(room);
            }
        } else {
            System.out.println("There are no rooms");
        }
        menuOfAdmin();
    }
    private static void getAllReservations(){
        adminResource.displayAllReservations();
        menuOfAdmin();
    }
    private static void addARoom(){
        Scanner scanner = new Scanner(System.in);
        String roomNumber;
        Double price;
        String option;
        RoomType type;
            System.out.println("Please enter Room number: ");
            roomNumber = scanner.nextLine();
            System.out.println("Please enter Room price: ");
            price = scanner.nextDouble();
            System.out.println("Please enter Room Type: 1 for single bed, 2 for double bed ");
            type = validateType(scanner);
            Room newRoom = new Room(roomNumber, price, type);
            adminResource.addRoom(Collections.singletonList(newRoom));
            System.out.println("Rooms are added!");
            System.out.println("Do you want to add another room? (y/n)");
            option = scanner.nextLine();
            do {
                if (option.equals("N") || option.equals("n")) {
                    menuOfAdmin();
                } else if (option.equals("Y") || option.equals("y")) {
                    addARoom();
                } else {
                    System.out.println("Please enter y (Yes) or n (No)");
                    option = scanner.nextLine();
                }
            }while (option != "n" || option != "N");
    }
    private static RoomType validateType(Scanner scanner){
        scanner.nextLine();
        try {
           return RoomType.validateType(scanner.nextLine());
        }
        catch (IllegalArgumentException e){
            System.out.println("Invalid room type");
            return validateType(scanner);
        }
    }



}

