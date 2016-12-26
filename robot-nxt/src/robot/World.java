package robot;

import java.util.ArrayList;
import java.util.List;

/**
 * Maze and context representation
 *
 */
public class World {

	protected List<Zone>zoneList;

	protected List<Zone>visitedZones;
	protected Zone currentZone;

	public World() {

		zoneList = new ArrayList<Zone>();
		visitedZones = new ArrayList<Zone>();
		Zone initZone = new Zone(0,0);
		currentZone = initZone;
		zoneList.add(initZone);
		visitedZones.add(initZone);

		initZoneBorders(0,0);
	}

	public Zone getOrCreateZone(int x, int y){
		if(!containsZone(x,y)){
			Zone zone = new Zone(x,y);
			zoneList.add(zone);
			initZoneBorders(x,y);
			System.out.println("Init:" + zone);

			return zone;
		}
		else{
			initZoneBorders(x,y);

			return getZone(x,y);
		}
	}
	
	/**
	 * Update maze to reach the given direction
	 * @param direction
	 * @return reached zone
	 */
	public Zone reachNextZone(Direction direction){

		int x = translateX(currentZone.getX(), direction);
		int y = translateY(currentZone.getY(), direction);

		Zone zone;
		if(!containsZone(x,y)){
			zone = new Zone(x,y);
			zoneList.add(zone);

		}
		else{
			zone = getZone(x,y);
		}
		
		initZoneBorders(x,y);

		if(!hasBeenVisited(zone)){
			visitedZones.add(zone);
		}
		zone.setCrossingCount(zone.getCrossingCount() + 1);
		currentZone = zone;
		return zone;
	}

	/**
	 * Check if the given coordinate are initialized
	 * @param x
	 * @param y
	 * @return Return true if zone exist for the given point
	 */
	public boolean containsZone(int x, int y){

		for(Zone zone: zoneList){
			if(zone.getX() == x && zone.getY() == y){
				return true;
			}
		}
		return false;
	}

	/**
	 * Retrieve zone at the given coordinates
	 * @param x
	 * @param y
	 * @return Zone
	 */
	public Zone getZone(int x, int y){
		for(Zone zone: zoneList){
			if(zone.getX() == x && zone.getY() == y){
				return zone;
			}
		}
		return null;
	}

	/**
	 * Initialize all neighbors of the given coordinate
	 * @param x
	 * @param y
	 */
	protected void initZoneBorders(int x, int y){

		if(!containsZone(x + 1,y)){
			zoneList.add(new Zone(x + 1,y));
		}
		if(!containsZone(x - 1,y)){
			zoneList.add(new Zone(x - 1,y));

		}
		if(!containsZone(x,y + 1)){
			zoneList.add(new Zone(x,y + 1));

		}
		if(!containsZone(x,y - 1)){
			zoneList.add(new Zone(x,y - 1));
		}
	}
	
	/**
	 * Return next zone from current zone at the given direction
	 * @param direction
	 * @return
	 */
	public Zone getNextZone(Direction direction) {

		int x = currentZone.getX();
		int y = currentZone.getY();

		return getNextZone(x,y,direction);

	}

	/**
	 * Return next zone from given zone and direction
	 * @param x
	 * @param y
	 * @param direction
	 * @return
	 */
	public Zone getNextZone(int x, int y, Direction direction){
		
		int nextX = translateX(x, direction);
		int nextY = translateY(y,direction);

		return getZone(nextX,nextY);
	}

	public ArrayList<Zone> getAccessibleNeighborZones(int x, int y){
		ArrayList<Zone> list = new ArrayList<Zone>();
		
		Zone zone = getZone(x,y);
		for(Direction direction : Direction.values()){
			State state = zone.getState(direction);
			if(state == State.STATE_ACCESSIBLE ||
					state == State.STATE_ACCESSIBLE_DEAD){
				list.add(getZone(translateX(x,direction), translateY(y,direction)));
			}
		}

		return list;
	}
	
	public ArrayList<Zone> getNeighborZones(int x, int y){
		ArrayList<Zone> list = new ArrayList<Zone>();
		
		//TODO use translate X Y
		list.add(getZone(x+1, y));
		list.add(getZone(x-1, y));
		list.add(getZone(x, y+1));
		list.add(getZone(x, y-1));

		return list;

	}

	public boolean hasBeenVisited(Zone zone){
		return visitedZones.contains(zone);
	}
	
	public boolean isTrap(){
		
		for(Zone zone : visitedZones){
			if(!zone.isFullyDiscovered()){
				return false;
			}
		}
		return true;
	}

	public Zone getCurrentZone() {
		return currentZone;
	}

	public void setCurrentZone(Zone currentZone) {
		this.currentZone = currentZone;
	}
	
	/**
	 * Compute direction from (x1,y1) to (x2, y2)
	 * This only work for neighbors points
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public static Direction resolveDirection(int x1, int y1, int x2, int y2){
		
		if(x1 - x2 == -1){
			return Direction.EAST;
		}
		else if (x1 - x2 == 1){
			return Direction.WEST;
		}
		else{
			if(y1 - y2 == -1){
				return Direction.SOUTH;
			}
			else if(y1-y2 == 1){
				return Direction.NORTH;
			}
			else{
				System.out.println("Error, next zone only");
				return null;
			}
		}
	}
	
	/**
	 * Compute X coordinate from given X and direction
	 * @param x
	 * @param direction
	 * @return x + 1 if Direction.EAST, x - 1 if Direction.WEST, x otherwise
	 */
	public static int translateX(int x, Direction direction){

		if (direction == Direction.EAST){
			x = x + 1;
		}
		else if (direction == Direction.WEST){
			x = x - 1;
		}
		return x;
	}
	
	/**
	 * Compute Y coordinate from given Y and direction
	 * @param y
	 * @param direction
	 * @return y + 1 if Direction.NORTH, y - 1 if Direction.SOUTH, y otherwise
	 */
	public static int translateY(int y, Direction direction){

		if(direction == Direction.NORTH){
			y = y - 1;
		}
		else if (direction == Direction.SOUTH){
			y = y + 1;
		}
		
		return y;
	}

}
