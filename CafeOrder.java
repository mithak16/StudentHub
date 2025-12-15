
//CafeOrder can be any type of Dessert or Coffee and implements the Billable interface 
public class CafeOrder<T extends Enum<T>> implements Billable{

    //private field that represents the order 
    private final T order; 

    //constructor where assigns order 
    public CafeOrder(T order){
        this.order = order; 
    }

    //returns the cost if order is of type Dessert or Coffee else returns 0.0 
    public double getCost(){
        if(order instanceof DessertType){
            return ((DessertType) order).getPrice(); 
        }

        if(order instanceof CoffeeType){
            return ((CoffeeType) order).getPrice(); 
        }

        return 0.0; 
    }

    //returns name of the order 
    public String getDescription(){
        return order.name(); 
    }

    //returns a string which contains the order name and price 
    public String toString(){
        return "Item: " + getDescription() + " Price: " + getCost(); 
    }

}
