import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.chart.plot.PlotOrientation;

import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart extends ApplicationFrame{

	public LineChart(String applicationTitle, String chartTitle) {
		super(applicationTitle);
		
		JFreeChart lineChart = ChartFactory.createLineChart(chartTitle, "Seconds Elapsed","Ping (ms)", new DefaultCategoryDataset(), PlotOrientation.VERTICAL,true,true,false);
		
		ChartPanel chartPanel = new ChartPanel( lineChart );
		chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
		setContentPane( chartPanel );
		
	}
	
	/**
	 * Repaint this LineChart
	 * @param chart	Create a new chart to replace the old one with
	 * @param dataset The dataset to populate the chart with
	 * @param chartTitle Title of the chart
	 */
	public void paintAgain(LineChart chart, DefaultCategoryDataset dataset, String chartTitle) {
		JFreeChart lineChart = ChartFactory.createLineChart(chartTitle, "Seconds Elapsed","Ping (ms)", dataset, PlotOrientation.VERTICAL,true,true,false);
		ChartPanel chartPanel = new ChartPanel( lineChart );
		chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
		chart.setContentPane( chartPanel );
		chart.pack();
		chart.repaint();
	}

}
