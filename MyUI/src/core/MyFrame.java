package core;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.Font;

public class MyFrame extends JFrame implements Designable{

	private MyDesign design;
	public JPanel top;
	public JPanel scene;
	private JLabel title;
	private JPanel topButtons;
	JLabel exit;
	JLabel minimize;
	JLabel maximize;
	
	public MyFrame() {
		this(new MyDesign());
	}
	
	public MyFrame(MyDesign d) {
		design = d;
		setUndecorated(true);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		top = new JPanel();
		getContentPane().add(top, BorderLayout.NORTH);
		
		top.setLayout(new BorderLayout(0, 0));
		
		
		title = new JLabel("-- Untitled Frame --");
		title.setFont(new Font("Tahoma", Font.PLAIN, 10));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		top.add(title, BorderLayout.CENTER);
		
		topButtons = new JPanel();
		top.add(topButtons, BorderLayout.EAST);
		
		exit = new JLabel(" (X) ");
		
		minimize = new JLabel(" (_) ");
		
		maximize = new JLabel(" ([]) ");
		
		topButtons.add(minimize);
		topButtons.add(maximize);
		topButtons.add(exit);
		
		scene = new JPanel();
		getContentPane().add(scene, BorderLayout.CENTER);
		scene.setLayout(new BorderLayout(0, 0));
		
		
		applyDesign();
		setBounds(100,100,500,500);
		setVisible(true);
		
		//operators
		exit.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		minimize.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				setExtendedState(ICONIFIED);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
		
		maximize.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				setExtendedState(MAXIMIZED_BOTH);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public void applyDesign() {
		System.out.println("APPLY");
		scene.setBackground(design.getBaseColor().getColor());
		
		//FRAME_TOP_DESIGN
		switch(design.getFrameTopDesign()) {
		case BAR: {
			top.setBackground(design.getAccentColor().getColor());
			topButtons.setBackground(design.getAccentColor().getColor());
			exit.setForeground(design.getTextColor().getColor());
			minimize.setForeground(design.getTextColor().getColor());
			maximize.setForeground(design.getTextColor().getColor());
			title.setForeground(design.getTextColor().getColor());
			break;
		}
		case FLAT: {
			top.setBackground(design.getBaseColor().getColor());
			topButtons.setBackground(design.getBaseColor().getColor());
			exit.setForeground(design.getAccentColor().getColor());
			minimize.setForeground(design.getAccentColor().getColor());
			maximize.setForeground(design.getAccentColor().getColor());
			title.setForeground(design.getAccentColor().getColor());
		}
		
		}
		
	}

	public MyDesign getDesign() {
		return design;
	}

	public void setDesign(MyDesign design) {
		this.design = design;
		applyDesign();
	}
	
	@Override
	public void setTitle(String t) {
		title.setText(t);
	}
	
	@Override
	public String getTitle() {
		return title.getText();
	}
	
	public void setContentPage(MyContentPage page) {
		scene.removeAll();
		scene.add(page, BorderLayout.CENTER);
	}
	
	public MyContentPage genContentPage() {
		return new MyContentPage(design);
	}
	
}
