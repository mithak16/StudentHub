public abstract class Person {

    //protected field of String name (the name of the person object)
    protected String name; 

    //Constructor that assigns name 
    public Person(String name){
        this.name = name; 
    }

    //abstract method where sub-classes must inherit this method
    public abstract String getRole(); 
}
