package testting;

import core.MyButton;
import core.MyColor;
import core.MyContentPage;
import core.MyDesign;
import core.MyDesign.FRAME_DESIGN;
import core.MyFrame;

public class Main {

	public static void main(String[]args) {
		MyFrame frame = new MyFrame();
		MyDesign design = new MyDesign(MyColor.DARK_GRAY, MyColor.ORANGE, MyColor.WHITE);
		design.frameTopDesign = FRAME_DESIGN.FLAT;
		frame.setDesign(design);
		frame.setTitle("App");
		frame.applyDesign();
		MyContentPage page = new MyContentPage(design);
		frame.setContentPage(page);
		MyButton btn = new MyButton(design, "HALLO WELT");
		btn.setLocation(132, 91);
		page.add(btn);
		frame.animation_open_window(1000, 1000);
		
	
	}

}
