import robot.Direction;
import robot.StateEnum;
import robot.World;
import robot.Zone;

public class MazeBuilder {

	World world;
	
	public MazeBuilder(){
		 world = new World();
	}
	
	public void addPath(int x, int y, Direction direction){
		
		Zone zone = world.getOrCreateZone(x, y);
		zone.setState(direction, StateEnum.STATE_ACCESSIBLE);
		
		world.getNextZone(x, y, direction).setState(direction.reverse(), StateEnum.STATE_ACCESSIBLE);
	}
	
	public void addExit(int x, int y, Direction direction){
		
		Zone zone = world.getOrCreateZone(x, y);
		zone.setState(direction, StateEnum.STATE_EXIT);
		world.getNextZone(x, y, direction).setState(direction.reverse(), StateEnum.STATE_ACCESSIBLE);
	}
	
	public World getWorld(){
		return world;
	}
	
	
	
}
