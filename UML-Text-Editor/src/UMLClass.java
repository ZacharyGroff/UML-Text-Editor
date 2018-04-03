import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;

/**
 * 
 */

/**
 * @author mattm
 *
 */
public class UMLClass extends VBox {
	// TODO find out how to expand textfields to support multiple lines
	// TODO support resizing
	TextArea name;
	TextArea attr;
	TextArea op;
	Text struct;
	static Boolean drag;
	Pane canvas;
	UMLClass ref;
	GenLine[] collection = new GenLine[10];
	int lineCount;
	Boolean lineExist;

	public UMLClass(Pane c,Text struct, TextArea className, TextArea classAttr, TextArea classOp) {
		super(struct, className, classAttr, classOp);
		this.name = className;
		this.attr = classAttr;
		this.op = classOp;
		this.struct = struct;
		canvas = c;
		ref = this;
		lineCount = 0;

		this.struct.setText("Structure");
		name.setPromptText("Name");
		attr.setPromptText("Attributes");
		op.setPromptText("Operations");
		setPrefWidth(150);
		setPrefHeight(250);
		wrapText(true);
		// TODO: Create CSS file instead of hard-coded styles.
		setStyle("-fx-background-color: #00b8f5;\n" + "-fx-border-color: black;\n" + "-fx-border-width: 3;");
		drag = true;
		lineExist = false;
		//dragable();
	}

	private void wrapText(boolean b) {
		// TODO Auto-generated method stub
		name.setWrapText(b);
		attr.setWrapText(b);
		op.setWrapText(b);

	}

	public void setDrag(Boolean b) {
		drag = b;
		dragable();
	}

	private void dragable() {
		// TODO Prevent objects from going off screen
		Delta d = new Delta();
		System.out.println(drag);

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
					if (lineExist) {
						for (int i = 0; i < lineCount; i++) {
						collection[i].update();
						System.out.println("aaa");
						}
					}
				}
			});
		} else {
			
			setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					int l = lineCount - 1;
					if(View.state == 1) {
					collection[l].setParent(ref);
					lineExist = true;
					View.state++;
					} else if (View.state == 2) {
						collection[l].setChild(ref);
						lineExist = true;
						//trueLine = line;
						View.state = 0;
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
		collection[lineCount++] = line2;
	}
}