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
public class WallFollower implements RobotDriver {

	@Override
	public void setRobot(Robot r) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMaze(Maze maze) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean drive2Exit() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean drive1Step2Exit() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public float getEnergyConsumption() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPathLength() {
		// TODO Auto-generated method stub
		return 0;
	}

}
