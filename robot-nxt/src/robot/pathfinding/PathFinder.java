package robot.pathfinding;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import robot.World;
import robot.Zone;

public class PathFinder {

	protected List<Zone> open;
	protected List<Zone> close;

	protected World world;

	public PathFinder(World world){
		this.world = world;
	}

	public List<Zone> findPath(){

		open = new LinkedList<Zone>();
		close = new LinkedList<Zone>();
		Zone firstNode  = world.getCurrentZone();
		open.add(firstNode); //add current node to open list

		Zone currentNode;
		boolean done = false;
		while(!done){

			currentNode = getLowestCostInOpen();

			close.add(currentNode);
			open.remove(currentNode);

			//Init zone found
			if(currentNode.getX() == 0 && currentNode.getY() == 0){
				System.out.println("Path finded");
				return generatePath(firstNode,currentNode);
			}

			for (Zone neighboor : world.getAccessibleNeighboorZones(currentNode.getX(), currentNode.getY())){
				if(!close.contains(neighboor)){
					if (!open.contains(neighboor)) { 
						neighboor.setPrevious(currentNode); 
						neighboor.sethCost(1); 
						neighboor.setgCost(currentNode); 
						open.add(neighboor); 
					}
					else if(neighboor.getgCost() > neighboor.calculategCosts(currentNode)) { // costs from current node are cheaper than previous costs
						neighboor.setPrevious(currentNode); 
						neighboor.setgCost(currentNode); 
					}

				}   
			}
			if (open.isEmpty()) { // no path exists
				return new LinkedList<Zone>(); 
			}
		}
		return null;
	}

	private List<Zone> generatePath(Zone start, Zone goal) {

		LinkedList<Zone> path = new LinkedList<Zone>();

		Zone current = goal;
		boolean done = false;
		
		//Start equals goal, no path required
		if(start == goal){
			return path;
		}
		while (!done) {
			path.add(0,current);

			current = (Zone) current.getPrevious();

			if (current.equals(start)) {
				done = true;
			}
		}
		return path;
	}


	private Zone getLowestCostInOpen() {
		Zone current = open.get(0);
		for (int i = 0; i < open.size(); i++) {
			if (open.get(i).getfCost() < current.getfCost()) {
				current = open.get(i);
			}
		}
		return current;
	}
}
