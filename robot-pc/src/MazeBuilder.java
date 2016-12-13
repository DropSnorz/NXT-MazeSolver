import robot.Direction;
import robot.State;
import robot.World;
import robot.Zone;

public class MazeBuilder {

	World world;
	
	public MazeBuilder(){
		 world = new World();
	}
	
	public MazeBuilder addPath(int x, int y, Direction direction){
		
		Zone zone = world.getOrCreateZone(x, y);
		zone.setState(direction, State.STATE_ACCESSIBLE);
		
		world.getNextZone(x, y, direction).setState(direction.reverse(), State.STATE_ACCESSIBLE);
		return this;
	}
	
	public MazeBuilder addExit(int x, int y, Direction direction){
		
		Zone zone = world.getOrCreateZone(x, y);
		zone.setState(direction, State.STATE_EXIT);
		world.getNextZone(x, y, direction).setState(direction.reverse(), State.STATE_ACCESSIBLE);
		return this;
	}
	
	public World getWorld(){
		return world;
	}
	
}
