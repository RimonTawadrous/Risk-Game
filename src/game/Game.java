package game;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import maps.*;
import players.*;

public class Game {

	
	//choose map (Egypt vs USA)
	//selection from GUI
	public static Graph map;
	
	
	
	
	
	//select 2 players (Modes)
	//Selection from GUI
//	public Player p1 = new AgressiveAgent();
//	public Player p2 = new AgressiveAgent();
	
	
	
	///////////////////////////////////////////start Game/////////////////////////////////////////////////////////////////////////
	
	//1.Initialize the board
	public void InitializeBoard(Player p1,Player p2){
		
		//put 1 soldier randomly in each country for each player
				int min = 1;
				int max = map.V()-1;
				
				ArrayList<Nodes> countriesToInitialize = new ArrayList<Nodes>();
				for(Nodes e : map.GraphNodes())
					countriesToInitialize.add(e);
				countriesToInitialize.remove(0);
				
//				int filledCountries = 0;
				Nodes n ;
				int t;
				
//				while(filledCountries != max){
				while(!countriesToInitialize.isEmpty()){
					
					 
					t = ThreadLocalRandom.current().nextInt(0,countriesToInitialize.size());
					n = map.getNodeDetails(countriesToInitialize.get(t).getId());
					countriesToInitialize.remove(t);
					
					
					///player 1
								
//					while (n.getPlayerNo() !=0){
//						
//						n = map.getNodesDetails(ThreadLocalRandom.current().nextInt(min,max+1));
//						
//					};
					
					
					n.IncrementSoliders();
					n.setPlayerNo(p1.getPlayerID());
					n.setPlayer1OR2(1);
					p1.decrementSoliders();
					p1.addCountry(n);
					p1.incrementCountriesOwned();
					
//					filledCountries ++;
					
//					if(filledCountries == max)	break;
					if(countriesToInitialize.isEmpty())	break;
					
					
					///player 2
					
					t = ThreadLocalRandom.current().nextInt(0,countriesToInitialize.size());
					n = map.getNodeDetails(countriesToInitialize.get(t).getId());
					countriesToInitialize.remove(t);
				
					
//					while (n.getPlayerNo() != 0){
//						
//						n = map.getNodesDetails(ThreadLocalRandom.current().nextInt(min,max+1));
//
//					};
					
					
					n.IncrementSoliders();
					n.setPlayerNo(p2.getPlayerID());
					n.setPlayer1OR2(2);
					p2.decrementSoliders();
					p2.addCountry(n);
					p2.incrementCountriesOwned();
					
//					filledCountries ++;
					
					//if(filledCountries == max) break;
					
				}
				

			
				//continue filling all remaining soldier for the 2 players (35) 
				while(p1.getSoliders() !=0 && p2.getSoliders()!=0){
					
					
					if(p1.getSoliders()>0){
						
						///player 1
					
						n = map.getNodeDetails(ThreadLocalRandom.current().nextInt(min,max+1));
					
						while (n.getPlayerNo() != p1.getPlayerID()){
							
							n = map.getNodeDetails(ThreadLocalRandom.current().nextInt(min,max+1));
							
						};
						
						
						
						n.IncrementSoliders();
						p1.decrementSoliders();
							
						
					}
					
					if(p2.getSoliders()>0){
						
						///player 2
						
						n = map.getNodeDetails(ThreadLocalRandom.current().nextInt(min,max+1));
						
						while (n.getPlayerNo() != p2.getPlayerID()){
							
							n = map.getNodeDetails(ThreadLocalRandom.current().nextInt(min,max+1));
							
						};
						
							n.IncrementSoliders();
							p2.decrementSoliders();
						
					}	
					
				
				}
		
	}
 
	
	public static Graph getMap(){
		return map;
	}
		
	public void printBoard(){
		
		System.out.println("ID\tName\tplayer\tsoldiers");
		for(Nodes n: map.GraphNodes()){
			
			System.out.println(n.getId()+"\t"+n.getCountryName()+"\t"+n.getPlayerNo()+"\t"+n.getSoliderNumb());
						
		}
		
	}
	
	
	
		
	
	
	
	//2. give turn to p1  / p2 simultaneously until  1 wins
	
	
	
	
	
}
