package model;

public class Room implements IRoom {

    private String roomNumber;
    private Double price;
    private RoomType roomType;

    public Room(String roomNumber, Double price, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
    }

    public Room(String roomNumber, double price, RoomType roomType) {
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public boolean isFree() {
        return price == 0.0;
    }

    // toString()
    @Override
    public String toString() {
        String displayPrice = (price == 0.0) ? "Free" : "$" + price;
        return "Room Number: " + roomNumber + ", Type: " + roomType + ", Price: " + displayPrice;
    }
}
