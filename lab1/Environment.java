package lab1;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

public class Environment {
	public static final Action MOVE_LEFT = new DynamicAction("LEFT");
	public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
	public static final Action MOVE_UP = new DynamicAction("UP");
	public static final Action MOVE_DOWN = new DynamicAction("DOWN");
	public static final Action SUCK_DIRT = new DynamicAction("SUCK");
	public static final String LOCATION_A = "A";
	public static final String LOCATION_B = "B";
	public static final String LOCATION_C = "C";
	public static final String LOCATION_D = "D";

	public enum LocationState {
		CLEAN, DIRTY
	}

	private EnvironmentState envState;
	private boolean isDone = false; // all squares are CLEAN
	private Agent agent = null;
	public int point=0;

	public Environment(LocationState locAState, LocationState locBState, LocationState locCState,
			LocationState locDState) {
		envState = new EnvironmentState(locAState, locBState, locCState, locDState);
	}

	// add an agent into the enviroment
	public void addAgent(Agent agent, String location) {
		// TODO
		this.agent = agent;
		envState.setAgentLocation(location);
	}

	public EnvironmentState getCurrentState() {

		return this.envState;
	}

	// Update enviroment state when agent do an action
	public EnvironmentState executeAction(Action action) {
		// TODO
		String agentLocation = envState.getAgentLocation();
		if (action.equals(SUCK_DIRT)) {
			envState.setLocationState(envState.getAgentLocation(), LocationState.CLEAN);
			point+=500;
			//A
		}else if(agentLocation==LOCATION_A&&(action.equals(MOVE_LEFT)||action.equals(MOVE_UP))) {
			envState.setAgentLocation(LOCATION_A);
			point-=100;
		}else if(agentLocation==LOCATION_A&&action.equals(MOVE_RIGHT)) {
			envState.setAgentLocation(LOCATION_B);
			point-=10;
		}else if(agentLocation==LOCATION_A&&action.equals(MOVE_DOWN)) {
			envState.setAgentLocation(LOCATION_D);
			point-=10;
			
		}//B
		else if(agentLocation==LOCATION_B&&(action.equals(MOVE_RIGHT)||action.equals(MOVE_UP))) {
			envState.setAgentLocation(LOCATION_B);
			point-=100;
		}else if(agentLocation==LOCATION_B&&action.equals(MOVE_LEFT)) {
			envState.setAgentLocation(LOCATION_A);
			point-=10;
		}else if(agentLocation==LOCATION_B&&action.equals(MOVE_DOWN)) {
			envState.setAgentLocation(LOCATION_C);
			point-=10;
		}//C
		else if(agentLocation==LOCATION_C&&(action.equals(MOVE_RIGHT)||action.equals(MOVE_DOWN))) {
			envState.setAgentLocation(LOCATION_C);
			point-=100;
		}else if(agentLocation==LOCATION_C&&action.equals(MOVE_UP)) {
			envState.setAgentLocation(LOCATION_B);
			point-=10;
		}else if(agentLocation==LOCATION_C&&action.equals(MOVE_LEFT)) {
			envState.setAgentLocation(LOCATION_D);
			point-=10;
		}
		//D
		else if(agentLocation==LOCATION_D&&(action.equals(MOVE_LEFT)||action.equals(MOVE_DOWN))) {
			envState.setAgentLocation(LOCATION_D);
			point-=100;
		}else if(agentLocation==LOCATION_D&&action.equals(MOVE_UP)) {
			envState.setAgentLocation(LOCATION_A);
			point-=10;
		}else if(agentLocation==LOCATION_D&&action.equals(MOVE_RIGHT)) {
			envState.setAgentLocation(LOCATION_C);
			point-=10;
		}
		return envState;
	}

	// get percept<AgentLocation, LocationState> at the current location where agent
	// is in.
	public Percept getPerceptSeenBy() {
		String agentLocation = envState.getAgentLocation();
		LocationState locationState = envState.getLocationState(agentLocation);

		return new Percept(agentLocation, locationState);
	}

	public void step() {
		envState.display();
		String agentLocation = this.envState.getAgentLocation();
		Action anAction = agent.execute(getPerceptSeenBy());
		EnvironmentState es = executeAction(anAction);

		System.out.println("Agent Loc.: " + agentLocation + "\tAction: " + anAction);
		System.out.println("point: "+point);
		if ((es.getLocationState(LOCATION_A) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_B) == LocationState.CLEAN))
			isDone = true;// if both squares are clean, then agent do not need to do any action
		es.display();
	}

	public void step(int n) {
		for (int i = 0; i < n; i++) {
			step();
			System.out.println("-------------------------");
		}
	}

	public void stepUntilDone() {
		int i = 0;

		while (!isDone) {
			System.out.println("step: " + i++);
			step();
		}
	}
}
