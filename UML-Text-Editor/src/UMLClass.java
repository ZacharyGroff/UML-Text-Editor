import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

/**
 * UMLClass is a Java representation of a UML Class diagram. UMLClass consists four child nodes:
 * one Text object and three TextArea objects. The Text object acts as a header and makes it 
 * clear to the user that UMLClass is a representation of a Class Diagram.  The three TextArea objects
 * contain information for the Class Diagram's name, attributes, and operations, in that order.
 * 
 * @author Matthew Gormley
 *
 */
public class UMLClass extends Structure {
	// TODO find out how to expand textfields to support multiple lines
	// TODO support resizing
	/**
	 * The user-given name of the Class Diagram.
	 */
	//TextArea name;
	/**
	 * The user-given attributes of the Class Diagram.
	 */
	TextArea attr;
	/**
	 * The user-given operations of the Class Diagram.
	 */
	TextArea op;
	/**
	 * The header for the Class Diagram.
	 */
	//Text type = new Text();
	/**
	 * Boolean value for whether the Class Diagrams can be dragged around. True indicates that they can be dragged.
	 */

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
		super(view, type, className, classAttr, classOp);
		name = className;
		attr = classAttr;
		op = classOp;
		this.type = type;

		stylize();
		//drag = true;
	}
	
	/**
	 * Sets the attributes of the UMLClass, the style and preferred dimensions, 
	 * and the attributes of the child nodes, the prompt text and wrapping of each Text Area.
	 */
	protected void stylize() {
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
	
	protected void highlight() {
		setStyle("-fx-background-color: yellow;\n" + "-fx-border-color: black;\n" + "-fx-border-width: 3;");
	}
	
	protected void unhighlight() {
		setStyle("-fx-background-color: #00b8f5;\n" + "-fx-border-color: black;\n" + "-fx-border-width: 3;");
	}

	
	/**
	 * Sets the value of the property drag, and calls the dragable method with this updated value.
	 */
	/*public void setDrag(Boolean b) {
		drag = b;
		dragable();
	}*/

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
	/*private void dragable() {
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
	}*/

	/**
	 * Provides the UMLClass with a line that may use a reference to the UMLClass itself.
	 * 
	 * @param line2 A line that may potentially need a reference to the UMLClass
	 */
	/*public void setPoLine(BinAssoc line2) {
		// TODO Auto-generated method stub
		lineCount++;
		collection.add(line2);
	}*/
}