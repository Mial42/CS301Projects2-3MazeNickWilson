package gui;

import generation.CardinalDirection;
import generation.Maze;
import gui.Robot.Direction;

/**
 * A BasicSensor is a DistanceSensor.
 * A DistanceSensor provides information how for it is
 * to a wall for a given position and in a specific direction.
 * 
 * The sensor is assumed to be mounted at a particular angle
 * relative to the forward direction of the robot.
 * This way one can have a left sensor, a right sensor, 
 * a forward, or a backward sensor.
 * 
 * So if asked about the distance to an obstacle, one needs
 * to provide the current position in the maze and the 
 * forward direction such that one can calculate the distance
 * to an obstacle a sensor measures in the relative direction
 * that it is mounted on the robot. 
 * 
 * The sensor consumes energy for its sensing operations, which
 * is why sensing expects a power supply to deduct the consumed
 * energy from.
 * 
 * A sensor can experience temporary failures of its operations.
 * So there are alternating time periods when it is up and running 
 * and down and broken. 
 * The down time of a sensor is characterized by its the mean time
 * it takes to repair itself. This could be the time it takes
 * for a reboot of the subsystem if it is a software failure.
 * The up time is described by the mean time between failures 
 * which is the time when the system comes up again and the next
 * failure that brings it down. 
 *  
 * The power consumption for the repair operations is ignored.
 * 
 * Collaborators: BasicSensors are mounted on Robots. Since each sensor looks in a
 * cardinal direction, you can mount up to 4 sensors on a Robot (N, E, S, W). Communicates
 * energy consumption to the RobotDriver through the Robot.
 * @author Nicholas Wilson
 *
 */
public class BasicSensor implements DistanceSensor {

	@Override
	public int distanceToObstacle(int[] currentPosition, CardinalDirection currentDirection, float[] powersupply)
			throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaze(Maze maze) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSensorDirection(Direction mountedDirection) {
		// TODO Auto-generated method stub

	}

	@Override
	public float getEnergyConsumptionForSensing() {
		// Return 1 as instructed
		return 1;
	}

	@Override
	public void startFailureAndRepairProcess(int meanTimeBetweenFailures, int meanTimeToRepair)
			throws UnsupportedOperationException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void stopFailureAndRepairProcess() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();

	}

}
