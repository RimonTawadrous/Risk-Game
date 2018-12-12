package playingGUI;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.SVGPath;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import players.Player;
import selectionGUI.MainModesSelectionController;
import mapGUI.MapController;
import maps.Graph;
import maps.Nodes;
public class PlayingGUI extends MapController{

	private Player p1;
	private Player p2;
	private Graph map;
	private HashMap <Integer,SVGPath> countriesPath;
	long startTime;
	public void initialize(){
		readValueFromPlayer.setDisable(true);
		submitValue.setDisable(true);
		super.initialize();
		startTime=System.currentTimeMillis();
	}
	int flag=0;
	//game starting
	@FXML
	public void startGame(){
    	this.p1=super.p1;
    	this.p2=super.p2;
    	this.map=super.map;
    	this.countriesPath=super.countriesPath;
    	//delay for 5 seconds before game starting
		if(flag==0){
			long x = System.currentTimeMillis();
			while(true){
				if(System.currentTimeMillis()-x==500){
					currentAction.setText("LET'S START");
					playerTurn.setVisible(true);
					break;
				}
			}
			flag=1;
			//run game
			gameRunning();
		}
	}
	private int turn=1; //1 for player 1 , 2 for player 2
    private int additionalSoliders=0;
    private int countryID=0;
    private boolean reinfromcment=false;
    private boolean attack=false;
    private int chooseEnemysCountry=0;
    private int atackingCountry=0;
    private int attackedCountry=0;
    private int player1Countries=25;
    private int player2Countries=25;
    Player p;
    public void gameRunning(){
    		turn=1;
    		playerRound();
	    	playerReinforcment();
    }
    private void playerRound(){
    	if(turn==1){
    		p=p1;
    		playerTurn.setText("PLAYER 1 TURN");
    		playerTurn.setStyle("-fx-fill:#e41010;");
    	}
    	else{
    		p=p2;
    		playerTurn.setText("PLAYER 2 TURN");
    		playerTurn.setStyle("-fx-fill:#3312ab;");
    	}
    }
    int flag2=0;
    int countryChoosingControl=0;
	private void playerReinforcment(){
		if(flag2==0){
			p.enforcementSoldiers();
			additionalSoliders=p.additionalSoliders;
			flag2=1;
		}
		//in case of human agent
		if((turn==1 && MainModesSelectionController.agent1Type==1)||(turn==2 && MainModesSelectionController.agent2Type==1)){
			attackingResults.setVisible(true);
			attack=false;
			reinfromcment=true;
			currentAction.setText("YOU HAVE "+additionalSoliders+
								  " ADITIONAL SOLIDERS CHOOSE COUNTRY THEN TYPE NUMBER OF SOLIDERS TO ADD");
			countryChoosingControl=1;
		}
		// agent
		else {
			String action="SOLIDERS ARE ADDED TO '";
				p.reinforce();
				for(int i=0;i<p.countryEditiedByReinfrocment.size();i++){
					solidersPosition.get(p.countryEditiedByReinfrocment.get(i).getId()).setText(p.countryEditiedByReinfrocment.get(i).getSoliderNumb()+"");
					if(i!=p.countryEditiedByReinfrocment.size()-1)
						action+=p.countryEditiedByReinfrocment.get(i).getCountryName()+" , ";
					else
						action+=p.countryEditiedByReinfrocment.get(i).getCountryName()+"'";
				}
				currentAction.setText(action+" NOW TIME TO ATTACK");
				action="";
				p.countryEditiedByReinfrocment.clear();
		}
	}
	//chooseCountry method function, setOnAction of map of choosing countries
	private void chooseReinforcmentCountryOnGUI(int i){
		countriesPath.get(i).setStyle("-fx-fill:#808080;");
		readValueFromPlayer.setDisable(false);
		submitValue.setDisable(false);
		countryChoosingControl=0;
	}
	private void chooseAttackingCountry(int i){
		atackingCountry=i;
		attackingCountriesSelection(i);
		currentAction.setText("CHOOSE ENEMY'S COUNTRY TO ATTACK THEN NUMBER OF SOLIDERS TO ATTACK WITH");
		chooseEnemysCountry=1;
	}
	private void chooseEnemysAttackedCountry(int i){
		attackedCountry=i;
		readValueFromPlayer.setDisable(false);
		submitValue.setDisable(false);
		chooseEnemysCountry=0;
		countryChoosingControl=0;
	}
	//submitButtonAction country soliders reinforcment function
	private void choosedCountryReinforcment(){
		if(Integer.parseInt(readValueFromPlayer.getText())<=additionalSoliders){
			map.getNodeDetails(countryID).setSoliderNumb(map.getNodeDetails(countryID).getSoliderNumb()+
					Integer.parseInt(readValueFromPlayer.getText()));
			solidersPosition.get(countryID).setText(map.getNodeDetails(countryID).getSoliderNumb()+"");
			additionalSoliders-=Integer.parseInt(readValueFromPlayer.getText());
			readValueFromPlayer.clear();
		}
		else{
		}
		readValueFromPlayer.setDisable(true);
		submitValue.setDisable(true);
		if(turn==1)
			countriesPath.get(countryID).setStyle("-fx-fill:#ff6666;");
		else
			countriesPath.get(countryID).setStyle("-fx-fill:#9999ff;");
		readValueFromPlayer.clear();
	}
	private void playerAttckInitialization(){
		attack=true;
		reinfromcment=false;
    	if(additionalSoliders==0){
    		currentAction.setText("DONE,NOW TIME TO ATTACK,CHOOSE COUNTRY TO ATTACK FROM AND COUNTRY TO ATTACK");
    		countryChoosingControl=1;
    	}
    	
	}
	private void attacking(){
		Dice.attckerResult.clear();
		Dice.defenderResult.clear();
		p.attackWithDice(map.getNodeDetails(atackingCountry), 
				map.getNodeDetails(attackedCountry), Integer.parseInt(readValueFromPlayer.getText()));	
		try {
			new Dice().run();
		} catch (IOException e) {
		}
		//return each country to its original color
		for(int i=0;i<map.GetNodeNeighbors(atackingCountry).size();i++){
			if(map.GetNodeNeighbors(atackingCountry).get(i).getPlayer1OR2()!=turn)
				if(turn ==1)
					countriesPath.get(map.GetNodeNeighbors(atackingCountry).get(i).getId()).setStyle("-fx-fill:#9999ff;");
				else
					countriesPath.get(map.GetNodeNeighbors(atackingCountry).get(i).getId()).setStyle("-fx-fill:#ff6666;");
		}
		//attacker won
		if(map.getNodeDetails(attackedCountry).getPlayer1OR2()==turn){
			attackingResults.setVisible(true);
			attackingResults.setText("ATTACKER WINS");
			if(turn==1){
				attackingResults.setStyle("-fx-fill:red;");
				player1Countries++;
				player2Countries--;
				p.setCountriesOwned(player1Countries);
				countriesPath.get(attackedCountry).setStyle("-fx-fill:#ff6666;");
			}
			else{
				attackingResults.setStyle("-fx-fill:blue;");
				player2Countries++;
				player1Countries--;
				p.setCountriesOwned(player2Countries);
				countriesPath.get(attackedCountry).setStyle("-fx-fill:#9999ff;");
			}

		}
		else{
			attackingResults.setVisible(true);
			attackingResults.setText("ATTACKING FAILED");
			if(turn==1)
				attackingResults.setStyle("-fx-fill:red;");
			else
				attackingResults.setStyle("-fx-fill:blue;");
				
		}
		if(player1Countries==countries.size()-1){
			currentAction.setText("PLAYER 1 WON!");
			readValueFromPlayer.clear();
			readValueFromPlayer.setDisable(true);
			submitValue.setDisable(true);
			numOfOwnedCountriesForPlayer1.setText(map.V()-1+"");
		}
		else if(player2Countries==countries.size()-1){
			currentAction.setText("PLAYER 2 WON!");
			readValueFromPlayer.clear();
			readValueFromPlayer.setDisable(true);
			submitValue.setDisable(true);
			numOfOwnedCountriesForPlayer1.setText(map.V()-1+"");
		}
		else{
			solidersPosition.get(attackedCountry).setText(""+map.getNodeDetails(attackedCountry).getSoliderNumb());
			solidersPosition.get(atackingCountry).setText(""+map.getNodeDetails(atackingCountry).getSoliderNumb());	
			readValueFromPlayer.clear();
			readValueFromPlayer.setDisable(true);
			submitValue.setDisable(true);
			if(turn ==1)
				turn=2;
			else
				turn =1;
			numOfOwnedCountriesForPlayer1.setText(player1Countries+"");
			numOfOwnedCountriesForPlayer2.setText(player2Countries+"");
			playerRound();
			flag2=0;
			playerReinforcment();
		}
	}
	@FXML
	public void chooseCountry(MouseEvent e){
		if(countryChoosingControl==1){
			int x = (int) e.getX();
			int y=(int) e.getY();
			for(int i=1;i<countriesPath.size();i++){
				if(MainModesSelectionController.country==2){
					if(countriesPath.get(i).contains(x, y)){				
						if(reinfromcment==true&&map.getNodeDetails(i).getPlayer1OR2()==turn)
							 chooseReinforcmentCountryOnGUI(i);
						else if(attack==true&&chooseEnemysCountry==0&&map.getNodeDetails(i).getPlayer1OR2()==turn)
							chooseAttackingCountry(i);
						else if(attack==true&&chooseEnemysCountry==1&&map.getNodeDetails(i).getPlayer1OR2()!=turn){
							currentAction.setText("YOU ARE ATTACKING '"+map.getNodeDetails(i).getCountryName()+"' ENTER NUMBER OF SOLIDERS");
							chooseEnemysAttackedCountry(i);
						}
						countryID=i;
					}
				}
				else{
					if(countriesPath.get(i).contains(x-170, y+250)){
						if(reinfromcment==true&&map.getNodeDetails(i).getPlayer1OR2()==turn)
							 chooseReinforcmentCountryOnGUI(i);
						else if(attack==true&&chooseEnemysCountry==0&&map.getNodeDetails(i).getPlayer1OR2()==turn)
							chooseAttackingCountry(i);
						else if(attack==true&&chooseEnemysCountry==1&&map.getNodeDetails(i).getPlayer1OR2()!=turn){
							currentAction.setText("YOU ARE ATTACKING '"+map.getNodeDetails(i).getCountryName()+"' ENTER NUMBER OF SOLIDERS");
							chooseEnemysAttackedCountry(i);
						}
						countryID=i;
					}
				}
			}
		}
	}
	@FXML
	public void submitButtonAction(){
			if(readValueFromPlayer.getText()!=""){
				if((turn==1 && MainModesSelectionController.agent1Type==1)||
									(turn==2 && MainModesSelectionController.agent2Type==1)){
					//added soliders to certain country
					if(reinfromcment==true){
						choosedCountryReinforcment();
						if(additionalSoliders>0)
							playerReinforcment();
						else{
							playerAttckInitialization();
							return;
						}
					}
					//attack country
				else if(attack==true&&attackedCountry!=0){
						attackingResults.setVisible(false);
					if(map.getNodeDetails(atackingCountry).getSoliderNumb()-Integer.parseInt(readValueFromPlayer.getText())>=1)
						attacking();
					else{
						readValueFromPlayer.clear();
						currentAction.setText("UNAVILABLE NUMBER OF ATTACKING SOLIDERS TO '"+map.getNodeDetails(attackedCountry).getCountryName()+"' TRY AGAIN");
						}
					}
				}
			}
	}
	

	  	private void attackNonAI(Nodes owned,Nodes adjecent,int soliders){
				try {
					new Dice().run();
				} catch (IOException e) {
				}
				currentAction.setText("YOU ARE ATTACKING FROM '"+owned.getCountryName()+"' TO"
						+ " '"+adjecent.getCountryName()+"' WITH "+soliders);
				checkAttackWinner(owned, adjecent);
	  		
	}
	  	int attackingCountriesCounter=0;
	  	private void checkAttackWinner(Nodes owned,Nodes adjecent){
			if(map.getNodeDetails(adjecent.getId()).getPlayer1OR2()==turn){
				attackingResults.setVisible(true);
				attackingResults.setText("ATTACKER WINS");
				if(turn==1){
					attackingResults.setStyle("-fx-fill:red;");
					player1Countries++;
					player2Countries--;
					p.setCountriesOwned(player1Countries);
					countriesPath.get(adjecent.getId()).setStyle("-fx-fill:#ff6666;");
				}
				else{
					attackingResults.setStyle("-fx-fill:blue;");
					player2Countries++;
					player1Countries--;
					p.setCountriesOwned(player2Countries);
					countriesPath.get(adjecent.getId()).setStyle("-fx-fill:#9999ff;");
				}
			}
			else{
				attackingResults.setVisible(true);
				attackingResults.setText("ATTACKING FAILED");
				if(turn==1)
					attackingResults.setStyle("-fx-fill:red;");
				else
					attackingResults.setStyle("-fx-fill:blue;");
					
			}
	  	}
	int check2=0;
	@FXML
	public void nextAction(){
		if(turn!=-1){
			if(System.currentTimeMillis()-startTime>120000){
				secondsLeft.setText("SECONDS LEFT: "+0);
				if(player1Countries>player2Countries){
					attackingResults.setText("PLAYER 1 WON!");
					currentAction.setText("GAME TIME ENDED , PLAYER 1 WON!");
					return;
				}
				else if(player1Countries<player2Countries){
					attackingResults.setText("PLAYER 2 WON!");
					currentAction.setText("GAME TIME ENDED , PLAYER 2 WON!");
					return;
				}
				else{}
					
			}
			secondsLeft.setText("");
			secondsLeft.setText("SECONDS LEFT: "+(120000-((System.currentTimeMillis()-startTime)))/1000);
			//not human agent
			if((turn==1 && MainModesSelectionController.agent1Type!=1)||
										(turn==2 && MainModesSelectionController.agent2Type!=1)){
				//passive agents don't attack
				if((turn==1 && MainModesSelectionController.agent1Type==2)||
										(turn==2 && MainModesSelectionController.agent2Type==2)){
					currentAction.setText("PASSIVE AGENTS DON'T ATTACK");	
					check2=2;
				}
				else if (check2==0){
					
					if((turn==1 && MainModesSelectionController.agent1Type<5)||
										(turn==2 && MainModesSelectionController.agent2Type<5)){
						p.attack();
						attackNonAI(p.ownedTerritoryToAttackGUI,p.adjacentTerritoryToAttackGUI,p.attackingSolidersGUI);
					}
					else{
						p.attack();
						attackNonAI(p.ownedTerritoryToAttackGUI,p.adjacentTerritoryToAttackGUI,p.attackingSolidersGUI);
					}
						numOfOwnedCountriesForPlayer1.setText(player1Countries+"");
						numOfOwnedCountriesForPlayer2.setText(player2Countries+"");
						if(player1Countries==countries.size()-1){
							attackingResults.setText("PLAYER 1 WON!");
							currentAction.setText("GAME ENDED , PLAYER 1 WON!");
							numOfOwnedCountriesForPlayer1.setText("50");
							turn=-1;
							return;
						}
						else if(player2Countries==countries.size()-1){
							attackingResults.setText("PLAYER 2 WON!");
							currentAction.setText("GAME ENDED , PLAYER 2 WON!");
							numOfOwnedCountriesForPlayer2.setText("50");
							turn=-1;
							return;
						}
						
					check2=1;
				}
				else{
					check2=0;
					if(turn ==1)
						turn=2;
					else
						turn =1;
					playerRound();
					flag2=0;
					playerReinforcment();
				}
				 if(check2==2){
					check2=0;
					if(turn ==1)
						turn=2;
					else
						turn =1;
					playerRound();
					flag2=0;
					playerReinforcment();
				}
			}
		}
	}
	private void attackingCountriesSelection(int pressedCountryCode){	
		if(attack==true){
			ArrayList<Nodes> neighbors =map.GetNodeNeighbors(pressedCountryCode);
			for(int i=0;i<neighbors.size();i++){
				if(neighbors.get(i).getPlayer1OR2()!=map.getNodeDetails(pressedCountryCode).getPlayer1OR2())
					countriesPath.get(neighbors.get(i).getId()).setStyle("-fx-fill:#696969;");
			}
		}
	}
}
