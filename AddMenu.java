import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AddMenu {

	private Stage addStage;
	private Scene addScene;
	private BorderPane addLayout;
	private GridPane addCenterGrid = new GridPane();
	private ComboBox<String> instrumentsOpt;
	private HBox addTopBox = new HBox();
	private HBox addBottomBox = new HBox();
	private ArrayList<MusicalInstrument> allInstruments = new ArrayList<MusicalInstrument>();
	private ArrayList<MusicalInstrument> searchedInstruments = new ArrayList<MusicalInstrument>();
	private AddGuitarWindow guitarWindow = new AddGuitarWindow();
	public AddBassWindow bassWindow = new AddBassWindow();
	private AddSaxophoneWindow saxophoneWindow = new AddSaxophoneWindow();
	private AddFluteWindow fluteWindow = new AddFluteWindow();

	private final String GUITAR = "Guitar", BASS = "Bass", FLUTE = "Flute", SAXOPHONE = "Saxophone";
	private final int ZERO_PLACE=0,FIRST_PLACE=1,SECOND_PLACE=2,THIRD_PLACE=3;

	// constructor
	public AddMenu(ArrayList<MusicalInstrument> allInstruments, ArrayList<MusicalInstrument> searchedInstruments) {
		this.allInstruments = allInstruments;
		this.searchedInstruments = searchedInstruments;
	}

	public ArrayList<MusicalInstrument> getAllInstruments() {
		return allInstruments;
	}

	public ArrayList<MusicalInstrument> getSearchedInstruments() {
		return searchedInstruments;
	}

	public void AddInstrument() {

		final String AFEKA_TITLE = "Afeka Instruments - Add New Instrument",
				COMBO_PROMPT = "Choose Instrument Type Here", CLASSIC = "Classic", ACOUSTIC = "Acoustic",
				ELECTRIC = "Electric", RECORDER = "Recorder", WOOD = "Wood", METAL = "Metal", PLASTIC = "Plastic";
        final int HIGHT=340,WIDTH=340;   
		
		addLayout = new BorderPane();
		addScene = new Scene(addLayout);
		addStage = new Stage();

		addStage.setHeight(HIGHT);
		addStage.setWidth(WIDTH);
		addStage.setTitle(AFEKA_TITLE);

		instrumentsOpt = new ComboBox<String>();

		instrumentsOpt.setPromptText(COMBO_PROMPT);
		instrumentsOpt.getItems().addAll(GUITAR, BASS, FLUTE, SAXOPHONE);

		guitarWindow.getGuitarTypeComboBox().getItems().addAll(CLASSIC, ACOUSTIC, ELECTRIC); // guitarTypeComboBox
		fluteWindow.getFluteTypeComboBox().getItems().addAll(FLUTE, RECORDER, BASS);
		fluteWindow.getFluteMaterialComboBox().getItems().addAll(WOOD, METAL, PLASTIC);

		addLayout.setCenter(instrumentsOpt);

		addComboBoxLogic();

		addStage.setScene(addScene);

		addStage.show();

	}

	// main comboBox of adding type of instrument
	public void addComboBoxLogic() {

		instrumentsOpt.setOnAction(e -> {
			if (instrumentsOpt.getValue().trim().equalsIgnoreCase(GUITAR)) {
				clearChilderen();
				GuitarOptDesign();
				GuitarOptFunctionality();
			} else if (instrumentsOpt.getValue().trim().equalsIgnoreCase(BASS)) {
				clearChilderen();
				BassOptDesign();
				BassOptFunctionality();
			} else if (instrumentsOpt.getValue().trim().equalsIgnoreCase(FLUTE)) {
				clearChilderen();
				fluteOptDesign();
				fluteOptFunctionality();
			} else if (instrumentsOpt.getValue().trim().equalsIgnoreCase(SAXOPHONE)) {
				clearChilderen();
				saxophoneOptDesign();
				saxophoneOptFunctionality();
			}

		});

	}

	public void clearChilderen() {
		addTopBox.getChildren().clear();
		addCenterGrid.getChildren().clear();
		addBottomBox.getChildren().clear();
	}

	// the "add" stage and scene builder for Flute,Saxophone, Guitar and Bass
	public void optDesignBuilder(AddWindowBasic window) {

		final int INITIAL = 0, GRID_PADDING = 10, TOPBOX_PADDING = 50, BOTTOMBOX_PADDING = 150, FIRST = 1;

		addCenterGrid.setPadding(new Insets(GRID_PADDING, INITIAL, GRID_PADDING, INITIAL));
		addCenterGrid.setVgap(GRID_PADDING);
		addCenterGrid.setHgap(GRID_PADDING);
		addCenterGrid.setAlignment(Pos.CENTER);

		addTopBox.setPadding(new Insets(TOPBOX_PADDING, INITIAL, INITIAL, INITIAL));
		addTopBox.setAlignment(Pos.TOP_CENTER);
		addTopBox.getChildren().add(instrumentsOpt);

		addBottomBox.setPadding(new Insets(INITIAL, INITIAL, BOTTOMBOX_PADDING, INITIAL));
		addBottomBox.setAlignment(Pos.BOTTOM_CENTER);
		addBottomBox.getChildren().add(window.getAddInstrumentButton());// addInstrumentButton

		GridPane.setConstraints(window.getBrandLabel(), INITIAL, INITIAL);// brandLabel
		GridPane.setConstraints(window.getAddBrandField(), FIRST, INITIAL);// addBrandField

		GridPane.setConstraints(window.getPriceLabel(), INITIAL, FIRST);// priceLabel
		GridPane.setConstraints(window.getAddPriceField(), FIRST, FIRST);// addPriceField

	}

	public void saxophoneOptDesign() {

		final String PROMPT_TYPE = "EX: Jupiter", PROMPT_PRICE = "EX: 5400";

		optDesignBuilder(saxophoneWindow);
		saxophoneWindow.getAddBrandField().setPromptText(PROMPT_TYPE);// addBrandField
		saxophoneWindow.getAddPriceField().setPromptText(PROMPT_PRICE);// addPriceField
		addCenterGrid.getChildren().addAll(saxophoneWindow.getBrandLabel(), saxophoneWindow.getAddBrandField(),
				saxophoneWindow.getPriceLabel(), saxophoneWindow.getAddPriceField());
		addLayout.setCenter(addCenterGrid);
		addLayout.setTop(addTopBox);
		addLayout.setBottom(addBottomBox);

	}

	public void saxophoneOptFunctionality() {
		saxophoneWindow.getAddInstrumentButton().setOnAction(e -> {// addInstrumentButton
			saxophoneWindow.createSaxophone(allInstruments);
			clearChilderen();
			saxophoneOptDesign();
		});

	}

	public void fluteOptDesign() {

		final String COMBOBOX_PROMPT_MATERIAL = "Material", COMBOBOX_PROMPT_TYPE = "Type", PROMPT_TYPE = "EX: Levit",
				PROMPT_PRICE = "EX: 300";
		optDesignBuilder(fluteWindow);
		GridPane.setConstraints(fluteWindow.getMaterialLabel(), ZERO_PLACE, SECOND_PLACE); // materialLabel
		fluteWindow.getFluteMaterialComboBox().setPromptText(COMBOBOX_PROMPT_MATERIAL);// fluteMaterialComboBox
		GridPane.setConstraints(fluteWindow.getFluteMaterialComboBox(), FIRST_PLACE, SECOND_PLACE);// fluteMaterialComboBox

		GridPane.setConstraints(fluteWindow.getTypeLabel(), ZERO_PLACE, THIRD_PLACE);// typeLabel
		fluteWindow.getFluteTypeComboBox().setPromptText(COMBOBOX_PROMPT_TYPE);// fluteTypeComboBox
		GridPane.setConstraints(fluteWindow.getFluteTypeComboBox(), FIRST_PLACE, THIRD_PLACE);// fluteTypeComboBox

		fluteWindow.getAddBrandField().setPromptText(PROMPT_TYPE);// addBrandField
		fluteWindow.getAddPriceField().setPromptText(PROMPT_PRICE);// addPriceField

		addCenterGrid.getChildren().addAll(fluteWindow.getBrandLabel(), fluteWindow.getAddBrandField(),
				fluteWindow.getPriceLabel(), fluteWindow.getAddPriceField(), fluteWindow.getMaterialLabel(),
				fluteWindow.getTypeLabel(), fluteWindow.getFluteMaterialComboBox(), fluteWindow.getFluteTypeComboBox());
		addLayout.setCenter(addCenterGrid);
		addLayout.setTop(addTopBox);
		addLayout.setBottom(addBottomBox);
	}

	public void fluteOptFunctionality() {
		fluteWindow.getAddInstrumentButton().setOnAction(e -> {// addInstrumentButton
			fluteWindow.createFlute(allInstruments);
			clearChilderen();
			fluteOptDesign();
		});
	}

	public void BassOptDesign() {
		
		final String PROMPT_TYPE = "EX: Fender Jazz",
				PROMPT_PRICE = "EX: 7500",PROMPT_NUM_STRINGS="EX: 4";
		
		GridPane.setConstraints(bassWindow.getNumberOfStringsLabel(), ZERO_PLACE, SECOND_PLACE);// numberOfStringsLabel
		GridPane.setConstraints(bassWindow.getAddNumberOfStringsField(), FIRST_PLACE, SECOND_PLACE);// addNumberOfStringsField
		optDesignBuilder(bassWindow);// brandLabel, priceLabel
		bassWindow.getAddNumberOfStringsField().setPromptText(PROMPT_NUM_STRINGS);// addNumberOfStringsField
		bassWindow.getAddBrandField().setPromptText(PROMPT_TYPE);// addBrandField
		bassWindow.getAddPriceField().setPromptText(PROMPT_PRICE);// addPriceField

		GridPane.setConstraints(bassWindow.getType(), ZERO_PLACE, THIRD_PLACE);// type
		GridPane.setConstraints(bassWindow.getFretlessBox(), FIRST_PLACE, THIRD_PLACE);// fretlessBox

		addCenterGrid.getChildren().addAll(bassWindow.getBrandLabel(), bassWindow.getAddBrandField(),
				bassWindow.getPriceLabel(), bassWindow.getAddPriceField(), bassWindow.getNumberOfStringsLabel(),
				bassWindow.getAddNumberOfStringsField(), bassWindow.getType(), bassWindow.getFretlessBox());
		addLayout.setCenter(addCenterGrid);
		addLayout.setTop(addTopBox);
		addLayout.setBottom(addBottomBox);

	}

	public void BassOptFunctionality() {
		bassWindow.getAddInstrumentButton().setOnAction(e -> {// addInstrumentButton
			bassWindow.createBass(bassWindow, getAllInstruments());
			clearChilderen();
			BassOptDesign();
		});

	}

	public void GuitarOptDesign() {	
		final String COMBOBOX_TYPE_PROMPT="Type",PROMPT_BRAND = "EX: Gibson",
				PROMPT_PRICE = "EX: 7500",PROMPT_NUM_STRINGS="EX: 6";	
		optDesignBuilder(guitarWindow);// brandLabel, priceLabel
		GridPane.setConstraints(guitarWindow.getNumberOfStringsLabel(), ZERO_PLACE, SECOND_PLACE);// numberOfStringsLabel
		GridPane.setConstraints(guitarWindow.getAddNumberOfStringsField(), FIRST_PLACE, SECOND_PLACE); // addNumberOfStringsField
		GridPane.setConstraints(guitarWindow.getType(), ZERO_PLACE, THIRD_PLACE);// type

		guitarWindow.getGuitarTypeComboBox().setPromptText(COMBOBOX_TYPE_PROMPT); // guitarTypeComboBox
		GridPane.setConstraints(guitarWindow.getGuitarTypeComboBox(), FIRST_PLACE, THIRD_PLACE);//// guitarTypeComboBox

		guitarWindow.getAddNumberOfStringsField().setPromptText(PROMPT_NUM_STRINGS); // addNumberOfStringsField
		guitarWindow.getAddBrandField().setPromptText(PROMPT_BRAND); // addBrandField
		guitarWindow.getAddPriceField().setPromptText(PROMPT_PRICE); // addPriceField

		addCenterGrid.getChildren().addAll(guitarWindow.getBrandLabel(), guitarWindow.getAddBrandField(),
				guitarWindow.getPriceLabel(), guitarWindow.getAddPriceField(), guitarWindow.getNumberOfStringsLabel(),
				guitarWindow.getAddNumberOfStringsField(), guitarWindow.getType(),
				guitarWindow.getGuitarTypeComboBox());
		addLayout.setCenter(addCenterGrid);
		addLayout.setTop(addTopBox);
		addLayout.setBottom(addBottomBox);
	}

	public void GuitarOptFunctionality() {
		guitarWindow.getAddInstrumentButton().setOnAction(e -> { // addInstrumentButton
			guitarWindow.createGuiter(getAllInstruments());
			clearChilderen();
			GuitarOptDesign();
		});
	}

}
