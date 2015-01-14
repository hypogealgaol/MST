//Most of the code / abstraction for this code
//comes from Professor Allen's Notes 	
public class DisjointSet {
	int[] parent; 
	
	public DisjointSet(int numel) {
		parent = new int[numel]; 
	}
	
	//Not sure if this is needed
	public void initialize() {
		for(int i = 0; i<parent.length; i++) {
			parent[i] = i; 
		}
	}
	public void union(int cID1, int cID2) {
		int px = find(cID1);
		int py = find(cID2);
		parent[px] = py; 
	}
	
	//input vert text ID
	public int find(int cityID) {
		
		while(parent[cityID]!=cityID)
			cityID=parent[cityID]; 
		return cityID; 
	}
}
