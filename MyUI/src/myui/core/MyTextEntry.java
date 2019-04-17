package myui.core;

import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JTextField;


import java.awt.Component;
import javax.swing.Box;

public class MyTextEntry extends JPanel implements Designable{
	private Component entry;
	private MyMaterialDesign design;
	private MyColor textColor;
	private MyColor color;
	private int lineThickness = 2;
	private String subtext = "";
	private int subtextSize = 8;
	private Component verticalStrut;
	private int subtextOffsetY = 8;
	private int subtextOffsetX;
	
	public enum MY_TEXT_ENTRY_MODE {
		NORMAL, PASSWORD
	}
	
	private MY_TEXT_ENTRY_MODE mode;
	/**
	 * Create the panel.
	 * @wbp.parser.constructor
	 */
	public MyTextEntry(MyMaterialDesign design, MY_TEXT_ENTRY_MODE mode) {
		setOpaque(false);
		setLayout(new BorderLayout(0, 0));
		this.mode = mode;
		switch(mode) {
		case NORMAL: {
			JTextField textEntry = new JTextField();
			textEntry.setColumns(10);
			textEntry.setBorder(null);
			this.entry = textEntry;
			break;}
		case PASSWORD: {
			JPasswordField passwordEntry = new JPasswordField();
			passwordEntry.setBorder(null);
			this.entry = passwordEntry;
			break;}
		}
		
		add(entry);
		setDesign(design);
		reset();
		
		verticalStrut = Box.createVerticalStrut(getFontMetrics(getFont().deriveFont(subtextSize)).getHeight() + subtextOffsetY + 4);
		add(verticalStrut, BorderLayout.SOUTH);
		
	}
	
	public MyTextEntry(MyMaterialDesign design) {
		this(design, MY_TEXT_ENTRY_MODE.NORMAL);
	}

	@Override
	public void applyDesign() {
		if(getDesign() == null)
			return;
		
		//Set Background
		setBackground(design.getBaseColor());
		entry.setBackground(design.getBaseColor());
		
		//Set Text Color
		MyColor tc = design.getTextColor();
		if(textColor != null)
			tc = textColor;
		entry.setForeground(tc);
		
		//Set Text Font
		Font f = design.font;
		if(super.getFont() != null) {
			f = super.getFont();
		}
		entry.setFont(f);
		
	}

	@Override
	public void setDesign(MyMaterialDesign d) {
		design = d;
		applyDesign();
	}

	@Override
	public MyMaterialDesign getDesign() {
		return design;
	}

	@Override
	public void reset() {
		setFont(null);
		textColor = null;
		color = null;
		applyDesign();
	}

	public MyColor getTextColor() {
		return textColor;
	}

	public void setTextColor(MyColor textColor) {
		this.textColor = textColor;
		applyDesign();
	}

	public Font getFont() {
		if(design == null)
			return super.getFont();
		
		Font f = design.font;
		if(super.getFont() != null)
			f = super.getFont();
		return f;
	}

	public void setFont(Font font) {
		System.out.println(font);
		super.setFont(font);
		applyDesign();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(getColor());
		g2d.setStroke(new BasicStroke(lineThickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
		g2d.drawLine(0, entry.getHeight() + 2, getWidth(), entry.getHeight()+2);
		g2d.setFont(design.font.deriveFont(subtextSize));
		g2d.drawString(subtext, subtextOffsetX, entry.getHeight() + subtextSize + subtextOffsetY);
	}

	public int getLineThickness() {
		return lineThickness;
	}

	public void setLineThickness(int lineThickness) {
		this.lineThickness = lineThickness;
		repaint();
	}

	public String getSubtext() {
		return subtext;
	}

	public void setSubtext(String subtext) {
		this.subtext = subtext;
		repaint();
	}
	
	public void setText(String text) {
		switch(mode) {
		case NORMAL: {
			((JTextField)entry).setText(text);
			break;}
		
		case PASSWORD: {
			((JPasswordField)entry).setText(text);
			break;
		}
		}
		
		
	}
	
	public String getText() {
		String text = null;
		switch(mode) {
		case NORMAL: {
			text = ((JTextField)entry).getText();
			break;}
		
		case PASSWORD: {
			text = ((JPasswordField)entry).getText();
			break;
		}
		}
		return text;
	}

	public int getSubtextSize() {
		return subtextSize;
	}

	public void setSubtextSize(int subtextSize) {
		this.subtextSize = subtextSize;
		verticalStrut = Box.createVerticalStrut(subtextSize + subtextOffsetY);
		repaint();
	}

	public MyColor getColor() {
		MyColor c = design.getAccentColor();
		if(color != null)
			c = color;
		return c;
	}

	public void setColor(MyColor color) {
		this.color = color;
		applyDesign();
	}
	
	
	
	
	
}
