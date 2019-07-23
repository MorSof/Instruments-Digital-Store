import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class MusicalInstrument implements InstrumentFunc<MusicalInstrument> {
	private Number price;
	private String brand;

	public MusicalInstrument(String brand, Number price) {
		setBrand(brand);
		setPrice(price);
	}

	public MusicalInstrument(Scanner scanner) {
		Number price;
		String priceString = "";
		String brand;

		try {
			priceString = scanner.next();
			price = checkIntOrDouble(priceString);
		} catch (InputMismatchException ex) {
			throw new InputMismatchException("Price not found!");
		}
		setPrice(price);
		scanner.nextLine();
		brand = scanner.nextLine();
		setBrand(brand);
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Number getPrice() {
		return price;
	}

	public void setPrice(Number price2) {

		if (price2.doubleValue() >= 0) {
			String price2Str = "" + price2;
			price2 = checkIntOrDouble(price2Str);
			this.price = price2;
		} else
			throw new InputMismatchException("Price must be a positive number!");

	}

	public Number checkIntOrDouble(String priceString) {
		if (priceString.contains(".0")) {
			int price = (int) Double.parseDouble(priceString);
			return price;
		} else if (priceString.contains(".")) {
			double price = Double.parseDouble(priceString);
			return price;
		} else {
			int price = Integer.parseInt(priceString);
			return price;		}

	}

	public boolean isValidType(String[] typeArr, String material) {
		for (int i = 0; i < typeArr.length; i++) {
			if (material.equalsIgnoreCase(typeArr[i])) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || !(o instanceof MusicalInstrument))
			return false;

		MusicalInstrument otherInstrument = (MusicalInstrument) o;

		return getPrice().doubleValue() == otherInstrument.getPrice().doubleValue()
				&& getBrand().equals(otherInstrument.getBrand());
	}

	@Override
	public String toString() {
		String priceString = "" + getPrice();
		return String.format("%-8s %-9s| Price: %7s,", getBrand(), getClass().getCanonicalName(), priceString);
	}
}
