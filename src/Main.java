import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.*;

public class Main extends Application {
	
	Stage gui;
	BorderPane layout;
	
	double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
	
	Button classDiagram = new Button("a");
	Button packageDiagram = new Button("b");
	Button objectDiagram = new Button("c");
	Button componentDiagram = new Button("d");
	Button generalizationArrow = new Button("e");
	Button inheritanceArrow = new Button("f");
	Button compositionArrow = new Button("g");
	Button aggregationArrow = new Button("h");
	Button dependencyArrow = new Button("i");
	Button multiplicityArrow = new Button("j");
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	//create event handler to drag and drop
	EventHandler<MouseEvent> stackOnMousePressedEventHandler = 
	        new EventHandler<MouseEvent>() {
	 
	        @Override
	        public void handle(MouseEvent t) {
	            orgSceneX = t.getSceneX();
	            orgSceneY = t.getSceneY();
	            orgTranslateX = ((StackPane)(t.getSource())).getTranslateX();
	            orgTranslateY = ((StackPane)(t.getSource())).getTranslateY();
	        }
	    };
	    
	    EventHandler<MouseEvent> stackOnMouseDraggedEventHandler = 
	            new EventHandler<MouseEvent>() {
	     
	            @Override
	            public void handle(MouseEvent t) {
	                double offsetX = t.getSceneX() - orgSceneX;
	                double offsetY = t.getSceneY() - orgSceneY;
	                double newTranslateX = orgTranslateX + offsetX;
	                double newTranslateY = orgTranslateY + offsetY;
	                 
	                ((StackPane)(t.getSource())).setTranslateX(newTranslateX);
	                ((StackPane)(t.getSource())).setTranslateY(newTranslateY);
	            }
	        };
	        

	@Override
	public void start(Stage primaryStage) throws Exception {
		gui = primaryStage;
		
		gui.setTitle("UML Text Editor");
		
		//create menus
		Menu fileMenu = new Menu("File");
		Menu editMenu = new Menu("Edit");
		Menu viewMenu = new Menu("View");
		
		//add menu items
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

		
		//add menu bars
		MenuBar menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(gui.widthProperty());
		menuBar.getMenus().addAll(fileMenu, editMenu, viewMenu);
		
		//create toolbar
		ToolBar toolBar = new ToolBar();
		toolBar.prefWidthProperty().bind(gui.widthProperty());
		
		//create buttons
		 classDiagram = new Button("a");
		 packageDiagram = new Button("b");
		 objectDiagram = new Button("c");
		 componentDiagram = new Button("d");
		 generalizationArrow = new Button("e");
		 inheritanceArrow = new Button("f");
		 compositionArrow = new Button("g");
		 aggregationArrow = new Button("h");
		 dependencyArrow = new Button("i");
		 multiplicityArrow = new Button("j");
		 
		//create stack
		 StackPane stack = new StackPane();
		 
		 //create event handler for button a
		classDiagram.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	
				 //create Rectangle
				 Rectangle rectangle = new Rectangle(50,50,100,90);
				 rectangle.setFill(Color.TRANSPARENT);
				 rectangle.setStroke(Color.BLACK);
				 rectangle.setStrokeWidth(5);

			     //create Text
			     Text text = new Text("Hello");
			     text.setFill(Color.BLACK);
			     
			     //Add rectangle and text to stack
			     stack.getChildren().addAll(rectangle, text);
			     //stack.setLayoutX(50);
			     //stack.setLayoutY(50);
			     
			     //create mouse events for stack
			     stack.setCursor(Cursor.HAND);
				 stack.setOnMousePressed(stackOnMousePressedEventHandler);
				 stack.setOnMouseDragged(stackOnMouseDraggedEventHandler); 
		    }
		});

		//add buttons to toolbar
		toolBar.getItems().addAll(classDiagram, packageDiagram, objectDiagram, componentDiagram, generalizationArrow,
				inheritanceArrow, compositionArrow, aggregationArrow, dependencyArrow, multiplicityArrow);
		
		
		//set borderPane
		layout = new BorderPane();
		layout.setTop(menuBar);
		layout.setLeft(toolBar);
		layout.setBottom(stack);
		
		//create Scene
		Scene scene = new Scene (layout, 1000, 750);
		
		//display GUI
		gui.setScene(scene);
		gui.show();
		}
}
