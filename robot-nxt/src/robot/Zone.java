package robot;

import java.util.HashMap;
import java.util.Map;

import robot.pathfinding.PathNode;

public class Zone extends PathNode {

	protected int x;
	protected int y;
	protected int crossingCount;
	protected boolean excluded = false;

	protected Map<Direction, State> stateMap;

	public Zone(int x, int y) {
		this.x = x;
		this.y = y;
		this.crossingCount = 0;
		stateMap = new HashMap<Direction, State>();
		stateMap.put(Direction.NORTH, State.STATE_UNKNOW);
		stateMap.put(Direction.SOUTH, State.STATE_UNKNOW);
		stateMap.put(Direction.EAST, State.STATE_UNKNOW);
		stateMap.put(Direction.WEST, State.STATE_UNKNOW);
	}

	public void setState(Direction direction, State state){

		stateMap.put(direction, state);

	}
	public State getState(Direction direction){

		return stateMap.get(direction);

	}

	public boolean isFullyDiscovered(){

		//Robot seems to have troubles iterating over sets
		//SO we write all cases

		if(stateMap.get(Direction.NORTH) == State.STATE_UNKNOW){
			return false;
		}
		if(stateMap.get(Direction.EAST) == State.STATE_UNKNOW){
			return false;
		}
		if(stateMap.get(Direction.SOUTH) == State.STATE_UNKNOW){
			return false;
		}
		if(stateMap.get(Direction.WEST) == State.STATE_UNKNOW){
			return false;
		}
		return true;
	}

	public boolean isDeadZone(){
		
		if(getCountBlockedZones() >= 3){
			return true;
		}
		return false;

	}
	
	public boolean isBlockedZone(){
		
		if(getCountBlockedZones() >= 4){
			return true;
		}
		return false;

	}
	
	
	public int getCountBlockedZones(){
		
		//NXT seems to have trouble iterating over sets of keys
		//So we write all the cases
		
		int blockedZones = 0;
		if(stateMap.get(Direction.NORTH) == State.STATE_INACCESSIBLE
				|| stateMap.get(Direction.NORTH) == State.STATE_ACCESSIBLE_DEAD ){
			blockedZones += 1;
		}
		if(stateMap.get(Direction.EAST) == State.STATE_INACCESSIBLE
				|| stateMap.get(Direction.EAST) == State.STATE_ACCESSIBLE_DEAD ){
			blockedZones += 1;
		}
		if(stateMap.get(Direction.SOUTH) == State.STATE_INACCESSIBLE
				|| stateMap.get(Direction.SOUTH) == State.STATE_ACCESSIBLE_DEAD ){
			blockedZones += 1;
		}
		if(stateMap.get(Direction.WEST) == State.STATE_INACCESSIBLE
				|| stateMap.get(Direction.WEST) == State.STATE_ACCESSIBLE_DEAD ){
			blockedZones += 1;
		}
		
		return blockedZones;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getCrossingCount() {
		return crossingCount;
	}

	public void setCrossingCount(int crossingCount) {
		this.crossingCount = crossingCount;
	}

	public boolean isExcluded() {
		return excluded;
	}

	public void setExcluded(boolean excluded) {
		this.excluded = excluded;
	}
	
	public String toString(){
		return "Zone: " + x + ", " + y;
	}


}
