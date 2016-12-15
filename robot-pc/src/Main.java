import java.util.List;

import robot.Direction;
import robot.Robot;
import robot.State;
import robot.World;
import robot.Zone;

public class Main {


	public static void main(String[] args) {

		Robot robot = new Robot("coucou");


		MazeBuilder builder =  new MazeBuilder();
		builder.addPath(0, 0, Direction.SOUTH)
		.addPath(0, 1, Direction.SOUTH)
		.addPath(0, 2, Direction.WEST)
		.addPath(0, 2, Direction.EAST)
		.addPath(-1, 2, Direction.NORTH)
		.addPath(-1, 1, Direction.NORTH)

		.addPath(1, 2, Direction.WEST)
		.addPath(1, 2, Direction.SOUTH)
		.addPath(1, 2, Direction.SOUTH)

		.addPath(2, 2, Direction.NORTH)
		.addExit(2, 2, Direction.SOUTH)
		.addPath(2, 2, Direction.WEST)
		.addPath(2, 1, Direction.NORTH)
		.addPath(2, 0, Direction.WEST);

		World example = builder.getWorld();


		MemoryContext context = new MemoryContext(example);
		robot.setContext(context);
		int i = 200;
		while(!robot.hasFindExit && robot.exploring && i > 0){
			robot.explore();
			i = i -1;
		}

		if(robot.hasFindExit){
			System.out.println("===========PATH==========");
			List<Zone> path = robot.getPathFinder().findPath();

			for(Zone zone : path){ 
				System.out.println(zone);
			}

			System.out.println("===========Resolving Path==========");

			robot.resolvePath(path);
		}

	}
}
