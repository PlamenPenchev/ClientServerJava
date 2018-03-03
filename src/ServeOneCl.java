import java.io.*;
import java.net.*;
class ServeOneCl extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Ferm f;
    
    private String name="";
    public ServeOneCl(Socket s, Ferm f)  throws IOException {
        socket = s;
        in = new BufferedReader(
                new InputStreamReader(
                  socket.getInputStream()));
        out =   new PrintWriter(
                new BufferedWriter(  new OutputStreamWriter(
                        socket.getOutputStream())),true);
        this.f=f;
        
        start();    // we are calling run()
  }
  public void run() {
      String readOrWrite;
      boolean readers;
    try {
        while (true) {
             name = in.readLine();            //get name
             if (name.equals("END")) {    //if local will break form the loop
            	 break;
             }
             readOrWrite = in.readLine();     //get type of client
             if(readOrWrite.equalsIgnoreCase("Read")) {
            	 readers=true;
             } else readers=false;
             
             (new Client(name,readers, f,out)).start();     //initialize the client
        }
    } catch (IOException e) {  }
   finally {
      try {
        socket.close();
      } catch(IOException e) {}
    }
  }
} 