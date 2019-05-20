package com.danielmehlber.myui;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel{

    int roundness = 2;
    MyColor color = MyColor.WHITE;

    public MyPanel(){

    }

    public MyPanel(MyColor _color, int roundness){
        color =_color;
    }

    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.drawRoundRect(0,0, getWidth(), getHeight(), roundness, roundness);
    }

}
