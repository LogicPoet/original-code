package com.cat.weigth;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Reducer<输入类型, 输入类型,输出类型,输出类型>
 * 输入类型：就是mapper输出的类型
 * 输出类型：框架并不知道，需要在job中设置类型
 *
 * @author LZ
 * @date 2020/8/24 13:48
 **/
@Slf4j
public class WeReducer extends Reducer<IntWritable, IntWritable, Text, DoubleWritable> {

    /**
     * 权重结果
     */
    Map<String, Double> weight = new ConcurrentHashMap<>();
    Map<String, Double> lastWeight = new ConcurrentHashMap<>();

    /**
     * 权重map的kv
     */
    Text key = new Text();
    DoubleWritable value = new DoubleWritable();


    /**
     * 期望数据
     */
    Map<String, Double> empiricalE = new ConcurrentHashMap<>();
    /**
     * 模型数据
     */
    Map<String, Double> modelE = new ConcurrentHashMap<>();

    //==========================计算数据源================
    /**
     * 数据源
     */
    List<Instance> instanceList = new ArrayList<>();
    /**
     * 数据分块[结束，因素 x N]
     * 结果 和 因素都是标签
     * 根据多因素数据  做出可能的预测 和推荐不同的是  推荐结果的是已知用户行为
     * 预测根据用户画像返回
     */
    List<Feature> featureList = new ArrayList<>();
    /**
     * 因素出现次数
     */
    Map<String, Integer> featureCountList = new ConcurrentHashMap<>();
    /**
     * 结果标签
     */
    List<String> labels = new ArrayList<>();
    /**
     * 一个数据集合里有多少因素标签
     */
    int C;

    private String rootPath = "/hadoop/input/";

    /**
     * 在任务开始时调用一次。
     *
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        // 加载数据源
        Configuration conf = context.getConfiguration();
        loadData(conf.get("forecastDataPath"));
        log.info("===数据源加载完毕===");
    }

    /**
     * 处理mapper输出的数据
     *
     * @param key     对mapper输出分组之后的键
     * @param values  对mapper输出分组之后的集合
     * @param context 上下文
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(IntWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        train(Integer.parseInt(key.toString()));
    }

    /**
     * 在任务结束时调用一次。
     *
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        // 收集结果，写出
        Set<Map.Entry<String, Double>> entries = weight.entrySet();
        for (Map.Entry<String, Double> entry : entries) {
            key.set(entry.getKey());
            value.set(entry.getValue());
            context.write(key, value);
        }
    }

    /**
     * 加载数据
     * featureList：特征函数的list
     * featureCountList:与特征函数一一对应的，特征函数出现的次数
     * instanceList:样本数据list
     * labels:类别list
     *
     * @param path
     * @throws IOException
     */
    private void loadData(String path) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        String line = br.readLine();
        while (line != null) {
            // 数据分块[结束，因素 x N]
            // 生成对应result, 因素
            String[] segs = line.split("\\s");
            String result = segs[0];
            List<String> fieldList = new ArrayList<>();
            for (int i = 1; i < segs.length; ++i) {
                fieldList.add(segs[i]);
                Feature feature = new Feature(result, segs[i]);
                int index = featureList.indexOf(feature);
                if (index == -1) {
                    featureList.add(feature);
                    featureCountList.put(result + "-" + segs[i], 1);
                    // 初始化权重值
                    weight.put(result + "-" + segs[i], 0.0d);
                    modelE.put(result + "-" + segs[i], 0.0d);
                } else {
                    Integer sum = featureCountList.get(result + "-" + segs[i]);
                    featureCountList.put(result + "-" + segs[i], sum + 1);
                }
            }
            if (fieldList.size() > C) {
                C = fieldList.size();
            }
            Instance instance = new Instance(result, fieldList);
            instanceList.add(instance);
            if (labels.indexOf(result) == -1) {
                labels.add(result);
            }
            line = br.readLine();
        }
    }


    /**
     * 训练模型
     *
     * @param maxIt 最大迭代次数
     */
    private void train(int maxIt) {
        log.info("===预测模型训练开始===");

        //计算因素比例
        log.info("===预测模型-因素比例计算-start===");

        featureCountList.forEach((k, v) -> {
            empiricalE.put(k, (double) v / instanceList.size());
        });

        log.info("===预测模型-因素比例计算-finish===");

        for (int i = 0; i < maxIt; ++i) {
            log.info("===预测模型-第{}次计算模型期望-start===", i + 1);
            long startTime = System.currentTimeMillis();
            computeModeE();

            // 计算权重系数
            weight.forEach((k, v) -> {
                // 初始化
                lastWeight.put(k, v);
                //权重系数 =  实际值和模型壁纸 * 占比系数
                v += 1.0 / C * Math.log(empiricalE.get(k) / modelE.get(k));
                weight.put(k, v);
            });

            long endTime = System.currentTimeMillis();
            log.info("===预测模型-第{}次计算模型期望-finish===耗时{}分钟", i + 1, (endTime - startTime) / 1000 / 60);
            if (checkConvergence()) {
                break;
            }
        }

        log.info("===预测模型训练完毕===");
    }

    /**
     * 计算模型期望，即在当前的特征函数的权重下，计算特征函数的模型期望值。
     */
    private void computeModeE() {
        for (int i = 0; i < instanceList.size(); ++i) {
            List<String> fieldList = instanceList.get(i).fieldList;
            //计算当前样本X对应所有类别的概率
            double[] pro = calProb(fieldList);
            for (String s : fieldList) {
                for (int k = 0; k < labels.size(); k++) {
                    Feature feature = new Feature(labels.get(k), s);
                    int index = featureList.indexOf(feature);
                    if (index != -1) {
                        String ke = labels.get(k) + "-" + s;
                        double value = modelE.get(ke);
                        double v = pro[k] * (1.0 / instanceList.size());
                        modelE.put(ke, value + v);
                    }
                }
            }
        }
    }

    /**
     * 计算p(y|x),此时的x指的是instance里的field
     * 求出各因素占比概率
     * !! 目前训练数据权重占比 存在list中 日后数据量大时 依然使用hadoop
     *
     * @param fieldList
     * @return
     */
    private double[] calProb(List<String> fieldList) {
        double[] p = new double[labels.size()];
        // 正则化因子，保证概率和为1
        double sum = 0;
        for (int i = 0; i < labels.size(); ++i) {
            double weightSum = 0;
            for (String field : fieldList) {
                Feature feature = new Feature(labels.get(i), field);
                int index = featureList.indexOf(feature);
                if (index != -1) {
                    weightSum += weight.get(labels.get(i) + "-" + field);
                }
            }
            p[i] = Math.exp(weightSum);
            sum += p[i];
        }
        for (int i = 0; i < p.length; ++i) {
            p[i] /= sum;
        }
        return p;
    }

    /**
     * 检查是否收敛
     * 防止过拟合
     *
     * @return 是否收敛
     */
    private boolean checkConvergence() {
        Set<Map.Entry<String, Double>> entries = lastWeight.entrySet();
        for (Map.Entry<String, Double> entry : entries) {
            String k = entry.getKey();
            Double v = entry.getValue();
            if (Math.abs(v - weight.get(k)) >= 0.01) {
                // 收敛阀值0.01可自行调整
                return false;
            }
        }
        return true;
    }
}
