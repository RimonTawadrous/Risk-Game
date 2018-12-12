package players;

import maps.*;

public class PassiveAgent extends Player {

	public PassiveAgent(){
		
		this.playerID = 2;
	}
	
	@Override
	public void reinforce() {

		enforcementSoldiers();
		Nodes minNode = null;
		int min = 99999;
		for(Nodes e : countries){
			if(min>e.getSoliderNumb()){
				min =  e.getSoliderNumb();
				minNode=e;
			}
		}
		countryEditiedByReinfrocment.add(minNode);
		countryEditiedByReinfrocment.get(0).setSoliderNumb(countryEditiedByReinfrocment.get(0).getSoliderNumb()+ additionalSoliders);
		System.out.println(countryEditiedByReinfrocment.size()+" aaa");
	}

	@Override
	public void attack() {
		
		//Passive Agents do not attack
		return;
	}

}
