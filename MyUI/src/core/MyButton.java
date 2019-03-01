package core;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MyButton extends JPanel implements Designable {

	
	public MyDesign design;
	public Font customFont = null;
	public MyColor customTextColor = null;
	public MyColor customColor = null;
	public JLabel text;
	public Runnable operator;
	
	/**
	 * Create the panel.
	 */
	public MyButton(MyDesign design, String s) {
		setLayout(new BorderLayout(0, 0));
		this.design = design;
		setBounds(0,0,150, 40);
		text = new JLabel(s);
		text.setHorizontalAlignment(SwingConstants.CENTER);
		add(text, BorderLayout.CENTER);
		applyDesign();
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("RELEASED");
				setBackground(design.accentColor.getColor());
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("PRESSED...");
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
		if(customFont == null) {
			text.setFont(design.font);
		}else {
			text.setFont(customFont);
		}
		
		if(customColor == null) {
			switch (design.buttonColorDesign){
			case ACCENT: {
				setBackground(design.accentColor.getColor());
				break;
			}
			case BASE: {
				setBackground(design.baseColor.getColor());
			}
			}
		}else {
			setBackground(customColor.getColor());
		}
		
		if(customTextColor == null) {
			text.setForeground(design.textColor.getColor());
		}else {
			text.setForeground(customTextColor.getColor());
		}
		
		
		
	}
	
	public void setText(String text) {
		this.text.setText(text);
	}
	
	public String getText() {
		return text.getText();
	}
	
	public void reset() {
		customFont = null;
		customTextColor = null;
		customColor = null;
	}
	
	public MyContentPage genContentPage() {
		return new MyContentPage(design);
	}
	
	public void setOperator(Runnable runnable) {
		operator = runnable;
	}
	
	
	

}
