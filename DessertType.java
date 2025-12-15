public enum DessertType
 {
    //The Desserts with their prices 
    CAKE(4.50), COOKIE(1.25), BROWNIE(2.75), PASTRY(3.00); 
    
    private final double price; 

    //Constructor that assigns price
    private DessertType(double price){
        this.price = price; 
    }

    //Getter method. Returns the price
    public double getPrice(){
        return price; 
    }

}
