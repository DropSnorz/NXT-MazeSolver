

import robot.Direction;
import robot.IContext;
import robot.State;
import robot.World;
import robot.Zone;

public class MemoryContext implements IContext {
	
	World world;
	Direction direction;
	
	public MemoryContext(World world){
		this.world = world;
		this.direction = Direction.NORTH;
		
	}
	@Override
	public void reachNextZone() {
		Zone zone = world.reachNextZone(direction);
		System.out.println("Move to " + direction.name() + " on " + zone);

	}

	@Override
	public void turnLeft() {
		System.out.println("Turn left to " + direction.previous().name());
		direction = direction.previous();
		
	}

	@Override
	public void turnRight() {
		System.out.println("Turn right to " + direction.next().name());
		direction = direction.next();
		
	}

	@Override
	public int getFrontDistance() {
		
		Zone currentZone = world.getCurrentZone();
		State state = currentZone.getState(direction);
		if (state == State.STATE_ACCESSIBLE){
			System.out.println("Scan[" + currentZone + "] to " + direction.name()+ ": Accessible");
			return 100;
		}
		else if (state == State.STATE_EXIT){
			System.out.println("Scan[" + currentZone + "] to " + direction.name()+ ": Exit");
			return 1000;
		}
		else{
			System.out.println("Scan[" + currentZone + "] to " + direction.name()+ ": Inaccessible");

			return 10;

		}
		
	}

}
