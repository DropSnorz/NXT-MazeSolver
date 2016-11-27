package robot;

import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

public class NXTContext implements IContext {
	
		
	protected LightSensor l4;
	protected LightSensor l2;
	protected UltrasonicSensor us;
	
	public NXTContext(){

		l4 = new LightSensor(SensorPort.S4);
		l2 = new LightSensor(SensorPort.S2);
		us = new UltrasonicSensor(SensorPort.S1);
		
	}

	@Override
	public void reachNextZone() {
		//TODO impl
		Motor.A.forward();
		Motor.C.forward();
		
		
	}

	@Override
	public void turnLeft() {
		Motor.A.rotate(600);
		Motor.C.rotate(-600);
		
	}

	@Override
	public void turnRight() {
		Motor.C.rotate(600);
		Motor.A.rotate(-600);
		
	}

	@Override
	public int getFrontDistance() {
		// TODO impl
		return 0;
	}

}
