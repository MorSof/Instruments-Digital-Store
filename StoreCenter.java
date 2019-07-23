import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class StoreCenter {

	private GridPane centerGrid = new GridPane();
	private TextField priceField = new TextField();
	private TextField brandField = new TextField();
	private TextField typeField = new TextField();

	public StoreCenter() {
		super();
		setCenter();
	}

	public GridPane getCenterGrid() {
		return centerGrid;
	}

	public void setCenterGrid(GridPane centerGrid) {
		this.centerGrid = centerGrid;
	}

	public TextField getPriceField() {
		return priceField;
	}

	public void setPriceField(TextField priceField) {
		this.priceField = priceField;
	}

	public TextField getBrandField() {
		return brandField;
	}

	public void setBrandField(TextField brandField) {
		this.brandField = brandField;
	}

	public TextField getTypeField() {
		return typeField;
	}

	public void setTypeField(TextField typeField) {
		this.typeField = typeField;
	}

	public void setCenter() {
		final int INITIAL = 0, FIRST = 1, SECOND = 2, PADDING = 10;
		final String TYPE_PROMPT = "Type: ", BRAND_PROMPT = "Brand..", PRICE_PROMPT = "Price..", TYPE = "Type: ",
				BRAND = "Brand: ", PRICE = "Price: ";
		centerGrid.setPadding(new Insets(PADDING, INITIAL, PADDING, INITIAL));
		centerGrid.setVgap(PADDING);
		centerGrid.setHgap(PADDING);
		centerGrid.setAlignment(Pos.CENTER);

		Label type = new Label(TYPE);
		GridPane.setConstraints(type, INITIAL, INITIAL);
		Label brand = new Label(BRAND);
		GridPane.setConstraints(brand, INITIAL, FIRST);
		Label price = new Label(PRICE);
		GridPane.setConstraints(price, INITIAL, SECOND);

		typeField.setPromptText(TYPE_PROMPT);
		brandField.setPromptText(BRAND_PROMPT);
		priceField.setPromptText(PRICE_PROMPT);

		GridPane.setConstraints(typeField, FIRST, INITIAL);
		GridPane.setConstraints(brandField, FIRST, FIRST);
		GridPane.setConstraints(priceField, FIRST, SECOND);

		centerGrid.getChildren().addAll(type, typeField, brand, brandField, price, priceField);

	}

}
