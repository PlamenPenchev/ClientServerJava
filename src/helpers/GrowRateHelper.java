
package helpers;

import java.util.Random;

/**
 *
 * @author Plamen
 */
/*
This is a Helper of whom main goal is to give a random individual variation of their growth rate.
We create new random number generator and set the min and max of the desired range.
*/
public class GrowRateHelper {
    public static Random randomRate = new Random();
    public static final double RANGE_MIN = -0.15;
    public static final double RANGE_MAX = 0.15;
    
    public static double GetAnimalIndividualGrowRate(){
        return (RANGE_MIN + (RANGE_MAX - RANGE_MIN) * randomRate.nextDouble());
    }
}

