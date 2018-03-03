import java.util.ArrayList;

import livestock.Buffalo;
import livestock.LiveStock;
import livestock.Rabbit;
import livestock.Sheep;

class Ferm {
    private int readerOrWriter;
    private boolean occupied;
    private final int maxClientsInside;
    private int currentClientsNumber;
    private ArrayList<LiveStock> liveStockList;
    private int listSize;
    Ferm(){
        readerOrWriter = currentClientsNumber = 0;
        occupied = false;
        maxClientsInside = 3;
        liveStockList = new ArrayList<LiveStock>();
        liveStockList.add(new Buffalo(2, 230));
        liveStockList.add(new Sheep(45, 24));
        liveStockList.add(new Buffalo(1, 230));
        liveStockList.add(new Rabbit(12, 1));
        liveStockList.add(new Rabbit(11, 2));
        liveStockList.add(new Buffalo(8, 233));
        liveStockList.add(new Rabbit(10, 4));
        liveStockList.add(new Sheep(5, 26));
        liveStockList.add(new Sheep(7, 31));
        listSize = liveStockList.size();
    }
    synchronized public int howManyReadersOrWriters(){
        return readerOrWriter;
    }
    synchronized public void readList() {
    	for(LiveStock item : liveStockList){
            item.show();
            }
    	System.out.println("end");
    }
    synchronized public void test() {
    	System.out.println("\t"+Thread.currentThread().getName()+" is browsing the animal list");
    	
    }
    synchronized public void enterFarm(boolean reader ){
    	
        while((readerOrWriter<0)&& (reader==true)||
              (readerOrWriter>0) && (reader==false) ||
              occupied){
            System.out.println("\t"+Thread.currentThread().getName()+" waiting");
            try{     
            	wait();
            } catch(InterruptedException e){
                System.err.println(e);
            }
        }
        listSize = liveStockList.size();
        if (reader) {   // check if reader
        	readerOrWriter++;
        	
        } else {   // else is writer
        	readerOrWriter--;
        }
        currentClientsNumber++;
        if(currentClientsNumber >= maxClientsInside) {  //check is fully occupied
        	occupied=true; 
        }
        System.out.println(Thread.currentThread().getName()+" is in the farm");
        try {
			Thread.sleep((int)(Math.random() *1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (reader) {   //print the list
        	readList();
        }else if (reader ==false) {    
        	int animalKind = (int)(Math.random()*100);          //generate a random animal and add it to the list
        	if (animalKind < 33) {
        		liveStockList.add(new Sheep((int)(Math.random() * 121212), (int)(Math.random() * 40)));
        		System.out.println(Thread.currentThread().getName()+" added a sheep");
        	} else if (animalKind >=33 && animalKind <66) {
        		liveStockList.add(new Rabbit((int)(Math.random() * 112112), (int)(Math.random() * 12)));
        		System.out.println(Thread.currentThread().getName()+" added a rabbit");
        	}else {
        		liveStockList.add(new Buffalo((int)(Math.random() * 112112), (int)(Math.random() * 250)));
        		System.out.println(Thread.currentThread().getName()+" on the buffalo");
        	}
        }
        listSize = liveStockList.size();
        
    }
    public synchronized int getListSize() {   
    	return listSize;
    }
    public synchronized String getAnimalByIndex(int i) {
    	return liveStockList.get(i).returnAnimal();
    }
    synchronized public void leaveFarm(){
    	try {
    		Thread.sleep((int)(Math.random() *1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (readerOrWriter>0) {   //if reader leaves 
        	readerOrWriter--;
        } else {
        	readerOrWriter++; //if writer leaves
        }
        if (readerOrWriter ==0) {
        	occupied=false;               //open the farm
        	currentClientsNumber=0;
        }
        System.out.println("\t\t"+Thread.currentThread().getName()+" left the farm");
        notifyAll();
    }
}