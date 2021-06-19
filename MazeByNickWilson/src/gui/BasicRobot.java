package gui;

import generation.CardinalDirection;
import gui.Constants.UserInput;

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
	 * A float representing the current battery level of the Robot.
	 */
	private float batteryLevel;
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
	/**
	 * A boolean that tells you if you've stopped for a non-battery related reason
	 * For instance, if you tried to move through a wall, or jump out of the maze
	 */
	private Boolean amStopped;
	//Note that interface methods do not have a Javadoc comment.
	/**
	 * Constructor that sets the fields to basic values and adds distanceSensors to each side of the robot
	 * @param Controller that will be this Robot's controller
	 */
	public BasicRobot(Controller controller) {
		myOdometer = 0;
		amStopped = false;
		batteryLevel = 2000;
		myController = controller;
		
		addDistanceSensor(new BasicSensor(), Direction.FORWARD);
		addDistanceSensor(new BasicSensor(), Direction.BACKWARD);
		addDistanceSensor(new BasicSensor(), Direction.LEFT);
		addDistanceSensor(new BasicSensor(), Direction.RIGHT);
	}
	@Override
	public void setController(Controller controller) {
		//Set myController to controller
		myController = controller;
	}

	@Override
	public void addDistanceSensor(DistanceSensor sensor, Direction mountedDirection) {
		//Add the Direction to the DistanceSensor
		//Set the appropriate DistanceSensor field to sensor
		if(mountedDirection.equals(Direction.FORWARD)) {
			forwardsSensor = sensor;
			forwardsSensor.setSensorDirection(Direction.FORWARD);
			forwardsSensor.setMaze(myController.getMazeConfiguration());
		}
		if(mountedDirection.equals(Direction.LEFT)) {
			leftSensor = sensor;
			leftSensor.setSensorDirection(Direction.LEFT);
			leftSensor.setMaze(myController.getMazeConfiguration());
		}
		if(mountedDirection.equals(Direction.RIGHT)) {
			rightSensor = sensor;
			rightSensor.setSensorDirection(Direction.RIGHT);
			rightSensor.setMaze(myController.getMazeConfiguration());
		}
		if(mountedDirection.equals(Direction.BACKWARD)) {
			backwardsSensor = sensor;
			backwardsSensor.setSensorDirection(Direction.BACKWARD);
			backwardsSensor.setMaze(myController.getMazeConfiguration());
		}
	}

	@Override
	public int[] getCurrentPosition() throws Exception {
		//Return the current position of the robot as [x, y] coords
		//Get the current position through controller
		int[] myPosition = myController.getCurrentPosition();
		if(!myController.getMazeConfiguration().isValidPosition(myPosition[0], myPosition[1])) {
			throw new Exception();
		}
		return myPosition;
	}

	@Override
	public CardinalDirection getCurrentDirection() {
		//return the CardinalDirection I am facing
		//Get this through the Controller
		return myController.getCurrentDirection();
	}

	@Override
	public float getBatteryLevel() {
		//return batteryLevel
		return batteryLevel;
	}

	@Override
	public void setBatteryLevel(float level) {
		//set batteryLevel to level
		batteryLevel = level;
	}

	@Override
	public float getEnergyForFullRotation() {
		// Return 12 as instructed
		return 12;
	}

	@Override
	public float getEnergyForStepForward() {
		// Return 4 as instructed
		return 4;
	}

	@Override
	public int getOdometerReading() {
		// Return odometer
		return myOdometer;
	}

	@Override
	public void resetOdometer() {
		// Set odometer to 0
		myOdometer = 0;
	}

	@Override
	public void rotate(Turn turn) {
		//Change myCardinalDirection to the appropriate value
		//Based on current direction and turn
		//So turning left from north heading means facing west
		//Do this through the StatePlaying class
		if(amStopped) {
			return;//Don't do anything if stopped.
		}
		if(turn.equals(Turn.RIGHT)) {//Turn right
			if(batteryLevel >= getEnergyForFullRotation() / 4) {
				setBatteryLevel(batteryLevel - getEnergyForFullRotation() / 4);
				myController.keyDown(UserInput.RIGHT, 0);
			}
			else {
				amStopped = true;
			}
		}
		else if(turn.equals(Turn.LEFT)) {//Turn left
			if(batteryLevel >= getEnergyForFullRotation() / 4) {
				setBatteryLevel(batteryLevel - getEnergyForFullRotation() / 4);
				myController.keyDown(UserInput.LEFT, 0);
			}
			else {
				amStopped = true;
			}
		}
		else { //Turn around
			if(batteryLevel > getBatteryLevel() / 2) {
				
				setBatteryLevel(batteryLevel - getEnergyForFullRotation() / 2);
				myController.keyDown(UserInput.LEFT, 0);
				myController.keyDown(UserInput.LEFT, 0);
			}
			else {
				amStopped = true;
			}
		}
		
	}

	@Override
	public void move(int distance) {
		//Keep moving forward step-by-step until
		//I run out of energy or run out of room (there's a wall in front me)
		//OR I've travelled the appropriate distance
		if(amStopped) {//Can't move if immobile
			return;
		}
		if(distance < 1) {//If the distance is not positive, throw an IllegalArgumentException
			throw new IllegalArgumentException();
		}
		int distanceToWall = distanceToObstacle(Direction.FORWARD);

		while(distance > 0) {
			if(distanceToWall == 0) {//If headed directly into a wall, stop and end the method.
				amStopped = true;
				return;
			}
			if(getBatteryLevel() < getEnergyForStepForward()) { //If there's not enough energy to contine, stop and end method
				amStopped = true;
				return;
			}
			setBatteryLevel(batteryLevel - getEnergyForStepForward());
			myController.keyDown(UserInput.UP, 0);
			distance--;
			distanceToWall--;
		}
	}

	@Override
	public void jump() {
		//If it would make me jump out of the Maze, set amStopped to true and return
		int x = myController.getCurrentPosition()[0];
		int y = myController.getCurrentPosition()[1];
		CardinalDirection myDirection = getCurrentDirection();
		if(y == 0 && myDirection.equals(CardinalDirection.North) //If I'm at the top border and going North
				|| x == 0 && myDirection.equals(CardinalDirection.West) //If I'm at the left border and going West
				|| x == myController.getMazeConfiguration().getWidth() - 1 && myDirection.equals(CardinalDirection.East)//If I'm at the right border and going East
				|| y == myController.getMazeConfiguration().getHeight() - 1 && myDirection.equals(CardinalDirection.South)) {//If I'm at the bottom border and going South
			//Stop and return
			amStopped = true;
			return;
		}
		//If jumping is legal, use StatePlaying to jump
		myController.keyDown(UserInput.JUMP, 0);
	}

	@Override
	public boolean isAtExit() {
		//Return true if I'm at the exit as determined by the underlying Maze
		int x = myController.getCurrentPosition()[0];
		int y = myController.getCurrentPosition()[1];
		return myController.getMazeConfiguration().getFloorplan().isExitPosition(x, y);
	}

	@Override
	public boolean isInsideRoom() {
		//Return true if I'm in a room as determined by the underlying Maze
		int x = myController.getCurrentPosition()[0];
		int y = myController.getCurrentPosition()[1];
		return myController.getMazeConfiguration().getFloorplan().isInRoom(x, y);
	}

	@Override
	public boolean hasStopped() {
		//Return true if my energy is 0 or if the amStopped boolean is true
		return batteryLevel == 0 || amStopped == true;
	}

	@Override
	public int distanceToObstacle(Direction direction) throws UnsupportedOperationException {
		//Return the corresponding DistanceSensor's distance to the nearest obstacle
		setBatteryLevel(batteryLevel - 1);//Count down counter
		float[] batteryAsFloatArray = {batteryLevel};
		if(this.hasDistanceSensor(direction) == false) { //If there's no distance sensor
			throw new UnsupportedOperationException(); //Throw an exception
		}
		try {
			if(direction.equals(Direction.FORWARD)) {
				return forwardsSensor.distanceToObstacle(this.getCurrentPosition(), this.getCurrentDirection(),batteryAsFloatArray);
			}
			else if(direction.equals(Direction.BACKWARD)) {
				return backwardsSensor.distanceToObstacle(getCurrentPosition(), getCurrentDirection(), batteryAsFloatArray);
			}
			else if(direction.equals(Direction.LEFT)) {
				return leftSensor.distanceToObstacle(getCurrentPosition(), getCurrentDirection(), batteryAsFloatArray);
			}
			else {
				return rightSensor.distanceToObstacle(getCurrentPosition(), getCurrentDirection(), batteryAsFloatArray);
			}
		}
		catch (Exception e) {
			throw new UnsupportedOperationException();
		}
	}
	/**
	 * This method tells you whether or not the robot has a distance sensor in the 
	 * specified direction.
	 * Note: May update this method to one testing if the distance sensor is reliable for project 4.
	 * @param direction
	 * @return whether or not a distance sensor exists in the corresponding direction
	 */
	private boolean hasDistanceSensor(Direction direction) {
		if(direction.equals(direction.FORWARD)) {
			return forwardsSensor != null;
		}
		else if(direction.equals(direction.BACKWARD)) {
			return backwardsSensor != null;
		}
		else if(direction.equals(direction.LEFT)) {
			return leftSensor != null;
		}
		else {
			return rightSensor != null;
		}
	}

	@Override
	public boolean canSeeThroughTheExitIntoEternity(Direction direction) throws UnsupportedOperationException {
		//Return true if the corresponding DistanceSensor's distance
		//To the nearest obstacle is infinite
		if(!hasDistanceSensor(direction)) {
			throw new UnsupportedOperationException();
		}
		return distanceToObstacle(direction) == Integer.MAX_VALUE;
	}

	@Override
	public void startFailureAndRepairProcess(Direction direction, int meanTimeBetweenFailures, int meanTimeToRepair)
			throws UnsupportedOperationException {
		// TODO Will be implemented in Project 4. 
		//Throw UnsupportedOperationException
		throw new UnsupportedOperationException();

	}

	@Override
	public void stopFailureAndRepairProcess(Direction direction) throws UnsupportedOperationException {
		// TODO Will be implemented in Project 4. 
		//Throw UnsupportedOperationException
		throw new UnsupportedOperationException();
	}

}
