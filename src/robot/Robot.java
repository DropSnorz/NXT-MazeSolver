package robot;

public class Robot {

	protected String name;
	protected Direction direction;
	protected boolean hasFindExit;
	
	protected World world;
	
	Robot(String name){
		this.name = name;
		direction = Direction.NORTH;
		world = new World();
	}
	
	public void explore(){
		
		scanContext();
	}
	
	public void moveToTheNextZone(){
		//TODO impl
	}
	
	public void turnLeft(){
		//TODO impl
		
	}
	public void turnRight(){
		//TODO impl
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
