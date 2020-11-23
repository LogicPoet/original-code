package cn.catguild.mathcalc.datasource;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author zhi.liu
 * @version V1.0
 * @package cn.catguild.mathcalc.datasource
 * @description
 * @date Created in 2020-08-31 15:27
 * @copyright Copyright (c) 2020
 * @modified zhi.liu
 */
public class DataSource {

    private static final String basePath = "D:\\Document\\Programme\\Java\\project\\original-code" +
            "\\math-calc-demo\\src\\main\\resources\\";

    public static List<KV> getFangji() {
        // 行
        List<KV> result = new LinkedList<>();
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader(new File(basePath + "fangjia.txt")))) {
            bufferedReader.readLine();
            String s = bufferedReader.readLine();
            do {
                String[] split = s.split("\\s");
                KV kv= new KV(Double.parseDouble(split[0]),Double.parseDouble(split[1]));
                result.add(kv);
                s = bufferedReader.readLine();
            }while (null != s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        DataSource.getFangji();
    }
}
