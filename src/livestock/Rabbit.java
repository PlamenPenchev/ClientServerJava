package livestock;

import helpers.GrowRateHelper;

/**
 *
 * @author Plamen
 */
/*
The class Rabbit is inheriting all the characteristics of it's parent LiveStock. 
It extends it with a different grow rate for the selected kind and max grow rate for
the kind.
*/
public class Rabbit extends LiveStock {
    private final double RABBIT_MAX_GROW_RATE =7;
    
    public Rabbit(int ID, double startWeight){
        super(ID, startWeight);
        double grow = (0.06 * startWeight); //6% grow rate per month
        double individualGrowRate = grow * GrowRateHelper.GetAnimalIndividualGrowRate(); //generating random individual grow rate 
        super.growRate = grow + individualGrowRate;
        super.MAX_GROW_RATE = this.RABBIT_MAX_GROW_RATE;
        super.name = "RABBIT";
    }
    
    @Override
	public void showAnimal() {
        System.out.println("It's a "+name+" and it's current weight is " + currentWeight);
    }
}
