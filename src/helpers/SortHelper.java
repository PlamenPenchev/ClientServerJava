package helpers;

import static helpers.SortHelper.LiveStockComparator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import livestock.*;


/**
 *
 * @author Plamen
 */
/*
This Helper class consists of 2 created Comparators which hold the ID of the animal
as a compared value and the name of the animal as another compared value. In the class
we have 3 methods created as follows: 
sortByID() which will sort the selected list by the ID of it's objects
sortByName() which will sort the selected list by the kind of it's objects
sortByIdAndName() which will sort the selected list by the kind and the ID of it's objects
*/
public class SortHelper {
    public static Comparator<LiveStock> LiveStockComparator = new Comparator<LiveStock>() {

        @Override
	public int compare(LiveStock s1, LiveStock s2) {
	   int liveStockID1 = s1.ID;
	   int liveStockID2 = s2.ID;

	   //ascending order
	   return liveStockID1-liveStockID2;
        }
    };
    public static void sortByID(List<LiveStock> list) {
        Collections.sort(list, LiveStockComparator );
        for (LiveStock item: list) {
            item.show();
        }
    }
    public static Comparator<LiveStock> NameComparator = new Comparator<LiveStock>() {

	public int compare(LiveStock s1, LiveStock s2) {
	   String LSName1 = s1.getName().toUpperCase();
	   String LSName2 = s2.getName().toUpperCase();

	   //ascending order
	   return LSName1.compareTo(LSName2);

	   //descending order
	   //return StudentName2.compareTo(StudentName1);
        }
    };
    public static void sortByName(List<LiveStock> list) {
        Collections.sort(list, NameComparator );
        for (LiveStock item: list) {
            item.show();
        }
    }
    public static void sortByIdAndName(List<LiveStock> list) {
        Collections.sort(list, LiveStockComparator );
        Collections.sort(list, NameComparator );
        for (LiveStock item: list) {
            item.show();
        }
    }
    
}

