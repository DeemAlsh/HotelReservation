# Hotel Reservation - A Java project for hotel reservation management
This is a Project of [Udacity]([https://www.java.com/](https://www.udacity.com/)) Java Programming course. The project's objective is to build a "Hotel Reservation Application", covering the fundamentals of the Java programming language.
## Project Requirements:

### User Scenarios
- **Creating a customer account:** The user needs to first create a customer account before they can create a reservation.
- **Searching for rooms:** The app should allow the user to search for available rooms based on provided checkin and checkout dates. If the application has available rooms for the specified date range, a list of the corresponding rooms will be displayed to the user for choosing.
- **Booking a room:** Once the user has chosen a room, the app will allow them to book the room and create a reservation.
- **Viewing reservations:** After booking a room, the app allows customers to view a list of all their reservations.

### Admin Scenarios
- **Displaying all customers accounts.**
- **Viewing all of the rooms in the hotel.**
- **Viewing all of the hotel reservations.**
- **Adding a room to the hotel application.**

## Reserving a Room
The application allows customers to reserve a room. Here are the specifics:

1. <b>Avoid conflicting reservations</b>: A single room may only be reserved by a single customer per a checkin and checkout date range.
2. <b>Search for recommended rooms</b>: If there are no available rooms for the customer's date range, a search will be performed that displays recommended rooms on alternative dates. The recommended room search will add seven days to the original checkin and checkout dates to see if the hotel has any availabilities, and then display the recommended rooms/dates to the customer.
> Example: If the customers date range search is 1/1/2020 – 1/5/2020 and all rooms are booked, the system will search again for recommended rooms using the date range 1/8/2020 - 1/12/2020. If there are no recommended rooms, the system will not return any rooms.

### Room Requirements
<b>Room cost</b>: Rooms will contain a price per night. When displaying rooms, paid rooms will display the price per night and free rooms will display "Free" or have a $0 price.
<b>Unique room numbers</b>: Each room will have a unique room number, meaning that no two rooms can have the same room number.
<b>Room type</b>: Rooms can be either single occupant or double occupant (Enumeration: SINGLE, DOUBLE).

### Customer Requirements
The application will have customer accounts. Each account has:

1. <b>A unique email for the customer</b>: RegEx is used to check that the email is in the correct format (i.e., name@domain.com).
2. <b>A first name and last name</b>.
3. The email RegEx is simple for the purpose of this exercise and may not cover all real-world valid emails. 
> For example "name@domain.com" would not be accepted by the above RegEx because it does end with ".com". If you would like to try to make your RegEx more sophisticated, you may—but it is not required for this project.

### Error Requirements
The hotel reservation application handles all exceptions gracefully (user inputs included), meaning:

1. <b>No crashing</b>: The application does not crash based on user input.
2. <b>No unhandled exceptions</b>: The app has try and catch blocks that are used to capture exceptions and provide useful information to the user. There are no unhandled exceptions.


## Built with
- [Java](https://www.java.com/) - The programming language used

