package gui;

import generation.Maze;
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

	}

	@Override
	public void setMaze(Maze maze) {
		// Set myMaze to maze

	}

	@Override
	public boolean drive2Exit() throws Exception {
		//While I'm not at the exit and I have power
		//Take a step towards the exit
		//If out of power, throw an exception
		//If I've driven in a loop (heuristic: I have travelled more than twice the total cells in the Maze)
		//Return false
		return false;
	}

	@Override
	public boolean drive1Step2Exit() throws Exception {
		//If at the exit, rotate to face the exit and return false
		//If out of power, throw an Exception
		//If not:
		//Rotate left if possible, step forward, return true
		//Else, step forward, return true
		//Else, rotate right and return false
		return false;
	}

	@Override
	public float getEnergyConsumption() {
		//Return the original energy (2000 by default) minus the robot's current level
		return 0;
	}

	@Override
	public int getPathLength() {
		//Return the robot's odometer reading
		return 0;
	}

}
