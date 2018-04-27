import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;


public class Dependency extends AbstractLine {

	
	private Line line1 = new Line();
	private Line line2 = new Line();
	private Pane lines = new Pane();
	private VBox multiplicity = new VBox();
	private TextArea type = new TextArea();
	
	public Dependency(Pane c) {
		super(c);
		//stylize();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void stylize() {
		
		//type.setPromptText("multiplicity");
		// TODO Auto-generated method stub
		setStrokeWidth(5);
		line1.setStrokeWidth(5);
		line2.setStrokeWidth(5);
		line1.setOpacity(0);
		line2.setOpacity(0);
		type.setPrefWidth(40);
		type.setPrefHeight(5);
		//lines.getChildren().addAll(line1, line2,type);
		canvas.getChildren().addAll(line1,line2,type);

		getStrokeDashArray().addAll(5d, 5d);

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
		line1.setStartX(getEndX());
		line1.setStartY(getEndY());
		line1.setEndX(getEndX() + (30) * -Math.cos(0.5 + Math.atan2(getEndY()-getStartY(), getEndX() - getStartX())));
		line1.setEndY(getEndY() + (30) * -Math.sin(0.5 + Math.atan2(getEndY()-getStartY(), getEndX() - getStartX())));
		line2.setStartX(getEndX());
		line2.setStartY(getEndY());
		line2.setEndX(getEndX() + (30) * -Math.cos(-0.5 + Math.atan2(getEndY()-getStartY(), getEndX() - getStartX())));
		line2.setEndY(getEndY() + (30) * -Math.sin(-0.5 + Math.atan2(getEndY()-getStartY(), getEndX() - getStartX())));
		line1.setOpacity(1);
		line2.setOpacity(1);
		type.setLayoutX(getEndX());
		type.setLayoutY(getEndY());
		type.toFront();
	}

}
