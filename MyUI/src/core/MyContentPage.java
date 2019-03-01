package core;

import javax.swing.JPanel;

public class MyContentPage extends JPanel implements Designable{

	public MyMaterialDesign design;
	
	public MyContentPage(MyMaterialDesign d) {
		design = d;
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

}
