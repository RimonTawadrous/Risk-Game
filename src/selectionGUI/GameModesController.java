package selectionGUI;
import game.Game;
import javafx.scene.layout.Pane;

import java.io.IOException;

import maps.Board;
import playingGUI.Dice;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.*;
public class GameModesController extends MainModesSelectionController{
	@FXML
	public ImageView egyptMap;	
	@FXML
	public ImageView usaMap;
	@FXML
	public RadioButton player1HumanAgent;
	@FXML
	public RadioButton player1PassiveAgent;
	@FXML
	public RadioButton player1AggresiveAgent;
	@FXML
	public RadioButton player1PastfistAgent;
	@FXML
	public RadioButton player1GreedyAgent;
	@FXML
	public RadioButton player1AStarAgent;
	@FXML
	public RadioButton player1RealAStarAgent;
	@FXML
	public RadioButton player1MinimaxAgent;
	@FXML
	public RadioButton player2HumanAgent;
	@FXML
	public RadioButton player2PassiveAgent;
	@FXML
	public RadioButton player2AggresiveAgent;
	@FXML
	public RadioButton player2PastfistAgent;
	@FXML
	public RadioButton player2GreedyAgent;
	@FXML
	public RadioButton player2AStarAgent;
	@FXML
	public RadioButton player2RealAStarAgent;
	@FXML
	public RadioButton player2MinimaxAgent;
	
	@FXML
	public RadioButton egyptPlayMap;
	@FXML
	public RadioButton usaPlayMap;
	
	@FXML
	public Button startGame;
	public ToggleGroup player1AgentsOptions;
	public ToggleGroup player2AgentsOptions;
	public ToggleGroup playingMapOptions;
	/*
	 * agents 0->7
	 * countries "egypt","usa"
	 *
	 */
	public void initialize(){
		egyptMap.setImage(new Image("egypt.jpg"));
		usaMap.setImage(new Image("usa.png"));
		player1AgentsOptions = new ToggleGroup();
		player1HumanAgent.setToggleGroup(player1AgentsOptions);
		player1HumanAgent.setUserData(0);
		player1PassiveAgent.setToggleGroup(player1AgentsOptions);
		player1PassiveAgent.setUserData(1);
		player1AggresiveAgent.setToggleGroup(player1AgentsOptions);
		player1AggresiveAgent.setUserData(2);
		player1PastfistAgent.setToggleGroup(player1AgentsOptions);
		player1PastfistAgent.setUserData(3);
		player1GreedyAgent.setToggleGroup(player1AgentsOptions);
		player1GreedyAgent.setUserData(4);
		player1AStarAgent.setToggleGroup(player1AgentsOptions);
		player1AStarAgent.setUserData(5);
		player1RealAStarAgent.setToggleGroup(player1AgentsOptions);
		player1RealAStarAgent.setUserData(6);
		player1MinimaxAgent.setToggleGroup(player1AgentsOptions);
		player1MinimaxAgent.setUserData(7);
		
		player2AgentsOptions = new ToggleGroup();
		player2HumanAgent.setToggleGroup(player2AgentsOptions);
		player2HumanAgent.setUserData(0);
		player2PassiveAgent.setToggleGroup(player2AgentsOptions);
		player2PassiveAgent.setUserData(1);
		player2AggresiveAgent.setToggleGroup(player2AgentsOptions);
		player2AggresiveAgent.setUserData(2);
		player2PastfistAgent.setToggleGroup(player2AgentsOptions);
		player2PastfistAgent.setUserData(3);
		player2GreedyAgent.setToggleGroup(player2AgentsOptions);
		player2GreedyAgent.setUserData(4);
		player2AStarAgent.setToggleGroup(player2AgentsOptions);
		player2AStarAgent.setUserData(5);
		player2RealAStarAgent.setToggleGroup(player2AgentsOptions);
		player2RealAStarAgent.setUserData(6);
		player2MinimaxAgent.setToggleGroup(player2AgentsOptions);
		player2MinimaxAgent.setUserData(7);
		
		playingMapOptions=new ToggleGroup();
		egyptPlayMap.setToggleGroup(playingMapOptions);
		egyptPlayMap.setUserData(1);
		usaPlayMap.setToggleGroup(playingMapOptions);
		usaPlayMap.setUserData(2);
		
	}
	
	@FXML
	public void startGameButtonAction(){
		if(player1AgentsOptions.getSelectedToggle()!=null &&
				player2AgentsOptions.getSelectedToggle()!=null&&
						playingMapOptions.getSelectedToggle()!=null){	
			try {
				agent1Type=(int) player1AgentsOptions.getSelectedToggle().getUserData()+1;
				agent2Type=(int) player2AgentsOptions.getSelectedToggle().getUserData()+1;
				country=(int) playingMapOptions.getSelectedToggle().getUserData();
				switch(country){
					case 1:Game.map=Board.EygptBoard();break;
					case 2:Game.map=Board.AmericaBorad();break;
				}
				startPagePane=FXMLLoader.load(getClass().getResource("/usa.fxml"));
			} catch (IOException e) {}
			MainModesSelectionController.primaryStage.setScene(new Scene(startPagePane,1124,675));
		}
	}

}
