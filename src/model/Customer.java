package model;

import java.util.regex.Pattern;

public class Customer {
   private final String firstName;
    private final String lastName;
    private final String email;

    public Customer(String firstName, String lastName, String email){
        if (!Pattern.compile("^(.+)@(.+)$").matcher(email).matches()) {
            throw new IllegalArgumentException("Please enter a valid email address.");
        }
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
    }

    public String getFirstName(){
        return firstName;
    }
    public String getlastName(){
        return lastName;
    }
    public String getemail(){
        return email;
    }

    public String toString() {
        return "Firstname: " + firstName + ", Lastname: " + lastName + ", Email: " + email;
    }
}
