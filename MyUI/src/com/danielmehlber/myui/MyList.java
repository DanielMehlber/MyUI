package com.danielmehlber.myui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class MyList extends MyPanel {

	private JScrollPane scrollPane;
	private ArrayList<MyPanel> gameEntries;
	private JPanel contentPane;

	public MyList(MyDesign design) {
		super(design);
		setRoundness(10);
		setColorStyle(COLOR_STYLE.DESIGN_BASE);
		setHeader("Spiele");
		setHeaderStyle(MyPanel.HEADER_STYLE.BOX);
		gameEntries = new ArrayList<MyPanel>();
		setLayout(new BorderLayout(0, 0));
		contentPane = new JPanel();
		scrollPane = new JScrollPane(contentPane);
		contentPane.setLayout(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBackground(Color.WHITE);

		add(scrollPane);
		applyDesign();
	}

	/**
	 * Adds Game to ui
	 * 
	 * @param id   ID
	 * @param name Name
	 */
	public void addEntry(MyPanel ge) {
		ge.setLocation(0, (int) (gameEntries.size() * ge.getHeight() * 1.05));
		gameEntries.add(ge);
		contentPane.add(ge);
		scrollPane.revalidate();
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		contentPane.setPreferredSize(new Dimension(200, ge.getHeight() * gameEntries.size()));
	}

	@Override
	public void applyDesign() {
		MyDesign design = getDesign();
		if (contentPane != null)
			contentPane.setBackground(getBackground());
		if (scrollPane != null) {
			scrollPane.setBackground(getBackground());
			JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
			verticalScrollBar.setBackground(design.baseColor);
			verticalScrollBar.setForeground(design.accentColor);
			verticalScrollBar.setBorder(new EmptyBorder(getInsets()));
		} else

			setColor((MyColor) getBackground());
	}

	@Override
	public void reset() {
		setBackground(getDesign().baseColor);
	}

	@Override
	public void setBackground(Color c) {
		super.setBackground(c);
		if (!(contentPane == null || scrollPane == null)) {
			contentPane.setBackground(c);
			scrollPane.setBackground(c);
		}
	}

}
