package livestock;
import helpers.GrowRateHelper;

/**
 *
 * @author Plamen
 */
/*
The class Sheep is inheriting all the characteristics of it's parent LiveStock. 
It extends it with a different grow rate for the selected kind and max grow rate for
the kind.
*/
public class Sheep extends LiveStock {
    private final double SHEEP_MAX_GROW_RATE = 90;
    
    public Sheep(int ID, double startWeight){
        super(ID, startWeight);
        double grow = (0.03 * startWeight); //3% grow rate per month
        double individualGrowRate = grow * GrowRateHelper.GetAnimalIndividualGrowRate(); //generating random individual grow rate 
        super.growRate = grow + individualGrowRate;
        super.MAX_GROW_RATE = this.SHEEP_MAX_GROW_RATE;
        super.name = "SHEEP";
    }
    
    @Override
	public void showAnimal() {
        System.out.println("It's a "+name+" and it's current weight is " + currentWeight);
    }
  
}

