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
 
    PieChart.java

 */

import javax.swing.JApplet;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.approximatrix.charting.DefaultChart;
import com.approximatrix.charting.CoordSystem;
import com.approximatrix.charting.model.ObjectChartDataModel;
import com.approximatrix.charting.render.PieChartRenderer;

/** This class implements a sample applet which creates a pie chart
 * in the applet region.  The code is relatively well commented so that
 * new users can follow along.
 * 
 * @author armstrong
 *
 */
public class PieChart extends JApplet {

	private static final String[] categories = {"Africa",
												"Asia",
												"Australia",
												"Europe",
												"North America",
												"South America"};
	private static final int[] amount = {40,
										 10,
										 20,
										 40,
										 80,
										 20};

	DefaultChart chart = null;
	
	/** Constructor for our pie chart applet
	 * 
	 */
	public PieChart() {
		// Initialize the applet
		super();
		
		// Pie Charts represent a special case for considering series.  Specifically, each
		// entry on the pie chart should be treated as a separate data series.  To do this,
		// we create a data array for passing that contains "amount.length" series, each having
		// exactly one data point.  Openchart2 does support multiple points in the data series,
		// creating a composite pie chart, but we'll ignore that for now.
		int data[][] = new int[amount.length][1];
		for(int i=0;i<amount.length;i++) {
			data[i][0] = amount[i];
		}
		
		// The column_id array holds the "x-axis labels" for each data point in each series.
		// Since this is a pie chart, however, the x-axis labels will remain unused.
		String[] column_id = {"Continental Amounts"};
		
		// Create a new data model for the chart.  The data array contains "amount.length" series
		// with 1 data point each.  The column_id variable contains a single entry that will not
		// actually be used.  The categories array contains the name of each series, and here it
		// will be used to label each pie slice.
		ObjectChartDataModel model = new ObjectChartDataModel(data, column_id, categories);
		
		// Now we have a complete model, so construct the proper chart object
		chart = new DefaultChart(model,"Pie Chart Example");
		
		// Create a pie chart renderer
		PieChartRenderer renderer = new PieChartRenderer(model);
		
		// Finally, add a pie chart renderer to our chart
		// Place the renderer at the z coordinate '0'
		chart.addChartRenderer(renderer, 0);
		
		// A coordinate system is necessary for any chart, regardless of type.  A pie chart,
		// however, really doesn't need to use one.  After creating the default CoordSystem
		// object, turn off all axis drawing since we don't need to see the axes.
		CoordSystem coord = new CoordSystem(model);
		coord.setPaintAxes(false);
		chart.setCoordSystem(coord);
		
		// Our graph is now constructed
	}
	
	/** Override the paint routine to draw the graph
	 */
	public void paint(Graphics g) {
		super.paint(g);
		
		// Pass the drawing bounds into the chart
		chart.setBounds(new Rectangle(this.getWidth(), this.getHeight()));
		
		// Render the chart
        chart.render((Graphics2D)g);
	}
	
}
