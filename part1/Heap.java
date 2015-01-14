import java.util.*; 
/*This heap is a heap that was gotten from the textbook, used on assignment
4 as well as this one*/ 
public class Heap  {
	
	private int currentSize; 
	private Comparable[] array; 
	private static final int DEFAULT_CAPACITY = 100; 

	// check what types we are using (not object nece) 
	public Heap(Comparable[] items) {
		currentSize = items.length;
		array = new Comparable[(currentSize*2)*11/10];
		
		int i = 1;
		for(Comparable item: items) {
			array[i++] = item;
		}
		buildHeap(); 
	}
	
	public Heap() {
		currentSize = 0;
		array = new Comparable[DEFAULT_CAPACITY + 1]; 
	}
	
	public void insert(Comparable x) {
		
		if(currentSize == array.length-1) {
			enlargeArray(array.length*2+1); 
		}
		
		int hole = ++currentSize;
		for(array[0] =x; x.compareTo(array[hole/2])<0; hole/=2) {
			array[hole] = array[hole/2];
		}
		array[hole] = x; 
	}
	
	public Comparable findMin() {
		if(isEmpty())
			return null;  
		return array[1];
	}
	
	public Comparable deleteMin() {
		//if empty 
			//what
		if(isEmpty())
			return null; 

		Comparable minItem = findMin();
		array[1] = array[currentSize--];
		percDown(1);

		return minItem;
	}
	
	public int getSize() {
		return currentSize; 
	}
	
	public boolean isEmpty() {
		return currentSize == 0; 
	}
	
	public void makeEmpty() {
		currentSize = 0; 
	}
	
	private void percDown(int hole) {
		
		int child; 
		Comparable temp  = array[hole];
		
		for(; hole*2<=currentSize; hole = child) {
			child = hole*2; 
			if(child!=currentSize && array[child+1].compareTo(array[child])< 0) {
				child++; 
			}
			
			if(array[child].compareTo(temp)<0) {
				array[hole] = array[child];
			}
			else
				break;
			
		}
		array[hole] = temp; 
	}
	
	private void buildHeap() {
		for (int i = currentSize/2; i>0; i--) {
			percDown(i); 
		}
	}
	
	private void enlargeArray(int newSize) {
		Comparable[] old = array;
		array = (Comparable[]) new Comparable[newSize];
		for(int i = 0; i <old.length;i++)
			array[i] = old[i];
		
	}
}
