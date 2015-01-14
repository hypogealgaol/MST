import java.util.*;
import java.io.*;

import javax.swing.*;

import java.awt.*;

//Executable 
public class Fibroconnect {
	
	public static ArrayList<City> cities = new ArrayList<City>(); 

	public static void main(String[] args) {
		
		if(args.length == 0 ) {
			
			System.out.println("You must run the program with the second arg being the "
					+ ".txt file cityxy.txt");
			System.exit(0);
		}
		File file;
		file = new File(args[0]);
		readFile(file); 
		System.out.println("--------------------"); 
		CityFrame f = new CityFrame(cities); 
		f.setVisible(true); 
		
	}
	
	//For testing purposes
	public static void printArray() {
		for(int i=0; i<cities.size(); i++) {
			System.out.println("Name:\t" + cities.get(i).getName());
			System.out.println("X:\t" +cities.get(i).getX()); 
			System.out.println("Y:\t" +cities.get(i).getY()); 
		}
	}
	
	//Meat
	public static void readFile(File f) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String line;
			int xCoord = 0;
			int yCoord = 0;
			String name = ""; 

			//Iterating through the file objects
			while((line = reader.readLine())!=null) {
				//Name
				name = line.substring(0, line.indexOf(" ")); 
				
				String coordPair = line.substring(line.indexOf(" "));
				
				//Removing leading spaces (since there's an uneven amount) 
				int probe = 0; 
				String xCoordinate = ""; 
				boolean rightIndex = false; 
				while(!rightIndex) {
					char c = coordPair.charAt(probe); 
					if(c == ' ')
						probe++;
					else {
						rightIndex = true;
						xCoordinate = coordPair.substring(probe); 
						xCoord = Integer.parseInt(xCoordinate.substring(0, xCoordinate.indexOf(' '))); 
					}	
				}
				
				//Finding second instanceof input
				probe = 0; 
				String yCoordinate = ""; 
				rightIndex = false; 
				while(!rightIndex) {
					char c = xCoordinate.charAt(probe); 
					if(c == ' ') {
						rightIndex = true;
						yCoordinate = xCoordinate.substring(probe);
					}
					else {
						probe++; 
					}
				}
				
				//final Instance
				probe = 0;  
				rightIndex = false; 
				while(!rightIndex) {
					char c = yCoordinate.charAt(probe); 
					if(c == ' ') {
						probe++; 
					}
					else {
						rightIndex = true;
						yCoordinate = yCoordinate.substring(probe);
						yCoordinate.replaceAll(" ", ""); 
						yCoord = Integer.parseInt(yCoordinate.substring(0)); 
						//System.out.println("Y:"+yCoordinate); 
					}
				}
				
				//Adding to list
				City c = new City(name, xCoord, yCoord);
				cities.add(c); 
			} 
			reader.close();
		}
		catch(IOException e) {
			System.out.println("Problem reading file.");
			System.out.println(e); 
		}
	}
}
