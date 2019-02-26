package core;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class MyFrame extends JFrame implements Designable{

	private MyDesign design;
	
	public MyFrame() {
		this(new MyDesign());
	}
	
	public MyFrame(MyDesign d) {
		design = d;
		setUndecorated(true);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel top = new JPanel();
		getContentPane().add(top, BorderLayout.NORTH);
		
		JPanel scene = new JPanel();
		getContentPane().add(scene, BorderLayout.CENTER);
		scene.setBackground(design.getBaseColor().getColor());
	}
	
	

}
