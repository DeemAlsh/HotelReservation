package service;

import model.Customer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {

    private  Map<String, Customer> MapOfCustomer = new HashMap<>();
    private static CustomerService customerService = new CustomerService();

    private CustomerService(){
    }
    public static CustomerService getCustomerService(){
        return customerService;
    }
    public  void addCustomer(String email, String firstName, String lastName) {
        Customer recentCustomer = new Customer(firstName, lastName, email);
        MapOfCustomer.put(email, recentCustomer);
    }

    public  Customer getCustomer(String customerEmail) {
        return MapOfCustomer.get(customerEmail);
    }

    public  Collection<Customer> getAllCustomers() {
        return MapOfCustomer.values();
    }
}