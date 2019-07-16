package com.danielmehlber.myui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

public class MyDialog extends JPanel implements Designable {

	MyDesign design;

	public MyDialog(MyDesign _design) {
		design = _design;

		Point point = new Point();
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				point.x = e.getX();
				point.y = e.getY();
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Point p = getLocation();
				setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
		g2d.setColor(design.dialogBaseColor);
		g2d.fillRoundRect(0, 0, getWidth(), getHeight(), design.dialogRoundness, design.dialogRoundness);
	}

	@Override
	public void applyDesign() {
		repaint();

	}

	@Override
	public MyDesign getDesign() {
		// TODO Auto-generated method stub
		return design;
	}

	@Override
	public void setDesign(MyDesign d) {
		design = d;

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
