import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
public class DistantClient extends JPanel implements ActionListener {
    private static final int PORT = 8080;
    private static String name;
    private static String clientType;
    
    
    protected static JTextField textField, textField2;
    protected static JTextArea textArea;
    protected static JButton button;
    protected static JLabel label1, label2 ;
    private final static String newline = "\n";
    
    public DistantClient() {
        super(new GridBagLayout());
        //set components
        button = new JButton("Click here!");
        button.addActionListener(this);
        textField = new JTextField(100);    
        
        label1 = new JLabel("Enter your name");
        label2 = new JLabel("Enter /'R/' if you want to Read or /'W/' to Write");
        textField2 = new JTextField(100);
        
        
        textArea = new JTextArea(20, 100);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
 
        //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(label1, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        add(textField, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        add(label2, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        add(textField2, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        add(button, c);
        
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scrollPane, c);
    }
 
    public void actionPerformed(ActionEvent evt) {
        name = textField.getText();     //get the name from the field
        clientType = textField2.getText();  //get the type form the field
        
        textField.selectAll();
        textField2.selectAll();
        
        //Make sure the new text is visible, even if there
        //was a selection in the text area.
        textArea.setCaretPosition(textArea.getDocument().getLength());
        String result = sendRequest(name, clientType);
        textArea.append(result);    //print the request result
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Farm Portal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add contents to the window.
        frame.add(new DistantClient());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    
    public static void main(String[] args)throws IOException {
    	
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();      //display the interface
            }
        });
       
    }
    private static String sendRequest(String name, String clientType) {
    		String result = "";
    	try {
    		//set the server and the socket
    		String server = "192.168.1.103";
            InetAddress addr =  InetAddress.getByName(server);
            System.out.println("addr = " + addr);
            Socket socket = new Socket(addr, PORT);  
            
            
            try {
            System.out.println("socket = " + socket);
            BufferedReader in =
                    new BufferedReader(                 // set socket input stream
                            new InputStreamReader(socket.getInputStream()));
            
            PrintWriter out =   new PrintWriter(     //set socket output stream
                    new BufferedWriter(  new OutputStreamWriter( 
                            socket.getOutputStream())),true);
            System.out.print("Your name please:");   //client's name
           
            System.out.print("input R(for reader) or W [to add an animal] :"); //first pass
            
           
            if(clientType.equalsIgnoreCase("R")){ //if reader type = then go to read
            	clientType="Read";
                System.out.println("Entering to see the animals in the farm");
            }
            else{
            	clientType="Write";         //if writer type go to register
                System.out.println("Entering to register new animal");
            }
                 
                out.println(name);   //send the name
                out.println(clientType); //send the type
               
                if (clientType.equals("Read")) {  //if the client is reader
                int count = Integer.parseInt(in.readLine());  //get list size so we know how many ines to print
                String a =null;
                for (int i=0; i<count; i++) {
                	a = in.readLine().toString();   //print the result lines
                	result += a + newline;
                }
                }else {
                	String a =null;                
                	result = "Animals alive: ";  // show how many animals were before we add another
                	for (int i=0; i<5; i++) {   // we define to see the first 5 lines of output only
                		
                    	a = in.readLine().toString();
                    
                    	result += a + newline;  // print the result to the user
                    }
                }
               
                System.out.println("out");
               
            out.println("END");     // end
            }
            catch(Exception e) {
            	System.out.println(e.getMessage());
				
            }
            finally {
           	 	socket.close();     
                System.out.println("closing...");
            }
            
        }catch(Exception e) {
        	System.out.println(e.getMessage());
			
        }
    	return result;
        
    }
} 
 