package com.danielmehlber.myui;

import javax.swing.JLabel;

public class MyLabel extends JLabel implements Designable {

	private MyDesign design;

	public MyLabel(MyDesign design) {
		setDesign(design);
	}
	
	public MyLabel(MyDesign design, String text) {
		this(design);
		setText(text);
	}

	@Override
	public void applyDesign() {
		setFont(getDesign().font);
		setForeground(design.textColor);
	}

	@Override
	public MyDesign getDesign() {
		return design;
	}

	@Override
	public void setDesign(MyDesign d) {
		if (design != null)
			design.unregister(this);
		design = d;
		design.register(this);
		applyDesign();
	}

	@Override
	public void reset() {

	}

}
