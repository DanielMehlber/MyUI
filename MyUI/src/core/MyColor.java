package core;

import java.awt.Color;

public class MyColor {

	private Color color;
	public final static MyColor BLACK = new MyColor(0,0,0);
	public final static MyColor WHITE = new MyColor(255,255,255);
	public final static MyColor RED = new MyColor(255,0,0);
	public final static MyColor BLUE = new MyColor(0,0,255);
	public final static MyColor GREEN = new MyColor(0,255,0);
	public final static MyColor LIGHT_GRAY = new MyColor(Color.LIGHT_GRAY);
	public final static MyColor DARK_GRAY = new MyColor(Color.DARK_GRAY);
	public final static MyColor CYAN = new MyColor(Color.CYAN);
	public final static MyColor MAGENTA = new MyColor(Color.MAGENTA);
	public final static MyColor ORANGE = new MyColor(Color.ORANGE);
	public final static MyColor YELLOW = new MyColor(Color.YELLOW);
	public final static MyColor PINK = new MyColor(Color.PINK);
	public final static MyColor GREEN_EMERALD = new MyColor(46, 204, 113);
	public final static MyColor ORANGE_PUMPKIN = new MyColor(211, 84, 0);
	public final static MyColor MIDNIGHT_BLUE = new MyColor(44, 62, 80);
	public final static MyColor YELLOW_SUNFLOWER = new MyColor(241, 196, 15);
	public final static MyColor GREEN_SEA = new MyColor(22, 160, 133);
	public final static MyColor PINK_WATERMELON = new MyColor(255, 71, 87);
	public final static MyColor PINK_NEON = new MyColor(204, 42, 73);
	
	
	public MyColor() {
		color = MyColor.BLACK.getColor();
	}
	
	public MyColor(int r ,int g, int b) {
		color = new Color(r,g,b);
	}
	
	public MyColor(Color c) {
		color = c;
	}
	
	public Color getColor() {
		return color;
	}
	
	public MyColor darker(int i) {
		int r = color.getRed();
		int g = color.getGreen();
		int b = color.getBlue();
		int delta_r = r - i;
		int delta_g = g - i;
		int delta_b = b - i;
		if(delta_r < 0)
			delta_r = i - delta_r;
		if(delta_g < 0)
			delta_g = i - delta_g;
		if(delta_b < 0)
			delta_b = i - delta_b;
		
		return new MyColor(new Color(delta_r, delta_g, delta_b));
	}

}
