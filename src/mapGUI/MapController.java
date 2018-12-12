package mapGUI;

import game.Game;
import helpers.Helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import players.AStar;
import players.AgressiveAgent;
import players.GreedyAgent;
import players.HumanAgent;
import players.MinMaxAgent;
import players.PacifistAgent;
import players.PassiveAgent;
import players.Player;
import players.RealTimeAStar;
import playingGUI.PlayingGUI;
import selectionGUI.MainModesSelectionController;
import testGame.TestGame;
import javafx.fxml.FXML;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import maps.Board;
import maps.Graph;
import maps.Nodes;

public abstract class MapController extends TestGame {
	@FXML
	public Pane pane;
	@FXML
	public Text numOfOwnedCountriesForPlayer1;
	@FXML
	public Text numOfOwnedCountriesForPlayer2;
	@FXML
	public Text playerTurn;
	@FXML
	public TextField readValueFromPlayer;
	@FXML
	public Button submitValue;
	@FXML
	public Text attackingResults;
	@FXML
	public Text secondsLeft;
	@FXML
	public Text currentAction;
	public HashMap <String,Integer> countries = new HashMap<String,Integer>();
	public HashMap <Integer,SVGPath> countriesPath = new HashMap<Integer,SVGPath>();	
	public HashMap <Integer,Text> solidersPosition = new HashMap<Integer,Text>();
	private SVGPath path = new SVGPath();
	private SVGPath lines = new SVGPath();
    private SVGPath otherLabels = new SVGPath();
    public Graph map;
	private Game g;
	public Player p1;
	public Player p2;
	public void initialize(){
		g = new Game();
		map=g.getMap();
		p1=choosedAgent(MainModesSelectionController.agent1Type);
		p2=choosedAgent(MainModesSelectionController.agent2Type);
		p1.setMap(map);
		p2.setMap(map);
		g.InitializeBoard(p1,p2);
		initializeBoardGui();
		if(MainModesSelectionController.country==1){
			egyptMapOffset();
			initializeEgyptMapSoliders();
		}
		else
			initializeUSAMapSoliders();
		numOfOwnedCountriesForPlayer1.setText(p1.getCountriesOwned()+"");
		numOfOwnedCountriesForPlayer2.setText(p2.getCountriesOwned()+"");
		playerTurn.setText("PLAYER 1 TURN");
		readValueFromPlayer.setFocusTraversable(false);
		submitValue.setFocusTraversable(false);
		currentAction.setText("GAME WILL START IN 5 SECONDS");
	}
	private void egyptMapOffset(){
		for(int i=1;i<countriesPath.size();i++){
			countriesPath.get(i).setLayoutX(170);
			countriesPath.get(i).setLayoutY(-250);
		}
	}
	private Player choosedAgent(int type){
		// = Board.AmericaBorad();
		switch(type){
			case 1:return new HumanAgent();
			case 2:return new PassiveAgent();
			case 3:return new AgressiveAgent();
			case 4:return new PacifistAgent();
			case 5:return new GreedyAgent();
			case 6:return new AStar();
			case 7:return new RealTimeAStar();
			case 8:return new MinMaxAgent();
		}
		return new HumanAgent();

	}
	private void initializeBoardGui(){
		Helpers.readCountries(countries,countriesPath);
		for(int i=1;i<countriesPath.size();i++){
			countriesPath.get(i).setStyle("-fx-fill:#808080;");
			pane.getChildren().add(countriesPath.get(i));
		}
		if(MainModesSelectionController.country==2){
			path.setContent(Helpers.readPath());	
			lines.setContent(Helpers.readLine());		
			otherLabels.setContent(Helpers.readOtherLabels());
			pane.getChildren().addAll(path,lines,otherLabels);
		}
		else{
			
		}
	}
	//add or edit number of soliders to country
	private void addSoliders(int countryId,int x,int y){
		Text temp;
		int soliders =map.getNodeDetails(countryId).getSoliderNumb();
		temp=new Text(soliders+"");
		temp.setLayoutX(x);
		temp.setLayoutY(y);
		temp.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.REGULAR,18));
		solidersPosition.put(countryId, temp);
		initializePlayersCountries(countryId);
		pane.getChildren().add(solidersPosition.get(countryId));
	}
	private void initializePlayersCountries(int countryId){
		if(map.getNodeDetails(countryId).getPlayer1OR2()==1)
			countriesPath.get(countryId).setStyle("-fx-fill:#ff6666;");
		else
			countriesPath.get(countryId).setStyle("-fx-fill:#9999ff;");
	}
	private void initializeEgyptMapSoliders(){
		addSoliders(1, 329, 145);
		addSoliders(2, 458, 95);
		addSoliders(3, 487, 101);
		addSoliders(4, 511, 69);
		addSoliders(5, 537, 77);
		addSoliders(6, 546, 55);
		addSoliders(7, 581, 70);
		addSoliders(8, 645, 177);
		addSoliders(9, 516, 90);
		addSoliders(10, 514, 109);
		addSoliders(11, 530, 116);
		addSoliders(12, 544, 97);
		addSoliders(13, 576, 98);
		addSoliders(14, 427, 208);
		addSoliders(15, 501, 165);
		addSoliders(16, 544, 148);
		addSoliders(17, 568, 151);
		addSoliders(18, 635, 103);
		addSoliders(19, 508, 189);
		addSoliders(20, 479, 219);
		addSoliders(21, 385, 376);
		addSoliders(22, 527, 278);
		addSoliders(23, 635, 352);
		addSoliders(24, 553, 312);
		addSoliders(25, 598, 338);
		addSoliders(26, 591, 350);
		addSoliders(27, 601, 463);
		addSoliders(28, 718, 507);
	}
	private void initializeUSAMapSoliders(){
		addSoliders(1, 122, 42);
		addSoliders(2, 92, 111);
		addSoliders(3, 67, 270);
		addSoliders(4, 127, 220);
		addSoliders(5, 183, 134);
		addSoliders(6, 274, 76);
		addSoliders(7, 295, 168);
		addSoliders(8, 213, 245);
		addSoliders(9, 198, 346);
		addSoliders(10, 318, 260);
		addSoliders(11, 298, 363);
		addSoliders(12, 426, 433);
		addSoliders(13, 461, 346);
		addSoliders(14, 439, 279);
		addSoliders(15, 427, 215);
		addSoliders(16, 409, 145);
		addSoliders(17, 410, 78);
		addSoliders(18, 500, 119);
		addSoliders(19, 519, 200);
		addSoliders(20, 540, 283);
		addSoliders(21, 542, 361);
		addSoliders(22, 542, 429);	
		addSoliders(23, 597, 400);
		addSoliders(24, 650, 391);
		addSoliders(25, 762, 490);
		addSoliders(26, 713, 392);
		addSoliders(27, 758, 362);
		addSoliders(28, 780, 320);
		addSoliders(29, 784, 270);
		addSoliders(30, 738, 263);
		addSoliders(31, 652, 335);
		addSoliders(32, 674, 289);
		addSoliders(33, 592, 242);
		addSoliders(34, 578, 147);
		addSoliders(35, 642, 236);
		addSoliders(36, 661, 172);
		addSoliders(37, 699, 225);
		addSoliders(38, 781, 199);
		addSoliders(39, 814, 143);
		addSoliders(40, 841, 125);
		addSoliders(41, 862, 139);
		addSoliders(42, 888, 70);
		addSoliders(43, 857, 163);
		addSoliders(44, 872, 174);
		addSoliders(45, 850, 181);
		addSoliders(46, 833, 212);
		addSoliders(47, 826, 247);
		addSoliders(48, 800, 244);
		addSoliders(49, 289, 551);
		addSoliders(50, 110, 478);
	}
}
