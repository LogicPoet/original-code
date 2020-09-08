package com.cat.hadoop.wordcount;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author LZ
 * @date 2020/8/24 13:48
 **/
@Slf4j
public class WcDriver {

    private static String rootPath = "C:\\Users\\Liuzhi\\Desktop\\github_project\\original-code" +
            "\\springboot-hadoop-demo\\src\\main\\resources\\";

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 1、获取一个job实例
        Job job = Job.getInstance(new Configuration());

        // 2、设置我们的类路径（Classpath）
        job.setJarByClass(WcDriver.class);

        // 3、设置Mapper和Reducer
        job.setMapperClass(WcMapper.class);
        job.setReducerClass(WcReducer.class);

        // 4、设置Mapper和Reducer输出的类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // 5、设置输入和输出的数据路径
        FileInputFormat.setInputPaths(job, new Path(rootPath+"input\\forecast-data.txt"));
        FileOutputFormat.setOutputPath(job, new Path(rootPath+"output"));

        // 6、提交我们的job
        long startTime = System.currentTimeMillis();
        boolean b = job.waitForCompletion(true);
        long endTime = System.currentTimeMillis();
        log.info("运行时间time= {} s", (endTime - startTime) / 1000);
        System.exit(b ? 0 : 1);
    }
}
