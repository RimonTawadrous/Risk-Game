package playingGUI;

import java.io.IOException;
import java.util.ArrayList;

import maps.Nodes;
import players.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.Text;
public class Dice {
	@FXML
	public Pane pane;
	@FXML
	public ImageView dice;
	@FXML
	public VBox diceValeusVBOX;
	@FXML
	public Button next;
	@FXML
	public ImageView attacker1;
	@FXML
	public ImageView attacker2;
	@FXML
	public ImageView attacker3;
	@FXML
	public ImageView defender1;
	@FXML
	public ImageView defender2;
	@FXML
	public Text header;
	long temp = System.currentTimeMillis();
	public static ArrayList<Integer[]> attckerResult = new ArrayList<Integer[]>();
	public static ArrayList<Integer[]> defenderResult = new ArrayList<Integer[]>();
	public void initialize(){
		dice.setImage(new Image("dice.gif"));
		
	}
	public static  void init(Integer []  attckResult,Integer [] defendResult){
		attckerResult.add(attckResult);
		defenderResult.add(defendResult);
		System.out.println("koko");
	}
	int check=0;
	@FXML
	public void rolling(){
			if(check==0&&System.currentTimeMillis()-temp>2800){
				check=1;
				pane.getChildren().remove(dice);
				diceValeusVBOX.setDisable(false);
				next.setVisible(true);
				header.setVisible(true);
					roolingResults(attckerResult.get(0),defenderResult.get(0));
			}
	}
	
	private void roolingResults(Integer []  attckerResult,Integer [] defenderResult){
		String temp="";
		ArrayList<String> path = new ArrayList<String>();
		for(int i=0;i<attckerResult.length;i++){
				temp="diceValues/";
				temp+=attckerResult[i]+".png";
				path.add(temp);
				temp="";
		}
		for(int i=0;i<path.size();i++){
			switch(i){
				case 0:attacker1.setImage(new Image(path.get(i)));break;
				case 1:attacker2.setImage(new Image(path.get(i)));break;
				case 2:attacker3.setImage(new Image(path.get(i)));break;
			}
		}
		path.clear();
		temp="";
		for(int i=0;i<defenderResult.length;i++){
			temp="diceValues/";
			temp+=defenderResult[i]+".png";
			path.add(temp);
			temp="";
		}
		for(int i=0;i<path.size();i++){
			switch(i){
				case 0:defender1.setImage(new Image(path.get(i)));break;
				case 1:defender2.setImage(new Image(path.get(i)));break;
		}
	}
		roolingActionOnGame(attckerResult,defenderResult);
}
	private void roolingActionOnGame(Integer []  attckerResult,Integer [] defenderResult){
		for(int i=0;i<defenderResult.length;i++){
			if(attckerResult[0]==defenderResult[0]){
				next.setOnAction(e->{
					this.attckerResult.clear();
					this.defenderResult.clear();
					primaryStage.close();
				});
			}
			else if(defenderResult.length<=attckerResult.length&&attckerResult[i]>defenderResult[i]){
				next.setOnAction(e->{
					this.attckerResult.clear();
					this.defenderResult.clear();
					primaryStage.close();
				});
			}
		}
	}
	int count=1;
	@FXML
	public void nextButton(){
		if(count<attckerResult.size()){
			roolingResults(attckerResult.get(count),defenderResult.get(count));
			count++;
		}
		else{
			attckerResult.clear();
			defenderResult.clear();
			primaryStage.close();
		}
	}
	public static Stage primaryStage;
	public static Parent root;
	public  void run() throws IOException {		
		primaryStage= new Stage();
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("dice.fxml"));
		primaryStage.setScene(new Scene(root,276,374));
		primaryStage.setTitle("Dice");
		primaryStage.setResizable(false);
		primaryStage.show();		

	}

}


