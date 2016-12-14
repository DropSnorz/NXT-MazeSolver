package robot.pathfinding;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import robot.World;
import robot.Zone;

/**
 * PathFinder Class
 * Provide A* implementation to find path between two zones
 * 
 * Highly inspired frm work by Tim Coen
 * http://software-talk.org/blog/2012/01/a-star-java/
 */
public class PathFinder {

	protected List<Zone> open;
	protected List<Zone> close;
	protected boolean useHeurisitcs = true;

	protected World world;

	public PathFinder(World world){
		this.world = world;
	}

	/**
	 * Run A* on the given world
	 * Return shortest path to origin node (0,0)
	 * @return Ordered list with zones from current to origin point
	 * or empty list if no path has been found
	 */
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

			//Origin zone found
			if(currentNode.getX() == 0 && currentNode.getY() == 0){
				System.out.println("Path finded");
				return generatePath(firstNode,currentNode);
			}

			//For all neighbors of the current node
			for (Zone neighbor : world.getAccessibleNeighborZones(currentNode.getX(), currentNode.getY())){
				if(!close.contains(neighbor)){
					//If neighbor is not in open list (and not in closed)
					if (!open.contains(neighbor)) { 
						//Init costs
						neighbor.setPrevious(currentNode); 
						if(useHeurisitcs){
							neighbor.sethCost(generatehCost(currentNode, neighbor)); 
						}
						else{
							neighbor.sethCost(0); 
						}
						neighbor.setgCost(currentNode); 
						//Add neighbor to open list
						open.add(neighbor); 
					}
					//If neighbor is in open list (and not in closed) and costs are better than previous
					else if(neighbor.getgCost() > neighbor.calculategCosts(currentNode)) { // costs from current node are cheaper than previous costs
						//Update previous and cost
						neighbor.setPrevious(currentNode); 
						neighbor.setgCost(currentNode); 
					}

				}   
			}
			if (open.isEmpty()) { // no path exists
				return new LinkedList<Zone>(); 
			}
		}
		return null;
	}

	/**
	 * Generate full path from A* results
	 * @param start Current zone
	 * @param goal Origine zone
	 * @return Ordered list with zones from current to origin point
	 */
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
	
	/**
	 * Generate heuristic cost for two adjacent nodes
	 * 
	 * Return positive cost if next zone is more away from (0,0) than current Zone
	 * This only works for neighbors zones
	 * @param current
	 * @param dest
	 * @return cost
	 */
	private int generatehCost(Zone current, Zone dest){
		
		if(Math.abs(current.getX()) - Math.abs(dest.getX()) >= 1){
			return 0;
		}
		if(Math.abs(current.getY()) - Math.abs(dest.getY()) >= 1){
			return 0;
		}
		
		return 1;
	}
}
