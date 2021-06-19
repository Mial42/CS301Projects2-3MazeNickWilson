package gui;
import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import generation.Maze;
import junit.extensions.TestSetup;
/**
 * Tests the ability of the WallFollower algorithm to solve mazes of varying complexity through
 * its robot.
 * @author Nicholas Wilson
 */
public class WallFollowerTest {
	/**
	 * The Controller used for testing.
	 */
	protected Controller testController;
	/**
	 * The robot used for testing.
	 */
	protected Robot testRobot;
	/**
	 * The RobotDriver used for testing.
	 */
	protected RobotDriver testRobotDriver;
	/**
	 * Creates a maze with a given size and perfect status.
	 * Using seed 56 and Prim's algorithm, because per Anshu
	 * Seed 56 Skill 2 Prim's algorithm will cause an infinite loop with WallFollower
	 * @param skill (the skill level of the Maze to be generated)
	 * @param perfect (boolean that gives the Maze's perfect status.
	 */
	protected Maze makeMaze(int skill, boolean perfect) {
		return null;
	}
	/**
	 * Create the robot, robotdriver and controller. Will need to do the maze setup itself within
	 * each method, because each method requires different mazes.
	 */
	@BeforeEach
	public void setUp() {
		
	}
	/**
	 * Tests that WallFollower can solve a skill 0 perfect maze.
	 */
	@Test
	public void testPerfect0Maze() {
		//Set up Maze
		//Run the algorithm
		//Assert that the robot is at the exit
	}
	/**
	 * Tests that WallFollower can solve a skill 1 perfect maze.
	 */
	@Test
	public void testPerfect1Maze() {
		//Set up Maze
		//Run the algorithm
		//Assert that the robot is at the exit
	}
	/**
	 * Tests that WallFollower can solve a skill 2 perfect maze.
	 */
	@Test
	public void testPerfect2Maze() {
		//Set up Maze
		//Run the algorithm
		//Assert that the robot is at the exit
	}
	/**
	 * Tests that WallFollower can solve a skill 3 perfect maze.
	 * Will probably run out of energy, not sure until I test it.
	 */
	@Test
	public void testPerfect3Maze() {
		//Set up Maze
		//Run the algorithm
		//Assert that the robot is at the exit OR that it is stopped and very low on energy (less than 4)
		//Less than 4 because that's what the robot requires to move or turn left or right
		//Robot shouldn't stop with lots of energy in the tank
	}
	/**
	 * Tests that WallFollower can solve a skill 4 perfect maze.
	 * Will probably run out of energy, not sure until I test it.
	 */
	@Test
	public void testPerfect4Maze() {
		//Set up Maze
		//Run the algorithm
		//Assert that the robot is at the exit OR that it is stopped and very low on energy (less than 4)
		//Less than 4 because that's what the robot requires to move or turn left or right
		//Robot shouldn't stop with lots of energy in the tank
	}
	/**
	 * Tests that WallFollower can solve a skill 0 imperfect maze.
	 */
	@Test
	public void testRooms0Maze() {
		//Set up Maze
		//Run the algorithm
		//Assert that the robot is at the exit
	}
	/**
	 * Tests that WallFollower can solve a skill 1 imperfect maze.
	 */
	@Test
	public void testRooms1Maze() {
		//Set up Maze
		//Run the algorithm
		//Assert that the robot is at the exit
	}
	/**
	 * Tests if WallFollower can solve a skill 2 imperfect maze. Per 
	 * Anshu, it will get stuck in a loop and definitely run out of energy.
	 */
	@Test
	public void testRooms2Maze() {
		//Set up Maze
		//Run the algorithm
		//Assert that the robot is low on energy (< 4) and stopped
	}
	/**
	 * Tests that WallFollower can solve a skill 3 imperfect maze.
	 * Will probably run out of energy, not sure until I test it.
	 */
	@Test
	public void testRooms3Maze() {
		//Set up Maze
		//Run the algorithm
		//Assert that the robot is at the exit OR that it is stopped and very low on energy (less than 4)
		//Less than 4 because that's what the robot requires to move or turn left or right
		//Robot shouldn't stop with lots of energy in the tank
	}
	/**
	 * Tests that WallFollower can solve a skill 4 imperfect maze.
	 * Will probably run out of energy, not sure until I test it.
	 */
	@Test
	public void testRooms4Maze() {
		//Set up Maze
		//Run the algorithm
		//Assert that the robot is at the exit OR that it is stopped and very low on energy (less than 4)
		//Less than 4 because that's what the robot requires to move or turn left or right
		//Robot shouldn't stop with lots of energy in the tank
	}
}
