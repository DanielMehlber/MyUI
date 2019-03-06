package core;

import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MyButton extends JPanel implements Designable {

	
	public MyMaterialDesign design;
	public Font customFont = null;
	public MyColor customTextColor = null;
	public MyColor customColor = null;
	public JLabel text;
	public Runnable operator;
	
	int shadowGap = 2;
	int shadowAlpha = 150;
	Color shadowColor = Color.BLACK;
	public boolean shady = true;
	protected Dimension arcs = new Dimension(8, 8);
	int shadowOffset = 10;
	int strokeSize = 1;
	
	/**
	 * Create the panel.
	 */
	public MyButton(MyMaterialDesign design, String s) {
		setLayout(new BorderLayout(0, 0));
		setDesign(design);
		setBounds(0,0,150, 40);
		text = new JLabel(s);
		text.setHorizontalAlignment(SwingConstants.CENTER);
		add(text, BorderLayout.CENTER);
		applyDesign();
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if(!isEnabled())
					return;
				setBackground(design.accentColor.getColor());
				if(operator != null)
					operator.run();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if(!isEnabled())
					return;
				setBackground(design.accentColor.darker(40).getColor());
				
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
		
		text.setFont(getUsedFont());
		setBackground(getUsedColor().getColor());
		text.setForeground(getUsedTextColor().getColor());
	
	}
	
	public void setText(String text) {
		this.text.setText(text);
	}
	
	public String getText() {
		return text.getText();
	}
	
	@Override
	public void reset() {
		customFont = null;
		customTextColor = null;
		customColor = null;
	}
	
	
	/**
	 * What's supposed to happen when the button is pressed
	 * @param runnable Runnable
	 */
	public void setOperator(Runnable runnable) {
		operator = runnable;
	}

	@Override
	public void setDesign(MyMaterialDesign d) {
		if(design != null)
			this.design.unregister(this);
		design = d;
		design.register(this);
		
	}

	@Override
	public MyMaterialDesign getDesign() {
		// TODO Auto-generated method stub
		return design;
	}
	
	
	public void setEnabled(boolean e) {
		super.setEnabled(e);
		
		if(e)
			reset();
		else
			customColor = getUsedColor().darker(50);
		
		applyDesign();
	}
	
	
	/**
	 * Returns currently used color (design or custom)
	 * @return
	 */
	public MyColor getUsedColor() {
		if(customColor == null)
			return design.accentColor;
		return customColor;
	}
	
	
	/**
	 * Returns currently used text color (design or custom)
	 * @return
	 */
	public MyColor getUsedTextColor() {
		if(customTextColor == null)
			return design.textColor;
		return customTextColor;
	}
	
	
	/**
	 * Returns currently used font (design or custom)
	 * @return
	 */
	public Font getUsedFont() {
		if(customFont == null)
			return design.font;
		return customFont;
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
	       

           graphics.setColor(design.baseColor.getColor());
	       graphics.fillRect(0, 0, width, height);//paint background
           
	       
	       //Draws shadow borders if any.
	       if (shady) {
	           graphics.setColor(shadowColorA);
	           graphics.fillRoundRect(
	                   shadowOffset,// X position
	                   shadowOffset,// Y position
	                   width - strokeSize - shadowOffset, // width
	                   height - strokeSize - shadowOffset, // height
	                   arcs.width, arcs.height);// arc Dimension
	       } else {
	           shadowGap = 1;
	       }
			
	       
	       //Draws the rounded opaque panel with borders.
	       graphics.setColor(getBackground());
	       graphics.fillRoundRect(0, 0, width - shadowGap,
	       height - shadowGap, arcs.width, arcs.height);
	       graphics.setColor(getForeground());
	       graphics.setStroke(new BasicStroke(strokeSize));
	       graphics.drawRoundRect(0, 0, width - shadowGap,
	       height - shadowGap, arcs.width, arcs.height);

	       //Sets strokes to default, is better.
	       graphics.setStroke(new BasicStroke());
	   }
	
}
