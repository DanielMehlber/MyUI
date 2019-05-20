package com.danielmehlber.myui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class MyScrollPane extends JPanel implements Designable{

    MyDesign design;
    MyPanel scrollBarY;
    MyPanel scrollerY;
    JPanel content;
    int scrollerWidth;

    public MyScrollPane(MyDesign design, boolean x, boolean y){
        scrollBarY = new MyPanel();
        scrollerY = new MyPanel();
        content = new MyPage(design);
        add(content, BorderLayout.CENTER);
        add(scrollBarY, BorderLayout.EAST);
        scrollBarY.add(scrollerY);
        setDesign(design);

        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateSize();
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

    @Override
    public void applyDesign() {
        setBackground(design.baseColor);
        scrollBarY.setBackground(design.baseColor);
        scrollerY.setBackground(design.accentColor);
    }

    @Override
    public MyDesign getDesign() {
        return design;
    }

    @Override
    public void setDesign(MyDesign d) {
        this.design = design;
    }

    @Override
    public void reset() {

    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

    }

    public void setContentPane(JPanel p){
        content.removeAll();
        content.add(p, BorderLayout.CENTER);
    }

    public void updateSize(){
        Dimension selfSize = getSize();
        Dimension contentSize = content.getSize();
        float scrollfacX = (float)selfSize.getWidth() / (float)contentSize.getWidth();
        float scrollfacY = (float)selfSize.getHeight() / (float)contentSize.getHeight();
        if(scrollfacX >= 1)
            scrollerY.setVisible(false);
        else
            scrollerY.setSize(scrollerWidth, (int)((float)getHeight() * scrollfacY));
    }

}
