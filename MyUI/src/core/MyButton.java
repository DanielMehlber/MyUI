package core;

import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MyButton extends JPanel implements Designable {

	
	private MyMaterialDesign design;
	private Font customFont = null;
	private MyColor customTextColor = null;
	private MyColor customColor = null;
	private JLabel text;
	private Runnable operator;
	
	private int shadowGap = 2;
	private int shadowAlpha = 150;
	private MyColor shadowColor = MyColor.BLACK;
	private boolean shady = true;
	private int roundness = 10;
	private int shadowOffset = 10;
	private int strokeSize = 1;
	
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
		
		text.setFont(getTextFont());
		setBackground(getColor().getColor());
		text.setForeground(getTextColor().getColor());
	
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
			customColor = getColor().darker(50);
		
		applyDesign();
	}
	
	
	/**
	 * Returns currently used color (design or custom)
	 * @return
	 */
	public MyColor getColor() {
		if(customColor == null)
			return design.accentColor;
		return customColor;
	}
	
	
	/**
	 * Returns currently used text color (design or custom)
	 * @return
	 */
	public MyColor getTextColor() {
		if(customTextColor == null)
			return design.textColor;
		return customTextColor;
	}
	
	
	/**
	 * Returns currently used font (design or custom)
	 * @return
	 */
	public Font getTextFont() {
		if(customFont == null)
			return design.font;
		return customFont;
	}
	
	

	public void setTextFont(Font customFont) {
		this.customFont = customFont;
	}


	public void setTextColor(MyColor customTextColor) {
		this.customTextColor = customTextColor;
	}


	public void setColor(MyColor customColor) {
		this.customColor = customColor;
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

	public int getShadowOffset() {
		return shadowOffset;
	}

	public void setShadowOffset(int shadowOffset) {
		this.shadowOffset = shadowOffset;
	}

	public Runnable getOperator() {
		return operator;
	}

	protected void paintComponent(Graphics g) {
	       super.paintComponent(g);
	       int width = getWidth();
	       int height = getHeight();
	       int shadowGap = this.shadowGap;
	       Color shadowColorA = new Color(shadowColor.getColor().getRed(),
	    		   shadowColor.getColor().getGreen(), shadowColor.getColor().getBlue(), shadowAlpha);
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
	                   roundness, roundness);// arc Dimension
	       } else {
	           shadowGap = 1;
	       }
			
	       
	       //Draws the rounded opaque panel with borders.
	       graphics.setColor(getBackground());
	       graphics.fillRoundRect(0, 0, width - shadowGap,
	       height - shadowGap, roundness, roundness);
	       graphics.setColor(getForeground());
	       graphics.setStroke(new BasicStroke(strokeSize));
	       graphics.drawRoundRect(0, 0, width - shadowGap,
	       height - shadowGap, roundness, roundness);

	       //Sets strokes to default, is better.
	       graphics.setStroke(new BasicStroke());
	   }
	
	void setRoundness(int _roundness) {
		roundness = _roundness;
	}
	
	
}
