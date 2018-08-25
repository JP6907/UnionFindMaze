
public class MazePoint {
	public static int STATE_OF_PASS = 1;
	public static int STATE_OF_BLOCK = 0;
	public int x;
	public int y;
	public int stateFlag;
	public int east;
	public int west;
	public int north;
	public int south;

	public MazePoint() {
		east = STATE_OF_BLOCK;
		west = STATE_OF_BLOCK;
		north = STATE_OF_BLOCK;
		south = STATE_OF_BLOCK;
		stateFlag = -1;
	}
	public void setEastPass(){
		east = STATE_OF_PASS;
	}
	public void setWestPass(){
		west = STATE_OF_PASS;
	}
	public void setNorthPass(){
		north = STATE_OF_PASS;
	}
	public void setSouthPass(){
		south = STATE_OF_PASS;
	}
	public boolean isEastPass(){
		return (east==STATE_OF_PASS)?true:false;
	}
	public boolean isWestPass(){
		return (west==STATE_OF_PASS)?true:false;
	}
	public boolean isNorthPass(){
		return (north==STATE_OF_PASS)?true:false;
	}
	public boolean isSouthPass(){
		return (south==STATE_OF_PASS)?true:false;
	}
}
