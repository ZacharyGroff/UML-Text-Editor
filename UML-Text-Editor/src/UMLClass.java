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
		setPrefHeight(250);;
		name.setWrapText(true);
		attr.setWrapText(true);
		op.setWrapText(true);
		/*name.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                 name.setPrefHeight(name.getPrefHeight()+1); 
            }
        });
		attr.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                 attr.setPrefHeight(attr.getPrefHeight()+10); 
            }
        });
		op.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                 op.setPrefHeight(op.getPrefHeight()+10); 
            }
        });*/
		setStyle("-fx-background-color: #00b8f5;\n" + "-fx-border-color: black;\n" + "-fx-border-width: 3;");
	}
	
	protected void highlight() {
		setStyle("-fx-background-color: yellow;\n" + "-fx-border-color: black;\n" + "-fx-border-width: 3;");
	}
	
	protected void unhighlight() {
		setStyle("-fx-background-color: #00b8f5;\n" + "-fx-border-color: black;\n" + "-fx-border-width: 3;");
	}
}