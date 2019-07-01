package com.danielmehlber.myui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class MyPanel extends JPanel implements Designable{

    private int roundness = 15;
    public int getRoundness() {
		return roundness;
	}

	public void setRoundness(int roundness) {
		this.roundness = roundness;
	}

	private MyColor color = MyColor.WHITE;
    private MyColor headerColor = MyColor.BLACK;
	private String header;
	private MyDesign design;
	private boolean titled = true;
	
	public static enum HEADER_STYLE {
		TEXT, BOX
	}
	
	public static enum COLOR_STYLE{
		DESIGN_BASE, DESIGN_ACCENT, CUSTOM
	}
	
	private COLOR_STYLE colorStyle = COLOR_STYLE.CUSTOM;
	
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
        
        g2d.setColor(getColor());
        
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
    
    public void setColor(MyColor c) {
    	color = c;
    	repaint();
    }
    
    public Color getColor() {
    	return color;
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
		MyColor r = color;
    	switch(colorStyle) {
		case CUSTOM:
			break;
		case DESIGN_ACCENT:
			r = design.accentColor;
			break;
		case DESIGN_BASE:
			r = design.baseColor;
			break;
		default:
			break;
    	
    	}
    	color = r;
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
		switch(colorStyle) {
		
		case CUSTOM:
			break;
		case DESIGN_ACCENT:
			color = design.getAccentColor();
			break;
		case DESIGN_BASE:
			color = design.getBaseColor();
			break;
		default:
			break;
		
		}
	}

	public boolean isTitled() {
		return titled;
	}

	public void setTitled(boolean titled) {
		this.titled = titled;
		repaint();
	}

	public HEADER_STYLE getHeaderStyle() {
		return headerStyle;
	}

	public void setHeaderStyle(HEADER_STYLE headerStyle) {
		this.headerStyle = headerStyle;
		repaint();
	}

	public COLOR_STYLE getColorStyle() {
		return colorStyle;
	}

	public void setColorStyle(COLOR_STYLE colorStyle) {
		this.colorStyle = colorStyle;
		applyDesign();
	}
    


}
