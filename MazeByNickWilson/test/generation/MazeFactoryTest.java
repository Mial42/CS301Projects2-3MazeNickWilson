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
		//Instantiate a counter for internal wallboards
		//Traverse the floorplan row by row, skipping the last cell in each row 
		//If the cell has a wallboard to the right, iterate the counter
		//If the counter is equal to the correct number, pass
		fail("Not yet implemented");
	}
	
	@Test
	void testAllCellsReachable() {
		//Tests if every cell in the maze has a valid path to the exit
		//Goal: Check that every cell in the maze has a valid path to the exit
		//Create the maze
		//Traverse through every cell in the maze
		//Check the cell's distance from the exit
		//Check if it has a neighbor with a distance one less than its distance
		//If every cell meets the above condition, every cell has a route to the exit
		//If not, fail
		fail("Not yet implemented");
	}
	
	@Test
	void testHasRooms() {
		//Tests if rooms have generated in the maze
		//Goal: make sure rooms can generate in the maze
		//Create a maze with rooms
		//Traverse through each cell, looking at wallboards
		//If an internal wallboard is a "border," pass
		//If not, fail
		fail("Not yet implemented");
	}
	
	@Test
	void testGenerateDifferentSeeds() {
		//Generate two mazes of the same size with different seeds, check that they're different
		//Goal: Make sure implementation takes seed into account
		//Create a maze with a certain seed
		//Create a maze of the same size, with a different seed
		//Compare the two
		//If they're equal, fail
		//Else, succeed
		fail("Not yet implemented");
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
