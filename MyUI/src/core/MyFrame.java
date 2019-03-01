package core;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.Font;

public class MyFrame extends JFrame implements Designable{

	private MyMaterialDesign design;
	public JPanel top;
	public JPanel scene;
	private JLabel title;
	private JPanel topButtons;
	JLabel exit;
	JLabel minimize;
	JLabel maximize;
	
	boolean animsEnabled = true;
	
	public MyFrame() {
		this(new MyMaterialDesign());
	}
	
	public MyFrame(MyMaterialDesign d) {
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
		
		exit = new JLabel(" X ");
		
		minimize = new JLabel(" _ ");
		
		maximize = new JLabel(" [] ");
		
		topButtons.add(minimize);
		topButtons.add(maximize);
		topButtons.add(exit);
		
		scene = new JPanel();
		getContentPane().add(scene, BorderLayout.CENTER);
		scene.setLayout(new BorderLayout(0, 0));
		
		
		applyDesign();
		setBounds(100,100,500,0);
		setVisible(true);
		
		//operators
		exit.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				animation_roll_in();
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
				if(getExtendedState() != MAXIMIZED_BOTH)
					setExtendedState(MAXIMIZED_BOTH);
				else
					setExtendedState(NORMAL);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
			}
		});
		
		Point point = new Point();
		addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                point.x = e.getX();
                point.y = e.getY();
            }
        });
		addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point p = getLocation();
                setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
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
		
		title.setFont(design.font);
		
	}

	public MyMaterialDesign getDesign() {
		return design;
	}

	public void setDesign(MyMaterialDesign design) {
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
	
	
	public void animation_roll_in() {
		if(!animsEnabled)
			return;
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(getSize().height > 0) {
					try {
						Thread.currentThread().sleep(2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					setSize(getSize().width, getSize().height - 10);
				}
				
			}
		});
		
		t.start();
		while(t.isAlive()) {}
	}
	
	public void animation_roll_out(int height) {
		if(!animsEnabled)
			return;
		setSize(getSize().width, 0);
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(getSize().height < height) {
					try {
						Thread.currentThread().sleep(2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					setSize(getSize().width, getSize().height + 10);
				}
				
			}
		});
		
		t.start();
		while(t.isAlive()) {}
	}
	
	public void animation_open_window(int width, int height) {
		if(!animsEnabled) {
			setSize(width, height);
			return;
		}
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				setSize(100, top.getSize().height);
				while(getSize().width < width) {
					try {
						Thread.currentThread().sleep(2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					setSize(getSize().width + 10, getSize().height);
				}
				
				while(getSize().height < height) {
					try {
						Thread.currentThread().sleep(2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					setSize(getSize().width, getSize().height + 10);
				}
				
			}
		});
		t.start();
		while(t.isAlive()) {}
	}
	
	public void setAnimationsEnabled(boolean b) {
		animsEnabled = b;
	}
	
}
