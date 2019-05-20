package com.danielmehlber.myui;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel{

    int roundness = 2;
    MyColor color = MyColor.WHITE;

    public MyPanel(){
    	setOpaque(false);
    }

    public MyPanel(MyColor _color, int _roundness){
    	this();
        color =_color;
        roundness = _roundness;
    }

    @Override
	public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.fillRoundRect(0,0, getWidth(), getHeight(), roundness, roundness);
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
    


}
