package myui.core;

import javax.swing.JPanel;

public class MyPage extends JPanel implements Designable{

	public MyMaterialDesign design;
	
	public MyPage(MyMaterialDesign d) {
		setDesign(d);
		setLayout(null);
		applyDesign();
	}

	@Override
	public void applyDesign() {
		setBackground(design.baseColor);
		
	}
	
	public MyButton genButton(String text) {
		MyButton ret = new MyButton(design, text);
		add(ret);
		return ret;
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

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
	
	public void animatation_slide_out(MyDirection dir) {
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				int x_fac = 0;
				int y_fac = 0;
				switch(dir) {
				case NORTH: {
					y_fac = -1;
					break;}
				
				case EAST: {
					y_fac = 1;
					break;
				}
				
				case WEST: {
					x_fac = -1;
					break;
				}
				
				case SOUTH:{
					y_fac = 1;
					break;
				}
				
				}
				
				int tick = 20;
				for(int i = 0; i < getWidth(); i++) {
					MySyncTask.sync(120);
					setLocation(getX() + tick * x_fac, getY() + tick * y_fac);
				}
				
			}
		});
		
		t.start();
	}
	
	public void animatation_slide_in(MyDirection dir) {
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				int x_fac = 0;
				int y_fac = 0;
				switch(dir) {
				case NORTH: {
					y_fac = -1;
					break;}
				
				case EAST: {
					y_fac = 1;
					break;
				}
				
				case WEST: {
					x_fac = -1;
					break;
				}
				
				case SOUTH:{
					y_fac = 1;
					break;
				}
				
				}
				
				setLocation(-getWidth() * x_fac, -getHeight() * y_fac);
				int tick = 20;
				while(!(Math.abs(getY()) < tick+1) || !(Math.abs(getX()) < tick+1)) {
					MySyncTask.sync(120);
					setLocation(getX() + tick * x_fac, getY() + tick * y_fac);
				}
				setLocation(0,0);
			}
		});
		
		t.start();
	}

}