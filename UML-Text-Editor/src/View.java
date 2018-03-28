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

public class View extends Application {

	Stage gui;
	BorderPane layout;

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
	
	public void begin() throws Exception {
		launch();
	}

	public void start(Stage primaryStage) throws Exception {

		gui = primaryStage;

		gui.setTitle("UML Text Editor");
		gui.setHeight(768);
		gui.setWidth(1024);
		
		// create group
		Pane canvas = new Pane();
						
		// create stack
		//StackPane stack = new StackPane();

		// set borderPane
		layout = new BorderPane();
		layout.setPrefSize(640, 480);
		layout.setTop(CreateMenuBar(gui));
		layout.setLeft(CreateToolbar(gui));
		layout.setBottom(canvas);
		
		// create Scene
		Scene scene = new Scene(layout, 1000, 750);
		
		gui.setScene(scene);
				
		Class t = new Class(new Text(), new TextArea(), new TextArea(), new TextArea());

		//stack.getChildren().add(t);
		canvas.getChildren().addAll(t);

		// create event handler to drag and drop
		/*EventHandler<MouseEvent> stackOnMousePressedEventHandler = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				orgSceneX = t.getSceneX();
				orgSceneY = t.getSceneY();
				orgTranslateX = ((StackPane) (t.getSource())).getTranslateX();
				orgTranslateY = ((StackPane) (t.getSource())).getTranslateY();
			}
		};

		EventHandler<MouseEvent> stackOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				offsetX = t.getSceneX() - orgSceneX;
				offsetY = t.getSceneY() - orgSceneY;
				newTranslateX = orgTranslateX + offsetX;
				newTranslateY = orgTranslateY + offsetY;

				((StackPane) (t.getSource())).setTranslateX(newTranslateX);
				((StackPane) (t.getSource())).setTranslateY(newTranslateY);
			}
		};*/
		
		// create event handler for button a
		ClassDiagram.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
				Class two = new Class(new Text(), new TextArea(), new TextArea(), new TextArea());
				canvas.getChildren().add(two);

				// create Text

				// Add rectangle and text to stack
				//stack.getChildren().addAll(rectangle2, text);
				// stack.setLayoutX(50);
				// stack.setLayoutY(50);

				// create mouse events for stack
				//stack.setCursor(Cursor.HAND);
				//stack.setOnMousePressed(stackOnMousePressedEventHandler);
				//stack.setOnMouseDragged(stackOnMouseDraggedEventHandler);

				//root.getChildren().add(stack);

			}
		});

		// add the line

		Line line = new Line();
		line.setMouseTransparent(true);
		BooleanProperty dragging = new SimpleBooleanProperty();
		BooleanProperty draggingOverRect2 = new SimpleBooleanProperty();

		/*rectangle.setOnDragDetected(event -> {
			rectangle.startFullDrag();
			Point2D mouseSceneCoords = new Point2D(event.getSceneX(), event.getSceneY());
			Point2D mousePaneCoords = canvas.sceneToLocal(mouseSceneCoords);
			line.setStartX(mousePaneCoords.getX());
			line.setStartY(mousePaneCoords.getY());
			line.setEndX(mousePaneCoords.getX());
			line.setEndY(mousePaneCoords.getY());
			line.setStrokeWidth(5);
			canvas.getChildren().add(line);
			dragging.set(true);
		});*/

		canvas.setOnMouseDragged(event -> {
			if (dragging.get()) {
				line.setEndX(event.getX());
				line.setEndY(event.getY());
			}
		});

		/*rectangle.setOnMouseReleased(event -> {
			if (draggingOverRect2.get()) {
				line.setStartX(rectangle.getX());
				line.setStartY(rectangle.getY());
				line.setEndX(newTranslateX);
				line.setEndY(newTranslateY);
				canvas.getChildren().add(line);
			}
			dragging.set(false);
			draggingOverRect2.set(false);
			canvas.getChildren().remove(line);
		});*/

		/*rectangle2.setOnMouseDragEntered(event -> {
			if (dragging.get()) {
				draggingOverRect2.set(true);
			}
		});
		rectangle2.setOnMouseDragExited(event -> draggingOverRect2.set(false));

		rectangle2.toFront();*/

		/*
		 * class Connection extends Line { public Connection(Rectangle startBall,
		 * Rectangle endBall) { startXProperty().bind(startBall.xProperty());
		 * startYProperty().bind(startBall.yProperty());
		 * endXProperty().bind(endBall.xProperty());
		 * endYProperty().bind(endBall.yProperty()); } } Connection connection = new
		 * Connection(rectangle, rectangle2); connection.setStroke(Color.CYAN);
		 * connection.setStrokeWidth(5); root.getChildren().add(connection);
		 */

		// display GUI
		gui.show();
	}
	
	public MenuBar CreateMenuBar(Stage gui) {
				
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
	
	public ToolBar CreateToolbar(Stage gui) {
				
				// create toolbar
				ToolBar toolBar = new ToolBar();
				toolBar.prefWidthProperty().bind(gui.widthProperty());

				// add images
				ImageView ClassBoxImage = new ImageView(
						new Image("file:src/ClassBox.jpg", 50, 50, false, true));
				ImageView PackageImage = new ImageView(
						new Image("file:src/Package.jpg", 50, 50, false, true));
				ImageView GeneralizationImage = new ImageView(
						new Image("file:src/Generalization.jpg", 50, 50, false, true));
				ImageView CompositionImage = new ImageView(
						new Image("file:src/Composition.jpg", 50, 50, false, true));
				ImageView AggregationImage = new ImageView(
						new Image("file:src/Aggregation.jpg", 50, 50, false, true));
				ImageView DependencyImage = new ImageView(
						new Image("file:src/Dependency.jpg", 50, 50, false, true));
				
				// create buttons
				ClassDiagram = new Button("", ClassBoxImage);
				PackageDiagram = new Button("", PackageImage);
				GeneralizationArrow = new Button("", GeneralizationImage);
				CompositionArrow = new Button("", CompositionImage);
				AggregationArrow = new Button("", AggregationImage);
				DependencyArrow = new Button("", DependencyImage);

				// add buttons to toolbar
				toolBar.getItems().addAll(ClassDiagram, PackageDiagram, GeneralizationArrow,
						CompositionArrow, AggregationArrow, DependencyArrow);

				return toolBar;
	}
}

class Delta { double x, y; }

class Class extends VBox {
	//TODO find out how to expand textfields to support multiple lines
	//TODO support resizing
	TextArea name;
	TextArea attr;
	TextArea op;
	Text struct;
	
	public Class(Text struct, TextArea className, TextArea classAttr, TextArea classOp) {
		super(struct,className,classAttr,classOp);
		this.name = className;
		this.attr = classAttr;
		this.op = classOp;
		this.struct = struct;
		
		this.struct.setText("Structure");		
		name.setPromptText("Name");
		attr.setPromptText("Attributes");
		op.setPromptText("Operations");
		setPrefWidth(150);
		setPrefHeight(250);
		wrapText(true);
		//TODO: Create CSS file instead of hard-coded styles. 
		setStyle("-fx-border-color: black;\n" + "-fx-border-width: 3;");
		dragable();
	}

	private void wrapText(boolean b) {
		// TODO Auto-generated method stub
		name.setWrapText(b);
		attr.setWrapText(b);
		op.setWrapText(b);
		
	}

	private void dragable() {
		// TODO Current drag method is wonky when moving up and down.
		// TODO Prevent objects from going off screen
		Delta d = new Delta();
		
		setOnMousePressed(new EventHandler<MouseEvent>() {
			  @Override public void handle(MouseEvent mouseEvent) {
			d.x = getLayoutX() - mouseEvent.getSceneX();
		    d.y = getLayoutY() - mouseEvent.getSceneY();
		    System.out.println(getLayoutX() + " " + getLayoutY());
			setCursor(Cursor.MOVE);
		}
		});
		
		setOnMouseDragged(new EventHandler<MouseEvent>() {
			  @Override public void handle(MouseEvent mouseEvent) {
			setLayoutX(mouseEvent.getSceneX() + d.x);
			setLayoutY(mouseEvent.getSceneY() + d.y);
		}});
	}
}