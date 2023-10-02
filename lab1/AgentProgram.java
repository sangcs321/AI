package lab1;

import java.util.Random;

import lab1.Environment.LocationState;

public class AgentProgram {

	public Action execute(Percept p) {// location, status
		//TODO
		//0: move left
		//1: move right
		//2: move up
		//3: move down
		Random r= new Random();
		int rNum=r.nextInt(4);
		
		
		
		if(p.getLocationState()==LocationState.DIRTY) {
			return Environment.SUCK_DIRT;
		}else if(rNum==0) {
			return Environment.MOVE_LEFT;
		}else if(rNum==1) {
			return Environment.MOVE_RIGHT;
		}else if(rNum==2) {
			return Environment.MOVE_UP;
		}else if(rNum==3) {
			return Environment.MOVE_DOWN;
		}
		return NoOpAction.NO_OP;
		
	}

}