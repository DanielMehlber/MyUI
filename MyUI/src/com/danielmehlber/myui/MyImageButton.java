package com.danielmehlber.myui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyImageButton extends MyPanel {

    private Image now;
    private Image normal;
    private Image hover;
    private Image click;
    private Runnable operation;

    public MyImageButton(Image n) {
        super(MyDesign.DEFAULT);
        this.normal = n;
        addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                now = n;
                if (operation != null)
                    operation.run();
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                now = click;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                now = n;
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                now = hover;
                repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub

            }
        });
    }

    public void paintComponent(Graphics g) {
        g.drawImage(now, 0, 0, getWidth(), getHeight(), null);
    }

    public Image getNow() {
        return now;
    }

    public void setNow(Image now) {
        this.now = now;
        repaint();
    }

    public Image getNormal() {
        return normal;
    }

    public void setNormal(Image normal) {
        this.normal = normal;
        repaint();
    }

    public Image getHover() {
        return hover;
    }

    public void setHover(Image hover) {
        this.hover = hover;
        repaint();
    }

    public Image getClick() {
        return click;
    }

    public void setClick(Image click) {
        this.click = click;
        repaint();
    }

    public Runnable getOperation() {
        return operation;
    }

    public void setOperation(Runnable operation) {
        this.operation = operation;
    }


}
