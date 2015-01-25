

package com.mba.charts;

import org.jCharts.axisChart.AxisChart;
import org.jCharts.chartData.ChartDataException;
import org.jCharts.chartData.DataSeries;
import org.jCharts.encoders.PNGEncoder;
import org.jCharts.properties.*;
import org.jCharts.properties.util.ChartStroke;
import org.jCharts.types.ChartType;

import java.awt.*;
import java.io.FileOutputStream;

public class AllCharts extends UserGuideBase
{
	private DataSeries dataSeries;



	public AllCharts() throws ChartDataException
	{
		this.dataSeries= AxisCharts.createDataSeries();
		this.dataSeries.addIAxisPlotDataSet( AxisCharts.createAxisChartDataSet( ChartType.AREA, new AreaChartProperties(), 200, 5000 ) );
	}


	/*****************************************************************************************
	* Tests a 'real' data set and usage.
	*
	* @throws Throwable
	******************************************************************************************/
	public void run() throws Throwable
	{
		this.exportImage();
		this.backgroundPaint();
		this.chartBorder();
		this.edgePadding();
		this.chartTitle();
      this.chartTitleWrapping();
	}



	/********************************************************************************************/
	private void exportImage() throws Throwable
	{
		ChartProperties chartProperties= new ChartProperties();
		AxisProperties axisProperties= new AxisProperties();
		LegendProperties legendProperties= new LegendProperties();

		AxisChart axisChart= new AxisChart( this.dataSeries, chartProperties, axisProperties, legendProperties, AxisCharts.width, AxisCharts.height );

		FileOutputStream fileOutputStream= new FileOutputStream( "img/exportPng.png" );
		PNGEncoder.encode( axisChart, fileOutputStream );
		fileOutputStream.flush();
		fileOutputStream.close();
	}


	/********************************************************************************************/
	private void backgroundPaint() throws Throwable
	{
		ChartProperties chartProperties= new ChartProperties();
		chartProperties.setBackgroundPaint( Color.gray );

		AxisProperties axisProperties= new AxisProperties();
		LegendProperties legendProperties= new LegendProperties();

		AxisChart axisChart= new AxisChart( this.dataSeries, chartProperties, axisProperties, legendProperties, AxisCharts.width, AxisCharts.height );

		super.exportImage( axisChart, "img/backgroundPaint" );
	}



	/*******************************************************************************************/
	private void chartBorder() throws Throwable
	{
		ChartProperties chartProperties= new ChartProperties();

		ChartStroke borderStroke= new ChartStroke( new BasicStroke( 1.0f ), Color.black );
		chartProperties.setBorderStroke( borderStroke );

		AxisProperties axisProperties= new AxisProperties();
		LegendProperties legendProperties= new LegendProperties();

		AxisChart axisChart= new AxisChart( this.dataSeries, chartProperties, axisProperties, legendProperties, AxisCharts.width, AxisCharts.height );

		super.exportImage( axisChart, "img/chartBorder" );
	}


	/********************************************************************************************/
	private void edgePadding() throws Throwable
	{
		ChartProperties chartProperties= new ChartProperties();
		chartProperties.setEdgePadding( 20f );

		AxisProperties axisProperties= new AxisProperties();
		LegendProperties legendProperties= new LegendProperties();

		AxisChart axisChart= new AxisChart( this.dataSeries, chartProperties, axisProperties, legendProperties, AxisCharts.width, AxisCharts.height );


		super.exportImage( axisChart, "img/edgePadding" );
	}


	/********************************************************************************************/
	private void chartTitle() throws Throwable
	{
		String[] xAxisLabels= { "1998", "1999", "2000", "2001", "2002", "2003", "2004" };
		String xAxisTitle= "Years";
		String yAxisTitle= "Problems";
		//String title= null;
		DataSeries dataSeries = new DataSeries( xAxisLabels, xAxisTitle, yAxisTitle, null );

		dataSeries.addIAxisPlotDataSet( AxisCharts.createAxisChartDataSet( ChartType.AREA, new AreaChartProperties(), 200, 5000 ) );

		ChartProperties chartProperties= new ChartProperties();
		AxisProperties axisProperties= new AxisProperties();
		LegendProperties legendProperties= new LegendProperties();

		AxisChart axisChart= new AxisChart( dataSeries, chartProperties, axisProperties, legendProperties, AxisCharts.width, AxisCharts.height );

		super.exportImage( axisChart, "img/chartTitle" );
	}


	/********************************************************************************************/
	private void chartTitleWrapping() throws Throwable
	{
		String[] xAxisLabels= { "1998", "1999", "2000", "2001", "2002", "2003", "2004" };
		String xAxisTitle= "Years";
		String yAxisTitle= "Problems";
		//String title= null;
		DataSeries dataSeries = new DataSeries( xAxisLabels, xAxisTitle, yAxisTitle, "This title is so very, very, very, very, very, very, very, very, very, very, long it is going to wrap to the next line" );

		dataSeries.addIAxisPlotDataSet( AxisCharts.createAxisChartDataSet( ChartType.AREA, new AreaChartProperties(), 200, 5000 ) );

		ChartProperties chartProperties= new ChartProperties();
		AxisProperties axisProperties= new AxisProperties();
		LegendProperties legendProperties= new LegendProperties();

		AxisChart axisChart= new AxisChart( dataSeries, chartProperties, axisProperties, legendProperties, AxisCharts.width, AxisCharts.height );

		super.exportImage( axisChart, "img/chartTitleWrapping" );
	}

}






