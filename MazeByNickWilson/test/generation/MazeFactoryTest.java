/**
 * 
 */
package generation;

import static org.junit.Assert.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import generation.Order.Builder;

/**
 * @author Nicholas Wilson
 *
 */
class MazeFactoryTest {
	private MazeFactory myMazeFactory; //MazeFactory to run tests on
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testExactlyOneExit() {
		//Tests if the generated maze has exactly one exit
		//Goal: Check if there is exactly one exit
		//Create the maze
		Maze tempMaze = makeMaze(5, Builder.DFS, false, 10); //Builder can be changed to different builders as needed
		//Instantiate a counter for the number of exits, record the height and width, create a variable for the floorplan
		int numExits = 0;
		Floorplan tempFloorplan = tempMaze.getFloorplan();
		int height = tempFloorplan.getHeight();//gui.Constants.SKILL_Y[5]; //There should really be some better way to do this
		int width = tempFloorplan.getWidth();//gui.Constants.SKILL_X[5]; //Maybe add a getHeight and getWidth method to floorplans
		//Traverse along the outside of the maze
		//If there is no wallboard, iterate the counter
		for(int x = 0; x < width; x++) {
			if(tempFloorplan.isExitPosition(x, 0)) { //Top border
				numExits++;
			}
			if(tempFloorplan.isExitPosition(x, height - 1)) { //Bottom border
				numExits++;
			}
		}
		for(int y = 1; y < height - 1; y++) { //Don't double-count corners
			if(tempFloorplan.isExitPosition(0, y)){ //Left side
				numExits++;
			}
			if(tempFloorplan.isExitPosition(width - 1, y)){ //Right side
				numExits++;
			}
		}
		assertEquals(1, numExits);
		//If the counter is 1, pass
		//Else, fail
	}
	
	@Test
	void testNoRoomsWallsCount() {
		//Tests if the generated maze has the expected number of walls, given it has no rooms
		//Goal: Check that the number of internal wallboards is the expected number if there are no rooms
		//If the maze is square, this number is (n-1)^2, where n is the dimension of the maze (eg 3x3 maze has 9 cells)
		//If the maze is not square, it is (m-1)(n-1), where m is the width and n is the height of the maze
		//Eg, a 4 x 5 perfect maze should have 3*4 = 12 internal wallboards
		//Create the maze with no rooms
		Maze tempMaze = makeMaze(5, Builder.DFS, true, 20);
		Floorplan tempFloorplan = tempMaze.getFloorplan();
		int height = tempFloorplan.getHeight();
		int width = tempFloorplan.getWidth();
		//Instantiate a counter for internal wallboards
		int internalWallboards = 0;
		//Traverse the floorplan row by row, skipping the last cell in each row
		//If the cell has a wallboard to the right, iterate the counter
		//Traverse the floorplan column by column, skipping the last cell in each column
		//If the cell has a wallboard to the bottom, iterate the counter
		for(int x = 0; x < width; x++){//x is the column; skip the last column
			for(int y = 0; y < height; y++) {//y is the row, skip the last row
				if(x < width - 1 && tempFloorplan.hasWall(x, y, CardinalDirection.East)) {
					internalWallboards++;
				}
				if(y < height - 1 && tempFloorplan.hasWall(x, y, CardinalDirection.South)) {
					internalWallboards++;
				}
			}
		}		
		//If the counter is equal to the correct number, pass
		assertEquals((width - 1) * (height - 1), internalWallboards);
	}
	
	@Test
	void testAllCellsReachable() {
		//Tests if every cell in the maze has a valid path to the exit
		//Goal: Check that every cell in the maze has a valid path to the exit
		//Create the maze
		Maze tempMaze = makeMaze(5, Builder.DFS, false, 20);
		Floorplan tempFloorplan = tempMaze.getFloorplan();
		int height = tempFloorplan.getHeight();
		int width = tempFloorplan.getWidth();
		//Get an array of the distance values
		int[][] distanceArray = tempMaze.getMazedists().getAllDistanceValues();
		//Traverse through every cell in the maze
		//Check the cell's distance from the exit
		//Check if it has a neighbor with a distance one less than its distance
		//If every cell meets the above condition or has a distance of 1 (minimum distance), every cell has a route to the exit
		for(int x = 0; x < width; x++){//x is the column; skip the last column
			for(int y = 0; y < height; y++) {//y is the row, skip the last row
				int tempDistance = distanceArray[x][y]; //Distance from this cell to the exit
				if(tempDistance < 1){ //If distance is less then one, there's a problem
					fail("Distance below minimum");
				} //If distance is equal to one, you're at the minimum possible distance
				if(tempDistance > 1) { //If distance is greater than 1, look for a neighbor with a distance one less than yours
					//4 possible neighbors
					boolean lesserNeighbor = false; //Temporary boolean recording if one neighbor has a distance ones less than yours
					if(y > 0 && !tempFloorplan.hasWall(x, y, CardinalDirection.North) && tempDistance - distanceArray[x][y - 1] == 1) {
						//If conditions checks if North neighbor exists and has a distance one less than my distance
						lesserNeighbor = true;
					}
					else if(y < height - 1 && !tempFloorplan.hasWall(x, y, CardinalDirection.South) && tempDistance - distanceArray[x][y + 1] == 1) { //Else if because if one neighbor is has a distance one less than yours, you can stop
						//South neighbor
						lesserNeighbor = true;
					}
					else if(x < width - 1 && !tempFloorplan.hasWall(x, y, CardinalDirection.East) && tempDistance - distanceArray[x + 1][y] == 1) {
						//East neighbor
						lesserNeighbor = true;
					}
					else if(x > 0 && !tempFloorplan.hasWall(x, y, CardinalDirection.West) && tempDistance - distanceArray[x - 1][y] == 1) {
						//West neighbor
						lesserNeighbor = true;
					}
					else {
						fail("Has no neighbors");
					}
					if(lesserNeighbor == false) {
						fail("No lesser neighbor");
					}
				}
			}
		}	
	}
	
	@Test
	void testHasRooms() {
		//Tests if rooms have generated in the maze
		//Goal: make sure rooms can generate in the maze
		//Create a maze with rooms
		Maze tempMaze = makeMaze(5, Builder.DFS, false, 20);
		Floorplan tempFloorplan = tempMaze.getFloorplan();
		int height = tempFloorplan.getHeight();
		int width = tempFloorplan.getWidth();
		if(width >= 12 && height >= 12) { //If the maze is small, no rooms may be generated correctly
			//Check if the floorplan overlaps with a room at some point
			assertTrue(tempFloorplan.areaOverlapsWithRoom(1, 1, width - 2, height - 2)); 
			//Cover the entire floorplan except for the cells touching the border, since those will always return true
		}

	}
	
	@Test
	void testGenerateDifferentSeeds() {
		//Generate two mazes of the same size with different seeds, check that they're different
		//Goal: Make sure implementation takes seed into account
		//Create a maze with a certain seed
		//Perfect maze so rooms don't mess things up, since rooms might create different mazes with the same seed
		Maze maze10 = makeMaze(5, Builder.DFS, true, 10); 
		//Create a maze of the same size, with a different seed
		Maze maze20 = makeMaze(5, Builder.DFS, true, 20);
		//Compare the floorplans of the two
		//If they're equal, fail
		//Else, succeed
		assertFalse(maze10.getFloorplan().equals(maze20.getFloorplan()));
	}
	
	private Maze makeMaze(int skill, Builder b, boolean p, int s) {
		//Makes a Maze with a given skill, builder, perfect status, and seed
		StubOrder myOrder = new StubOrder(skill, b, p, s);
		MazeFactory myMazeFactory = new MazeFactory();
		myMazeFactory.order(myOrder);
		myMazeFactory.waitTillDelivered();
		return myOrder.getMaze();
	}
//	void testCorrectNumberOfRooms() { //May implement later, not sure what a good way to do this is
//		//Tests if the correct number of rooms has been generated
//		//Goal: Check that rooms are generating correctly 
//		fail("Not yet implemented");
//	}
	

}
