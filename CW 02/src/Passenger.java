import com.mongodb.MongoClient;
import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Passenger {


    public static List<String> passengerNames = new ArrayList<>();
    public static List<Integer> passengerSeats = new ArrayList<>();

    private String Name;
    private int secondsInQueue;
    private int seatNumber;
    int die1, die2, die3;



    public void loadData(String[]customerName, String[]seatBooking, String[]seatBookingAndCustomerName) {

        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("customerDetails");

        MongoCollection<Document> collection = database.getCollection("collection");
        System.out.println("Collection customerDetails selected successfully!");

        Iterator<Document> cursor = collection.find().iterator();

        while (cursor.hasNext()) {
            Document document = cursor.next();
            System.out.println(document);
            String customer_name = (String) document.get("CustomerName");
            Integer seat_number = (Integer) document.get("seatID");
            customerName[seat_number] = customer_name;

            passengerNames.add(customer_name);
            passengerSeats.add(seat_number);
        }
    }


    public List<String> getName(String[]customerName, String[]seatBooking, String[]seatBookingAndCustomerName) {

        Passenger n = new Passenger();
        n.loadData(customerName,seatBooking,seatBookingAndCustomerName);

        return passengerNames;
    }
    public void setName(final String name) {

        this.Name = name;
    }


    public int getSecondsInQueue() {

        die1 = (int) (Math.random() * 6) + 1;
        die2 = (int) (Math.random() * 6) + 1;
        die3 = (int) (Math.random() * 6) + 1;
        secondsInQueue = die1 + die2 + die3;
        return secondsInQueue;
    }
    public void setSecondsInQueue(int secondsInQueue) {
        this.secondsInQueue = secondsInQueue;
    }


    public List<Integer> getSeatNumber(String[] customerName, String[] seatBooking, String[] seatBookingAndCustomerName) {

        Passenger s = new Passenger();
        s.loadData(customerName,seatBooking,seatBookingAndCustomerName);
        return passengerSeats;
    }
    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }


}












