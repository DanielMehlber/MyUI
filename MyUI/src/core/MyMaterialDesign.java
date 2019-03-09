package core;

import java.awt.Font;
import java.util.ArrayList;

public class MyMaterialDesign {

	public MyColor baseColor;
	public MyColor accentColor;
	public MyColor textColor;
	
	public Font font;
	
	ArrayList<Designable> users = new ArrayList<Designable>();
	
	public enum FRAME_DESIGN {
		BAR, FLAT
	}
	
	
	public enum BUTTON_DESIGN {
		FLAT
	}
	
	public enum TOGGLE_BUTTON_DESIGN{
		FIELD, DOT, CHECKMARK
	}
	
	
	public FRAME_DESIGN frameTopDesign;
	public BUTTON_DESIGN buttonDesign;
	public TOGGLE_BUTTON_DESIGN toggleButtonDesign;
	
	public final static MyMaterialDesign DEFAULT = new MyMaterialDesign(new MyColor(240,240,240), MyColor.LIGHT_GRAY, MyColor.BLACK);
	public final static MyMaterialDesign FOX = new MyMaterialDesign(MyColor.DARK_GRAY, MyColor.ORANGE, MyColor.WHITE);
	public final static MyMaterialDesign SEA = new MyMaterialDesign(MyColor.MIDNIGHT_BLUE, MyColor.GREEN_SEA, MyColor.WHITE);
	public final static MyMaterialDesign PINK_NEON = new MyMaterialDesign(new MyColor(52,78,92), MyColor.PINK_NEON, MyColor.WHITE);
	public final static MyMaterialDesign BEACH = new MyMaterialDesign(new MyColor(71,92,122), new MyColor(252, 187, 109),  MyColor.WHITE);
	public final static MyMaterialDesign ORANGE_IS_THE_NEW_BLUE = new MyMaterialDesign(new MyColor(50, 93, 121), new MyColor(242,102,39), MyColor.WHITE);
	
	
	public MyMaterialDesign() {
		this(MyColor.WHITE, MyColor.WHITE, MyColor.BLACK);
	}
	
	public MyMaterialDesign(MyColor base, MyColor accent, MyColor text) {
		baseColor = base;
		accentColor = accent;
		textColor = text;
		frameTopDesign = FRAME_DESIGN.FLAT;
		font = new Font("Tahoma", Font.BOLD, 12);
		buttonDesign = BUTTON_DESIGN.FLAT;
		toggleButtonDesign = TOGGLE_BUTTON_DESIGN.FIELD;
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
	
	public void register(Designable obj) {
		users.add(obj);
	}
	
	public void unregister(Designable obj) {
		users.remove(obj);
	}
	
	public void apply() {
		for(Designable user : users) {
			user.applyDesign();
		}
	}
	
	

}
