//don't think first comparable is needed on City but definitely needed on Edge 

public class City implements Comparable {
	//Coordinates + name 
	private String name;
	private int xC;
	private int yC; 
	private int globalX;
	private int globalY; 
	private boolean visited; 
	private int ID; 
	
	public City(String n, int x, int y) {
		name = n;
		xC = x;
		yC = y; 
		globalX = 0;
		globalY = 0; 
		visited = false; 
	}
	public int getX() {
		return xC; 
	}
	public int getY() {
		return yC;
	}
	public String getName() {
		return name; 
	}
	//Coordinates after modification
	public void setGlobalX(int a) {
		globalX = a; 	
	}
	public void setGlobalY(int a) {
		globalY = a; 
	}
	public int getGlobalX() {
		return globalX;  	
	}
	public int getGlobalY() {
		return globalY;  
	}
	public void setVisited() {
		visited = true; 
	}
	public void setNotVisited() {
		visited = false; 
	}
	public boolean getVisited() {
		return visited; 
	}
	public void setID(int i) {
		ID = i; 
	}
	public int getID() {
		return ID; 
	}
	//For traversing through vertexes based on ID
	public int compareTo(Object c) {
		City x = (City)c; 
		if(ID>x.ID) return 1;
		if(ID == x.ID) return 0;
		return -1;
	}
}
