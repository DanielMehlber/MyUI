package com.danielmehlber.myui;

import java.awt.*;

public class MyChartEntry implements Comparable<MyChartEntry>{

	private Color color;
	private int percent;
	
	public MyChartEntry(int _percent, Color blue) {
		color = blue;
		percent = _percent;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(MyColor color) {
		this.color = color;
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

	@Override
	public int compareTo(MyChartEntry o) {
		int r = o.percent-percent;
		return r;
	}
	
	
	
	
	
}
