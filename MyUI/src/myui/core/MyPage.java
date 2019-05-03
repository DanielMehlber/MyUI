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
				System.out.println("SLIDE");
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
				
				int tick = (int) (20 * design.animation_speed);
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
				
				if(dir != null) {
				
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
				}
				
				
				setLocation(-getWidth() * x_fac, -getHeight() * y_fac);
				int tick = (int) (20 * design.animation_speed);
				while(!(Math.abs(getY()) < tick+1) || !(Math.abs(getX()) < tick+1)) {
					MySyncTask.sync(10);
					setLocation(getLocation().x + (tick * x_fac), getLocation().y + (tick * y_fac));
				}
				setLocation(0,0);
				setVisible(true);
			}
		});
		
		t.start();
	}
	
	public void animatation_change_to(MyDirection dir, MyPage to) {
		if(this == to) {
			System.err.println("Change failed: Same Page");
			return;
		}
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				int x_fac = 0;
				int y_fac = 0;
				
				if(dir != null) {
				
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
				}
				
				
				to.setLocation(-to.getWidth() * x_fac, -to.getHeight() * y_fac);
				int tick = (int) (20 * design.animation_speed);
				while(!(Math.abs(to.getY()) < tick+1) || !(Math.abs(to.getX()) < tick+1)) {
					MySyncTask.sync(120);
					int deltax = (tick * x_fac);
					int deltay = (tick * y_fac);
					setLocation(getLocation().x + deltax, getLocation().y + deltay);
					to.setLocation(to.getLocation().x + deltax, to.getLocation().y + deltay);
					System.out.println("Loaction From: "+getX()+ ", "+getY());
					System.out.println("Location TO  : "+to.getX()+ ", "+to.getY());
				}
				to.setLocation(0,0);
				setVisible(true);
			}
		});
		
		t.start();
		//while(t.isAlive()) {MySyncTask.sleep(1);}
	}
	

}
