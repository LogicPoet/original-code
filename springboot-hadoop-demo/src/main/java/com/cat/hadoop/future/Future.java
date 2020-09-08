//package com.cat.hadoop.future;
//
//import com.cat.hadoop.bean.Feature;
//import com.cat.hadoop.bean.Instance;
//import javafx.util.Pair;
//import lombok.extern.slf4j.Slf4j;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * @author chosen
// * @date 2020/7/10 3:31 下午
// * <p>
// * 训练算法采用GIS训练算法 + 贝叶斯算法修改
// */
//@Slf4j
//public class Future {
//    /**
//     * 数据源
//     */
//    List<Instance> instanceList = new ArrayList<>();
//    /**
//     * 数据分块[结束，因素 x N]
//     * 结果 和 因素都是标签
//     * 根据多因素数据  做出可能的预测 和推荐不同的是  推荐结果的是已知用户行为
//     * 预测根据用户画像返回
//     */
//    List<Feature> featureList = new ArrayList<>();
//    /**
//     * 因素出现次数
//     */
//    List<Integer> featureCountList = new ArrayList<>();
//    /**
//     * 结果标签
//     */
//    List<String> labels = new ArrayList<>();
//    /**
//     * 因素标签的权重
//     */
//    double[] weight;
//    /**
//     * 一个数据集合里有多少因素标签
//     */
//    int C;
//
//    /**
//     * 加载数据
//     * featureList：特征函数的list
//     * featureCountList:与特征函数一一对应的，特征函数出现的次数
//     * instanceList:样本数据list
//     * labels:类别list
//     *
//     * @param path
//     * @throws IOException
//     */
//    public void loadData(String path) throws IOException {
//
//        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
//        String line = br.readLine();
//        while (line != null) {
//            // 数据分块[结束，因素 x N]
//            // 生成对应result, 因素
//            String[] segs = line.split("\\s");
//            String result = segs[0];
//            List<String> fieldList = new ArrayList<>();
//            for (int i = 1; i < segs.length; ++i) {
//                fieldList.add(segs[i]);
//                // [文章id，标签]
//                Feature feature = new Feature(result, segs[i]);
//                // 判断标签是否重复
//                int index = featureList.indexOf(feature);
//                if (index == -1) {
//                    // 没有这个标签
//                    featureList.add(feature);
//                    featureCountList.add(1);
//                } else {
//                    // 有这个标签
//                    featureCountList.set(index, featureCountList.get(index) + 1);
//                }
//            }
//            if (fieldList.size() > C) {
//                // 标签个数
//                C = fieldList.size();
//            }
//            Instance instance = new Instance(result, fieldList);
//            instanceList.add(instance);
//            // 判断该文章是否已经加入
//            if (labels.indexOf(result) == -1) {
//                labels.add(result);
//            }
//            line = br.readLine();
//        }
//    }
//
//    /**
//     * 训练模型
//     *
//     * @param maxIt 最大迭代次数
//     */
//    public void train(int maxIt) {
//        log.info("===========================预测模型训练开始===========================");
//
//        int size = featureList.size();
//        // 权重初始化
//        weight = new double[size];
//        // 期望数据
//        double[] empiricalE = new double[size];
//        // 模型数据
//        double[] modelE = new double[size];
//
//        //计算因素比例
//        log.info("===========================预测模型-因素比例计算-start===========================");
//        for (int i = 0; i < size; ++i) {
//            empiricalE[i] = (double) featureCountList.get(i) / instanceList.size();
//        }
//        log.info("===========================预测模型-因素比例计算-finish===========================");
//        double[] lastWeight = new double[weight.length];
//        for (int i = 0; i < maxIt; ++i) {
//            log.info("===========================预测模型-第{}次计算模型期望-start===========================", i);
//            computeModeE(modelE);
//            log.info("===========================预测模型-第{}次计算模型期望-finish===========================", i);
//            for (int w = 0; w < weight.length; w++) {
//                lastWeight[w] = weight[w];
//                //权重系数 =  实际值和模型壁纸 * 占比系数
//                weight[w] += 1.0 / C * Math.log(empiricalE[w] / modelE[w]);
//            }
//            if (checkConvergence(lastWeight, weight)) {
//                break;
//            }
//        }
//
//        log.info("===========================预测模型训练完毕===========================");
//    }
//
//    /**
//     * 预测类别
//     *
//     * @param fieldList
//     * @return
//     */
//    public Pair<String, Double>[] predict(List<String> fieldList) {
//        double[] prob = calProb(fieldList);
//        Pair<String, Double>[] pairResult = new Pair[prob.length];
//        for (int i = 0; i < prob.length; ++i) {
//            pairResult[i] = new Pair<>(labels.get(i), prob[i]);
//        }
//
//        return pairResult;
//    }
//
//    /**
//     * 检查是否收敛
//     * 防止过拟合
//     *
//     * @param w1
//     * @param w2
//     * @return 是否收敛
//     */
//    public boolean checkConvergence(double[] w1, double[] w2) {
//        for (int i = 0; i < w1.length; ++i) {
//            // 收敛阀值0.01可自行调整
//            if (Math.abs(w1[i] - w2[i]) >= 0.01)  {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    /**
//     * 计算模型期望，即在当前的特征函数的权重下，计算特征函数的模型期望值。
//     *
//     * @param modelE 储存空间，应当事先分配好内存（之所以不return一个modelE是为了避免重复分配内存）
//     */
//    public void computeModeE(double[] modelE) {
//        Arrays.fill(modelE, 0.0f);
//        log.info("===========================计算模型期望,需要计算{}篇文章-start===========================", instanceList.size());
//        for (int i = 0; i < instanceList.size(); ++i) {
//            log.info("===========================计算第{}篇文章-start===========================", i);
//            List<String> fieldList = instanceList.get(i).fieldList;
//            //计算当前样本X对应所有类别的概率
//            double[] pro = calProb(fieldList);
//            for (int j = 0; j < fieldList.size(); j++) {
//                for (int k = 0; k < labels.size(); k++) {
//                    Feature feature = new Feature(labels.get(k), fieldList.get(j));
//                    int index = featureList.indexOf(feature);
//                    if (index != -1) {
//                        modelE[index] += pro[k] * (1.0 / instanceList.size());
//                    }
//                }
//            }
//            log.info("===========================计算第{}篇文章-finish===========================", i);
//        }
//        log.info("===========================计算模型期望,需要计算{}篇文章-finish===========================", instanceList.size());
//    }
//
//    /**
//     * 计算p(y|x),此时的x指的是instance里的field
//     * 求出各因素占比概率
//     * !! 目前训练数据权重占比 存在list中 日后数据量大时 依然使用hadoop
//     *
//     * @param fieldList
//     * @return
//     */
//    public double[] calProb(List<String> fieldList) {
//        double[] p = new double[labels.size()];
//        // 正则化因子，保证概率和为1
//        double sum = 0;
//        for (int i = 0; i < labels.size(); ++i) {
//            double weightSum = 0;
//            for (String field : fieldList) {
//                Feature feature = new Feature(labels.get(i), field);
//                int index = featureList.indexOf(feature);
//                if (index != -1) {
//                    weightSum += weight[index];
//                }
//            }
//            p[i] = Math.exp(weightSum);
//            sum += p[i];
//        }
//        for (int i = 0; i < p.length; ++i) {
//            p[i] /= sum;
//        }
//        return p;
//    }
//}
//
