package selectionGUI;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
public class StartPageController extends MainModesSelectionController{
	
	@FXML
	public ImageView startImage;
	@FXML
	public void initialize(){
		startGameButton.setFocusTraversable(false);
		startImage.setImage(new Image("riskImage.jpeg"));
	}
	
	@FXML
	public void startGameButtonAction(){
		try {
			
			startPagePane=FXMLLoader.load(getClass().getResource("/gameModes.fxml"));
		} catch (IOException e) {}
		MainModesSelectionController.primaryStage.setScene(new Scene(startPagePane,687,611));
		
	}

}
