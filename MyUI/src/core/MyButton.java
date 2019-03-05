package core;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;
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
				setBackground(design.accentColor.getColor());
				if(operator != null)
					operator.run();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
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
	
	
	

}
