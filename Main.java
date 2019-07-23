import java.io.File;
import java.util.Optional;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class Main extends Application {
		
//	:בודק יקר שלום רב! להלן קצת סדר במחלקות החדשות בעבודה
//	
//	Class Main - here we call to Class StoreMenu and active all javaFX program
//	
//	Class StoreMenu - managing all "Store" Classes and creating the Store menu
//	Class StoreBottom - helping class StoreMenu to create the bottom section
//	Class StoreTop - helping class StoreMenu to create the Top section
//	Class StoreCenter- helping class StoreMenu to create the Center section
//	
//	Class AddMenu - managing all "Add" Classes and creating the Add menu 
//	Abstract Class AddWindowBasic - include all common properties of Add instrument Window family
//	Class AddBassWindow - inherit from AddWindowBasic Class and creating the Add Bass window
//	Class AddGuitarWindow - inherit from AddWindowBasic Class and creating the Add Guitar window
//	Class AddFluteWindow - inherit from AddWindowBasic Class and creating the Add Flute window
//	Class AddSaxophoneWindow - inherit from AddWindowBasic Class and creating the Add Saxophone window


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		final int HIGHT = 265, WIDTH = 720;
		final String Error = "Error", File_Error = "File Error",
				ERROR_MESSAGE = "Cannot read from file, please try again", CONFIRMATION = "Confimation",
				LOAD_MESSAGE = "Load Instrument From File ", FILE_REQUEST = "Please enter file name: ";

		File file = null;
		do {
			String result = setTextInputDialog(CONFIRMATION, LOAD_MESSAGE, FILE_REQUEST);
			file = AfekaInstruments.getInstrumentsFileFromUser(result);
			if (file == null) {
				setErrorDialog(Error, File_Error, ERROR_MESSAGE);
			}
		} while (file == null);

		StoreMenu storeMenu = new StoreMenu();
		
		primaryStage.centerOnScreen();
		primaryStage.setHeight(HIGHT);
		primaryStage.setWidth(WIDTH);
		AfekaInstruments.loadInstrumentsFromFile(file, storeMenu.getAllInstruments());
		AfekaInstruments.printInstruments(storeMenu.getAllInstruments());
		primaryStage.setTitle("Afeka Instruments Music Store");
		storeMenu.activeStoreMenu();
		primaryStage.setScene(storeMenu.getScene());
		primaryStage.show();
	}

	// text dialog window method
	public static String setTextInputDialog(String title, String Header, String contant) {
		final String DEFAULT_TEXT = "instruments1b.txt";
        final int EXIT=0;
		TextInputDialog dialog = new TextInputDialog(DEFAULT_TEXT);
		dialog.setTitle(title);
		dialog.setHeaderText(Header);
		dialog.setContentText(contant);
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			return result.get();
		} else {
			dialog.close();
			System.exit(EXIT);
			return null;
		}
	}
	// error dialog window method
	public static void setErrorDialog(String title, String Header, String contant) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(Header);
		alert.setContentText(contant);
		alert.showAndWait();
	}

	// info dialog window method
	public static void setInfoDialog(String title, String Header, String contant) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(Header);
		alert.setContentText(contant);
		alert.showAndWait();
	}

}
