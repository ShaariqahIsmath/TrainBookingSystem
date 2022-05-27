public class PassengerQueue {

     private int maxStayInQueue;

     Passenger [] queueArray = new Passenger[42];
     int first;
     int last;
     int maxLength;
     int die;

     public int addPassenger(Passenger data) {

          if (!isFull()) {
               die = (int) (Math.random() * 6) + 1;
          }
          else {
               System.out.println("The queue is Full!");
          }

          return die;
     }
     public Passenger removePassenger(){

          Passenger data = queueArray[first];
          first = first + 1;
          maxLength--;

          return data;

     }

     public void show(){

          System.out.println("The Passengers: ");
          for ( Passenger n : queueArray){
               System.out.println(n + " ");

          }
     }
     public int getMaxLength() {
          return maxLength;
     }

     public boolean isEmpty(){
          return getMaxLength() == 0;
     }

     public boolean isFull(){
          return getMaxLength() == 42;
     }

     public int getMaxStayInQueue() {


          return maxStayInQueue;
     }
     public void setMaxStayInQueue(int maxStayInQueue) {
          this.maxStayInQueue = maxStayInQueue;
     }
}
