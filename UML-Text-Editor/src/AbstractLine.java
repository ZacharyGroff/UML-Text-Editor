import javafx.scene.shape.Line;
import javafx.scene.layout.Pane;

public abstract class AbstractLine extends Line {

	Pane canvas;
	double x1, x2, y1, y2;
	Structure parent;
	Structure child;
	AbstractLine ref;

	public AbstractLine(Pane c) {
		super();
		setMouseTransparent(true);
		canvas = c;
		ref = this;
		//setStrokeWidth(5);
		setOpacity(0);
	}
	
	protected abstract void stylize();
	
	public void setParent(Structure p) {
		parent = p;
		setStartX(parent.getLayoutX() + parent.getWidth() / 2);
		setStartY(parent.getLayoutY() + parent.getHeight() / 2);
	}

	public void setChild(Structure c) {
		child = c;
		//setEndX(child.getLayoutX() + child.getWidth() / 2);
		//setEndY(child.getLayoutY() + child.getHeight() / 2);
		stylize();
		canvas.getChildren().add(ref);
		toBack();
		System.out.println(getStartX() + " " + getStartY());
		updatePoints();
		setOpacity(1);
	}

	public void updatePoints() {
		// TODO Auto-generated method stub
		if (Math.abs(child.getLayoutX() - parent.getLayoutX()) > Math.abs(child.getLayoutY() - parent.getLayoutY())) {
			if (child.getLayoutX() > parent.getLayoutX()) {
				setStartX(parent.getLayoutX() + parent.getWidth());
				setStartY(parent.getLayoutY() + parent.getHeight() / 2);
				setEndX(child.getLayoutX());
				setEndY(child.getLayoutY() + child.getHeight() / 2);
			} else {
				setStartX(parent.getLayoutX());
				setStartY(parent.getLayoutY() + parent.getHeight() / 2);
				setEndX(child.getLayoutX() + child.getWidth());
				setEndY(child.getLayoutY() + child.getHeight() / 2);
			}
		}
		if (Math.abs(child.getLayoutX() - parent.getLayoutX()) < Math.abs(child.getLayoutY() - parent.getLayoutY())) {
			if (child.getLayoutY() > parent.getLayoutY()) {
				setStartX(parent.getLayoutX() + parent.getWidth() / 2);
				setStartY(parent.getLayoutY() + parent.getHeight());
				setEndX(child.getLayoutX() + child.getWidth() / 2);
				setEndY(child.getLayoutY());
			} else {
				setStartX(parent.getLayoutX() + parent.getWidth() / 2);
				setStartY(parent.getLayoutY());
				setEndX(child.getLayoutX() + child.getWidth() / 2);
				setEndY(child.getLayoutY() + child.getHeight());
			}
		}
	}

	/*public BinAssoc(double a, double b, double c, double d) {
		x1 = a;
		x2 = b;
		y1 = c;
		y2 = d;
		setStartX(x1);
		setStartY(y1);
		setEndX(x2);
		setEndY(y2);
		setStrokeWidth(5);
	}*/

	/*public void update() {
		// TODO Auto-generated method stub
		if (parent != null) {
			setStartX(parent.getLayoutX() + parent.getWidth() / 2);
			setStartY(parent.getLayoutY() + parent.getHeight() / 2);
		}
		if (child != null) {
			setEndX(child.getLayoutX() + child.getWidth() / 2);
			setEndY(child.getLayoutY() + child.getHeight() / 2);
		}
	}*/

	public Structure getLineChild() {
		return child;
	}

	public Structure getLineParent() {
		return parent;
	}
}