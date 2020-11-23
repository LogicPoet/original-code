package com.cat.hadoop.weigth;

import com.cat.hadoop.bean.Feature;
import com.cat.hadoop.bean.FuBean;
import com.cat.hadoop.bean.Instance;
import com.cat.hadoop.mywritable.FeatureArrayWritable;
import com.cat.hadoop.mywritable.TextArrayWritable;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
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
public class WeMapper extends Mapper<LongWritable, Text, IntWritable, IntWritable> {

    private IntWritable maxIt = new IntWritable();
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
        maxIt.set(Integer.parseInt(value.toString()));
        context.write(maxIt,one);
    }

}
