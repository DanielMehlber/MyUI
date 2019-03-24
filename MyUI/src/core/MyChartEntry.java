package core;

public class MyChartEntry implements Comparable<MyChartEntry>{

	private MyColor color;
	private int percent;
	
	public MyChartEntry(int _percent, MyColor _color) {
		color = _color;
		percent = _percent;
	}

	public MyColor getColor() {
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
