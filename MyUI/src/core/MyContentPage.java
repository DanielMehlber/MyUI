package core;

import javax.swing.JPanel;

public class MyContentPage extends JPanel implements Designable{

	public MyDesign design;
	
	public MyContentPage(MyDesign d) {
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
