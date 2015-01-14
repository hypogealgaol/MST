
public class Edge implements Comparable {
	
	private City city1;
	private City city2; 
	private int weight; 
	
	public Edge(City c1, City c2, int w) {
		city1 = c1;
		city2 = c2;
		weight = w; 
	}
	public int getWeight() {
		return weight; 
	}
	public int compareTo(Object c) {
		Edge x = (Edge)c; 
		if(weight>x.weight) return 1;
		if(weight == x.weight) return 0;
		return -1;
	}
	public City getC1() {
		return city1;
	}
	public City getC2() {
		return city2; 
	}
	//useless
	public void print() {
		System.out.println("Edge between " + city1.getName() + " and " + city2.getName() + " of weight " + weight); 
	}

}
