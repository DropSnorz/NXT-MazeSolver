package robot;

import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;

public class NXTContext implements IContext {


	protected LightSensor l4;
	protected LightSensor l2;
	protected UltrasonicSensor us;

	protected DifferentialPilot pilot;
	public NXTContext(){

		l4 = new LightSensor(SensorPort.S4);
		l2 = new LightSensor(SensorPort.S2);
		us = new UltrasonicSensor(SensorPort.S1);

		Motor.A.setSpeed(400);
		Motor.C.setSpeed(400);

		//TODO fix parameters
		pilot = new DifferentialPilot(1.18f, 4.4f, Motor.A, Motor.C, true);  // parameters in inches
		pilot.setRotateSpeed(50);  // cm per second

		//15cm: 5.9 inches
		//11cm: 4.33 inches
	}

	@Override
	public void reachNextZone() {

		boolean blackline = false;
		Motor.A.forward();
		Motor.C.forward();

		while(!blackline){

			//TODO align robot with black line
			if(l4.getLightValue() < 30 && l2.getLightValue() < 30 ){
				blackline = true;
				Motor.A.stop();
				Motor.C.stop();
			}
		}



		Motor.A.forward();
		Motor.C.forward();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Motor.A.stop();
		Motor.C.stop();

		//pilot.travel(20, false);

	}

	@Override
	public void turnLeft() {

		pilot.rotate(90);
		while(pilot.isMoving())Thread.yield();
		/*
		Motor.A.rotate(600);
		Motor.C.rotate(-600);

		 */

	}

	@Override
	public void turnRight() {

		pilot.rotate(-90);
		while(pilot.isMoving())Thread.yield();
		/*
		Motor.C.rotate(400);
		Motor.A.rotate(-350);
		 */

	}

	@Override
	public int getFrontDistance() {

		int dest = us.getDistance();
		System.out.println(dest);
		if(dest < 20){
			Sound.beep();
			return 10;
		}
		else if (dest > 250){
			return 1000;
		}
		else{
			return 100;
		}

	}
}
