package robot;

import java.util.List;

public class Main {


	public static void main(String[] args) {

		Robot robot = new Robot("hello");
		robot.setContext(new NXTContext());

		while(!robot.hasFindExit && robot.exploring){
			robot.explore();

		}

		if(robot.hasFindExit){
			List<Zone> path = robot.getPathFinder().findPath();	
			robot.resolvePath(path);
		}

	}
}
