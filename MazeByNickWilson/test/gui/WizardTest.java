package gui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the ability of the Wizard algorithm to solve mazes of varying complexity through
 * its robot. Tests are very similar to the WallFollower class, except is should never fall into an infinite
 * loop, and should only ever fail due to lack of energy.
 * @author Nicholas Wilson
 */
public class WizardTest extends WallFollowerTest {
	/**
	 * Create the robot, RobotDriver and controller. Overridden because the
	 * RobotDriver is a Wizard this time, not a base WallFollower. Will need to do the maze setup itself within
	 * each method, because each method requires different mazes.
	 */
	@BeforeEach@Override
	public void setUp() {
		
	}
	/**
	 * Tests if Wizard can solve a skill 2 imperfect maze. WallFollower gets stuck,
	 * Wizard shouldn't.
	 */
	@Test
	public void testRooms2Maze() {
		//Set up Maze
		//Run the algorithm
		//Assert that the robot is at the exit
	}
	/**
	 * Checks that Wizards can solve even very large Mazes quickly given infinite energy.
	 * Solves a size 15 maze with rooms. 
	 */
	@Test
	public void testRooms15Maze() {
		//Add a very large amount of energy to the robot.
		//Set up Maze
		//Run the algorithm
		//Assert that the robot is at the exit
	}
}
