package com.mba.openchart;

/*
    OpenChart2 Java Charting Library and Toolkit
    Copyright (C) 2005-2007 Approximatrix, LLC
    Copyright (C) 2001  Sebastian M�ller
    http://www.approximatrix.com

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 
    RandomChart.java

 */

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import com.approximatrix.charting.model.MultiScatterDataModel;
import com.approximatrix.charting.swing.ChartPanel;
import com.approximatrix.charting.render.MultiScatterChartRenderer;
import com.approximatrix.charting.CoordSystem;

/** Creates a dialog that contains a scatter plot of random data points.
 * This example also places a menu bar in the dialog and a button bar below
 * the plot to allow the addition and removal of series from the chart. 
 * The tutorial demonstrates the ease at which plots can be modified in
 * real-time, including data sets, the title, and the legend.
 * 
 * @author armstrong
 *
 */
public class RandomChart extends JDialog implements ActionListener {

	/** The lengths of the initial series */
	private final static int[] INITIAL_LENGTHS = {10,15,20};
	
	/** A menu item for closing the dialog */
	JMenuItem m_exit = null;
	
	/** Adds more data to the plot */
	JButton b_add = null;
	
	/** Removes data from the plot */
	JButton b_remove = null;
	
	/** The panel containing our chart */
	ChartPanel chartPanel = null;
	
	/** Initializes the dialog and its chart in a ChartPanel dialog */
	public RandomChart() {
		super();
		
		// Initialize GUI components
		this.setTitle("Openchart2: Scatter Plot Example");
		
		// Make the dialog a reasonable size
		this.setSize(500, 400);
		
		// Allow resize!  It works seamlessly in Opechart2
		this.setResizable(true);
		
		// The content pane
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		this.setContentPane(contentPane);
		
		// Set up a menu on this dialog, just to show that everything plays
		// well together.
		JMenuBar menu_bar = new JMenuBar();
		JMenu file_menu = new JMenu();
		file_menu.setText("File");
		m_exit = new JMenuItem();
		m_exit.setText("Exit");
		file_menu.add(m_exit);
		menu_bar.add(file_menu);
		this.setJMenuBar(menu_bar);
		
		// Make sure that on window close, we dispose of this dialog
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		// Set up this class as the action listener for our one menu item
		m_exit.addActionListener(this);
		
		// Create two buttons, add and remove, and set their ActionListener
		// to this class
		b_add = new JButton("Add Series");
		b_remove = new JButton("Remove Series");
		b_add.addActionListener(this);
		b_remove.addActionListener(this);
		
		// Create a panel for the buttons below the chart
		JPanel buttons = new JPanel();
		buttons.add(b_add);
		buttons.add(b_remove);
		
		// Add the components to the content pane
		contentPane.add(buttons,BorderLayout.SOUTH);
		contentPane.add(getChartPanel(),BorderLayout.CENTER);
	}
	
	/** Constructs (if necessary) and returns a valid chart panel */
	private ChartPanel getChartPanel() {
		
		// Create the panel if necessary
		if(chartPanel == null) {
			
			// Create a MultiScatterDataModel
			MultiScatterDataModel model = new MultiScatterDataModel(); 
			
			// Add our default data sets
			for(int i=0;i<INITIAL_LENGTHS.length;i++) {
				// Generate random xy pairs
				double[][] xy = getRandomSeries(INITIAL_LENGTHS[i]);
				
				// Add the data to the model
				model.addData(xy, "Series "+Integer.toString(i+1));
				
				// Set to markers only
				model.setSeriesLine("Series "+Integer.toString(i+1), false);
				model.setSeriesMarker("Series "+Integer.toString(i+1), true);
			}
			
			// Create an associated coordinate system object
			CoordSystem coord = new CoordSystem(model);
			
			// Generate the chart panel
			chartPanel = new ChartPanel(model,"Random Data");
			
			// Add the coordinate system to the chart
			chartPanel.setCoordSystem(coord);
			
			// Add the chart renderer
			MultiScatterChartRenderer renderer = new MultiScatterChartRenderer(coord,model);
			
			// Disable the point buffering in the renderer since this will be a dynamic graph
			renderer.setAllowBuffer(false);
			
			// Add a renderer to the chart
			chartPanel.addChartRenderer(renderer, 0);
			
		}
		return chartPanel;
	}
	
	/** Listener for our single menu item and our two buttons.  The proper action
	 * is taken based on which component caused the event to occur.
	 */
	public void actionPerformed(java.awt.event.ActionEvent e) {
		// If this was our exit button responding, dispose of the dialog
		if(e.getSource() == m_exit)
			dispose();
		
		// If this was the add button, create a new series
		else if(e.getSource() == b_add)
			addSeries();
		
		// If this was the remove button, delete the last series added
		else if(e.getSource() == b_remove)
			removeSeries();
	}
	
	/** Adds another random series to the plot */
	private void addSeries() {
		// Generate a random series of random length
		int length = 15+(int)(Math.random()*20.0);
		double[][] data = getRandomSeries(length);
		
		// Access the model
		MultiScatterDataModel model = (MultiScatterDataModel)chartPanel.getChartDataModel();
		
		// Get the current count of series
		int series_count = model.getDataSetNumber();
		
		if(series_count >= 10) {
			// That should be enough
			getChartPanel().getTitle().setText("That should be enough already...");
		} else {
			// Add the new one
			model.addData(data, "Series "+Integer.toString(series_count+1));
			
			// Set to markers only
			model.setSeriesLine("Series "+Integer.toString(series_count+1), false);
			model.setSeriesMarker("Series "+Integer.toString(series_count+1), true);		
		}
		
		// Force a redraw
		getChartPanel().repaint();
	}
	
	/** Removes the last series from the chart */
	private void removeSeries() {
		// Access the model
		MultiScatterDataModel model = (MultiScatterDataModel)chartPanel.getChartDataModel();
		
		// Get the current count of series
		int series_count = model.getDataSetNumber();
		
		if(series_count <= 3) {
			// That should be enough
			getChartPanel().getTitle().setText("Don't delete the originals!");
		} else {
			// Removes the last data set
			model.removeData(series_count-1);
		}
		
		// Force a redraw
		getChartPanel().repaint();
	}
	
	/** Returns a 2D array of random numbers for use in scatter plots
	 * 
	 * @param length the number of x,y pairs
	 * @return an array of dim length-by-2 containing random numbers
	 */
	private double[][] getRandomSeries(int length) {
		double[][] return_val = new double[length][2];
		
		for(int i=0;i<length;i++) {
			
			// Scale some random values to be between 0 and 100
			return_val[i][0] = Math.random()*100.0;
			return_val[i][1] = Math.random()*100.0;
		}
		
		return return_val;
	}
	
	/** Main routine to start the demonstration dialog
	 * 
	 * @param args unnecessary command line arguments
	 */
	public static void main(String[] args) {
		
		// Make this application appear native
		try {
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { 
			System.out.println("Change of LNF failed...");
		}
		
		// Construct our bar chart dialog
		RandomChart temp = new RandomChart();
		
		// Make the dialog visible
		temp.setVisible(true);
	}
}

