
package com.mba.manager.charts;

import com.mba.charts.*;
import java.awt.Paint;
import java.awt.Color;
import java.awt.BasicStroke;

import org.jCharts.chartData.*;
import org.jCharts.properties.*;
import org.jCharts.nonAxisChart.*;


/************************************************************************************
*
*
*************************************************************************************/
public class PieChart extends UserGuideBase
{

        public void run() throws Throwable
	{
		this.borderStroke();
	}


	private void outputChart( PieChart2DProperties pieChart2DProperties, String name) throws Throwable
	{
		double[] data= { 10, 20, 30, 40, 50 };
		String[] labels= { "Vishal", "Stefi", "Trupti", "Abhijeet", "Shubham" };
		Paint[] paints= { Color.lightGray, Color.green, Color.blue, Color.red, Color.yellow };

		PieChartDataSet pieChartDataSet= new PieChartDataSet( "Cars That Own", data, labels, paints, pieChart2DProperties );

		PieChart2D pieChart2D= new PieChart2D( pieChartDataSet, new LegendProperties(), new ChartProperties(), 400, 350 );
		super.exportImage( pieChart2D, "img/" + name );
	}


	/******************************************************************************************/
	private void borderStroke() throws Throwable
	{
		PieChart2DProperties pieChart2DProperties= new PieChart2DProperties();
		pieChart2DProperties.setBorderStroke( new BasicStroke( 4f ) );
		this.outputChart( pieChart2DProperties, "pieChartBorderStroke");
	}
	/******************************************************************************************/
}





