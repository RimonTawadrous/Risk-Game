package players;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import game.Game;
import maps.Nodes;

public class AgressiveAgent extends Player{

	public AgressiveAgent(){
		
		this.playerID = 3;
	}
	
	@Override
	public void reinforce() {

		enforcementSoldiers();
		Nodes maxNode = null;
		int max = -1;
		for(Nodes e : countries){
			if(max<e.getSoliderNumb()){
				max =  e.getSoliderNumb();
				maxNode=e;
			}
		}
		countryEditiedByReinfrocment.add(maxNode);
		countryEditiedByReinfrocment.get(0).setSoliderNumb(countryEditiedByReinfrocment.get(0).getSoliderNumb()+ additionalSoliders);
		
	}
	
	private boolean existIn(ArrayList<Nodes> t, Nodes n){
		
		for (Nodes e : t)
			if(e.getId() == n.getId())	return true;
		
		return false;
	}
	
	@Override
	public void attack() {
		
		//find 3 most soldiered territories that owner can attack 
		
		ArrayList<Nodes> oponentAdjacentTeritories = new ArrayList<Nodes>();
		
		for(Nodes owned : countries){
			for( Nodes adjacent : Game.getMap().GetNodeNeighbors(owned.getId()) ){
				
				if(adjacent.getPlayerNo() != owned.getPlayerNo() && !existIn(oponentAdjacentTeritories,adjacent))
					oponentAdjacentTeritories.add(adjacent);
					
			}
		}
		
		
		//all countries to be attacked are sorted in ascending way 
		Collections.sort(oponentAdjacentTeritories,Nodes.SoldierNumbComparator);
		ArrayList<Nodes> temp = null;
		int count = 0;
		for(int i=oponentAdjacentTeritories.size()-1; i>=0 ;i--){
			
			//select all Nodes you can attack from this Nodes
			temp = getAttackerAdj(oponentAdjacentTeritories.get(i));
			System.out.println("Adjacent owned Nodess to attack "+oponentAdjacentTeritories.get(i).getId()+" " +oponentAdjacentTeritories.get(i).getCountryName());
			for(Nodes e : temp)
				System.out.println(e.getId()+" "+e.getCountryName()+" "+e.getSoliderNumb()+"soldiers");
			
			//find the greatest soldiered Nodes to attack from 
			if(temp.get(temp.size()-1).getSoliderNumb()>1){
				
				System.out.println("Max Nodes to attack: "+oponentAdjacentTeritories.get(i).getId()+" "+oponentAdjacentTeritories.get(i).getCountryName());
				
				count ++;
				ownedTerritoryToAttackGUI=temp.get(temp.size()-1);
				adjacentTerritoryToAttackGUI=oponentAdjacentTeritories.get(i);
				attackingSolidersGUI=temp.get(temp.size()-1).getSoliderNumb()-1;
				attackWithDice(temp.get(temp.size()-1), oponentAdjacentTeritories.get(i), temp.get(temp.size()-1).getSoliderNumb()-1);
				
				
				System.out.println("Attack from "+temp.get(temp.size()-1).getId()+" "+temp.get(temp.size()-1).getCountryName()+" to "+
								oponentAdjacentTeritories.get(i).getId()+" "+oponentAdjacentTeritories.get(i).getCountryName()+" with "
								+(temp.get(temp.size()-1).getSoliderNumb()-1)+"soldiers done!");
				
			}
					
			if(count == 3) break;
			
		} 
		
		
		
	}
	
	
	private ArrayList<Nodes> getAttackerAdj(Nodes n){
		
		ArrayList<Nodes> t = new ArrayList<Nodes>();
		
		for(Nodes e : map.GetNodeNeighbors(n.getId())){
			
			if(e.getPlayerNo()!= n.getPlayerNo())
				t.add(e);
				
		}
		Collections.sort(t,Nodes.SoldierNumbComparator);
		
		return t;
	
	}

}
