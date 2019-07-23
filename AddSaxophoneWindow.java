import java.util.ArrayList;

public class AddSaxophoneWindow extends AddWindowBasic {

	//creating Saxophone window class and handling exceptions
	public void createSaxophone(ArrayList<MusicalInstrument> allInstruments) {
		try {
		createBasic(allInstruments);
		}catch (Exception e) {
			return;
		}
		try {
			Saxophone saxophone = new Saxophone(getBrandFieldValue(), getPriceFieldValue());
		allInstruments.add(saxophone);
		} catch (Exception e1) {
			Main.setErrorDialog(ERROR, ERROR, e1.getMessage());
		}
		
	}


}
