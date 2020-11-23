package cn.catguild.mathcalc.matrix;

import cn.catguild.mathcalc.chart.DefaultLineChart;
import cn.catguild.mathcalc.chart.LineChart_AWT;
import cn.catguild.mathcalc.datasource.DataSource;
import cn.catguild.mathcalc.datasource.KV;
import org.jblas.DoubleMatrix;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;
import org.junit.Test;

import java.math.MathContext;
import java.util.List;

public class MatrixTest {

    /**
     * 创建一个矩阵，并输出
     */
    @Test
    public void createMatrix() {
        double[] a = new double[]{0.1, 0.3, 1.5, 2.4};
        DoubleMatrix matrix = new DoubleMatrix(a);
        System.out.println(matrix.toString("%f" , "[" , "]" , "]\n[" , " "));
    }

    /**
     * 两个向量相乘
     */
    @Test
    public void vectorMultiplication() {
        double[] a = new double[]{0.1, 0.3, 1.5, 2.4};
        double[] b = new double[]{2.0, 2.2, 1.3, 1.7};
        DoubleMatrix aMatrix = new DoubleMatrix(a);
        DoubleMatrix bMatrix = new DoubleMatrix(b);
        // 转置a向量
        DoubleMatrix transpose = aMatrix.transpose();
        DoubleMatrix muli = transpose.muli(bMatrix);

        System.out.println(muli.toString("%f" , "[" , "]" , "]\n[" , " "));

        // 计算矩阵所有元素的总和
        double sum = muli.sum();
        System.out.println("计算矩阵所有元素的总和: " + sum);
    }

    @Test
    public void linearRegression() {
        List<KV> fangji = DataSource.getFangji();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (KV kv : fangji) {
            dataset.addValue(kv.getV(), "schools" , String.valueOf(kv.getK()));
        }

        DefaultLineChart chart = new DefaultLineChart(
                "house price forecast" ,
                "house price" , dataset);

        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 梯度下降
     * 线性回归
     */
    @Test
    public void gradientDescent() {
        // 加载数据集
        List<KV> fangji = DataSource.getFangji();
        int m = fangji.size();
        double r = 0.5;
        double a0 = 0;
        double a1 = 0;

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


        // y = a0+a1x
    }
}