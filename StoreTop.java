import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class StoreTop {

	private final String GO="Go!";	
	private HBox topBox = new HBox();
	private TextField search;
	private Button go = new Button(GO);

	public void setTopDesign() {
		setStoreTop();
	}

	public void setStoreTop() {		
		final int TOPBOX_PADDING=10,SEARCH_PADDING=5,SEARCH_LENGTH=500;
        final String SEARCH_PROMPT="Search...";		
		topBox.setSpacing(TOPBOX_PADDING);
		topBox.setPadding(new Insets(TOPBOX_PADDING, TOPBOX_PADDING, TOPBOX_PADDING, TOPBOX_PADDING));
		topBox.setAlignment(Pos.TOP_CENTER);
		search = new TextField();
		search.setPadding(new Insets(SEARCH_PADDING, SEARCH_LENGTH, SEARCH_PADDING, SEARCH_PADDING));
		search.setPromptText(SEARCH_PROMPT);
		topBox.getChildren().addAll(search, go);
	}

	public HBox getTopBox() {
		return topBox;
	}

	public void setTopBox(HBox topBox) {
		this.topBox = topBox;
	}

	public TextField getSearch() {
		return search;
	}

	public void setSearch(TextField search) {
		this.search = search;
	}

	public Button getGo() {
		return go;
	}

	public void setGo(Button go) {
		this.go = go;
	}

}
