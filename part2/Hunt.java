import java.util.*; 
import java.io.*;

public class Hunt {
	
	//Globals
	private static int N; 
	private static ArrayList<LetterNode> words = new ArrayList<LetterNode>();
	private static int[][] adjacencyMatrix; 
	static final int TABLE_SIZE = 304961; //for dictionary
	private static String[] table; 
	public static CQueue q = new CQueue(); 
	public static String trav = ""; 
	
	public static void main(String[] args) {
			
			if(args.length != 3 ) {
				System.out.println("Usage: java Hunt words.txt dictionary.txt N");
				System.exit(0);
			}
			N = Integer.parseInt(args[2]); 
			File file;
			file = new File(args[0]);
			File dictionary;
			dictionary = new File(args[1]); 
			table = new String[TABLE_SIZE]; 
			
			//304961 is next biggest double the prime for dictionary of size
			readFile(file); 
			secondRead(file); 
			//Read in dictionary
			readDictionary(dictionary); 
			//play(N); 
			
			//Now everything is set up 
	}
	
	public static void play(int n) {

		for(int i=0; i<words.size(); i++) {
			//ITERATING THROUGH EVERY NODE
			q.enQueue(words.get(i)); 
			System.out.println(q.rear.toChar()); 
			trav+=q.deQueue().getC(); 
			
			
			//Only iterate through upper half
			for(int j = i; j<words.size(); j++) {
				dfs(words.get(j).getID(), 0, 0, 0, 0); 
				
			}
			trav = ""; 
		}
	}
	public static int getID(char c) {
		for(int p=0; p<words.size(); p++) {
			if(words.get(p).getC() == c) {
				return p; 
			}
		}
		return -1; 
	}
	
	public static void dfs(int rowChar, int sP1, int sP2, int row, int col) {
		
		
		if(trav.length() == N) {
			System.out.println("String is length N"); 
			int lookup = hash(trav); 
			if(table[lookup]==null) {
				System.out.println("Found word: " + table[lookup]); 
			}
			trav = trav.substring(0, trav.length()-1); //remove one char
			q.deQueue(); 
			char temp = trav.charAt(trav.length()-1); 
			int next = getID(temp); 
			dfs(next, 0, 0, 0, 0); 
			
		}
		
		for(int i=rowChar; i<words.size(); i++) {
			if(adjacencyMatrix[row][i] == 1) {
				q.enQueue(words.get(i));
				trav+=words.get(i).getC(); 
				dfs(words.get(i).getID(), 0, 0, 0, 0); 
				
			}
		}
		q.deQueue();
		trav = trav.substring(0, trav.length()-1); 
		
		
	}
	
	//18 chars total 
	public static void readFile(File f) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(f));
			 
			String line; 
			int count = 0; 
			//First read, find positions of all chars
			while((line = reader.readLine())!=null) {
				if(Character.isAlphabetic(line.charAt(0))) {
					
					String coord = reader.readLine().replaceAll(" " , ""); 
					//Create a new letter node
					LetterNode l = new LetterNode(Integer.parseInt(coord), line.charAt(0), count); 
					words.add(l); 
					count++; 
				}
			}
			reader.close(); 
			adjacencyMatrix = new int[count][count];  //filled with 0s to start
			
		}
		catch(IOException e) {
			System.out.println("Problem reading file.");
			System.out.println(e); 
		}
		
	}
	
	public static void secondRead(File f) { //for adjacencies
		try {
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String line = reader.readLine(); 
			int count = 0; 
			
			while(line!=null) {
				 
				if(Character.isAlphabetic(line.charAt(0))) {
					//System.out.println(line.charAt(0));
					reader.readLine(); //skip 
					String adj = reader.readLine();  
					while(adj!=null && !Character.isAlphabetic((adj.charAt(0)))) {
					 
						String coord = adj.replaceAll(" " , "");
						//System.out.println(coord); 
						
						//Lookup which adjacency it is
						for(int i=0; i<words.size(); i++) {
							if(words.get(i).getPos() == Integer.parseInt(coord)) {
								adjacencyMatrix[count][words.get(i).getID()] =1; 
							}
						}
						adj = reader.readLine(); 
						
					}//next char soon
					count++; 
					line = adj; 
				}
			}
			reader.close(); 
//			for(int i =0; i<adjacencyMatrix[0].length; i++) {
//				for(int j = 0; j<adjacencyMatrix.length; j++ ) {
//					System.out.print(adjacencyMatrix[i][j]); 
//				}
//				System.out.print("\n"); 
//			}
			
		}
		catch(IOException e) {
			System.out.println("Problem reading file.");
			System.out.println(e); 
		}
	}
	
	public static void readDictionary(File file) {
		 
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			int inc = 1; 
			while((line = reader.readLine())!=null) {
				int index = hash(line); 
				int original = index; 
				do {
					
					if(table[index] == null) {
						//not found, empty slot
						table[index] = line; 
						break; 
					}
					else {
						//collision
						//System.out.println("Collision detected"); 
						index = (hash(line)+inc)%TABLE_SIZE;
						inc++; 
					}
				} while(index!=original); 
			} 
			reader.close(); 
//			//Table printing for testing
//			for(int i=0; i<table.length; i++) {
//				System.out.println(table[i]); 
//			}
			
		}
		catch(IOException e) {
			System.out.println("Problem reading file.");
			System.out.println(e);  
		}
		
	}
	
	//returns index of place to add, basic function from professor Allen's notes
	public static int hash(String key) {

		int hashVal = 0; 
		for(int i=0; i<key.length(); i++) {
			hashVal = 37*hashVal+key.charAt(i); 
		}
		hashVal%=TABLE_SIZE; 
		if(hashVal < 0)
			hashVal+=TABLE_SIZE;
		return hashVal; 
	}
}


