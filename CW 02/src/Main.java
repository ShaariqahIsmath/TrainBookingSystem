
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.bson.Document;

import java.util.*;

public class Main extends Application {

    static final int SEATING_CAPACITY = 42;
    Stage window;
    Scene scene1;
    String[] customerName = new String[SEATING_CAPACITY + 1];
    Scene scene2;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;

        String[] seatBooking = new String[SEATING_CAPACITY + 1];
        String[] seatBookingAndCustomerName = new String[SEATING_CAPACITY + 1];

        for (int s = 1; s < (SEATING_CAPACITY + 1); s++){
            seatBooking[s] = "Empty";
            customerName[s] = "";
            seatBookingAndCustomerName[s] = "null";
        }
        displayMenu(seatBooking,customerName,seatBookingAndCustomerName);
    }

    private void displayMenu(String[] seatBooking, String[] customerName, String[] seatBookingAndCustomerName) {
        System.out.println("Enter \"A\" to add customer to a seat"); //collects customer details and store them
        System.out.println("Enter \"V\" to view all seats"); //is to view all the 42 seats available
        System.out.println("Enter \"E\" to display empty seats");//displays all 42 seats, shows the unbooked seats in green and the booked seats are set to be invisible
        System.out.println("Enter \"D\" to delete a booked seat");//check the customer name that has been inputted and check with the customer name array.
        System.out.println("Enter \"F\" to find a seat by customer name");//check the customer name that has been inputted and check with the customer name array.
        System.out.println("Enter \"S\" to store program data into a file"); //customer details are stored.
        System.out.println("Enter \"L\" to load program data from file");//customer details are retrieved when necessary
        System.out.println("Enter \"O\" to view seats ordered alphabetically by name");// set the the customer names stored in the customerName array, in an alphabetical order.
        System.out.println("Enter \"Q\" to quit");//helps exit the program

        Scanner input = new Scanner(System.in);
        System.out.println("Select an option: ");  //prompts user to choose an option.
        String option = input.nextLine();

        switch (option) {
            case "A":
            case "a":
                addCustomerToSeat(seatBooking, customerName, seatBookingAndCustomerName);
                break;
            case "V":
            case "v":
                viewAllSeats(seatBooking, customerName, seatBookingAndCustomerName);
                break;
            case "E":
            case "e":
                viewEmptySeats(seatBooking, customerName, seatBookingAndCustomerName);
                break;
            case "D":
            case "d":
                deleteBookedSeats(seatBooking, customerName, seatBookingAndCustomerName);
                break;
            case "F":
            case "f":
                findCustomerSeats(seatBooking, customerName, seatBookingAndCustomerName);
                break;
            case "O":
            case "o":
                viewOrderedSeats(seatBooking, customerName, seatBookingAndCustomerName);
                break;
            case "S":
            case "s":
                storeDataInFile(seatBooking, customerName, seatBookingAndCustomerName);
                break;
            case "L":
            case "l":
                loadDataFromFile(seatBooking, customerName, seatBookingAndCustomerName);
                break;
            case "Q":
            case "q":
                System.exit(0);
                break;
            default:
                System.out.println("You have entered an invalid option.");
                System.out.println("Please enter a correct option from (A,V,E,D,F,S,L,O,Q) only..");
                displayMenu(seatBooking, customerName, seatBookingAndCustomerName);

        }
    }

    private void addCustomerToSeat(String[] seatBooking, String[] customerName, String[] seatBookingAndCustomerName) {
        Button[] seat = new Button[(SEATING_CAPACITY + 1)];

        Button selectSeat = new Button(" Click to Book Seats ");
        selectSeat.setLayoutX(320);
        selectSeat.setLayoutY(512);
        selectSeat.setOnAction(event -> {
            window.setScene(scene2);
        });

        Label header = new Label("TRAIN BOOKING SYSTEM");
        header.setLayoutX(250);
        header.setLayoutY(30);
        header.setStyle("-fx-font-size: 25px;");

        Label clientName = new Label("Enter Customer Name: ");
        clientName.setLayoutX(225);
        clientName.setLayoutY(150);
        clientName.setStyle("-fx-font-size: 16px;");
        TextField cusName = new TextField();
        cusName.setLayoutX(397);
        cusName.setLayoutY(150);



        Label destination = new Label("Choose destination: ");
        destination.setLayoutX(225);
        destination.setLayoutY(200);
        destination.setStyle("-fx-font-size:16px;");

        String [] destinations = {"Colombo to Badulla", "Badulla to Colombo"};
        ComboBox Destination = new ComboBox(FXCollections.observableArrayList(destinations));
        Destination.setLayoutX(397);
        Destination.setLayoutY(200);


        AnchorPane layout1 = new AnchorPane();
        layout1.setStyle("-fx-background-color:#5a89a3; ");
        layout1.getChildren().addAll(Destination,destination,selectSeat,clientName,cusName,header);


        scene1 = new Scene(layout1,800,600);
        window.setTitle("Train Booking System");
        window.setScene(scene1);
        window.show();



        Label header1 = new Label("TRAIN BOOKING SYSTEM");
        header1.setLayoutX(250);
        header1.setLayoutY(30);
        header1.setStyle("-fx-font-size: 25px;");

        Button submit = new Button("Submit");
        submit.setLayoutX(640);
        submit.setLayoutY(480);
        Button exit = new Button("Exit");
        exit.setLayoutX(710);
        exit.setLayoutY(480);
        exit.setOnAction(event -> {
            window.close();
            displayMenu(seatBooking,customerName,seatBookingAndCustomerName);
        });

        Label greenSeat = new Label("Unbooked Seat");
        greenSeat.setLayoutY(160);
        greenSeat.setLayoutX(590);
        greenSeat.setStyle("-fx-font-size:14px;");
        Button unbooked = new Button("   ");
        unbooked.setLayoutY(160);
        unbooked.setLayoutX(560);
        unbooked.setStyle("-fx-background-color:green;");

        Label redSeat = new Label("Booked Seat");
        redSeat.setLayoutX(590);
        redSeat.setLayoutY(200);
        redSeat.setStyle("-fx-font-size:14px;");
        Button booked = new Button("   ");
        booked.setLayoutX(560);
        booked.setLayoutY(200);
        booked.setStyle("-fx-background-color:red;");


        GridPane gridPane = new GridPane();
        int columnIndex = 0;
        int rowIndex = 0;
        int rowIndexes = 0;

        List<Integer> reservedSeats = new ArrayList<>();

        String seatNumber;
        for (int i = 1; i < (SEATING_CAPACITY + 1); i++) {
            if (i <= 9) {
                seatNumber = "0" + (i);
            } else {
                seatNumber = "" + (i);
            }
            seat[i] = new Button(seatNumber);
            gridPane.add(seat[i], columnIndex, rowIndex);
            columnIndex++;
            rowIndexes++;

            if (rowIndexes == 4) {
                columnIndex = 0;
                rowIndexes = 0;
                rowIndex++;
            }
        }
        for (int f = 1; f < (SEATING_CAPACITY + 1); f++) {
            if (seatBooking[f].equals("Empty")) {
                seat[f].setStyle("-fx-background-color: green;");
            }
            if (seatBooking[f] != "Empty") {
                seat[f].setStyle("-fx-background-color: red");
            }
        }

        List<Integer> bookedCurrent = new ArrayList<>();
        for (int f = 1; f < (SEATING_CAPACITY + 1); f++) {
            int finalF = f;
            seat[f].setOnAction(event -> {
                seat[finalF].setStyle("-fx-background-color: red");
                seatBooking[finalF] = "booked";
                reservedSeats.add(finalF);
                bookedCurrent.add(finalF);
            });

            submit.setOnAction(event -> {
                String personName = (cusName.getText().toLowerCase());
                window.close();

                for (int index : reservedSeats){
                    seatBooking[index] = "Booked";
                    customerName[index] = personName;
                }
                displayMenu(seatBooking, customerName, seatBookingAndCustomerName);
            });
        }

        gridPane.setLayoutX(160);
        gridPane.setLayoutY(80);
        gridPane.setHgap(20);
        gridPane.setVgap(5);


        AnchorPane layout2 = new AnchorPane();
        layout2.setStyle("-fx-background-color:#5a89a3; ");
        layout2.getChildren().addAll(gridPane,submit,exit,header1,greenSeat,unbooked,redSeat,booked);

        scene2 = new Scene(layout2,800,600);
        window.setTitle("Train Booking System");
        window.show();
        window.setOnCloseRequest(event -> {
            window.close();
            displayMenu(seatBooking,customerName,seatBookingAndCustomerName);
        });

    }

    private void viewAllSeats(String[] seatBooking, String[] customerName, String[] seatBookingAndCustomerName) {
        Button[] seat = new Button[SEATING_CAPACITY + 1];

        Button exit = new Button("    Exit    ");
        exit.setLayoutX(600);
        exit.setLayoutY(480);
        exit.setOnAction(event -> {
            window.close();
            displayMenu(seatBooking,customerName,seatBookingAndCustomerName);
        });

        Label greenSeat = new Label("Unbooked Seat");
        greenSeat.setLayoutY(280);
        greenSeat.setLayoutX(590);
        greenSeat.setStyle("-fx-font-size:14px;");
        Button unbooked = new Button("  ");
        unbooked.setLayoutY(280);
        unbooked.setLayoutX(560);
        unbooked.setStyle("-fx-background-color:green;");

        Label redSeat = new Label("Booked Seat");
        redSeat.setLayoutX(590);
        redSeat.setLayoutY(330);
        redSeat.setStyle("-fx-font-size:14px;");
        Button booked = new Button("  ");
        booked.setLayoutX(560);
        booked.setLayoutY(330);
        booked.setStyle("-fx-background-color:red;");

        Label header1 = new Label("TRAIN BOOKING SYSTEM");
        header1.setLayoutX(250);
        header1.setLayoutY(30);
        header1.setStyle("-fx-font-size: 25px;");

        GridPane gridPane = new GridPane(); //create a gridpane, to organize all 42 of the seats .
        int columnIndex = 0;
        int rowIndex = 0;
        int rowIndexes = 0;

        String seatNumber;
        for (int i = 1; i < (SEATING_CAPACITY + 1); i++) {
            if (i <= 9) {
                seatNumber = "0" + i;
            } else {
                seatNumber = "" + i;
            }
            seat[i] = new Button(seatNumber);
            gridPane.add(seat[i], columnIndex, rowIndex);
            columnIndex++;
            rowIndexes++;

            if (rowIndexes == 4) {
                columnIndex = 0;
                rowIndexes = 0;
                rowIndex++;
            }
        }

        for (int j = 1; j < (SEATING_CAPACITY + 1); j++) {
            if (seatBooking[j] != "Empty") {
                seat[j].setStyle("-fx-background-color:red");
            } else {
                seat[j].setStyle("-fx-background-color:green");
            }
        }

        gridPane.setLayoutX(160);
        gridPane.setLayoutY(80);
        gridPane.setHgap(20);
        gridPane.setVgap(5);

        AnchorPane layout2 = new AnchorPane();
        layout2.setStyle("-fx-background-color:#5a89a3; ");
        layout2.getChildren().addAll(gridPane,header1,greenSeat,unbooked,redSeat,booked,exit);

        scene2 = new Scene(layout2,800,600);
        window.setTitle("Train Booking System");
        window.setScene(scene2);
        window.show();
        window.setOnCloseRequest(event -> {
            window.close();
            displayMenu(seatBooking,customerName,seatBookingAndCustomerName);
        });
    }

    private void viewEmptySeats(String[] seatBooking, String[] customerName, String[] seatBookingAndCustomerName) {
        Button[]seat = new Button[SEATING_CAPACITY + 1];
        Button exit = new Button("    Exit    ");
        exit.setLayoutX(600);
        exit.setLayoutY(480);
        exit.setOnAction(event -> {
            window.close();
            displayMenu(seatBooking,customerName,seatBookingAndCustomerName);
        });

        Label greenSeat = new Label("Unbooked Seat");
        greenSeat.setLayoutY(280);
        greenSeat.setLayoutX(590);
        greenSeat.setStyle("-fx-font-size:14px;");
        Button unbooked = new Button("  ");
        unbooked.setLayoutY(280);
        unbooked.setLayoutX(560);
        unbooked.setStyle("-fx-background-color:green;");

        Label redSeat = new Label("Booked Seat");
        redSeat.setLayoutX(590);
        redSeat.setLayoutY(330);
        redSeat.setStyle("-fx-font-size:14px;");
        Button booked = new Button("  ");
        booked.setLayoutX(560);
        booked.setLayoutY(330);
        booked.setStyle("-fx-background-color:red;");

        Label header1 = new Label("TRAIN BOOKING SYSTEM");
        header1.setLayoutX(250);
        header1.setLayoutY(30);
        header1.setStyle("-fx-font-size: 25px;");

        GridPane gridPane = new GridPane();
        int columnIndex = 0;
        int rowIndex = 0;
        int rowIndexes = 0;//number of seats you need in a row

        String seatNumber; //initializing a string for seat numbers so all seats would be numbered.
        for (int i = 1; i < (SEATING_CAPACITY + 1); i++) {
            if (i <= 9) {
                seatNumber = "0" + i;
            } else {
                seatNumber = "" + i;
            }
            seat[i] = new Button(seatNumber);
            gridPane.add(seat[i], columnIndex, rowIndex);
            columnIndex++;
            rowIndexes++;

            if (rowIndexes == 4) {
                columnIndex = 0;
                rowIndexes = 0;
                rowIndex++;
            }
        }

        boolean z = false;

        for (int j = 1; j < (SEATING_CAPACITY + 1); j++) {
            if (seatBooking[j] != "Empty") {
                seat[j].setVisible(z);
            }if(seatBooking[j].equals("Empty")) {
                seat[j].setStyle("-fx-background-color:green");
            }
        }

        gridPane.setLayoutX(250);
        gridPane.setLayoutY(150);
        gridPane.setHgap(23);
        gridPane.setVgap(6);

        AnchorPane layout2 = new AnchorPane();
        layout2.setStyle("-fx-background-color:#5a89a3; "); //setting a background colour to the GUI
        layout2.getChildren().addAll(gridPane,header1,greenSeat,unbooked,redSeat,booked, exit);

        scene2 = new Scene(layout2, 800, 600);
        window.setTitle("Train Booking System");
        window.setScene(scene2);
        window.show();
        window.setOnCloseRequest(event ->{
            window.close();
            displayMenu(seatBooking, customerName,seatBookingAndCustomerName);
        });


    }

    private void deleteBookedSeats(String[] seatBooking, String[] customerName, String[] seatBookingAndCustomerName) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter customer name: ");
        String name = input.nextLine();
        boolean flag = false;

        for (int f = 1; f < SEATING_CAPACITY + 1; f++){
            if (name.toLowerCase().equals(customerName[f])){
                customerName[f] = "";
                seatBooking[f] = "Empty";
                flag = true;
            }
        }
        if (flag = true){
            System.out.println(name + " has been successfully deleted from booked seats for the date ");
        }
        if (!flag){
            System.out.println(name + " has not booked a seat on " );
        }

        displayMenu(seatBooking,customerName,seatBookingAndCustomerName);

    }

    private void findCustomerSeats(String[] seatBooking, String[] customerName, String[] seatBookingAndCustomerName) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter customer name: ");
        String name = input.nextLine();
        boolean flag = false;


        for (int i = 1; i < (SEATING_CAPACITY + 1); i++){
            if (name.toLowerCase().equals(customerName[i].toLowerCase())){
                System.out.println("The seats: " + i );
                flag = true;

            }
        }
        if (!flag){
            System.out.println(name + " has not reserved a seat on " );
        }
        displayMenu(seatBooking,customerName,seatBookingAndCustomerName);
    }

    private void viewOrderedSeats(String[] seatBooking, String[] customerName, String[] seatBookingAndCustomerName) {

        String temp;
        for (int f = 1; f <= SEATING_CAPACITY + 1; f++) {
            for (int g = f + 1; g < SEATING_CAPACITY + 1; g++){
                if (customerName[f].compareTo(customerName[g])>0){
                    temp = customerName[f];
                    customerName[f] = customerName[g];
                    customerName [g] = temp;
                }
            }
        }


        System.out.println("The Order is: ");
        for (int a = 1; a < SEATING_CAPACITY; a++){
            System.out.println(customerName[a] + ",");
        }
        System.out.println(customerName[SEATING_CAPACITY]);
        displayMenu(seatBooking,customerName,seatBookingAndCustomerName);
    }

    private void storeDataInFile(String[] seatBooking, String[] customerName, String[] seatBookingAndCustomerName) {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("customerDetails");


        MongoCollection collection = database.getCollection("collection");


        for (int s = 1; s < customerName.length; s++){

            String Name= customerName[s];
            Integer SeatBooking = s;

            Document document = new Document();
            document.put("CustomerName", Name);
            document.put("seatID", SeatBooking);

            collection.insertOne(document);
        }

        displayMenu(customerName,seatBooking,seatBookingAndCustomerName);
    }

    private void loadDataFromFile(String[] seatBooking, String[] customerName, String[] seatBookingAndCustomerName) {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("customerDetails");

        MongoCollection<Document> collection = database.getCollection("collection");
        System.out.println("Collection customerDetails selected successfully!");

        Iterator<Document> cursor = collection.find().iterator();

        while(cursor.hasNext()) {
            Document document = cursor.next();
            System.out.println(document);
            String customer_name = (String) document.get("CustomerName");
            Integer seat_number = (Integer) document.get("seatID");
            customerName[seat_number] = customer_name;

        }

        displayMenu(seatBooking,customerName,seatBookingAndCustomerName);


    }

    public static void main(String[] args) {
        launch(args);
    }


}