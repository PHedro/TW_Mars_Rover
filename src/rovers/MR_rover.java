package rovers;

import java.util.ArrayList;
import java.util.List;

import plateau.MR_plateau;

public class MR_rover {	
	private final char SOUTH = 'S';
	private final char NORTH = 'N';
	private final char WEST  = 'W';
	private final char EAST  = 'E';
	private final char MOVE  = 'M';
	private final char LEFT  = 'L';
	private final char RIGHT = 'R';
	private final static String CARDINALS = "NESW";
	
	private char faced_to;
	private int[] position = new int[2];
	private List<String> instructions_history = new ArrayList<String>();
	private MR_plateau plateau_assigned;
	
	public MR_rover(int x, int y, char face, MR_plateau assigned_to){
		setPosition( x,y);
		setFaced_to(face);
		setPlateau_assigned(assigned_to);
	}
	
	public String toString(){
		return ""+getPosition()[0]+" "+getPosition()[1]+" "+getFaced_to()+"";
	}
	
	//string with directions that can be faced
	public static String getCardinals(){
		return CARDINALS;
	}

	public MR_plateau getPlateau_assigned() {
		return plateau_assigned;
	}

	public void setPlateau_assigned(MR_plateau plateau_assigned) {
		this.plateau_assigned = plateau_assigned;
		if(null != plateau_assigned){
			plateau_assigned.assign_rover(this);
		}
		
	}
	
	public char getFaced_to() {
		return faced_to;
	}

	//set to where the rover is faced if non valid direction faces north
	public void setFaced_to(char faced_to) {
		if(getCardinals().indexOf(faced_to) != -1){
			this.faced_to = faced_to;
		}
		else{
			this.faced_to = getCardinals().charAt(0);
		}
	}

	public int[] getPosition() {
		return position;
	}
	
	public void setPosition(int[] position) {
		this.position = position;
	}

	public void setPosition(int x, int y) {
		this.position[0] = x;
		this.position[1] = y;
	}
		
	public List<String> getInstructions_history() {
		return instructions_history;
	}
	
	public boolean set_and_execute_instructions(String instructions){
		boolean instructions_executed = true;
		//storing instructions for the rover
		this.instructions_history.add(instructions);
		
		char[] instructions_array = instructions.toUpperCase().toCharArray();
		
		for(char instruction : instructions_array ){
			execute(instruction);
		}
		
		return instructions_executed;
	}
	
	private void execute(char instruction){		
		switch (instruction) {
		case LEFT:
		case RIGHT:
			this.turn(instruction);
			break;
		case MOVE:
			this.move();
			break;
		default:
			System.out.println("Instruction "+instruction+" not avaiable!");
			break;
		}
	}
	
	//move the rover 1 unit 
	private void move(){
		int[] aux = getPosition();
		switch (getFaced_to()) {
		case NORTH:
			if(check_limits_before_move(aux[1] + 1))
				aux[1] += 1;
			break;
		case SOUTH:
			if(check_limits_before_move(aux[1] - 1))
				aux[1] -= 1;
			break;
		case WEST:
			if(check_limits_before_move(aux[0] - 1))
				aux[0] -= 1;
			break;
		case EAST:
			if(check_limits_before_move(aux[0] + 1))
				aux[0] += 1;
			break;
		default:
			break;
		}
		this.setPosition(aux);
	}
	
	private boolean check_limits_before_move(int new_pos){
		boolean can_move = true;
		
		switch (getFaced_to()) {
		case NORTH:
		case WEST:
			can_move = new_pos < getPlateau_assigned().getLimit(getFaced_to());
			break;
		case SOUTH:
		case EAST:
			can_move = 0 <= new_pos; 
			break;
		default:
			break;
		}
		
		return can_move;
	}

	//rotate the rover 90 degrees to the chosen side
	private void turn(char turn){
		int index = getCardinals().indexOf(getFaced_to());
		
		if(turn == RIGHT){
			index = index + 1 == 4 ? 0 : index + 1;
		}
		else if(turn == LEFT){
			index = index - 1 < 0 ? 3 : index - 1;
		}
		
		this.setFaced_to(getCardinals().charAt(index));
	}
	
}
