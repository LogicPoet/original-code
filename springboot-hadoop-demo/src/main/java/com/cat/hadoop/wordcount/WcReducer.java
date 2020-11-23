package com.cat.hadoop.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 *
 * Reducer<输入类型, 输入类型,输出类型,输出类型>
 * 输入类型：就是mapper输出的类型
 * 输出类型：框架并不知道，需要在job中设置类型
 *
 * @author LZ
 * @date 2020/8/24 13:48
 **/
public class WcReducer extends Reducer<Text, IntWritable,Text,IntWritable> {

    private IntWritable total = new IntWritable();

    /**
     * 处理mapper输出的数据
     *
     * @param key 对mapper输出分组之后的键
     * @param values 对mapper输出分组之后的集合
     * @param context 上下文
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable value : values) {
            sum += value.get();
        }
        total.set(sum);
        context.write(key,total);
    }
}
