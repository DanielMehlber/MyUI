package core;

import java.awt.Color;

public class MyColor {

	private Color color;
	final static MyColor BLACK = new MyColor(0,0,0);
	final static MyColor WHITE = new MyColor(1,1,1);
	final static MyColor RED = new MyColor(1,0,0);
	final static MyColor BLUE = new MyColor(0,0,1);
	final static MyColor GREEN = new MyColor(0,1,0);
	public MyColor() {
		color = MyColor.BLACK.getColor();
	}
	
	public MyColor(float r , float g, float b) {
		
	}
	
	public Color getColor() {
		return color;
	}

}
