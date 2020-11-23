package cn.catguild.mathcalc.matrix;

import org.jblas.DoubleMatrix;

/**
 * <p>
 *
 * </p>
 *
 * @author zhi.liu
 * @version V1.0
 * @package cn.catguild.mathcalc.matrix
 * @description
 * @date Created in 2020-08-31 16:56
 * @copyright Copyright (c) 2020
 * @modified zhi.liu
 */
public class Demo {

    public static void main(String[] args) {
        double[] a = new double[]{0.1, 0.3, 1.5, 2.4};
        double[] b = new double[]{2.0, 2.2, 1.3, 1.7};
        double prediction = 0.0;
        for (int i = 0; i < a.length; i++) {
            prediction += a[i] * b[i];
        }
        System.out.println(prediction);


        DoubleMatrix aMatrix = new DoubleMatrix(a);
        DoubleMatrix bMatrix = new DoubleMatrix(b);
        DoubleMatrix transpose = aMatrix.transpose();
        DoubleMatrix muli = transpose.muli(bMatrix);

        System.out.println(muli.toString("%f","[","]","]\n["," "));

        double sum = muli.sum();
        System.out.println(sum);
    }

}
