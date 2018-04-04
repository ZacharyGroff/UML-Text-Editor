import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.util.Duration;

/**
 * @
 *
 */
public class View extends Application {

	Stage gui;
	BorderPane layout;
	Pane canvas;
	VBox menu;
	Text text;
	View ref;

	double orgSceneX, orgSceneY;
	double orgTranslateX, orgTranslateY;
	double offsetX, offsetY;
	double newTranslateX, newTranslateY;

	Button ClassDiagram;
	Button PackageDiagram;
	Button DependencyArrow;
	Button AggregationArrow;
	Button CompositionArrow;
	Button GeneralizationArrow;
	Button Selector;
	Timeline fadeIn, fadeOut, hold;

	int state;

	public void begin() throws Exception {
		launch();
	}

	public void start(Stage primaryStage) throws Exception {

		gui = primaryStage;

		gui.setTitle("UML Text Editor");
		gui.setHeight(768);
		gui.setWidth(1024);

		// create group
		layout = new BorderPane();
		menu = new VBox();
		menuGenerate(menu);
		canvas = new Pane();
		text = new Text();
		text.setOpacity(0);
		text.setStyle("-fx-font: 24 default");

		fadeIn = new Timeline();
		fadeOut = new Timeline();
		hold = new Timeline();

		layout.setTop(menu);
		layout.setCenter(canvas);
		layout.setBottom(text);

		state = 0;

		ref = this;

		// create Scene
		Scene scene = new Scene(layout);
		gui.setScene(scene);

		// create event handler for button a
		ClassDiagram.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				UMLClass two = new UMLClass(ref, canvas, new Text(), new TextArea(), new TextArea(), new TextArea());
				canvas.getChildren().add(two);
				System.out.println(canvas.getChildren());
				text.setText("New Class added");
				fadeText();

				for (Node i : canvas.getChildren()) {
					if (UMLClass.class.isInstance(i))
						((UMLClass) i).setDrag(true);
				}
			}
		});


		GeneralizationArrow.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				text.setText("Now in line mode: Click on the parent, followed by the child");
				text.setOpacity(1);
				GenLine line = new GenLine(canvas);
				for (Node i : canvas.getChildren()) {
					if (UMLClass.class.isInstance(i)) {
						((UMLClass) i).setDrag(false);
						((UMLClass) i).setPoLine(line);
					}
				state = 1;
				}
			}
		});

		Selector.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				state = 0;
				text.setText("Now in Select mode");
				fadeText();

				for (Node i : canvas.getChildren()) {
					if (UMLClass.class.isInstance(i))
						((UMLClass) i).setDrag(true);
				}
			}
		});

		gui.show();
	}

	public void setDragable() {

		for (Node i : canvas.getChildren()) {
			if (UMLClass.class.isInstance(i))
				((UMLClass) i).setDrag(true);
		}

	}

	private void menuGenerate(VBox menu) {
		menu.getChildren().addAll(CreateMenuBar(gui), CreateToolbar(gui));
	}

	public void setState(int s) {
		if (s < 0 || s > 2)
			state = 0;
		else
			state = s;
	}

	public int getState() {
		return state;
	}

	private void fadeText() {
		fadeIn.getKeyFrames().add(new KeyFrame(Duration.millis(500), new KeyValue(text.opacityProperty(), 1)));
		hold.getKeyFrames().add(new KeyFrame(Duration.millis(2000)));
		fadeOut.getKeyFrames().add(new KeyFrame(Duration.millis(300), new KeyValue(text.opacityProperty(), 0)));
		SequentialTransition seq = new SequentialTransition(fadeIn, hold, fadeOut);
		seq.play();
	}

	private MenuBar CreateMenuBar(Stage gui) {

		// create menus
		Menu fileMenu = new Menu("File");
		Menu editMenu = new Menu("Edit");
		Menu viewMenu = new Menu("View");

		// add menu items
		fileMenu.getItems().add(new MenuItem("New"));
		fileMenu.getItems().add(new MenuItem("Open"));
		fileMenu.getItems().add(new MenuItem("Save"));
		fileMenu.getItems().add(new MenuItem("Print"));

		editMenu.getItems().add(new MenuItem("Copy"));
		editMenu.getItems().add(new MenuItem("Paste"));
		editMenu.getItems().add(new MenuItem("Cut"));
		editMenu.getItems().add(new MenuItem("Undo"));
		editMenu.getItems().add(new MenuItem("Redo"));

		viewMenu.getItems().add(new MenuItem("Zoom in"));
		viewMenu.getItems().add(new MenuItem("Zoom out"));

		// add menu bars
		MenuBar menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(gui.widthProperty());
		menuBar.getMenus().addAll(fileMenu, editMenu, viewMenu);

		return menuBar;
	}

	private ToolBar CreateToolbar(Stage gui) {

		// create toolbar
		ToolBar toolBar = new ToolBar();
		toolBar.prefWidthProperty().bind(gui.widthProperty());

		// add images
		ImageView ClassBoxImage = new ImageView(new Image("file:src/ClassBox.jpg", 50, 50, false, true));
		ImageView PackageImage = new ImageView(new Image("file:src/Package.jpg", 50, 50, false, true));
		ImageView GeneralizationImage = new ImageView(new Image("file:src/Generalization.jpg", 50, 50, false, true));
		ImageView CompositionImage = new ImageView(new Image("file:src/Composition.jpg", 50, 50, false, true));
		ImageView AggregationImage = new ImageView(new Image("file:src/Aggregation.jpg", 50, 50, false, true));
		ImageView DependencyImage = new ImageView(new Image("file:src/Dependency.jpg", 50, 50, false, true));

		// create buttons
		ClassDiagram = new Button("", ClassBoxImage);
		PackageDiagram = new Button("", PackageImage);
		GeneralizationArrow = new Button("", GeneralizationImage);
		CompositionArrow = new Button("", CompositionImage);
		AggregationArrow = new Button("", AggregationImage);
		DependencyArrow = new Button("", DependencyImage);
		Selector = new Button("Selector");

		// add buttons to toolbar
		toolBar.getItems().addAll(ClassDiagram, PackageDiagram, GeneralizationArrow, CompositionArrow, AggregationArrow,
				DependencyArrow, Selector);

		return toolBar;
	}
}

class Delta {
	double x, y;
}
