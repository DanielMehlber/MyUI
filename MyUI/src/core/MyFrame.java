package core;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JLabel;
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
	
	/**
	 * Creates a Frame with no Material Design
	 */
	public MyFrame() {
		this(new MyMaterialDesign());
	}
	
	/**
	 * Creates a Frame with specified Material Design
	 * @param d Material Design
	 */
	public MyFrame(MyMaterialDesign d) {
		setDesign(d);
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
				animation_close_window(true);
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

	/**
	 * Returns the current Design
	 * @return Design
	 */
	public MyMaterialDesign getDesign() {
		return design;
	}

	
	public void setDesign(MyMaterialDesign design) {
		if(this.design != null)
			this.design.unregister(this);
		this.design = design;
		this.design.register(this);
	}
	
	/**
	 * Title of frame
	 */
	@Override
	public void setTitle(String t) {
		title.setText(t);
	}
	
	@Override
	public String getTitle() {
		return title.getText();
	}
	
	/**
	 * Set the Content Page
	 * @param page A content page to be set
	 */
	public void setContentPage(MyContentPage page) {
		scene.removeAll();
		scene.add(page, BorderLayout.CENTER);
	}
	
	/**
	 * Generates a content page from design
	 * @return new content page
	 */
	public MyContentPage genContentPage() {
		return new MyContentPage(design);
	}
	
	/**
	 * Plays the snapping-in animation
	 * @param wait should the program wait till the animation finishes ?
	 */
	public void animation_snap_in(boolean wait) {
		if(!animsEnabled)
			return;
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(getSize().height > 0) {
					MySyncTask.sync(120);
					setBounds(getX(), getY() + 20 / 2, getWidth(), getHeight() - 40 / 2);
				}
				
			}
		});
		
		if(!wait)
			t.start();
		else
			t.run();
	}
	
	/**
	 * Plays the snapping-out animation
	 * @param height The end-height
	 * @param wait should the program wait till the animation finishes ?
	 */
	public void animation_snap_out(int height, boolean wait) {
		if(!animsEnabled)
			return;
		setSize(getSize().width, 0);
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(getSize().height < height) {
					MySyncTask.sync(120);
					setBounds(getX(), getY() - 20 / 2, getWidth(), getHeight()+40 / 2);
				}
				
			}
		});
		
		if(!wait)
			t.start();
		else
			t.run();
	
	}
	
	/**
	 * Plays the roll-out animation
	 * @param height The end-height
	 * @param wait should the program wait till the animation finishes ?
	 */
	public void animation_roll_out(int height, boolean wait) {
		if(!animsEnabled)
			return;
		setSize(getSize().width, 0);
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(getSize().height < height) {
					MySyncTask.sync(120);
					setSize(getWidth(), getHeight() + 20);
				}
				
			}
		});
		
		if(!wait)
			t.start();
		else
			t.run();
	}
	
	/**
	 * Plays the roll-out animation
	 * @param height The end-height
	 * @param wait should the program wait till the animation finishes ?
	 */
	public void animation_roll_in(boolean wait) {
		if(!animsEnabled)
			return;
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(getSize().height > 0) {
					MySyncTask.sync(120);
					setSize(getWidth(), getHeight() - 20);
				}
				
			}
		});
		if(!wait)
			t.start();
		else
			t.run();
		
	}
	
	/**
	 * Plays the open window animation
	 * @param width Width
	 * @param height Height
	 */
	public void animation_open_window(int width, int height, boolean wait) {
		if(!animsEnabled) {
			setSize(width, height);
			return;
		}
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				setSize(100, top.getSize().height);
				while(getSize().width < width) {
					MySyncTask.sync(120);
					setSize(getSize().width + 20, getSize().height);
				}
				
				while(getSize().height < height) {
					MySyncTask.sync(120);
					setSize(getSize().width, getSize().height + 20);
				}
				
			}
		});
		if(!wait)
			t.start();
		else
			t.run();
	}
	
	/**
	 * Plays the close window animation
	 * @param width Width
	 * @param height Height
	 */
	public void animation_close_window(boolean wait) {
		if(!animsEnabled) {
			setSize(0, 0);
			return;
		}
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				while(getSize().height > top.getHeight()) {
					MySyncTask.sync(120);
					setSize(getSize().width, getSize().height - 20);
				}
				
				while(getSize().width > 0) {
					MySyncTask.sync(120);
					setSize(getSize().width - 20, getSize().height);
				}
				
				
			}
		});
		if(!wait)
			t.start();
		else
			t.run();
	}
	
	/**
	 * Enable or disable the standard window animations
	 * @param b
	 */
	public void setAnimationsEnabled(boolean b) {
		animsEnabled = b;
	}
	
	
	/**
	 * Simplest way to start a frame
	 * @param width Width
	 * @param height Height
	 */
	public void go(int width, int height) {
		animation_open_window(width, height, true);
	}
	
	/**
	 * Redesign this frame and all its components
	 * @param animation Should the redesign animation be played ?
	 */
	public void redesign(boolean animation) {
		if(animation) {
			int height = getSize().height;
			animation_roll_in(true);
			getDesign().apply();
			animation_roll_out(height, false);
		}else
			getDesign().apply();
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
	
}
