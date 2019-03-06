package core;

import javax.swing.JPanel;

public class MyContentPage extends JPanel implements Designable{

	public MyMaterialDesign design;
	
	public MyContentPage(MyMaterialDesign d) {
		setDesign(d);
		setLayout(null);
		applyDesign();
	}

	@Override
	public void applyDesign() {
		setBackground(design.baseColor.getColor());
		
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

}
