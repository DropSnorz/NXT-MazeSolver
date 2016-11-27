package robot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class World {

	protected Set<Zone>zoneList;
	
	protected List<Zone>visitedZones;
	protected Zone currentZone;

	public World() {
	
		zoneList = new HashSet<Zone>();
		visitedZones = new ArrayList<Zone>();
		Zone initZone = new Zone(0,0);
		currentZone = initZone;
		zoneList.add(initZone);
		visitedZones.add(initZone);
		
		zoneList.add(new Zone(0,-1));
		zoneList.add(new Zone(1,0));
		zoneList.add(new Zone(0,1));
		zoneList.add(new Zone(-1,0));
	}
	
	public Zone getOrCreateZone(int x, int y){
		if(!containsZone(x,y)){
			Zone zone = new Zone(x,y);
			zoneList.add(zone);
			initZoneBorders(x,y);
			System.out.println(zone);


			return zone;
		}
		else{
			return getZone(x,y);
		}
	}
	
	public Zone reachNextZone(Direction direction){
		
		int x = currentZone.getX();
		int y = currentZone.getY();
		
		if(direction == Direction.NORTH){
			y = y - 1;
		}
		else if(direction == Direction.SOUTH){
			y = y + 1;
		}
		else if(direction == Direction.EAST){
			x = x +1;
		}
		else{
			x = x -1;
		}
		
		Zone zone;
		if(!containsZone(x,y)){
			zone = new Zone(x,y);
			zoneList.add(zone);
			
		}else{
			zone = getZone(x,y);
		}
		initZoneBorders(x,y);

		
		if(!hasBeenVisited(zone)){
			visitedZones.add(zone);
		}
		currentZone = zone;
		return zone;
	}
	
	public boolean containsZone(int x, int y){
		
		for(Zone zone: zoneList){
			if(zone.getX() == x && zone.getY() == y){
				return true;
			}
		}
		return false;
	}
	
	public Zone getZone(int x, int y){
		for(Zone zone: zoneList){
			if(zone.getX() == x && zone.getY() == y){
				return zone;
			}
		}
		return null;
	}
	
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

	public Zone getNextZone(Direction direction) {
		
		int x = currentZone.getX();
		int y = currentZone.getY();
		
		if(direction == Direction.NORTH){
			y = y - 1;
		}
		else if(direction == Direction.SOUTH){
			y = y + 1;
		}
		else if(direction == Direction.EAST){
			x = x +1;
		}
		else{
			x = x -1;
		}
		
		return getZone(x,y);
	}
	
	public boolean hasBeenVisited(Zone zone){
		return visitedZones.contains(zone);
	}
	
	public Zone getCurrentZone() {
		return currentZone;
	}

	public void setCurrentZone(Zone currentZone) {
		this.currentZone = currentZone;
	}
	
}

 	