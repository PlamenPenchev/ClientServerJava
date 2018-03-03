
public class ServerStart  {
	
    public static void main(String arg[]){
       
        Ferm f = new Ferm();
        (new Server(f)).start();
        try{
            Thread.sleep(50);
        }catch (InterruptedException e){}

        System.out.println("to experiment with local clients press enter");   
              
          // To test the system we can add locally some clients on server start
          
          /*for(int i = 0; i < 3; i++){
            try{
                Thread.sleep(5000);
            }catch (InterruptedException e){}
           		(new Client(null,true, f, null)).start();
           		(new Client(null,false, f, null)).start();
                        
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e){}
        	}*/
        
    }
}


