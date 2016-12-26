import robot.Direction;
import robot.World;

public class MazePatterns {

	
	public static World GMaze(){
		// G Maze
		MazeBuilder builder = new MazeBuilder();
		builder.addPath(0, 0, Direction.NORTH)
		.addPath(0, 0, Direction.EAST)
		.addPath(1, -1, Direction.SOUTH)
		.addPath(1, -1, Direction.NORTH)
		.addExit(1, -2, Direction.WEST);

		return builder.getWorld();
	}
	
	public static World loopMaze(){
		
		MazeBuilder builder = new MazeBuilder();
		builder.addPath(0, 0, Direction.EAST)
		
		.addPath(0, 0, Direction.WEST)
		.addPath(-1, 0, Direction.WEST)
		.addExit(-2, 0, Direction.SOUTH)
		
		.addPath(1, 0, Direction.NORTH)
		.addPath(1, 0, Direction.SOUTH)

		.addPath(1, -1, Direction.EAST)

		.addPath(2, -1, Direction.EAST)

		.addPath(3, -1, Direction.SOUTH)

		.addPath(3, 0, Direction.SOUTH)

		.addPath(3,1, Direction.WEST)

		.addPath(2,1, Direction.WEST)

		.addPath(1,1, Direction.NORTH);

		return builder.getWorld();
		
	}
	
public static World loopMazeWithoutExit(){
		
		MazeBuilder builder = new MazeBuilder();
		builder.addPath(0, 0, Direction.EAST)
		.addPath(1, 0, Direction.NORTH)
		.addPath(1, 0, Direction.SOUTH)
		.addPath(1, -1, Direction.EAST)
		.addPath(2, -1, Direction.EAST)
		.addPath(3, -1, Direction.SOUTH)
		.addPath(3, 0, Direction.SOUTH)
		.addPath(3,1, Direction.WEST)
		.addPath(2,1, Direction.WEST)
		.addPath(1,1, Direction.NORTH);

		return builder.getWorld();
		
	}
	
	/**
	 * Robot in front of exit
	 */
	public static World noMaze(){
		//Robot in front of exit
		MazeBuilder builder4 =  new MazeBuilder();
		builder4.addExit(0, 0, Direction.NORTH);
		return builder4.getWorld();
	}
	
	public static World blockedMaze(){
		//Robot in front of exit
		MazeBuilder builder4 =  new MazeBuilder();
		builder4.addPath(0, 0, Direction.NORTH);
		return builder4.getWorld();
	}
}
