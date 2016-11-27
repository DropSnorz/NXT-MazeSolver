package robot;

import java.util.HashMap;
import java.util.Map;

public class Zone {

	protected int x;
	protected int y;

	protected Map<Direction, StateEnum> stateMap;

	public Zone(int x, int y) {
		this.x = x;
		this.y = y;
		stateMap = new HashMap<Direction, StateEnum>();
		stateMap.put(Direction.NORTH, StateEnum.STATE_UNKNOW);
		stateMap.put(Direction.SOUTH, StateEnum.STATE_UNKNOW);
		stateMap.put(Direction.EAST, StateEnum.STATE_UNKNOW);
		stateMap.put(Direction.WEST, StateEnum.STATE_UNKNOW);
	}

	public void setState(Direction direction, StateEnum state){

		stateMap.put(direction, state);

	}
	public StateEnum getState(Direction direction){

		return stateMap.get(direction);

	}

	public boolean isFullyDiscovered(){

		for(Map.Entry<Direction,StateEnum > entry : stateMap.entrySet()) {
			StateEnum value = entry.getValue();

			if(value == StateEnum.STATE_UNKNOW){
				return false;
			}
		}
		return true;
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