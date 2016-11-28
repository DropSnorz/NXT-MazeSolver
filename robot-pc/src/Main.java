import robot.Direction;
import robot.Robot;
import robot.StateEnum;
import robot.World;

public class Main {

	
public static void main(String[] args) {
		
		Robot robot = new Robot("coucou");
		
		World memWorld = new World();
		memWorld.getOrCreateZone(0, 0).setState(Direction.NORTH, StateEnum.STATE_INACCESSIBLE);
		memWorld.getOrCreateZone(0, 0).setState(Direction.EAST, StateEnum.STATE_INACCESSIBLE);
		memWorld.getOrCreateZone(0, 0).setState(Direction.SOUTH, StateEnum.STATE_ACCESSIBLE);
		memWorld.getOrCreateZone(0, 0).setState(Direction.WEST, StateEnum.STATE_INACCESSIBLE);

		memWorld.getOrCreateZone(0, 1).setState(Direction.NORTH, StateEnum.STATE_ACCESSIBLE);
		memWorld.getOrCreateZone(0, 1).setState(Direction.EAST, StateEnum.STATE_ACCESSIBLE);
		memWorld.getOrCreateZone(0, 1).setState(Direction.SOUTH, StateEnum.STATE_INACCESSIBLE);
		memWorld.getOrCreateZone(0, 1).setState(Direction.WEST, StateEnum.STATE_INACCESSIBLE);
		
		memWorld.getOrCreateZone(1, 1).setState(Direction.NORTH, StateEnum.STATE_INACCESSIBLE);
		memWorld.getOrCreateZone(1, 1).setState(Direction.EAST, StateEnum.STATE_INACCESSIBLE);
		memWorld.getOrCreateZone(1, 1).setState(Direction.SOUTH, StateEnum.STATE_EXIT);
		memWorld.getOrCreateZone(1, 1).setState(Direction.WEST, StateEnum.STATE_INACCESSIBLE);
		
		
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







		
		
		
		MemoryContext context = new MemoryContext(world2);
		robot.setContext(context);
		int i = 100;
		while(!robot.hasFindExit && i > 0){
			robot.explore();
			i = i -1;
		}
	}
}
