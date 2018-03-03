
package helpers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import livestock.LiveStock;
/**
 *
 * @author Plamen
 */
/*
This class consists methods which will save the list as objects in the file, 
load those objects back into the list and a 3rd method which reads from the 
console and saves the printed list as a string in a file.
*/
public class FileHelper {
    public static void SaveInFile(List<LiveStock> list) throws FileNotFoundException, IOException{
        FileOutputStream fileOut = new FileOutputStream("LiveStock.txt");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
         
        for (LiveStock item : list){
            item.toString();
            out.writeObject(item);
        }
        out.writeObject(null);
        
        out.close();
        fileOut.close();
    }
    
    public static List<LiveStock> LoadFromFile() throws FileNotFoundException, IOException, ClassNotFoundException{
        List<LiveStock> result = new ArrayList<LiveStock>();
        LiveStock item = null;
        Object element = null;
        
        
        FileInputStream fileIn = new FileInputStream("LiveStock.txt");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        
        try{
            while ((element = in.readObject()) != null) {
                item = (LiveStock) element;
                result.add(item);
            }
        }
        catch(Exception ex){
            System.out.println("No elements in file.");
            result = new ArrayList<LiveStock>();
        }
        
        in.close();
        fileIn.close();
        
        return result;
    }

    
    public static void SaveListIntoFile(List<LiveStock> list) {
            
        PrintStream out;
        PrintStream original = System.out;
        try {
            out = new PrintStream(new FileOutputStream("output.txt"));
            System.setOut(out);
             for (LiveStock item: list) {
                item.show();
             }
             out.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File Was not found");
            }
        finally{
            System.setOut(original);
        }
        
    }
}
