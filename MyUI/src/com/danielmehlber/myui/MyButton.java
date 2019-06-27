package com.danielmehlber.myui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MyButton extends JPanel implements Designable, MyRunnable {

    final ArrayList<Runnable> runnables;

    /**
     * Design of the Component
     */
    private MyDesign design;

    /**
     * Custom font to be used instead
     */
    private Font customFont = null;

    /**
     * Custom text color to be used instead
     */
    private MyColor customTextColor = null;

    /**
     * Custom Button color to be used instead
     */
    private MyColor customColor = null;

    private MyColor restore_color;

    /**
     * Label printed on the button
     */
    private final JLabel text;

    /**
     * Runs at click
     */

    /**
     * Controlles the Gap between Button and Shadow
     */
    private int shadowGap = 1;
    /**
     * Alpha value of Shadow
     */
    private int shadowAlpha = 50;
    /**
     * Color of Shadow
     */
    private MyColor shadowColor = MyColor.BLACK;
    /**
     * has Shadow ?
     */
    private boolean shady = true;
    /**
     * Controlles the corner rounding of the button
     */
    private int roundness = 10;
    /**
     * X Offset of Shadow
     */
    private int shadowXOffset = 0;
    /**
     * Y Offset of Shadow
     */
    private int shadowYOffset = 3;
    /**
     * Thickness of stroke
     */
    private final int strokeSize = 1;
    /**
     * Restoration value (temporary)
     */
    private int restore_shadow_gap;

    /**
     * Create the panel.
     */
    public MyButton(MyDesign design, String s) {
        runnables = new ArrayList<>();
        setLayout(new BorderLayout(0, 0));
        setBounds(0, 0, 150, 40);
        text = new JLabel(s);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        add(text, BorderLayout.CENTER);
        setDesign(design);
        setOpaque(false);

        addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                release();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                press();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub

            }
        });
    }

    @Override
    public void applyDesign() {

        text.setFont(getTextFont());
        setBackground(getColor());
        text.setForeground(getTextColor());
        repaint();

    }

    /**
     * Returns the text of Button
     *
     * @return text
     */
    public String getText() {
        return text.getText();
    }

    /**
     * Sets the text of the button
     *
     * @param text
     */
    public void setText(String text) {
        this.text.setText(text);
    }

    @Override
    public void reset() {
        customFont = null;
        customTextColor = null;
        customColor = null;
    }

    @Override
    public MyDesign getDesign() {
        // TODO Auto-generated method stub
        return design;
    }

    @Override
    public void setDesign(MyDesign d) {
        if (design != null)
            this.design.unregister(this);
        design = d;
        design.register(this);
        applyDesign();
    }

    /**
     * Enables or Disables the button
     */
    public void setEnabled(boolean e) {
        super.setEnabled(e);

        if (e)
            reset();
        else
            customColor = getColor().darker(50);

        applyDesign();
    }

    /**
     * Returns currently used color (design or custom)
     *
     * @return
     */
    public MyColor getColor() {
        if (customColor == null)
            return design.accentColor;
        return customColor;
    }

    public void setColor(MyColor customColor) {
        this.customColor = customColor;
    }

    /**
     * Returns currently used text color (design or custom)
     *
     * @return
     */
    public MyColor getTextColor() {
        if (customTextColor == null)
            return design.textColor;
        return customTextColor;
    }

    public void setTextColor(MyColor customTextColor) {
        this.customTextColor = customTextColor;
    }

    /**
     * Returns currently used font (design or custom)
     *
     * @return
     */
    public Font getTextFont() {
        if (customFont == null)
            return design.font;
        return customFont;
    }

    public void setTextFont(Font customFont) {
        this.customFont = customFont;
    }

    public int getShadowGap() {
        return shadowGap;
    }

    public void setShadowGap(int shadowGap) {
        this.shadowGap = shadowGap;
    }

    public int getShadowAlpha() {
        return shadowAlpha;
    }

    public void setShadowAlpha(int shadowAlpha) {
        this.shadowAlpha = shadowAlpha;
    }

    public MyColor getShadowColor() {
        return shadowColor;
    }

    public void setShadowColor(MyColor shadowColor) {
        this.shadowColor = shadowColor;
    }

    public boolean isShadow() {
        return shady;
    }

    public void setShadow(boolean shady) {
        this.shady = shady;
    }

    public int getCornersRounding() {
        return roundness;
    }

    public void setCornersRounding(int cornersRounding) {
        this.roundness = cornersRounding;
    }

    public int getShadowXOffset() {
        return shadowXOffset;
    }

    public void setShadowXOffset(int shadowOffset) {
        this.shadowXOffset = shadowOffset;
    }

    public int getShadowYOffset() {
        return shadowYOffset;
    }

    public void setShadowYOffset(int shadowOffset) {
        this.shadowYOffset = shadowOffset;
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        int shadowGap = this.shadowGap;
        Color shadowColorA = new Color(shadowColor.getRed(),
                shadowColor.getGreen(), shadowColor.getBlue(), shadowAlpha);
        Graphics2D graphics = (Graphics2D) g;


        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);


        //Draws shadow borders if any.
        if (shady) {
            graphics.setColor(shadowColorA);
            graphics.fillRoundRect(
                    shadowXOffset,// X position
                    shadowYOffset,// Y position
                    width - strokeSize - shadowXOffset, // width
                    height - strokeSize - shadowYOffset, // height
                    roundness, roundness);// arc Dimension
        } else {
            shadowGap = 0;
        }


        //Draws the rounded opaque panel with borders.
        graphics.setColor(getColor());
        graphics.fillRoundRect(0, 0, width - shadowGap * shadowXOffset,
                height - shadowGap * shadowYOffset, roundness, roundness);
	       /*
	       graphics.setColor(getForeground());
	       graphics.setStroke(new BasicStroke(strokeSize));
	       graphics.drawRoundRect(0, 0, width - shadowGap,
	       height - shadowGap, roundness, roundness);
			*/
        //Sets strokes to default, is better.
        graphics.setStroke(new BasicStroke());
    }

    void setRoundness(int _roundness) {
        roundness = _roundness;
    }

    void press() {
        if (!isEnabled())
            return;
        restore_color = getColor();
        setColor(getColor().darker(40));
        restore_shadow_gap = shadowGap;
        setShadowGap(0);
        repaint();
    }

    public void release() {
        setColor(restore_color);
        setShadowGap(restore_shadow_gap);
        restore_shadow_gap = 0;
        repaint();
        run();
    }

    @Override
    public void addRunnable(Runnable r) {
        runnables.add(r);

    }

    @Override
    public void run() {
        for (Runnable r : runnables)
            //r.run();
            new Thread(r).start();

    }


}
