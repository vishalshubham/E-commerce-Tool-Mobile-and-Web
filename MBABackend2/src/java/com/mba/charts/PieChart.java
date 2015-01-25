
package com.mba.charts;

import java.awt.Paint;
import java.awt.Color;
import java.awt.BasicStroke;
import org.jCharts.chartData.*;
import org.jCharts.properties.*;
import org.jCharts.nonAxisChart.*;


public class PieChart extends UserGuideBase
{
        double[] data;
	String[] labels;
        Paint[] paints;
        String name;

	public void run() throws Throwable
	{
		this.borderStroke();
	}

        public PieChart(double[] data1, String[] labels1, String path)
        {
            System.out.println("in piechart");
            data = data1;
            labels = labels1;
            int len = data1.length;
            name = path;
            System.out.println("in piechart==" + name);
            paints = getPaints(len);
        }

	private void outputChart( PieChart2DProperties pieChart2DProperties, String name ) throws Throwable
	{
		PieChartDataSet pieChartDataSet= new PieChartDataSet( "Graph Analysis", data, labels, paints, pieChart2DProperties );
		PieChart2D pieChart2D= new PieChart2D( pieChartDataSet, new LegendProperties(), new ChartProperties(), 400, 350 );
		super.exportImage( pieChart2D, name );
	}
	/******************************************************************************************/
	private void borderStroke() throws Throwable
	{
		PieChart2DProperties pieChart2DProperties= new PieChart2DProperties();
		pieChart2DProperties.setBorderStroke( new BasicStroke( 4f ) );
		this.outputChart( pieChart2DProperties, name/*"D:/Apache Software Foundation/Apache Tomcat 6.0.20/webapps/images/myimg"*/ );
	}
	/******************************************************************************************/

        public static void main(String args[]) throws Throwable
        {
            double[] data1 = {23,50,10,100,20,20,20};
            String[] labels1 = {"Vishal","Stefi","Trupti","Rajnesh","Rajnesh","Rajnesh","Rajnesh"};
            PieChart p = new PieChart(data1, labels1, "hello");
            p.run();
        }

    private Paint[] getPaints(int len)
    {
        switch(len)
        {
            case 1 :    Paint[] paint1= { Color.red};
                        return paint1;
            case 2 :    Paint[] paint2= { Color.red, Color.green};
                        return paint2;
            case 3 :    Paint[] paint3= { Color.red, Color.green, Color.blue};
                        return paint3;
            case 4 :    Paint[] paint4= { Color.red, Color.green, Color.blue, Color.yellow};
                        return paint4;
            case 5 :    Paint[] paint5= { Color.red, Color.green, Color.blue, Color.yellow, Color.orange};
                        return paint5;
            case 6 :    Paint[] paint6= { Color.red, Color.green, Color.blue, Color.yellow, Color.orange, Color.cyan};
                        return paint6;
            case 7 :    Paint[] paint7= { Color.red, Color.green, Color.blue, Color.yellow, Color.orange, Color.cyan, Color.magenta};
                        return paint7;
            case 8 :    Paint[] paint8= { Color.red, Color.green, Color.blue, Color.yellow, Color.orange, Color.cyan, Color.magenta, Color.pink};
                        return paint8;
            case 9 :    Paint[] paint9= { Color.red, Color.green, Color.blue, Color.yellow, Color.orange, Color.cyan, Color.magenta, Color.pink, Color.white};
                        return paint9;
            case 10 :   Paint[] paint10= { Color.red, Color.green, Color.blue, Color.yellow, Color.orange, Color.cyan, Color.magenta, Color.pink, Color.white, Color.black};
                        return paint10;
            case 11 :   Paint[] paint11= { Color.red, Color.green, Color.blue, Color.yellow, Color.orange, Color.cyan, Color.magenta, Color.pink, Color.white, Color.black, Color.gray};
                        return paint11;
            case 12 :   Paint[] paint12= { Color.red, Color.green, Color.blue, Color.yellow, Color.orange, Color.cyan, Color.magenta, Color.pink, Color.white, Color.black, Color.gray, Color.lightGray};
                        return paint12;
            case 13 :   Paint[] paint13= { Color.red, Color.green, Color.blue, Color.yellow, Color.orange, Color.cyan, Color.magenta, Color.pink, Color.white, Color.black, Color.gray, Color.lightGray, Color.darkGray};
                        return paint13;
        }
        return null;
    }
}





