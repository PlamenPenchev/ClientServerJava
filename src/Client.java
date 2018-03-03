import java.io.PrintWriter;

public class Client extends Thread{
    private boolean reader;
    private Ferm f;
    private String name="";
    private static int num;
    private PrintWriter out;
    Client(String name,boolean reader, Ferm f, PrintWriter out){
        this.reader=reader;
        this.f = f;
        this.out=out;
        if(name==null) {
        	name = "Client "+ ++num + (reader?" reader":" writer");  
        }else {
        	name = "               "+name + (reader?" reader":" writer"); 
        }
        super.setName(name);
    }
    public void run(){
        try {
            sleep((int)(Math.random()*600)); //takes time to prepare to enter
        } catch (InterruptedException e){}
        f.enterFarm(reader);      //enter the farm
        try {
            sleep((int)(Math.random()*300));
        }catch (InterruptedException e){}
        if (out!=null){   
        	out.println(f.getListSize()+2);       //get the amount of lines we have to send to the user
			out.println(name + (reader ? " enter to read" : " entered to write") + " in the ferm"); 
            
            if (reader) {
            	
            for (int i = 0; i <f.getListSize(); i++) {
            	out.println(f.getAnimalByIndex(i));
            	            	//send the animal lines to the user
            }
            	
            } else {
            	out.println("Registering");
            	out.println(f.getAnimalByIndex(f.getListSize()-1)); //print the last animal added to the user
            }
           
        }
        try {
            sleep((int)(Math.random()*600));
        } catch (InterruptedException e){}
        f.leaveFarm();   //leave the farm
        if(out!=null) {
        	out.println(name+" left the ferm");
        }
     }
}