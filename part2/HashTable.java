
public class HashTable {

	private final int TABLE_SIZE = 304961; //for dictionary
	private String[] table; 
	
	public HashTable() {
		allocateArray(TABLE_SIZE);
	}
	public void allocateArray(int size) {
		table = new String[size];
	}
	public int getSize() {
		return table.length;
	}

}
