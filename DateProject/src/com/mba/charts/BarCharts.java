
package com.mba.charts;

import org.jCharts.axisChart.AxisChart;
import org.jCharts.axisChart.customRenderers.axisValue.renderers.*;
import org.jCharts.chartData.*;
import org.jCharts.properties.*;
import org.jCharts.properties.util.ChartStroke;
import org.jCharts.test.TestDataGenerator;
import org.jCharts.types.ChartType;

import java.awt.*;


/************************************************************************************
*
*
*************************************************************************************/
public class BarCharts extends AxisCharts
{

	/*****************************************************************************************
	* Tests a 'real' data set and usage.
	*
	* @throws ChartDataException
	******************************************************************************************/
	public void run() throws ChartDataException
	{
		this.barChart();
		this.stackedBars();
		this.clusteredBars();

		this.barLabels();
		this.barWidths();
		this.barOutlines();
		this.barBackground();
	}


	/*****************************************************************************************
	*
	*
	******************************************************************************************/
	private AxisChart getBarChart( BarChartProperties barChartProperties ) throws ChartDataException
	{
		String[] xAxisLabels= { "1998", "1999", "2000", "2001", "2002", "2003", "2004" };
		String xAxisTitle= "Years";
		String yAxisTitle= "Problems";
		String title= "Micro$oft at Work";
		DataSeries dataSeries = new DataSeries( xAxisLabels, xAxisTitle, yAxisTitle, title );

      double[][] data= new double[][]{ { 250, 45, -36, 66, 145, 80, 55  } };
		String[] legendLabels= { "Bugs" };
		Paint[] paints= TestDataGenerator.getRandomPaints( 1 );

		AxisChartDataSet axisChartDataSet= new AxisChartDataSet( data, legendLabels, paints, ChartType.BAR, barChartProperties );

		dataSeries.addIAxisPlotDataSet( axisChartDataSet );

		ChartProperties chartProperties= new ChartProperties();
		AxisProperties axisProperties= new AxisProperties();
		LegendProperties legendProperties= new LegendProperties();

		AxisChart axisChart= new AxisChart( dataSeries, chartProperties, axisProperties, legendProperties, AxisCharts.width, AxisCharts.height );

		return axisChart;
	}


	/*****************************************************************************************
	*
	*
	******************************************************************************************
	private AxisChart getMultiBarCharts( ChartType chartType, BarChartProperties barChartProperties ) throws ChartDataException
	{
		String[] xAxisLabels= { "1998", "1999", "2000", "2001", "2002", "2003", "2004" };
		String xAxisTitle= "Years";
		String yAxisTitle= "Problems";
		String title= "Micro$oft at Work";
		DataSeries dataSeries = new DataSeries( xAxisLabels, xAxisTitle, yAxisTitle, title );


		double[][] data= new double[][]{ {   } };
		String[] legendLabels= { "Bugs", "Security Holes", "Backdoors" };
		Paint[] paints= TestDataGenerator.getRandomPaints( 3 );

		AxisChartDataSet axisChartDataSet= new AxisChartDataSet( data, legendLabels, paints, chartType, barChartProperties );

		dataSeries.addIAxisPlotDataSet( axisChartDataSet );

		ChartProperties chartProperties= new ChartProperties();
		AxisProperties axisProperties= new AxisProperties();
		LegendProperties legendProperties= new LegendProperties();

		AxisChart axisChart= new AxisChart( dataSeries, chartProperties, axisProperties, legendProperties, AxisCharts.width, AxisCharts.height );

		return axisChart;
	}



	/******************************************************************************************/
	private void barChart() throws ChartDataException
	{
		String[] xAxisLabels= { "1998", "1999", "2000", "2001", "2002", "2003", "2004" };
		String xAxisTitle= "Years";
		String yAxisTitle= "Problems";
		String title= "Micro$oft at Work";
		DataSeries dataSeries = new DataSeries( xAxisLabels, xAxisTitle, yAxisTitle, title );

      double[][] data= new double[][]{ { 250, 45, -36, 66, 145, 80, 55  } };
		String[] legendLabels= { "Bugs" };
		Paint[] paints= new Paint[]{ Color.blue.darker() };
		BarChartProperties barChartProperties= new BarChartProperties();
		AxisChartDataSet axisChartDataSet= new AxisChartDataSet( data, legendLabels, paints, ChartType.BAR, barChartProperties );
		dataSeries.addIAxisPlotDataSet( axisChartDataSet );

		ChartProperties chartProperties= new ChartProperties();
		AxisProperties axisProperties= new AxisProperties();
		LegendProperties legendProperties= new LegendProperties();
		AxisChart axisChart= new AxisChart( dataSeries, chartProperties, axisProperties, legendProperties, AxisCharts.width, AxisCharts.height );

		super.exportImage( axisChart, "img/barChart" );
	}


	/******************************************************************************************/
	private void barLabels() throws ChartDataException
	{
		String[] xAxisLabels= { "1998", "1999", "2000", "2001", "2002", "2003", "2004" };
		String xAxisTitle= "Years";
		String yAxisTitle= "Problems";
		String title= "Micro$oft at Work";
		DataSeries dataSeries = new DataSeries( xAxisLabels, xAxisTitle, yAxisTitle, title );

      double[][] data= new double[][]{ { 250, 45, -36, 66, 145, 80, 55  } };
		String[] legendLabels= { "Bugs" };
		Paint[] paints= new Paint[]{ Color.yellow };
		BarChartProperties barChartProperties= new BarChartProperties();

		ValueLabelRenderer valueLabelRenderer = new ValueLabelRenderer( false, true, -1 );
		valueLabelRenderer.setValueLabelPosition( ValueLabelPosition.AT_TOP );
		valueLabelRenderer.useVerticalLabels( false );
		barChartProperties.addPostRenderEventListener( valueLabelRenderer );


		AxisChartDataSet axisChartDataSet= new AxisChartDataSet( data, legendLabels, paints, ChartType.BAR, barChartProperties );
		dataSeries.addIAxisPlotDataSet( axisChartDataSet );

		ChartProperties chartProperties= new ChartProperties();
		AxisProperties axisProperties= new AxisProperties( true );
		LegendProperties legendProperties= new LegendProperties();
		AxisChart axisChart= new AxisChart( dataSeries, chartProperties, axisProperties, legendProperties, AxisCharts.width, AxisCharts.height );

		super.exportImage( axisChart, "img/barLabels" );
	}



	/******************************************************************************************/
	private void barBackground() throws ChartDataException
	{
		String[] xAxisLabels= { "1998", "1999", "2000", "2001", "2002", "2003", "2004" };
		String xAxisTitle= "Years";
		String yAxisTitle= "Problems";
		String title= "Micro$oft at Work";
		DataSeries dataSeries = new DataSeries( xAxisLabels, xAxisTitle, yAxisTitle, title );

      double[][] data= new double[][]{ { 250, 45, -36, 66, 145, 80, 55  } };
		String[] legendLabels= { "Bugs" };
		Paint[] paints= new Paint[]{ Color.blue.darker() };
		BarChartProperties barChartProperties= new BarChartProperties();

		//---paints over the background of every other bar area.
		BackgroundRenderer backgroundRenderer = new BackgroundRenderer( new Color( 20, 20, 20, 50 ) );
		barChartProperties.addPreRenderEventListener( backgroundRenderer );

		AxisChartDataSet axisChartDataSet= new AxisChartDataSet( data, legendLabels, paints, ChartType.BAR, barChartProperties );
		dataSeries.addIAxisPlotDataSet( axisChartDataSet );

		ChartProperties chartProperties= new ChartProperties();
		AxisProperties axisProperties= new AxisProperties();
		LegendProperties legendProperties= new LegendProperties();
		AxisChart axisChart= new AxisChart( dataSeries, chartProperties, axisProperties, legendProperties, AxisCharts.width, AxisCharts.height );

		super.exportImage( axisChart, "img/barBackground" );
	}


	/**********************************************************************************************/
	private void stackedBars() throws ChartDataException
	{
		String[] xAxisLabels= { "1998", "1999", "2000", "2001", "2002", "2003", "2004" };
		String xAxisTitle= "Years";
		String yAxisTitle= "Problems";
		String title= "Micro$oft at Work";
		DataSeries dataSeries = new DataSeries( xAxisLabels, xAxisTitle, yAxisTitle, title );

		double[][] data= new double[][]{ { 250, 45, 36, 66, 145, 80, 55  }, { 150, 15, 6, 62, 54, 10, 84  }, { 250, 45, 36, 66, 145, 80, 55  } };
		String[] legendLabels= { "Bugs", "Security Holes", "Backdoors" };
		Paint[] paints= TestDataGenerator.getRandomPaints( 3 );
		StackedBarChartProperties stackedBarChartProperties= new StackedBarChartProperties();
		AxisChartDataSet axisChartDataSet= new AxisChartDataSet( data, legendLabels, paints, ChartType.BAR_STACKED, stackedBarChartProperties );
		dataSeries.addIAxisPlotDataSet( axisChartDataSet );

		ChartProperties chartProperties= new ChartProperties();
		AxisProperties axisProperties= new AxisProperties();
		LegendProperties legendProperties= new LegendProperties();
		AxisChart axisChart= new AxisChart( dataSeries, chartProperties, axisProperties, legendProperties, AxisCharts.width, AxisCharts.height );

		super.exportImage( axisChart, "img/stackedBarChart" );
	}


	/**********************************************************************************************/
	private void clusteredBars() throws ChartDataException
	{
		String[] xAxisLabels= { "1998", "1999", "2000", "2001", "2002", "2003", "2004" };
		String xAxisTitle= "Years";
		String yAxisTitle= "Problems";
		String title= "Micro$oft at Work";
		DataSeries dataSeries = new DataSeries( xAxisLabels, xAxisTitle, yAxisTitle, title );

		double[][] data= new double[][]{ { 250, 45, -36, 66, 145, 80, 55  }, { 150, 15, 6, 62, -54, 10, 84  }, { 20, 145, 36, 6, 45, 18, 5  } };
		String[] legendLabels= { "Bugs", "Security Holes", "Backdoors" };
		Paint[] paints= TestDataGenerator.getRandomPaints( 3 );
		ClusteredBarChartProperties clusteredBarChartProperties= new ClusteredBarChartProperties();
		AxisChartDataSet axisChartDataSet= new AxisChartDataSet( data, legendLabels, paints, ChartType.BAR_CLUSTERED, clusteredBarChartProperties );
		dataSeries.addIAxisPlotDataSet( axisChartDataSet );

		ChartProperties chartProperties= new ChartProperties();
		AxisProperties axisProperties= new AxisProperties();
		LegendProperties legendProperties= new LegendProperties();
		AxisChart axisChart= new AxisChart( dataSeries, chartProperties, axisProperties, legendProperties, AxisCharts.width, AxisCharts.height );

		super.exportImage( axisChart, "img/clusteredBarChart" );
	}



	/******************************************************************************************/
	private void barWidths() throws ChartDataException
	{
		BarChartProperties barChartProperties= new BarChartProperties();
		barChartProperties.setWidthPercentage( 1f );

		AxisChart axisChart= this.getBarChart( barChartProperties );

		super.exportImage( axisChart, "img/barChartWidths" );
	}

	/******************************************************************************************/
	private void barOutlines() throws ChartDataException
	{
		BarChartProperties barChartProperties= new BarChartProperties();
		barChartProperties.setShowOutlinesFlag( true );
		ChartStroke outlineChartStroke= new ChartStroke( new BasicStroke( 2.0f ), Color.red );
		barChartProperties.setBarOutlineStroke( outlineChartStroke );

		AxisChart axisChart= this.getBarChart( barChartProperties );

		super.exportImage( axisChart, "img/barChartOutlines" );
	}



}

