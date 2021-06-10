/**
 * 
 */
package generation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		myMazeFactory = new MazeFactory(); //Instantiate myMazeFactory
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
		//Instantiate a counter for the number of exits
		//Traverse along the outside of the maze
		//If there is no wallboard, iterate the counter
		//If the counter is 0, fail with a 0 message
		//If the counter is more than 1, fail with a more than 1 message
		//If the counter is 1, pass
		fail("Not yet implemented");
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
//	void testCorrectNumberOfRooms() { //May implement later, not sure what a good way to do this is
//		//Tests if the correct number of rooms has been generated
//		//Goal: Check that rooms are generating correctly 
//		fail("Not yet implemented");
//	}
	

}
