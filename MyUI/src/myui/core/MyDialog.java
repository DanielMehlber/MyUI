package myui.core;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class MyDialog extends JPanel implements Designable{

	MyMaterialDesign design;
	
	public MyDialog(MyMaterialDesign _design) {
		design = _design;
		
		Point point = new Point();
		addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                point.x = e.getX();
                point.y = e.getY();
            }
        });
		addMouseMotionListener(new MouseMotionAdapter() {
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
	public void setDesign(MyMaterialDesign d) {
		design = d;
		
	}

	@Override
	public MyMaterialDesign getDesign() {
		// TODO Auto-generated method stub
		return design;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
