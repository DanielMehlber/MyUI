package designer;

import java.awt.Color;

import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import com.danielmehlber.myui.MyButton;
import com.danielmehlber.myui.MyColor;
import com.danielmehlber.myui.MyFrame;
import com.danielmehlber.myui.MyMaterialDesign;
import com.danielmehlber.myui.MyPage;

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
		
		MyPage page = new MyPage(getDesign());
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
		
		choose_base.setOperator(new Runnable() {
			
			@Override
			public void run() {
				Color c = new JColorChooser().showDialog(null, "Pick the Base Color", getDesign().baseColor);
				if(c == null)
					return;
				getDesign().baseColor = new MyColor(c);
				redesign(true);
			}
		});
		
		choose_accent.setOperator(new Runnable() {
			
			@Override
			public void run() {
				Color c = new JColorChooser().showDialog(null, "Pick the Accent Color", getDesign().accentColor);
				if(c == null)
					return;
				getDesign().accentColor = new MyColor(c);
				redesign(true);
			}
		});
		
		choose_text.setOperator(new Runnable() {
			
			@Override
			public void run() {
				Color c = new JColorChooser().showDialog(null, "Pick the Text Color", getDesign().textColor);
				if(c == null)
					return;
				getDesign().textColor = new MyColor(c);
				redesign(true);
			}
		});
		
		
	}
}
