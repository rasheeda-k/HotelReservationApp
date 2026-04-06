package model;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Reservation {

    private Customer customer;
    private IRoom room;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return "Reservation Details:\n" + "Customer: " + customer.getFirstName() + " " + customer.getLastName() + "\n"
                + "Room: " + room.getRoomNumber() + " (" + room.getRoomType() + ")\n" + "Price: "
                + (room.isFree() ? "Free" : "$" + room.getRoomPrice()) + "\n" + "Check-in Date: "
                + dateFormat.format(checkInDate) + "\n" + "Check-out Date: " + dateFormat.format(checkOutDate) + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Reservation other = (Reservation) obj;
        return customer.equals(other.customer) && room.equals(other.room) && checkInDate.equals(other.checkInDate)
                && checkOutDate.equals(other.checkOutDate);
    }

    @Override
    public int hashCode() {
        return (customer.getEmail() + room.getRoomNumber() + checkInDate.toString() + checkOutDate.toString())
                .hashCode();
    }
}
