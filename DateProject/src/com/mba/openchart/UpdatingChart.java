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
 
    UpdatingChart.java

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
 * A thread is started after the plot is created which randomly updates
 * the single data series after a specified time.  The example is meant
 * to demonstrate the ability of Openchart2 to handle dynamic data using
 * the MultiScatterDataModel.
 * 
 * @author armstrong
 *
 */
public class UpdatingChart extends JDialog {

	/** The lengths of the initial series */
	public final static int INITIAL_LENGTH = 10;
	
	/** The panel containing our chart */
	ChartPanel chartPanel = null;
	
	/** A custom updating thread */
	Updater updateThread = null;
	
	/** Initializes the dialog and its chart in a ChartPanel dialog */
	public UpdatingChart() {
		super();
		
		// Initialize GUI components
		this.setTitle("Openchart2: Updating Scatter Plot Example");
		
		// Make the dialog a reasonable size
		this.setSize(500, 400);
		
		// Allow resize!  It works seamlessly in Opechart2
		this.setResizable(true);
		
		// The content pane
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		this.setContentPane(contentPane);

		// Make sure that on window close, we dispose of this dialog
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		// Add the components to the content pane
		contentPane.add(getChartPanel(),BorderLayout.CENTER);
		
		// Start the update thread
		updateThread.start();
		
		// Set up a window listener to kill the thread on close
		this.addWindowListener(new java.awt.event.WindowAdapter() { 
			public void windowClosing(java.awt.event.WindowEvent e) {    
				updateThread.stopUpdating();
				dispose();
			}
		});
	}
	
	/** Constructs (if necessary) and returns a valid chart panel */
	private ChartPanel getChartPanel() {
		
		// Create the panel if necessary
		if(chartPanel == null) {
			
			// Create a MultiScatterDataModel
			MultiScatterDataModel model = new MultiScatterDataModel(); 
			
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
			
			// Finally, create the update thread
			updateThread = new Updater(chartPanel);
			
		}
		return chartPanel;
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
		UpdatingChart temp = new UpdatingChart();
		
		// Make the dialog visible
		temp.setVisible(true);
	}
	
	/** A private class that extends Thread and performs timed updates of the
	 * chart panel.  This thread is for demonstration purposes only, and is not
	 * meant to represent the only way to provide chart updates.  Other objects
	 * could easily update the scatter plot using similar techniques.
	 * 
	 * @author armstrong
	 */
	private class Updater extends Thread {
		
		/** Number of milliseconds between updates */
		private static final int MS_SLEEP = 1000;

		/** The changing series name */
		private static final String SERIES_NAME = "Dynamic";
		
		/** The panel to update with new data */
		ChartPanel panel = null;
		
		/** Flag to start/stop the thread */
		private boolean running = true;
		
		/** This constructor accepts the ChartPanel object that will be updated.
		 * The entire panel is passed in so that after the data model is updated,
		 * the panel can be signalled to repaint.
		 * 
		 * @param panel the ChartPanel object to update periodically
		 */
		public Updater(ChartPanel panel) {
			this.panel = panel;
			
			// Access the model to set up some prelim data
			MultiScatterDataModel model = (MultiScatterDataModel)panel.getChartDataModel();
			
			
			// Create a series of data to start with and add it to the 
			double[][] xy = getRandomData(20);
			model.addData(xy,SERIES_NAME);
			model.setSeriesLine(SERIES_NAME,false);
			model.setSeriesMarker(SERIES_NAME,true);
		}
		
		/** The main thread loop will force a new data set into the data model
		 * and notify the panel to repaint before sleeping for a specified number
		 * of milliseconds.  */
		public void run() {
			
			// Access the data model locally for updates
			MultiScatterDataModel model = (MultiScatterDataModel)panel.getChartDataModel();
			
			// Loop until a stop is signalled
			while(running) {
			
				// Simply reset the data by using the updateData command
				double[][] xy = getRandomData(20+(int)(20*Math.random()));
				model.updateData(SERIES_NAME,xy);
				
				// Now, just signal a repaint on the ChartPanel
				panel.repaint();
				
				// Sleep for the specified period
				try {
					Thread.sleep(MS_SLEEP);
				} catch (InterruptedException ie) {
					running = false;
				}
			}
		}
		
		/** Changes the start/stop flag to stop the thread */
		public void stopUpdating() {
			running = false;
		}
		
		/** Generates a 2D array of X and random Y values for plotting
		 *
		 * @param size the length of the desired data set
		 * @return 2D array of data (size x 2)
		 */
		private double[][] getRandomData(int size) {
			double[][] return_val = new double[size][2];
			
			for(int i=0;i<size;i++) {
				// Set the x value
				return_val[i][0] = (double)i;
				
				// And the y value
				return_val[i][1] = 100.0*Math.random();
			}
			return return_val;
		}
		
	}
}

