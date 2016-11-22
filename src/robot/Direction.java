package robot;

public enum Direction {

	NORTH{
		@Override
		public Direction previous(){
			return values()[3];
		}
	},
	EAST,
	SOUTH,
	WEST{
		@Override
		public Direction next(){
			return values()[0];
		}
	};
	
	
	
	public Direction next(){
		return values()[ordinal()+1];
	}

	public Direction previous(){
		return values()[ordinal()-1];
	}
}
