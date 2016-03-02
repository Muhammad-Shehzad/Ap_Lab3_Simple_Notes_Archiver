public class Note implements java.io.Serializable{
	public String user_name;
	public String data;
	
	Note(){
		user_name = "Unspecified";
		data = "Nil";		
	}
	
	Note(String argU, String argD){
		user_name = argU;
		data = argD;		
	}
}
