import java.util.ArrayList;

public class Student extends Person {

    //private fields such as double bill (cost of orders), the StudySession, and their order list 
    private double bill; 
    private StudySession session; 
    private ArrayList<Billable> orders; 

    //Constructor that calls super (person constructor) so the name gets assigned.
    //StudySession is a new class, orders is instantiated, and the bill starts at 0
    public Student(String name, String area, int minutes){
        super(name); 
        this.session = new StudySession(area, minutes);
        orders = new ArrayList<Billable>();
        bill = 0.0; 
    }

    //getter method for name <- returns name
    public String getName(){
        return name; 
    }

    //getter method for the role. Returns "Student"
    public String getRole(){
        return "Student"; 
    }

    //assigns orders to this orders and then calls calcBill to compute cost 
    public void addOrders(ArrayList<Billable> orders){
        this.orders = orders; 
        calcBill();; 
    }

    //getter method for returning the session 
    public StudySession getSession(){
        return session; 
    }

    //getter method for returning the ArrayList of type Billable "orders"
    public ArrayList<Billable> getBillable(){
        return orders; 
    }

    //iterates through the orders list and adds each cost to the private double bill field 
    private void calcBill(){
        for(int i =0; i < orders.size(); i++){
            bill += orders.get(i).getCost(); 
        }
    }

    //getter method returns, the total bill 
    public double getBill(){
        return bill; 
    }

    //returns formatted string containing student info
    public String toString(){
        String toRet = "";

        toRet += "Student: " + getName() + " " + session + "\n";

        toRet += "Student Ordered: \n";

        for (Billable b : orders) {
            toRet += String.format("Item: %s Price: %.2f\n", b.getDescription(),b.getCost());
        }

        toRet += String.format("Total Bill = %.2f\n", bill);

        return toRet;

    }
    

    //nested class inside of Student class
    public static class StudySession{
        //private fields where area is the place student studied at & minutes is how long they studied for 
        private final String area; 
        private final int minutes; 

        //constructor where area and minutes are initalized 
        public StudySession(String area, int minutes){
            this.area = area; 
            this.minutes = minutes; 
        }

        //getter method, returns area 
        public String getArea(){
            return area; 
        }

        //getter method, returns minutes
        public int getMinutes(){
            return minutes; 
        }

        //returns a formatted string containing info about area and minutes 
        public String toString(){
            return "Studied at: " + area + " for: " + minutes + " mins"; 
        }
    }

}
