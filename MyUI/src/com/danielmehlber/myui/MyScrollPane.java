package com.danielmehlber.myui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 * Scroll Pane
 */
public class MyScrollPane extends MyPage implements Designable{

    MyDesign design;
    MyPanel scrollBarY;
    MyPanel scrollerY;
    JPanel contentPane;
    JPanel content;
    int scrollerWidth = 10;

    public MyScrollPane(MyDesign design, boolean x, boolean y){
        setLayout(new BorderLayout(0, 0));
        scrollBarY = new MyPanel(design.baseColor, 0);
        scrollBarY.setBorder(null);
        contentPane = new MyPage(design);
        add(contentPane, BorderLayout.CENTER);
        add(scrollBarY, BorderLayout.EAST);
        scrollBarY.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        scrollerY = new MyPanel(design.accentColor, 5);
        scrollerY.setBorder(null);
        scrollBarY.add(scrollerY);
        scrollerY.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        setDesign(design);
        
        scrollBarY.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
        

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
        this.design = d;
        super.setDesign(d);
        super.applyDesign();
        applyDesign();
    }

    @Override
    public void reset() {

    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

    }

    public void setContentPane(JPanel p){
        contentPane.removeAll();
        contentPane.add(p, BorderLayout.CENTER);
        content = p;
    }

    public void updateSize(){
    	if(content == null) {
    		scrollerY.setVisible(false);
    	}
    	scrollerY.setVisible(true);
        Dimension selfSize = getSize();
        Dimension contentSize = content.getSize();
        float scrollfacX = (float)selfSize.getWidth() / (float)contentSize.getWidth();
        float scrollfacY = (float)selfSize.getHeight() / (float)contentSize.getHeight();
        if(scrollfacY >= 1)
            scrollerY.setVisible(false);
        else
            scrollerY.setSize(scrollerWidth, (int)((float)getHeight() * scrollfacY));
    }

}
