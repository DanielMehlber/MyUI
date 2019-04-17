package testting;

import java.awt.Color;

import myui.core.MyButton;
import myui.core.MyChartEntry;
import myui.core.MyColor;
import myui.core.MyPage;
import myui.core.MyDialog;
import myui.core.MyDirection;
import myui.core.MyFrame;
import myui.core.MyMaterialDesign;
import myui.core.MyProgessChart;
import myui.core.MySyncTask;
import myui.core.MyTextEntry;
import myui.core.MyToggleButton;
import myui.core.MyMaterialDesign.FRAME_DESIGN;

public class Main extends MyFrame{

	public static void main(String[]args) {
		new Main();
	}
	
	
	public Main() {
		MyMaterialDesign design = MyMaterialDesign.FOX;
		design.frameTopDesign = FRAME_DESIGN.FLAT;
		setDesign(design);
		design.font = design.font.deriveFont(15f);
		design.apply();
		setTitle("Test");
		setBounds(100,100,600,600);
		
		MyPage page = new MyPage(getDesign());
		setContentPage(page);
		
		MyPage next = new MyPage(MyMaterialDesign.SEA);
		
		MyDialog dialog = new MyDialog(getDesign());
		dialog.setLocation(277, 112);
		page.add(dialog);
		dialog.setSize(240,108);
		
		MyProgessChart chart =  new MyProgessChart(getDesign());
		chart.setLocation(104, 290);
		page.add(chart);
		chart.setSize(168, 169);
		chart.add(new MyChartEntry(10, MyColor.GREEN));
		chart.add(new MyChartEntry(90, MyColor.RED));
		chart.add(new MyChartEntry(50, MyColor.YELLOW));
		chart.add(new MyChartEntry(60, MyColor.BLUE));
		chart.add(new MyChartEntry(30, MyColor.CYAN));
		
		MyToggleButton tbtn = new MyToggleButton(getDesign(), true);
		page.add(tbtn);
		tbtn.setLocation(400, 400);
		
		MyButton btn = new MyButton(getDesign(), "PRESS ME");
		btn.setLocation(36, 61);
		btn.setShadow(true);
		page.add(btn);
		btn.setOperator(new Runnable() {
			
			@Override
			public void run() {
				changePage(next, MyDirection.SOUTH);
				
			}
		});
		
		
		MyTextEntry text = new MyTextEntry(getDesign(), MyTextEntry.MY_TEXT_ENTRY_MODE.NORMAL);
		text.setLocation(36, 112);
		text.setSize(203, 60);
		page.add(text);
		text.setVisible(true);
		text.setFont(text.getFont().deriveFont(18f));
		text.setSubtext("Please enter your Name");
		
		
		go(600,600);
		chart.go();
		
		
	}
}


