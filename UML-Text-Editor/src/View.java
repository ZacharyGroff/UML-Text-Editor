import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.*;
import javafx.geometry.Point2D;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
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

	double orgSceneX, orgSceneY;
	double orgTranslateX, orgTranslateY;
	double offsetX, offsetY;
	double newTranslateX, newTranslateY;
	double x1, x2, y1, y2;

	Button ClassDiagram;
	Button PackageDiagram;
	Button DependencyArrow;
	Button AggregationArrow;
	Button CompositionArrow;
	Button GeneralizationArrow;
	Timeline fadeIn, fadeOut, hold;

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

		// create Scene
		Scene scene = new Scene(layout);
		gui.setScene(scene);

		UMLClass t1 = new UMLClass(canvas, new Text(), new TextArea(), new TextArea(), new TextArea());
		UMLClass t2 = new UMLClass(canvas, new Text(), new TextArea(), new TextArea(), new TextArea());

		t1.setDrag(true);
		t2.setDrag(true);

		t1.toFront();

		// stack.getChildren().add(t);
		canvas.getChildren().addAll(t1, t2);

		// create event handler to drag and drop
		/*
		 * EventHandler<MouseEvent> stackOnMousePressedEventHandler = new
		 * EventHandler<MouseEvent>() {
		 * 
		 * @Override public void handle(MouseEvent t) { orgSceneX = t.getSceneX();
		 * orgSceneY = t.getSceneY(); orgTranslateX = ((StackPane)
		 * (t.getSource())).getTranslateX(); orgTranslateY = ((StackPane)
		 * (t.getSource())).getTranslateY(); } };
		 * 
		 * EventHandler<MouseEvent> stackOnMouseDraggedEventHandler = new
		 * EventHandler<MouseEvent>() {
		 * 
		 * @Override public void handle(MouseEvent t) { offsetX = t.getSceneX() -
		 * orgSceneX; offsetY = t.getSceneY() - orgSceneY; newTranslateX = orgTranslateX
		 * + offsetX; newTranslateY = orgTranslateY + offsetY;
		 * 
		 * ((StackPane) (t.getSource())).setTranslateX(newTranslateX); ((StackPane)
		 * (t.getSource())).setTranslateY(newTranslateY); } };
		 */

		// create event handler for button a
		ClassDiagram.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				UMLClass two = new UMLClass(canvas, new Text(), new TextArea(), new TextArea(), new TextArea());
				canvas.getChildren().add(two);
				System.out.println(canvas.getChildren());
				text.setText("New Class added");
				fadeText();

				for (Node i : canvas.getChildren()) {
					if (UMLClass.class.isInstance(i))
						((UMLClass) i).setDrag(true);
				}
				// create Text

				// Add rectangle and text to stack
				// stack.getChildren().addAll(rectangle2, text);
				// stack.setLayoutX(50);
				// stack.setLayoutY(50);

				// create mouse events for stack
				// stack.setCursor(Cursor.HAND);
				// stack.setOnMousePressed(stackOnMousePressedEventHandler);
				// stack.setOnMouseDragged(stackOnMouseDraggedEventHandler);

				// root.getChildren().add(stack);

			}
		});
		// line.setMouseTransparent(true);
		BooleanProperty dragging = new SimpleBooleanProperty();
		BooleanProperty draggingOverRect2 = new SimpleBooleanProperty();

		GeneralizationArrow.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				text.setText("Now in line mode");
				fadeText();
				for (Node i : canvas.getChildren()) {
					if (UMLClass.class.isInstance(i))
						((UMLClass) i).setDrag(false);
				}
				dragging.set(true);
				// UMLClass.setDrag(false);
			}
		});

		// add the line

		GenLine line = new GenLine(canvas);

		t1.setOnDragDetected(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				if (dragging.getValue()) {
					t1.startFullDrag();
					Point2D mouseSceneCoords = new Point2D(event.getSceneX(), event.getSceneY());
					Point2D mousePaneCoords = canvas.sceneToLocal(mouseSceneCoords);
					line.setStartX(mousePaneCoords.getX());
					line.setStartY(mousePaneCoords.getY());
					line.setEndX(mousePaneCoords.getX());
					line.setEndY(mousePaneCoords.getY());
					line.setStrokeWidth(5);
					canvas.getChildren().add(line);
				}
			}
			
		});

		canvas.setOnMouseDragged(event -> {
			if (dragging.get()) {
				line.setEndX(event.getX());
				line.setEndY(event.getY());
			}
		});

		t2.setOnMouseDragEntered(event -> {
			// if (draggingOverRect2.get()) {
			line.setStartX(t1.getLayoutX() + t1.getWidth() / 2);
			x1 = t1.getLayoutX() + t1.getWidth() / 2;
			line.setStartY(t1.getLayoutY() + t1.getHeight() / 2);
			y1 = t1.getLayoutY() + t1.getWidth() / 2;
			line.setEndX(t2.getLayoutX() + t2.getWidth() / 2);
			x2 = t2.getLayoutX() + t2.getWidth() / 2;
			line.setEndY(t2.getLayoutY() + t2.getHeight() / 2);
			y2 = t2.getLayoutY() + t2.getHeight() / 2;
			line.toBack();
			GenLine realLine = new GenLine(x1, x2, y1, y2);
			canvas.getChildren().remove(line);
			canvas.getChildren().add(realLine);
			// canvas.getChildren().add(line);
			// }
			dragging.set(false);
			draggingOverRect2.set(false);
			// canvas.getChildren().remove(line);
		});

		/*
		 * rectangle2.setOnMouseDragEntered(event -> { if (dragging.get()) {
		 * draggingOverRect2.set(true); } }); rectangle2.setOnMouseDragExited(event ->
		 * draggingOverRect2.set(false));
		 * 
		 * rectangle2.toFront();
		 */

		// display GUI
		gui.show();
	}

	private void menuGenerate(VBox menu) {
		menu.getChildren().addAll(CreateMenuBar(gui), CreateToolbar(gui));
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

		// add buttons to toolbar
		toolBar.getItems().addAll(ClassDiagram, PackageDiagram, GeneralizationArrow, CompositionArrow, AggregationArrow,
				DependencyArrow);

		return toolBar;
	}
}

class Delta {
	double x, y;
}