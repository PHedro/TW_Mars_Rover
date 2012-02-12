package plateau;

import java.util.ArrayList;
import java.util.List;

import rovers.MR_rover;

public class MR_plateau {
	private int[] size = new int[2];
	private final char NORTH = 'N';
	
	private List<MR_rover> rovers;
	
	public MR_plateau(int x, int y){
		this.size[0] = x;
		this.size[1] = y;
	}
	
	public void assign_rover(MR_rover rover){
		if(null == rovers){
			rovers = new ArrayList<MR_rover>();
		}
		rovers.add(rover);
	}
	
	public List<MR_rover> getRovers(){
		return rovers;
	}
	
	//return limit that can be reached by the rover, if NORTH the limit to north, else 
	//limit to east
	public int getLimit(char direction){
		return direction == NORTH ? this.size[0] : this.size[1];
	}
}
