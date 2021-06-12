package generation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

public class MazeBuilderKruskal extends MazeBuilder implements Runnable {
	public MazeBuilderKruskal() {
		super();
		System.out.println("MazeBuilderKruskaluses Kruskal's algorithm to generate maze.");
	}
	
	
	/**
	 * Kruskal's algorithm generates a spanning tree for a graph. The below is copied from MazeBuilderPrim.java:
	 * The cells are the nodes of the graph and the spanning tree. An edge represents that one can move from one cell to an adjacent cell.
	 * So an edge implies that its nodes are adjacent cells in the maze and that there is no wallboard separating these cells in the maze. 
	 */
	@Override
	protected void generatePathways() {
		//Create an array of arrays of Trees. Each Tree contains one node, so there should be width * height nodes in the set.
		//Create a list of all possible edges in the Maze
		//Given that a node is defined as an x,y pair, there can be 2, 3, or 4 possible edges per node
		//Then, loop until the list of edges is empty. Loop based on the seed.
		//Within that loop:
		//Remove the next edge from the list
		//Calculate the endpoint of the edge (for instance, if the edge has x coord 2, y coord 2, and points south, it's connected to x coord 2, y coord 3)
		//If the two nodes are not in the same set, merge their sets and remove the appropriate wall from the maze
		//Either way, discard the edge
		//Once all edges are gone, there should be one Tree of all nodes
		//Done
	}
	/**
	 * This method returns an ArrayList of all possible edges in a maze, represented as wallboards
	 * @return list of wallboards representing all possible edges
	 */
	private ArrayList<Wallboard> createListOfPossibleEdges(){
		//There are a total of n(m-1) + m(n-1) potential edges in the graph, not counting the borders
		//Use Wallboards to represent edges
		//Create all possible edges by going to the right for edges between horizontal nodes
		//And down for edges between vertical nodes
		return null; //TODO code this
	}
	/**
	 * Nodes are represented as x, y coordinates. In the array of arrays of Trees that this method returns, a Tree at
	 * the x, y positions on the array corresponds to the node at the x, y positions on the Maze
	 * Corresponds to the same coords in Floorplan
	 * @return array of array of Trees containing all possible nodes. Each Tree contains one node. 
	 */
	private Tree[][] createListOfSetsOfNodes(){
		//Loop through all possible x and y coordinate combinations, creating a new set each time with an array
		//Representing that 
		return null;
	}
	//Note from Nicholas Wilson: I did not make this Tree class. I copied it directly from here: https://github.com/psholtz/Puzzles/blob/master/mazes/maze-04/java/Kruskal.java
	//It is quite similar to the Ruby tree class from Buckblog (https://weblog.jamisbuck.org/2011/1/3/maze-generation-kruskal-s-algorithm.html). Rather than reproduce this tree myself, I decided to copy-paste the Java version from this source.
	//The purpose is to efficiently be able to merge sets, as described on Buckblog.
	/***********************************************************************
	 * We will use a tree structure to model the "set" (or "vertex") 
	 * that is used in Kruskal to build the graph.
	 * 
	 * @author psholtz
	 ***********************************************************************/
	private class Tree {
		
		private Tree _parent = null;
		
		//
		// Build a new tree object
		//
		public Tree() {
			
		}
		
		// 
		// If we are joined, return the root. Otherwise, return this object instance.
		//
		public Tree root() {
			return _parent != null ? _parent.root() : this;
		}
		
		// 
		// Are we connected to this tree?
		//
		public boolean connected(Tree tree) {
			return this.root() == tree.root();
		}
		
		//
		// Connect to the tree
		//
		public void connect(Tree tree) {
			tree.root().setParent(this);
		}
		
		//
		// Set the parent of the object instance
		public void setParent(Tree parent) {
			this._parent = parent;
		}
	}
}
