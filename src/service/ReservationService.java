package service;

import model.*;

import java.util.*;
import java.util.stream.Collectors;


public class ReservationService {
    private static final Map<String, IRoom> rooms = new HashMap<>();
    private Map <String, Collection<Reservation>> reservations = new HashMap<>();
    private static ReservationService reservationService = new ReservationService();

    private ReservationService(){

    }
    public static ReservationService getReservationService(){
        return reservationService;
    }

    public Collection<IRoom> getAllRooms(){
        return rooms.values();
    }

    public void addRoom(IRoom room) {
        rooms.put(room.getRoomNumber(), room);
    }

    public static IRoom getARoom(String roomId) {
        return rooms.get(roomId);
    }



    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        Collection<Reservation> customersReservation = getCustomersReservation(customer);
        if(customersReservation == null){
            customersReservation = new LinkedList<>();
        }
        customersReservation.add(reservation);
        reservations.put(customer.getemail(),customersReservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        Collection<Reservation> allReservation = getAllReservation();
        Collection<IRoom> noRoom = new LinkedList<>();
        for (Reservation reservation : allReservation){
            if(checkInDate.before(reservation.getCheckOutDate())&&checkOutDate.after(reservation.getCheckInDate())){
                noRoom.add(reservation.getRoom());
            }
        }
        return rooms.values().stream().filter(room -> noRoom.stream().noneMatch(unAvailable -> unAvailable.equals(room))).collect(Collectors.toList());
    }

    public  Collection<Reservation> getCustomersReservation(Customer customer) {
       return reservations.get(customer.getemail());
    }

    public void printAllReservation(){
        Collection<Reservation> allReservation = getAllReservation();
        if(allReservation.isEmpty()){
            System.out.println("There is no reservation in the system");
        }
        else {
            for (Reservation reservation : allReservation){
                System.out.println(reservation + "\n");
            }
        }
    }

    public Collection<Reservation> getAllReservation() {
        Collection<Reservation> allReservation = new LinkedList<>();
        for (Collection<Reservation> reservation : reservations.values()){
            allReservation.addAll(reservation);
        }
        return allReservation;
    }



}