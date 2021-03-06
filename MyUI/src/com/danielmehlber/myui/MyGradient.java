package com.danielmehlber.myui;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class MyGradient extends JPanel {

	MyDirection direction;
	Point point1;
	Point point2;
	MyColor color1;
	MyColor color2;
	Dimension last;

	public MyGradient(MyDirection dir, MyColor c1, MyColor c2) {
		setOpaque(false);
		color1 = c1;
		color2 = c2;
		setDirection(dir);
		last = new Dimension(getWidth(), getHeight());

		addComponentListener(new ComponentListener() {
			@Override
			public void componentResized(ComponentEvent e) {
				setDirection(direction);
			}

			@Override
			public void componentMoved(ComponentEvent e) {

			}

			@Override
			public void componentShown(ComponentEvent e) {

			}

			@Override
			public void componentHidden(ComponentEvent e) {

			}
		});

	}

	public void setDirection(MyDirection dir) {
		direction = dir;
		switch (dir) {
		case NORTH:
			point1 = new Point(getWidth() / 2, getHeight());
			point2 = new Point(getWidth() / 2, 0);
			break;
		case SOUTH:
			point1 = new Point(getWidth() / 2, 0);
			point2 = new Point(getWidth() / 2, getHeight());
			break;
		case WEST:
			point1 = new Point(getWidth(), getHeight() / 2);
			point2 = new Point(0, getHeight() / 2);
			break;
		case EAST:
			point1 = new Point(0, getHeight() / 2);
			point2 = new Point(getWidth(), getHeight() / 2);
			break;
		default:
			break;
		}
	}

	@Override
	public void paint(Graphics g) {
		if (!last.equals(new Dimension(getWidth(), getHeight())))
			setDirection(direction);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
		g2d.setPaint(new GradientPaint(point1.x, point1.y, color1, point2.x, point2.y, color2));
		// g2d.setPaint(new GradientPaint(0, getHeight()/2, MyColor.black, getWidth(),
		// getHeight()/2, MyColor.white));
		g2d.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
	}

}
