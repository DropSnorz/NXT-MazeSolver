package robot;

import java.util.List;

import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import robot.pathfinding.PathFinder;

public class Robot {

	protected String name;
	protected Direction direction;
	public boolean hasFindExit = false;
	public boolean exploring = true;

	protected IContext context;
	protected PathFinder pathFinder;

	protected World world;

	public Robot(String name){
		this.name = name;
		direction = Direction.NORTH;
		world = new World();
		pathFinder = new PathFinder(world);
		
	}

	public void explore(){

		Zone currentZone = world.getCurrentZone();

		State state = currentZone.getState(direction);
		boolean scanned = currentZone.getIsScanned(direction);
		Zone nextZone = world.getNextZone(direction);

		boolean scan = false;

		if(state == State.STATE_UNKNOW || !scanned){
			scanContext();
			scan = true;
			state = world.getCurrentZone().getState(direction);

		}

		if(state == State.STATE_INACCESSIBLE || state == State.STATE_ACCESSIBLE_DEAD){
			//Chemin bloqué, on tourne a droite;
			if(currentZone.isBlockedZone()){
				exploring = false;
				System.out.println("I'm blocked");
			}
			else{
				turnRight();
			}
		}

		else if (state == State.STATE_EXIT){
			//On ne fait plus rien, pour l'instant
		}

		else if (state == State.STATE_ACCESSIBLE && !world.hasBeenVisited(nextZone)){
			//Chemin viable et prochaine zone iconnue, on continue (inutile?)
			moveToTheNextZone();
		}

		else if (state == State.STATE_ACCESSIBLE && scan){
			moveToTheNextZone();
		}

		else if(state == State.STATE_ACCESSIBLE && nextZone.isDeadZone()){
			//TODO test validity
			System.out.println("dead: "+nextZone);
			currentZone.setState(direction, State.STATE_ACCESSIBLE_DEAD);
			turnRight();
		}

		else if(state == State.STATE_ACCESSIBLE && 
				world.hasBeenVisited(nextZone) &&
				!currentZone.isFullyDiscovered()){
			//Chemin viable, prochaine zone connue, zone courante non pleinement découverte.
			//On tourne a droite � la recherche de nouveaux chemins.
			turnRight();
		}
			
		else if(state == State.STATE_ACCESSIBLE && 
				currentZone.getLastAccessZone() == nextZone){
			currentZone.setLastAccessZone(currentZone);
			turnRight();
		}
		else{
			moveToTheNextZone();
		}
		
	}

	public void moveToTheNextZone(){
		//TODO impl

		context.reachNextZone();
		world.reachNextZone(direction);
		
		if(!hasFindExit && world.isTrap()){
			System.out.println("Trap detected");
			exploring = false;
		}

	}

	public void turnLeft(){

		context.turnLeft();
		direction = direction.previous();

	}
	public void turnRight(){

		context.turnRight();
		direction = direction.next();

	}

	public void halfTurn(){
		
		context.turnRight();
		context.turnRight();
		direction = direction.reverse();
	}

	public void scanContext(){
		
		int d = context.getFrontDistance();
		boolean available;
		if(d < 100){
			world.getCurrentZone().setStateFromScan(direction, State.STATE_INACCESSIBLE);
			world.getNextZone(direction).setStateFromScan(direction.reverse(), State.STATE_INACCESSIBLE);
		}
		
		else if(d > 900){
			this.hasFindExit = true;
			world.getCurrentZone().setStateFromScan(direction, State.STATE_EXIT);
		}
		
		else{
			world.getCurrentZone().setStateFromScan(direction, State.STATE_ACCESSIBLE);
			world.getNextZone(direction).setState(direction.reverse(), State.STATE_ACCESSIBLE);
		}

	}

	/**
	 * Compute actions to travel from current zone to all
	 * zones on the given list
	 * @param path Ordered zone list
	 */
	public void resolvePath(List<Zone> path){

		for(int i =0; i < path.size(); i++){

			Zone current = world.getCurrentZone();
			Zone next = path.get(i);
			Direction nextDir = World.resolveDirection(current.getX(), current.getY(), next.getX(), next.getY());

			if(direction != nextDir){
				if (direction.next() == nextDir){
					turnRight();
				}
				else if(direction.previous() == nextDir){
					turnLeft();
				}
				else{
					halfTurn();
				}
			}
			moveToTheNextZone();
		}
	}

	public IContext getContext() {
		return context;
	}

	public void setContext(IContext context) {
		this.context = context;
	}

	public PathFinder getPathFinder(){
		return pathFinder;
	}

}
