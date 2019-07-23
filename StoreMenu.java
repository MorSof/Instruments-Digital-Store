import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StoreMenu {

	private final String ARROW_RIGHT = ">", ARROW_LEFT = "<", EMPTY_STRING = "", MESSAGE = "Message",
			ALL_DELETED = "All instrument have been deleted from inventory!",
			ALL_SEARCHED_DELETED = "All searched Instrument have been deleted from inventory,\n showing the rest of the inventory.",
			STORE_EMPTY = "Store Is Empty", DIDNT_FOUND = "Instrument didnt found -> showing the full inventory.";
	private final int INITIAL=0, ARROW_PADDING=10;
	
	private Scene primaryScene;
	private BorderPane layout;
	private VBox rightBox = new VBox();
	private VBox leftBox = new VBox();
	private Button arrowRight = new Button(ARROW_RIGHT);
	private Button arrowLeft = new Button(ARROW_LEFT);
	private int index = INITIAL;
	private boolean searchActive = false;

	private StoreTop storeTop = new StoreTop();
	private StoreBottom storeBottom = new StoreBottom();
	private StoreCenter storeCenter = new StoreCenter();

	private ArrayList<MusicalInstrument> allInstruments = new ArrayList<MusicalInstrument>();
	private ArrayList<MusicalInstrument> searchedInstruments = new ArrayList<MusicalInstrument>();

	public ArrayList<MusicalInstrument> getAllInstruments() {
		return allInstruments;
	}

	public ArrayList<MusicalInstrument> getSearchedInstruments() {
		return searchedInstruments;
	}

	public Scene getScene() {
		return primaryScene;
	}

	//Activate all windows
	public void activeStoreMenu() {
		layout = new BorderPane();
		primaryScene = new Scene(layout);
		setTop();
		setCenter();
		setBottom();
		setLeft();
		setRight();
	}

	//active all window class and logic
	private void setTop() {
		storeTop.setTopDesign();
		storeTop.getGo().setOnAction(e -> {
			index = INITIAL;
			searchedInstruments.clear();
			searchLogic();
		});
		storeTop.getTopBox().setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				index = INITIAL;
				searchedInstruments.clear();
				searchLogic();
			}
		});
		layout.setTop(storeTop.getTopBox());
	}

	// the search logic algorithm
	public void searchLogic() {
		String searchResualt = storeTop.getSearch().getText();
		if (searchResualt.isEmpty()) {
			// get here if iv pressed ENTER on an Empty String
			searchActive = false;
			searchedInstruments.clear();
			setCenterTextFields(allInstruments);
			return;
		} else {
			// if code gets here it means that iv searched something
			searchActive = true;
			index = INITIAL;
			for (int i = 0; i < allInstruments.size(); i++) {
				if (allInstruments.get(i).toString().toLowerCase().contains(searchResualt.toLowerCase())) {
					searchedInstruments.add(allInstruments.get(i));
				}
			}
		}
		if ((!searchResualt.isEmpty()) && searchedInstruments.isEmpty()) {
			// get here if iv searched something but i didn't found
			Main.setInfoDialog(MESSAGE, MESSAGE, DIDNT_FOUND);
			searchActive = false;
			searchedInstruments.clear();
			setCenterTextFields(allInstruments);
		} else {
			// get here if iv searched something but i found
			setCenterTextFields(searchedInstruments);
		}
	}

	//Activate  center window and logic
	public void setCenter() {
		layout.setCenter(storeCenter.getCenterGrid());
		setCenterTextFields(allInstruments);
	}

	// responsible of what will be written on the center text fields
	public void setCenterTextFields(ArrayList<MusicalInstrument> myInstruments) {
		if (myInstruments.isEmpty()) {
			setTextFieldToEmpty();
			if (allInstruments.isEmpty()) {
				// get specific - if my main inventory is Empty- send a message
				Main.setInfoDialog(MESSAGE, MESSAGE, STORE_EMPTY);
			}
		} else {
			try {
				storeCenter.getTypeField().setText(myInstruments.get(index).getClass().getCanonicalName());
				storeCenter.getBrandField().setText(myInstruments.get(index).getBrand().toString());
				storeCenter.getPriceField().setText(myInstruments.get(index).getPrice().toString());
			} catch (IndexOutOfBoundsException e) {
				index = 0;
				return;
			}

		}
		arrowLeft.setOnAction(e -> instrumentChanged(false, myInstruments));
		arrowRight.setOnAction(e -> instrumentChanged(true, myInstruments));
	}

	// instrument change with arrow left or right logic
	public void instrumentChanged(boolean forward, ArrayList<MusicalInstrument> myInstruments) {	
		if (forward)
			index++;
		else
			index--;
		if (index >= myInstruments.size())
			index = INITIAL;
		else if (index < 0)
			index = myInstruments.size() - 1;

		System.out.println(index);
		
		setCenterTextFields(myInstruments);
	}

	//Activate the bottom screen with logic
	public void setBottom() {
		storeBottom.getBottomBox().getChildren().addAll(setBottomButtons(), storeBottom.setBottomMovingLable());
		layout.setBottom(storeBottom.getBottomBox());
	}

	// Responsible for the buttons logic
	public HBox setBottomButtons() {
		storeBottom.getDelete().setOnAction(e -> deleteInstrument());
		primaryScene.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.DELETE) {
				deleteInstrument();
			}
		});

		storeBottom.getAdd().setOnAction(e -> addStagePhase());
		primaryScene.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.A) {			
				addStagePhase();
			}
		});

		storeBottom.getClear().setOnAction(e -> {
			clearInstruments();
		});
		storeBottom.getButtonBox().getChildren().addAll(storeBottom.getAdd(), storeBottom.getDelete(),
				storeBottom.getClear());

		return storeBottom.getButtonBox();

	}

	// here we are going to the "Add" Windows
	public void addStagePhase() {
		AddMenu addMenu = new AddMenu(allInstruments, searchedInstruments);
		addMenu.AddInstrument();
		allInstruments = addMenu.getAllInstruments();
		searchedInstruments = addMenu.getSearchedInstruments();
	}

	//Delete instruments logic
	public void deleteInstrument() {
		if (searchedInstruments.isEmpty() && searchActive == false) {
			// it means that iv searched nothing and i'm looking on my regular inventory
			
			try {
				allInstruments.remove(index);
				if(index == allInstruments.size()) { //last index
					index=0;
				}
				setCenterTextFields(allInstruments);
			} catch (IndexOutOfBoundsException e) {
				index = INITIAL;
				setTextFieldToEmpty();
			}
			System.out.println(index);
		} else if (searchedInstruments.isEmpty() && searchActive == true) {
			// that means that i'm on the search array and iv deleted everything
			Main.setInfoDialog(MESSAGE, MESSAGE, ALL_SEARCHED_DELETED);
			searchActive = false;
			setCenterTextFields(allInstruments);
		} else { // it means that the search is active
			try {
				int tempIndex = allInstruments.indexOf(searchedInstruments.get(index));
				searchedInstruments.remove(index);
				allInstruments.remove(tempIndex);
				if(index == searchedInstruments.size()) { //last index
					index=0;
				}
			} catch (IndexOutOfBoundsException e) {
				index = INITIAL;
				setTextFieldToEmpty();
			}
			setCenterTextFields(searchedInstruments);
		}
	}

	//setting the right window
	public void setRight() {
		layout.setRight(rightBox);
		rightBox.setAlignment(Pos.CENTER_RIGHT);
		rightBox.setPadding(new Insets(INITIAL, ARROW_PADDING, INITIAL, INITIAL));
		rightBox.getChildren().addAll(arrowRight);
	}

	//setting the left window
	public void setLeft() {
		layout.setLeft(leftBox);
		leftBox.setAlignment(Pos.CENTER_LEFT);
		leftBox.setPadding(new Insets(INITIAL, INITIAL, INITIAL, ARROW_PADDING));
		leftBox.getChildren().addAll(arrowLeft);
	}

	public void setTextFieldToEmpty() {
		storeCenter.getTypeField().setText(EMPTY_STRING);
		storeCenter.getBrandField().setText(EMPTY_STRING);
		storeCenter.getPriceField().setText(EMPTY_STRING);
	}

	// clear logic
	public void clearInstruments() {
		allInstruments.clear();
		searchedInstruments.clear();
		setTextFieldToEmpty();
		Main.setInfoDialog(MESSAGE, MESSAGE, ALL_DELETED);

	}

}
