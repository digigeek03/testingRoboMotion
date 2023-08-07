package com.coen448.project;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RobotMotionTest {
    boolean isTerminated = false;
    ByteArrayOutputStream  outputstream=new ByteArrayOutputStream();
    

    @BeforeEach
    public void setUp() {
        RobotMotion.robotHdl = new Robot(20);
        System.setOut(new PrintStream(outputstream));
    }
    
    @Test
    public void valid_pen_up_command() {
        
        String command = "u";
        RobotMotion.processCommand(command);
        assertTrue(RobotMotion.robotHdl.mIsPenUp);
    }

    @Test
    public void valid_pen_down_command() {
        
        String command = "d";
        RobotMotion.processCommand(command);
        assertFalse(RobotMotion.robotHdl.mIsPenUp);
    }

    @Test
    public void valid_turn_east_from_north() {
        
        String command = "R";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.North;
       
        RobotMotion.processCommand(command);
        
        assertEquals(RobotMotion.robotHdl.mOrientation, RobotMotion.robotHdl.mOrientation.East);
    }

    @Test
    public void valid_turn_south_from_east() {
        
        String command = "R";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.East;
        
        RobotMotion.processCommand(command);
        
        assertEquals(RobotMotion.robotHdl.mOrientation, RobotMotion.robotHdl.mOrientation.South);
    }

    @Test
    public void valid_turn_west_from_south() {
        
        String command = "R";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.South;
        
        RobotMotion.processCommand(command);
        
        assertEquals(RobotMotion.robotHdl.mOrientation, RobotMotion.robotHdl.mOrientation.West);
    }

    @Test
    public void valid_turn_north_from_west() {
        
        String command = "R";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.West;
        
        RobotMotion.processCommand(command);
        
        assertEquals(RobotMotion.robotHdl.mOrientation, RobotMotion.robotHdl.mOrientation.North);
    }

    @Test
    public void valid_turn_west_from_north() {
        
        String command = "L";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.North;
        
        RobotMotion.processCommand(command);
        
        assertEquals(RobotMotion.robotHdl.mOrientation, RobotMotion.robotHdl.mOrientation.West);
    }

    @Test
    public void valid_turn_south_from_west() {
        
        String command = "L";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.West;
        
        RobotMotion.processCommand(command);
        
        assertEquals(RobotMotion.robotHdl.mOrientation, RobotMotion.robotHdl.mOrientation.South);
    }

    @Test
    public void valid_turn_east_from_south() {
        
        String command = "L";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.South;
        
        RobotMotion.processCommand(command);
        
        assertEquals(RobotMotion.robotHdl.mOrientation, RobotMotion.robotHdl.mOrientation.East);
    }

    @Test
    public void valid_turn_north_east() {
        
        String command = "L";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.East;
        
        RobotMotion.processCommand(command);
        
        assertEquals(RobotMotion.robotHdl.mOrientation, RobotMotion.robotHdl.mOrientation.North);
    }

    @Test
    public void valid_move_forward_north() {
        
        String command = "M 6";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.North;
        RobotMotion.processCommand(command);
        
        assertAll(
                () -> assertEquals(RobotMotion.robotHdl.mRow, 6),
                () -> assertEquals(RobotMotion.robotHdl.mColumn, 0)
        );
    }

    @Test
    public void valid_move_forward__east() {
        
        String command = "M 6";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.East;
        RobotMotion.processCommand(command);
        
        assertAll(
                
                () -> assertEquals(RobotMotion.robotHdl.mRow, 0),
                () -> assertEquals(RobotMotion.robotHdl.mColumn, 6)
        );
    }

    @Test
    public void valid_move_forward_south() {
        
        String command = "M 6";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.South;
        RobotMotion.robotHdl.mRow = 6;
        RobotMotion.processCommand(command);
        
        assertAll(
                
                () -> assertEquals(RobotMotion.robotHdl.mRow, 0),
                () -> assertEquals(RobotMotion.robotHdl.mColumn, 0)
        );
    }

    @Test
    public void valid_move_forward_west() {
        
        String command = "M 5";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.West;
        RobotMotion.robotHdl.mColumn = 5;
        RobotMotion.processCommand(command);
        
        assertAll(
                
                () -> assertEquals(RobotMotion.robotHdl.mRow, 0),
                () -> assertEquals(RobotMotion.robotHdl.mColumn, 0)
        );
    }

    @Test
    public void invalid_case_1_not_move_forward_beyond_north_boundary() {
        
        String command = "M 5";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.North;
        RobotMotion.robotHdl.mRow = 19;
        RobotMotion.robotHdl.mColumn = 0;
        
        RobotMotion.processCommand(command);
        
        assertAll(
                
                () -> assertEquals(RobotMotion.robotHdl.mRow, 19),
                () -> assertEquals(RobotMotion.robotHdl.mColumn, 0)
        );
    }
    
    @Test
    public void invalid_case_2_not_move_forward_beyond_north_boundary() {
        
        String command = "M 5";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.North;
        RobotMotion.robotHdl.mRow = 15;
        RobotMotion.robotHdl.mColumn = 0;
        
        RobotMotion.processCommand(command);
        
        assertAll(
                // assert that robot did not move forward
                () -> assertEquals(RobotMotion.robotHdl.mRow, 15),
                () -> assertEquals(RobotMotion.robotHdl.mColumn, 0)
        );
    }

    @Test
    public void invalid_case_1_not_move_forward_when_move_forward_beyond_east_boundary() {
        
        String command = "M 5";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.East;
        RobotMotion.robotHdl.mRow = 0;
        RobotMotion.robotHdl.mColumn = 19;
        
        RobotMotion.processCommand(command);
        
        assertAll(
                // assert that robot did not move forward
                () -> assertEquals(RobotMotion.robotHdl.mRow, 0),
                () -> assertEquals(RobotMotion.robotHdl.mColumn, 19)
        );
    }
    
    @Test
    public void invalid_case_2_not_move_forward_when_move_forward_beyond_east_boundary() {
        
        String command = "M 5";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.East;
        RobotMotion.robotHdl.mRow = 0;
        RobotMotion.robotHdl.mColumn = 15;
        
        RobotMotion.processCommand(command);
        
        assertAll(
                // assert that robot did not move forward
                () -> assertEquals(RobotMotion.robotHdl.mRow, 0),
                () -> assertEquals(RobotMotion.robotHdl.mColumn, 15)
        );
    }
    
    @Test
    public void invalid_case_1_not_move_forward_when_move_forward_beyond_south_boundary() {
        
        String command = "M 5";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.South;
        RobotMotion.robotHdl.mRow = 0;
        RobotMotion.robotHdl.mColumn = 0;
        
        RobotMotion.processCommand(command);
        
        assertAll(
                // assert that robot did not move forward
                () -> assertEquals(RobotMotion.robotHdl.mRow, 0),
                () -> assertEquals(RobotMotion.robotHdl.mColumn, 0)
        );
    }
    
    @Test
    public void invalid_case_2_not_move_forward_when_move_forward_beyond_south_boundary() {
        
        String command = "M 5";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.South;
        RobotMotion.robotHdl.mRow = 3;
        RobotMotion.robotHdl.mColumn = 0;
        
        RobotMotion.processCommand(command);
        
        assertAll(
                // assert that robot did not move forward
                () -> assertEquals(RobotMotion.robotHdl.mRow, 3),
                () -> assertEquals(RobotMotion.robotHdl.mColumn, 0)
        );
    }

    @Test
    public void invalid_case_1_not_move_forward_when_move_forward_beyond_west_boundary() {
        
        String command = "M 5";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.West;
        RobotMotion.robotHdl.mRow = 0;
        RobotMotion.robotHdl.mColumn = 0;
        
        RobotMotion.processCommand(command);
        
        assertAll(
                // assert that robot did not move forward
                () -> assertEquals(RobotMotion.robotHdl.mRow, 0),
                () -> assertEquals(RobotMotion.robotHdl.mColumn, 0)
        );
    }
    
    @Test
    public void invalid_case_2_not_move_forward_when_move_forward_beyond_west_boundary() {
        
        String command = "M 5";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.West;
        RobotMotion.robotHdl.mRow = 0;
        RobotMotion.robotHdl.mColumn = 2;
        
        RobotMotion.processCommand(command);
        
        assertAll(
                // assert that robot did not move forward
                () -> assertEquals(RobotMotion.robotHdl.mRow, 0),
                () -> assertEquals(RobotMotion.robotHdl.mColumn, 2)
        );
    }
    
    @Test
    public void valid_set_IsTerminated_when_quit_command_is_given() {
        
        String command = "Q";
        
        RobotMotion.processCommand(command);
        
        assertTrue(RobotMotion.isTerminated);
    }

    @Test
    public void valid_reset_the_robot_and_the_floor_array_when_initialize_command_is_given() {
        
        String command = "I 5";
        RobotMotion.robotHdl.mRow = 1;
        RobotMotion.robotHdl.mColumn = 1;
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.East;
        RobotMotion.robotHdl.mFloor = new int[5][5];
        RobotMotion.robotHdl.mFloor[1][1] = 1;
        
        RobotMotion.processCommand(command);
        
        assertAll(
                // assert that robot is reset to its initial state
                () -> assertEquals(RobotMotion.robotHdl.mRow, 0),
                () -> assertEquals(RobotMotion.robotHdl.mColumn, 0),
                () -> assertEquals(RobotMotion.robotHdl.mOrientation, Robot.Orientation.North),
                // assert that floor array is reset to its initial state
                () -> assertEquals(RobotMotion.robotHdl.mFloor.length, 5),
                () -> assertEquals(RobotMotion.robotHdl.mFloor[1][1], 0)
        );
    }

     @Test
    public void valid_check_command_is_valid_when_input_command_is_called() {
        
        String command = "I 5";
        
        RobotMotion.processCommand(command);
        
        assertAll(
                
                () -> assertTrue(RobotMotion.isValidCommand)
        );
    }
    
    @Test
    public void valid_print_current_position_facing_north() {
        
        String command = "I 6";
        String expectedCurrentPosition = "Position:0,0 Pen: up Facing: North";
        
        RobotMotion.processCommand(command);
        
        assertAll(
                
                () -> assertEquals(expectedCurrentPosition, RobotMotion.robotHdl.printCurrentPosition())
        );
    }
    
    @Test
    public void valid_print_current_position_facing_east() {
        
        String command1 = "I 5";
        String command2 = "D";
        String command3 = "R";
        String expectedCurrentPosition = "Position:0,0 Pen: down Facing: East";
        
        RobotMotion.processCommand(command1);
        RobotMotion.processCommand(command2);
        RobotMotion.processCommand(command3);
        
        assertAll(
                
                () -> assertEquals(expectedCurrentPosition, RobotMotion.robotHdl.printCurrentPosition())
        );
    }
    
    @Test
    public void valid_print_current_position_facing_west() {
        
        String command1 = "I 5";
        String command2 = "D";
        String command3 = "L";
        String expectedCurrentPosition = "Position:0,0 Pen: down Facing: West";
        
        RobotMotion.processCommand(command1);
        RobotMotion.processCommand(command2);
        RobotMotion.processCommand(command3);
        
        assertAll(
                
                () -> assertEquals(expectedCurrentPosition, RobotMotion.robotHdl.printCurrentPosition())
        );
    }
    
    @Test
    public void valid_print_current_position_facing_south() {
        
        String command1 = "I 5";
        String command2 = "D";
        String command3 = "L";
        String command4 = "L";
        String expectedCurrentPosition = "Position:0,0 Pen: down Facing: South";
        
        RobotMotion.processCommand(command1);
        RobotMotion.processCommand(command2);
        RobotMotion.processCommand(command3);
        RobotMotion.processCommand(command4);
        
        assertAll(
                
                () -> assertEquals(expectedCurrentPosition, RobotMotion.robotHdl.printCurrentPosition())
        );
    }
    
    
    @Test
    public void valid_print_floor_given_input_pen_down_and_move_north() {
        
        String command = "I 2";
        RobotMotion.processCommand(command);
        command = "D";
        RobotMotion.processCommand(command);
        command = "M 1";
        RobotMotion.processCommand(command);
        String expectedFloor = "";
        expectedFloor += '*';
        expectedFloor += ' ';
        expectedFloor += '\n';
        expectedFloor += '*';
        expectedFloor += ' ';
        expectedFloor += '\n';
        assertEquals(true,expectedFloor.equals(RobotMotion.robotHdl.printFloor()));

    }
    
    @Test
    public void valid_print_floor_given_input_pen_down_and_move_east() {
        
        String command = "I 2";
        RobotMotion.processCommand(command);
        command = "D";
        RobotMotion.processCommand(command);
        command = "R";
        RobotMotion.processCommand(command);
        command = "M 1";
        RobotMotion.processCommand(command);
        String expectedFloor = "";
        expectedFloor += ' ';
        expectedFloor += ' ';
        expectedFloor += '\n';
        expectedFloor += '*';
        expectedFloor += '*';
        expectedFloor += '\n';
        assertEquals(true,expectedFloor.equals(RobotMotion.robotHdl.printFloor()));

    }
    
    @Test
    public void valid_print_floor_given_input_pen_down_and_move_west() {
        
        String command = "I 2";
        RobotMotion.processCommand(command);
        command = "R";
        RobotMotion.processCommand(command);
        command = "M 1";
        RobotMotion.processCommand(command);
        command = "D";
        RobotMotion.processCommand(command);
        command = "R";
        RobotMotion.processCommand(command);
        command = "R";
        RobotMotion.processCommand(command);
        command = "M 1";
        RobotMotion.processCommand(command);
        String expectedFloor = "";
        expectedFloor += ' ';
        expectedFloor += ' ';
        expectedFloor += '\n';
        expectedFloor += '*';
        expectedFloor += '*';
        expectedFloor += '\n';
        assertEquals(true,expectedFloor.equals(RobotMotion.robotHdl.printFloor()));

    }
    
    @Test
    public void valid_print_floor_given_input_pen_down_and_move_south() {
        
        String command = "I 2";
        RobotMotion.processCommand(command);
        command = "M 1";
        RobotMotion.processCommand(command);
        command = "R";
        RobotMotion.processCommand(command);
        command = "R";
        RobotMotion.processCommand(command);
        command = "D";
        RobotMotion.processCommand(command);
        command = "M 1";
        RobotMotion.processCommand(command);
        String expectedFloor = "";
        expectedFloor += '*';
        expectedFloor += ' ';
        expectedFloor += '\n';
        expectedFloor += '*';
        expectedFloor += ' ';
        expectedFloor += '\n';
        assertEquals(true,expectedFloor.equals(RobotMotion.robotHdl.printFloor()));

    }
    
    @Test
    public void valid_print_help_command() {
    	String command = "H";
        RobotMotion.processCommand(command);
        		  String h= "Available commands are: \n"
        		  		+ "U|u: Pen up\n"
        		  		+ "D|d: Pen down\n"
        		  		+ "R|r: Turn right\n"
        		  		+ "L|l: Turn left\n"
        		  		+ "M s|m s: Move forward s spaces (s is a non-negative integer)\n"
        		  		+ "P|p: Print the floor\n"
        		  		+ "C|c: Print the current position\n"
        		  		+ "Q|q: Quit the program\n"
        		  		+ "I n|i n: Initialize the system: The values of the array floor are zeros and the robot\n"
        		  		+ "		 is back to [0, 0], pen up and facing north. n size of the array, an integer\n"
        		  		+ "		 greater than zero.\n"
        		  		+ "Command entered: H\n"
        		  		+ "Command entered: Q\n";
        		  assertNotEquals(h,(outputstream.toString()));
        		   
    }
    
    @Test
    public void invalid_input_command() {
        
        String command = "I -2";
        
        RobotMotion.processCommand(command);
        
        assertFalse(RobotMotion.isValidCommand);
        
        command  = "I";
        RobotMotion.processCommand(command);
        
        assertFalse(RobotMotion.isValidCommand);
        
        command  = "I Q";
        RobotMotion.processCommand(command);
        
        assertFalse(RobotMotion.isValidCommand);
        
        RobotMotion.processCommand(null);
        
        assertFalse(RobotMotion.isValidCommand);
        
        command  = " ";
        RobotMotion.processCommand(command);
        
        assertFalse(RobotMotion.isValidCommand);
        
        command  = "           ";
        RobotMotion.processCommand(command);
        
        assertFalse(RobotMotion.isValidCommand);

    }
}
