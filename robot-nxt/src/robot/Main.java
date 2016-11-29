package robot;


public class Main {

	
	public static void main(String[] args) {
		
		Robot robot = new Robot("hello");
		robot.setContext(new NXTContext());
		
		while(!robot.hasFindExit){
			robot.explore();
		}
		
	}
}
