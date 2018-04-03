import javafx.scene.shape.Line;
import javafx.scene.layout.Pane;

public class GenLine extends Line {
	
	Pane canvas;
	double x1, x2, y1, y2;
	
	public GenLine(Pane c) {
		super();
		setMouseTransparent(true);
		canvas = c;
	}
	
	public GenLine (double a, double b, double c, double d) {
		x1 = a;
		x2 = b;
		y1 = c;
		y2 = d;
		setStartX(x1);
		setStartY(y1);
		setEndX(x2);
		setEndY(y2);
		setStrokeWidth(5);
	}
	
}