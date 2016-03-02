
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	static ArrayList<Note> stored = new ArrayList<Note>();
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		int port = 9090;
		ServerSocket server_socket = null;	
		
		try {
			server_socket = new ServerSocket(port);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("Running...");
		try{
			while(true){
				Socket socket = server_socket.accept();
				
				InputStream input_stream = socket.getInputStream();
				DataInputStream data_input_stream = new DataInputStream(socket.getInputStream());
				DataOutputStream data_output_stream = new DataOutputStream(socket.getOutputStream());
				ObjectInputStream object_input_stream = new ObjectInputStream(socket.getInputStream());

				int choice = input_stream.read();
				
				if(choice==0){ //Send notes of that user
					String uname = data_input_stream.readUTF();
					System.out.println("Username Received: " + uname);
					String toSend = "";
					for(int i=0; i<stored.size(); i++){
						if(stored.get(i).user_name.equals(uname)){
							toSend+="Notes: "+stored.get(i).data +"\n";
						}
					}
					data_output_stream.writeUTF(toSend);					
				}else if(choice==1){
					Note tempNote = (Note)object_input_stream.readObject();
					if(tempNote != null){
						stored.add(tempNote);
					}
				}	
				socket.close();
				}			
		} catch(IOException e){
			e.printStackTrace();
		}
		
		server_socket.close();
	}
}
