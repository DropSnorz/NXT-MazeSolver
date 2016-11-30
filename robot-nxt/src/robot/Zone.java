package robot;

import java.util.HashMap;
import java.util.Map;

import robot.pathfinding.PathNode;

public class Zone extends PathNode {

	protected int x;
	protected int y;
	
	public int cost;
	protected Map<Direction, StateEnum> stateMap;
	protected Map<Direction, Boolean> scanMap;


	public Zone(int x, int y) {
		this.x = x;
		this.y = y;
		stateMap = new HashMap<Direction, StateEnum>();
		scanMap = new HashMap<Direction, Boolean>();

		stateMap.put(Direction.NORTH, StateEnum.STATE_UNKNOW);
		stateMap.put(Direction.SOUTH, StateEnum.STATE_UNKNOW);
		stateMap.put(Direction.EAST, StateEnum.STATE_UNKNOW);
		stateMap.put(Direction.WEST, StateEnum.STATE_UNKNOW);
		
		scanMap.put(Direction.NORTH, false);
		scanMap.put(Direction.SOUTH, false);
		scanMap.put(Direction.EAST, false);
		scanMap.put(Direction.WEST, false);
	}

	public void setStateFromScan(Direction direction, StateEnum state){

		stateMap.put(direction, state);
		scanMap.put(direction, true);

	}
	
	public void setState(Direction direction, StateEnum state){

		stateMap.put(direction, state);

	}
	public StateEnum getState(Direction direction){

		return stateMap.get(direction);

	}

	public boolean isFullyDiscovered(){

		for(Map.Entry<Direction,Boolean > entry : scanMap.entrySet()) {
			Boolean value = entry.getValue();

			if(value == false){
				return false;
			}
		}
		return true;
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
	
	
	public String toString(){
		return "Zone: " + x + ", " + y;
	}


}
