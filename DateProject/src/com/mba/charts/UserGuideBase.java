
package com.mba.charts;


import java.io.FileOutputStream;
import java.awt.*;
import javax.swing.JLabel;

import org.jCharts.Chart;
import org.jCharts.encoders.PNGEncoder;



public abstract class UserGuideBase
{
    private Panel p = new Panel();
    private JLabel l = new JLabel();
    public UserGuideBase()
    {
        p.setVisible(true);
        p.setSize(500, 500);
        p.setLocation(100, 100);
        p.add(l);
    }
	public static void main( String[] args ) throws Throwable
	{
                System.out.println("This is your base");

                AllCharts allCharts= new AllCharts();
		allCharts.run();

                Legends legends= new Legends();
		legends.run();

                StockCharts stockCharts= new StockCharts();
		stockCharts.run();

//		PieChart pieChart= new PieChart();
//		pieChart.run();

		AxisCharts axisCharts= new AxisCharts();
		axisCharts.run();

		AreaCharts areaCharts= new AreaCharts();
		areaCharts.run();

		BarCharts barCharts= new BarCharts();
		barCharts.run();

		LineCharts lineCharts= new LineCharts();
		lineCharts.run();

		PointCharts pointCharts= new PointCharts();
		pointCharts.run();

		ComboCharts comboCharts= new ComboCharts();
		comboCharts.run();

	}


	abstract void run() throws Throwable;

        void exportImage( Chart chart, String fileName )
	{
	   String extension= ".jpg";
		FileOutputStream fileOutputStream;

		try
		{
			fileOutputStream= new FileOutputStream( fileName + extension );
			PNGEncoder.encode( chart, fileOutputStream );
			fileOutputStream.flush();
			fileOutputStream.close();
		}
		catch( Throwable throwable )
		{
			throwable.printStackTrace();
		}
	}
}