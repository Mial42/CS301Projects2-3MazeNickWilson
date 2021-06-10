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
		//
		fail("Not yet implemented");
	}
	
	@Test
	void testNoRoomsWallsCount() {
		//Tests if the generated maze has the expected number of walls, given it has no rooms
		//
		fail("Not yet implemented");
	}
	
	@Test
	void testAllCellsReachable() {
		//Tests if every cell in the maze has a valid path to the exit
		//
		fail("Not yet implemented");
	}
	void testCorrectNumberOfRooms() {
		//Tests if the correct number of rooms has been generated
		//
		fail("Not yet implemented");
	}
	

}
