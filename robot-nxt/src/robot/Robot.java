package robot;

import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

public class Robot {

	protected String name;
	protected Direction direction;
	public boolean hasFindExit;
	protected IContext context;
	
	protected World world;
	
	public Robot(String name){
		this.name = name;
		direction = Direction.NORTH;
		/*
		l4 = new LightSensor(SensorPort.S4);
		l2 = new LightSensor(SensorPort.S2);
		us = new UltrasonicSensor(SensorPort.S1);
		*/
		world = new World();
	}
	
	public void explore(){
		
		//Chemin disponible et zone non visit�e, on avance
		//Chemin disponible mais zone visit� avec un lien inconnu menant sur une zone non visit�e
			// on tourne a droite
		// on avance
		
		StateEnum state = world.getCurrentZone().getState(direction);
		Zone currentZone = world.getCurrentZone();
		Zone nextZone = world.getNextZone(direction);
		
		boolean scan = false;
		
		
		if(state == StateEnum.STATE_UNKNOW){
			scanContext();
			scan = true;
			state = world.getCurrentZone().getState(direction);

		}
		
		
		
		if(state == StateEnum.STATE_INACCESSIBLE){
			//Chemin bloqu�, on tourne a droite;
			turnRight();
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
	
	public boolean scanContext(){
		//TODO impl
		int d = context.getFrontDistance();
		boolean available;
		if(d < 100){
			available = false;
		}
		else{
			available = true;
		}
		
		if(d > 900){
			this.hasFindExit = true;
		}
		
		if(available){
			world.getCurrentZone().setState(direction, StateEnum.STATE_ACCESSIBLE);
			// On force le robot a explorer les liens dans les deux sens
			//world.getNextZone(direction).setState(direction.reverse(), StateEnum.STATE_ACCESSIBLE);
		}
		else{
			world.getCurrentZone().setState(direction, StateEnum.STATE_INACCESSIBLE);
			world.getNextZone(direction).setState(direction.reverse(), StateEnum.STATE_INACCESSIBLE);

		}
		
		return available;
	}

	public IContext getContext() {
		return context;
	}

	public void setContext(IContext context) {
		this.context = context;
	}
	
	
}
