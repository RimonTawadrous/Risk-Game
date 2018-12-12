package players;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

import playingGUI.Dice;
import mapGUI.MapController;
import maps.*;

public abstract class Player {
	public Nodes ownedTerritoryToAttackGUI = null;
	public Nodes adjacentTerritoryToAttackGUI = null;
	public ArrayList <Nodes> countryEditiedByReinfrocment = new ArrayList <Nodes>();
	public int attackingSolidersGUI;
	//AI agent
	public ArrayList <Nodes> attackFrom = new ArrayList<Nodes>();
	public ArrayList <Nodes> attackTo = new ArrayList<Nodes>();
	public ArrayList <Integer> solidersAttacking = new ArrayList<Integer>();
	protected static Graph map;
	public Graph getMap() {
		return map;
	}
	private int countriesOwned;
	
	public int getCountriesOwned() {
		return countriesOwned;
	}


	public void setCountriesOwned(int countriesOwned) {
		this.countriesOwned = countriesOwned;
	}
	protected ArrayList<Nodes> countries = new ArrayList<Nodes>();

	private int startingSoliders = 45;
	public int additionalSoliders = 0;

	protected int playerID;


	public int getPlayerID(){
		return playerID;
	}


	public void setMap(Graph m){
		map = m;
	}


	public void attackWithDice(Nodes attackSource,Nodes attackDestination,int soldNumb){
			Dice.attckerResult.clear();
			Dice.defenderResult.clear();
		System.out.println("number of soldier to attack with"+soldNumb);
		attackSource.setSoliderNumb(attackSource.getSoliderNumb()- soldNumb);
		int nmbSoldiersToAttackWith = soldNumb;

		int curentSoldiersToAttackWith = 0;
		int curentSoldiersToDefendWith = 0;
		Integer []  attckerResult;
		Integer [] defenderResult;


		while(nmbSoldiersToAttackWith!= 0 && attackDestination.getSoliderNumb() !=0){

			//Attack soldiers
			if(nmbSoldiersToAttackWith >= 3){
				curentSoldiersToAttackWith = 3;
			}else{
				curentSoldiersToAttackWith = nmbSoldiersToAttackWith;
			}

			//defend soldiers
			if(attackDestination.getSoliderNumb() >= 2){
				curentSoldiersToDefendWith = 2;
			}else{
				curentSoldiersToDefendWith = 1;
			}

			//roll dice
			attckerResult = rollDice(curentSoldiersToAttackWith);
			defenderResult = rollDice(curentSoldiersToDefendWith);
			Dice.init(attckerResult, defenderResult);
			//print dice values
			System.out.println("Turn");
			System.out.println("Attacker Dice: ");
			for (Integer e :attckerResult){
				System.out.println(e);
			}
			System.out.println("Defender Dice: ");
			for (Integer e :defenderResult){
				System.out.println(e);
			}

			int min=0 ;
			if(defenderResult.length <= attckerResult.length ){
				min = defenderResult.length ;
			}
			else if(attckerResult.length < defenderResult.length ){
				min = attckerResult.length;
			}
			int flag = 0;
			for(int i=0; i< min ; i++){

				if(attckerResult[i] == defenderResult[i] ){
					nmbSoldiersToAttackWith--;
					if(i==0){
						flag = 1;
						break;
					}
				}
				else if(attckerResult[i] < defenderResult[i]){
					nmbSoldiersToAttackWith--;
				}
				else if(attckerResult[i] > defenderResult[i]){
					attackDestination.setSoliderNumb(attackDestination.getSoliderNumb() -1);
				}
			}
			if(flag == 1){
				break;
			}
		}
		if(attackDestination.getSoliderNumb()==0){
			//ksb el balad
			attackDestination.setSoliderNumb(nmbSoldiersToAttackWith);
			attackDestination.setPlayerNo(attackSource.getPlayerNo());
			int n = attackDestination.getPlayer1OR2();
			if(n==1)
				attackDestination.setPlayer1OR2(2);
			else
				attackDestination.setPlayer1OR2(1);
			this.countries.add(attackDestination);
		}
		else if(nmbSoldiersToAttackWith==0){
			//5ssr el balad
		}
		else if(attackDestination.getSoliderNumb()!=0 && nmbSoldiersToAttackWith!=0 ){
			// ta3adol fl awel
			attackSource.setSoliderNumb(attackSource.getSoliderNumb()+ nmbSoldiersToAttackWith);
		}
	}


	public void attackWithDice(int attackSourceID,int attackDestinationID,int soldNumb){
		Dice.attckerResult.clear();
		Dice.defenderResult.clear();
		System.out.println("number of soldier to attack with"+soldNumb);
		map.getNodeDetails(attackSourceID).setSoliderNumb(map.getNodeDetails(attackSourceID).getSoliderNumb()- soldNumb);
		int nmbSoldiersToAttackWith = soldNumb;

		int curentSoldiersToAttackWith = 0;
		int curentSoldiersToDefendWith = 0;
		Integer []  attckerResult;
		Integer [] defenderResult;


		while(nmbSoldiersToAttackWith!= 0 && map.getNodeDetails(attackDestinationID).getSoliderNumb() !=0){

			//Attack soldiers
			if(nmbSoldiersToAttackWith >= 3){
				curentSoldiersToAttackWith = 3;
			}else{
				curentSoldiersToAttackWith = nmbSoldiersToAttackWith;
			}

			//defend soldiers
			if( map.getNodeDetails(attackDestinationID).getSoliderNumb() >= 2){
				curentSoldiersToDefendWith = 2;
			}else{
				curentSoldiersToDefendWith = 1;
			}

			//roll dice
			attckerResult = rollDice(curentSoldiersToAttackWith);
			defenderResult = rollDice(curentSoldiersToDefendWith);
			Dice.init(attckerResult, defenderResult);
			//print dice values
			System.out.println("Turn");
			System.out.println("Attacker Dice: ");
			for (Integer e :attckerResult){
				System.out.println(e);
			}
			System.out.println("Defender Dice: ");
			for (Integer e :defenderResult){
				System.out.println(e);
			}

			int min=0 ;
			if(defenderResult.length <= attckerResult.length ){
				min = defenderResult.length ;
			}
			else if(attckerResult.length < defenderResult.length ){
				min = attckerResult.length;
			}
			int flag = 0;
			for(int i=0; i< min ; i++){

				if(attckerResult[i] == defenderResult[i] ){
					nmbSoldiersToAttackWith--;
					if(i==0){
						flag = 1;
						break;
					}
				}
				else if(attckerResult[i] < defenderResult[i]){
					nmbSoldiersToAttackWith--;
				}
				else if(attckerResult[i] > defenderResult[i]){
                    map.getNodeDetails(attackDestinationID).setSoliderNumb( map.getNodeDetails(attackDestinationID).getSoliderNumb() -1);
				}
			}
			if(flag == 1){
				break;
			}
		}
		if( map.getNodeDetails(attackDestinationID).getSoliderNumb()==0){
			//ksb el balad
            map.getNodeDetails(attackDestinationID).setSoliderNumb(nmbSoldiersToAttackWith);
            map.getNodeDetails(attackDestinationID).setPlayerNo(map.getNodeDetails(attackSourceID).getPlayerNo());
			int n =  map.getNodeDetails(attackDestinationID).getPlayer1OR2();
			if(n==1)
                map.getNodeDetails(attackDestinationID).setPlayer1OR2(2);
			else
                map.getNodeDetails(attackDestinationID).setPlayer1OR2(1);
			this.countries.add( map.getNodeDetails(attackDestinationID));
		}
		else if(nmbSoldiersToAttackWith==0){
			//5ssr el balad
		}
		else if( map.getNodeDetails(attackDestinationID).getSoliderNumb()!=0 && nmbSoldiersToAttackWith!=0 ){
			// ta3adol fl awel
            map.getNodeDetails(attackSourceID).setSoliderNumb(map.getNodeDetails(attackSourceID).getSoliderNumb()+ nmbSoldiersToAttackWith);
		}
	}


	private Integer [] rollDice(int count){

		Integer [] temp = new Integer [count];

		for(int i=0; i<count; i++){

			temp[i] = ThreadLocalRandom.current().nextInt(1,6+1);

		}

		Arrays.sort(temp,Collections.reverseOrder());

		return temp;

	}

	public void enforcementSoldiers(){
		int t = countriesOwned /3;
		if(t>0) additionalSoliders = t;
		else 	additionalSoliders = 3;

	}

	public abstract void reinforce();

	public abstract void attack();

	public void playTurn(){

		reinforce();
		attack();

	}

	public void decrementSoliders(){
		startingSoliders --;
	}

	public int getSoliders(){
		return startingSoliders;
	}

	public void incrementCountriesOwned(){
		countriesOwned ++;
	}

	public void addCountry(Nodes n){
		countries.add(n);
	}
}
