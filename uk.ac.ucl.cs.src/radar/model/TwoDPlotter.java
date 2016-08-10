package radar.model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Map;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class TwoDPlotter extends JFrame {
	public TwoDPlotter(){} 
	public void plot (Model semanticModel, AnalysisResult results){
		float[][] dataset =  new float[2][];
		XYSeriesCollection result= new XYSeriesCollection();
	    
		XYSeries series = new XYSeries("Objective Function Values");
		int i =0;
		for (Map.Entry<Solution, double[]> entry: results.getEvaluatedSolutions().entrySet()){
			float x = (float) (entry.getValue()[0]);
			float y = (float) (entry.getValue()[1]);
			series.add(x, y);
			i++;
		}
		result.addSeries(series);
		
		String chartTitle = "Pareto front for the " + semanticModel.getModelName();
		String Xlabel =   semanticModel.getObjectives().get(0).getLabel();
		String Ylabel =   semanticModel.getObjectives().get(1).getLabel();
		JFreeChart chart = ChartFactory.createScatterPlot(chartTitle, Xlabel, Ylabel, result);
		ChartFrame frame = new ChartFrame("RADAR", chart);
        frame.pack();
        frame.setVisible(true);
	}
	public void plotAll (Model semanticModel, AnalysisResult results){
		try{
			String chartTitle = "Pareto front for the " + semanticModel.getModelName();
			String Xlabel =  semanticModel.getObjectives().get(0).getLabel();
			String Ylabel =  semanticModel.getObjectives().get(1).getLabel();
			
			final ChartPanel chartPanel = createDemoPanel(chartTitle, populateDataSeries(results), Xlabel,Ylabel);
	        chartPanel.setPreferredSize(new Dimension(400, 500));
	        chartPanel.setRangeZoomable(true);
	        this.add(chartPanel, BorderLayout.CENTER);
	        this.pack();
	        this.setLocationRelativeTo(null);
	        this.setVisible(true);
			
		}catch (IllegalArgumentException e){
			throw new IllegalArgumentException (e.getMessage());
		}catch (Exception e){
			throw new RuntimeException (e.getMessage());
		} 
	}
	 private void adjustAxis(NumberAxis axis, boolean vertical) {
	        
		 Font font = new Font("Dialog",Font.PLAIN, 25); 
		 axis.setLabelFont(font);
		 axis.setLabelFont(font);
		 
		 	/*axis.setRange(-3.0, 3.0);
	        axis.setTickUnit(new NumberTickUnit(0.5));
	        axis.setVerticalTickLabels(vertical);*/
	 }
	private XYDataset populateDataSeries (AnalysisResult analysis_result){
		XYSeriesCollection result= new XYSeriesCollection();
	    
		XYSeries series = new XYSeries("Non Dominated Solutions");
		for (int i =0; i < analysis_result.getShortListObjectives().size(); i ++){
			float v_0 = (float) analysis_result.getShortListObjectives().get(i)[0];
			float v_1 = (float) analysis_result.getShortListObjectives().get(i)[1];
			float x = analysis_result.getObjectives().get(0).getIsMinimisation() == true?v_0: -1*v_0;
			float y = analysis_result.getObjectives().get(1).getIsMinimisation() == true?v_1: -1*v_1;
			series.add(x, y);
		}
		
		XYSeries series2 = new XYSeries("Dominated Solutions");
		for (int i =0; i < analysis_result.getEvaluatedObjectives().size(); i ++){
			float v_0 = (float) analysis_result.getEvaluatedObjectives().get(i)[0];
			float v_1 = (float) analysis_result.getEvaluatedObjectives().get(i)[1];
			float x = analysis_result.getObjectives().get(0).getIsMinimisation() == true?v_0: -1*v_0;
			float y = analysis_result.getObjectives().get(1).getIsMinimisation() == true?v_1: -1*v_1;
			series2.add(x, y);
		}
		
		result.addSeries(series);
		result.addSeries(series2);
		
		return result;
	}
	private ChartPanel createDemoPanel(String title, XYDataset dataset,String Xlabel, String Ylabel) {
	        JFreeChart jfreechart = ChartFactory.createScatterPlot(
	            title, Xlabel, Ylabel, dataset,
	            PlotOrientation.VERTICAL, true, true, false);
	        
	        
	        XYPlot xyPlot = (XYPlot) jfreechart.getPlot();
	        xyPlot.setDomainCrosshairVisible(true);
	        xyPlot.setRangeCrosshairVisible(true);
	        
	        XYItemRenderer renderer = xyPlot.getRenderer();
	        renderer.setSeriesPaint(1, Color.green);
	        //renderer.setSeriesPaint(0, Color.blue);
	        
	        renderer.setSeriesVisible(1, true);
	        //renderer.setSeriesVisible(0, true);
	        
	      /*  XYItemRenderer renderer2 = xyPlot.getRenderer();
	        renderer2.setSeriesPaint(0, Color.green);
	        renderer2.setSeriesPaint(1, Color.red);*/
	        
	        //renderer.setSeriesVisible(1, true);;
	        
	        //rend.setSeriesFillPaint(0, Color.black);
	        //rend.setSeriesFillPaint(1, Color.green);
	        
	        
	        adjustAxis((NumberAxis) xyPlot.getDomainAxis(), true);
	        adjustAxis((NumberAxis) xyPlot.getRangeAxis(), false);
	        
	        
	        //xyPlot.setRenderer(0, renderer);
	        //xyPlot.setDataset(0, dataset1());
	        
	        //xyPlot.setRenderer(1, renderer2);
	        //xyPlot.setDataset(1, dataset2());
	        
	        xyPlot.setBackgroundPaint(Color.white);
	        
	        return new ChartPanel(jfreechart);
	    }
}
