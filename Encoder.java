import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Encoder{
	
	private static final int MAX_DICT_SIZE = 256;
	public static final char[] hexLibrary = {0,1,2,3,4,5,6,7,8,9,A,B,C,D,E,F};

	static void Main(string[] args)
    {
		//Get the bytes as the input to the encoder and store in an array called byteStream
		byte[] byteStream = System.in.readAllBytes();
		//Get the Hexadecimal value by using a method to convert the bytes and then store in an char array called hexArray
		char[] hexArray = bytesToHex(byteStream);
		//
		List<Pair<Interger,Interger>> encodedArray = Encode(hexArray);

		/*
		*Method which takes an array of bytes the returns them as an array of hexdeciamls
		*/
		private int[] bytesToHex(byte[] bytes){
			//Create an array called nibbles that is twice the size of the bytes array.
			char[] hexStream = new char[bytes.length * 2];


			for(int i = 0; i < bytes.length; i++){
				int x = bytes[i] & 0xFF;
				//take the second half of the byte convert to hex and store it in the array
	        	hexStream[i * 2] = hexLibrary[x >>> 4];
	        	//take the first half of the byte convert to hex and store it in the next index
	        	hexStream[i * 2 + 1] = hexLibrary[x & 0x0F];
	    	}	
	    	return hexStream;
		}

		/*
		Encode is a method which takes the array of hex charcters and encodes them. It then returns a list full of pairs with the first from each pair being the
		longest matching phrase and the second is the value of the hex
		*/
		private static List<Pair<Interger,Interger>> Encode(char[] hexStream){
			//Initialize dictionary and encoded array
			Map<List<Integer>, Integer> dictionary = new HashMap<>();
			List<Pair<Interger, Integer>> encodedArray = new ArrayList<>();
			
			//For every hex in the array check if it exists in the dictionary add it if not add it but if it does clear the dictionary. 
			//then sort all the pairs into an array and return it   
			for(int i = 0; i < hexArray.length;){
				int dictSize = 0;
				List<Interger> phrase = new ArrayList<>();
            	int j = i;

            	while(j < hexArray.length && dictionary.containsKey(phrase)){
                	phrase.add(hexArray[j++]);
            	}

            	if(!dictionary.containsKey(phrase)){
            		if(dictSize >= MAX_DICT_SIZE){
            			dictionary.clear();
            			dictSize = 0;
            		}
            		dictionary.put(phrase, dictSize++);
            		phrase.remove(phrase.size() - 1);
            	}
            	encodedArray.add(new Pair<>(dictionary.get(phrase), hexArray[j]));
            	i = j + 1;
            }

            return encodedArray;
		}

		/*
		Class which creates a pair
		*/
		private static class Pair<P1, P2>{
			private final P1 first;
			private final P2 second;

			public Pair(P1 first, P2 second){
            	this.first = first;
            	this.second = second;
        	}

        	public P1 getFirst(){
            	return first;
        	}
        	public P2 getSecond(){
            	return second;
        	}
		}

    }
}