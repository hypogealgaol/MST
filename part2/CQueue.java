// Simple Queue using a Circular Linked List type Data Structure
// rear.next always point to front of queue 

//C stands for coordinate in this case
public class CQueue
{
	public CNode rear;
	
    public CQueue( ) {
        rear=null;
    }

    public boolean isEmpty() {
    	
    	return (rear==null);
    }
    
    public void enQueue(LetterNode i) {
    	
        if (isEmpty()) {
           CNode tmp= new CNode(i,null);
           rear=tmp;
           tmp.next=rear; 
        }
        else {
            CNode front=rear.next;
            rear.next= new CNode(i,front);
            rear=rear.next;
        }
    }

    public LetterNode deQueue() {
        if(isEmpty()){
        	System.out.println("error: dequeue from empty queue!") ;
        	return(null);
        }
        else {
        	CNode front=rear.next;
        	LetterNode x =front.data;
        	if(rear==rear.next) rear=null; //deQueue single element
            	else rear.next=front.next;
           return x;
        }
    }

    public void printQueue() { 
        System.out.print("the Queue: ");
	    if (!isEmpty()) {
	    	CNode p =rear.next;
	    	while (p!=rear){
	    		System.out.print(" " + p.data);
	    		p=p.next;
	    	} 
	    	System.out.print(" " + p.data);
	    	System.out.println();
	    } 
	    else System.out.println("Queue empty!");
    }

//	public static void main( String [ ] args ) {
//	   CQueue Q = new CQueue();
//	   Object x;  int i;
//	
//	   Q.printQueue();  //should print empty queue message 
//	
//	   for( i = 0; i < 5; i++ ) {
//	        System.out.print("Enqueue " + i + " ");
//	        Q.enQueue( new Integer( i ));
//	        Q.printQueue();
//	   }
//	   
//	   System.out.println( "do some dequeues..." );
//	   while(!Q.isEmpty()) {
//	     x = Q.deQueue();
//	     System.out.print( "Dequeued " +(Integer)x +" ");
//	     Q.printQueue();
//	   }
//	   
//	    Q.deQueue();  //error: dequeue from empty queue
//	
//	    // check to see queue is still ok after being emptied
//	    System.out.print("Enqueue " + 73 + " ");
//	    Q.enQueue(new Integer(73));
//	    Q.printQueue();
//	}
//
//}

    //CNode data is a LetterNode
	 class CNode {
		 
	    CNode(LetterNode x, CNode p)
	    {   
	        data=x;
	        next = p;
	    }
	
	    LetterNode data;
	    CNode next;
	    
	    public char toChar() {
	    	return data.getC();
	    }
	    
	}
}
 
//
//
//the Queue: Queue empty!
//Enqueue 0 the Queue:  0
//Enqueue 1 the Queue:  0 1
//Enqueue 2 the Queue:  0 1 2
//Enqueue 3 the Queue:  0 1 2 3
//Enqueue 4 the Queue:  0 1 2 3 4
//do some dequeues...
//Dequeued 0 the Queue:  1 2 3 4
//Dequeued 1 the Queue:  2 3 4
//Dequeued 2 the Queue:  3 4
//Dequeued 3 the Queue:  4
//Dequeued 4 the Queue: Queue empty!
//error: dequeue from empty queue!
//Enqueue 73 the Queue:  73
