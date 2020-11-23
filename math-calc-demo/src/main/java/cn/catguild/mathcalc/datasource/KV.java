package cn.catguild.mathcalc.datasource;

/**
 * <p>
 *
 * </p>
 *
 * @author zhi.liu
 * @version V1.0
 * @package cn.catguild.mathcalc.datasource
 * @description
 * @date Created in 2020-08-31 18:02
 * @copyright Copyright (c) 2020
 * @modified zhi.liu
 */
public class KV {
    private double k;
    private double v;

    public KV() {
    }

    public KV(double k, double v) {
        this.k = k;
        this.v = v;
    }

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }

    public double getV() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
    }
}
