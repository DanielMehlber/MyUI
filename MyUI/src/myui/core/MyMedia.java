package myui.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import javax.swing.JPanel;

public class MyMedia extends JPanel{

	public static enum LAYOUT_MODE {
		FIT, SCALE, REPEAT
	}
	LAYOUT_MODE layout;
	double xScale = 1, yScale = 1;
	BufferedImage image;
	BufferedImage processed;
	public static final BufferedImage PLACEHOLDER_IMAGE = genDefaultImage(64, 64);
	
	public MyMedia() {
		this(PLACEHOLDER_IMAGE);
	}
	
	public MyMedia(String path) {
		
	}
	
	public MyMedia(BufferedImage image) {
		this.image = image;
		this.processed = this.image;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		int width = (int) (image.getWidth() * xScale);
		int height = (int) (image.getHeight() * yScale);
		switch(layout) {
		case SCALE: {
			g.drawImage(image, 0, 0, width, height, null);
			break;}
		case FIT: {
			//TODO: FIT
			break;
		}
		case REPEAT: {
			
			int x_times = (int) Math.ceil(getWidth() / (image.getWidth() * xScale));
			int y_times = (int) Math.ceil(getHeight() / (image.getHeight() * xScale));
			for(int x = 0; x < x_times; x++) {
				for(int y = 0; y < y_times; y++) {
					g.drawImage(processed, x*width, y*height, width, height, null);
				}
			}
			
		}
		}
		
	
		
	}
	
	public static BufferedImage genDefaultImage(int w, int h) {
		BufferedImage img = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
		Color paint;
		for(int x = 0; x < w; x++) {
			for(int y = 0; y < h; y++) {
				if( x < w/2) {
					if(y < h/2)
						paint = Color.MAGENTA;
					else
						paint = Color.black;
				}else {
					if(y < h/2)
						paint = Color.black;
					else
						paint = Color.MAGENTA;
				}
				img.setRGB(x, y, paint.getRGB());
			}
		}
			
		return img;
	}

	public LAYOUT_MODE getLayoutMode() {
		return layout;
	}

	public void setLayoutMode(LAYOUT_MODE layout) {
		this.layout = layout;
	}


	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public double getxScale() {
		return xScale;
	}

	public void setxScale(double xScale) {
		this.xScale = xScale;
	}

	public double getyScale() {
		return yScale;
	}

	public void setyScale(double yScale) {
		this.yScale = yScale;
	}
	
	public static BufferedImage blur(BufferedImage img, int size, float weight) {
		float[] kernel = new float[size * size];
		for(int i = 0; i < kernel.length; i++)
			kernel[i] = weight;
		
		BufferedImage blured = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
		
		return blured;
	}
	
	public void setBlur(int blur) {
		processed = blur(image, 2, 0.5f);
	}
	
	public float[] mixColor(float[] a, float[] b, float fac) {
		if(a.length != b.length)
			try {
				throw new Exception("Vector length different");
			} catch (Exception e) {
				e.printStackTrace();
			}
		float[] r = new float[a.length];
		for(int i = 0; i < a.length; i++) {
			r[i] = (a[i] * (1-fac) + b[i]*fac);
		}
		return r;
	}
	
	
	
}
