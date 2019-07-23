
import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public abstract class AddWindowBasic {

	//This class is the father of all Add window Classes
	
	public final String ADD = "Add", BRAND = "Brand: ", PRICE = "Price: ", ERROR = "Error",
			ERROR_PRICE = "Price Field must be a Number!", ERROR_EMPTY_FIELDS = "all fields must be full",
			EMPTY_STRING = "", NUMBER_OF_STRINGS = "NumberOfStrings: ",
			NUMOFSTRINGS_ERROR = "Number of Strings Field must be a Number!", FRETLESS = "Fretless: ", TYPE = "Type: ",
			MATERIAL = "Material: ";

	private Label brandLabel = new Label();
	private Label priceLabel = new Label();
	private TextField addPriceField = new TextField();
	private TextField addBrandField = new TextField();
	private Button addInstrumentButton = new Button(ADD);
	private String brandFieldValue;
	private Number priceFieldValue;

	public AddWindowBasic() {
		super();
		brandLabel = new Label(BRAND);
		priceLabel = new Label(PRICE);
		addPriceField = new TextField();
		addBrandField = new TextField();
	}

	public Label getBrandLabel() {
		return brandLabel;
	}

	public void setBrandLabel(Label brandLabel) {
		this.brandLabel = brandLabel;
	}

	public Label getPriceLabel() {
		return priceLabel;
	}

	public void setPriceLabel(Label priceLabel) {
		this.priceLabel = priceLabel;
	}

	public TextField getAddPriceField() {
		return addPriceField;
	}

	public void setAddPriceField(TextField addPriceField) {
		this.addPriceField = addPriceField;
	}

	public TextField getAddBrandField() {
		return addBrandField;
	}

	public void setAddBrandField(TextField addBrandField) {
		this.addBrandField = addBrandField;
	}

	public Button getAddInstrumentButton() {
		return addInstrumentButton;
	}

	public void setAddInstrumentButton(Button addInstrumentButton) {
		this.addInstrumentButton = addInstrumentButton;
	}

	public String getBrandFieldValue() {
		return brandFieldValue;
	}

	public void setBrandFieldValue(String brandFieldValue) {
		this.brandFieldValue = brandFieldValue;
	}

	public Number getPriceFieldValue() {
		return priceFieldValue;
	}

	public void setPriceFieldValue(Number priceFieldValue) {
		this.priceFieldValue = priceFieldValue;
	}

	//creating basic window class and handling exceptions (used by inherited classes)
	public void createBasic(ArrayList<MusicalInstrument> allInstruments) throws Exception {
		try {
			brandFieldValue = getAddBrandField().getText();// addBrandField
			if (brandFieldValue.equals(EMPTY_STRING)) {
				throw new Exception();
			}
			priceFieldValue = Double.parseDouble(getAddPriceField().getText());// addPriceField
		} catch (NumberFormatException e2) {
			Main.setErrorDialog(ERROR, ERROR, ERROR_PRICE);
			throw new NumberFormatException();
		} catch (Exception e2) {
			Main.setErrorDialog(ERROR, ERROR, ERROR_EMPTY_FIELDS);
			throw new Exception();
		}

	}

}
