package gui;

import generation.Maze;
import gui.Robot.Direction;
import gui.Robot.Turn;
/**
 * WallFollowers implement the RobotDriver interface and so operate a Robot to escape a maze.
 * Uses the WallFollower algorithm to escape the maze.
 * Collaborators: RobotDrivers control Robots. This algorithm doesn't need to know
 * anything about the Maze itself
 * @author Nicholas Wilson
 *
 */
//Note: Interface methods do not have Javadoc comments
public class WallFollower implements RobotDriver {
	/**
	 * The Robot the class is driving.
	 */
	protected Robot myRobot;
	/**
	 * The Maze the class is driving through.
	 */
	protected Maze myMaze;
	@Override
	public void setRobot(Robot r) {
		//Set myRobot to r
		myRobot = r;
	}

	@Override
	public void setMaze(Maze maze) {
		// Set myMaze to maze
		myMaze = maze;
	}

	@Override
	public boolean drive2Exit() throws Exception {
		//While I'm not at the exit and I have power to take one more step
		//Take a step towards the exit
		//If out of power, throw an exception
		//If I've driven in a loop (heuristic: I have travelled more than the total cells in the Maze)
		//Return false
		while(!myRobot.isAtExit()) {
			try {
				drive1Step2Exit(); //Try to drive 1 more step
			} catch (Exception e) {
				throw new Exception();//If out of power, throw an exception
			}
			if(myRobot.getOdometerReading() >= myMaze.getHeight() * myMaze.getWidth()) {//Have I travelled
				//Too many cells?
				return false;
			}
		}
		return true;//If I reach the exit, return true
	}

	@Override
	public boolean drive1Step2Exit() throws Exception {
		//If at the exit, rotate to face the exit and return false
		if(myRobot.isAtExit()) {
			return false;
		}
		//If out of power, throw an Exception
		if(myRobot.getBatteryLevel() < 4) {
			throw new Exception();
		}
		//If not:
		//Rotate left if possible, step forward, return true
		if(myRobot.distanceToObstacle(Direction.LEFT) > 0) {
			myRobot.rotate(Turn.LEFT);
			myRobot.move(1);
			return true;
		}
		//Else, step forward, return true
		if(myRobot.distanceToObstacle(Direction.FORWARD) > 0) {
			myRobot.move(1);
			return true;
		}
		//Else, rotate right and return false
		myRobot.rotate(Turn.RIGHT);
		return false;
	}

	@Override
	public float getEnergyConsumption() {
		//Return the original energy (2000 by default) minus the robot's current level
		return (2000 - myRobot.getBatteryLevel());
	}

	@Override
	public int getPathLength() {
		//Return the robot's odometer reading
		return myRobot.getOdometerReading();
	}

}
