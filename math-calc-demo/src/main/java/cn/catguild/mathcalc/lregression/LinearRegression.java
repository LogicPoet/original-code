package cn.catguild.mathcalc.lregression;

import cn.catguild.mathcalc.chart.DefaultLineChart;
import cn.catguild.mathcalc.datasource.DataSource;
import cn.catguild.mathcalc.datasource.KV;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 线性回归
 * </p>
 *
 * @author zhi.liu
 * @version V1.0
 * @package cn.catguild.mathcalc.lregression
 * @description
 * @date Created in 2020-09-01 11:47
 * @copyright Copyright (c) 2020
 * @modified zhi.liu
 */
public class LinearRegression {
    public static void main(String[] args) {
        List<Double> doubles = gradientDescent();
        Double a0 = doubles.get(0);
        Double a1 = doubles.get(1);
        // y = a0+a1x
        System.out.println("a0:" + a0);
        System.out.println("a1:" + a1);
        List<KV> fangji = DataSource.getFangji();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (KV kv : fangji) {
            dataset.addValue(kv.getV(), "schools" , String.valueOf(kv.getK()));
            dataset.addValue(a0 + a1 * kv.getK(), "predict" , String.valueOf(kv.getK()));
        }


        DefaultLineChart chart = new DefaultLineChart(
                "house price forecast" ,
                "house price" , dataset);

        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }

    public static List<Double> gradientDescent() {
        // 加载数据集
        List<KV> fangji = DataSource.getFangji();
        int m = fangji.size();
        double r = 0.000005;
        double a0 = 0;
        double a1 = 0;


        while (a1 < 15) {
            double sum0 = 0.0;
            for (int i = 0; i < fangji.size(); i++) {
                sum0 += a0 + a1 * fangji.get(i).getK() - fangji.get(i).getV();
            }

            double sum1 = 0.0;
            for (int i = 0; i < fangji.size(); i++) {
                sum1 += (a0 + a1 * fangji.get(i).getK() - fangji.get(i).getV()) * fangji.get(i).getK();
            }

            a0 = a0 - r * sum0 / m;
            a1 = a1 - r * sum1 / m;
        }

        // y = a0+a1x


        List<Double> list = new LinkedList<>();
        list.add(a0);
        list.add(a1);
        return list;
    }

//    public static void dataSet(){
//        List<KV> fangji = DataSource.getFangji();
//
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//
//        for (KV kv : fangji) {
//            dataset.addValue(kv.getV(), "schools" , String.valueOf(kv.getK()));
//        }
//
//        DefaultLineChart chart = new DefaultLineChart(
//                "house price forecast" ,
//                "house price" , dataset);
//
//        chart.pack();
//        RefineryUtilities.centerFrameOnScreen(chart);
//        chart.setVisible(true);
//
//    }
}
