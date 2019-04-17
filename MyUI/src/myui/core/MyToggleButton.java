package myui.core;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

//TODO: Enable & Disable
//TODO: Status (invalid input, checking)


public class MyToggleButton extends JPanel implements Designable{

	public MyMaterialDesign design;
	public MyColor custom_color;
	private EtchedBorder border;
	public Component clickable;
	private boolean clicked;
	
	public MyToggleButton(MyMaterialDesign d, boolean _clicked) {
		design = d;
		clicked = _clicked;
		setSize(20,20);
		setLayout(new BorderLayout(0, 0));
		applyDesign();
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if(!isEnabled())
					return;
				if(clicked)
					clicked = false;
				else
					clicked = true;
				update();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
	}

	@Override
	public void applyDesign() {
		//Is there a custom color defined?
		MyColor accent;
		if(custom_color != null)
			accent = custom_color;
		else
			accent = design.accentColor;
		
		border = new EtchedBorder(EtchedBorder.RAISED, accent, accent.darker(10));
		setBorder(border);
		setBackground(design.baseColor);
		
		switch(design.toggleButtonDesign) {
		case CHECKMARK: {
			//TODO
			break;}
		case DOT: {
			//TODO
			break;}
		case FIELD: {
			clickable = new JPanel();
			clickable.setBackground(accent);
			((JPanel) clickable).setBorder(new EtchedBorder(EtchedBorder.RAISED, design.baseColor, design.baseColor.darker(10)));
			break;}
		}
		
		add(clickable, BorderLayout.CENTER);
		update();
	}

	@Override
	public void setDesign(MyMaterialDesign d) {
		if(design != null)
			design.unregister(this);
		design = d;
		design.register(this);
		applyDesign();
	}

	@Override
	public MyMaterialDesign getDesign() {
		// TODO Auto-generated method stub
		return design;
	}
	
	private void update() {
		clickable.setVisible(clicked);
	}
	
	/**
	 * Returns the value
	 * @return
	 */
	public boolean get() {
		return clicked;
	}
	
	public void set(boolean value) {
		clicked = value;
		update();
	}

	@Override
	public void reset() {
		
		
	}
	
	
	
}
