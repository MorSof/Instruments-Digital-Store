import java.util.ArrayList;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddBassWindow extends AddWindowBasic {

	private Label numberOfStringsLabel = new Label(NUMBER_OF_STRINGS);
	private TextField addNumberOfStringsField = new TextField();
	private Label type = new Label(FRETLESS);
	private CheckBox fretlessBox = new CheckBox();

	public Label getNumberOfStringsLabel() {
		return numberOfStringsLabel;
	}

	public void setNumberOfStringsLabel(Label numberOfStringsLabel) {
		this.numberOfStringsLabel = numberOfStringsLabel;
	}

	public TextField getAddNumberOfStringsField() {
		return addNumberOfStringsField;
	}

	public void setAddNumberOfStringsField(TextField addNumberOfStringsField) {
		this.addNumberOfStringsField = addNumberOfStringsField;
	}

	public Label getType() {
		return type;
	}

	public void setType(Label type) {
		this.type = type;
	}

	public CheckBox getFretlessBox() {
		return fretlessBox;
	}

	public void setFretlessBox(CheckBox fretlessBox) {
		this.fretlessBox = fretlessBox;
	}

	//creating bass window class and handling exceptions
	public void createBass(AddBassWindow bassWindow, ArrayList<MusicalInstrument> allInstruments) {
		final int INITIAL = 0;
		int numOfStringsValue = INITIAL;
		boolean isFratless = bassWindow.getFretlessBox().isSelected();

		try {
			createBasic(allInstruments);
		} catch (Exception e) {
			return;
		}
		try {
			numOfStringsValue = Integer.parseInt(bassWindow.getAddNumberOfStringsField().getText());// addNumberOfStringsField
		} catch (NumberFormatException e2) {
			Main.setErrorDialog(ERROR, ERROR, NUMOFSTRINGS_ERROR);
			return;
		} catch (Exception e2) {
			Main.setErrorDialog(ERROR, ERROR, ERROR_EMPTY_FIELDS);
			return;
		}
		try {
			Bass bass = new Bass(getBrandFieldValue(), getPriceFieldValue(), numOfStringsValue, isFratless);
			allInstruments.add(bass);
		} catch (Exception e1) {
			Main.setErrorDialog(ERROR, ERROR, e1.getMessage());
		}
	}

}
