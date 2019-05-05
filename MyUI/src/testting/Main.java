package testting;

import java.awt.Color;

import myui.core.MyButton;
import myui.core.MyChartEntry;
import myui.core.MyColor;
import myui.core.MyPage;
import myui.core.MyDialog;
import myui.core.MyDirection;
import myui.core.MyFrame;
import myui.core.MyGradient;
import myui.core.MyMaterialDesign;
import myui.core.MyMedia;
import myui.core.MyProgessChart;
import myui.core.MySyncTask;
import myui.core.MyTextEntry;
import myui.core.MyToggleButton;
import myui.core.MyMaterialDesign.FRAME_DESIGN;
import myui.core.MyTextEntry.MY_TEXT_ENTRY_MODE;

public class Main extends MyFrame{

	public static void main(String[]args) {
		new Main();
	}
	
	public void login(MyTextEntry username, MyTextEntry password) {
		if(username.getText().equals("daniel.mehlber@gmail.com") && password.getText().equals("al123pha")) {
			
		}else {
			username.error("Unkown Username... Try again", 3);
			password.error("Password incorrect...", 3.5f);
		}
	}
	
	
	public Main() {
		MyMaterialDesign design = MyMaterialDesign.FOX;
		design.frameTopDesign = FRAME_DESIGN.FLAT;
		design.setAnimationSpeed(MyMaterialDesign.ANIM_NORMAL);
		setDesign(design);
		design.font = design.font.deriveFont(15f);
		design.apply();
		setTitle("Test");
		//setBounds(100,100,600,600);
		
		MyPage pageLogin = new MyPage(getDesign());
		changePage(pageLogin, null);
		
		MyPage next = new MyPage(getDesign());
		
		MyTextEntry entryUsername = new MyTextEntry(getDesign(), MyTextEntry.MY_TEXT_ENTRY_MODE.NORMAL);
		
		MyGradient gradient = new MyGradient(MyDirection.WEST, MyColor.BLACK, MyColor.WHITE);
		gradient.setBounds(360, 0, 200, 536);
		pageLogin.add(gradient);
		
		MyTextEntry	entryPassword = new MyTextEntry(getDesign(), MY_TEXT_ENTRY_MODE.PASSWORD);
		entryPassword.setBounds(21, 148, 313, 60);
		entryPassword.setSubtext("Enter Password");
		pageLogin.add(entryPassword);
		entryPassword.addRunnable(new Runnable() {
			
			@Override
			public void run() {
				login(entryUsername, entryPassword);
				
			}
		});
		
		entryUsername.setLocation(21, 84);
		entryUsername.setSize(313, 60);
		pageLogin.add(entryUsername);
		entryUsername.setVisible(true);
		entryUsername.setFont(entryUsername.getFont().deriveFont(18f));
		entryUsername.setSubtext("Username or Email");
		entryUsername.addRunnable(new Runnable() {
			
			@Override
			public void run() {
				login(entryUsername, entryPassword);
				
			}
		});
		
		MyButton BtnLogin = new MyButton(getDesign(), "Login");
		BtnLogin.setLocation(201, 470);
		BtnLogin.setShadow(true);
		pageLogin.add(BtnLogin);
		BtnLogin.setOperator(new Runnable() {
			
			@Override
			public void run() {
				login(entryUsername, entryPassword);
			}
		});
		
		
		MyMedia media = new MyMedia();
		media.setLocation(360, 0);
		media.setSize(200, 536);
		pageLogin.add(media);
		media.setLayoutMode(MyMedia.LAYOUT_MODE.REPEAT);
		
		
		MyButton BtnBackToHome = new MyButton(getDesign(), "Back");
		BtnBackToHome.setBounds(21, 470, 150, 40);
		pageLogin.add(BtnBackToHome);
		
		go(600, 600);
		
	}
}


