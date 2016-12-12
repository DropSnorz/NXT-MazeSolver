package robot.pathfinding;

public abstract class PathNode {

	PathNode previous;
	int hCost; 
	int fCost;
	int gCost;
	
    public int calculategCosts(PathNode previousNode) {
        
            return previousNode.getgCost() + 1;
        
    }
    
	public PathNode getPrevious() {
		return previous;
	}
	public void setPrevious(PathNode previous) {
		this.previous = previous;
	}
	public int gethCost() {
		return hCost;
	}
	public void sethCost(int hCost) {
		this.hCost = hCost;
	}
	public int getfCost() {
		  return gCost + hCost;
	}
	public void setfCost(int fCost) {
		this.fCost = fCost;
	}
	public int getgCost() {
		return gCost;
	}
	public void setgCost(PathNode node) {
        setgCost(previous, 1);

	}
	public void setgCost(PathNode node, int cost) {
        setgCost(previous.getgCost()+ cost);

	}
	private void setgCost(int cost) {
		this.gCost = cost + 1;
	}
	
	
}
