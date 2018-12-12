package players;

import maps.Nodes;

public class HumanAgent extends Player{

	public HumanAgent(){	
		this.playerID = 1;		
	}
	@Override
	public void reinforce() {
		enforcementSoldiers();	
	}
	public void attack() {}
	
}
