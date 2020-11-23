package cn.catguild.mathcalc.chart;

import cn.catguild.mathcalc.util.ChartUtils;
import cn.catguild.mathcalc.util.Serie;

import java.util.Vector;

/**
 * <p>
 *
 * </p>
 *
 * @author zhi.liu
 * @version V1.0
 * @package cn.catguild.mathcalc.chart
 * @description
 * @date Created in 2020-08-31 16:20
 * @copyright Copyright (c) 2020
 * @modified zhi.liu
 */
public class Demo {
    public static void main(String[] args) {
        Vector<Serie> series = new Vector<>();
        Serie serie = new Serie();
        serie.setName("demo");
        Vector<Object> d = new Vector<>();
        d.add("1");
        serie.setData(d);
        series.add(serie);
        String[] categories = new String[]{"1","2"};
        ChartUtils.createDefaultCategoryDataset(series,categories);
    }
}
