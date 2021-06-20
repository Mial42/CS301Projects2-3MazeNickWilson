package gui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import generation.Maze;

/**
 * Tests the ability of the Wizard algorithm to solve mazes of varying complexity through
 * its robot. Tests are very similar to the WallFollower class, except is should never fall into an infinite
 * loop, and should only ever fail due to lack of energy.
 * @author Nicholas Wilson
 */
public class WizardTest extends WallFollowerTest {
	/**
	 * Overrides the instantiation of testRobotDriver from WallFollowerTest to
	 * a Wizard rather than a WallFollower.
	 */
	@Override
	protected void unInheritableSetup() {
		testRobotDriver = new Wizard();
	}
	/**
	 * Tests if Wizard can solve a skill 2 imperfect maze. WallFollower gets stuck,
	 * Wizard shouldn't.
	 */
	@Test
	public void testRooms2Maze() throws Exception {
				//Set up Maze
		Maze testMaze = makeMaze(2, false);
		finishSetup(testMaze);
			//Run the algorithm
		testRobotDriver.drive2Exit();
		//Assert that the robot is at the exit
	}
	/**
	 * Checks that Wizards can solve even relatively large Mazes quickly given infinite energy.
	 * Solves a size 15 maze with rooms. 
	 * @throws Exception 
	 */
	@Test
	public void testRooms15Maze() throws Exception {
		Maze testMaze = makeMaze(10, false);
		//Set up Maze
		finishSetup(testMaze);
		//Run the algorithm
		testRobotDriver.drive2Exit();
		//Assert that the robot is at the exit
	}
}
