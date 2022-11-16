package api;


import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class HotelResource {

    public static HotelResource hotelResource = new HotelResource();
    private final ReservationService reservationService = ReservationService.getReservationService();
    private final CustomerService customerService = CustomerService.getCustomerService();

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void createACustomer(String  email,  String firstName, String lastName){
        customerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber){
        return ReservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
        Customer customer = getCustomer(customerEmail);
        return reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
    }
    public Collection<Reservation> getCustomersReservations(String customerEmail){
        Customer customer = getCustomer(customerEmail);
        if (customer == null){
            return Collections.emptyList();
        }
        return reservationService.getCustomersReservation(customer);
    }
    public Collection<IRoom> findARoom(Date checkIn, Date checkOut){
        return reservationService.findRooms(checkIn, checkOut);
    }
}
