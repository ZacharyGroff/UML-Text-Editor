import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
public class Package extends Structure {
	
	public Package(View view) {
		super(view, new Text(), new TextArea());
		stylize();
		//System.out.println(this);
	}

	protected void stylize() {
		// TODO Auto-generated method stub
		type.setText("Package");
		name.setPromptText("Name");
		setPrefWidth(125);
		setPrefHeight(100);
		setStyle("-fx-background-color: red;\n");
	}
	
	protected void unhighlight() {
		setStyle("-fx-background-color: red;\n");
	}
	
	protected void highlight() {
		setStyle("-fx-background-color: yellow;\n");
	}
}