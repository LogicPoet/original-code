package cn.catguild.mathcalc.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

/**
 * <p>
 *
 * </p>
 *
 * @author zhi.liu
 * @version V1.0
 * @package cn.catguild.mathcalc.chart
 * @description
 * @date Created in 2020-08-31 18:07
 * @copyright Copyright (c) 2020
 * @modified zhi.liu
 */
public class DefaultLineChart extends ApplicationFrame {

    public DefaultLineChart(String applicationTitle , String chartTitle,DefaultCategoryDataset dataset) {
        super(applicationTitle);
        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                "area","price",
                dataset,
                PlotOrientation.VERTICAL,
                true,true,false);

        ChartPanel chartPanel = new ChartPanel( lineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 1300 , 700 ) );
        setContentPane( chartPanel );
    }
}
