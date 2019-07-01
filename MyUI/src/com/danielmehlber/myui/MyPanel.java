package com.danielmehlber.myui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class MyPanel extends JPanel implements Designable {

    private int roundness = 15;
    private MyColor color = MyColor.WHITE;
    private MyColor headerColor = MyColor.BLACK;
    private String header;
    private MyDesign design;
    private boolean titled = true;
    private COLOR_STYLE colorStyle = COLOR_STYLE.CUSTOM;
    private HEADER_STYLE headerStyle = HEADER_STYLE.BOX;

    public MyPanel(MyDesign design) {
        setDesign(design);
        setOpaque(false);
        setLayout(null);
    }

    public MyPanel(MyDesign design, MyColor _color, int _roundness) {
        this(design);
        color = _color;
        roundness = _roundness;
    }

    public int getRoundness() {
        return roundness;
    }

    public void setRoundness(int roundness) {
        this.roundness = roundness;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setColor(getColor());
        

        switch (colorStyle) {
            case CUSTOM:
                g2d.setColor(color);
                break;
            case DESIGN_ACCENT:
                g2d.setColor(design.accentColor);
                break;
            case DESIGN_BASE:
                g2d.setColor(design.baseColor);
                break;
            default:
                break;

        }

        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), roundness, roundness);
        if (!isTitled())
            return;

        switch (headerStyle) {
            case BOX:
                g2d.setColor(design.accentColor);
                g2d.fillRoundRect(0, 0, getWidth(), 5 + design.font.getSize() + 5, 3, 3);
                break;
            case TEXT:
                break;
            default:
                break;

        }

        g2d.setColor(headerColor);
        g2d.setFont(design.font);
        if (header != null)
            g2d.drawChars(header.toCharArray(), 0, header.length(), 5, 5 + design.font.getSize());
    }
    
    public void setColor(MyColor c) {
    	color = c;
    	repaint();
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

    
    public Color getColor() {
    	return color;
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

    public void setHeaderColor(MyColor headerColor) {
        this.headerColor = headerColor;
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

    @Override
    public MyDesign getDesign() {
        return design;
    }

    @Override
    public void setDesign(MyDesign d) {
        if (design != null)
            design.unregister(this);
        d.register(this);
        design = d;
        applyDesign();
    }


    public boolean isTitled() {
        return titled;
    }

    public void setTitled(boolean titled) {
        this.titled = titled;
        repaint();
    }


	public void setColorStyle(COLOR_STYLE colorStyle) {
		this.colorStyle = colorStyle;
		applyDesign();
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


    public static enum HEADER_STYLE {
        TEXT, BOX
    }

    public static enum COLOR_STYLE {
        DESIGN_BASE, DESIGN_ACCENT, CUSTOM
    }



}
