
public class LetterNode {
	
	private int ID; 
	private Integer position; 
	private char c; 
	private boolean visited; 

	public LetterNode(Integer coord, char c, int ID) {
		position = coord;  
		this.c = c; 
		this.ID = ID; 
		visited = false; 
	}
	public int getPos() {
		return position; 
	}
	public int getID() {
		return ID; 
	}
	public char getC() {
		return c; 
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
}
