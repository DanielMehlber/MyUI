package designer;

import java.awt.Color;

import javax.swing.JColorChooser;

import core.MyButton;
import core.MyColor;
import core.MyContentPage;
import core.MyFrame;
import core.MyMaterialDesign;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;

public class Designer extends MyFrame{

	public static void main(String[]args) {
		new Designer();
	}
	
	public Designer() {
		super(MyMaterialDesign.BEACH);
		setTitle("MyUI Material Designer");
		setBounds(100,100,500,500);
		go(600,600);
		
		MyContentPage page = new MyContentPage(getDesign());
		setContentPage(page);
		MyButton choose_base = new MyButton(getDesign(), "Choose Base Color");
		choose_base.setSize(121, 27);
		choose_base.setLocation(10, 11);
		MyButton choose_accent = new MyButton(getDesign(), "Choose Accent Color");
		choose_accent.setSize(121, 27);
		choose_accent.setLocation(10, 49);
		MyButton choose_text = new MyButton(getDesign(), "Choose Text Color");
		choose_text.setSize(121, 27);
		choose_text.setLocation(10, 87);
		
		page.add(choose_base);
		page.add(choose_accent);
		page.add(choose_text);
		
		choose_base.operator = new Runnable() {
			
			@Override
			public void run() {
				Color c = new JColorChooser().showDialog(null, "Pick the Base Color", getDesign().baseColor.getColor());
				getDesign().baseColor = new MyColor(c);
				redesign(true);
			}
		};
		
		choose_accent.operator = new Runnable() {
			
			@Override
			public void run() {
				Color c = new JColorChooser().showDialog(null, "Pick the Accent Color", getDesign().accentColor.getColor());
				getDesign().accentColor = new MyColor(c);
				redesign(true);
			}
		};
		
		choose_text.operator = new Runnable() {
			
			@Override
			public void run() {
				Color c = new JColorChooser().showDialog(null, "Pick the Text Color", getDesign().textColor.getColor());
				getDesign().textColor = new MyColor(c);
				redesign(true);
			}
		};
		
		
	}
}
