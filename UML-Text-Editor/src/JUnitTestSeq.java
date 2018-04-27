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
		View view = new View();
		UMLClass testBox = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		Assert.assertTrue("Class Box has been generated", testBox != null);
	}
	
	@Test
	public void makeUMLBoxDraggable() {
		View view = new View();
		UMLClass testBox = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		testBox.setDrag(true);
		Assert.assertTrue("Class Box is draggable", testBox.drag);
	}
	
	@Test
	public void constructLine() {
		Pane view = new Pane();
		BinAssoc line = new BinAssoc(view);
		Assert.assertTrue("Line has been generated", line != null);
	}
	
	@Test
	public void connectUMLBoxes() {
		Pane canvas = new Pane();
		View view = new View();
		UMLClass testBox = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		UMLClass testBox2 = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		BinAssoc line = new BinAssoc(canvas);
		line.setParent(testBox);
		line.setChild(testBox2);
		Assert.assertTrue("Line has been generated with testBox as parent and testBox2 as child", line.getLineParent() == testBox && line.getLineChild() == testBox2);
	}
	
	@Test
	public void outOfBoundsTop() {
		View view = new View();
		UMLClass testBox = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		testBox.setLayoutY(-20);
		Assert.assertTrue("Class box is not able to be moved out the drawing region towards the top of the GUI", testBox.getLayoutY() >= 0);
	}
	
	@Test
	public void outOfBoundsBottom() {
		View view = new View();
		UMLClass testBox = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		testBox.setLayoutY(1000);
		Assert.assertTrue("Class box is not able to be moved out the drawing region towards the bottom of the GUI", testBox.getLayoutY() <= 850);
	}
	
	@Test
	public void outOfBoundsLeft() {
		View view = new View();
		UMLClass testBox = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		testBox.setLayoutX(-50);
		Assert.assertTrue("Class box is not able to be moved out the drawing region towards the bottom of the GUI", testBox.getLayoutX() >= 0);
	}
	
	@Test
	public void outOfBoundsRight() {
		View view = new View();
		UMLClass testBox = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		testBox.setLayoutX(2000);
		Assert.assertTrue("Class box is not able to be moved out the drawing region towards the bottom of the GUI", testBox.getLayoutX() < 1560);
	}
	
	@Test
	public void binLineSetTwoLineParents() {
		Pane canvas = new Pane();
		View view = new View();
		UMLClass testBox = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		UMLClass testBox2 = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		BinAssoc line = new BinAssoc(canvas);
		line.setParent(testBox);
		line.setParent(testBox2);
		Assert.assertTrue(line.getLineParent() == testBox2 && line.getLineParent() != testBox);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void binLineSetTwoLineChildren() {
		Pane canvas = new Pane();
		View view = new View();
		UMLClass testBox = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		UMLClass testBox2 = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		BinAssoc line = new BinAssoc(canvas);
		line.setParent(testBox);
		line.setChild(testBox);
		line.setChild(testBox2);
		Assert.assertTrue(line.getLineChild() == testBox2 && line.getLineChild() != testBox);

	}
	
	@Test
	public void binLineSetUMLClassAsChildAndParent() {
		Pane canvas = new Pane();
		View view = new View();
		UMLClass testBox = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		BinAssoc line = new BinAssoc(canvas);
		line.setParent(testBox);
		line.setChild(testBox);
		Assert.assertTrue(line.getLineChild() == testBox && line.getLineParent() == testBox);
	}
	
	@Test
	public void changeBinLineParent() {
		Pane canvas = new Pane();
		View view = new View();
		UMLClass testBox = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		UMLClass testBox2 = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		BinAssoc line = new BinAssoc(canvas);
		line.setParent(testBox);
		line.setParent(testBox2);
		Assert.assertTrue(line.getLineParent() == testBox2);
	}
	
	@Test
	public void changeBinLineChild() {
		Pane canvas = new Pane();
		View view = new View();
		UMLClass testBox = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		UMLClass testBox2 = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		BinAssoc line = new BinAssoc(canvas);
		line.setParent(testBox);
		line.setChild(testBox);
		
		try {
		line.setChild(testBox2);
		} catch (Exception IllegalArgumentException) {
			Assert.assertFalse(true);
		}
		
		Assert.assertTrue(line.getLineChild() == testBox2);
	}
	
	@Test
	public void binLineSetMultiLineParent() {
		Pane canvas = new Pane();
		View view = new View();
		UMLClass testBox = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		UMLClass testBox2 = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		UMLClass testBox3 = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		BinAssoc line = new BinAssoc(canvas);
		BinAssoc line2 = new BinAssoc(canvas);
		line.setParent(testBox);
		line.setChild(testBox2);
		line2.setParent(testBox);
		line2.setChild(testBox3);
		
		Assert.assertTrue(line.getLineParent() == testBox && line2.getLineParent() == testBox);
	}
	
	@Test
	public void binLineSetMultiLineChild() {
		Pane canvas = new Pane();
		View view = new View();
		UMLClass testBox = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		UMLClass testBox2 = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		UMLClass testBox3 = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		BinAssoc line = new BinAssoc(canvas);
		BinAssoc line2 = new BinAssoc(canvas);
		line.setParent(testBox);
		line.setChild(testBox2);
		line2.setParent(testBox3);
		line2.setChild(testBox2);
		
		Assert.assertTrue(line.getLineChild() == testBox2 && line2.getLineChild() == testBox2);
	}
	
	@Test
	public void deleteDirLine() {
		View view = new View();
		UMLClass testBox = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		UMLClass testBox2 = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		BinAssoc line = new BinAssoc(view.canvas);
		line.setParent(testBox);
		line.setChild(testBox2);
		view.canvas.getChildren().add(testBox);
		view.canvas.getChildren().add(testBox2);
		view.deleteStructure();
		testBox.setDrag(true);
		System.out.println(testBox.collection);
		System.out.println(testBox2.collection);
	}
	
	@Test
	public void dirLineSetTwoLineParents() {
		Pane canvas = new Pane();
		View view = new View();
		UMLClass testBox = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		UMLClass testBox2 = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		DirAssoc line = new DirAssoc(canvas);
		line.setParent(testBox);
		line.setParent(testBox2);
		Assert.assertTrue(line.getLineParent() == testBox2 && line.getLineParent() != testBox);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void dirLineSetTwoLineChildren() {
		Pane canvas = new Pane();
		View view = new View();
		UMLClass testBox = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		UMLClass testBox2 = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		DirAssoc line = new DirAssoc(canvas);
		line.setParent(testBox);
		line.setChild(testBox);
		line.setChild(testBox2);
		Assert.assertTrue(line.getLineChild() == testBox2 && line.getLineChild() != testBox);
	}
	
	@Test
	public void dirLineSetUMLClassAsChildAndParent() {
		Pane canvas = new Pane();
		View view = new View();
		UMLClass testBox = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		DirAssoc line = new DirAssoc(canvas);
		line.setParent(testBox);
		line.setChild(testBox);
		Assert.assertTrue(line.getLineChild() == testBox && line.getLineParent() == testBox);
	}
	
	@Test
	public void changeDirLineParent() {
		Pane canvas = new Pane();
		View view = new View();
		UMLClass testBox = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		UMLClass testBox2 = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		DirAssoc line = new DirAssoc(canvas);
		line.setParent(testBox);
		line.setParent(testBox2);
		Assert.assertTrue(line.getLineParent() == testBox2);
	}
	
	@Test
	public void changeDirLineChild() {
		Pane canvas = new Pane();
		View view = new View();
		UMLClass testBox = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		UMLClass testBox2 = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		DirAssoc line = new DirAssoc(canvas);
		line.setParent(testBox);
		line.setChild(testBox);
		
		try {
		line.setChild(testBox2);
		} catch (Exception IllegalArgumentException) {
			Assert.assertFalse(true);
		}
		
		Assert.assertTrue(line.getLineChild() == testBox2);
	}
	
	@Test
	public void dirLineSetMultiLineParent() {
		Pane canvas = new Pane();
		View view = new View();
		UMLClass testBox = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		UMLClass testBox2 = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		UMLClass testBox3 = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		DirAssoc line = new DirAssoc(canvas);
		DirAssoc line2 = new DirAssoc(canvas);
		line.setParent(testBox);
		line.setChild(testBox2);
		line2.setParent(testBox);
		line2.setChild(testBox3);
		
		Assert.assertTrue(line.getLineParent() == testBox && line2.getLineParent() == testBox);
	}
	
	@Test
	public void dirLineSetMultiLineChild() {
		Pane canvas = new Pane();
		View view = new View();
		UMLClass testBox = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		UMLClass testBox2 = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		UMLClass testBox3 = new UMLClass(view, new Text(), new TextArea(), new TextArea(), new TextArea());
		DirAssoc line = new DirAssoc(canvas);
		DirAssoc line2 = new DirAssoc(canvas);
		line.setParent(testBox);
		line.setChild(testBox2);
		line2.setParent(testBox3);
		line2.setChild(testBox2);
		
		Assert.assertTrue(line.getLineChild() == testBox2 && line2.getLineChild() == testBox2);
	}
}
