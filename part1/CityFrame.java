import java.util.*;
import java.io.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CityFrame extends JFrame {
	
	private final int WINDOW_HEIGHT = 600;
	private final int WINDOW_WIDTH = 800; 
	JScrollPane scrollpane;
	Panel panel, panel2; 
	JTextField textField;
	JButton button1, button2, button3; 
	int[] frequencies;  
	ArrayList<Edge> edges; 
	Heap edgeHeap; 
 
	public CityFrame(ArrayList<City> cities) {
		
		edges = new ArrayList<Edge>(); 
		edgeHeap = new Heap(); 
		
		//OH
		
		//Setting IDs for every city
		for(int y =0; y<cities.size(); y++) {
			cities.get(y).setID(y); //giving every tree a unique ID to compare for Queue 
		}
		
		//Calculating all edge pairs
		int count = 0; 
		for(int i =0; i<cities.size(); i++) {
			
			//Giving each city a unique ID
			
			for(int j = i+1; j<cities.size(); j++) {
				//Calculate distance, this loop technique works because it is a K graph
				System.out.print(cities.get(i).getName() + " " + cities.get(j).getName() + " "); 
				
				int dx = Math.abs(cities.get(i).getX() - cities.get(j).getX()); 
				int dy = Math.abs(cities.get(i).getY() - cities.get(j).getY()) ;
				int dist = (int)(Math.sqrt(dx*dx + dy*dy)); 
				System.out.print(dist + "\n");
				
				//adding to edge array
				Edge e = new Edge(cities.get(i), cities.get(j), dist); 
				edges.add(e); 
				edgeHeap.insert(e);
			}
		}
		
		//Setting up minimum spanning tree stuff
		ArrayList<Edge> mst = new ArrayList<Edge>(); 
		DisjointSet ds = new DisjointSet(cities.size()); 
		ds.initialize(); 

		//Kruskal
		while(mst.size()!=(cities.size()-1)) {
			Edge temp = (Edge)edgeHeap.deleteMin(); 
			//System.out.println(temp.getC1().getName()); 
			int c1 = temp.getC1().getID();
			int c2 = temp.getC2().getID(); 
			
			int set1 = ds.find(c1);
			int set2 = ds.find(c2); 
			
			if(set1!=set2) {
				mst.add(temp); 
				ds.union(c1, c2);
			}
		}
		
		//Panel Stuff
		panel = new Panel(cities, mst);
		panel.setPreferredSize(new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT)); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		scrollpane = new JScrollPane(panel);
	    getContentPane().add(scrollpane, BorderLayout.CENTER);
		
		button1 = new JButton("Draw City Points"); 
		button2 = new JButton("Connect City Points");
		button3 = new JButton("Find MST"); 
		
		//===ACTION LISTENERS===
		button1.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				panel.setOption(1);
				panel.repaint(); 
			}
		}); 
		button2.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				panel.setOption(2);
				panel.repaint(); 
			}
		}); 
		button3.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				panel.setOption(3);
				panel.repaint(); 
			}
		});
		panel.add(button1); 
		panel.add(button2);
		panel.add(button3); 
		pack(); 
		
		
		
	}
	
	
	
	//==========================
	// PANEL ------------------
	//==========================
	class Panel extends JPanel{
		
		int xs;
		int yx; 
		int option; 

		ArrayList<City> c; 
		ArrayList<Edge> mst; 

		public Panel(ArrayList<City> cities, ArrayList<Edge> mst) {
			setBackground(Color.white);
			setForeground(Color.black);
			this.c = cities; 
			option = 0; 
			this.mst = mst; 
		}
		protected void paintComponent(Graphics g) {
			g.setColor(getBackground()); //colors the window
		    g.fillRect(0, 0, getWidth(), getHeight());
		    g.setColor(getForeground()); //set color and fonts
		    Font MyFont = new Font("Arial",Font.PLAIN,11);
		    g.setFont(MyFont);
		    
		    xs = 40;
		    yx = 50;
		    
		    if(option == 1) {
		    	drawGraph(g, c, 0); 
		    }
		    if(option == 2) {
		    	connectGraph(g, c); 
		    	drawGraph(g, c, 0); 
		    }
		    if(option == 3) {
		    	drawMST(g, mst); 
		    	drawGraph(g, c, 0); 
		    }
		    
		}
		
		//Just drawing the points
		public void drawGraph(Graphics g, ArrayList<City> c, int option) {
			g.setColor(getBackground()); 
			
			for(int i =0; i<c.size(); i++) {
				int xc = c.get(i).getX()/4; //we lose data here because we have to convert into integer
				int yc = WINDOW_HEIGHT - c.get(i).getY()/4;
				
				int absX = xc+75;
				int absY = yc-100; 
				
				//For edge calculation 
				c.get(i).setGlobalX(absX);
				c.get(i).setGlobalY(absY);
				
				
				g.setColor(Color.black);
				g.drawOval(absX, absY, 10, 10);
				g.fillOval(absX, absY, 10, 10);
				g.drawString(c.get(i).getName(), xc+90, yc+10-100);
				
			}
			
		}
		
		public void drawMST(Graphics g, ArrayList<Edge> mst) {
			
			g.setColor(Color.orange);;
			
			//5 is just to account for the radius of the circles which are 10x10
			for(int i =0; i<mst.size(); i++) {
				g.drawLine(mst.get(i).getC1().getGlobalX()+5, 
						   mst.get(i).getC1().getGlobalY()+5,
						   mst.get(i).getC2().getGlobalX()+5,
						   mst.get(i).getC2().getGlobalY()+5); 
			}
			
		}
		
		public void connectGraph(Graphics g, ArrayList<City> c) {
			
			g.setColor(Color.cyan);
			for(int i =0; i<c.size(); i++) {
				
				for(int j = i+1; j<c.size(); j++) {
					
					g.drawLine(c.get(i).getGlobalX()+5, c.get(i).getGlobalY()+5, 
							c.get(j).getGlobalX()+5, c.get(j).getGlobalY()+5);
				}
			}
		}
		public void setOption(int x) {
			option = x; 
		}
	}		

}
