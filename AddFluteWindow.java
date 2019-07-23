import java.util.ArrayList;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class AddFluteWindow extends AddWindowBasic {

	private Label materialLabel = new Label(MATERIAL);
	private Label typeLabel = new Label(TYPE);
	private ComboBox<String> fluteMaterialComboBox = new ComboBox<String>();
	private ComboBox<String> fluteTypeComboBox = new ComboBox<String>();

	public Label getMaterialLabel() {
		return materialLabel;
	}

	public void setMaterialLabel(Label materialLabel) {
		this.materialLabel = materialLabel;
	}

	public Label getTypeLabel() {
		return typeLabel;
	}

	public void setTypeLabel(Label typeLabel) {
		this.typeLabel = typeLabel;
	}

	public ComboBox<String> getFluteMaterialComboBox() {
		return fluteMaterialComboBox;
	}

	public void setFluteMaterialComboBox(ComboBox<String> fluteMaterialComboBox) {
		this.fluteMaterialComboBox = fluteMaterialComboBox;
	}

	public ComboBox<String> getFluteTypeComboBox() {
		return fluteTypeComboBox;
	}

	public void setFluteTypeComboBox(ComboBox<String> fluteTypeComboBox) {
		this.fluteTypeComboBox = fluteTypeComboBox;
	}

	//creating Flute window class and handling exceptions
	public void createFlute(ArrayList<MusicalInstrument> allInstruments) {
		String MaterialValue;
		String TypeValue;

		try {
			createBasic(allInstruments);
		} catch (Exception e) {
			return;
		}

		try {
			MaterialValue = fluteMaterialComboBox.getValue();// fluteMaterialComboBox
			TypeValue = fluteTypeComboBox.getValue();// fluteTypeComboBox
			if (TypeValue.equals(null) || MaterialValue.equals(null)) {
				throw new Exception();
			}
		} catch (NumberFormatException e2) {
			Main.setErrorDialog(ERROR, ERROR, ERROR_PRICE);
			return;
		} catch (Exception e2) {
			Main.setErrorDialog(ERROR, ERROR, ERROR_EMPTY_FIELDS);
			return;
		}

		try {
			Flute flute = new Flute(getBrandFieldValue(), getPriceFieldValue(), MaterialValue, TypeValue);
			allInstruments.add(flute);
		} catch (Exception e1) {
			Main.setErrorDialog(ERROR, ERROR, e1.getMessage());
		}

	}

}
