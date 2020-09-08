package com.cat.hadoop.future;

import com.cat.hadoop.bean.Feature;
import com.cat.hadoop.bean.FuBean;
import com.cat.hadoop.bean.Instance;
import com.cat.hadoop.mywritable.FeatureArrayWritable;
import com.cat.hadoop.mywritable.IntArrayWritable;
import com.cat.hadoop.mywritable.TextArrayWritable;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Mapper<读入文件偏移量类型, 读入文件的数据类型,处理之后输出给框架的数据类型, 处理之后输出给框架的数据类型>
 * 输出类型：key是固定的  LongWritable
 * 输出类型：框架并不知道，需要在job中设置类型
 * <p>
 * 负责加载数据 {key：[string]}
 *
 * @author LZ
 * @date 2020/8/24 13:47
 **/
@Slf4j
public class FuMapper extends Mapper<LongWritable, Text, Text, FuBean> {

    /**
     * 减少对象创建，map每读一行数据都会被调用
     */
    private Text key = new Text();
    private FuBean fuBean = new FuBean();
    private Instance instance = new Instance();
    private Feature feature = new Feature();
    private TextArrayWritable fieldList = new TextArrayWritable();
    private FeatureArrayWritable featureList = new FeatureArrayWritable();
    private int[] index = new int[2];

    /**
     * 处理读入的对象，尽量不要在此方法中new 对象
     *
     * @param key     读入文件偏移量，对应Mapper的第一个泛型
     * @param value   读入文件的数据类型，对应Mapper的第二个泛型
     * @param context 上下文，将处理的结果写给框架
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 将读到的文件转成string类型
        String line = value.toString();
        // 处理成指定格式
        String[] words = line.split("\\s");

        if (words.length > 1) {
            // key = 文章id
            this.key.set(words[0]);
            instance.setLabel(words[0]);
            fuBean.setResultLabel(words[0]);

            Writable[] labelNames = new Writable[words.length - 1];
            Writable[] keyLabel = new Writable[words.length - 1];
            // 将数据写出
            for (int i = 1; i < words.length; i++) {
                labelNames[i - 1] = new Text(words[i]);

                feature.setLabel(words[0]);
                feature.setValue(words[i]);

                // 查找是否相同的
                indexOf(keyLabel);
                if (-1 == index[0]) {
                    // 第一次加入
                    feature.setCount(1);
                    keyLabel[i - 1] = feature;
                } else {
                    feature.setCount(index[1] + 1);
                    keyLabel[i - 1] = feature;
                }

            }
            fieldList.set(labelNames);
            featureList.set(keyLabel);
            instance.setFieldList(fieldList);
            fuBean.setInstance(instance);

            // 标签总数
            fuBean.setLabelCount(keyLabel.length);
            fuBean.setFeatureList(featureList);

            log.info("map输出：{}==={}", words[0], fuBean);
            context.write(this.key, fuBean);
        }

    }

    /**
     * 查找索引下标
     *
     * @param keyLabel
     * @return 返回索引下标，查不到返回-1
     */
    private void indexOf(Writable[] keyLabel) {
        if (keyLabel.length > 0) {
            for (int i = 0; i < keyLabel.length; i++) {
                if (null == keyLabel[i]) {
                    break;
                }
                String[] split = keyLabel[i].toString().split(",");
                if (feature.getValue().equals(split[1])) {
                    // 有相同的
                    index[0] = i;
                    index[1] = Integer.parseInt(split[2]);
                    break;
                }
            }
        }
        index[0] = -1;
    }
}
