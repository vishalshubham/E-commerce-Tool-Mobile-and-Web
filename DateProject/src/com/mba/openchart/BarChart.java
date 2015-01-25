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
 
    BarChart.java

 */

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import com.approximatrix.charting.model.ObjectChartDataModel;
import com.approximatrix.charting.model.ChartDataModel;
import com.approximatrix.charting.swing.ChartPanel;
import com.approximatrix.charting.render.BarChartRenderer;
import com.approximatrix.charting.CoordSystem;

/** This class implements a simple dialog that displays a bar chart along
 * side a menu.  The chart is rescaled to show 0 to 100 rather than simply
 * allowing autoscaling.  The example is well commented and should be easy
 * to follow.
 * 
 * @author armstrong
 *
 */
public class BarChart extends JDialog implements ActionListener {

	/** Our labels for each bar */
	String[] names = {"Shea","Flick","Will"};
	
	/** The value assocaited with each label */
	double[] values = {10.0,30.0,60.0};
	
	/** A menu item for closing the dialog */
	JMenuItem m_exit = null;
	
	/** Initializes the dialog and its chart in a ChartPanel dialog */
	public BarChart() {
		super();
		
		// Initialize GUI components
		this.setTitle("Openchart2: Bar Chart Example");
		
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
		
		// Resize our data for passing
		// Each series in an ObjectChartDataModel will lie in one column
		Double[][] data = new Double[1][values.length];
		for(int i=0;i<values.length;i++) {
			data[0][i] = new Double(values[i]);
		}
		
		// We need to pass a title for this series as well
		String[] series_names = {"Ohmsfords"};
		
		// Create a ObjectDataModel for the bar chart data
		ChartDataModel model = new ObjectChartDataModel(data, names, series_names); 
		
		// Create an associated coordinate system object
		CoordSystem coord = new CoordSystem(model);
		
		// Create a default chart panel for our bar graph
		ChartPanel chart_panel = new ChartPanel(model,"Disapproval Rating");
		
		// Add the coordinate system to the chart
		chart_panel.setCoordSystem(coord);
		
		// Add a renderer to the chart
		chart_panel.addChartRenderer(new BarChartRenderer(coord,model), 0);
		
		// Add the completed chart panel to our content pane
		contentPane.add(chart_panel,BorderLayout.CENTER);
		
		// Make sure that the y-axis goes from 0 to 100
		// By default, charts are never autoscaled; the axes are simply scaled to
		// constraints.  Manual scaling allows the explicit declaration of the 
		// axes limits.  Note that the x-axis values will remain at the contrained
		// limits after manual scaling is enabled because the manual scaling
		// parameters inherit their default values from the original chart 
		// constraints.
		model.setManualScale(true);
		model.setMinimumValue(new Double(0.0));
		model.setMaximumValue(new Double(100.0));
		
		// Notice that no repainting was specified with the ChartPanel.  All
		// (re)drawing is handled internally in the ChartPanel object, making
		// this code relatively simple.
	}
	
	/** Listener for our single menu item.  When clicked, dispose of this
	 * dialog
	 */
	public void actionPerformed(java.awt.event.ActionEvent e) {
		// If this was our exit button responding, dispose of the dialog
		if(e.getSource() == m_exit)
			dispose();
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
		BarChart temp = new BarChart();
		
		// Make the dialog visible
		temp.setVisible(true);
	}
}
