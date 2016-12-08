import java.util.List;

import robot.Direction;
import robot.Robot;
import robot.State;
import robot.World;
import robot.Zone;

public class Main {

	
public static void main(String[] args) {
		
		Robot robot = new Robot("coucou");
		
		World memWorld = new World();
		memWorld.getOrCreateZone(0, 0).setState(Direction.NORTH, State.STATE_INACCESSIBLE);
		memWorld.getOrCreateZone(0, 0).setState(Direction.EAST, State.STATE_INACCESSIBLE);
		memWorld.getOrCreateZone(0, 0).setState(Direction.SOUTH, State.STATE_ACCESSIBLE);
		memWorld.getOrCreateZone(0, 0).setState(Direction.WEST, State.STATE_INACCESSIBLE);

		memWorld.getOrCreateZone(0, 1).setState(Direction.NORTH, State.STATE_ACCESSIBLE);
		memWorld.getOrCreateZone(0, 1).setState(Direction.EAST, State.STATE_ACCESSIBLE);
		memWorld.getOrCreateZone(0, 1).setState(Direction.SOUTH, State.STATE_INACCESSIBLE);
		memWorld.getOrCreateZone(0, 1).setState(Direction.WEST, State.STATE_INACCESSIBLE);
		
		memWorld.getOrCreateZone(1, 1).setState(Direction.NORTH, State.STATE_INACCESSIBLE);
		memWorld.getOrCreateZone(1, 1).setState(Direction.EAST, State.STATE_INACCESSIBLE);
		memWorld.getOrCreateZone(1, 1).setState(Direction.SOUTH, State.STATE_EXIT);
		memWorld.getOrCreateZone(1, 1).setState(Direction.WEST, State.STATE_INACCESSIBLE);
		
		
		MazeBuilder builder = new MazeBuilder();
		builder.addPath(0, 0, Direction.EAST);
		builder.addExit(0, 0, Direction.WEST);
		builder.addPath(1, 0, Direction.NORTH);
		builder.addPath(1, 0, Direction.SOUTH);
		
		builder.addPath(1, -1, Direction.EAST);
		
		builder.addPath(2, -1, Direction.EAST);
		
		builder.addPath(3, -1, Direction.SOUTH);
		
		builder.addPath(3, 0, Direction.SOUTH);
		
		builder.addPath(3,1, Direction.WEST);
		
		builder.addPath(2,1, Direction.WEST);
		
		builder.addPath(1,1, Direction.NORTH);
		
		//builder.addExit(3, 0, Direction.EAST);
		
		World world2 = builder.getWorld();

		

		MazeBuilder builder3 =  new MazeBuilder();
		builder3.addPath(0, 0, Direction.SOUTH);
		builder3.addPath(0, 1, Direction.SOUTH);
		builder3.addPath(0, 2, Direction.WEST);
		builder3.addPath(0, 2, Direction.EAST);
		
		builder3.addPath(-1, 2, Direction.NORTH);
		builder3.addPath(-1, 1, Direction.NORTH);
		
		builder3.addPath(1, 2, Direction.WEST);
		builder3.addPath(1, 2, Direction.SOUTH);
		builder3.addExit(1, 3, Direction.SOUTH); //
		
		builder3.addPath(2, 2, Direction.NORTH);
		builder3.addPath(2, 1, Direction.NORTH);
		builder3.addPath(2, 0, Direction.WEST);
		
		World world3 = builder3.getWorld();

		
		//Robot in front of exit
		MazeBuilder builder4 =  new MazeBuilder();
		builder4.addExit(0, 0, Direction.NORTH);
		World world4 = builder4.getWorld();
		
		MazeBuilder builder5 = new MazeBuilder();
		builder5.addPath(0, 0, Direction.NORTH);
		builder5.addPath(0, 0, Direction.EAST);
		builder5.addPath(1, -1, Direction.SOUTH);
		builder5.addPath(1, -1, Direction.NORTH);
		builder5.addExit(1, -2, Direction.WEST);

		World world5 = builder5.getWorld();
		

		
		
		MemoryContext context = new MemoryContext(world3);
		robot.setContext(context);
		int i = 100;
		while(!robot.hasFindExit && i > 0){
			robot.explore();
			i = i -1;
		}
		
		System.out.println("===========PATH==========");
		List<Zone> path = robot.getPathFinder().findPath();
		
		for(Zone zone : path){
			System.out.println(zone);
		}
		
		System.out.println("===========Resolving Path==========");
		
		robot.resolvePath(path);

	}
}
