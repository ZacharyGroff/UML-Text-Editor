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
public class UMLClass extends VBox {
	// TODO find out how to expand textfields to support multiple lines
	// TODO support resizing
	TextArea name;
	TextArea attr;
	TextArea op;
	Text type = new Text();
	static Boolean drag;
	UMLClass ref;
	ArrayList<GenLine> collection = new ArrayList<>(10);
	int lineCount;
	View view;

	/**
	 * Creates a UMLClass with a set size and style as determined by the stylize method.
	 * 
	 * @param view A reference to the view object that created the UMLCLass
	 * @param type The initial type for this UMLClass
	 * @param className The initial name for this UMLClass
	 * @param classAttr The initial attribute for this UMLClass
	 * @param classOp The initial operation for this UMLCLass
	 */
	public UMLClass(View view, Text type, TextArea className, TextArea classAttr, TextArea classOp) {
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
	}
	
	/**
	 * Sets the attributes of the UMLClass, the style and preferred dimensions, 
	 * and the attributes of the child nodes, the prompt text and wrapping of each Text Area.
	 */
	private void stylize() {
		type.setText("Class Diagram                ");
		name.setPromptText("Name");
		attr.setPromptText("Attributes");
		op.setPromptText("Operations");
		setPrefWidth(150);
		setPrefHeight(250);
		name.setWrapText(true);
		attr.setWrapText(true);
		op.setWrapText(true);
		setStyle("-fx-background-color: #00b8f5;\n" + "-fx-border-color: black;\n" + "-fx-border-width: 3;");
	}

	
	/**
	 * Sets the value of the property drag, and calls the dragable method with this updated value
	 */
	public void setDrag(Boolean b) {
		drag = b;
		dragable();
	}

	private void dragable() {
		// TODO Prevent objects from going off screen
		Delta d = new Delta();

		type.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				setStyle("-fx-background-color: yellow;\n" + "-fx-border-color: black;\n" + "-fx-border-width: 3;");
			}
		});

		type.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				setStyle("-fx-background-color: #00b8f5;\n" + "-fx-border-color: black;\n" + "-fx-border-width: 3;");
			}
		});

		if (drag) {

			setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					d.x = getLayoutX() - mouseEvent.getSceneX();
					d.y = getLayoutY() - mouseEvent.getSceneY();
					setCursor(Cursor.MOVE);
					toFront();
				}
			});

			setOnMouseDragged(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					setLayoutX(mouseEvent.getSceneX() + d.x);
					setLayoutY(mouseEvent.getSceneY() + d.y);
					for (GenLine line : collection) {
						line.update();
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
						;
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

	public void setPoLine(GenLine line2) {
		// TODO Auto-generated method stub
		lineCount++;
		collection.add(line2);
	}
}
