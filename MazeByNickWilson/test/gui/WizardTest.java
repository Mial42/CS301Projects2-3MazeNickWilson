package gui;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import generation.Maze;

/**
 * Tests the ability of the Wizard algorithm to solve mazes of varying complexity through
 * its robot. Tests are very similar to the WallFollower class, except is should never fall into an infinite
 * loop, and should only ever fail due to lack of energy.
 * Responsibilities: Ensure that Wizard can solve mazes,
 * Collaborators: Controller, JFrame, Robot, RobotDriver
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
	 * Checks that Wizards can solve even relatively large Mazes quickly given infinite energy.
	 * Solves a size 10 maze with rooms. 
	 * @throws Exception 
	 */
	@Test
	public void testRooms10Maze() throws Exception {
		Maze testMaze = makeMaze(10, false);
		//Set up Maze
		finishSetup(testMaze);
		//Run the algorithm
		boolean hasCompletedMaze = testRobotDriver.drive2Exit();
		//Assert that the robot is at the exit
		assertTrue(hasCompletedMaze);
	}
}
