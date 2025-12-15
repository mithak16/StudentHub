
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class StudentHub {
    //private attributes where waitingQueue is the queue of all students in line
    //hubData is a 2D arrayList. First row contains all students in Cafe Area, second row contains all students in Lounge Area, 
    //third row contains all students in Study Room area 
    private final Queue<Student> waitingQueue; 
    private final ArrayList<ArrayList<Student>> hubData; 

    //intitalizes waitingQueue and hubData new objs 
    public StudentHub(){
        waitingQueue = new LinkedList<Student>();
         hubData = new ArrayList<ArrayList<Student>>(); 
         hubData.add(new ArrayList<Student>()); //cafe 
         hubData.add(new ArrayList<Student>()); //lounge
         hubData.add(new ArrayList<Student>()); //study room
    }

    //adds student obj into waiting queue
    public void buildQueue(Student student){
        waitingQueue.add(student); 
    }

    //returns waitingQueue. This is getter method 
    public Queue<Student> getWaitingQueue(){
        return waitingQueue; 
    }

    //builds the arraylists that will go inside the hubData 2D array. There are 3 of these sub-arrays
    //while the waiting queue is not empty, remove students and put them in their respective sub-arrays
    public void buildHubData(){
        while (!waitingQueue.isEmpty()){

            Student s = waitingQueue.poll(); 
            String area = s.getSession().getArea().toLowerCase(); 

            //Cafe sub-array 
            if(area.compareTo("cafe") == 0){
                hubData.get(0).add(s); 
            }

            //Lounge sub-array 
            if(area.compareTo("lounge") == 0){
                hubData.get(1).add(s); 
            }

            //Study Room sub-array 
            if(area.compareTo("study room") == 0){
                hubData.get(2).add(s); 
            }

        }
    }

    //getter method for returning the hubData 2D arraylist 
    public ArrayList<ArrayList<Student>> getHubData(){
        return hubData; 
    }

    //sorts each row in hubData by the student name. This is done using a lambda expression and comparing the names
    public void sortHubDatabyStudentName(){
        for(ArrayList<Student> row: hubData){
            row.sort((s1, s2) -> (s1.getName().toLowerCase()).compareTo(s2.getName().toLowerCase())); 
        }
    }

    //sorts each row in hubData by the student bill. This is done by comparing student bills. 
    public void sortHubDatabyStudentBill(){
        for (ArrayList<Student> row : hubData) {
            row.sort((s1, s2) -> Double.compare(s1.getBill(), s2.getBill()));
        }
    }

    public double getStudentBill(String name) throws UnKnownStudentException{
        //fixed 3 rows 
        for(int i =0; i < 3; i++){
            //for each row, iterate through entire arraylist
            for(int j = 0; j < hubData.get(i).size(); j++){
                Student s = hubData.get(i).get(j); 
                //if the students name match, return the total. A student appears only once
                if(s.getName().toLowerCase().compareTo(name.toLowerCase()) == 0){
                    return s.getBill(); 
                }
            }
        }

        //if not found at all then throw exception 
        throw new UnKnownStudentException("Student not found."); 
    }

    //call the private recursive helper method starting with row and col both 0
    public double calculateTotalHubProfit(){
        return calculateTotalHubProfitRecursive(0, 0); 
    }

    //recursively calculates the total billable cost by going through rows and cols
    private double calculateTotalHubProfitRecursive(int row, int col){
        //base case 
        if (row == hubData.size()) {
            return 0.0;
        }

        //if done with this row, move onto the next
        if (col == hubData.get(row).size()) {
            return calculateTotalHubProfitRecursive(row + 1, 0);
        }

        //get current student's bill 
        double currentBill = hubData.get(row).get(col).getBill();

        //move onto the next column if finished
        return currentBill + calculateTotalHubProfitRecursive(row, col + 1);
    }


}
