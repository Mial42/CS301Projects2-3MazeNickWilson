/**
 * 
 */
package gui;

import generation.Order;
import generation.Order.Builder;

import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.SortOrder;

import org.junit.Ignore;


/**
 * This class is a wrapper class to startup the Maze game as a Java application
 * 
 * This code is refactored code from Maze.java by Paul Falstad, www.falstad.com, Copyright (C) 1998, all rights reserved
 * Paul Falstad granted permission to modify and use code for teaching purposes.
 * Refactored by Peter Kemper
 * 
 * TODO: use logger for output instead of Sys.out
 */
public class MazeApplication extends JFrame {

	// not used, just to make the compiler, static code checker happy
	private static final long serialVersionUID = 1L;
	
	// developments vs production version
	// for development it is more convenient if we produce the same maze over an over again
	// by setting the following constant to false, the maze will only vary with skill level and algorithm
	// but not on its own
	// for production version it is desirable that we never play the same maze 
	// so even if the algorithm and skill level are the same, the generated maze should look different
	// which is achieved with some random initialization
	private static final boolean DEVELOPMENT_VERSION_WITH_DETERMINISTIC_MAZE_GENERATION = true; //true

	/**
	 * Constructor
	 */
	public MazeApplication() {
		init(null);
	}

	/**
	 * Constructor that loads a maze from a given file or uses a particular method to generate a maze
	 * @param parameter can identify a generation method (Prim, Kruskal, Eller)
     * or a filename that stores an already generated maze that is then loaded, or can be null
	 */
	public MazeApplication(String parameter) {
		init(parameter);
	}
	/**
	 * Constructor that loads a maze from a given file or uses a particular method to generate a maze
	 * @param parameter can identify a generation method (Prim, Kruskal, Eller)
     * or a filename that stores an already generated maze that is then loaded, or can be null
     * @param robot a string that identifies a type of RobotDriver to be used
	 */
	public MazeApplication(String parameter, String robot) {
		initWithRobot(parameter, robot);
	}
	/**
	 * Instantiates a controller with settings according to the given parameter.
	 * @param parameter can identify a generation method (Prim, Kruskal, Eller)
	 * or a filename that contains a generated maze that is then loaded,
	 * or can be null
	 * @return the newly instantiated and configured controller
	 */
	 Controller createController(String parameter) {
	    // need to instantiate a controller to return as a result in any case
	    Controller result = new Controller() ;
	    // can decide if user repeatedly plays the same mazes or 
	    // if mazes are different each and every time
	    // set to true for testing purposes
	    // set to false for playing the game
	    if (DEVELOPMENT_VERSION_WITH_DETERMINISTIC_MAZE_GENERATION)
	    	result.setDeterministic(true);
	    else
	    	result.setDeterministic(false);
	    String msg = null; // message for feedback
	    // Case 1: no input
	    if (parameter == null) {
	        msg = "MazeApplication: maze will be generated with a randomized algorithm."; 
	    }
	    // Case 2: Prim
	    else if ("Prim".equalsIgnoreCase(parameter))
	    {
	        msg = "MazeApplication: generating random maze with Prim's algorithm.";
	        result.setBuilder(Order.Builder.Prim);
	    }
	    // Case 3 a and b: Eller, Kruskal or some other generation algorithm
	    else if ("Kruskal".equalsIgnoreCase(parameter))
	    {
	    	// TODO: for P2 assignment, please add code to set the builder accordingly
	        //throw new RuntimeException("Don't know anybody named Kruskal ...");
	    	msg = "MazeApplication: generating random maze with Kruskal's algorithm.";
	    	result.setBuilder(Order.Builder.Kruskal);
	    }
	    else if ("Eller".equalsIgnoreCase(parameter))
	    {
	    	// TODO: for P2 assignment, please add code to set the builder accordingly
	        throw new RuntimeException("Don't know anybody named Eller ...");
	    }
	    // Case 4: a file
	    else {
	        File f = new File(parameter) ;
	        if (f.exists() && f.canRead())
	        {
	            msg = "MazeApplication: loading maze from file: " + parameter;
	            result.setFileName(parameter);
	            return result;
	        }
	        else {
	            // None of the predefined strings and not a filename either: 
	            msg = "MazeApplication: unknown parameter value: " + parameter + " ignored, operating in default mode.";
	        }
	    }
	    // controller instanted and attributes set according to given input parameter
	    // output message and return controller
	    System.out.println(msg);
	    return result;
	}

		/**
		 * Instantiates a controller with settings according to the given parameter.
		 * @param parameter can identify a generation method (Prim, Kruskal, Eller)
		 * or a filename that contains a generated maze that is then loaded,
		 * or can be null
		 * @param robot a string that tells which type of RobotDriver we should be using.
		 * @return the newly instantiated and configured controller
		 */
		 Controller createController(String parameter, String robot) {
		    // need to instantiate a controller to return as a result in any case
		    Controller result = new Controller() ;
		    String msg = null; // message for feedback
		    //Tells the controller to create a robotDriver
		    if("Wizard".equalsIgnoreCase(robot)) {
				 result.setRobotAndDriver(new BasicRobot(result), new Wizard());
		    }
		    else if("Wallfollower".equalsIgnoreCase(robot)) {
				 result.setRobotAndDriver(new BasicRobot(result), new WallFollower());
		    }
		    // can decide if user repeatedly plays the same mazes or 
		    // if mazes are different each and every time
		    // set to true for testing purposes
		    // set to false for playing the game
		    if (DEVELOPMENT_VERSION_WITH_DETERMINISTIC_MAZE_GENERATION)
		    	result.setDeterministic(true);
		    else
		    	result.setDeterministic(false);
		    // Case 1: no input
		    if (parameter == null) {
		        msg = "MazeApplication: maze will be generated with a randomized algorithm."; 
		    }
		    // Case 2: Prim
		    else if ("Prim".equalsIgnoreCase(parameter))
		    {
		        msg = "MazeApplication: generating random maze with Prim's algorithm.";
		        result.setBuilder(Order.Builder.Prim);
		    }
		    // Case 3 a and b: Eller, Kruskal or some other generation algorithm
		    else if ("Kruskal".equalsIgnoreCase(parameter))
		    {
		    	// TODO: for P2 assignment, please add code to set the builder accordingly
		        //throw new RuntimeException("Don't know anybody named Kruskal ...");
		    	msg = "MazeApplication: generating random maze with Kruskal's algorithm.";
		    	result.setBuilder(Order.Builder.Kruskal);
		    }
		    else if ("Eller".equalsIgnoreCase(parameter))
		    {
		    	// TODO: for P2 assignment, please add code to set the builder accordingly
		        throw new RuntimeException("Don't know anybody named Eller ...");
		    }
		    // Case 4: a file
		    else {
		        File f = new File(parameter) ;
		        if (f.exists() && f.canRead())
		        {
		            msg = "MazeApplication: loading maze from file: " + parameter;
		            result.setFileName(parameter);
		            return result;
		        }
		        else {
		            // None of the predefined strings and not a filename either: 
		            msg = "MazeApplication: unknown parameter value: " + parameter + " ignored, operating in default mode.";
		        }
		    }
		    // controller instanted and attributes set according to given input parameter
		    // output message and return controller
		    System.out.println(msg);
		    return result;
		}
	 
	/**
	 * Initializes some internals and puts the game on display.
	 * @param parameter can identify a generation method (Prim, Kruskal, Eller)
     * or a filename that contains a generated maze that is then loaded, or can be null
	 */
	private void init(String parameter) {
	    // instantiate a game controller and add it to the JFrame
	    Controller controller = createController(parameter);
		add(controller.getPanel()) ;
		// instantiate a key listener that feeds keyboard input into the controller
		// and add it to the JFrame
		KeyListener kl = new SimpleKeyListener(this, controller) ;
		addKeyListener(kl) ;
		// set the frame to a fixed size for its width and height and put it on display
		setSize(Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT+22) ;
		setVisible(true) ;
		// focus should be on the JFrame of the MazeApplication and not on the maze panel
		// such that the SimpleKeyListener kl is used
		setFocusable(true) ;
		// start the game, hand over control to the game controller
		controller.start();
	}
	/**
	 * Initializes some internals and puts the game on display.
	 * @param parameter can identify a generation method (Prim, Kruskal, Eller)
     * or a filename that contains a generated maze that is then loaded, or can be null
     * @param robot a string that tells which type of RobotDriver we should be using.
	 */
	private void initWithRobot(String parameter, String robot) {
		Controller controller = createController(parameter, robot);
		add(controller.getPanel()) ;
		// instantiate a key listener that feeds keyboard input into the controller
		// and add it to the JFrame
		KeyListener kl = new SimpleKeyListener(this, controller) ;
		addKeyListener(kl) ;
		// set the frame to a fixed size for its width and height and put it on display
		setSize(Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT+22) ;
		setVisible(true) ;
		// focus should be on the JFrame of the MazeApplication and not on the maze panel
		// such that the SimpleKeyListener kl is used
		setFocusable(true) ;
		// start the game, hand over control to the game controller
		controller.start();
	}
	
	/**
	 * Main method to launch Maze game as a java application.
	 * The application can be operated in three ways. 
	 * 1) The intended normal operation is to provide no parameters
	 * and the maze will be generated by a randomized DFS algorithm (default). 
	 * 2) If a filename is given that contains a maze stored in xml format. 
	 * The maze will be loaded from that file. 
	 * This option is useful during development to test with a particular maze.
	 * 3) A predefined constant string is given to select a maze
	 * generation algorithm, currently supported is "Prim".
	 * @param args is optional, first string can be a fixed constant like Prim or
	 * the name of a file that stores a maze in XML format
	 */
	public static void main(String[] args) {
	    JFrame app ; 
	    if(args.length == 1) {
	    	app = new MazeApplication(args[0]);
	    }
	    else if(args.length > 1) {
	    	int i = 0;
	    	String parameter = null;
	    	String robot = null;
	    	while(i < args.length) {
	    		if(args[i].equals("-g") && parameter == null) {
	    			parameter = args[i + 1];
	    			i++;
	    		}
	    		if(args[i].equals("-f")) {
	    			parameter = args[i + 1];
	    			i++;
	    		}
	    		if(args[i].equals("-d")) {
	    			robot = args[i + 1];
	    			i++;
	    		}
	    		i++;
	    	}
	    	app = new MazeApplication(parameter, robot);
	    }
	    else {
	    	app = new MazeApplication();
	    }
		app.repaint() ;
	}

}
