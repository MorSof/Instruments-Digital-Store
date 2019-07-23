import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AfekaInstruments {

	// instruments1b.txt

	public static Scanner input = new Scanner(System.in);

	// public static void main(String[] args) throws CloneNotSupportedException {
	// ArrayList<MusicalInstrument> allInstruments = new
	// ArrayList<MusicalInstrument>();
	// File file = getInstrumentsFileFromUser();
	// loadInstrumentsFromFile(file, allInstruments);
	// if (allInstruments.size() == 0) {
	// System.out.println("There are no instruments in the store currently");
	// return;
	// }
	// printInstruments(allInstruments);
	// int different = getNumOfDifferentElements(allInstruments);
	// System.out.println("\n\nDifferent Instruments: " + different);
	// MusicalInstrument mostExpensive = getMostExpensiveInstrument(allInstruments);
	// System.out.println("\n\nMost Expensive Instrument:\n" + mostExpensive);
	// startInventoryMenu(allInstruments);
	//
	// }

	public static File getInstrumentsFileFromUser(String filepath) throws FileNotFoundException {
		File file = new File(filepath);
		if (file.exists())
			return file;
		else
			return null;
	}

	public static void loadInstrumentsFromFile(File file, ArrayList<MusicalInstrument> allInstruments) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
			addAllInstruments(allInstruments, loadGuitars(scanner));
			addAllInstruments(allInstruments, loadBassGuitars(scanner));
			addAllInstruments(allInstruments, loadFlutes(scanner));
			addAllInstruments(allInstruments, loadSaxophones(scanner));
		} catch (InputMismatchException | IllegalArgumentException ex) {
			System.err.println("\n" + ex.getMessage());
			System.exit(1);
		} catch (FileNotFoundException ex) {
			System.err.println("\nFile Error! File was not found");
			System.exit(2);
		} finally {
			scanner.close();
		}
		System.out.println("\nInstruments loaded from file successfully!\n");
	}

	public static ArrayList<MusicalInstrument> loadGuitars(Scanner scanner) {
		int numOfInstruments = scanner.nextInt();
		ArrayList<MusicalInstrument> guitars = new ArrayList<MusicalInstrument>(numOfInstruments);
		for (int i = 0; i < numOfInstruments; i++)
			guitars.add(new Guitar(scanner));
		return guitars;
	}

	public static ArrayList<MusicalInstrument> loadBassGuitars(Scanner scanner) {
		int numOfInstruments = scanner.nextInt();
		ArrayList<MusicalInstrument> bassGuitars = new ArrayList<MusicalInstrument>(numOfInstruments);
		for (int i = 0; i < numOfInstruments; i++)
			bassGuitars.add(new Bass(scanner));
		return bassGuitars;
	}

	public static ArrayList<MusicalInstrument> loadFlutes(Scanner scanner) {
		int numOfInstruments = scanner.nextInt();
		ArrayList<MusicalInstrument> flutes = new ArrayList<MusicalInstrument>(numOfInstruments);
		for (int i = 0; i < numOfInstruments; i++)
			flutes.add(new Flute(scanner));
		return flutes;
	}

	public static ArrayList<MusicalInstrument> loadSaxophones(Scanner scanner) {
		int numOfInstruments = scanner.nextInt();
		ArrayList<MusicalInstrument> saxophones = new ArrayList<MusicalInstrument>(numOfInstruments);
		for (int i = 0; i < numOfInstruments; i++)
			saxophones.add(new Saxophone(scanner));
		return saxophones;
	}

	public static void addAllInstruments(ArrayList<MusicalInstrument> instruments,
			ArrayList<MusicalInstrument> moreInstruments) {
		for (int i = 0; i < moreInstruments.size(); i++) {
			instruments.add(moreInstruments.get(i));
		}
	}

	public static void printInstruments(ArrayList<MusicalInstrument> instruments) {
		for (int i = 0; i < instruments.size(); i++)
			System.out.println(instruments.get(i));
	}

	public static int getNumOfDifferentElements(ArrayList<MusicalInstrument> instruments) {
		int numOfDifferentInstruments;
		ArrayList<MusicalInstrument> differentInstruments = new ArrayList<MusicalInstrument>();
		System.out.println();
		for (int i = 0; i < instruments.size(); i++) {
			if (!differentInstruments.contains((instruments.get(i)))) {
				differentInstruments.add(instruments.get(i));
			}
		}
		if (differentInstruments.size() == 1)
			numOfDifferentInstruments = 0;
		else
			numOfDifferentInstruments = differentInstruments.size();
		return numOfDifferentInstruments;
	}

	public static MusicalInstrument getMostExpensiveInstrument(ArrayList<MusicalInstrument> instruments) {
		Number maxPrice = 0;
		MusicalInstrument mostExpensive = instruments.get(0);
		for (int i = 0; i < instruments.size(); i++) {
			MusicalInstrument temp = instruments.get(i);
			if (temp.getPrice().doubleValue() > maxPrice.doubleValue()) {
				maxPrice = temp.getPrice();
				mostExpensive = temp;
			}
		}
		return mostExpensive;
	}

	/*
	 * לאחר הפעלת התכנית, בחירת קובץ לטעינה והדפסת נתונים על המסך שהיה עד כה הוחלט,
	 * startInventoryMenu ואתם צריכים לעשות זאת, בנוסף לזה לכתוב ולהפעיל מתודה
	 * שמקבלת מערך של כלי נגינה שהוטענו מהקובץ ובונה תפריט לניהול מלאי
	 */

	// instruments1b.txt

	public static void startInventoryMenu(ArrayList<MusicalInstrument> instrumentsArray) {
		final String ONE = "1", TWO = "2", THREE = "3", FOUR = "4", FIVE = "5", SIX = "6", SEVEN = "7";
		final int NOT_EXISTS = -1;
		int index = 0;
		String brandFromUser;
		Number priceFromUser;
		AfekaInventory<MusicalInstrument> inventory = new AfekaInventory<>();
		boolean run = true;
		String userChoice;

		do {

			System.out.print(inventory.StringMenu());
			userChoice = input.next();
			System.out.println();

			switch (userChoice) {

			case ONE: // add all string instrument
				inventory.addAllStringInstruments(instrumentsArray, inventory.getMyInventory());
				System.out.println("All String Instruments Added Successfully!");
				break;

			case TWO: // add all wind instruments
				inventory.addAllWindInstruments(instrumentsArray, inventory.getMyInventory());
				System.out.println("All Wind Instruments Added Successfully!");
				break;

			case THREE: // sort by brand and price
				inventory.setMyInventory(inventory.SortByBrandAndPrice(inventory.getMyInventory()));
				System.out.println("Instruments Sorted Successfully!");
				break;

			case FOUR:// search by brand and price. if brand not found- printing not found.
				// if price not found it will return the closest price instrument
				System.out.println("SEARCH INSTRUMENT: ");
				System.out.print("Brand: ");
				brandFromUser = input.next();
				System.out.print("Price: ");
				priceFromUser = getPriceFromUser(new Scanner(System.in));// exception dealing
				if (priceFromUser.doubleValue() == -1)
					break; // return to inventory menu with error massage
				else// Search by brand and price. can return the index of instrument that have the
					// closest price if price not found
					index = inventory.binnarySearchByBrandAndPrice(inventory.getMyInventory(), brandFromUser,
							priceFromUser);
				if (index == NOT_EXISTS)
					System.out.println("Instrument Not Found!");
				else
					System.out.println("Resulte: \n      " + inventory.getMyInventory().get(index).toString());
				break;

			case FIVE: // Delete single instrument by brand and price.
						// if brand not found printing not found. if price not found it ask you if you
						// want to delete the closest price instrument
				System.out.println("DELETE INSTRUMENT: ");
				System.out.print("Brand: ");
				brandFromUser = input.next();
				System.out.print("Price: ");
				priceFromUser = getPriceFromUser(new Scanner(System.in)); // exception dealing
				if (priceFromUser.doubleValue() == -1) // price from user is negative
					break;// return to inventory menu with error massage
				else // Search by brand and price. can return the index of instrument that have the
						// closest price if price not found
					index = inventory.binnarySearchByBrandAndPrice(inventory.getMyInventory(), brandFromUser,
							priceFromUser);
				if (index == NOT_EXISTS)
					System.out.println("\nInstrument is not exists in the inventory");
				else {
					System.out.println("Resulte: \n      " + inventory.getMyInventory().get(index).toString());
					boolean yes = yesNoOption(new Scanner(System.in));
					if (yes) {
						boolean instrumentRemoved = inventory.removeInstrument(inventory.getMyInventory(),
								inventory.getMyInventory().get(index));
						if (instrumentRemoved) {
							System.out.println("\nInstrument Deleted Successfully!");
						}
					} else {
						System.out.println("\nInstrument has not been Deleted");
					}
				}
				break;

			case SIX: // remove all instrument from inventory
				System.out.println("DELETE ALL INSTRUMENTS: ");
				boolean yes = yesNoOption(input);
				if (yes) {
					boolean inventoryCleard = inventory.removeAll(inventory.getMyInventory());
					if (inventoryCleard) {
						System.out.println("\nAll Instruments Deleted Successfully!");
					}
				} else {
					System.out.println("\nInstruments has not been Deleted");
				}
				break;

			case SEVEN: // print inventory
				System.out.println(inventory.inventoryHeadline());
				if (inventory.getMyInventory().isEmpty()) {
					System.out.println("There Are No Instruments To Show\n");
					System.out.println(inventory.toString());
				} else {
					printInstruments(inventory.getMyInventory());
					System.out.println("\n" + inventory.toString());
				}
				break;

			default: // if user input is not a number between 1-7
				System.out.println("Finished!");
				run = false;
				break;
			}
		} while (run);

	}

	public static boolean yesNoOption(Scanner userInput) {// as long as not Y or N try again
		String yesNo;
		do {
			System.out.print("Are You Sure?(Y/N): ");
			yesNo = userInput.next();
			if (yesNo.equalsIgnoreCase("Y"))
				return true;
			else if (yesNo.equalsIgnoreCase("N"))
				return false;
			System.out.println("Your answer is not 'Y' OR 'N' , Please Try Again:");
		} while (!yesNo.equalsIgnoreCase("Y") && !yesNo.equalsIgnoreCase("N"));
		return false;
	}

	public static Number getPriceFromUser(Scanner input) { // return priceFromUser if no exceptions
															// return default 0 if InputMismatchException
															// return -1 if priceFromUser is negative
		final int DEFAULT_PRICE_SEARCH = 0;
		Number priceFromUser;
		try {
			priceFromUser = input.nextDouble();
			if (priceFromUser.doubleValue() < 0) {
				throw new IllegalArgumentException("Price must be a positive number! returning to inventory menu...");
			}
			return priceFromUser;
		} catch (InputMismatchException e) {
			return DEFAULT_PRICE_SEARCH;
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
			return priceFromUser = -1;
		}
	}

}
