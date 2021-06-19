package gui;

import generation.Maze;
/**
 * Wizards implement the RobotDriver interface and so operate a Robot to escape a maze.
 * Uses the Distance Array to escape the maze.
 * Collaborators: RobotDrivers control Robots. Wizards access the Maze through the Controller class.
 * anything about the Maze itself
 * @author Nicholas Wilson
 *
 */
public class Wizard extends WallFollower implements RobotDriver {

	@Override
	public boolean drive1Step2Exit() throws Exception {
		//If at the exit, rotate to face the exit and return false
		//If out of power, throw an Exception
		//If not:
		//Find the closest neighbor to the exit
		//Move to that neighbor
		//Return true
		return false;
	}


}
