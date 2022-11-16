
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Scanner;

import static api.HotelResource.hotelResource;

public class MainMenu {
    public static void mainMenu() {
        System.out.println("\n" + "                  Main Menu                  ");
        System.out.println("----------------------------------------------");
        System.out.println("1.  Find and reserve a room");
        System.out.println("2.  See my reservations");
        System.out.println("3.  Create an account");
        System.out.println("4.  Admin");
        System.out.println("5.  Exit");
        System.out.println("----------------------------------------------");
        System.out.println("Please select a number for the option");
        options();
    }

    public static void options() {
        try {
            Scanner scanner = new Scanner(System.in);
            int opt = scanner.nextInt();
            switch (opt) {
                case 1:
                    findAndReserveRoom();
                    break;
                case 2:
                    customerReservations();
                    break;
                case 3:
                    create();
                    break;
                case 4:
                    AdminMenu.menuOfAdmin();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter a valid choice");
                    mainMenu();
                    break;
            }
        }catch (InputMismatchException e){
            System.out.println("Please enter a valid choice");
            mainMenu();
        }
    }

    private static  void findAndReserveRoom(){
        final Scanner scanner = new Scanner(System.in);
        System.out.println(" Please enter checkIn date mm/dd/yyyy ex: 02/01/2020");
        Date checkIn = validateDate(scanner);
        System.out.println(" Please enter checkIn date month/day/year ex: 2/21/2020");
        Date checkOut = validateDate(scanner);
        if (checkIn != null && checkOut != null){
            Collection <IRoom> rooms = hotelResource.findARoom(checkIn, checkOut);
            if(rooms.isEmpty()){
                System.out.println("There is no room");
            }else {
                for(IRoom room : rooms){
                    System.out.println(room);
                }
                System.out.println("Would you like to book a room? y/n");
                String book = scanner.nextLine();
                if(book.equals("Y")||book.equals("y")){
                    System.out.println("Do you hava an account with us? y/n");
                    String account = scanner.nextLine();
                    if(account.equals("Y")||account.equals("y")){
                        System.out.println("Please enter your email:");
                        String email = scanner.nextLine();
                        Customer customer = hotelResource.getCustomer(email);
                        if(customer == null){
                            System.out.println("There is no customer with this email");
                        }else{
                            System.out.println("What room number would you like to reserve");
                            String num = scanner.nextLine();
                            if(rooms.stream().anyMatch(room -> room.getRoomNumber().equals(num))){
                               IRoom room = hotelResource.getRoom(num);
                               Reservation reservation = hotelResource.bookARoom(email , room , checkIn, checkOut);
                               System.out.println("successfully!");
                                System.out.println(reservation);
                            }else {
                                System.out.println("There is no room with this number");
                            }
                        }
                    }else {
                        System.out.println("Please create an account");
                    } mainMenu();
                } else if (book.equals("N")||book.equals("n")){
                    mainMenu();
                }else{
                    System.out.println("Please enter valid choice, start reservation again");
                    findAndReserveRoom();
                }
            }
        }
    }

    private static Date validateDate (Scanner scanner){
        try{
            return new SimpleDateFormat("MM/dd/yyyy").parse(scanner.nextLine());
        }catch (ParseException ex){
            System.out.println("invalid Date");
            findAndReserveRoom();
        }
        return null;
    }

    private static void customerReservations(){
        final Scanner scanner = new Scanner(System.in);
        System.out.println(" Please enter your email which has this format: name@domain.com");
        final String customerEmail = scanner.nextLine();
        printReservations(hotelResource.getCustomersReservations(customerEmail));

    }

    private static void printReservations(Collection<Reservation> customersReservations) {
        if(customersReservations == null || customersReservations.isEmpty()){
            System.out.println(" Reservations not found");
        }else {
            for(Reservation reservation : customersReservations){
                System.out.println(reservation);
            }
        }
        mainMenu();
    }

    private static void create(){
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your email which has this format: name@domain.com");
        String email = scanner.nextLine();
        System.out.println("Enter first name:");
        String first = scanner.nextLine();
        System.out.println("Enter last name:");
        String last = scanner.nextLine();
        try {
            hotelResource.createACustomer(email, first, last);
            System.out.println("successfully!");
            mainMenu();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            create();
        }
    }


}
