import java.io.*;
import java.net.*;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Client {
	public static void main(String[] args){
		try{

	        char choice;
	        String user_name="";
        	for (;;)					//Infinite Loop.
	{
    			Socket socket = new Socket("localhost", 9090);
    			DataOutputStream data_out = new DataOutputStream(socket.getOutputStream());
    			
    			InputStream input_stream = socket.getInputStream();
    			OutputStream output_stream = socket.getOutputStream();
    			DataInputStream data_input_stream = new DataInputStream(input_stream);
    			ObjectOutputStream object_output_stream = new ObjectOutputStream(output_stream);
    			Scanner scan = new Scanner(System.in);
    			Scanner sc = new Scanner(System.in);
    			choice= JOptionPane.showInputDialog("Enter \n\t0: To Download Notes \n\t1: To archive to server  \n\t2: To exit\n").charAt(0);

			switch (choice)
			{
			case '0':	//Download Notes
				user_name = JOptionPane.showInputDialog("Enter username: ");
				output_stream.write(0); //Tell server that we're downloading
				data_out.writeUTF(user_name);
				//For checking
				try {
					  Thread.sleep(1000);
					} catch (InterruptedException ie) { }

				JOptionPane.showMessageDialog(null, data_input_stream.readUTF());
				break;
			case '1': //To write to server
				user_name = JOptionPane.showInputDialog("Enter username: ");
				String noteData =JOptionPane.showInputDialog("Enter Note: ");
				output_stream.write(1); 
				Note tempNote = new Note(user_name,noteData);
				object_output_stream.writeObject(tempNote);
		
				JOptionPane.showMessageDialog(null,"Stored: \n        Username:    " +tempNote.user_name +"\n        Notes:        " +tempNote.data);
				break;
			case '2': //to exit
				socket.close();
				scan.close();
				sc.close();
				return;
			default:
				JOptionPane.showMessageDialog(null,"Wrong number entered");
			
			}//end switch
			socket.close();
	//		scan.close();
//			sc.close();
	}//end for loop	
	
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}
}
