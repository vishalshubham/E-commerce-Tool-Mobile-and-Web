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
 
    ExpensiveChart.java

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
import com.approximatrix.charting.swing.BufferedChartPanel;
import com.approximatrix.charting.render.MultiScatterChartRenderer;
import com.approximatrix.charting.CoordSystem;

/** Creates a dialog that contains a scatter plot of random data points.
 * The data sets in this example will be extra large, meaning that a 
 * buffered chart should be used to allow the layout and rendering to be
 * performed outside the main Swing thread.  
 * 
 * @author armstrong
 *
 */
public class ExpensiveChart extends JDialog implements ActionListener {

	/** The lengths of the initial series */
	private final static int[] INITIAL_LENGTHS = {10000,15000};
	
	/** A menu item for closing the dialog */
	JMenuItem m_exit = null;
	
	/** The panel containing our chart */
	BufferedChartPanel chartPanel = null;
	
	/** Initializes the dialog and its chart in a ChartPanel dialog */
	public ExpensiveChart() {
		super();
		
		// Initialize GUI components
		this.setTitle("Openchart2: An Expensive Plot...");
		
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
		
		contentPane.add(getChartPanel(),BorderLayout.CENTER);
	}
	
	/** Constructs (if necessary) and returns a valid chart panel */
	private BufferedChartPanel getChartPanel() {
		
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
			chartPanel = new BufferedChartPanel(model,"Random Data");
			
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
			return_val[i][0] = Math.random()*1000.0;
			return_val[i][1] = Math.random()*1000.0;
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
		ExpensiveChart temp = new ExpensiveChart();
		
		// Make the dialog visible
		temp.setVisible(true);
	}
}

