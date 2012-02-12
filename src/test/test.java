package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import plateau.MR_plateau;
import rovers.MR_rover;

public class test {

	/**
	 * @param args
	 * @throws IOException 
	 */
	
	public static void main(String[] args) throws IOException {
		
		//plateau
		MR_plateau plateau;
		
		//file reader and buffer
		File test_input = new File("test.txt");
		FileReader test_reader = new FileReader(test_input);
		BufferedReader test_buffer = new BufferedReader(test_reader);
		
		//line buffer and first line read
		String eachLine = test_buffer.readLine();
		
		//create plateau
		String[] dimensions = eachLine.split("\\s+");
		int x = Integer.parseInt(dimensions[0]);
		int y = Integer.parseInt(dimensions[1]);
		plateau = new MR_plateau( x, y );
		
		//read next instruction
		eachLine = test_buffer.readLine();
		
		//file reading instructions for create the rover, move and rotate them
		while (eachLine != null) {			
			//rover creation
			String[] position_face = eachLine.split("\\s+");
			int px = Integer.parseInt(position_face[0]);
			int py = Integer.parseInt(position_face[1]);
			char face = position_face[2].charAt(0);
			MR_rover rover = new MR_rover(px, py, face, plateau);
						
			//read instructions for the rover
			eachLine = test_buffer.readLine().toUpperCase();
			rover.set_and_execute_instructions(eachLine);
			
			//print rover position after instructions
			System.out.println(rover.toString());
			
			//read next instruction
			eachLine = test_buffer.readLine();
		}
	}

}
