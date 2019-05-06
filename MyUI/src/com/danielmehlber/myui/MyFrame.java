package com.danielmehlber.myui;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;

public class MyFrame extends JFrame implements Designable{

	private MyMaterialDesign design;
	public JPanel top;
	public JPanel scene;
	private JLabel title;
	private JPanel topButtons;
	private MyPage currentPage;
	JLabel exit;
	JLabel minimize;
	JLabel maximize;
	
	Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
	
	boolean animsEnabled = true;
	private JPanel y_resize;
	private JPanel x_resize;
	private Component verticalStrut;
	private Component horizontalStrut;
	
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
		setVisible(false);
		setDesign(d);
		setUndecorated(true);
		setSize(0,0);
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
		//scene.setLayout(new BorderLayout(0, 0));
		scene.setLayout(null);
		
		y_resize = new JPanel();
		getContentPane().add(y_resize, BorderLayout.SOUTH);
		y_resize.setBorder(null);
		y_resize.setLayout(new BorderLayout(0, 0));
		
		verticalStrut = Box.createVerticalStrut(1);
		y_resize.add(verticalStrut);
		
		x_resize = new JPanel();
		getContentPane().add(x_resize, BorderLayout.EAST);
		x_resize.setBorder(null);
		x_resize.setLayout(new BoxLayout(x_resize, BoxLayout.X_AXIS));
		
		horizontalStrut = Box.createHorizontalStrut(1);
		x_resize.add(horizontalStrut);
		
		
		y_resize.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!isResizable())
					return;
				setCursor(defaultCursor);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!isResizable())
					return;
				setCursor(Cursor.S_RESIZE_CURSOR);
			}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		
		y_resize.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				if(!isResizable())
					return;
				int y = e.getPoint().y;
				int height = getHeight() + y;
				if(height > top.getHeight())
					setSize(getWidth(), height);
			}
		});
		
		
		x_resize.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!isResizable())
					return;
				setCursor(defaultCursor);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!isResizable())
					return;
				setCursor(Cursor.W_RESIZE_CURSOR);
			}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		
		x_resize.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				if(!isResizable())
					return;
				int x = e.getPoint().x;
				int width = getWidth() + x;
				if(width > 200)
					setSize(width, getHeight());
			}
		});
		
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
		
		addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				if(currentPage == null)
					return;
				currentPage.setSize(scene.getSize());
				
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	@Override
	public void applyDesign() {
		scene.setBackground(design.getBaseColor());
		
		//FRAME_TOP_DESIGN
		switch(design.getFrameTopDesign()) {
		case BAR: {
			top.setBackground(design.getAccentColor());
			topButtons.setBackground(design.getAccentColor());
			exit.setForeground(design.getTextColor());
			minimize.setForeground(design.getTextColor());
			maximize.setForeground(design.getTextColor());
			title.setForeground(design.getTextColor());
			break;
		}
		case FLAT: {
			top.setBackground(design.getBaseColor());
			topButtons.setBackground(design.getBaseColor());
			exit.setForeground(design.getAccentColor());
			minimize.setForeground(design.getAccentColor());
			maximize.setForeground(design.getAccentColor());
			title.setForeground(design.getAccentColor());
		}
		
		}
		
		x_resize.setBackground(design.baseColor);
		y_resize.setBackground(design.baseColor);
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
	public void setContentPage(MyPage page) {
		currentPage = page;
		scene.removeAll();
		scene.add(page, BorderLayout.CENTER);
	}
	
	/**
	 * Generates a content page from design
	 * @return new content page
	 */
	public MyPage genPage() {
		return new MyPage(design);
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
					setBounds(getX(), (int) (getY() + 20*design.animation_speed / 2), getWidth(), (int) (getHeight() - 40*design.animation_speed / 2));
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
					setBounds(getX(), (int) (getY() - 20*design.animation_speed / 2), getWidth(), (int) (getHeight()+40*design.animation_speed / 2));
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
					setSize(getWidth(), (int) (getHeight() + 20*design.animation_speed));
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
					setSize(getWidth(), (int) (getHeight() - 20*design.animation_speed));
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
					setSize((int) (getSize().width + 20*design.animation_speed), getSize().height);
				}
				
				while(getSize().height < height) {
					MySyncTask.sync(120);
					setSize(getSize().width, (int) (getSize().height + 20*design.animation_speed));
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
					setSize(getSize().width, (int) (getSize().height - 20*design.animation_speed));
					if(getSize().height < top.getHeight())
						setSize(getWidth(), top.getHeight());
				}
				
				setSize(getWidth(), top.getHeight());
				
				while(getSize().width > 0) {
					MySyncTask.sync(120);
					setSize((int) (getSize().width - 20*design.animation_speed), getSize().height);
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
		setVisible(true);
		animation_open_window(width, height, true);
	}
	
	public void go() {
		go(getWidth(), getHeight());
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

	public Cursor getDefaultCursor() {
		return defaultCursor;
	}

	public void setDefaultCursor(Cursor defaultCursor) {
		this.defaultCursor = defaultCursor;
	}
	
	
	public void changePage(MyPage to, MyDirection dir) {
		
		if(to == null) {
			System.err.println("Page can't be null");
			return;
		}
		
		if(currentPage == to) {
			System.err.println("Change failed: Same Page");
			return;
		}
		
		to.setSize(scene.getSize());
		scene.add(to);
		if(currentPage == null) {
			to.animatation_slide_in(dir);
			currentPage = to;
			return;
		}
		
		if(to.isMoving || currentPage.isMoving) {
			return;
		}
		
		currentPage.isMoving = true;
		to.isMoving = true;
		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				int x_fac = 0;
				int y_fac = 0;
				
				if(dir != null) {
				
					switch(dir) {
					case NORTH: {
						y_fac = -1;
						break;}
					
					case EAST: {
						y_fac = 1;
						break;
					}
					
					case WEST: {
						x_fac = -1;
						break;
					}
					
					case SOUTH:{
						y_fac = 1;
						break;
					}
					
					}
				}
				
				
				to.setLocation(-to.getWidth() * x_fac, -to.getHeight() * y_fac);
				int tick = (int) (20 * design.animation_speed);
				while(!(Math.abs(to.getY()) < tick+1) || !(Math.abs(to.getX()) < tick+1)) {
					MySyncTask.sync(120);
					int deltax = (tick * x_fac);
					int deltay = (tick * y_fac);
					currentPage.setLocation(currentPage.getLocation().x + deltax, currentPage.getLocation().y + deltay);
					to.setLocation(to.getLocation().x + deltax, to.getLocation().y + deltay);
					repaint();
				}
				scene.remove(currentPage);
				currentPage.isMoving = false;
				currentPage = to;
				to.setLocation(0,0);
				setVisible(true);
				to.isMoving = false;
			}
		});
		
		t.start();
		
	}
	
	
	
}
