import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Trial extends Application {

    Passenger [] waitingRoom  = new Passenger[42];
    PassengerQueue[] trainQueue = new PassengerQueue[42];

    Stage window;
    Scene scene1;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        for (int n = 1; n < 43; n++) {

        }
        displayMenu(waitingRoom,trainQueue);


    }

    private void displayMenu(Passenger[] waitingRoom, PassengerQueue[] trainQueue) {
        System.out.println("Enter \"A\" to add customer to the queue from the waiting room.");
        System.out.println("Enter \"V\" to view the train queue.");
        System.out.println("Enter \"D\" to delete customer from the queue");
        System.out.println("Enter \"S\" to store queue data into a file.");
        System.out.println("Enter \"L\" to load data from file into the train queue.");
        System.out.println("Enter \"R\" to run the simulation and produce report.");
        System.out.println("Enter \"Q\" to quit.");

        Scanner input = new Scanner(System.in);
        System.out.println("Select an option: ");
        String option = input.nextLine();

        switch (option){
            case "A":
            case "a":
                addCustomerToQueue(waitingRoom,trainQueue);
                break;
            case "V":
            case "v":
                viewTrainQueue(waitingRoom,trainQueue);
                break;
            case "D":
            case "d":
                deleteCustomerFromQueue(waitingRoom,trainQueue);
                break;
            case "S":
            case "s":
                storeDataInFile(waitingRoom,trainQueue);
                break;
            case "L":
            case "l":
                loadDataFromFile(waitingRoom,trainQueue);
                break;
            case "R":
            case "r":
                runTheSimulation(waitingRoom,trainQueue);
                break;
            case "Q":
            case "q":
                System.exit(0);
                break;
            default:
                System.out.println("You have entered an invalid option.");
                System.out.println("Please enter a correct option from (A,V,D,S,L,R,Q) only..");
                displayMenu(waitingRoom,trainQueue);

        }
    }

    private void addCustomerToQueue(Passenger[] waitingRoom, PassengerQueue[] trainQueue) {

        Passenger details = new Passenger();
        String[] customerName = new String[43];


        Button [] queue = new Button[43];
        Button exit = new Button("  Exit  ");
        exit.setLayoutY(400);
        exit.setLayoutX(640);


        Label heading = new Label("THE QUEUE");
        heading.setLayoutY(30);
        heading.setLayoutX(250);
        heading.setStyle("-fx-font-size: 35px");
        GridPane gridPane = new GridPane();
        int columnIndex = 0;
        int rowIndex = 0;
        int rowIndexes = 0;

        String customerSeat;
        for (int i = 1; i < 43; i++){
            customerSeat = "        ";

            queue[i] = new Button(customerSeat);
            gridPane.add(queue[i],columnIndex,rowIndex);
            columnIndex++;
            rowIndexes++;

            if (rowIndexes == 6){
                columnIndex = 0;
                rowIndexes = 0;
                rowIndex++;
            }
        }

        gridPane.setLayoutX(160);
        gridPane.setLayoutY(160);
        gridPane.setHgap(25);
        gridPane.setVgap(10);

        AnchorPane layout = new AnchorPane();
        layout.setStyle("-fx-background-color: black");
        layout.getChildren().addAll(heading,exit,gridPane);

        scene1 = new Scene(layout,800,600);
        window.setTitle("Denuwara Express");
        window.setScene(scene1);
        window.show();

        exit.setOnAction(event -> {
            window.close();
        });
        window.setOnCloseRequest(event -> {
            window.close();
            displayMenu(waitingRoom,trainQueue);
        });

    }

    private void viewTrainQueue(Passenger[] waitingRoom, PassengerQueue[] trainQueue) {
        Button [] queue = new Button[43];

        Button exit = new Button("  Exit  ");
        exit.setLayoutY(200);
        exit.setLayoutX(600);

        Label present = new Label("Arrived");
        present.setLayoutX(590);
        present.setLayoutY(280);
        Button named = new Button("  Name  ");
        named.setLayoutY(280);
        named.setLayoutX(560);

        Label absent = new Label("Empty");
        absent.setLayoutY(330);
        absent.setLayoutX(590);
        Button empty = new Button("    ");
        empty.setLayoutY(330);
        empty.setLayoutX(560);

        Label heading = new Label("JOIN THE QUEUE");
        heading.setLayoutY(30);
        heading.setLayoutX(250);
        heading.setStyle("-fx-font-size: 35px");
        GridPane gridPane = new GridPane();
        int columnIndex = 0;
        int rowIndex = 0;
        int rowIndexes = 0;

        String customerSeat;
        for (int i = 1; i < 43; i++){
            customerSeat = "        ";

            queue[i] = new Button(customerSeat);
            gridPane.add(queue[i],columnIndex,rowIndex);
            columnIndex++;
            rowIndexes++;

            if (rowIndexes == 6){
                columnIndex = 0;
                rowIndexes = 0;
                rowIndex++;
            }
        }

        for (int j = 1; j < 43; j++)

        gridPane.setLayoutX(160);
        gridPane.setLayoutY(160);
        gridPane.setHgap(25);
        gridPane.setVgap(10);

        AnchorPane layout = new AnchorPane();
        layout.setStyle("-fx-background-color: black");
        layout.getChildren().addAll(heading,exit,gridPane,present,named,absent,empty);

        scene1 = new Scene(layout,800,600);
        window.setTitle("Denuwara Express");
        window.setScene(scene1);
        window.show();



    }

    private void deleteCustomerFromQueue(Passenger[] waitingRoom, PassengerQueue[] trainQueue) {
    }

    private void storeDataInFile(Passenger[] waitingRoom, PassengerQueue[] trainQueue) {
    }

    private void loadDataFromFile(Passenger[] waitingRoom, PassengerQueue[] trainQueue) {
    }

    private void runTheSimulation(Passenger[] waitingRoom, PassengerQueue[] trainQueue) {


    }

    public static void main(String[] args) {
        launch(args);
    }

}
