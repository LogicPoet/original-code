package com.cat.hadoop.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Mapper<读入文件偏移量类型, 读入文件的数据类型,处理之后输出给框架的数据类型, 处理之后输出给框架的数据类型>
 * 输出类型：key是固定的  LongWritable
 * 输出类型：框架并不知道，需要在job中设置类型
 *
 * @author LZ
 * @date 2020/8/24 13:47
 **/
public class WcMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    /**
     * 减少对象创建，map每读一行数据都会被调用
     */
    private Text word = new Text();

    private IntWritable one = new IntWritable(1);

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
        String[] words = line.split(" ");
        // 将数据写出
        for (String word : words) {
            // 写出的类型对应，Mapper的第三个、第四个泛型
            this.word.set(word);
            context.write(this.word, one);
        }
    }
}
