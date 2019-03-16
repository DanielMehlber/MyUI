package testting;

import core.MyButton;
import core.MyColor;
import core.MyContentPage;
import core.MyMaterialDesign;
import core.MyToggleButton;
import core.MyMaterialDesign.FRAME_DESIGN;
import core.MyPieChart;
import core.MyPieChartEntry;
import core.MySyncTask;
import core.MyTextEntry;
import core.MyFrame;

public class Main extends MyFrame{

	public static void main(String[]args) {
		new Main();
	}
	
	
	public Main() {
		MyMaterialDesign design = MyMaterialDesign.FOX;
		design.frameTopDesign = FRAME_DESIGN.FLAT;
		setDesign(design);
		design.apply();
		setTitle("Test");
		setBounds(100,100,600,600);
		
		
		MyContentPage page = new MyContentPage(getDesign());
		setContentPage(page);
		
		MyPieChart chart = new MyPieChart();
		chart.setLocation(104, 290);
		page.add(chart);
		chart.setSize(168, 169);
		chart.add(new MyPieChartEntry(10, MyColor.GREEN));
		chart.add(new MyPieChartEntry(90, MyColor.RED));
		chart.add(new MyPieChartEntry(50, MyColor.YELLOW));
		chart.add(new MyPieChartEntry(60, MyColor.BLUE));
		chart.add(new MyPieChartEntry(30, MyColor.CYAN));
		
		MyToggleButton tbtn = new MyToggleButton(getDesign(), true);
		page.add(tbtn);
		tbtn.setLocation(400, 400);
		
		MyButton btn = new MyButton(getDesign(), "PRESS ME");
		btn.setLocation(36, 61);
		page.add(btn);
		btn.setOperator(new Runnable() {
			
			@Override
			public void run() {
				chart.animation(1);
				
			}
		});
		
		
		MyTextEntry text = new MyTextEntry(getDesign(), MyTextEntry.MODE.NORMAL);
		text.setLocation(36, 112);
		text.setSize(203, 48);
		page.add(text);
		text.setVisible(true);
		text.setFont(text.getFont().deriveFont(18f));
		text.setSubtext("Please enter your Name");
		
		go(600,600);
		chart.go();
		
		
	}

}


