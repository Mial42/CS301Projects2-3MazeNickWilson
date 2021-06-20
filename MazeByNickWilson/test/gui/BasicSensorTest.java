package gui;
/**
 * BasicSensorTest does some smoke tests to ensure that BasicSensor's methods
 * are working. It extends BasicRobotTest because BasicRobotTest
 * already covers much of the same territory.
 * Responsibilities: Make sure that BasicSensor's methods are working under simple conditions.
 * Collaborators: Robot and Controller.
 * @author Nicholas Wilson
 *
 */

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import gui.Robot.Direction;
public class BasicSensorTest extends BasicRobotTest {
	/**
	 * This test makes sure the correct exceptions are thrown when viewing some edge cases.
	 * That is, Exception for too little battery, UnsupportedOperationException for negative battery.
	 */
	@Test
	public void testPowerSupplyExceptions(){
		boolean throwsExceptionCorrectly = false;
		boolean throwsOutOfBoundsCorrectly = false;
		testRobot.setBatteryLevel(-1);
		try {
			testRobot.distanceToObstacle(Direction.LEFT);
		} catch (UnsupportedOperationException e) {
			throwsOutOfBoundsCorrectly = true;
		}
		testRobot.setBatteryLevel(0);
		try {
			testRobot.distanceToObstacle(Direction.RIGHT);
		}
		catch (Exception e) {
			throwsExceptionCorrectly = true;
		}
		assertTrue(throwsExceptionCorrectly && throwsOutOfBoundsCorrectly);
	}
}
