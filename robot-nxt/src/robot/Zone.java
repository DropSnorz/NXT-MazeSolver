package robot;

import java.util.HashMap;
import java.util.Map;

import robot.pathfinding.PathNode;

public class Zone extends PathNode {

	protected int x;
	protected int y;
	protected boolean excluded = false;

	public int cost;
	protected Map<Direction, State> stateMap;
	protected Map<Direction, Boolean> scanMap;


	public Zone(int x, int y) {
		this.x = x;
		this.y = y;
		stateMap = new HashMap<Direction, State>();
		scanMap = new HashMap<Direction, Boolean>();

		stateMap.put(Direction.NORTH, State.STATE_UNKNOW);
		stateMap.put(Direction.SOUTH, State.STATE_UNKNOW);
		stateMap.put(Direction.EAST, State.STATE_UNKNOW);
		stateMap.put(Direction.WEST, State.STATE_UNKNOW);

		scanMap.put(Direction.NORTH, false);
		scanMap.put(Direction.SOUTH, false);
		scanMap.put(Direction.EAST, false);
		scanMap.put(Direction.WEST, false);
	}

	public void setStateFromScan(Direction direction, State state){

		stateMap.put(direction, state);
		scanMap.put(direction, true);

	}

	public void setState(Direction direction, State state){

		stateMap.put(direction, state);

	}
	public State getState(Direction direction){

		return stateMap.get(direction);

	}

	public boolean isFullyDiscovered(){

		//Robot seems to have troubles iterating over sets

		if(scanMap.get(Direction.NORTH) == false){
			return false;
		}
		if(scanMap.get(Direction.EAST) == false){
			return false;
		}
		if(scanMap.get(Direction.SOUTH) == false){
			return false;
		}
		if(scanMap.get(Direction.WEST) == false){
			return false;
		}
		return true;
	}

	public boolean isDeadZone(){

		//Robot seems to have troubles iterating over sets
		
		if(getCountBlockedZones() >= 3){
			return true;
		}
		return false;

	}
	
	public boolean isBlockedZone(){

		//Robot seems to have troubles iterating over sets
		
		if(getCountBlockedZones() >= 4){
			return true;
		}
		return false;

	}
	
	
	public int getCountBlockedZones(){
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
	public boolean getIsScanned(Direction direction) {

		return scanMap.get(direction);
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
	

	public boolean isExcluded() {
		return excluded;
	}

	public void setExcluded(boolean excluded) {
		this.excluded = excluded;
	}
	

	/*
	public boolean equals(Object o){

		if(o instanceof Zone){
			Zone zone = (Zone)o;
			if(this.x == zone.getX() && this.y == zone.getY()){
				return true;
			}
			return false;
		}
		else{
			return super.equals(o);
		}
	}

	 */


	public String toString(){
		return "Zone: " + x + ", " + y;
	}


}
