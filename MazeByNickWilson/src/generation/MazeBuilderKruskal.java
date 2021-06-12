package generation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

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
		//Create a list of all possible edges in the Maze
		//Given that a node is defined as an x,y pair, there can be 2, 3, or 4 possible edges per node
		//There are a total of n(m-1) + m(n-1) potential edges in the graph, not counting the borders
		//Use Wallboards to represent edges
		//Create all possible edges by going to the right for edges between horizontal nodes
		//And down for edges between vertical nodes
		//Then, loop until the list of edges is empty. Loop based on the seed.
		//Within that loop
	}
	/**
	 * This method returns an ArrayList of all possible edges in a maze, represented as wallboards
	 */
	private ArrayList<Wallboard> createListOfPossibleEdges(){
		return null; //TODO code this
	}
}
