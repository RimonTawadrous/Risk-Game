package testGame;

import players.HumanAgent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import selectionGUI.StartPageController;
import selectionGUI.AudioPlayer;
public class TestGame extends Application{
	public static AudioPlayer x;
	public static void main(String[] args) {		
		Application.launch(args);
		System.exit(0);

	}
	public static Stage primaryStage;
	public static Parent root;
	@Override
	public void start(Stage s) throws Exception {	
		primaryStage= new Stage();
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("startPage.fxml"));
		//Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("usa.fxml"));

		primaryStage.setScene(new Scene(root,1050,620));
		//primaryStage.setScene(new Scene(root,1124,675));
		primaryStage.setTitle("Risk Game");
		primaryStage.setResizable(false);
//		primaryStage.setFullScreen(true);
		primaryStage.show();		
		 x = new AudioPlayer();
		x.play("/risk.mp3");

	}
}
