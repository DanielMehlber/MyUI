package core;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;

public class MyProgessChart extends JPanel{

	private int stroke_thickness = 30;
	private ArrayList<MyChartEntry> entries;
	private float fac = 0;
	boolean animation_running = false;
	
	public MyProgessChart() {
		entries = new ArrayList<MyChartEntry>();
		super.setOpaque(false);
	}
	
	public void add(MyChartEntry entry) {
		entries.add(entry);
	}
	
	public void add(int percent, MyColor color) {
		add(new MyChartEntry(percent, color));
	}
	
	
	public int getStrokeThickness() {
		return stroke_thickness;
	}

	public void setStrokeThickness(int stroke_thickness) {
		this.stroke_thickness = stroke_thickness;
	}


	public void setEntries(ArrayList<MyChartEntry> entries) {
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
		for(MyChartEntry entry : entries) {
			g2d.setColor(entry.getColor().getColor());
			g2d.drawArc(0+stroke_thickness, 0+stroke_thickness, getWidth() - stroke_thickness*2, getHeight() - stroke_thickness*2, 90, (int) (-360f/100f*entry.getPercent()*fac));
		}
		
	}
	
	public void animation(double speed) {
		if(animation_running)
			return;
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
			
				
				fac = 0;
				double i = 0;
				animation_running = true;
				while(fac < 1) {
					fac = (float) (0.5 * Math.sin(i - Math.PI / 2) + 0.51);
					i += 0.01;
					MySyncTask.sync((int) (100*speed));
					repaint();
				}
				fac = 1;
				repaint();
				animation_running = false;
			}
		});
		
		t.start();
	}
	
	public void go() {
		Collections.sort(entries);
		animation(1);
	}
	
	public ArrayList<MyChartEntry> getChartEntries(){
		return entries;
	}

	
}
