package cn.catguild.mathcalc.matrix;

import org.jblas.FloatMatrix;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @author zhi.liu
 * @version V1.0
 * @package cn.catguild.mathcalc.matrix
 * @description
 * @date Created in 2020-09-05 10:26
 * @copyright Copyright (c) 2020
 * @modified zhi.liu
 */
public class SparseMatrix {
    static String basePath = "D:\\Document\\Programme\\Java\\project\\original-code\\math-calc-demo\\src\\main\\java\\cn\\catguild\\mathcalc\\matrix\\";

    public static FloatMatrix loadDataSource() {
        String path = "D:\\Document\\Programme\\Java\\project\\original-code\\math-calc-demo\\src\\main\\java\\cn\\catguild\\mathcalc\\matrix\\weight.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(new File(path)))) {
            List<String> collect = br.lines().skip(1).collect(Collectors.toList());
            int col = collect.get(0).split(",").length;
            float[][] x = new float[collect.size()][col];
            for (int i = 0; i < collect.size(); i++) {
                String[] split = collect.get(i).split(",");
                for (int j = 0; j < split.length; j++) {
                    try {
                        x[i][j] = Float.parseFloat(split[j]);
                    } catch (NumberFormatException e) {
                        x[i][j] = 0;
                    }
                }
            }
            collect = null;

            FloatMatrix floatMatrix = new FloatMatrix(x);
            x = null;
            System.gc();
            return floatMatrix;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws InterruptedException {
        FloatMatrix X = loadDataSource();
        Thread.sleep(1_000);
        FloatMatrix XCOPY = new FloatMatrix();
        XCOPY.copy(X);
        FloatMatrix gei = XCOPY.gt(0);
        XCOPY = null;
        System.gc();
        Thread.sleep(1_000);
        FloatMatrix XT = gei.transpose();
        FloatMatrix muli = X.muli(XT);




    }
}
