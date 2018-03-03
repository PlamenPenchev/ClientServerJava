package livestock;


import helpers.GrowRateHelper;

/**
 *
 * @author Plamen
 */
/*
The class Buffalo is inheriting all the characteristics of it's parent LiveStock. 
It extends it with a different grow rate for the selected kind and max grow rate for
the kind.
*/
public class Buffalo extends LiveStock implements java.io.Serializable {
    private final double BUFFALO_MAX_GROW_RATE = 500;
    
    public Buffalo(int ID, double startWeight){
        super(ID, startWeight);
        double grow = (0.05 * startWeight); //5% grow rate per month
        double individualGrowRate = grow * GrowRateHelper.GetAnimalIndividualGrowRate(); //generating random individual grow rate 
        super.growRate = grow + individualGrowRate;
        super.MAX_GROW_RATE = this.BUFFALO_MAX_GROW_RATE;
        super.name = "BUFFALO";
    }

    @Override
	public void showAnimal() {
        System.out.println("It's a "+name+" and it's current weight is " + currentWeight);
    }
}
