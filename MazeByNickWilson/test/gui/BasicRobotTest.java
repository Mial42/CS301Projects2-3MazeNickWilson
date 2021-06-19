package gui;
import static org.junit.Assert.*;
/**
 * Tests the individual methods of the BasicRobot.java class
 * Does not test RobotDriver at all.
 * @author Nicholas Wilson
 */

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import generation.CardinalDirection;
import generation.Maze;
import generation.MazeFactory;
import generation.StubOrder;
import gui.Robot.Direction;
import gui.Robot.Turn;
import generation.Order.Builder;

public class BasicRobotTest {
	private Controller testController;
	private Robot testRobot;
	/**
	 * Set up a controller and the appropriate robot
	 */
	@BeforeEach
	public void setUp() {
		//Instantiate a controller. Will use DFS because I know it works.
		//Will use skill 0, since I'm just smoke-testing robot methods.
		//Get a reference to the robot.
		testController = new Controller();
		testController.start();
		testController.turnOffGraphics(); //No graphics to save time
		testController.switchFromGeneratingToPlaying(makeTestMaze());
		testRobot = new BasicRobot(testController);
		testController.setRobotAndDriver(testRobot, null);
	}
	/**
	 * Check that position and direction return some non-null value equal to that returned by the controller's methods.
	 * Combined with the move and rotate tests, this provided a gut check about
	 * whether or not the method is actually working.
	 * @throws Exception 
	 */
	@Test
	public void testPositionAndDirection() throws Exception {
		//Check that position is not null
		assertTrue(testRobot.getCurrentPosition() != null);
		assertTrue(testRobot.getCurrentPosition()[0] == testController.getCurrentPosition()[0] 
				&& testRobot.getCurrentPosition()[1] == testController.getCurrentPosition()[1] );
		//Check that direction is not null and equals the direction recorded by the controller
		assertTrue(testRobot.getCurrentDirection() != null);
		assertTrue(testRobot.getCurrentDirection().equals(testController.getCurrentDirection()));
	}
	/**
	 * Check that the battery level for a new robot is correctly 2000, 
	 * that the setBatteryLevel method correctly changes the battery level,
	 * and that getEnergyForFullRotation and getEnergyForStepForward return the expected 
	 * values of 12 and 4.
	 */
	@Test
	public void testBatteryLevel() {
		//Check that battery level is currently 2000
		assertEquals(2000, testRobot.getBatteryLevel(), 0);
		//Set the battery level to 500
		testRobot.setBatteryLevel(500);
		//Check that the battery level is 500
		assertEquals(500, testRobot.getBatteryLevel(), 0);
		//Check that the energy for a full rotation is 12
		assertEquals(12, testRobot.getEnergyForFullRotation(), 0);
		//Check that the energy for a move is 4
		assertEquals(4, testRobot.getEnergyForStepForward(), 0);
	}
	/**
	 * Check that the odometer of a starting robot is 0. Move once.
	 * Check that the odometer is 1. Reset the odometer. Check that it is 0.
	 */
	@Test
	public void testOdometerReadings() {
		//Check that the odometer of a starting robot is 0.
		assertEquals(0, testRobot.getOdometerReading(), 0);
		while(testRobot.distanceToObstacle(Direction.FORWARD) == 0) {//If you start facing a wall
			testRobot.rotate(Turn.RIGHT); //turn until not facing a wall
		}
		//Move once.
		testRobot.move(1);
		//Check that the odometer is 1. 
		assertEquals(1, testRobot.getOdometerReading(), 0);
		//Reset the odometer. 
		testRobot.resetOdometer();
		//Check that it is 0.
		assertEquals(0, testRobot.getOdometerReading(), 0);
	}
	/**
	 * Tests that the move() method works as expected. This means that
	 * it must correctly move the parameter distance if there is no obstacle in front of it.
	 * If there is an obstacle in front of it, it should stop.
	 * @throws Exception 
	 */
	@Test
	public void testMove() throws Exception {
		//Rotate until not facing a wall
		while(testRobot.distanceToObstacle(Direction.FORWARD) < 1) {
			testRobot.rotate(Turn.RIGHT); 
		}
		//Get the distance to the nearest forward wall
		int originalDistance = testRobot.distanceToObstacle(Direction.FORWARD);
		int[] originalPos = testRobot.getCurrentPosition();
		//Move that distance
		testRobot.move(originalDistance);
		//Check that current position has changed the expected amount
		int[] newPos = testRobot.getCurrentPosition();
		assertTrue(Math.abs(originalPos[0] - newPos[0]) + Math.abs(originalPos[1] - newPos[1]) == 
				originalDistance);
		//Move the full original distance forward. This should collide with the wall.
		testRobot.move(originalDistance);
		//Test that you stopped in front of the wall and the amStopped boolean is true.
		assertTrue(testRobot.distanceToObstacle(Direction.FORWARD) == 0);
		assertTrue(testRobot.hasStopped());
	}
	/**
	 * Tests that the rotate method correctly changes the robot's
	 * CardinalDirection. There are three possible rotations.
	 * Record the robot's original orientation, then try each rotation, ensuring
	 * that the recorded and expected new CardinalDirection after each time is correct.
	 * For instance, if turning around from North, cardinal direction should be South.
	 * Also checks that the turns correctly deduct 3/6 energy for quarter/half turns.
	 */
	@Test
	public void testRotate() {
		//Record the current CardinalDirection
		CardinalDirection originalDirection = testRobot.getCurrentDirection();
		//Rotate left. Check that the new CardinalDirection is the expected one
		testRobot.rotate(Turn.LEFT);
		assertEquals(1997, testRobot.getBatteryLevel(), 0);
		assertEquals(originalDirection, testRobot.getCurrentDirection().oppositeDirection().rotateClockwise());
		//Rotate right. Check that the new CardinalDirection is the original one
		testRobot.rotate(Turn.RIGHT);
		assertEquals(1994, testRobot.getBatteryLevel(), 0);
		assertEquals(originalDirection, testRobot.getCurrentDirection());
		//Rotate around. Check that the new CardinalDirection is opposite the original one.
		testRobot.rotate(Turn.AROUND);
		assertEquals(1988, testRobot.getBatteryLevel(), 0);
		assertEquals(originalDirection, testRobot.getCurrentDirection().oppositeDirection());
	}
	/**
	 * Tests that the Jump method correctly jumps over walls.
	 * Test this by moving forward until you reach a non-border wall.
	 * Then jump forward. Check that your new position is what you expected.
	 * For instance, if you jump forward while facing North, your new y coordinate should
	 * be one less then before.
	 */
	@Test
	public void testJump() {
		//Move forward until you hit a non-border wall.
		//If you hit a border wall, rotate and try again, until you hit a non-border wall
		//Record current position and direction
		//Jump
		//Check that new position is what you expect based on the direction
	}
	/**
	 * Tests if the hasStopped() method correctly returns true
	 * after doing an illegal move, by having the robot attempt to
	 * move 200 cells forwards, which is impossible unless facing the exit.
	 * Must have a functional move() method for this to work.
	 */
	@Test
	public void testHasStopped() {
		//Move the robot 200 steps
		//Check if hasStopped() returns true
	}
	/**
	 * Tests that the distanceToObstacle() method correctly decrements by one
	 * when moving forwards, provided there is space to move forward.
	 * Also checks that that the distance recorded by a backwards sensor is the
	 * same as that recorded by the forwards sensor after turning 180 degrees.
	 * Requires a functional implementation of DistanceSensor.
	 */
	@Test
	public void testDistanceToObstacle() {
		//Check that there's no obstacle immediately in front
		//If that's not true, rotate CW until it is.
		//Check the forward distance.
		//Move forward by one, check that the forward distance has dropped by one.
		//Record the backwards distance.
		//Rotate the robot 180 degrees.
		//Check that the forward distance equals the recorded backwards distance.
	}
	/**
	 * 
	 * @return a skill level 0 perfect maze with seed 13 and the DFS builder for testing purposes
	 */
	private Maze makeTestMaze() {
		StubOrder myOrder = new StubOrder(0, Builder.DFS, true, 13);
		MazeFactory myMazeFactory = new MazeFactory();
		myMazeFactory.order(myOrder);
		myMazeFactory.waitTillDelivered();
		return myOrder.getMaze();
	}
}
