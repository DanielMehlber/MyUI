package core;

public class MyDesign {

	private MyColor base_color;
	private MyColor accent_color;
	private MyColor text_color;
	
	final static MyDesign DEFAULT = new MyDesign();
	
	
	public MyDesign() {
		this(MyColor.WHITE, MyColor.WHITE, MyColor.BLACK);
	}
	
	public MyDesign(MyColor base, MyColor accent, MyColor text) {
		base_color = base;
		accent_color = accent;
		text_color = text;
	}

	public MyColor getBaseColor() {
		return base_color;
	}

	public void setBaseColor(MyColor base_color) {
		this.base_color = base_color;
	}

	public MyColor getAccentColor() {
		return accent_color;
	}

	public void setAccentColor(MyColor accent_color) {
		this.accent_color = accent_color;
	}

	public MyColor getTextColor() {
		return text_color;
	}

	public void setTextColor(MyColor text_color) {
		this.text_color = text_color;
	}
	
	

}
