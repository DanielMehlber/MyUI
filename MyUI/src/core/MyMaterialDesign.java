package core;

import java.awt.Font;

public class MyMaterialDesign {

	public MyColor baseColor;
	public MyColor accentColor;
	public MyColor textColor;
	
	public Font font;
	
	public enum FRAME_DESIGN {
		BAR, FLAT
	}
	
	public enum BUTTON_COLOR_DESIGN {
		ACCENT, BASE
	}
	
	public enum BUTTON_DESIGN {
		FLAT
	}
	
	
	public FRAME_DESIGN frameTopDesign;
	public BUTTON_COLOR_DESIGN buttonColorDesign;
	public BUTTON_DESIGN buttonDesign;
	
	final static MyMaterialDesign DEFAULT = new MyMaterialDesign();
	
	
	public MyMaterialDesign() {
		this(MyColor.WHITE, MyColor.WHITE, MyColor.BLACK);
	}
	
	public MyMaterialDesign(MyColor base, MyColor accent, MyColor text) {
		baseColor = base;
		accentColor = accent;
		textColor = text;
		frameTopDesign = FRAME_DESIGN.FLAT;
		font = new Font("Tahoma", Font.PLAIN, 10);
		buttonColorDesign = BUTTON_COLOR_DESIGN.ACCENT;
		buttonDesign = BUTTON_DESIGN.FLAT;
	}

	public MyColor getBaseColor() {
		return baseColor;
	}

	public void setBaseColor(MyColor base_color) {
		this.baseColor = base_color;
	}

	public MyColor getAccentColor() {
		return accentColor;
	}

	public void setAccentColor(MyColor accent_color) {
		this.accentColor = accent_color;
	}

	public MyColor getTextColor() {
		return textColor;
	}

	public void setTextColor(MyColor text_color) {
		this.textColor = text_color;
	}

	public FRAME_DESIGN getFrameTopDesign() {
		return frameTopDesign;
	}

	public void setFrameTopDesign(FRAME_DESIGN frame_design) {
		this.frameTopDesign = frame_design;
	}
	
	
	
	

}
