import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

class FernData{
	private double x;
	private double y;
	private Circle circle;
	
	FernData(){
		this(0.0, 0.0);
		circle = new Circle(640 + x, 720 - y, 1, Color.GREEN);
	}
	
	FernData(double x, double y){
		this.x = x;
		this.y = y;
		circle = new Circle(640 + x, 720 - y, 1, Color.GREEN);
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public Circle getCircle(){
		return circle;
	}
}