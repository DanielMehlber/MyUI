package testting;

import core.MyButton;
import core.MyColor;
import core.MyContentPage;
import core.MyMaterialDesign;
import core.MyToggleButton;
import core.MyMaterialDesign.FRAME_DESIGN;
import core.MyPieChart;
import core.MyPieChartEntry;
import core.MyFrame;

public class Main extends MyFrame{

	public static void main(String[]args) {
		new Main();
	}
	
	
	public Main() {
		super(MyMaterialDesign.FOX);
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
		
		go(600,600);
		chart.go();
	}

}


