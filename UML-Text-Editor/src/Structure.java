import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;

/**
 * UMLClass is a Java representation of a UML Class diagram. UMLClass consists four child nodes:
 * one Text object and three TextArea objects. The Text object acts as a header and makes it 
 * clear to the user that UMLClass is a representation of a Class Diagram.  The three TextArea objects
 * contain information for the Class Diagram's name, attributes, and operations, in that order.
 * 
 * @author Matthew Gormley
 *
 */
public abstract class Structure extends VBox {
	// TODO find out how to expand textfields to support multiple lines
	// TODO support resizing
	Text type = new Text();
	
	TextArea name;
	/**
	 * Boolean value for whether the Class Diagrams can be dragged around. True indicates that they can be dragged.
	 */
	static Boolean drag;
	/**
	 * A reference to the UMLClass itself.
	 */
	ArrayList<BinAssoc> collection = new ArrayList<>(10);
	/**
	 * An integer count of the number of lines associated with the UMLClass.
	 */
	int lineCount;
	/**
	 * A reference to the view that created the UMLClass
	 */
	View view;
	
	Structure ref;

	/**
	 * Creates a UMLClass with a set size and style as determined by the stylize method.
	 * @param view 
	 * 
	 * @param view A reference to the view object that created the UMLCLass
	 * @param type 
	 * @param type The initial type for this UMLClass
	 * @param name 
	 * @param className The initial name for this UMLClass
	 * @param classAttr The initial attribute for this UMLClass
	 * @param classOp The initial operation for this UMLCLass
	 */
	/*public Structure(View view, Text type, TextArea className, TextArea classAttr, TextArea classOp) {
		super(type, className, classAttr, classOp);
		this.name = className;
		this.attr = classAttr;
		this.op = classOp;
		this.type = type;
		this.view = view;
		ref = this;
		lineCount = 0;

		stylize();
		drag = true;
	}*/
	
	public Structure(View view, Text type, TextArea name) {
		super(type,name);
		this.view = view;
		this.type = type;
		this.name = name;
		ref = this;
	}
	
	public Structure(View view, Text type, TextArea className, TextArea classAttr, TextArea classOp) {
		// TODO Auto-generated constructor stub
		super(type, className, classAttr, classOp);
		lineCount = 0;
		drag = true;
		ref = this;
		this.view = view;
	}
	
	/**
	 * Sets the value of the property drag, and calls the dragable method with this updated value.
	 */
	public void setDrag(Boolean b) {
		drag = b;
		dragable();
	}

	/**
	 * Handles all the mouse events for the UMLClass.  When the mouse hovers over the header of a UMLClass, then the
	 * header color will change from blue to yellow.
	 * 
	 * If the Boolean drag is true, then clicking on a UMLCLass and dragging it will drag the UMLClass along with the mouse.
	 * Dragging a UMLClass will also update the positions of all lines connected to UMLCLass objects.
	 * 
	 * If the Boolean drag is false, then clicking on a UMLClass will set that object to either be a parent or child of
	 * the potential line.
	 */
	private void dragable() {
		// TODO Prevent objects from going off screen
		Delta d = new Delta();

		type.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				highlight();//setStyle("-fx-background-color: yellow;\n" + "-fx-border-color: black;\n" + "-fx-border-width: 3;");
			}
		});

		type.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				unhighlight();//setStyle("-fx-background-color: #00b8f5;\n" + "-fx-border-color: black;\n" + "-fx-border-width: 3;");
			}
		});

		if (drag) {

			setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					d.x = getLayoutX() - mouseEvent.getSceneX();
					d.y = getLayoutY() - mouseEvent.getSceneY();
					System.out.println(getLayoutX() + " " + getLayoutY());
					setCursor(Cursor.MOVE);
					toFront();
				}
			});

			setOnMouseDragged(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					setLayoutX(mouseEvent.getSceneX() + d.x);
					setLayoutY(mouseEvent.getSceneY() + d.y);
					for (BinAssoc line : collection) {
						line.updatePoints();
					}
				}
			});

		} else {

			setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					int l = lineCount - 1;
					if (view.getState() == 1) {
						collection.get(l).setParent(ref);
						view.setState(view.getState() + 1);
					} else if (view.getState() == 2) {
						collection.get(l).setChild(ref);
						view.setState(0);
						view.selectMode();
					}
				}
			});

			setOnMouseDragged(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
				}
			});
		}
	}

	protected abstract void unhighlight();
	
	protected abstract void highlight();

	/**
	 * Provides the UMLClass with a line that may use a reference to the UMLClass itself.
	 * 
	 * @param line2 A line that may potentially need a reference to the UMLClass
	 */
	public void setPoLine(BinAssoc line2) {
		// TODO Auto-generated method stub
		
		lineCount++;
		collection.add(line2);
	}
}