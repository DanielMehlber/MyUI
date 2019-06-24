package com.danielmehlber.myui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class MyTextEntry extends JPanel implements Designable, MyRunnable {
    ArrayList<Runnable> runnables;
    private Component entry;
    private MyDesign design;
    private MyColor textColor;
    private MyColor color;
    private int lineThickness = 2;
    private String subtext = "";
    private int subtextSize = 15;
    private Component verticalStrut;
    private int subtextOffsetY = 8;
    private int subtextOffsetX;
    private float line_fac = 1f;
    private boolean isBusy = false;
    private MY_TEXT_ENTRY_MODE mode;

    /**
     * Create the panel.
     *
     * @wbp.parser.constructor
     */
    public MyTextEntry(MyDesign design, MY_TEXT_ENTRY_MODE mode) {
        this.design = design;
        runnables = new ArrayList<>();
        setOpaque(false);
        setLayout(new BorderLayout(0, 0));
        setSize(200, 50);
        this.mode = mode;
        switch (mode) {
            case NORMAL: {
                JTextField textEntry = new JTextField();
                textEntry.setColumns(10);
                textEntry.setBorder(null);
                this.entry = textEntry;
                break;
            }
            case PASSWORD: {
                JPasswordField passwordEntry = new JPasswordField();
                passwordEntry.setBorder(null);
                this.entry = passwordEntry;
                break;
            }
        }

        add(entry);
        setDesign(design);

        verticalStrut = Box.createVerticalStrut(getFontMetrics(getFont().deriveFont(subtextSize)).getHeight() + subtextOffsetY + 4);
        add(verticalStrut, BorderLayout.SOUTH);
        entry.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {


            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    run();
                }

            }

            @Override
            public void keyPressed(KeyEvent e) {


            }
        });

    }

    public MyTextEntry(MyDesign design) {
        this(design, MY_TEXT_ENTRY_MODE.NORMAL);
    }

    @Override
    public void applyDesign() {
        if (design == null)
            return;
        setBackground(design.getBaseColor());
        entry.setBackground(design.getBaseColor());
        MyColor tc = design.getTextColor();
        if (textColor != null)
            tc = textColor;
        entry.setForeground(tc);
        entry.setFont(getFont());
        repaint();
    }

    @Override
    public MyDesign getDesign() {
        return design;
    }

    @Override
    public void setDesign(MyDesign d) {
        if (design != null)
            design.unregister(this);
        design = d;
        design.register(this);
        applyDesign();
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
        return design == null ? super.getFont() : design.font;
    }

    public void setFont(Font font) {
        super.setFont(font);
        applyDesign();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(getColor());
        g2d.setStroke(new BasicStroke(lineThickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
        g2d.drawLine(0, entry.getHeight() + 2, (int) (getWidth() * line_fac), entry.getHeight() + 2);
        g2d.setFont(design.font.deriveFont((float) subtextSize));
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

    public String getText() {
        String text = null;
        switch (mode) {
            case NORMAL:
                text = ((JTextField) entry).getText();
                break;

            case PASSWORD:
                text = ((JPasswordField) entry).getText();
                break;
        }
        return text;
    }

    public void setText(String text) {
        switch (mode) {
            case NORMAL: {
                ((JTextField) entry).setText(text);
                break;
            }

            case PASSWORD: {
                ((JPasswordField) entry).setText(text);
                break;
            }
        }


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
        if (color != null)
            c = color;
        return c;
    }

    public void setColor(MyColor color) {
        this.color = color;
        applyDesign();
    }

    public void animation_reunterline() {
        Thread t = new Thread(() -> {
            line_fac = 0f;
            repaint();
            double i = 0;
            while (line_fac < 1f) {
                MySyncTask.sync(120);
                line_fac = (float) (0.5 * Math.sin(i - Math.PI / 2) + 0.51);
                i += 0.05;
                repaint();
            }


        });
        t.start();
    }

    @Override
    public void addRunnable(Runnable r) {
        runnables.add(r);

    }

    @Override
    public void run() {
        for (Runnable r : runnables)
            r.run();

    }

    public void error(String message, float seconds) {

        Thread t = new Thread(() -> {
            String _subtext = subtext;
            setSubtext(message);
            MyColor _color = getColor();
            setColor(MyColor.RED);
            animation_reunterline();
            if (seconds <= 0)
                return;
            isBusy = true;
            MySyncTask.sleep((long) (1000 * seconds));
            setSubtext(_subtext);
            reset();
            animation_reunterline();
            isBusy = false;
        });

        if (isBusy)
            return;
        t.start();

    }

    public void setTextSize(float size) {
        setFont(getFont().deriveFont(size));
    }

    public enum MY_TEXT_ENTRY_MODE {
        NORMAL, PASSWORD
    }


}
