public enum CoffeeType {

    //The coffee types with their prices 
    ESPRESSO(2.00), LATTE(3.50), MOCHA(4.00), AMERICANO(2.50); 

    private final double price; 

    //Constructor, assigns price
    private CoffeeType(double price){
        this.price = price; 

    }

    //Getter method for price
    public double getPrice(){
        return price; 
    }
}
