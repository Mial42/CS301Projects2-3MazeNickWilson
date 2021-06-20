package gui;
import static org.junit.Assert.*;

import java.awt.event.KeyListener;

import javax.swing.JFrame;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import generation.Maze;
import generation.MazeFactory;
import generation.StubOrder;
import generation.Order.Builder;
import gui.Constants.UserInput;
import gui.Robot.Direction;
import junit.extensions.TestSetup;
/**
 * Tests the ability of the WallFollower algorithm to solve mazes of varying complexity through
 * its robot.
 * @author Nicholas Wilson
 */
public class WallFollowerTest {
	/**
	 * A JFrame used to display the progress of the algorithm as it is run.
	 */
	protected JFrame displayFrame;
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
	 * Tells the program to check for an infinite loop.
	 * Exists because Wizard doesn't have infinite loops, and 
	 * I'd like to reuse tests between them. Set this to true for WallFollower in 
	 * unInheritableSetup.
	 */
	protected boolean testInfiniteLoops = false;
	/**
	 * Tells the program to check for a power shortage.
	 * Exists because Wizard solves mazes too quickly for power shortages, and 
	 * I'd like to reuse tests between Wizard and WallFollower.
	 * Set this to true for WallFollower in 
	 * unInheritableSetup.
	 */
	protected boolean testPowerShortage = false;
	/**
	 * Creates a maze with a given size and perfect status.
	 * Using seed 56 and Prim's algorithm, because per Anshu
	 * Seed 56 Skill 2 Prim's algorithm will cause an infinite loop with WallFollower
	 * @param skill (the skill level of the Maze to be generated)
	 * @param perfect (boolean that gives the Maze's perfect status.
	 */
	protected Maze makeMaze(int skill, boolean perfect) {
		StubOrder myOrder = new StubOrder(skill, Builder.Prim, perfect, 56);
		MazeFactory myMazeFactory = new MazeFactory();
		myMazeFactory.order(myOrder);
		myMazeFactory.waitTillDelivered();
		return myOrder.getMaze();
	}
	/**
	 * Create the robot, robotdriver and controller. Will need to do the maze setup itself within
	 * each method, because each method requires different mazes.
	 */
	@BeforeEach
	public void setUp() {
		testController = new Controller();
		testRobot = new BasicRobot(testController);
		unInheritableSetup();
		testRobotDriver.setRobot(testRobot);
		testController.setRobotAndDriver(testRobot, testRobotDriver);
		//Instantiates the JFrame and adds the controller and keylisteners to it
		displayFrame = new JFrame();
		displayFrame.add(testController.getPanel());
		KeyListener kListener = new SimpleKeyListener(displayFrame, testController);
		displayFrame.addKeyListener(kListener);
		displayFrame.setSize(Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT+22) ;
		displayFrame.setVisible(true) ;
		// focus should be on the JFrame of the MazeApplication and not on the maze panel
		// such that the SimpleKeyListener kl is used
		displayFrame.setFocusable(true) ;
		//Start the game with the Controller
		testController.start();
	}
	/**
	 * Get rid of superfluous JFrames to avoid cluttering the screen.
	 */
	@AfterEach
	public void tearDown() {
		displayFrame.dispose();
	}
	/**
	 * This method concentrates all the setup that changes with the Wizard subclass. This instantiates a
	 * testRobotDriver as a WallFollower.
	 */
	protected void unInheritableSetup() {
		testRobotDriver = new WallFollower();
		testInfiniteLoops = true;
		testPowerShortage = true;
	}
	/**
	 * This method finishes setting up the RobotDriver, Robot, and Controller by swapping Controller
	 * from generating to playing, adding the maze to the RobotDriver, and adding functional sensors to the robot
	 * @param maze (a Maze that is required to finish the setup of the Robot, RobotDriver, Controller, etc)
	 */
	protected void finishSetup(Maze maze) {
		testRobotDriver.setMaze(maze);
		testController.switchFromGeneratingToPlaying(maze);
		testRobot.addDistanceSensor(new BasicSensor(), Direction.FORWARD);
		testRobot.addDistanceSensor(new BasicSensor(), Direction.BACKWARD);
		testRobot.addDistanceSensor(new BasicSensor(), Direction.LEFT);
		testRobot.addDistanceSensor(new BasicSensor(), Direction.RIGHT);
		
		//Give the Robot lots of extra power so it can finish
		//testRobot.setBatteryLevel(10000);
		
		//Toggle minimap so it's easier to see what's going on
		testController.keyDown(UserInput.TOGGLELOCALMAP, 0);
		testController.keyDown(UserInput.TOGGLEFULLMAP, 0);
		testController.keyDown(UserInput.TOGGLESOLUTION, 0);
	}
	/**
	 * Tests that WallFollower can solve a skill 0 perfect maze.
	 * @throws Exception 
	 */
	@Test
	public void testPerfect0Maze() throws Exception {
		//Set up Maze
		Maze testMaze = makeMaze(0, true);
		finishSetup(testMaze);
		//Run the algorithm
		boolean hasCompletedMaze = testRobotDriver.drive2Exit();
		//Assert that the robot is at the exit
		assertTrue(hasCompletedMaze);
	}
	/**
	 * Tests that WallFollower can solve a skill 1 perfect maze.
	 * @throws Exception 
	 */
	@Test
	public void testPerfect1Maze() throws Exception {
		//Set up Maze
		Maze testMaze = makeMaze(1, true);
		finishSetup(testMaze);
		//Run the algorithm
		boolean hasCompletedMaze = testRobotDriver.drive2Exit();
		//Assert that the robot is at the exit
		assertTrue(hasCompletedMaze);
	}
	/**
	 * Tests that WallFollower can solve a skill 2 perfect maze.
	 * @throws Exception 
	 */
	@Test
	public void testPerfect2Maze() throws Exception {
		//Set up Maze
		Maze testMaze = makeMaze(2, true);
		finishSetup(testMaze);
		//Run the algorithm
		boolean hasCompletedMaze = testRobotDriver.drive2Exit();
		//Assert that the robot is at the exit
		assertTrue(hasCompletedMaze);
	}
	/**
	 * Tests that WallFollower can solve a skill 3 perfect maze.
	 * Runs out of energy unless a Wizard.
	 * @throws Exception 
	 */
	@Test
	public void testPerfect3Maze() throws Exception {
		//Set up Maze
		Maze testMaze = makeMaze(3, true);
		finishSetup(testMaze);
		//Run the algorithm
		boolean hasCompletedMaze = false; 
		boolean threwCorrectError = false; //Make sure error is getting thrown right.
		try{
			hasCompletedMaze = testRobotDriver.drive2Exit();
		}
		catch(Exception e){ //If it runs out of energy it should throw an exception.
			//Assert that the robot is very low on energy (less than 4)
			//Less than 4 because that's what the robot requires to move or turn left or right
			//Robot shouldn't stop with lots of energy in the tank
			//As it happens it runs out of energy per visual testing
			assertTrue(testRobot.getBatteryLevel() < testRobot.getEnergyForStepForward());
			threwCorrectError = true;
		}
		if(!testPowerShortage) { //If a Wizard, shoould still complete the Maze
			assertTrue(hasCompletedMaze);
		}
		else {
			assertTrue(threwCorrectError);
		}
	}
	/**
	 * Tests that WallFollower can solve a skill 4 perfect maze.
	 * Runs out of energy unless a Wizard.
	 * @throws Exception 
	 */
	@Test
	public void testPerfect4Maze() throws Exception {
		//Set up Maze
		Maze testMaze = makeMaze(4, true);
		finishSetup(testMaze);
		//Run the algorithm
		boolean hasCompletedMaze = false;
		boolean threwCorrectError = false; //Make sure error is getting thrown right.
		try{
			hasCompletedMaze = testRobotDriver.drive2Exit();
		}
		catch(Exception e){//If it runs out of energy it should throw an exception.
			//Assert that the robot is very low on energy (less than 4)
			//Less than 4 because that's what the robot requires to move or turn left or right
			//Robot shouldn't stop with lots of energy in the tank
			//As it happens it runs out of energy per visual testing
			assertTrue(testRobot.getBatteryLevel() < testRobot.getEnergyForStepForward());
			threwCorrectError = true;
		}
		if(!testPowerShortage) { //If a Wizard, shoould still complete the Maze
			assertTrue(hasCompletedMaze);
		}
		else {
			assertTrue(threwCorrectError);
		}
	}
	/**
	 * Tests that WallFollower can solve a skill 0 imperfect maze.
	 * @throws Exception 
	 */
	@Test
	public void testRooms0Maze() throws Exception {
		//Set up Maze
		Maze testMaze = makeMaze(0, false);
		finishSetup(testMaze);
		//Run the algorithm
		boolean hasCompletedMaze = testRobotDriver.drive2Exit();
		//Assert that the robot is at the exit
		assertTrue(hasCompletedMaze);
	}
	/**
	 * Tests that WallFollower can solve a skill 1 imperfect maze.
	 */
	@Test
	public void testRooms1Maze() throws Exception {
		//Set up Maze
		Maze testMaze = makeMaze(1, false);
		finishSetup(testMaze);
		//Run the algorithm
		boolean hasCompletedMaze = testRobotDriver.drive2Exit();
		//Assert that the robot is at the exit
		assertTrue(hasCompletedMaze);
	}
	/**
	 * Tests if WallFollower can solve a skill 2 imperfect maze. Per 
	 * Anshu, it will get stuck in a loop and definitely run out of energy.
	 * I've given the robot massive additional energy so that it fails due to looping
	 * rather than running out of energy.
	 */
	@Test
	public void testRooms2Maze() throws Exception {
		//Set up Maze
		Maze testMaze = makeMaze(2, false);
		finishSetup(testMaze);
		testRobot.setBatteryLevel(999999);
		//Run the algorithm
		boolean hasCompletedMaze = testRobotDriver.drive2Exit();
		if(testInfiniteLoops) { //If testing for loops, should not have finished the maze
			assertFalse(hasCompletedMaze);
			}
		else {
			assertTrue(hasCompletedMaze);
		}
	}
	/**
	 * Tests that WallFollower can solve a skill 3 imperfect maze.
	 * Runs out of energy unless a Wizard.
	 */
	@Test
	public void testRooms3Maze() throws Exception {
		//Set up Maze
		Maze testMaze = makeMaze(3, false);
		finishSetup(testMaze);
		//Run the algorithm
		boolean hasCompletedMaze = false;
		boolean threwCorrectError = false; //Make sure error is getting thrown right.
		try{
			hasCompletedMaze = testRobotDriver.drive2Exit();
		}
		catch(Exception e){//If it runs out of energy it should throw an exception.
			//Assert that the robot is very low on energy (less than 4)
			//Less than 4 because that's what the robot requires to move or turn left or right
			//Robot shouldn't stop with lots of energy in the tank
			//As it happens it runs out of energy per visual testing
			assertTrue(testRobot.getBatteryLevel() < testRobot.getEnergyForStepForward());
			threwCorrectError = true;
		}
		if(!testPowerShortage) { //If a Wizard, shoould still complete the Maze
			assertTrue(hasCompletedMaze);
		}
		else {
			assertTrue(threwCorrectError);
		}
	}
	/**
	 * Tests that WallFollower can solve a skill 4 imperfect maze.
	 * Runs out of energy unless a Wizard.
	 */
	@Test
	public void testRooms4Maze() throws Exception {
		//Set up Maze
		Maze testMaze = makeMaze(4, false);
		finishSetup(testMaze);
		//Run the algorithm
		boolean hasCompletedMaze = false;
		boolean threwCorrectError = false; //Make sure error is getting thrown right.

		try{
			hasCompletedMaze = testRobotDriver.drive2Exit();
		}
		catch(Exception e){//If it runs out of energy it should throw an exception.
			//Assert that the robot is very low on energy (less than 4)
			//Less than 4 because that's what the robot requires to move or turn left or right
			//Robot shouldn't stop with lots of energy in the tank
			//As it happens it runs out of energy per visual testing
			assertTrue(testRobot.getBatteryLevel() < testRobot.getEnergyForStepForward());
			threwCorrectError = true;
		}
		if(!testPowerShortage) { //If a Wizard, shoould still complete the Maze
			assertTrue(hasCompletedMaze);
		}
		else {
			assertTrue(threwCorrectError);
		}
	}
}
