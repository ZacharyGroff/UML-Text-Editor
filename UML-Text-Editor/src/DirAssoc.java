import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class DirAssoc extends AbstractLine{
	
	//private Line line1 = new Line();
	//private Line line2 = new Line();
	Polygon triangle = new Polygon();
	Pane canvas;

	public DirAssoc(Pane c) {
		super(c);
		canvas = c;
		//stylize();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void stylize() {
		// TODO Auto-generated method stub
		setStrokeWidth(5);
		triangle.setStrokeWidth(5);
		triangle.setStroke(Color.BLACK);
		triangle.setOpacity(0);
		triangle.setFill(Color.WHITE);
		canvas.getChildren().addAll(triangle);//line2);
	}
	
	@Override
	public void updatePoints() {	
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
		triangle.getPoints().removeAll();
		triangle.getPoints().setAll(
				getEndX(), getEndY(),
				getEndX() + (30) * -Math.cos(0.5 + Math.atan2(getEndY()-getStartY(), getEndX() - getStartX())),
				getEndY() + (30) * -Math.sin(0.5 + Math.atan2(getEndY()-getStartY(), getEndX() - getStartX())),
				getEndX() + (30) * -Math.cos(-0.5 + Math.atan2(getEndY()-getStartY(), getEndX() - getStartX())),
				getEndY() + (30) * -Math.sin(-0.5 + Math.atan2(getEndY()-getStartY(), getEndX() - getStartX())));
		triangle.setOpacity(1);
		/*line1.setStartX(getEndX());
		line1.setStartY(getEndY());
		line1.setEndX(getEndX() + (30) * -Math.cos(0.5 + Math.atan2(getEndY()-getStartY(), getEndX() - getStartX())));
		line1.setEndY(getEndY() + (30) * -Math.sin(0.5 + Math.atan2(getEndY()-getStartY(), getEndX() - getStartX())));
		line2.setStartX(getEndX());
		line2.setStartY(getEndY());
		line2.setEndX(getEndX() + (30) * -Math.cos(-0.5 + Math.atan2(getEndY()-getStartY(), getEndX() - getStartX())));
		line2.setEndY(getEndY() + (30) * -Math.sin(-0.5 + Math.atan2(getEndY()-getStartY(), getEndX() - getStartX())));
		line1.setOpacity(1);
		line2.setOpacity(1);*/
	}

	@Override
	protected void remove() {
		// TODO Auto-generated method stub
		canvas.getChildren().removeAll(this, triangle);
	}

}