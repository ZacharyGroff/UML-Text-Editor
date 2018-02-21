import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.*;

public class Main extends Application{
	
	Stage gui;
	BorderPane layout;
	
	public static void main(String[] args) {
		launch(args);
	}

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

		//add buttons to toolbar
		toolBar.getItems().addAll(classDiagram, packageDiagram, objectDiagram, componentDiagram, generalizationArrow,
				inheritanceArrow, compositionArrow, aggregationArrow, dependencyArrow, multiplicityArrow);
		
		
		//set borderPane
		layout = new BorderPane();
		layout.setTop(menuBar);
		layout.setLeft(toolBar);
		
		//create Scene
		Scene scene = new Scene (layout, 1000, 750);
		
		//display GUI
		gui.setScene(scene);
		gui.show();
	}
}
