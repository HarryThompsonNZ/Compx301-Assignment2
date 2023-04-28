import java.io.*;

public class Encoder{
	
	static void Main(string[] args)
    {
    	try{
    		FileInputStream file = new FileInputStream("input.text"); //get input file of data
    		int data;
    		//While the data is valid
    		while((data = file.read()) != -1){
    			//get bytes turn into nibbles(4 bits) and then translate to hex
    		}
    		file.close();
    	}
    	catch(IOException e){
    		e.printStackTrace();
    	}
    }
}
