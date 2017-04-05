# NXT - MazeSolver

Java maze solver implementation running on NXT bricks (Lego Mindstorms NXT). 

A robot is placed inside a simple labyrinth without any knowledge about the context.
The robot have to discover some parts of the maze to find the exit. When the exit has been detected, robot runs A* to compute the shortest path from the exit to the starting point

![Path finding example](http://www.dropsnorz.com/projects/nxt/NXTMazeSolverExample-en.PNG)

## Robot capabilities and Maze structure

Robots instances calls Context objects to retrieve/manage sensors and motors. Default context use two tracked wheels and an ultrasonic sensor in front of the robot.This sensor is used to detect walls. Light sensors tracks black lines on the floor. 

## Tests and Virtual Context

The robot-pc project provides simple tools for testing and trace algorithms on robot instances without using NXT bricks. MazeBuilder allows to create mazes in memory and run a robot instance on it.

```java
MazeBuilder builder =  new MazeBuilder();
builder.addPath(0, 0, Direction.EAST)
	.addExit(0, 1, Direction.NORTH);
MemoryContext context = new MemoryContext(builder.getWorld());
robot.setContext(context);
robot.explore();
```

## About

BIA Project @ Lyon1 University  
Supervisors: Marie Lefevre  
Contributors: [dropsnorz](http://github.com/dropsnorz) (Arthur POIRET), Thibaut Othomene, Kassim Hassani  
