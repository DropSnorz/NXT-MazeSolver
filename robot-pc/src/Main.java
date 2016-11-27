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
		
		MemoryContext context = new MemoryContext(memWorld);
		robot.setContext(context);
		int i = 100;
		while(!robot.hasFindExit && i > 0){
			robot.explore();
			i = i -1;
		}
	}
}
