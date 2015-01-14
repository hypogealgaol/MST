README: 

part1: Executable file = Fibroconnect.java

	The program creates a GUI window in which there are no nodes displayed on default. To be able to show
	the MST and the K graph you must already display the nodes. So the buttons for those two won't work individually
	until the nodes are shown (you would have to press show MST / connct graph twice if you didn't press draw the first time). 

	For GUI adjustments I divided each of the cities by 4 and stored those in a variable called GlobalX/GlobalY for each city.
	These are the absolute coordinates in terms of the display. There are arbitrary values in some draw methods to correct for
	the display and making sure everything can be seen right away. 

	I created an arrayList of edges and put all of the edges in the heap so that the minimum ones would be found first.
	I used a disjoint set class similar to the one professor provided that initializes an array with each node corresponding
	to its own tree. I then did a union of those two (by changing the array cell to = the other vertex) and added the edge to the
	tree if it was not corresponding to an edge already in some set. 

	I used, to iterate through each edge (non repeating) a (n)(n-1)(n-2)...(1) equation by iterating through an innter for loop
	j+i times. 

	The loop to create the MSTonly runs through the # of vertices-1, so even with 0 or 1 vertices there shouldn't be any drawing mistakes. 
	
	Again: Disjoint set + Heap code credits go to Peter Allen 


part2: Executable file = Hunt.java

	NOTE: This program as of now does not have full functionality. It does everything except do the word search. The method is in its 
	starting stages but is not finished. Here is my skeleton of what this function should look like: 
	(note: q is a queue, el is the adjacency matrix element (just 1s or 0s for yes/no connection) 
	
	search(char x row, needed indexes)  
	
	for(row of char x)

	if string length == N (user input) 
		lookup string w/hash
		dequeue last character
		search(row of last character starting from index after dequeued character)
	
	for( each column in row with x with starting index passed in)  <--2ND LOOP
		if(el == 1)
			q.add(column)
			search(row of the letter of that column) 
	}
	
	//no more 1s left in that row
	dequeue last char
	search(row of char with starting point from last char+1)

	---
	The idea for this recursive method is that you iterate through the adjacency matrix where each row number corresponds to the letter
	read in. Whenever you see a 1 in the adjacency matrix you put the letter (an object) on the queue and continue to search using the row
	of the letter you just added. You simultaneously keep a global variable called word which adds a character to a string every time something is added
	to the queue, and removes a character when something is dequeued. 
	
	If the length of this string (word) becomes N, then we look up the string in the dictionary. If it's in the dictionary we print it. If not then 
	we do nothing. We then dequeue the last letter and iterate through the search again, this time using the row of the last character starting from
	the column of the dequeued character. 
	
	The recursive method should stop running when all the rows for the first letter have been exhausted (simple O(n))
	---
	
	The dictionary hashing and the file reading work perfectly. I did some simple test cases to see if words would be in the dictionary and collisions
	are dealt with as requested. The program reads through the file twice, first creating the adjacency matrix skeleton (by using only the row with the letter and
	the row directly under it). There is an arrayList called words where words(i) corresponds to the ith row of the adjacency matrix. To fill out the
	adjacencies, we test whether that arraylist cell has a position equal to a position that is being read (the x y z). If so, we mae that adjacency matrix
	cell 1. 
	
	The dictionary hashing was relatively simple, open addressing with linear probing -  the hash code slightly modified from Professor Allen's notes. 
	
	---
	The play method does not work but is the most important part (obviously). I explained how it should work above (iterate through the matrix and recursively 
	check the cells with edges). 
	
	