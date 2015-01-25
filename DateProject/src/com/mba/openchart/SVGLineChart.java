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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

// These are our imports necessary for writing the SVG file
//import org.apache.batik.svggen.SVGGraphics2D;
//import org.apache.batik.dom.GenericDOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DOMImplementation;

// The necessary Openchart2 imports
import com.approximatrix.charting.CoordSystem;
import com.approximatrix.charting.model.MultiScatterDataModel;
import com.approximatrix.charting.render.MultiScatterChartRenderer;
import com.approximatrix.charting.swing.ChartPanel;

/** Creates a dialog that contains a line plot with markers on each point.
 * This example also contains a menu bar that has a "Save As..." option,
 * allowing the user to save the chart as an SVG.  The code requires the
 * proper Batik libraries to be included in the classpath.  The current
 * requirements are:
 * 
 * batik-svggen.jar
 * batik-awt-util.jar
 * batik-css.jar
 * batik-dom.jar
 * batik-svg-dom.jar
 * batik-util.jar
 * batik-xml.jar
 * 
 * The Batik package is used to save the file to SVG (Scalable Vector Graphics)
 * using Batik's SVGGenerator.  The example is meant to illustrate the ability
 * to integrate Openchart2 with Batik.
 * 
 * @author armstrong
 *
 */
public class SVGLineChart extends JDialog implements ActionListener {
	
	/** The size of the data series to create in this example */
	private static final int SERIES_LENGTH = 100;
	
	/** The name for our series */
	private static final String SERIES_NAME = "Line Series";
	
	/** A menu item for closing the dialog */
	private JMenuItem m_exit = null;
	
	/** A menu item fo saving our svg */
	private JMenuItem m_save = null;
	
	/** The panel containing our chart */
	private ChartPanel chartPanel = null;
	
	/** Initializes the dialog and its chart in a ChartPanel dialog */
	public SVGLineChart() {
		super();
		
		// Initialize GUI components
		this.setTitle("Openchart2: Line Chart Example with Batik");
		
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
		
		// The save menu item
		m_save = new JMenuItem();
		m_save.setText("Save SVG...");
		file_menu.add(m_save);
		
		// The exit menu item
		m_exit = new JMenuItem();
		m_exit.setText("Exit");
		file_menu.add(m_exit);
		menu_bar.add(file_menu);
		this.setJMenuBar(menu_bar);
		
		// Make sure that on window close, we dispose of this dialog
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		// Set up this class as the action listener for our exit menu item
		m_exit.addActionListener(this);

		// Again for the save menu item
		m_save.addActionListener(this);
		
		// Add the components to the content pane
		contentPane.add(getChartPanel(),BorderLayout.CENTER);
	}
	
	/** Constructs (if necessary) and returns a valid chart panel */
	private ChartPanel getChartPanel() {
		
		// Create the panel if necessary
		if(chartPanel == null) {
			
			// Create a MultiScatterDataModel
			MultiScatterDataModel model = new MultiScatterDataModel(); 
			
			// Generate a simple series with a bit of variation for drawing
			double[] x = new double[SERIES_LENGTH];
			double[] y = new double[SERIES_LENGTH];
			for(int i=0;i<SERIES_LENGTH;i++) {
				x[i] = (double)i;
				y[i] = (double)i+(10.0*Math.random()-5.0);
			}
			
			// Add our single series
			model.addData(x, y, SERIES_NAME);
			
			// Show lines and points
			model.setSeriesLine(SERIES_NAME, true);
			model.setSeriesMarker(SERIES_NAME, true);
			
			// Create an associated coordinate system object
			CoordSystem coord = new CoordSystem(model);
			
			// Limit our tick marks
			coord.setMaximumXTicks(12);
			coord.setMaximumYTicks(12);
			
			// Direct the chart to draw a complete grid
			coord.setPaintGrid(true);
			
			// Generate the chart panel
			chartPanel = new ChartPanel(model,"Random Data");
			
			// Add the coordinate system to the chart
			chartPanel.setCoordSystem(coord);
			
			// Add the chart renderer
			MultiScatterChartRenderer renderer = new MultiScatterChartRenderer(coord,model);
			
			// Disable the point buffering - there are only 100 points anyway
			renderer.setAllowBuffer(false);
			
			// Add a renderer to the chart
			chartPanel.addChartRenderer(renderer, 0);
			
		}
		return chartPanel;
	}
	
	/** Handles all events caused by our two menu items.  The proper action
	 * is taken based on which component caused the event to occur.
	 */
	public void actionPerformed(ActionEvent e) {
		// If this was our exit button responding, dispose of the dialog
		if(e.getSource() == m_exit)
			dispose();
		
		// Otherwise, save to an SVG
		else if(e.getSource() == m_save)
			saveChart();
	}
	
	/** This method takes the necessary steps to save the chart as an SVG 
	 * using Batik to do all the hard work.  The method is called when the
	 * menu option is selected.  A file chooser will appear, and, if OK is
	 * clicked, a proper SVG is created using the SVGGenerator component of
	 * Batik.
	 */
	private void saveChart() {
		
		// Open a file chooser to select a name
		JFileChooser chooser = new JFileChooser();
		
		chooser.setDialogTitle("Save SVG Output As...");
		chooser.setDialogType(JFileChooser.SAVE_DIALOG);

		if (chooser.showSaveDialog(chooser) == JFileChooser.APPROVE_OPTION) { 
			File to_save = chooser.getSelectedFile();
			
	        // Get a DOMImplementation
//	        DOMImplementation domImpl =
//	            GenericDOMImplementation.getDOMImplementation();

	        // Create an instance of org.w3c.dom.Document
//	        Document document = domImpl.createDocument(null, "svg", null);

	        // Create an instance of the SVG Generator
//	        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
	        
	        // Paint our chart to the SVG Generator
//	        getChartPanel().paint(svgGenerator);

	        // Now write to the file
	        try {
	        	// Open a writer
	        	Writer filewriter = new OutputStreamWriter(new FileOutputStream(to_save), "UTF8");
	        	
	        	// Generate the stream
	        	boolean useCSS = true; // we want to use CSS style attribute
//	        	svgGenerator.stream(filewriter, useCSS);
	        	
	        	// Close the file
	        	filewriter.close();
			
	        } catch(IOException ioe) {
			
	        	// Something went wrong with the file, so report it
	        	JOptionPane.showMessageDialog(this,
	        								  "Problem saving the SVG",
	        								  "Error",
	        								  JOptionPane.ERROR_MESSAGE);
	        	
			}
		}

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
		SVGLineChart temp = new SVGLineChart();
		
		// Make the dialog visible
		temp.setVisible(true);

	}

}
