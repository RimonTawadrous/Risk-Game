package players;

import game.Game;
import maps.Nodes;

public class PacifistAgent extends Player {
	
	public PacifistAgent(){
		
		this.playerID = 4;
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
	}
	@Override
	public void attack() {
		
		//get minimum adjacent territory to attack an from where to attack it 
		int min = 9999;
		
		for(Nodes owned : countries){
			
			for( Nodes adjacent : Game.getMap().GetNodeNeighbors(owned.getId()) ){
				
				if(adjacent.getPlayerNo()!= this.getPlayerID()  && min>adjacent.getSoliderNumb() && owned.getSoliderNumb()>1){
					
					min = adjacent.getSoliderNumb();
					ownedTerritoryToAttackGUI = owned;
					adjacentTerritoryToAttackGUI = adjacent;
					
				}
					
			}
			
		}
		
		System.out.println("Country to attack from: "+ownedTerritoryToAttackGUI.getCountryName());
		System.out.println("Adjacent min territory to attack: "+adjacentTerritoryToAttackGUI.getCountryName());
		
		//begin attack 
		attackingSolidersGUI=ownedTerritoryToAttackGUI.getSoliderNumb()-1;
			attackWithDice(ownedTerritoryToAttackGUI,adjacentTerritoryToAttackGUI,ownedTerritoryToAttackGUI.getSoliderNumb()-1);
	}
	
}
