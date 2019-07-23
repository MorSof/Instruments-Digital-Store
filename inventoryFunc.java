import java.util.ArrayList;

public interface inventoryFunc<T extends MusicalInstrument> {

	void addAllStringInstruments(ArrayList<T> copyOnlyStringArr, ArrayList<T> addOnlyStringArr);

	void addAllWindInstruments(ArrayList<T> copyOnlyWindArr, ArrayList<T> addOnlyWindArr);

	ArrayList<T> SortByBrandAndPrice(ArrayList<T> instrumentsArray);

	int binnarySearchByBrandAndPrice(ArrayList<T> instrumentsArray, String brand, Number price);

	int binnarySearchByBrand(ArrayList<T> instrumentsArray, String brand);

	void addInstrument(ArrayList<T> instrumentsArray, T singelInstrument);

	boolean removeInstrument(ArrayList<T> instrumentsArray, T singelInstrument);

	boolean removeAll(ArrayList<T> instrumentsArray);

}
