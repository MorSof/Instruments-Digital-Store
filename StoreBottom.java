import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class StoreBottom {

	private final String ADD = "Add", DELETE = "Delete", CLEAR = "Clear";
	private final int INITIAL = 0;

	private HBox buttonBox = new HBox();
	private Button add = new Button(ADD);
	private Button delete = new Button(DELETE);
	private Button clear = new Button(CLEAR);
	private HBox MovingLableBox = new HBox();
	private Timeline timeClock = new Timeline();
	private VBox bottomBox = new VBox();

	public StoreBottom() {
		super();
		setStoreBottom();
	}

	public void setStoreBottom() {

		final int SPACING = 20, BUTTONBOX_PADDING = 10;

		buttonBox.setSpacing(SPACING);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setPadding(new Insets(BUTTONBOX_PADDING, INITIAL, INITIAL, INITIAL));

	}

	public HBox getButtonBox() {
		return buttonBox;
	}

	public void setButtonBox(HBox buttonBox) {
		this.buttonBox = buttonBox;
	}

	public Button getAdd() {
		return add;
	}

	public void setAdd(Button add) {
		this.add = add;
	}

	public Button getDelete() {
		return delete;
	}

	public void setDelete(Button delete) {
		this.delete = delete;
	}

	public Button getClear() {
		return clear;
	}

	public void setClear(Button clear) {
		this.clear = clear;
	}

	public HBox getMovingLableBox() {
		return MovingLableBox;
	}

	public void setMovingLableBox(HBox movingLableBox) {
		MovingLableBox = movingLableBox;
	}

	public Timeline getTimeClock() {
		return timeClock;
	}

	public void setTimeClock(Timeline timeClock) {
		this.timeClock = timeClock;
	}

	public VBox getBottomBox() {
		return bottomBox;
	}

	public void setBottomBox(VBox bottomBox) {
		this.bottomBox = bottomBox;
	}

	// Handling the moving red text
	public HBox setBottomMovingLable() {
		final int LABELBOX_PADDING = 70, DURATION = 1;
		final String FONT_BOLD = "-fx-font-weight: bold;",
				MOVING_TEXT = " Afeka Instruments Music Store $$$ ON SALE!!! $$$ Guitars,Basses,Flutes,Saxophones,and more!",
				HOUR_PATTERN = "HH:mm:ss";
		MovingLableBox.setAlignment(Pos.CENTER);
		MovingLableBox.setPadding(new Insets(INITIAL, INITIAL, LABELBOX_PADDING, INITIAL));
		LocalDate date = LocalDate.now();
		String dateString = date.toString();
		Text bottomTextDisplay = new Text();
		bottomTextDisplay.setStyle(FONT_BOLD);
		bottomTextDisplay.fillProperty();
		bottomTextDisplay.setFill(Color.RED);
		timeClock.getKeyFrames().add(new KeyFrame(Duration.seconds(DURATION), e -> {
			bottomTextDisplay.setText(
					dateString + " " + LocalTime.now().format(DateTimeFormatter.ofPattern(HOUR_PATTERN)) + MOVING_TEXT);
		}));
		timeClock.setCycleCount(Animation.INDEFINITE);
		timeClock.play();
		MovingLableBox.getChildren().add(bottomTextDisplay);
		TextTransition(bottomTextDisplay);
		return MovingLableBox;
	}

	// the transition logic of the Text
	public void TextTransition(Text movingLable) {
		final int LINE_LENGTH = 700, LINE_DURATION = 10;
		Line line = new Line(INITIAL, INITIAL, LINE_LENGTH, INITIAL);
		PathTransition transition = new PathTransition();
		transition.setNode(movingLable);
		transition.setDuration(Duration.seconds(LINE_DURATION));
		transition.setPath(line);
		transition.setCycleCount(PathTransition.INDEFINITE);
		transition.setAutoReverse(true);
		movingLable.setOnMouseEntered(e -> transition.pause());
		movingLable.setOnMouseExited(e -> transition.play());
		transition.play();
	}
}
