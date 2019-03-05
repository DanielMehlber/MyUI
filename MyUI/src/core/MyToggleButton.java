package core;

import javax.swing.JPanel;

public class MyToggleButton extends JPanel implements Designable{

	MyMaterialDesign design;
	
	public MyToggleButton(MyMaterialDesign d) {
		design = d;
		setSize(10,10);
	}

	@Override
	public void applyDesign() {
		// TODO Auto-generated method stub
		
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
	
	
	
}
