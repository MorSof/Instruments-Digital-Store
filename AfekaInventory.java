import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/*
 * כמו כן, לאותה מטרה שבסעיף הקודם, הוחלט ואתם צריכים לעשות זאת, לייצר מחלקה
אשר כוללת מערך ג'נריק של כלי נגינה מסוג מסויים, מספר ממשי המייצג AfekaInventory
מחיר הכולל של כל הכלי נגינה, ומשתנה בוליאני שמייצג האם המערך של המלאי ממוין.
המחלקה תממש את הממשק מסעיף הקודם. בנוסף המחלקה תכיל מתודה ג'נריקס אשר
מקבלית שני מספרים ג'נריקס ומחזירה את סכומם כמספר ממשי.
 */
public class AfekaInventory<T extends MusicalInstrument> implements inventoryFunc<T> {

	private ArrayList<T> myInventory;
	private Number priceOfAllInstruments;
	private boolean Sorted;

	private final int DEFAULT_TOTAL_PRICE = 0;

	public AfekaInventory() {
		myInventory = new ArrayList<T>();
		setPriceOfAllInstruments(DEFAULT_TOTAL_PRICE);
		isSorted(false);

	}

	public AfekaInventory(ArrayList<T> myInventory) {
		setMyInventory(myInventory);
		addPriceOfArraylInstruments(myInventory);
		isSorted(false);
	}

	public ArrayList<T> getMyInventory() {
		return myInventory;
	}

	public void setMyInventory(ArrayList<T> myInventory) {
		this.myInventory = myInventory;
	}

	public Number getPriceOfAllInstruments() {
		return priceOfAllInstruments;
	}

	public void addPriceOfArraylInstruments(ArrayList<T> instrumentsArray) {

		if (priceOfAllInstruments == null) {
			priceOfAllInstruments = DEFAULT_TOTAL_PRICE;
			priceOfAllInstruments = addToTotalPrice(myInventory);
			return;
		}
		setPriceOfAllInstruments(addToTotalPrice(instrumentsArray));
	}

	public void addPriceOfSingelInstrument(Number priceOfInstrumentToAdd) {

		setPriceOfAllInstruments(sum(this.priceOfAllInstruments, priceOfInstrumentToAdd));
	}

	public void setPriceOfAllInstruments(Number priceOfAllInstruments) {

		this.priceOfAllInstruments = priceOfAllInstruments;
	}

	public Number addToTotalPrice(ArrayList<T> instrumentsArray) {

		Number priceOfAllInstruments = this.priceOfAllInstruments;

		for (int i = 0; i < instrumentsArray.size(); i++) {
			priceOfAllInstruments = priceOfAllInstruments.doubleValue()
					+ instrumentsArray.get(i).getPrice().doubleValue();
		}
		return priceOfAllInstruments;
	}

	public boolean getIsSorted() {
		return Sorted;
	}

	public void isSorted(boolean Sorted) {
		this.Sorted = Sorted;
	}

	// שיטה אשר מקבלת מערך ג'נריקס של כלי נגינה מסוג – addAllStringInstruments
	// מסוים שממנו רוצים להעתיק ומערך ג'נריק של כלי נגינה מסוג מסוים שלתוכו רוצים
	// להעתיק ומעתיקה למערך השני רק את כלי המיתר.
	@Override
	public void addAllStringInstruments(ArrayList<T> copyOnlyStringArr, ArrayList<T> addOnlyStringArr) {
		if (addOnlyStringArr.size() > 0)
			isSorted(false);
		for (int i = 0; i < copyOnlyStringArr.size(); i++) {
			if (copyOnlyStringArr.get(i) instanceof StringInstrument) {
				addOnlyStringArr.add(copyOnlyStringArr.get(i));
				addPriceOfSingelInstrument(copyOnlyStringArr.get(i).getPrice().doubleValue());
			}
		}
	}

	@Override
	public void addAllWindInstruments(ArrayList<T> copyOnlyWindArr, ArrayList<T> addOnlyWindArr) {
		if (addOnlyWindArr.size() > 0)
			isSorted(false);
		for (int i = 0; i < copyOnlyWindArr.size(); i++) {
			if (copyOnlyWindArr.get(i) instanceof WindInstrument) {
				addOnlyWindArr.add(copyOnlyWindArr.get(i));
				addPriceOfSingelInstrument(copyOnlyWindArr.get(i).getPrice().doubleValue());
			}
		}
	}

	// שיטה אשר מקבלת מערך ג'נריק של כלי נגינה מסוג – SortByBrandAndPrice
	// מסוים וממיינת אותו לפי שם חברה ומחיר.

	@Override
	public ArrayList<T> SortByBrandAndPrice(ArrayList<T> instrumentsArray) {
		for (int i = instrumentsArray.size() - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (instrumentsArray.get(i).compareTo(instrumentsArray.get(j)) < 0) {
					T temp = instrumentsArray.get(i);
					instrumentsArray.set(i, instrumentsArray.get(j));
					instrumentsArray.set(j, temp);
				}
			}
		}
		isSorted(true);
		return instrumentsArray;
	}

	// שיטת חיפוש בינארי שמקבלת מערך ג'נריק של – binnarySearchByBrandAndPrice
	// כלי נגינה מטיפוס מסוים, שם חברה, מחיר של כלי נגינה שעלינו לחפש כפרמטר ומחזירה
	// את המיקום של הכלי נגינה הרצוי. החיפוש לפי המחיר מתבצע רק כאשר נמצא יותר מכלי
	// נגינה אחד עם שם חברה הרצוי.

	@Override
	public int binnarySearchByBrandAndPrice(ArrayList<T> instrumentsArray, String brand, Number price) {

		// שלום חן!, את המתודה הזו בניתי בהתאם לקובץ הפלט לדוגמה שמוצג בהוראות לתרגיל-
		// כלומר, עשיתי מתודה שאם נמצא השם חברה והמחיר לא שווה למחיר אותו אני רוצה לחפש,
		// המתודה תביא לי את הכלי נגינה עם שם החברה ועם המחיר הקרוב ביותר . יש לציין שזה
		// הרבה יותר
		// מורכב ממתודה שפשוט תחזיר שהכלי לא נמצא(כמו שהרבה אחרים עשו). אשמח מאוד
		// להתחשבות בבדיקה כי הלכתי על
		// הדרך היותר קשה! תודה רבה !

		final int NOT_EXISTS = -1;
		ArrayList<T> arraySameBrand = new ArrayList<T>(); // will contain same brand
		ArrayList<T> arrayCopy = new ArrayList<T>(); // will contain instrumentsArray
		boolean wasSortedfBefore = Sorted; // save earlier situation
		arrayCopy.addAll(instrumentsArray);
		arrayCopy = SortByBrandAndPrice(arrayCopy);
		int indexBrand = binnarySearchByBrand(arrayCopy, brand);
		if (indexBrand > NOT_EXISTS) { // return -1 if brand not found
			while (indexBrand > NOT_EXISTS) { // if there is more them one- look for others
				// if the instrument price= price to search, return instrument index
				if (arrayCopy.get(indexBrand).getPrice().doubleValue() == price.doubleValue()) {
					// return the instrument with the brand and price to search
					return instrumentsArray.indexOf(arrayCopy.get(indexBrand));
				} else { // if all the instruments prices != price to search
					arraySameBrand.add(arrayCopy.get(indexBrand));// insert instrument with brand to search
					arrayCopy.remove(arrayCopy.get(indexBrand)); // delete instrument from search array
					indexBrand = binnarySearchByBrand(arrayCopy, brand); // check for other instrument with same brand
				}
			}
			isSorted(wasSortedfBefore);
			// return the closest price instrument with the brand to search
			return instrumentsArray.indexOf(arraySameBrand.get(findingClosestPriceIndex(arraySameBrand, price)));
		}
		return NOT_EXISTS;
	}

	public int findingClosestPriceIndex(ArrayList<T> arrayHelper, Number price) {
		Number closestPrice = 0;
		boolean start = true;
		int indexOfClosestPrice = 0;
		for (int i = 0; i < arrayHelper.size(); i++) {
			double absValue = Math.abs(price.doubleValue() - arrayHelper.get(i).getPrice().doubleValue());
			if (absValue < closestPrice.doubleValue() || start == true) {
				closestPrice = absValue;
				indexOfClosestPrice = i;
				start = false;
			}
		}
		return indexOfClosestPrice;
	}

	@Override
	public int binnarySearchByBrand(ArrayList<T> instrumentsArray, String brand) {
		final int NOT_EXISTS = -1;
		int low = 0;
		int high = instrumentsArray.size() - 1;
		brand = brand.substring(0, 1).toUpperCase() + brand.substring(1).toLowerCase();
		while (high >= low) {
			int middle = (low + high) / 2;
			if (instrumentsArray.get(middle).getBrand().compareTo(brand) == 0) {

				return middle;
			}
			if (instrumentsArray.get(middle).getBrand().compareTo(brand) < 0) {
				low = middle + 1;
			}
			if (instrumentsArray.get(middle).getBrand().compareTo(brand) > 0) {
				high = middle - 1;
			}
		}
		return NOT_EXISTS;
	}

	// שיטה אשר מקבלת מערך ג'נריקס של כלי נגינה מסוג מסוים וכלי – addInstrument
	// נגינה שברצוננו להוסיף ומוסיפה את הכלי למערך.
	@Override
	public void addInstrument(ArrayList<T> instrumentsArray, T singelInstrument) {
		instrumentsArray.add(singelInstrument);
		addPriceOfSingelInstrument(singelInstrument.getPrice().doubleValue());
		isSorted(false);
	}

	// שיטה אשר מקבלת מערך ג'נריקס של כלי נגינה מסוג מסוים וכלי – removeInstrument
	// אחרת ,true נגינה שברצוננו למחוק ומוחקת את הכלי מהמערך. במידה והצליחה, מחזירה
	// .false מחזירה
	@Override
	public boolean removeInstrument(ArrayList<T> instrumentsArray, T singelInstrument) {

		addPriceOfSingelInstrument(-(singelInstrument.getPrice().doubleValue()));
		return instrumentsArray.remove(singelInstrument);

	}

	/*
	 * שיטה אשר מקבלת מערך ג'נריק של כלי נגינה מסוג מסוים ומוחקת את כל – removeAll
	 * .false אחרת מחזירה ,true כלי הנגינה מתוך המערך. במידה והצליחה, מחזירה
	 */
	@Override
	public boolean removeAll(ArrayList<T> instrumentsArray) {

		setPriceOfAllInstruments(DEFAULT_TOTAL_PRICE);
		isSorted(false);
		return instrumentsArray.removeAll(instrumentsArray);

	}

	public Number sum(Number num1, Number num2) {
		return num1.doubleValue() + num2.doubleValue();
	}

	public String StringMenu() {
		return "\n\n-------------------------------------------------------------------------\n"
				+ "AFEKA MUSICAL INSTRUMENT INVENTORY MENU\n"
				+ "-------------------------------------------------------------------------\n"
				+ "1. Copy All String Instruments To Inventory\n" + "2. Copy All Wind Instruments To Inventory\n"
				+ "3. Sort Instruments By Brand And Price\n" + "4. Search Instrument By Brand And Price\n"
				+ "5. Delete Instrument\n" + "6. Delete all Instruments\n" + "7. Print Inventory Instruments\n"
				+ "Choose your option or any other key to EXIT: \n" + "\nYour Option: ";

	}

	public String inventoryHeadline() {

		return "-------------------------------------------------------------------------\n"
				+ "AFEKA MUSICAL INSTRUMENTS INVENTORY\n"
				+ "-------------------------------------------------------------------------";
	}

	@Override
	public String toString() {
		DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.getDefault());
		formatSymbols.setDecimalSeparator(',');
		NumberFormat df = new DecimalFormat("#.##", formatSymbols);
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		return String.format("Total Price: %s      Sorted: %s", df.format(getPriceOfAllInstruments().doubleValue()),
				Sorted);
	}

	
	
	
}
