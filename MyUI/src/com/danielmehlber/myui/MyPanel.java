package com.danielmehlber.myui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class MyPanel extends JPanel implements Designable{

    private int roundness = 15;
    private MyColor color = MyColor.WHITE;
    private MyColor headerColor = MyColor.BLACK;
	private String header;
	private MyDesign design;
	private boolean titled = true;
	
	public static enum HEADER_STYLE {
		TEXT, BOX
	}
	
	private HEADER_STYLE headerStyle = HEADER_STYLE.BOX;

    public MyPanel(MyDesign design){
    	setDesign(design);
    	setOpaque(false);
    	setLayout(null);
    }

    public MyPanel(MyDesign design, MyColor _color, int _roundness){
    	this(design);
        color =_color;
        roundness = _roundness;
    }

    @Override
	public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), roundness, roundness);
        if(!isTitled())
        	return;
        
        switch(headerStyle) {
		case BOX:
			g2d.setColor(design.accentColor);
			g2d.fillRoundRect(0, 0, getWidth(), 5+design.font.getSize()+5 , 3, 3);
			break;
		case TEXT:
			break;
		default:
			break;
        
        }
        
        g2d.setColor(headerColor);
        g2d.setFont(design.font);
        if(header != null)
        	g2d.drawChars(header.toCharArray(), 0, header.length(), 5, 5+design.font.getSize());
    }
    
    @Override
    public void setBackground(Color c) {
    	color = new MyColor(c);
    	repaint();
    	revalidate();
    }
    
    @Override
    public void setForeground(Color c) {
    	color = new MyColor(c);
    	repaint();
    	revalidate();
    }
    
    public void setBackground(MyColor c) {
    	color = c;
    	repaint();
    	revalidate();
    }
    
    public void setForeground(MyColor c) {
    	color = c;
    	repaint();
    	revalidate();
    }

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}
	
	public MyColor getHeaderColor() {
		return headerColor;
	}

	public void setHeaderColor(MyColor headerColor) {
		this.headerColor = headerColor;
	}

	@Override
	public void applyDesign() {
		repaint();
		
	}

	@Override
	public MyDesign getDesign() {
		return design;
	}

	@Override
	public void setDesign(MyDesign d) {
		if(design != null)
			design.unregister(this);
		d.register(this);
		design = d;
		applyDesign();
	}

	@Override
	public void reset() {
		
	}

	public boolean isTitled() {
		return titled;
	}

	public void setTitled(boolean titled) {
		this.titled = titled;
	}

	public HEADER_STYLE getHeaderStyle() {
		return headerStyle;
	}

	public void setHeaderStyle(HEADER_STYLE headerStyle) {
		this.headerStyle = headerStyle;
	}
    


}
