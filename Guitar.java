import java.util.Scanner;

public class Guitar extends StringInstrument {

	private String typeOfGuitar;
	private final String[] OptionalTypes = { "Acoustic", "Electric", "Classic" };

	public Guitar(Scanner s) throws IllegalArgumentException {
		super(s); //constructor using Scanner
		String typeOfGuitar = s.next();
		setTypeOfGuitar(typeOfGuitar);
	}

	public Guitar(Number price, String companyName, int numberOfStrings, String typeOfGuitar)
			throws IllegalArgumentException {
		super(companyName, price, numberOfStrings);
		setTypeOfGuitar(typeOfGuitar);
	}


	public String getTypeOfGuitar() {
		return typeOfGuitar;
	}

	public void setTypeOfGuitar(String typeOfGuitar) throws IllegalArgumentException {
		if (isTypeOfGuitarExists(typeOfGuitar)) {
			this.typeOfGuitar = typeOfGuitar.substring(0, 1).toUpperCase() + typeOfGuitar.substring(1).toLowerCase();
			checkNumOfStringsRange(this.typeOfGuitar);
		}
	}

	public boolean isTypeOfGuitarExists(String typeOfGuitar) throws IllegalArgumentException {
		// checking if type of guitar can be exists in the store
		for (int i = 0; i < OptionalTypes.length; i++) {
			if (typeOfGuitar.equalsIgnoreCase(OptionalTypes[i])) {
				return true;
			}
		}
		throw new IllegalArgumentException("We dont have -" + typeOfGuitar + "- type of guitar in our store");
	}

	public void checkNumOfStringsRange(String typeOfGuitar) throws IllegalArgumentException {
		// throwing Exception if number of strings is not in rang
		if ((typeOfGuitar.equalsIgnoreCase("Acoustic") || typeOfGuitar.equalsIgnoreCase("Classic"))
				&& getNumOfStrings() != 6) {
			throw new IllegalArgumentException(typeOfGuitar + " Guitar have 6 strings, not " + getNumOfStrings());
		} else if ((typeOfGuitar.equalsIgnoreCase("Electric")) && ((getNumOfStrings() > 8)
				|| (getNumOfStrings() < 6))) {
			throw new IllegalArgumentException(typeOfGuitar
					+ " Guitar number of strings is a number between 6 and 8, not " + getNumOfStrings());
		}
	}

	@Override
	public String toString() {
		return super.toString() + String.format(" Type: %7s", typeOfGuitar);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Guitar))
			return false;
		Guitar other = (Guitar) obj;
		if (typeOfGuitar == null) {
			if (other.typeOfGuitar != null)
				return false;
		} else if (!typeOfGuitar.equals(other.typeOfGuitar))
			return false;
		return true;
	}

	@Override
	public int compareTo(MusicalInstrument o) {
		if (getBrand().compareTo(o.getBrand()) > 0)
			return 1;
		else if (getBrand().compareTo(o.getBrand()) < 0)
			return -1;
		else {
			if (getPrice().doubleValue() > o.getPrice().doubleValue())
				return 1;
			else if (getPrice().doubleValue() < o.getPrice().doubleValue())
				return -1;
		}
		return 0;
	}

	@Override
	public Guitar clone() throws CloneNotSupportedException {
		return (Guitar) super.clone();
	}

}


//public class Guitar extends StringInstrument {
//
//	public static final String GUITAR_TYPE[] = { "Classic", "Acoustic", "Electric" };
//
//	public static final int CLASSIC = 0, ACOUSTIC = 1, ELECTRIC = 2;
//	public static final int CLASSIC_NUM_OF_STRINGS = 6, ACOUSTIC_NUM_OF_STRINGS = 6, ELEC_MAX_NUM_OF_STRINGS = 8,
//			ELEC_MIN_NUM_OF_STRINGS = 6;
//
//	private String type;
//
//	public Guitar(String brand, Number price, int numOfStrings, String type) {
//		super(brand, price, numOfStrings);
//		setType(type);
//	}
//
//	public Guitar(Scanner scanner) {
//		super(scanner);
//		int numOfStrings;
//		String type;
//
//		numOfStrings = getNumOfStrings();
//
//		scanner.nextLine();
//		type = scanner.nextLine();
//		setType(type);
//
////		switch (indexOfType()) {
////		case CLASSIC:
////			if (numOfStrings != CLASSIC_NUM_OF_STRINGS)
////				throw new InputMismatchException("Classic Guitars have 6 strings, not " + numOfStrings);
////			break;
////
////		case ACOUSTIC:
////			if (numOfStrings != ACOUSTIC_NUM_OF_STRINGS)
////				throw new InputMismatchException("Acoustic Guitars have 6 strings, not " + numOfStrings);
////			break;
////
////		case ELECTRIC:
////			if (numOfStrings < ELEC_MIN_NUM_OF_STRINGS || numOfStrings > ELEC_MAX_NUM_OF_STRINGS)
////				throw new InputMismatchException("Acoustic Guitars have 6 strings, not " + numOfStrings);
////			break;
////		}
//	}
//
//	private boolean isGuitarType(String input) {
//		return isValidType(GUITAR_TYPE, input);
//	}
//
//	private int indexOfType() {
//		for (int i = 0; i < GUITAR_TYPE.length; i++) {
//			if (getType().equals(GUITAR_TYPE[i])) {
//				return i;
//			}
//		}
//		return 0;
//	}
//
//	public String getType() {
//		return type;
//	}
//
//	public void setType(String type) {
//	
//		if (isGuitarType(type)) {
//			
//			this.type = type;
//		}
//			else
//			throw new InputMismatchException("Invalid guitar type: " + type);
//	
//	}
//	@Override
//	public void setNumOfStrings(int numOfStrings) {
//		if (numOfStrings < 0)
//			throw new IllegalArgumentException("Number of strings cannot be negative!");
//		else
//			super.setNumOfStrings(numOfStrings);
//	}
//
//	@Override
//	public boolean equals(Object o) {
//		if (!super.equals(o))
//			return false;
//
//		if (!(o instanceof Guitar))
//			return false;
//
//		return getType().equals(((Guitar) o).getType());
//	}
//
//	@Override
//	public String toString() {
//		return super.toString() + String.format(" Type: %7s", getType().toString());
//	}
//

//
//}
