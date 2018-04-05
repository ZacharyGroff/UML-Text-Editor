import org.junit.Test;
import org.junit.Assert;
import org.junit.BeforeClass;
import javafx.application.Application;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class JUnitTestSeq {
	
	public static class AsNonApp extends Application {
	    @Override
	    public void start(Stage primaryStage) throws Exception {
	    }
	}

	@BeforeClass
	public static void init() {
	    Thread t = new Thread("init") {
	        public void run() {
	            Application.launch(AsNonApp.class, new String[0]);
	        }
	    };
	    t.setDaemon(true);
	    t.start();
	}

	@Test
	public void constructView() {
		View testView = new View();
		Assert.assertTrue("View has been generated", testView != null);
	}
	
	@Test
	public void constructUMLBox(){
		Pane canvas = new Pane();
		UMLClass testBox = new UMLClass(canvas, new Text(), new TextArea(), new TextArea(), new TextArea());
		Assert.assertTrue("Class Box has been generated", testBox != null);
	}
	
	@Test
	public void makeUMLBoxDraggable() {
		Pane canvas = new Pane();
		UMLClass testBox = new UMLClass(canvas, new Text(), new TextArea(), new TextArea(), new TextArea());
		testBox.setDrag(true);
		Assert.assertTrue("Class Box is draggable", testBox.drag);
	}
	
	@Test
	public void constructLine() {
		Pane canvas = new Pane();
		GenLine line = new GenLine(canvas);
		Assert.assertTrue("Line has been generated", line != null);
	}
	
	@Test
	public void connectUMLBoxes() {
		Pane canvas = new Pane();
		UMLClass testBox = new UMLClass(canvas, new Text(), new TextArea(), new TextArea(), new TextArea());
		UMLClass testBox2 = new UMLClass(canvas, new Text(), new TextArea(), new TextArea(), new TextArea());
		GenLine line = new GenLine(canvas);
		line.setParent(testBox);
		line.setChild(testBox2);
		Assert.assertTrue("Line has been generated with testBox as parent and testBox2 as child", line.getLineParent() == testBox && line.getLineChild() == testBox2);
	}
	
	@Test
	public void outOfBoundsTop() {
		Pane canvas = new Pane();
		UMLClass testBox = new UMLClass(canvas, new Text(), new TextArea(), new TextArea(), new TextArea());
		testBox.setLayoutY(-20);
		Assert.assertTrue("Class box is not able to be moved out the drawing region towards the top of the GUI", testBox.getLayoutY() >= 0);
	}
	
	@Test
	public void outOfBoundsBottom() {
		Pane canvas = new Pane();
		UMLClass testBox = new UMLClass(canvas, new Text(), new TextArea(), new TextArea(), new TextArea());
		testBox.setLayoutY(1000);
		Assert.assertTrue("Class box is not able to be moved out the drawing region towards the bottom of the GUI", testBox.getLayoutY() <= 850);
	}
	
	@Test
	public void outOfBoundsLeft() {
		Pane canvas = new Pane();
		UMLClass testBox = new UMLClass(canvas, new Text(), new TextArea(), new TextArea(), new TextArea());
		testBox.setLayoutX(-50);
		Assert.assertTrue("Class box is not able to be moved out the drawing region towards the bottom of the GUI", testBox.getLayoutX() >= 0);
	}
	
	@Test
	public void outOfBoundsRight() {
		Pane canvas = new Pane();
		UMLClass testBox = new UMLClass(canvas, new Text(), new TextArea(), new TextArea(), new TextArea());
		testBox.setLayoutX(2000);
		Assert.assertTrue("Class box is not able to be moved out the drawing region towards the bottom of the GUI", testBox.getLayoutX() < 1560);
	}
}
