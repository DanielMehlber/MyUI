package core;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;

public class MyPieChart extends JPanel{

	private int stroke_thickness = 30;
	private ArrayList<MyPieChartEntry> entries;
	private float fac = 0;
	
	public MyPieChart() {
		entries = new ArrayList<MyPieChartEntry>();
		super.setOpaque(false);
	}
	
	public void add(MyPieChartEntry entry) {
		entries.add(entry);
		Collections.sort(entries);
	}
	
	public void add(int percent, MyColor color) {
		add(new MyPieChartEntry(percent, color));
	}
	
	
	
	public int getStrokeThickness() {
		return stroke_thickness;
	}

	public void setStrokeThickness(int stroke_thickness) {
		this.stroke_thickness = stroke_thickness;
	}


	public void setEntries(ArrayList<MyPieChartEntry> entries) {
		this.entries = entries;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY));
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
		g2d.setStroke(new BasicStroke(stroke_thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g2d.setColor(Color.darkGray);
		g2d.drawArc(0+stroke_thickness, 0+stroke_thickness, getWidth() - stroke_thickness*2, getHeight() - stroke_thickness*2, 90, 360);
		for(MyPieChartEntry entry : entries) {
			g2d.setColor(entry.getColor().getColor());
			g2d.drawArc(0+stroke_thickness, 0+stroke_thickness, getWidth() - stroke_thickness*2, getHeight() - stroke_thickness*2, 90, (int) (-360f/100f*entry.getPercent()*fac));
		}
		
	}
	
	public void animation(double speed) {
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				fac = 0;
				while(fac < 1) {
					fac+=0.01;
					MySyncTask.sync((int) (100*speed));
					repaint();
				}
				fac = 1;
				repaint();
			}
		});
		
		t.start();
	}
	
	public void go() {
		animation(1);
	}
	
}
