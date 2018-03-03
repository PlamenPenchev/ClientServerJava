package livestock;


/**
 *
 * @author Plamen
 */
/*
This is the LiveStock class which will contain all the common information 
between the different kind of animals. Such as ID, weight etc. This is the 
main parent of the following classes - Rabbit, Buffalo and Sheep. 
*/
public abstract class LiveStock implements java.io.Serializable {
    public int ID;
    public double startWeight;
    protected double currentWeight;
    protected double MAX_GROW_RATE;
    protected double growRate;
    protected String name;
    
    public LiveStock(int ID, double startWeight) { //the constructor for LiveStock
    this.ID = ID;
    this.startWeight = startWeight;
    currentWeight = startWeight;
    }
    
    public void setId(int id){ //method to set ID of the animal
        this.ID = id;
    }
    public String getName(){ //method to get the kind of the animal
        return this.name;
    }
    public int getId(){ //mehtod to get the ID of the animal
        return this.ID;
    }
    
    public void setStartWeight(double startWeight) { //method to set the starting weight of the animal
        this.startWeight = startWeight;
    }
    public double getStartWeight() { //methof to get the starting weight of the animal
        return this.startWeight;
    }
    
    public abstract void showAnimal();
    
    public void show() { // printing the ID and the star weight of the animal
        System.out.println("The animal ID: "+ID+" and it's weight when registered is "+startWeight+"kg.");
        showAnimal();
    }
    public String returnAnimal() {
    	return "The animal is a "+name+" ID: "+ID+" and it's weight when registered is "+startWeight+"kg.";
    }
    /*
    The next method is growing the animal by using it's kind grow rate which is fixed and
    adding to it the individual grow rate of each different member of the same kind (+/- 15%).
    We must declare the number of the months we want to see the animal grow. For example if
    we want to see the weight of the animal after 1 month we should add '1' as a value in grow().
    There is also a declared condition if the animal get to the MAX grow rate for it's kind
    to be printed in the console that it will be sold. 
    */
    public void grow(int N) { 
        double weight = startWeight;
        int M = 1;
        currentWeight = weight;
        while(M<=N && weight <=MAX_GROW_RATE){
            weight += growRate;
            System.out.println("The weight of the "+name+" with ID "+ID+" after "+M+" month/s is "+weight);
            M++;
            if (weight >=MAX_GROW_RATE){
                System.out.println("The "+name+" with ID "+ID+ " will be sold");
            }
        }
        currentWeight = weight;
    }
    /*
    The method monthyGrow() will start a loop in which all the animals will start growing
    until they reach their max grow rate per kind and we will see the months it took for each single
    animal to be fully grown. Then again when we get to that point on the console we will have printed 
    a msg telling us that the animal will be sold. 
    */
    public void monthlyGrow(){
        double weight = startWeight;
        
        int N = 0;
        while(N>=0 && weight <=MAX_GROW_RATE){
            weight += growRate;
            
            N++;
            System.out.println("The weight of the "+name+" with ID "+ID+" after "+N+" month/s is "+weight);
            currentWeight = weight;
            if (weight >=MAX_GROW_RATE){
                System.out.println("The "+name+" with ID "+ID+ " will be sold");
            }
        }
    }
}

        
        