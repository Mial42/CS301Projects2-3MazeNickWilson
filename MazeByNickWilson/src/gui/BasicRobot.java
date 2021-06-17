package gui;

import java.util.Dictionary;

import generation.CardinalDirection;
/**
 * A BasicRobot is a simple robot. 
 * A robot needs to be given an existing maze (a controller) to be operational.
 * It is configured by mounting distance sensors to a robot such that it can
 * measure the distance to an obstacle, a wall, in the direction that sensor
 * has been mounted on the robot.
 * It provides an operating platform for a robot driver that experiences a maze (the real world) 
 * through the sensors and actuators of this robot interface.
 * 
 * Note that a robot may be very limited in its mobility, e.g. only 90 degree left or right turns, 
 * which makes sense in the artificial terrain of a maze, and its sensing capability, 
 * e.g. only a sensor on its front or left to detect remote obstacles. 
 * Left/right is a notion relative to the robot's direction 
 * or relative to the underlying maze. 
 * To avoid a confusion, the latter is considered a direction in an absolute sense 
 * and it may be better to describe it as a cardinal direction 
 * north, south, east, west than up, down, right, left. 
 * 
 * A robot comes with a battery level that is depleted during operations 
 * such that a robot may actually stop if it runs out of energy.
 * This class supports energy consideration. 
 * A robot may also stop when hitting an obstacle.
 * The robot's distance sensors are subject to failures and repairs
 * such that the sensors may become temporarily unavailable 
 * during the robot's mission. 
 * 
 * WARNING: the use of CW_BOT/CW_TOP and CardinalDirection in
 * Floorplan and Mazebuilder does not directly match with the Map 
 * which draws position (0,0) at the lower left corner, such that 
 * x values grow towards the right, y values grow towards the top and
 * direction SOUTH is towards the top of the display. 
 * Or in other words, the maze is drawn upside down by the Map but
 * East and West are as one expects it (East to the right, West to the left).
 *  
 * The rotation is calculated with polar coordinates (angle) towards a 
 * Cartesian coordinate system where a southbound direction is (dx,dy)=(0,1).
 * 
 * Collaborators: a controller that holds a maze to be explored, 
 * a robotdriver class that operates robot. This class uses DistanceSensors as well.
 * Cooperates with the program at large through a controller.
 * 
 * Note that interface methods do not have a Javadoc comment.
 * @author Nicholas Wilson
 *
 */
public class BasicRobot implements Robot {
	/**
	 * A controller that holds the maze to be explored.
	 */
	private Controller myController;
	/**
	 * The direction the Robot is facing.
	 */
	private CardinalDirection myCardinalDirection;
	/**
	 * An integer representing the current battery level of the Robot.
	 */
	private int batteryLevel;
	/**
	 * A DistanceSensor corresponding to forwards
	 */
	private DistanceSensor forwardsSensor;
	/**
	 * A DistanceSensor corresponding to backwards
	 */
	private DistanceSensor backwardsSensor;
	/**
	 * A DistanceSensor corresponding to left
	 */
	private DistanceSensor leftSensor;
	/**
	 * A DistanceSensor corresponding to right
	 */
	private DistanceSensor rightSensor;
	/**
	 * An int that tells you how far the Robot has travelled since it was last reset
	 */
	private int myOdometer;
	//Note that interface methods do not have a Javadoc comment.
	@Override
	public void setController(Controller controller) {
		//Set myController to controller

	}

	@Override
	public void addDistanceSensor(DistanceSensor sensor, Direction mountedDirection) {
		//Add the Direction to the DistanceSensor
		//Set the appropriate DistanceSensor field to sensor
	}

	@Override
	public int[] getCurrentPosition() throws Exception {
		//Return the current position of the robot as [x, y] coords
		//Get the current position through controller
		return null;
	}

	@Override
	public CardinalDirection getCurrentDirection() {
		//return myCardinalDirection
		return null;
	}

	@Override
	public float getBatteryLevel() {
		//return batteryLevel
		return 0;
	}

	@Override
	public void setBatteryLevel(float level) {
		//set batteryLevel to level

	}

	@Override
	public float getEnergyForFullRotation() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getEnergyForStepForward() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getOdometerReading() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void resetOdometer() {
		// TODO Auto-generated method stub

	}

	@Override
	public void rotate(Turn turn) {
		// TODO Auto-generated method stub

	}

	@Override
	public void move(int distance) {
		// TODO Auto-generated method stub

	}

	@Override
	public void jump() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isAtExit() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInsideRoom() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasStopped() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int distanceToObstacle(Direction direction) throws UnsupportedOperationException {
		//Return the corresponding DistanceSensor's distance to the nearest obstacle
		return 0;
	}

	@Override
	public boolean canSeeThroughTheExitIntoEternity(Direction direction) throws UnsupportedOperationException {
		//Return true if the corresponding DistanceSensor's distance
		//To the nearest obstacle is infinite
		return false;
	}

	@Override
	public void startFailureAndRepairProcess(Direction direction, int meanTimeBetweenFailures, int meanTimeToRepair)
			throws UnsupportedOperationException {
		// TODO Will be implemented in Project 4. 
		//Throw UnsupportedOperationException

	}

	@Override
	public void stopFailureAndRepairProcess(Direction direction) throws UnsupportedOperationException {
		// TODO Will be implemented in Project 4. 
		//Throw UnsupportedOperationException
	}

}
