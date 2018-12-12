package selectionGUI;
import javafx.scene.Scene;
import mapGUI.MapController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
public abstract class MainModesSelectionController extends MapController{
	@FXML
	public Pane startPagePane;
	@FXML
	public Button startGameButton;
	
	public static int agent1Type;
	public static int agent2Type;
	public static int country;
}
