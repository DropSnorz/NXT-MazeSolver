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
	public boolean hasFindExit;

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

		//Chemin disponible et zone non visit�e, on avance
		//Chemin disponible mais zone visit� avec un lien inconnu menant sur une zone non visit�e
		// on tourne a droite
		// on avance
		Zone currentZone = world.getCurrentZone();

		StateEnum state = currentZone.getState(direction);
		boolean scanned = currentZone.getIsScanned(direction);
		Zone nextZone = world.getNextZone(direction);

		boolean scan = false;


		if(state == StateEnum.STATE_UNKNOW || !scanned){
			scanContext();
			scan = true;
			state = world.getCurrentZone().getState(direction);

		}


		if(state == StateEnum.STATE_INACCESSIBLE){
			//Chemin bloqu�, on tourne a droite;
			turnRight();
		}

		else if (state == StateEnum.STATE_EXIT){
			//On ne fait plus rien, pour l'instant
		}

		else if (state == StateEnum.STATE_ACCESSIBLE && !world.hasBeenVisited(nextZone)){
			//Chemin viable et prochaine zone iconnue, on continue (inutile?)
			moveToTheNextZone();
		}


		else if (state == StateEnum.STATE_ACCESSIBLE && scan){
			moveToTheNextZone();
		}



		else if(state == StateEnum.STATE_ACCESSIBLE && 
				world.hasBeenVisited(nextZone) &&
				!currentZone.isFullyDiscovered()){
			//Chemin viable, prochaine zone connue, zone courante non pleinement d�couverte.
			//On tourne a droite � la recherche de nouveaux chemins.
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
		//TODO impl real half turn
		context.turnRight();
		context.turnRight();

		direction = direction.reverse();
	}

	public void scanContext(){
		//TODO impl
		int d = context.getFrontDistance();
		boolean available;
		if(d < 100){
			world.getCurrentZone().setStateFromScan(direction, StateEnum.STATE_INACCESSIBLE);
			world.getNextZone(direction).setStateFromScan(direction.reverse(), StateEnum.STATE_INACCESSIBLE);
		}

		else if(d > 900){
			this.hasFindExit = true;
			world.getCurrentZone().setStateFromScan(direction, StateEnum.STATE_EXIT);
		}
		else{
			world.getCurrentZone().setStateFromScan(direction, StateEnum.STATE_ACCESSIBLE);
			// On force le robot a explorer les liens dans les deux sens
			world.getNextZone(direction).setState(direction.reverse(), StateEnum.STATE_ACCESSIBLE);
		}

	}

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
