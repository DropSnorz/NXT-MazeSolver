package robot;

import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

public class Robot {

	protected String name;
	protected Direction direction;
	protected boolean hasFindExit;
	protected LightSensor l4;
	protected LightSensor l2;
	protected UltrasonicSensor us;
	
	protected World world;
	
	Robot(String name){
		this.name = name;
		direction = Direction.NORTH;
		l4 = new LightSensor(SensorPort.S4);
		l2 = new LightSensor(SensorPort.S2);
		us = new UltrasonicSensor(SensorPort.S1);
		world = new World();
	}
	
	public void explore(){
		
		//Chemin disponible et zone non visitée, on avance
		//Chemin disponible mais zone visité avec un lien inconnu menant sur une zone non visitée
			// on tourne a droite
		// on avance
		StateEnum state = world.getCurrentZone().getState(direction);
		Zone currentZone = world.getCurrentZone();
		Zone nextZone = world.getNextZone(direction);
		
		if(state == StateEnum.STATE_UNKNOW){
			scanContext();
			state = world.getCurrentZone().getState(direction);

		}
		
		if(state == StateEnum.STATE_INACCESSIBLE){
			//Chemin bloqué, on tourne a droite;
			turnRight();
		}
		else if (state == StateEnum.STATE_ACCESSIBLE && !world.hasBeenVisited(nextZone)){
			//Chemin viable et prochaine zone iconnue, on continue (inutile?)
			moveToTheNextZone();
		}
		else if(state == StateEnum.STATE_ACCESSIBLE && 
				world.hasBeenVisited(nextZone) &&
				!currentZone.isFullyDiscovered()){
			//Chemin viable, prochaine zone connue, zone courante non pleinement découverte.
			//On tourne a droite à la recherche de nouveaux chemins.
			turnRight();
		}
		else{
			moveToTheNextZone();
		}
		
	}
	
	public void moveToTheNextZone(){
		//TODO impl
		Motor.A.forward();
		Motor.C.forward();
		
		world.reachNextZone(direction);
	}
	
	public void turnLeft(){
		//TODO impl
		
		Motor.A.rotate(600);
		Motor.C.rotate(-600);
		direction = direction.previous();
		
	}
	public void turnRight(){
		//TODO impl
		Motor.C.rotate(600);
		Motor.A.rotate(-600);
		direction = direction.next();
		
	}
	
	public boolean scanContext(){
		//TODO impl
		boolean available = true;
		
		if(available){
			world.getCurrentZone().setState(direction, StateEnum.STATE_ACCESSIBLE);
			world.getNextZone(direction).setState(direction.reverse(), StateEnum.STATE_ACCESSIBLE);
		}
		else{
			world.getCurrentZone().setState(direction, StateEnum.STATE_INACCESSIBLE);
			world.getNextZone(direction).setState(direction.reverse(), StateEnum.STATE_INACCESSIBLE);

		}
		
		return available;
	}
}
