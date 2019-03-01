package testting;

import core.MyButton;
import core.MyColor;
import core.MyContentPage;
import core.MyMaterialDesign;
import core.MyMaterialDesign.FRAME_DESIGN;
import core.MyFrame;

public class Main extends MyFrame{

	public static void main(String[]args) {
		new Main();
	}
	
	
	public Main() {
		super(MyMaterialDesign.FOX);
		setTitle("Test");
		go(600,600);
		
		MyContentPage page = genContentPage();
		setContentPage(page);
		MyButton btn = page.genButton("Say Hello");
		btn.operator = new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Hello");
				
			}
		};
	}

}
