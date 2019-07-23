import java.util.ArrayList;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddGuitarWindow extends AddWindowBasic {

	private Label numberOfStringsLabel = new Label(NUMBER_OF_STRINGS);
	private Label type = new Label(TYPE);
	private ComboBox<String> guitarTypeComboBox = new ComboBox<String>();
	private TextField addNumberOfStringsField = new TextField();

	public Label getNumberOfStringsLabel() {
		return numberOfStringsLabel;
	}

	public void setNumberOfStringsLabel(Label numberOfStringsLabel) {
		this.numberOfStringsLabel = numberOfStringsLabel;
	}

	public Label getType() {
		return type;
	}

	public void setType(Label type) {
		this.type = type;
	}

	public ComboBox<String> getGuitarTypeComboBox() {
		return guitarTypeComboBox;
	}

	public void setGuitarTypeComboBox(ComboBox<String> guitarTypeComboBox) {
		this.guitarTypeComboBox = guitarTypeComboBox;
	}

	public TextField getAddNumberOfStringsField() {
		return addNumberOfStringsField;
	}

	public void setAddNumberOfStringsField(TextField addNumberOfStringsField) {
		this.addNumberOfStringsField = addNumberOfStringsField;
	}

	//creating Guiter window class and handling exceptions
	public void createGuiter(ArrayList<MusicalInstrument> allInstruments) {
		final int INITIAL=0;
		int numOfStringsValue = INITIAL;
		String TypeValue;

		try {
			createBasic(allInstruments);
		} catch (Exception e) {
			return;
		}

		try {
			TypeValue = guitarTypeComboBox.getValue(); // guitarTypeComboBox
			if (TypeValue.equals(null)) {
				throw new Exception();
			}
			numOfStringsValue = Integer.parseInt(addNumberOfStringsField.getText());// addNumberOfStringsField
		} catch (NumberFormatException e2) {
			Main.setErrorDialog(ERROR, ERROR, ERROR_PRICE);
			return;
		} catch (Exception e2) {
			Main.setErrorDialog(ERROR, ERROR, ERROR_EMPTY_FIELDS);
			return;
		}

		try {
			Guitar guitar = new Guitar(getPriceFieldValue(), getBrandFieldValue(), numOfStringsValue, TypeValue);
			allInstruments.add(guitar);
		} catch (Exception e1) {
			Main.setErrorDialog(ERROR, ERROR, e1.getMessage());
		}

	}

}
