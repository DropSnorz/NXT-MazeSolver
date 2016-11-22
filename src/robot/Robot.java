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
		
		scanContext();
	}
	
	public void moveToTheNextZone(){
		//TODO impl
		Motor.A.forward();
		Motor.C.forward();
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
		}
		else{
			world.getCurrentZone().setState(direction, StateEnum.STATE_INACCESSIBLE);

		}
		
		return available;
	}
}
